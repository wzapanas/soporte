package pe.gob.pj.csjar.tickets.controlador;

import java.sql.Timestamp;

public class Asignado {
	   private String idAsignado;
	   private Timestamp fecAsigna;
	   private int idDependencia;
	   private String idCargo;
	   private int idSolicitante;
	   private String nroOficina;
	   private String ultimo;
	   private String piso;
	   private String observacion;
	   private String usuarioReg;
	   private Timestamp fechaReg;
	   private String usuarioMod;
	   private Timestamp fechaMod;
	   private String motivoMod;
	   private String usuarioAnul;
	   private Timestamp fechaAnul;
	   private String motivoAnul;
	   private String activo;
	   private String auxFecAsigna;
	   private int anioAsignado;
	   
	public int getAnioAsignado() {
		return anioAsignado;
	}
	public void setAnioAsignado(int anioAsignado) {
		this.anioAsignado = anioAsignado;
	}
	public String getAuxFecAsigna() {
		return auxFecAsigna;
	}
	public void setAuxFecAsigna(String auxFecAsigna) {
		this.auxFecAsigna = auxFecAsigna;
	}
	public String getIdAsignado() {
		return idAsignado;
	}
	public void setIdAsignado(String idAsignado) {
		this.idAsignado = idAsignado;
	}
	public Timestamp getFecAsigna() {
		return fecAsigna;
	}
	public void setFecAsigna(Timestamp fecAsigna) {
		this.fecAsigna = fecAsigna;
	}
	public int getIdDependencia() {
		return idDependencia;
	}
	public void setIdDependencia(int idDependencia) {
		this.idDependencia = idDependencia;
	}
	public String getIdCargo() {
		return idCargo;
	}
	public void setIdCargo(String idCargo) {
		this.idCargo = idCargo;
	}
	public int getIdSolicitante() {
		return idSolicitante;
	}
	public void setIdSolicitante(int idSolicitante) {
		this.idSolicitante = idSolicitante;
	}
	public String getNroOficina() {
		return nroOficina;
	}
	public void setNroOficina(String nroOficina) {
		this.nroOficina = nroOficina;
	}
	public String getUltimo() {
		return ultimo;
	}
	public void setUltimo(String ultimo) {
		this.ultimo = ultimo;
	}
	public String getPiso() {
		return piso;
	}
	public void setPiso(String piso) {
		this.piso = piso;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getUsuarioReg() {
		return usuarioReg;
	}
	public void setUsuarioReg(String usuarioReg) {
		this.usuarioReg = usuarioReg;
	}
	public Timestamp getFechaReg() {
		return fechaReg;
	}
	public void setFechaReg(Timestamp fechaReg) {
		this.fechaReg = fechaReg;
	}
	public String getUsuarioMod() {
		return usuarioMod;
	}
	public void setUsuarioMod(String usuarioMod) {
		this.usuarioMod = usuarioMod;
	}
	public Timestamp getFechaMod() {
		return fechaMod;
	}
	public void setFechaMod(Timestamp fechaMod) {
		this.fechaMod = fechaMod;
	}
	public String getMotivoMod() {
		return motivoMod;
	}
	public void setMotivoMod(String motivoMod) {
		this.motivoMod = motivoMod;
	}
	public String getUsuarioAnul() {
		return usuarioAnul;
	}
	public void setUsuarioAnul(String usuarioAnul) {
		this.usuarioAnul = usuarioAnul;
	}
	public Timestamp getFechaAnul() {
		return fechaAnul;
	}
	public void setFechaAnul(Timestamp fechaAnul) {
		this.fechaAnul = fechaAnul;
	}
	public String getMotivoAnul() {
		return motivoAnul;
	}
	public void setMotivoAnul(String motivoAnul) {
		this.motivoAnul = motivoAnul;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}

	}