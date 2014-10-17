package reporteria.jsf;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

@Named
@SessionScoped
public class JasperMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date cpoDesde = new Date();
	private Date cpoHasta = new Date();
	
	@Inject DataSource dataSource;

	public void doGenerarReporte(ActionEvent event){
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("p_desde", cpoDesde);
		parametros.put("p_hasta", cpoHasta);
		
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reporte/jasper/consultas_diarias.jasper"));
		
		
		try(Connection cxn = dataSource.getConnection()) {
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, cxn);
			
			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			response.addHeader("Content-disposition", "attachment; filename=jsfReporte.pdf");
			
			ServletOutputStream outStream = response.getOutputStream();
			
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outStream);
			exporter.exportReport();
			
			outStream.flush();
			outStream.close();
			FacesContext.getCurrentInstance().responseComplete();
			
		} catch (JRException e) {
			e.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
