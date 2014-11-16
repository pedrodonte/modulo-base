package modulo.base.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import modulo.base.dao.TbParametroDAO;
import modulo.base.dto.TbParametro;
import modulo.base.excepciones.ErrorDelSistemaException;
import modulo.base.excepciones.RegistrosNoEncontradosException;
import modulo.base.vo.VoParametro;
import modulo.usuarios.vo.HelperMapper;

import org.apache.log4j.Logger;

@Stateless
public class ParametroEJBImpl implements ParametroEJB {
	
	HelperMapper helperMapper = new HelperMapper();
	Logger logger = Logger.getLogger(getClass());
	
	@Inject
	TbParametroDAO dao;

	@Override
	public VoParametro nuevoRegistro(VoParametro registro) throws ErrorDelSistemaException {

		TbParametro dto = helperMapper.toDTO(registro);
		dao.save(dto);

		return registro;
	}

	@Override
	public VoParametro actualizarRegistro(VoParametro registro)
			throws ErrorDelSistemaException {

		TbParametro dto = helperMapper.toDTO(registro);
		dao.update(dto);

		return registro;
	}

	@Override
	public void eliminarRegistro(VoParametro registro)
			throws ErrorDelSistemaException {

		throw new UnsupportedOperationException("Metodo Sin Implementar");

	}

	@Override
	public List<VoParametro> obtenerRegistros() throws ErrorDelSistemaException,
			RegistrosNoEncontradosException {

		List<VoParametro> registros = new ArrayList<>();

		for (TbParametro dto : dao.findAll()) {
			VoParametro vo = helperMapper.toVO(dto);
			registros.add(vo);
		}

		return registros;
	}

	@Override
	public VoParametro obtenerRegistroPorID(long id) throws ErrorDelSistemaException,
			RegistrosNoEncontradosException {
		TbParametro dto = dao.find(id);
		return helperMapper.toVO(dto);
	}

}
