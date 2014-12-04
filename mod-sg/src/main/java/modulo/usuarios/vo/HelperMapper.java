package modulo.usuarios.vo;

import modulo.base.dto.DTOPersona;
import modulo.base.dto.TbParametro;
import modulo.base.excepciones.HelperMapeoException;
import modulo.base.vo.VoParametro;
import modulo.base.vo.VoPersona;
import modulo.consulta_medica.dto.DTOConsulta;
import modulo.consulta_medica.dto.DTOPrestadorMedico;
import modulo.consulta_medica.vo.VoConsulta;
import modulo.consulta_medica.vo.VoPrestadorMedico;
import modulo.usuarios.dto.DTORol;
import modulo.usuarios.dto.DTOUsuario;
import modulo.usuarios.dto.DTORolDeUsuario;

public class HelperMapper {

	public VoRol toVO(DTORol dto) throws HelperMapeoException {
		VoRol vo = null;
		if (dto != null) {
			vo = new VoRol();
			try {
				vo.setRolId(dto.getRolId());
				vo.setRegFecUpdate(dto.getRegFecUpdate());
				vo.setNombre(dto.getNombre());
				vo.setIdentificador(dto.getIdentificador());
				vo.setDescripcion(dto.getDescripcion());
				vo.setRegFecInsert(dto.getRegFecInsert());

			} catch (Exception e) {
				controlarException(e);
			}
		}
		return vo;
	}

	public DTORol toDTO(VoRol vo) throws HelperMapeoException {
		DTORol dto = null;
		if (vo != null) {
			dto = new DTORol();
			try {
				dto.setRolId(vo.getRolId());
				dto.setRegFecUpdate(vo.getRegFecUpdate());
				dto.setNombre(vo.getNombre());
				dto.setIdentificador(vo.getIdentificador());
				dto.setDescripcion(vo.getDescripcion());
				dto.setRegFecInsert(vo.getRegFecInsert());

			} catch (Exception e) {
				controlarException(e);
			}
		}
		return dto;
	}

	public VoUser toVO(DTOUsuario dto) throws HelperMapeoException {
		VoUser vo = null;
		if (dto != null) {
			vo = new VoUser();
			try {
				vo.setRegFecUpdate(dto.getRegFecUpdate());
				vo.setIdentificador(dto.getIdentificador());
				vo.setRegFecInsert(dto.getRegFecInsert());
				vo.setClave(dto.getClave());
				vo.setUsuarioId(dto.getUsuarioId());
				vo.setEmail(dto.getEmail());
				vo.setPersonaAsociada(toVO(dto.getDtoPersonaAsociada()));
				vo.setCambiarClave((dto.getFlagCambiarClave()) == 1 ? true
						: false);

			} catch (Exception e) {
				System.out.println(dto);
				controlarException(e);
			}
		}
		return vo;
	}

	public DTOUsuario toDTO(VoUser vo) throws HelperMapeoException {
		DTOUsuario dto = null;
		if (vo != null) {
			dto = new DTOUsuario();
			try {
				dto.setRegFecUpdate(vo.getRegFecUpdate());
				dto.setIdentificador(vo.getIdentificador());
				dto.setRegFecInsert(vo.getRegFecInsert());
				dto.setClave(vo.getClave());
				dto.setUsuarioId(vo.getUsuarioId());
				dto.setEmail(vo.getEmail());
				dto.setFlagCambiarClave(vo.isCambiarClave() ? 1 : 0);
				dto.setDtoPersonaAsociada(toDTO(vo.getPersonaAsociada()));

			} catch (Exception e) {
				controlarException(e);
			}
		}
		return dto;
	}

	public VoUserRol toVO(DTORolDeUsuario dto) throws HelperMapeoException {
		VoUserRol vo = null;
		if (dto != null) {
			vo = new VoUserRol();
			try {
				vo.setRegFecUpdate(dto.getRegFecUpdate());
				vo.setValidoDesde(dto.getValidoDesde());
				vo.setVoUser(toVO(dto.getDtoUsuario()));
				vo.setUsuarioRolId(dto.getUsuarioRolId());
				vo.setValidoHasta(dto.getValidoHasta());
				vo.setRegFecInsert(dto.getRegFecInsert());
				vo.setVoRol(toVO(dto.getDtoRol()));

			} catch (Exception e) {
				controlarException(e);
			}
		}
		return vo;
	}

	public DTORolDeUsuario toDTO(VoUserRol vo) throws HelperMapeoException {
		DTORolDeUsuario dto = null;
		if (vo != null) {
			dto = new DTORolDeUsuario();
			try {
				dto.setRegFecUpdate(vo.getRegFecUpdate());
				dto.setValidoDesde(vo.getValidoDesde());
				dto.setDtoUsuario(toDTO(vo.getVoUser()));
				dto.setUsuarioRolId(vo.getUsuarioRolId());
				dto.setValidoHasta(vo.getValidoHasta());
				dto.setRegFecInsert(vo.getRegFecInsert());
				dto.setDtoRol(toDTO(vo.getVoRol()));

			} catch (Exception e) {
				controlarException(e);
			}
		}
		return dto;
	}

