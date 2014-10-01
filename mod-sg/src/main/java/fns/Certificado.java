package fns;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "rutbenef", "dgvbenef", "apell1", "apell2", "nombres",
		"sex", "fec_nac", "tramo", "tipo_carga", "fec_bloq", "cod_cybl", "cod_desc", "numero_cargas", "error" })
public class Certificado {

	private String rutbenef;
	private String dgvbenef;
	private String apell1;
	private String apell2;
	private String nombres;
	private String sex;
	private String fec_nac;
	private String tramo;
	private Tipo_carga tipo_carga;
	private String fec_bloq;
	private String cod_cybl;
	private String cod_desc;
	private String numero_cargas;
	private String error;

	public String getRutbenef() {
		return rutbenef;
	}

	public void setRutbenef(String rutbenef) {
		this.rutbenef = rutbenef;
	}

	public String getDgvbenef() {
		return dgvbenef.toUpperCase();
	}

	public void setDgvbenef(String dgvbenef) {
		this.dgvbenef = dgvbenef;
	}

	public String getApell1() {
		return apell1.toUpperCase();
	}

	public void setApell1(String apell1) {
		this.apell1 = apell1;
	}

	public String getApell2() {
		return apell2.toUpperCase();
	}

	public void setApell2(String apell2) {
		this.apell2 = apell2;
	}

	public String getNombres() {
		return nombres.toUpperCase();
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getSex() {
		return sex.toUpperCase();
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getFec_nac() {
		return fec_nac;
	}

	public void setFec_nac(String fec_nac) {
		this.fec_nac = fec_nac;
	}

	public String getTramo() {
		return tramo;
	}

	public void setTramo(String tramo) {
		this.tramo = tramo;
	}

	public Tipo_carga getTipo_carga() {
		return tipo_carga;
	}

	public void setTipo_carga(Tipo_carga tipo_carga) {
		this.tipo_carga = tipo_carga;
	}

	public String getFec_bloq() {
		return fec_bloq;
	}

	public void setFec_bloq(String fec_bloq) {
		this.fec_bloq = fec_bloq;
	}

	public String getCod_cybl() {
		return cod_cybl;
	}

	public void setCod_cybl(String cod_cybl) {
		this.cod_cybl = cod_cybl;
	}

	public String getCod_desc() {
		return cod_desc;
	}

	public void setCod_desc(String cod_desc) {
		this.cod_desc = cod_desc;
	}

	public String getNumero_cargas() {
		return numero_cargas;
	}

	public void setNumero_cargas(String numero_cargas) {
		this.numero_cargas = numero_cargas;
	}

	public Certificado dummyObj() {
		Certificado c = new Certificado();
		c.setRutbenef("16564");
		c.setDgvbenef("K");
		c.setApell1("");
		c.setApell2("");
		c.setNombres("");
		c.setSex("");
		c.setFec_nac("1988-05-31");
		c.setTramo("1988-05-31");
		c.setTipo_carga(new Tipo_carga("", ""));
		c.setFec_bloq("2013-10");
		c.setCod_cybl("0000");
		c.setCod_desc("...");
		c.setNumero_cargas("0");
		return c;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
