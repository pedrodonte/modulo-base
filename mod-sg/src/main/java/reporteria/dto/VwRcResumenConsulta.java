package reporteria.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the vw_rc_resumen_consultas database table.
 * 
 */
@Entity
@Table(name="vw_rc_resumen_consultas")
@NamedQuery(name="VwRcResumenConsulta.findAll", query="SELECT v FROM VwRcResumenConsulta v")
public class VwRcResumenConsulta implements Serializable {
	private static final long serialVersionUID = 1L;

	private int duracion;

	@Column(name="duracion_exacta")
	private String duracionExacta;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_consulta")
	private Date fechaConsulta;

	@Column(name="fecha_hora")
	private Date fechaHora;

	private String paciente;

	private String sexo;

	@Id
	@Column(name="vista_id")
	private long vistaId;

	public VwRcResumenConsulta() {
	}

	public int getDuracion() {
		return this.duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getDuracionExacta() {
		return this.duracionExacta;
	}

	public void setDuracionExacta(String duracionExacta) {
		this.duracionExacta = duracionExacta;
	}

	public Date getFechaConsulta() {
		return this.fechaConsulta;
	}

	public void setFechaConsulta(Date fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}

	public Date getFechaHora() {
		return this.fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getPaciente() {
		return this.paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public long getVistaId() {
		return this.vistaId;
	}

	public void setVistaId(long vistaId) {
		this.vistaId = vistaId;
	}

}