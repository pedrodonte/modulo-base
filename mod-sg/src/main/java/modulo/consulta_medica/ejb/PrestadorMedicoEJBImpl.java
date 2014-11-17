package modulo.consulta_medica.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import modulo.base.excepciones.ErrorDelSistemaException;
import modulo.base.excepciones.RegistrosNoEncontradosException;
import modulo.consulta_medica.dao.DTOPrestadorMedicoDAO;
import modulo.consulta_medica.dto.DTOPrestadorMedico;
import modulo.consulta_medica.vo.VoPrestadorMedico;
import modulo.usuarios.vo.HelperMapper;

import org.apache.log4j.Logger;

@Stateless
public class PrestadorMedicoEJBImpl implements PrestadorMedicoEJB {

	@Inject
	DTOPrestadorMedicoDAO prestadorDAO;

	HelperMapper helperMapper = new HelperMapper();
	Logger logger = Logger.getLogger(getClass());

	@Override
	public VoPrestadorMedico nuevoRegistro(VoPrestadorMedico registro)
			throws ErrorDelSistemaException {

		DTOPrestadorMedico dto = helperMapper.toDTO(registro);
		prestadorDAO.save(dto);
		registro = helperMapper.toVO(dto);

		return registro;

	}

	@Override
	public VoPrestadorMedico actualizarRegistro(VoPrestadorMedico registro)
			throws ErrorDelSistemaException {
		DTOPrestadorMedico dto = helperMapper.toDTO(registro);
		prestadorDAO.update(dto);
		registro = helperMapper.toVO(dto);

		return registro;
	}

	@Override
	public void eliminarRegistro(VoPrestadorMedico registro)
			throws ErrorDelSistemaException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<VoPrestadorMedico> obtenerRegistros()
			throws ErrorDelSistemaException, RegistrosNoEncontradosException {
		List<VoPrestadorMedico> registros = new ArrayList<VoPrestadorMedico>();

		for (DTOPrestadorMedico dto : prestadorDAO.findAll()) {
			VoPrestadorMedico vo = helperMapper.toVO(dto);
			registros.add(vo);
		}

		return registros;
	}

	@Override
	public VoPrestadorMedico obtenerRegistroPorID(long id)
			throws ErrorDelSistemaException, RegistrosNoEncontradosException {
		DTOPrestadorMedico dto = prestadorDAO.find(id);
		return helperMapper.toVO(dto);
	}

}
