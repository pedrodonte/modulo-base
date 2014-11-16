package modulo.reporteria.ejb;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Stateless
public class GeneradorReporteEJBImpl implements GeneradorReporteEJB {
	
	//private static final String PATH_DESTINO= "C:\\pcarrasco\\credenciales\\";
	//private static final String RECURSOS  = "src/main/resources/";
	
	@Override
	public String generarCredencialesBiblioteca(long codGrupo) {
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("COD_CURSO", codGrupo);
		
		try {
			return generarReporte("carnet_alumno", parametros, getConexion());
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	private Connection getConexion() throws NamingException, SQLException {
		InitialContext ctx=new InitialContext();	
		DataSource dataSource=(DataSource)ctx.lookup("DS_SOLARIA");
		Connection con=dataSource.getConnection();
		return con;
	}


	public String generarReporte(String nombreReporte, Map<String, Object> param, Connection conexion ) {
		try {
			
			JasperPrint print = JasperFillManager.fillReport(getPathDestino()+nombreReporte+ ".jasper", param, conexion);
			
			String nombreFinalReporte = obtenerNombreFinal(nombreReporte, "pdf");

			String newFileName = getPathDestino() + nombreFinalReporte;
			JasperExportManager.exportReportToPdfFile(print, newFileName);
			
			return nombreFinalReporte;

		} catch (JRException e) {
			e.printStackTrace();
		}

		return null;
	}

	private String getPathDestino() {
		// TODO Auto-generated method stub
		return null;
	}

	private String obtenerNombreFinal(String nombre, String extension) {
		Date date = new Date();
		
		return date.getTime()+"_"+nombre+"."+extension;
	}

}
