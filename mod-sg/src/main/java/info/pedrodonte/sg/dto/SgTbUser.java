package info.pedrodonte.sg.dto;

import info.pedrodonte.util.Auditable;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the sg_tb_user database table.
 * 
 */
@Entity
@Table(name="sg_tb_user")
public class SgTbUser implements Serializable, Auditable {
	private static final long serialVersionUID = 3L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="usuario_id")
	private long usuarioId;

	private String clave;
	private String identificador;
	private String email;
	
	@Column(name="flag_cambiar_clave")
	private Integer flagCambiarClave;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reg_fec_insert")
	private Date regFecInsert;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reg_fec_update")
	private Date regFecUpdate;

	public SgTbUser() {
	}

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

	public Integer getFlagCambiarClave() {
		return flagCambiarClave;
	}

	public void setFlagCambiarClave(Integer flagCambiarClave) {
		this.flagCambiarClave = flagCambiarClave;
	}

	@Override
	public String toString() {
		return "SgTbUser [usuarioId=" + usuarioId + ", clave=" + clave
				+ ", identificador=" + identificador + ", email=" + email
				+ ", flagCambiarClave=" + flagCambiarClave + ", regFecInsert="
				+ regFecInsert + ", regFecUpdate=" + regFecUpdate + "]";
	}
	
	

}