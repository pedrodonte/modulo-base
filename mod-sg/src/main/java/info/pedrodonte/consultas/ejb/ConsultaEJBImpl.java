package info.pedrodonte.consultas.ejb;


import info.pedrodonte.consultas.dao.CsTbConsultaDAO;
import info.pedrodonte.consultas.dto.CsTbConsulta;
import info.pedrodonte.consultas.vo.VoConsulta;
import info.pedrodonte.protask.excepciones.ErrorDelSistemaException;
import info.pedrodonte.protask.excepciones.RegistrosNoEncontradosException;
import info.pedrodonte.sg.vo.HelperMapper;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

@Stateless
public class ConsultaEJBImpl implements ConsultaEJB { // EJB EJBImpl

	@Inject
	CsTbConsultaDAO consultaDAO;

	HelperMapper helperMapper = new HelperMapper();
	Logger logger = Logger.getLogger(getClass());

	@Override
	public VoConsulta nuevoRegistro(VoConsulta registro)
			throws ErrorDelSistemaException {

		CsTbConsulta dto = helperMapper.toDTO(registro);
		consultaDAO.save(dto);
		registro.setConsultaId(dto.getConsultaId());
		
		return registro;
	}

	@Override
	public VoConsulta actualizarRegistro(VoConsulta registro)
			throws ErrorDelSistemaException {

		CsTbConsulta dto = helperMapper.toDTO(registro);
		consultaDAO.update(dto);

		return registro;
	}

	@Override
	public void eliminarRegistro(VoConsulta registro)
			throws ErrorDelSistemaException {

		throw new UnsupportedOperationException("Metodo Sin Implementar");

	}

	@Override
	public List<VoConsulta> obtenerRegistros() throws ErrorDelSistemaException,
			RegistrosNoEncontradosException {

		List<VoConsulta> registros = new ArrayList<>();

		for (CsTbConsulta dto : consultaDAO.findAll()) {
			VoConsulta vo = helperMapper.toVO(dto);
			registros.add(vo);
		}

		return registros;
	}

	@Override
	public VoConsulta obtenerRegistroPorID(long id)
			throws ErrorDelSistemaException, RegistrosNoEncontradosException {
		CsTbConsulta dto = consultaDAO.find(id);
		return helperMapper.toVO(dto);
	}


}
