package modulo.usuarios.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import modulo.base.excepciones.ErrorDelSistemaException;
import modulo.base.excepciones.HelperMapeoException;
import modulo.base.excepciones.PersistenciaDAOException;
import modulo.base.excepciones.RegistrosNoEncontradosException;
import modulo.email.SenderServiceEJB;
import modulo.email.dto.EmailDTO;
import modulo.email.exception.EmailInvalidoException;
import modulo.email.exception.SendException;
import modulo.usuarios.dao.DTOUsuarioDAO;
import modulo.usuarios.dao.DTORolDeUsuarioDAO;
import modulo.usuarios.dto.DTOUsuario;
import modulo.usuarios.dto.DTORolDeUsuario;
import modulo.usuarios.vo.HelperMapper;
import modulo.usuarios.vo.VoRol;
import modulo.usuarios.vo.VoUser;

import org.apache.log4j.Logger;

import util.HelperFecha;
import util.HelperString;

@Stateless
public class UserEJBImpl implements UserEJB { // EJB EJBImpl

	@Inject
	DTOUsuarioDAO sgTbUserDAO;

	@Inject
	DTORolDeUsuarioDAO userRolDAO;

	@Inject
	RolEJB rolEJB;
	@Inject
	UserRolEJB userRolEJB;

	@Inject
	SenderServiceEJB emailService;

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

		try {
			DTOUsuario dto = helperMapper.toDTO(registro);
			sgTbUserDAO.save(dto);
			registro.setUsuarioId(dto.getUsuarioId());

			desasociarVigentes(registro);
			asociarNuevos(registro);
		} catch (HelperMapeoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistenciaDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return registro;
	}

	private void desasociarVigentes(VoUser usuario) {
		try {
			// Buscar los vigentes actuales
			List<VoRol> vigentesActuales = rolEJB
					.obtenerAsociadosUsuario(usuario);

			// Cruzar con los asociandose ahora
			for (VoRol rolVigente : vigentesActuales) {
				if (!usuario.getRoles().contains(rolVigente)) {
					
					DTORolDeUsuario relacionUserRol = userRolDAO.buscarRol(
							usuario.getUsuarioId(), rolVigente.getRolId());
					
					relacionUserRol.setValidoHasta(HelperFecha.getActual());
					
					userRolDAO.update(relacionUserRol);
				}
			}

			// Si un Vigente actual NO esta con los asociandose ahora lo dejamos
			// obsoleto
		} catch (ErrorDelSistemaException e) {
			e.printStackTrace();
		} catch (PersistenciaDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public VoUser actualizarRegistro(VoUser registro)
			throws ErrorDelSistemaException {

		try {
			DTOUsuario dto = helperMapper.toDTO(registro);
			sgTbUserDAO.update(dto);
			desasociarVigentes(registro);
			asociarNuevos(registro);
		} catch (HelperMapeoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistenciaDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

		try {
			for (DTOUsuario dto : sgTbUserDAO.findAll()) {
				VoUser vo = helperMapper.toVO(dto);
				registros.add(vo);
			}
		} catch (HelperMapeoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistenciaDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return registros;
	}

	@Override
	public VoUser obtenerRegistroPorID(long id)
			throws ErrorDelSistemaException, RegistrosNoEncontradosException {
		try {
			DTOUsuario dto = sgTbUserDAO.find(id);
			if (dto == null) {
				throw new RegistrosNoEncontradosException("No se encuentran registros para el parametro de entrada:"+id);
			}
			return helperMapper.toVO(dto);
		} catch (HelperMapeoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistenciaDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private void asociarNuevos(VoUser usuario) {
		try {
			DTOUsuario dtoUsuario = helperMapper.toDTO(usuario);

			// Crea NUEVOS Registro
			// Si NO tiene el ROL en entonces crea un nuevo registro.
			for (VoRol rol : usuario.getRoles()) {
				DTORolDeUsuario newUserRol = new DTORolDeUsuario();
				newUserRol.setDtoUsuario(dtoUsuario);
				newUserRol.setDtoRol(helperMapper.toDTO(rol));
				if (!usuarioTieneRol(usuario, rol)) {
					newUserRol.setValidoDesde(HelperFecha.getActual());
					newUserRol.setValidoHasta(HelperFecha.getInfinito());
					userRolDAO.save(newUserRol);
				}
			}
		} catch (HelperMapeoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistenciaDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private boolean usuarioTieneRol(VoUser usuario, VoRol rol) {
		try {
			DTORolDeUsuario userRol = null;
			userRol = userRolDAO.buscarRol(usuario.getUsuarioId(), rol.getRolId());
			logger.info("usuarioTieneRol " + usuario.getIdentificador() + " "
					+ rol.getIdentificador());
			logger.info(userRol);
			if (userRol != null) {
				return true;
			}
		} catch (PersistenciaDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				throw new RegistrosNoEncontradosException(
						"Error al generar la contraseña");
			}

			usuario.setClave(passwordEncriptada);

			DTOUsuario dto = helperMapper.toDTO(usuario);
			sgTbUserDAO.update(dto);

			String asunto = usuario.getIdentificador()
					+ " Generación de Contraseña";
			String cuerpo = usuario.getIdentificador()
					+ " se generó la clave: <b>" + claveSinEcriptar + "</b>";
			String destinatarios = "Pedrodonte <pedrodonte@gmail.com>";
			EmailDTO email = new EmailDTO(asunto, cuerpo, destinatarios);
			emailService.enviarEmail(email);
		} catch (SendException e) {
			e.printStackTrace();
		} catch (EmailInvalidoException e) {
			e.printStackTrace();
		} catch (HelperMapeoException e) {
			e.printStackTrace();
		} catch (PersistenciaDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void cambiarContrasena(VoUser usuario, String contrasena,
			String confirmacion) throws RegistrosNoEncontradosException {

		try {

			if (contrasena.equals(confirmacion)) {
				usuario.setClave(HelperString.encrypt(contrasena));

				DTOUsuario dto = helperMapper.toDTO(usuario);
				sgTbUserDAO.update(dto);

			}
		} catch (ErrorDelSistemaException e) {
			e.printStackTrace();
		} catch (HelperMapeoException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public VoUser obtenerRegistroPorIdentificador(String identificador)
			throws RegistrosNoEncontradosException {
		try {
			DTOUsuario dto = sgTbUserDAO.findByIdentificador(identificador);
			return helperMapper.toVO(dto);
		} catch (HelperMapeoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistenciaDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public VoUser obtenerRegistroPersona(long idPersona)
			throws RegistrosNoEncontradosException {
		try {
			DTOUsuario dto = sgTbUserDAO.findByPersonaID(idPersona);
			if (dto != null) {
				return helperMapper.toVO(dto);
			} else {
				throw new RegistrosNoEncontradosException(
						"Esta Persona NO tiene registros de usuario creado, RUT="
								+ idPersona);
			}
		} catch (HelperMapeoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistenciaDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
