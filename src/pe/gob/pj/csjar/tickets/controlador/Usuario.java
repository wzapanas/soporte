package pe.gob.pj.csjar.tickets.controlador;

import java.util.Date;

public class Usuario {
	
   private int idUsuario;
   private String usuario;
   private String apePaterno;
   private String apeMaterno;
   private String nombres;
   private String dni;
   private String email;
   private String clave;
   private int idRol;
   private int idDependencia;
   private Date fechaReg;
   private String activo;
   private String nomRol;
   private String nomDep;
  
public int getIdUsuario() {
	return idUsuario;
}
public void setIdUsuario(int idUsuario) {
	this.idUsuario = idUsuario;
}

public String getUsuario() {
	return usuario;
}
public void setUsuario(String usuario) {
	this.usuario = usuario;
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
public String getNombres() {
	return nombres;
}
public void setNombres(String nombres) {
	this.nombres = nombres;
}
public String getDni() {
	return dni;
}
public void setDni(String dni) {
	this.dni = dni;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getClave() {
	return clave;
}
public void setClave(String clave) {
	this.clave = clave;
}
public int getIdRol() {
	return idRol;
}
public void setIdRol(int idRol) {
	this.idRol = idRol;
}
public int getIdDependencia() {
	return idDependencia;
}
public void setIdDependencia(int idDependencia) {
	this.idDependencia = idDependencia;
}
public Date getFechaReg() {
	return fechaReg;
}
public void setFechaReg(Date fechaReg) {
	this.fechaReg = fechaReg;
}
public String getActivo() {
	return activo;
}
public void setActivo(String activo) {
	this.activo = activo;
}
   
public String getNomRol() {
	return nomRol;
}
public void setNomRol(String nomRol) {
	this.nomRol = nomRol;
}

public String getNomDep() {
	return nomDep;
}
public void setNomDep(String nomDep) {
	this.nomDep = nomDep;
}
   
}