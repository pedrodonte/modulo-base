package modulo.reporteria.vo;

import java.util.Date;

public class VwResumenConsultas {
	
	private int duracion;
	private String duracionExacta;
	private Date fechaConsulta;
	private Date fechaHora;
	private String paciente;
	private String sexo;
	private long vistaId;
	
	private long cantidad;
	
	
	
	public VwResumenConsultas(Date fechaConsulta, long cantidad) {
		super();
		this.fechaConsulta = fechaConsulta;
		this.cantidad = cantidad;
	}

	public VwResumenConsultas(Date fechaConsulta, String sexo, long cantidad) {
		super();
		this.fechaConsulta = fechaConsulta;
		this.sexo = sexo;
		this.cantidad = cantidad;
	}

	public VwResumenConsultas(int duracion, String duracionExacta,
			Date fechaConsulta, Date fechaHora, String paciente, String sexo,
			long vistaId) {
		super();
		this.duracion = duracion;
		this.duracionExacta = duracionExacta;
		this.fechaConsulta = fechaConsulta;
		this.fechaHora = fechaHora;
		this.paciente = paciente;
		this.sexo = sexo;
		this.vistaId = vistaId;
	}
	
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public String getDuracionExacta() {
		return duracionExacta;
	}
	public void setDuracionExacta(String duracionExacta) {
		this.duracionExacta = duracionExacta;
	}
	public Date getFechaConsulta() {
		return fechaConsulta;
	}
	public void setFechaConsulta(Date fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}
	public Date getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}
	public String getPaciente() {
		return paciente;
	}
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public long getVistaId() {
		return vistaId;
	}
	public void setVistaId(long vistaId) {
		this.vistaId = vistaId;
	}

	public long getCantidad() {
		return cantidad;
	}

	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "VwResumenConsultas [duracion=" + duracion + ", duracionExacta="
				+ duracionExacta + ", fechaConsulta=" + fechaConsulta
				+ ", fechaHora=" + fechaHora + ", paciente=" + paciente
				+ ", sexo=" + sexo + ", vistaId=" + vistaId + ", cantidad="
				+ cantidad + "]";
	}
	
	

}
