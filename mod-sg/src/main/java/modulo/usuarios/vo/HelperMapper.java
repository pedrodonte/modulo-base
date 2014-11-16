package modulo.usuarios.vo;

import modulo.base.dto.BsTbPersona;
import modulo.base.dto.TbParametro;
import modulo.base.vo.VoParametro;
import modulo.base.vo.VoPersona;
import modulo.consulta_medica.dto.CsTbConsulta;
import modulo.consulta_medica.vo.VoConsulta;
import modulo.usuarios.dto.DTORol;
import modulo.usuarios.dto.DTOUsuario;
import modulo.usuarios.dto.DTORolDeUsuario;

public class HelperMapper {

	public VoRol toVO(DTORol dto) {
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
				e.printStackTrace();
			}
		}
		return vo;
	}

	public DTORol toDTO(VoRol vo) {
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
				e.printStackTrace();
			}
		}
		return dto;
	}

	public VoUser toVO(DTOUsuario dto) {
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

				vo.setCambiarClave((dto.getFlagCambiarClave()) == 1 ? true
						: false);

			} catch (Exception e) {
				System.out.println(dto);
				e.printStackTrace();
			}
		}
		return vo;
	}

	public DTOUsuario toDTO(VoUser vo) {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}

	public VoUserRol toVO(DTORolDeUsuario dto) {
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
				e.printStackTrace();
			}
		}
		return vo;
	}

	public DTORolDeUsuario toDTO(VoUserRol vo) {
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
				e.printStackTrace();
			}
		}
		return dto;
	}

	public BsTbPersona toDTO(VoPersona vo) {
		BsTbPersona dto = null;
		if (vo != null) {
			dto = new BsTbPersona();
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
				e.printStackTrace();
			}
		}
		return dto;
	}

	public VoPersona toVO(BsTbPersona dto) {
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
				e.printStackTrace();
			}
		}
		return vo;
	}

	public VoConsulta toVO(CsTbConsulta dto) {
		VoConsulta vo = null;
		if (dto != null) {
			vo = new VoConsulta();
			try {
				vo.setConsultaId(dto.getConsultaId());
				vo.setFechaConsulta(dto.getFechaConsulta());
				vo.setRegFecInsert(dto.getRegFecInsert());
				vo.setRegFecUpdate(dto.getRegFecUpdate());
				vo.setTxtDesarrollo(dto.getTxtDesarrollo());
				vo.setVoPersona(toVO(dto.getPaciente()));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return vo;
	}

	public CsTbConsulta toDTO(VoConsulta vo) {
		CsTbConsulta dto = null;
		if (vo != null) {
			dto = new CsTbConsulta();
			try {
				dto.setConsultaId(vo.getConsultaId());
				dto.setFechaConsulta(vo.getFechaConsulta());
				dto.setRegFecInsert(vo.getRegFecInsert());
				dto.setRegFecUpdate(vo.getRegFecUpdate());
				dto.setTxtDesarrollo(vo.getTxtDesarrollo());
				dto.setPaciente((toDTO(vo.getVoPersona())));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}

	public TbParametro toDTO(VoParametro vo) {
		TbParametro dto = null;
		if (vo != null) {
			dto = new TbParametro();
			try {
				dto.setNombre(vo.getNombre());
				dto.setParametroID(vo.getParametroID());
				dto.setValor(vo.getValor());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}
	
	public VoParametro toVO(TbParametro dto) {
		VoParametro vo = null;
		if (dto != null) {
			vo = new VoParametro();
			try {
				vo.setNombre(dto.getNombre());
				vo.setParametroID(dto.getParametroID());
				vo.setValor(dto.getValor());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return vo;
	}

}
