package info.pedrodonte.sg.vo;

import info.pedrodonte.base.dto.BsTbPersona;
import info.pedrodonte.base.vo.VoPersona;
import info.pedrodonte.sg.dto.SgTbRol;
import info.pedrodonte.sg.dto.SgTbUser;
import info.pedrodonte.sg.dto.SgTbUserRol;

public class HelperMapper {

	public VoRol toVO(SgTbRol dto) {
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

	public SgTbRol toDTO(VoRol vo) {
		SgTbRol dto = null;
		if (vo != null) {
			dto = new SgTbRol();
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

	public VoUser toVO(SgTbUser dto) {
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

	public SgTbUser toDTO(VoUser vo) {
		SgTbUser dto = null;
		if (vo != null) {
			dto = new SgTbUser();
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

	public VoUserRol toVO(SgTbUserRol dto) {
		VoUserRol vo = null;
		if (dto != null) {
			vo = new VoUserRol();
			try {
				vo.setRegFecUpdate(dto.getRegFecUpdate());
				vo.setValidoDesde(dto.getValidoDesde());
				vo.setVoUser(toVO(dto.getSgTbUser()));
				vo.setUsuarioRolId(dto.getUsuarioRolId());
				vo.setValidoHasta(dto.getValidoHasta());
				vo.setRegFecInsert(dto.getRegFecInsert());
				vo.setVoRol(toVO(dto.getSgTbRol()));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return vo;
	}

	public SgTbUserRol toDTO(VoUserRol vo) {
		SgTbUserRol dto = null;
		if (vo != null) {
			dto = new SgTbUserRol();
			try {
				dto.setRegFecUpdate(vo.getRegFecUpdate());
				dto.setValidoDesde(vo.getValidoDesde());
				dto.setSgTbUser(toDTO(vo.getVoUser()));
				dto.setUsuarioRolId(vo.getUsuarioRolId());
				dto.setValidoHasta(vo.getValidoHasta());
				dto.setRegFecInsert(vo.getRegFecInsert());
				dto.setSgTbRol(toDTO(vo.getVoRol()));

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

}
