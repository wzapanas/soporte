package pe.gob.pj.csjar.tickets.controlador;

import java.sql.Timestamp;

public class Local {
	   private int idLocal;
	   private String nomLocal;
	   private String desLocal;
	   private String direccion;
	   private int idDistInstit;
	   private String activo;
	   
	public int getIdDistInstit() {
		return idDistInstit;
	}
	public void setIdDistInstit(int idDistInstit) {
		this.idDistInstit = idDistInstit;
	}
	public int getIdLocal() {
		return idLocal;
	}
	public void setIdLocal(int idLocal) {
		this.idLocal = idLocal;
	}
	public String getNomLocal() {
		return nomLocal;
	}
	public void setNomLocal(String nomLocal) {
		this.nomLocal = nomLocal;
	}
	public String getDesLocal() {
		return desLocal;
	}
	public void setDesLocal(String desLocal) {
		this.desLocal = desLocal;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	   
}
