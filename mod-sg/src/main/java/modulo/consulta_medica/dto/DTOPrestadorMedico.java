package modulo.consulta_medica.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import modulo.base.dto.DTOPersona;
import modulo.usuarios.dto.DTOUsuario;
import util.Auditable;

@Entity
@Table(name="cs_tb_prestador_medico")
public class DTOPrestadorMedico implements Serializable, Auditable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="prestador_medico_id")
	private long prestadorMedicoId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reg_fec_insert")
	private Date regFecInsert;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reg_fec_update")
	private Date regFecUpdate;

	@ManyToOne
	@JoinColumn(name="persona_id")
	private DTOPersona dtoPersona;

	@ManyToOne
	@JoinColumn(name="usuario_id")
	private DTOUsuario dtoUsuario;
	
	@Column(name="profesion")
	private String profesion;
	
	@Column(name="especialidad")
	private String especialidad;

	public DTOPrestadorMedico() {
	}

	public long getPrestadorMedicoId() {
		return this.prestadorMedicoId;
	}

	public void setPrestadorMedicoId(long prestadorMedicoId) {
		this.prestadorMedicoId = prestadorMedicoId;
	}

	public Date getRegFecInsert() {
		return this.regFecInsert;
	}

	@Override
	public void setRegFecInsert(Date regFecInsert) {
		this.regFecInsert = regFecInsert;
	}

	public Date getRegFecUpdate() {
		return this.regFecUpdate;
	}
	
	@Override
	public void setRegFecUpdate(Date regFecUpdate) {
		this.regFecUpdate = regFecUpdate;
	}

	public DTOPersona getDtoPersona() {
		return dtoPersona;
	}

	public void setDtoPersona(DTOPersona dtoPersona) {
		this.dtoPersona = dtoPersona;
	}

	public DTOUsuario getDtoUsuario() {
		return dtoUsuario;
	}

	public void setDtoUsuario(DTOUsuario dtoUsuario) {
		this.dtoUsuario = dtoUsuario;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	@Override
	public String toString() {
		return "DTOPrestadorMedico [prestadorMedicoId=" + prestadorMedicoId
				+ ", regFecInsert=" + regFecInsert + ", regFecUpdate="
				+ regFecUpdate + ", dtoPersona=" + dtoPersona + ", dtoUsuario="
				+ dtoUsuario + ", profesion=" + profesion + ", especialidad="
				+ especialidad + "]";
	}



}