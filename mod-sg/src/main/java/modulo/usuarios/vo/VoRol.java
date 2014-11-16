
package modulo.usuarios.vo;

import java.util.Date;

public class VoRol implements Cloneable{ 

	private long rolId;
	private String descripcion;
	private String identificador;
	private String nombre;
	private Date regFecInsert;
	private Date regFecUpdate;



	public long getRolId() {
		return this.rolId;
	}
	
	public void setRolId(long rolId) {
		this.rolId = rolId;
	}
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getIdentificador() {
		return this.identificador;
	}
	
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result
				+ ((identificador == null) ? 0 : identificador.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result
				+ ((regFecInsert == null) ? 0 : regFecInsert.hashCode());
		result = prime * result
				+ ((regFecUpdate == null) ? 0 : regFecUpdate.hashCode());
		result = prime * result + (int) (rolId ^ (rolId >>> 32));
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
		VoRol other = (VoRol) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (identificador == null) {
			if (other.identificador != null)
				return false;
		} else if (!identificador.equals(other.identificador))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
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
		if (rolId != other.rolId)
			return false;
		return true;
	}
	
}
