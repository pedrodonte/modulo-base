package fns;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "rutafil", "dgvafil" })
public class Tipo_carga {

	private String rutafil;
	private String dgvafil;
	
	public Tipo_carga() {
		super();
	}

	public Tipo_carga(String rutafil, String dgvafil) {
		super();
		this.rutafil = rutafil;
		this.dgvafil = dgvafil;
	}
	
	public String getRutafil() {
		return rutafil;
	}
	public void setRutafil(String rutafil) {
		this.rutafil = rutafil;
	}
	public String getDgvafil() {
		return dgvafil;
	}
	public void setDgvafil(String dgvafil) {
		this.dgvafil = dgvafil;
	}
	
	
}
