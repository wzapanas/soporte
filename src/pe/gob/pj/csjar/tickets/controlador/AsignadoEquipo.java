package pe.gob.pj.csjar.tickets.controlador;

public class AsignadoEquipo {
	   private String idAsignado;
	   private String idEquipo;
	   private int grupo;
	   private String nomEquipo;
	   private String dirIp;
	   private String secuencia;
	   private String activo;
	   
	public String getSecuencia() {
		return secuencia;
	}
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}
	public String getIdAsignado() {
		return idAsignado;
	}
	public void setIdAsignado(String idAsignado) {
		this.idAsignado = idAsignado;
	}
	public String getIdEquipo() {
		return idEquipo;
	}
	public void setIdEquipo(String idEquipo) {
		this.idEquipo = idEquipo;
	}
	public int getGrupo() {
		return grupo;
	}
	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}
	public String getNomEquipo() {
		return nomEquipo;
	}
	public void setNomEquipo(String nomEquipo) {
		this.nomEquipo = nomEquipo;
	}
	public String getDirIp() {
		return dirIp;
	}
	public void setDirIp(String dirIp) {
		this.dirIp = dirIp;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}

	}