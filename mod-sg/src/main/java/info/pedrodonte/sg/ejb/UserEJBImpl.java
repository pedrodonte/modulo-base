package info.pedrodonte.sg.ejb;

import info.pedrodonte.email.SenderServiceEJB;
import info.pedrodonte.email.dto.EmailDTO;
import info.pedrodonte.email.exception.EmailInvalidoException;
import info.pedrodonte.email.exception.SendException;
import info.pedrodonte.protask.excepciones.ErrorDelSistemaException;
import info.pedrodonte.protask.excepciones.RegistrosNoEncontradosException;
import info.pedrodonte.sg.dao.SgTbUserDAO;
import info.pedrodonte.sg.dao.SgTbUserRolDAO;
import info.pedrodonte.sg.dto.SgTbUser;
import info.pedrodonte.sg.dto.SgTbUserRol;
import info.pedrodonte.sg.vo.HelperMapper;
import info.pedrodonte.sg.vo.VoRol;
import info.pedrodonte.sg.vo.VoUser;
import info.pedrodonte.util.HelperFecha;
import info.pedrodonte.util.HelperString;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

@Stateless
public class UserEJBImpl implements UserEJB { // EJB EJBImpl

	@Inject
	SgTbUserDAO sgTbUserDAO;

	@Inject
	SgTbUserRolDAO userRolDAO;

	@Inject
	RolEJB rolEJB;
	@Inject
	UserRolEJB userRolEJB;
	
	@Inject SenderServiceEJB emailService;

	HelperMapper helperMapper = new HelperMapper();
	Logger logger = Logger.getLogger(getClass());

	@Override
	public VoUser nuevoRegistro(VoUser registro)
			throws ErrorDelSistemaException {
		
		String passwordEncriptada = HelperString.generarClaveAleatorea();
		try {
			passwordEncriptada = HelperString.encrypt(passwordEncriptada);
		} catch (Exception e) {
			throw new ErrorDelSistemaException("Error al generar la contraseña");
		}

		registro.setClave(passwordEncriptada);

		SgTbUser dto = helperMapper.toDTO(registro);
		sgTbUserDAO.save(dto);
		registro.setUsuarioId(dto.getUsuarioId());
		
		desasociarVigentes(registro);
		asociarNuevos(registro);

		return registro;
	}

	private void desasociarVigentes(VoUser usuario) {
		try {
			//Buscar los vigentes actuales
			List<VoRol> vigentesActuales = rolEJB.obtenerAsociadosUsuario(usuario);
			
			//Cruzar con los asociandose ahora
			for (VoRol rolVigente : vigentesActuales) {
				if (!usuario.getRoles().contains(rolVigente)) {
					SgTbUserRol relacionUserRol = userRolDAO.buscarRol(
							usuario.getUsuarioId(), rolVigente.getRolId());
					relacionUserRol.setValidoHasta(HelperFecha.getActual());
					userRolDAO.update(relacionUserRol);
				}
			}
			
			//Si un Vigente actual NO esta con los asociandose ahora lo dejamos obsoleto
		} catch (ErrorDelSistemaException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public VoUser actualizarRegistro(VoUser registro)
			throws ErrorDelSistemaException {

		SgTbUser dto = helperMapper.toDTO(registro);
		sgTbUserDAO.update(dto);
		desasociarVigentes(registro);
		asociarNuevos(registro);

		return registro;
	}

	@Override
	public void eliminarRegistro(VoUser registro)
			throws ErrorDelSistemaException {

		throw new UnsupportedOperationException("Metodo Sin Implementar");

	}

	@Override
	public List<VoUser> obtenerRegistros() throws ErrorDelSistemaException,
			RegistrosNoEncontradosException {

		List<VoUser> registros = new ArrayList<>();

		for (SgTbUser dto : sgTbUserDAO.findAll()) {
			VoUser vo = helperMapper.toVO(dto);
			registros.add(vo);
		}

		return registros;
	}

	@Override
	public VoUser obtenerRegistroPorID(long id)
			throws ErrorDelSistemaException, RegistrosNoEncontradosException {
		SgTbUser dto = sgTbUserDAO.find(id);
		return helperMapper.toVO(dto);
	}

	private void asociarNuevos(VoUser usuario) {
		SgTbUser dtoUsuario = helperMapper.toDTO(usuario);

		try {
			// Crea NUEVOS Registro
			// Si NO tiene el ROL en entonces crea un nuevo registro.
			for (VoRol rol : usuario.getRoles()) {
				SgTbUserRol newUserRol = new SgTbUserRol();
				newUserRol.setSgTbUser(dtoUsuario);
				newUserRol.setSgTbRol(helperMapper.toDTO(rol));
				if (!usuarioTieneRol(usuario, rol)) {
					newUserRol.setValidoDesde(HelperFecha.getActual());
					newUserRol.setValidoHasta(HelperFecha.getInfinito());
					userRolDAO.save(newUserRol);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private boolean usuarioTieneRol(VoUser usuario, VoRol rol) {
		SgTbUserRol userRol = null;
		userRol = userRolDAO.buscarRol(usuario.getUsuarioId(), rol.getRolId());
		logger.info("usuarioTieneRol "+usuario.getIdentificador()+" "+rol.getIdentificador());
		logger.info(userRol);
		if (userRol != null) {
			return true;
		}
		return false;
	}

	@Override
	public void generarContrasena(VoUser usuario)
			throws RegistrosNoEncontradosException {
		
		String claveSinEcriptar = "";
		try {
			
			String passwordEncriptada = HelperString.generarClaveAleatorea();
			claveSinEcriptar = new String(passwordEncriptada);
			try {
				passwordEncriptada = HelperString.encrypt(passwordEncriptada);
			} catch (Exception e) {
				throw new RegistrosNoEncontradosException("Error al generar la contraseña");
			}

			
			usuario.setClave(passwordEncriptada);
			
			SgTbUser dto = helperMapper.toDTO(usuario);
			sgTbUserDAO.update(dto);
			
			String asunto = usuario.getIdentificador()+" Generación de Contraseña";
			String cuerpo = usuario.getIdentificador()+" se generó la clave: <b>"+claveSinEcriptar+"</b>";
			String destinatarios = "Pedrodonte <pedrodonte@gmail.com>";
			EmailDTO email = new EmailDTO(asunto, cuerpo, destinatarios);
			emailService.enviarEmail(email);
		} catch (SendException e) {
			e.printStackTrace();
		} catch (EmailInvalidoException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void cambiarContrasena(VoUser usuario, String contrasena,
			String confirmacion) throws RegistrosNoEncontradosException {
		
		
		try {
			
			if (contrasena.equals(confirmacion)) {
				usuario.setClave(HelperString.encrypt(contrasena));
				actualizarRegistro(usuario);
			}
		} catch (ErrorDelSistemaException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public VoUser obtenerRegistroPorIdentificador(String identificador)
			throws RegistrosNoEncontradosException {
		SgTbUser dto = sgTbUserDAO.findByIdentificador(identificador);
		return helperMapper.toVO(dto);
	}

}