	public DTOPersona toDTO(VoPersona vo) throws HelperMapeoException {
		DTOPersona dto = null;
		if (vo != null) {
			dto = new DTOPersona();
			try {
				dto.setApellidos(vo.getApellidos());
				dto.setFechaNacimiento(vo.getFechaNacimiento());
				dto.setIdentificador(vo.getIdentificador());
				dto.setIdPersona(vo.getIdPersona());
				dto.setNombres(vo.getNombres());
				dto.setRegFecInsert(vo.getRegFecInsert());
				dto.setRegFecUpdate(vo.getRegFecUpdate());
				dto.setSexo(vo.getSexo());

			} catch (Exception e) {
				controlarException(e);
			}
		}
		return dto;
	}

	public VoPersona toVO(DTOPersona dto) throws HelperMapeoException {
		VoPersona vo = null;
		if (dto != null) {
			vo = new VoPersona();
			try {
				vo.setApellidos(dto.getApellidos());
				vo.setFechaNacimiento(dto.getFechaNacimiento());
				vo.setIdentificador(dto.getIdentificador());
				vo.setIdPersona(dto.getIdPersona());
				vo.setNombres(dto.getNombres());
				vo.setRegFecInsert(dto.getRegFecInsert());
				vo.setRegFecUpdate(dto.getRegFecUpdate());
				vo.setSexo(dto.getSexo());
			} catch (Exception e) {
				controlarException(e);
			}
		}
		return vo;
	}

	public VoConsulta toVO(DTOConsulta dto) throws HelperMapeoException {
		VoConsulta vo = null;
		if (dto != null) {
			vo = new VoConsulta();
			try {
				vo.setConsultaId(dto.getConsultaId());
				vo.setFechaInicioConsulta(dto.getFechaInicioConsulta());
				vo.setFechaFinConsulta(dto.getFechaFinConsulta());
				vo.setRegFecInsert(dto.getRegFecInsert());
				vo.setRegFecUpdate(dto.getRegFecUpdate());
				vo.setTxtDesarrollo(dto.getTxtDesarrollo());
				vo.setVoPersona(toVO(dto.getPaciente()));

			} catch (Exception e) {
				controlarException(e);
			}
		}
		return vo;
	}

	public DTOConsulta toDTO(VoConsulta vo) throws HelperMapeoException {
		DTOConsulta dto = null;
		if (vo != null) {
			dto = new DTOConsulta();
			try {
				dto.setConsultaId(vo.getConsultaId());
				dto.setFechaInicioConsulta(vo.getFechaInicioConsulta());
				dto.setFechaFinConsulta(vo.getFechaFinConsulta());
				dto.setRegFecInsert(vo.getRegFecInsert());
				dto.setRegFecUpdate(vo.getRegFecUpdate());
				dto.setTxtDesarrollo(vo.getTxtDesarrollo());
				dto.setPaciente((toDTO(vo.getVoPersona())));
			} catch (Exception e) {
				controlarException(e);
			}
		}
		return dto;
	}

	public TbParametro toDTO(VoParametro vo) throws HelperMapeoException {
		TbParametro dto = null;
		if (vo != null) {
			dto = new TbParametro();
			try {
				dto.setNombre(vo.getNombre());
				dto.setParametroID(vo.getParametroID());
				dto.setValor(vo.getValor());
			} catch (Exception e) {
				controlarException(e);
			}
		}
		return dto;
	}
	
	public VoParametro toVO(TbParametro dto) throws HelperMapeoException {
		VoParametro vo = null;
		if (dto != null) {
			vo = new VoParametro();
			try {
				vo.setNombre(dto.getNombre());
				vo.setParametroID(dto.getParametroID());
				vo.setValor(dto.getValor());
			} catch (Exception e) {
				controlarException(e);
			}
		}
		return vo;
	}

	public DTOPrestadorMedico toDTO(VoPrestadorMedico vo) throws HelperMapeoException {
		DTOPrestadorMedico dto = null;
		if (vo != null) {
			dto = new DTOPrestadorMedico();
			try {
				dto.setEspecialidad(vo.getEspecialidad());
				dto.setProfesion(vo.getProfesion());
				dto.setRegFecInsert(vo.getRegFecInsert());
				dto.setRegFecUpdate(vo.getRegFecUpdate());
				dto.setDtoPersona(toDTO(vo.getVoPersona()));
				dto.setDtoUsuario(toDTO(vo.getVoUsuario()));
			} catch (Exception e) {
				controlarException(e);
			}
		}
		return dto;
	}

	public VoPrestadorMedico toVO(DTOPrestadorMedico dto) throws HelperMapeoException {
		VoPrestadorMedico vo = null;
		if (dto != null) {
			vo = new VoPrestadorMedico();
			try {
				vo.setEspecialidad(dto.getEspecialidad());
				vo.setProfesion(dto.getProfesion());
				vo.setRegFecInsert(dto.getRegFecInsert());
				vo.setRegFecUpdate(dto.getRegFecUpdate());
				vo.setVoPersona(toVO(dto.getDtoPersona() ));
				vo.setVoUsuario(toVO(dto.getDtoUsuario()));
			} catch (Exception e) {
				controlarException(e);
			}
		}
		return vo;
	}
	
	private void controlarException(Exception e) throws HelperMapeoException{
		throw new HelperMapeoException("Error al tratar de convertir entre DTO-VO-DTO", e);
	}

}
