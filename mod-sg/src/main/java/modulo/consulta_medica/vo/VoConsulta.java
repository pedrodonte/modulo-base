package modulo.consulta_medica.vo;

import java.util.Date;

import modulo.base.excepciones.ErrorDelSistemaException;
import modulo.base.vo.VoPersona;
import util.HelperFecha;

public class VoConsulta implements Cloneable {

	private long consultaId;
	private String txtDesarrollo;
	
	private Date fechaInicioConsulta;
	private Date fechaFinConsulta;
	
	private Date regFecInsert;
	private Date regFecUpdate;
	private VoPersona voPersona;
	
	private int duracion;
	private String horaInicio;
	private String horaTermino;
	
	public long getConsultaId() {
		return consultaId;
	}
	public void setConsultaId(long consultaId) {
		this.consultaId = consultaId;
	}
	public String getTxtDesarrollo() {
		return txtDesarrollo;
	}
	public void setTxtDesarrollo(String txtDesarrollo) {
		this.txtDesarrollo = txtDesarrollo;
	}
	public Date getRegFecInsert() {
		return regFecInsert;
	}
	public void setRegFecInsert(Date regFecInsert) {
		this.regFecInsert = regFecInsert;
	}
	public Date getRegFecUpdate() {
		return regFecUpdate;
	}
	public void setRegFecUpdate(Date regFecUpdate) {
		this.regFecUpdate = regFecUpdate;
	}
	public VoPersona getVoPersona() {
		return voPersona;
	}
	public void setVoPersona(VoPersona voPersona) {
		this.voPersona = voPersona;
	}
	
	public Date getFechaInicioConsulta() {
		return fechaInicioConsulta;
	}
	public void setFechaInicioConsulta(Date fechaInicioConsulta) {
		horaInicio = HelperFecha.getHoraDeFecha(fechaInicioConsulta);
		this.fechaInicioConsulta = fechaInicioConsulta;
	}
	public Date getFechaFinConsulta() {
		return fechaFinConsulta;
	}
	public void setFechaFinConsulta(Date fechaFinConsulta) {
		horaTermino = HelperFecha.getHoraDeFecha(fechaFinConsulta);
		this.fechaFinConsulta = fechaFinConsulta;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraTermino() {
		return horaTermino;
	}
	public void setHoraTermino(String horaTermino) {
		this.horaTermino = horaTermino;
	}
	public int getDuracion() {
		try {
			this.duracion = HelperFecha.calcularDiferenciaMinutos(this.fechaInicioConsulta, this.fechaFinConsulta);
		} catch (ErrorDelSistemaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	
	

}
