package modulo.consulta_medica.vo;

import java.util.Date;

import util.HelperFecha;
import modulo.base.vo.VoPersona;

public class VoConsulta implements Cloneable {

	private long consultaId;
	private String txtDesarrollo;
	private Date fechaConsulta;
	private Date regFecInsert;
	private Date regFecUpdate;
	private VoPersona voPersona;
	
	private int duracion;
	
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
	public VoPersona getVoPersona() {
		return voPersona;
	}
	public void setVoPersona(VoPersona voPersona) {
		this.voPersona = voPersona;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (consultaId ^ (consultaId >>> 32));
		result = prime * result
				+ ((fechaConsulta == null) ? 0 : fechaConsulta.hashCode());
		result = prime * result
				+ ((regFecInsert == null) ? 0 : regFecInsert.hashCode());
		result = prime * result
				+ ((regFecUpdate == null) ? 0 : regFecUpdate.hashCode());
		result = prime * result
				+ ((txtDesarrollo == null) ? 0 : txtDesarrollo.hashCode());
		result = prime * result
				+ ((voPersona == null) ? 0 : voPersona.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VoConsulta other = (VoConsulta) obj;
		if (consultaId != other.consultaId)
			return false;
		if (fechaConsulta == null) {
			if (other.fechaConsulta != null)
				return false;
		} else if (!fechaConsulta.equals(other.fechaConsulta))
			return false;
		if (regFecInsert == null) {
			if (other.regFecInsert != null)
				return false;
		} else if (!regFecInsert.equals(other.regFecInsert))
			return false;
		if (regFecUpdate == null) {
			if (other.regFecUpdate != null)
				return false;
		} else if (!regFecUpdate.equals(other.regFecUpdate))
			return false;
		if (txtDesarrollo == null) {
			if (other.txtDesarrollo != null)
				return false;
		} else if (!txtDesarrollo.equals(other.txtDesarrollo))
			return false;
		if (voPersona == null) {
			if (other.voPersona != null)
				return false;
		} else if (!voPersona.equals(other.voPersona))
			return false;
		return true;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	@Override
	public String toString() {
		return "VoConsulta [consultaId=" + consultaId + ", txtDesarrollo="
				+ txtDesarrollo + ", fechaConsulta=" + fechaConsulta
				+ ", regFecInsert=" + regFecInsert + ", regFecUpdate="
				+ regFecUpdate + ", voPersona=" + voPersona + "]";
	}
	public int getDuracion() {
		this.duracion = HelperFecha.calcularDiferenciaMinutos(this.fechaConsulta, this.regFecInsert);
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	
	

}
