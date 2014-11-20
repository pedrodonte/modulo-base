package modulo.consulta_medica.vo;

import java.util.Date;

import modulo.base.vo.VoPersona;
import modulo.usuarios.vo.VoUser;

public class VoPrestadorMedico implements Cloneable{
	
	private long prestadorMedicoId;
	
	private Date regFecInsert;
	private Date regFecUpdate;
	
	private VoPersona voPersona = new VoPersona();
	private VoUser voUsuario = new VoUser();
	
	private String profesion;
	private String especialidad;
	
	//datos temporales
	private String rutPersona;
	
	public VoPrestadorMedico() {
		super();
	}

	public long getPrestadorMedicoId() {
		return prestadorMedicoId;
	}
	
	public void setPrestadorMedicoId(long prestadorMedicoId) {
		this.prestadorMedicoId = prestadorMedicoId;
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
	
	public VoUser getVoUsuario() {
		return voUsuario;
	}
	
	public void setVoUsuario(VoUser voUsuario) {
		this.voUsuario = voUsuario;
	}
	
	public String getProfesion() {
		return profesion;
	}
	
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}
	
	public String getEspecialidad() {
		return especialidad;
	}
	
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	
	@Override
	public String toString() {
		return "VoPrestadorMedico [prestadorMedicoId=" + prestadorMedicoId
				+ ", regFecInsert=" + regFecInsert + ", regFecUpdate="
				+ regFecUpdate + ", voPersona=" + voPersona + ", voUsuario="
				+ voUsuario + ", profesion=" + profesion + ", especialidad="
				+ especialidad + "]";
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((especialidad == null) ? 0 : especialidad.hashCode());
		result = prime * result
				+ (int) (prestadorMedicoId ^ (prestadorMedicoId >>> 32));
		result = prime * result
				+ ((profesion == null) ? 0 : profesion.hashCode());
		result = prime * result
				+ ((regFecInsert == null) ? 0 : regFecInsert.hashCode());
		result = prime * result
				+ ((regFecUpdate == null) ? 0 : regFecUpdate.hashCode());
		result = prime * result
				+ ((voPersona == null) ? 0 : voPersona.hashCode());
		result = prime * result
				+ ((voUsuario == null) ? 0 : voUsuario.hashCode());
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
		VoPrestadorMedico other = (VoPrestadorMedico) obj;
		if (especialidad == null) {
			if (other.especialidad != null)
				return false;
		} else if (!especialidad.equals(other.especialidad))
			return false;
		if (prestadorMedicoId != other.prestadorMedicoId)
			return false;
		if (profesion == null) {
			if (other.profesion != null)
				return false;
		} else if (!profesion.equals(other.profesion))
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
		if (voPersona == null) {
			if (other.voPersona != null)
				return false;
		} else if (!voPersona.equals(other.voPersona))
			return false;
		if (voUsuario == null) {
			if (other.voUsuario != null)
				return false;
		} else if (!voUsuario.equals(other.voUsuario))
			return false;
		return true;
	}

	public String getRutPersona() {
		return rutPersona;
	}

	public void setRutPersona(String rutPersona) {
		this.rutPersona = rutPersona;
	}
	
}
