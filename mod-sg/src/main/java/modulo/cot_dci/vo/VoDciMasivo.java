package modulo.cot_dci.vo;

import java.util.Date;

import modulo.usuarios.vo.VoUser;

public class VoDciMasivo implements Cloneable {

	private long idProceso;
	private Date fechaCreacion;
	private long desdePeriodoRemuneracion;
	private long hastaPeriodoRemuneracion;
	private long registrosProactivos;
	private VoUser usuario;
	private String estadoProceso;

	public long getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(long idProceso) {
		this.idProceso = idProceso;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public long getDesdePeriodoRemuneracion() {
		return desdePeriodoRemuneracion;
	}

	public void setDesdePeriodoRemuneracion(long desdePeriodoRemuneracion) {
		this.desdePeriodoRemuneracion = desdePeriodoRemuneracion;
	}

	public long getHastaPeriodoRemuneracion() {
		return hastaPeriodoRemuneracion;
	}

	public void setHastaPeriodoRemuneracion(long hastaPeriodoRemuneracion) {
		this.hastaPeriodoRemuneracion = hastaPeriodoRemuneracion;
	}

	public long getRegistrosProactivos() {
		return registrosProactivos;
	}

	public void setRegistrosProactivos(long registrosProactivos) {
		this.registrosProactivos = registrosProactivos;
	}

	public VoUser getUsuario() {
		return usuario;
	}

	public void setUsuario(VoUser usuario) {
		this.usuario = usuario;
	}

	public String getEstadoProceso() {
		return estadoProceso;
	}

	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (int) (desdePeriodoRemuneracion ^ (desdePeriodoRemuneracion >>> 32));
		result = prime * result
				+ ((estadoProceso == null) ? 0 : estadoProceso.hashCode());
		result = prime * result
				+ ((fechaCreacion == null) ? 0 : fechaCreacion.hashCode());
		result = prime
				* result
				+ (int) (hastaPeriodoRemuneracion ^ (hastaPeriodoRemuneracion >>> 32));
		result = prime * result + (int) (idProceso ^ (idProceso >>> 32));
		result = prime * result
				+ (int) (registrosProactivos ^ (registrosProactivos >>> 32));
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		VoDciMasivo other = (VoDciMasivo) obj;
		if (desdePeriodoRemuneracion != other.desdePeriodoRemuneracion)
			return false;
		if (estadoProceso == null) {
			if (other.estadoProceso != null)
				return false;
		} else if (!estadoProceso.equals(other.estadoProceso))
			return false;
		if (fechaCreacion == null) {
			if (other.fechaCreacion != null)
				return false;
		} else if (!fechaCreacion.equals(other.fechaCreacion))
			return false;
		if (hastaPeriodoRemuneracion != other.hastaPeriodoRemuneracion)
			return false;
		if (idProceso != other.idProceso)
			return false;
		if (registrosProactivos != other.registrosProactivos)
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
