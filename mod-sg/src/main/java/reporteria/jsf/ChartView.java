package reporteria.jsf;

import info.pedrodonte.protask.excepciones.ErrorDelSistemaException;
import info.pedrodonte.protask.excepciones.RegistrosNoEncontradosException;
import info.pedrodonte.util.HelperFecha;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import reporteria.ejb.ReporteriaEJB;
import reporteria.vo.VwResumenConsultas;

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
			resultados = reporteriaEJB.consultasPeriodoSexo(cpoDesde, cpoHasta);

			mdlConsultasSexoDiario = new LineChartModel();
			
			LineChartSeries hombres = new LineChartSeries();
			LineChartSeries mujeres = new LineChartSeries();
			hombres.setFill(true);
			hombres.setLabel("Hombres");
			mujeres.setFill(true);
			mujeres.setLabel("Mujeres");
			
			String fechaActual="";
			long valorHombre=0, valorMujer=0;

			for (VwResumenConsultas dato : resultados) {
				
				if (fechaActual.equals(HelperFecha.toString(dato.getFechaConsulta()))) {
					mujeres.set(fechaActual,valorMujer);
					hombres.set(fechaActual,valorHombre);
					valorHombre=0;
					valorMujer=0;
				}else{
					fechaActual = HelperFecha.toString(dato.getFechaConsulta());
				}
				
				
				
				
				
				if (dato.getSexo().equals("Femenino")) {
					mujeres.set(HelperFecha.toString(dato.getFechaConsulta()),
							dato.getCantidad());
				} else if (dato.getSexo().equals("Masculino")) {
					hombres.set(HelperFecha.toString(dato.getFechaConsulta()),
							dato.getCantidad());
				}
				System.out.println(HelperFecha.toString(dato.getFechaConsulta()));
			}

			mdlConsultasSexoDiario.addSeries(hombres);
			mdlConsultasSexoDiario.addSeries(mujeres);

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

}
