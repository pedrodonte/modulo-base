package modulo.reporteria.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the vw_cd_consultas_diarias database table.
 * 
 */
@Entity
@Table(name="vw_cd_consultas_diarias")
@NamedQuery(name="VwCdConsultasDiaria.findAll", query="SELECT v FROM VwCdConsultasDiaria v")
public class VwCdConsultasDiaria implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long cantidad;

	@Id
	@Temporal(TemporalType.DATE)
	private Date dia;

	public VwCdConsultasDiaria() {
	}

	public Long getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public Date getDia() {
		return this.dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

}