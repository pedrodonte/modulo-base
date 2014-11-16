package modulo.reporteria.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import modulo.base.excepciones.ErrorDelSistemaException;
import modulo.base.excepciones.RegistrosNoEncontradosException;
import modulo.reporteria.vo.VwResumenConsultas;
import modulo.usuarios.vo.HelperMapper;

import org.apache.log4j.Logger;

import util.HelperFecha;

@Stateless
public class ReporteriaEJBImpl implements ReporteriaEJB { // EJB EJBImpl

	@Inject
	private EntityManager em;

	HelperMapper helperMapper = new HelperMapper();
	Logger logger = Logger.getLogger(getClass());

	@SuppressWarnings("unchecked")
	@Override
	public List<VwResumenConsultas> consultasPeriodo(Date desde, Date hasta)
			throws ErrorDelSistemaException, RegistrosNoEncontradosException {

		List<VwResumenConsultas> results = new ArrayList<>();
		logger.info("Query de busqueda ");
		String namedQuery = "select NEW reporteria.vo.VwResumenConsultas(m.dia, m.cantidad) "
				+ "from VwCdConsultasDiaria m "
				+ "where m.dia between :desde and :hasta";

		try {
			// Query query = em.createNamedQuery(namedQuery);
			Query query = em.createQuery(namedQuery);
			query.setParameter("desde", desde);
			query.setParameter("hasta", hasta);

			results = (List<VwResumenConsultas>) query.getResultList();

		} catch (NoResultException e) {
			logger.info("No hay registros para la query " + namedQuery);
		} catch (Exception e) {
			logger.info("Error buscando un resultado", e);
		}

		return results;
	}

	/*
	 * (non-Javadoc) select fecha_consulta, sexo, count(1) as cantidad from
	 * vw_rc_resumen_consultas group by fecha_consulta,sexo order by
	 * fecha_consulta desc;
	 * 
	 * @see reporteria.ejb.ReporteriaEJB#consultasPeriodoSexo(java.util.Date,
	 * java.util.Date)
	 */
	@Override
	public List<VwResumenConsultas> consultasPeriodoSexo(Date desde, Date hasta)
			throws ErrorDelSistemaException, RegistrosNoEncontradosException {

		List<Date> fechas = HelperFecha.obtenerFechasDiariasIntervalo(desde,
				hasta);
		for (Date date : fechas) {
			System.out.println(fechas);
		}

		List<VwResumenConsultas> results = new ArrayList<>();
		logger.info("Query de busqueda ");
		String namedQuery = "select "
				+ "NEW reporteria.vo.VwResumenConsultas(v.fechaConsulta, v.sexo, count(v)) "
				+ " from VwRcResumenConsulta v where fechaConsulta between :desde and :hasta "
				+ " group by v.fechaConsulta,v.sexo order by v.fechaConsulta desc";

		logger.info("sql :" + namedQuery);
		logger.info("p1 :" + desde);
		logger.info("p2 :" + hasta);

		try {
			// Query query = em.createNamedQuery(namedQuery);
			Query query = em.createNativeQuery(namedQuery);
			query.setParameter("desde", desde);
			query.setParameter("hasta", hasta);

			results = (List<VwResumenConsultas>) query.getResultList();

		} catch (NoResultException e) {
			logger.info("No hay registros para la query " + namedQuery);
		} catch (Exception e) {
			logger.info("Error buscando un resultado", e);
		}

		return results;
	}

}
