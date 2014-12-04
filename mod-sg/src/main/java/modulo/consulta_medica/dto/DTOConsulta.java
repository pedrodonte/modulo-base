package modulo.consulta_medica.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import util.Auditable;
import modulo.base.dto.DTOPersona;

@Entity
@Table(name="cs_tb_consulta")
public class DTOConsulta implements Serializable, Auditable {
	
	private static final long serialVersionUID = 1654L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="consulta_id")
	private long consultaId;
	
	@Column(name="txt_desarrollo")
	private String txtDesarrollo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_inicio_consulta")
	private Date fechaInicioConsulta;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_fin_consulta")
	private Date fechaFinConsulta;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reg_fec_insert")
	private Date regFecInsert;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reg_fec_update")
	private Date regFecUpdate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "persona_paciente_id")
	private DTOPersona paciente;
	
	public long getConsultaId() {
		return consultaId;
	}

	public void setConsultaId(long consultaId) {
		this.consultaId = consultaId;
	}

	public String getTxtDesarrollo() {
		return txtDesarrollo;
	}

	public void setTxtDesarrollo(String txtDesarrollo) {
		this.txtDesarrollo = txtDesarrollo;
	}

	public Date getRegFecInsert() {
		return regFecInsert;
	}

	public void setRegFecInsert(Date regFecInsert) {
		this.regFecInsert = regFecInsert;
	}

	public Date getRegFecUpdate() {
		return regFecUpdate;
	}

	public void setRegFecUpdate(Date regFecUpdate) {
		this.regFecUpdate = regFecUpdate;
	}

	public DTOPersona getPaciente() {
		return paciente;
	}

	public void setPaciente(DTOPersona paciente) {
		this.paciente = paciente;
	}

	public Date getFechaInicioConsulta() {
		return fechaInicioConsulta;
	}

	public void setFechaInicioConsulta(Date fechaInicioConsulta) {
		this.fechaInicioConsulta = fechaInicioConsulta;
	}

	public Date getFechaFinConsulta() {
		return fechaFinConsulta;
	}

	public void setFechaFinConsulta(Date fechaFinConsulta) {
		this.fechaFinConsulta = fechaFinConsulta;
	}

	@Override
	public String toString() {
		return "DTOConsulta [consultaId=" + consultaId + ", txtDesarrollo="
				+ txtDesarrollo + ", fechaInicioConsulta="
				+ fechaInicioConsulta + ", fechaFinConsulta="
				+ fechaFinConsulta + ", regFecInsert=" + regFecInsert
				+ ", regFecUpdate=" + regFecUpdate + ", paciente=" + paciente
				+ "]";
	}
	

}
