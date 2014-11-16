package modulo.reporteria.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import modulo.base.excepciones.ErrorDelSistemaException;
import modulo.base.excepciones.RegistrosNoEncontradosException;
import modulo.reporteria.ejb.ReporteriaEJB;
import modulo.reporteria.vo.VwResumenConsultas;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import util.HelperFecha;

@Named
@SessionScoped
public class ChartView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ReporteriaEJB reporteriaEJB;

	private LineChartModel mdlConsultasSexoDiario = new LineChartModel();
	private Date cpoDesde = new Date();
	private Date cpoHasta = new Date();

	List<VwResumenConsultas> resultados = new ArrayList<VwResumenConsultas>();

	@PostConstruct
	public void init() {
	}

	public void doConsultasSexoDiario(ActionEvent event) {
		try {
			resultados = reporteriaEJB.consultasPeriodo(cpoDesde, cpoHasta);

			mdlConsultasSexoDiario = new LineChartModel();
			
			LineChartSeries todos = new LineChartSeries();
			todos.setFill(true);
			todos.setLabel("Consultas");
			
			for (VwResumenConsultas dato : resultados) {
					todos.set(HelperFecha.toString(dato.getFechaConsulta()),
							dato.getCantidad());
				System.out.println(HelperFecha.toString(dato.getFechaConsulta()));
			}

			mdlConsultasSexoDiario.addSeries(todos);

			mdlConsultasSexoDiario.setTitle("Resumen Consultas diarias");
			mdlConsultasSexoDiario.setLegendPosition("ne");
			mdlConsultasSexoDiario.setStacked(true);
			mdlConsultasSexoDiario.setShowPointLabels(true);

			Axis xAxis = new CategoryAxis("Fecha");
			mdlConsultasSexoDiario.getAxes().put(AxisType.X, xAxis);
			Axis yAxis = mdlConsultasSexoDiario.getAxis(AxisType.Y);
			yAxis.setLabel("Consultas");
			yAxis.setMin(0);
			yAxis.setMax(15);

		} catch (ErrorDelSistemaException | RegistrosNoEncontradosException e) {
			e.printStackTrace();
		}
		System.out.println(resultados.size());
	}

	public LineChartModel getMdlConsultasSexoDiario() {
		return mdlConsultasSexoDiario;
	}

	public Date getCpoDesde() {
		return cpoDesde;
	}

	public void setCpoDesde(Date cpoDesde) {
		this.cpoDesde = cpoDesde;
	}

	public Date getCpoHasta() {
		return cpoHasta;
	}

	public void setCpoHasta(Date cpoHasta) {
		this.cpoHasta = cpoHasta;
	}

	public List<VwResumenConsultas> getResultados() {
		return resultados;
	}

	public void setResultados(List<VwResumenConsultas> resultados) {
		this.resultados = resultados;
	}
	
	

}
