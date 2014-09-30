package info.pedrodonte.base.vo;

import java.util.Date;

public class VoPersona implements Cloneable{
	
	private int idPersona;
	private String identificador;
	private Date fechaNacimiento;
	private String nombres;
	private String apellidos;
	private int sexo;
	private Date regFecInsert;
	private Date regFecUpdate;
	
	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public int getSexo() {
		return sexo;
	}
	public void setSexo(int sexo) {
		this.sexo = sexo;
	}
	@Override
	public String toString() {
		return "VoPersona [idPersona=" + idPersona + ", identificador="
				+ identificador + ", fechaNacimiento=" + fechaNacimiento
				+ ", nombres=" + nombres + ", apellidos=" + apellidos
				+ ", sexo=" + sexo + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result
				+ ((fechaNacimiento == null) ? 0 : fechaNacimiento.hashCode());
		result = prime * result + idPersona;
		result = prime * result
				+ ((identificador == null) ? 0 : identificador.hashCode());
		result = prime * result + ((nombres == null) ? 0 : nombres.hashCode());
		result = prime * result
				+ ((regFecInsert == null) ? 0 : regFecInsert.hashCode());
		result = prime * result
				+ ((regFecUpdate == null) ? 0 : regFecUpdate.hashCode());
		result = prime * result + sexo;
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
		VoPersona other = (VoPersona) obj;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (fechaNacimiento == null) {
			if (other.fechaNacimiento != null)
				return false;
		} else if (!fechaNacimiento.equals(other.fechaNacimiento))
			return false;
		if (idPersona != other.idPersona)
			return false;
		if (identificador == null) {
			if (other.identificador != null)
				return false;
		} else if (!identificador.equals(other.identificador))
			return false;
		if (nombres == null) {
			if (other.nombres != null)
				return false;
		} else if (!nombres.equals(other.nombres))
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
		if (sexo != other.sexo)
			return false;
		return true;
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
	
	

}
