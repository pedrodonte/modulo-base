package info.pedrodonte.base.dto;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_ps_parametros_sistema")
public class TbParametro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ps_id")
	private long parametroID;
	private String nombre;
	private String valor;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Param[" + nombre + "=>" + valor + "]";
	}

	public long getParametroID() {
		return parametroID;
	}

	public void setParametroID(long parametroID) {
		this.parametroID = parametroID;
	}
	

}
