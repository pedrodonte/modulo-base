package modulo.usuarios.dto;

import java.io.Serializable;

import javax.persistence.*;

import util.Auditable;

import java.util.Date;


/**
 * The persistent class for the sg_tb_user_rol database table.
 * 
 */
@Entity
@Table(name="sg_tb_user_rol")
public class DTORolDeUsuario implements Serializable, Auditable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="usuario_rol_id")
	private long usuarioRolId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reg_fec_insert")
	private Date regFecInsert;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reg_fec_update")
	private Date regFecUpdate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="valido_desde")
	private Date validoDesde;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="valido_hasta")
	private Date validoHasta;

	//uni-directional many-to-one association to SgTbRol
	@ManyToOne
	@JoinColumn(name="rol_id")
	private DTORol dtoRol;

	//uni-directional many-to-one association to SgTbUser
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private DTOUsuario dtoUsuario;

	public DTORolDeUsuario() {
	}

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



	public DTORol getDtoRol() {
		return dtoRol;
	}

	public void setDtoRol(DTORol dtoRol) {
		this.dtoRol = dtoRol;
	}

	public DTOUsuario getDtoUsuario() {
		return dtoUsuario;
	}

	public void setDtoUsuario(DTOUsuario dtoUsuario) {
		this.dtoUsuario = dtoUsuario;
	}

	@Override
	public String toString() {
		return "SgTbUserRol [usuarioRolId=" + usuarioRolId + ", regFecInsert="
				+ regFecInsert + ", regFecUpdate=" + regFecUpdate
				+ ", validoDesde=" + validoDesde + ", validoHasta="
				+ validoHasta + ", sgTbRol=" + dtoRol + ", sgTbUser="
				+ dtoUsuario + "]";
	}
	
	

}