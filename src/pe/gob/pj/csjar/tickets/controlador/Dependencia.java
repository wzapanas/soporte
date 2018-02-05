package pe.gob.pj.csjar.tickets.controlador;

public class Dependencia {
	
	private int idDependencia;
	private String nomDep;
	private String desDep;
	private int nivel;
	private int idDepSup;
	private int idLocal;
	private String activo;
	

	public int getIdDependencia() {
		return idDependencia;
	}

	public void setIdDependencia(int idDependencia) {
		this.idDependencia = idDependencia;
	}

	public String getNomDep() {
		return nomDep;
	}

	public void setNomDep(String nomDep) {
		this.nomDep = nomDep;
	}

	public String getDesDep() {
		return desDep;
	}

	public void setDesDep(String desDep) {
		this.desDep = desDep;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getIdDepSup() {
		return idDepSup;
	}

	public void setIdDepSup(int idDepSup) {
		this.idDepSup = idDepSup;
	}

	public int getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(int idLocal) {
		this.idLocal = idLocal;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	
}
