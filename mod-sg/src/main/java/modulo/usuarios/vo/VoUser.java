
package modulo.usuarios.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VoUser implements Cloneable{ 

	private long usuarioId;
	private String clave;
	private String identificador;
	private String email;
	private boolean cambiarClave;
	private Date regFecInsert;
	private Date regFecUpdate;

	//Campo añadido para hacer una gestión integrada de las 2 entidades
	private List<VoRol> roles = new ArrayList<>();

	public long getUsuarioId() {
		return this.usuarioId;
	}
	
	public void setUsuarioId(long usuarioId) {
		this.usuarioId = usuarioId;
	}
	public String getClave() {
		return this.clave;
	}
	
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getIdentificador() {
		return this.identificador;
	}
	
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clave == null) ? 0 : clave.hashCode());
		result = prime * result
				+ ((identificador == null) ? 0 : identificador.hashCode());
		result = prime * result
				+ ((regFecInsert == null) ? 0 : regFecInsert.hashCode());
		result = prime * result
				+ ((regFecUpdate == null) ? 0 : regFecUpdate.hashCode());
		result = prime * result + (int) (usuarioId ^ (usuarioId >>> 32));
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
		VoUser other = (VoUser) obj;
		if (clave == null) {
			if (other.clave != null)
				return false;
		} else if (!clave.equals(other.clave))
			return false;
		if (identificador == null) {
			if (other.identificador != null)
				return false;
		} else if (!identificador.equals(other.identificador))
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
		if (usuarioId != other.usuarioId)
			return false;
		return true;
	}

	public List<VoRol> getRoles() {
		return roles;
	}

	public void setRoles(List<VoRol> roles) {
		this.roles = roles;
	}

	public boolean isCambiarClave() {
		return cambiarClave;
	}

	public void setCambiarClave(boolean cambiarClave) {
		this.cambiarClave = cambiarClave;
	}
	
}
