package info.pedrodonte.consultas.dto;

import info.pedrodonte.base.dto.BsTbPersona;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: CsTbConsulta
 *
 */
@Entity
@Table(name="cs_tb_consulta")

public class CsTbConsulta implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="persona_id")
	private long consultaId;
	
	@Column(name="txt_desarrollo")
	private String txtDesarrollo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_consulta")
	private Date fechaConsulta;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reg_fec_insert")
	private Date regFecInsert;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reg_fec_update")
	private Date regFecUpdate;
	
	@ManyToOne
	@JoinColumn(name="persona_id",insertable = false, updatable = false)
	private BsTbPersona bsTbPersona;
	
	@Override
	public String toString() {
		return "CsTbConsulta [consultaId=" + consultaId + ", txtDesarrollo="
				+ txtDesarrollo + ", fechaConsulta=" + fechaConsulta
				+ ", regFecInsert=" + regFecInsert + ", regFecUpdate="
				+ regFecUpdate + ", bsTbPersona=" + bsTbPersona + "]";
	}

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

	public Date getFechaConsulta() {
		return fechaConsulta;
	}

	public void setFechaConsulta(Date fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
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

	public BsTbPersona getBsTbPersona() {
		return bsTbPersona;
	}

	public void setBsTbPersona(BsTbPersona bsTbPersona) {
		this.bsTbPersona = bsTbPersona;
	}

}
