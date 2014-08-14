
package info.pedrodonte.sg.vo;

import info.pedrodonte.sg.vo.VoUser;
import info.pedrodonte.sg.vo.VoRol;

import java.util.Date;

public class VoUserRol implements Cloneable{ 

	private long usuarioRolId;
	private Date regFecInsert;
	private Date regFecUpdate;
	private Date validoDesde;
	private Date validoHasta;
	private VoRol voRol;
	private VoUser voUser;



	public long getUsuarioRolId() {
		return this.usuarioRolId;
	}
	
	public void setUsuarioRolId(long usuarioRolId) {
		this.usuarioRolId = usuarioRolId;
	}
	public Date getRegFecInsert() {
		return this.regFecInsert;
	}
	
	public void setRegFecInsert(Date regFecInsert) {
		this.regFecInsert = regFecInsert;
	}
	public Date getRegFecUpdate() {
		return this.regFecUpdate;
	}
	
	public void setRegFecUpdate(Date regFecUpdate) {
		this.regFecUpdate = regFecUpdate;
	}
	public Date getValidoDesde() {
		return this.validoDesde;
	}
	
	public void setValidoDesde(Date validoDesde) {
		this.validoDesde = validoDesde;
	}
	public Date getValidoHasta() {
		return this.validoHasta;
	}
	
	public void setValidoHasta(Date validoHasta) {
		this.validoHasta = validoHasta;
	}
	public VoRol getVoRol() {
		return this.voRol;
	}
	
	public void setVoRol(VoRol voRol) {
		this.voRol = voRol;
	}
	public VoUser getVoUser() {
		return this.voUser;
	}
	
	public void setVoUser(VoUser voUser) {
		this.voUser = voUser;
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
				+ ((regFecInsert == null) ? 0 : regFecInsert.hashCode());
		result = prime * result
				+ ((regFecUpdate == null) ? 0 : regFecUpdate.hashCode());
		result = prime * result + (int) (usuarioRolId ^ (usuarioRolId >>> 32));
		result = prime * result
				+ ((validoDesde == null) ? 0 : validoDesde.hashCode());
		result = prime * result
				+ ((validoHasta == null) ? 0 : validoHasta.hashCode());
		result = prime * result + ((voRol == null) ? 0 : voRol.hashCode());
		result = prime * result + ((voUser == null) ? 0 : voUser.hashCode());
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
		VoUserRol other = (VoUserRol) obj;
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
		if (usuarioRolId != other.usuarioRolId)
			return false;
		if (validoDesde == null) {
			if (other.validoDesde != null)
				return false;
		} else if (!validoDesde.equals(other.validoDesde))
			return false;
		if (validoHasta == null) {
			if (other.validoHasta != null)
				return false;
		} else if (!validoHasta.equals(other.validoHasta))
			return false;
		if (voRol == null) {
			if (other.voRol != null)
				return false;
		} else if (!voRol.equals(other.voRol))
			return false;
		if (voUser == null) {
			if (other.voUser != null)
				return false;
		} else if (!voUser.equals(other.voUser))
			return false;
		return true;
	}
	
}
