package pe.gob.pj.csjar.tickets.controlador;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;


public class Incidencia {
   private String idIncidencia;
   private int numIncidencia;
   private int anioIncidencia;
   private Timestamp fechaIng;
   private String idTipIng;
   private int idDependencia;
   private int idUbicacion;
   private int idSolicitante;
   private String detIncidencia;
   private int idUsuario;
   private String usuarioReg;
   private Timestamp fechaReg;
   private String usuarioMod;
   private Timestamp fechaMod;
   private String motivoMod;
   private String usuarioAnul;
   private Timestamp fechaAnul;
   private String motivoAnul;
   private String activo;
   private String tipoIngreso;
   private String nomDep;
   private String nombres;
   private String apePaterno;
   private String apeMaterno;
   private String apePaternoU;
   private String apeMaternoU;
   private String nombresU;
   private String auxFecha;
   private String idAtencion;
   private int nroAtenciones;
   private String idSituacionTicket;
   private String situacionTicket;
   private String estado;
   
public String getEstado() {
	return estado;
}
public void setEstado(String estado) {
	this.estado = estado;
}
public String getSituacionTicket() {
	return situacionTicket;
}
public void setSituacionTicket(String situacionTicket) {
	this.situacionTicket = situacionTicket;
}
public String getIdSituacionTicket() {
	return idSituacionTicket;
}
public void setIdSituacionTicket(String idSituacionTicket) {
	this.idSituacionTicket = idSituacionTicket;
}
public String getIdIncidencia() {
	return idIncidencia;
}
public void setIdIncidencia(String idIncidencia) {
	this.idIncidencia = idIncidencia;
}
public int getNumIncidencia() {
	return numIncidencia;
}
public void setNumIncidencia(int numIncidencia) {
	this.numIncidencia = numIncidencia;
}
public int getAnioIncidencia() {
	return anioIncidencia;
}
public void setAnioIncidencia(int anioIncidencia) {
	this.anioIncidencia = anioIncidencia;
}
public Timestamp getFechaIng() {
	return fechaIng;
}
public void setFechaIng(Timestamp fechaIng) {
	this.fechaIng = fechaIng;
}
public String getIdTipIng() {
	return idTipIng;
}
public void setIdTipIng(String idTipIng) {
	this.idTipIng = idTipIng;
}
public int getIdDependencia() {
	return idDependencia;
}
public void setIdDependencia(int idDependencia) {
	this.idDependencia = idDependencia;
}
public int getIdUbicacion() {
	return idUbicacion;
}
public void setIdUbicacion(int idUbicacion) {
	this.idUbicacion = idUbicacion;
}
public int getIdSolicitante() {
	return idSolicitante;
}
public void setIdSolicitante(int idSolicitante) {
	this.idSolicitante = idSolicitante;
}
public String getDetIncidencia() {
	return detIncidencia;
}
public void setDetIncidencia(String detIncidencia) {
	this.detIncidencia = detIncidencia;
}
public int getIdUsuario() {
	return idUsuario;
}
public void setIdUsuario(int idUsuario) {
	this.idUsuario = idUsuario;
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
public String getTipoIngreso() {
	return tipoIngreso;
}
public void setTipoIngreso(String tipoIngreso) {
	this.tipoIngreso = tipoIngreso;
}
public String getNomDep() {
	return nomDep;
}
public void setNomDep(String nomDep) {
	this.nomDep = nomDep;
}
public String getNombres() {
	return nombres;
}
public void setNombres(String nombres) {
	this.nombres = nombres;
}
public String getApePaterno() {
	return apePaterno;
}
public void setApePaterno(String apePaterno) {
	this.apePaterno = apePaterno;
}
public String getApeMaterno() {
	return apeMaterno;
}
public void setApeMaterno(String apeMaterno) {
	this.apeMaterno = apeMaterno;
}
public String getApePaternoU() {
	return apePaternoU;
}
public void setApePaternoU(String apePaternoU) {
	this.apePaternoU = apePaternoU;
}
public String getApeMaternoU() {
	return apeMaternoU;
}
public void setApeMaternoU(String apeMaternoU) {
	this.apeMaternoU = apeMaternoU;
}
public String getNombresU() {
	return nombresU;
}
public void setNombresU(String nombresU) {
	this.nombresU = nombresU;
}
public String getAuxFecha() {
	return auxFecha;
}
public void setAuxFecha(String auxFecha) {
	this.auxFecha = auxFecha;
}
public String getIdAtencion() {
	return idAtencion;
}
public void setIdAtencion(String idAtencion) {
	this.idAtencion = idAtencion;
}
public int getNroAtenciones() {
	return nroAtenciones;
}
public void setNroAtenciones(int nroAtenciones) {
	this.nroAtenciones = nroAtenciones;
}
   
}