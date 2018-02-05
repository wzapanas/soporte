package pe.gob.pj.csjar.tickets.controlador;

public class Solicitante {
	
	private int idSolicitante;
	private String nombres;
	private String apePaterno;
	private String apeMaterno;
	private int idDependencia;
	private String activo;
	
	
	public int getIdSolicitante() {
		return idSolicitante;
	}
	public void setIdSolicitante(int idSolicitante) {
		this.idSolicitante = idSolicitante;
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
	public int getIdDependencia() {
		return idDependencia;
	}
	public void setIdDependencia(int idDependencia) {
		this.idDependencia = idDependencia;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	
	
	
}
