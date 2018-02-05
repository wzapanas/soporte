package pe.gob.pj.csjar.tickets.controlador;

import java.sql.Timestamp;

public class Atencion {
	
	    private Timestamp fechaInicio;
	    private Timestamp fechaTermino;
	    private String tipo_ingreso;
	    private String usuario;
	    private String det_incidencia;
	    private String tipo_atencion;
	    private String tipo_servicio;
	    private String estado_equipo;
	    private String equipo;
	    private String marca;
	    private String modelo;
		private String idAtencion;
		private String idTipAtencion;
		private String idTipServicio;
		private String idEstadoServicio;
		private String detalleServicio;
		private String observaciones;
		private String activo;
		private String idIncidencia;
		private String usuarioReg;
		private Timestamp fechaReg;
		private String usuarioMod;
		private Timestamp fechaMod;
		private String motivoMod;
		private String usuarioAnul;
		private Timestamp fechaAnul;
		private String motivoAnul;
		private String idTipEquipo;
		private String idMarca;
		private String idModelo;
		private String serie;
		private String auxFecha;
		private int numAtencion;
		private int anioAtencion;
		
		
		public Timestamp getFechaInicio() {
			return fechaInicio;
		}
		public void setFechaInicio(Timestamp fechaInicio) {
			this.fechaInicio = fechaInicio;
		}
		public String getTipo_ingreso() {
			return tipo_ingreso;
		}
		public void setTipo_ingreso(String tipo_ingreso) {
			this.tipo_ingreso = tipo_ingreso;
		}
		public String getUsuario() {
			return usuario;
		}
		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}
		public String getDet_incidencia() {
			return det_incidencia;
		}
		public void setDet_incidencia(String det_incidencia) {
			this.det_incidencia = det_incidencia;
		}
		public String getTipo_atencion() {
			return tipo_atencion;
		}
		public void setTipo_atencion(String tipo_atencion) {
			this.tipo_atencion = tipo_atencion;
		}
		public String getTipo_servicio() {
			return tipo_servicio;
		}
		public void setTipo_servicio(String tipo_servicio) {
			this.tipo_servicio = tipo_servicio;
		}
		public String getEstado_equipo() {
			return estado_equipo;
		}
		public void setEstado_equipo(String estado_equipo) {
			this.estado_equipo = estado_equipo;
		}
		public String getEquipo() {
			return equipo;
		}
		public void setEquipo(String equipo) {
			this.equipo = equipo;
		}
		public String getMarca() {
			return marca;
		}
		public void setMarca(String marca) {
			this.marca = marca;
		}
		public String getModelo() {
			return modelo;
		}
		public void setModelo(String modelo) {
			this.modelo = modelo;
		}
		public int getNumAtencion() {
			return numAtencion;
		}
		public void setNumAtencion(int numAtencion) {
			this.numAtencion = numAtencion;
		}
		public int getAnioAtencion() {
			return anioAtencion;
		}
		public void setAnioAtencion(int anioAtencion) {
			this.anioAtencion = anioAtencion;
		}
		public String getAuxFecha() {
			return auxFecha;
		}
		public void setAuxFecha(String auxFecha) {
			this.auxFecha = auxFecha;
		}
		public String getIdTipEquipo() {
			return idTipEquipo;
		}
		public void setIdTipEquipo(String idTipEquipo) {
			this.idTipEquipo = idTipEquipo;
		}
		public String getIdMarca() {
			return idMarca;
		}
		public void setIdMarca(String idMarca) {
			this.idMarca = idMarca;
		}
		public String getIdModelo() {
			return idModelo;
		}
		public void setIdModelo(String idModelo) {
			this.idModelo = idModelo;
		}
		public String getSerie() {
			return serie;
		}
		public void setSerie(String serie) {
			this.serie = serie;
		}
		public Timestamp getFechaTermino() {
			return fechaTermino;
		}
		public void setFechaTermino(Timestamp fechaTermino) {
			this.fechaTermino = fechaTermino;
		}
		public String getIdAtencion() {
			return idAtencion;
		}
		public void setIdAtencion(String idAtencion) {
			this.idAtencion = idAtencion;
		}
		public String getIdTipAtencion() {
			return idTipAtencion;
		}
		public void setIdTipAtencion(String idTipAtencion) {
			this.idTipAtencion = idTipAtencion;
		}
		public String getIdTipServicio() {
			return idTipServicio;
		}
		public void setIdTipServicio(String idTipServicio) {
			this.idTipServicio = idTipServicio;
		}
		public String getIdEstadoServicio() {
			return idEstadoServicio;
		}
		public void setIdEstadoServicio(String idEstadoServicio) {
			this.idEstadoServicio = idEstadoServicio;
		}
		public String getDetalleServicio() {
			return detalleServicio;
		}
		public void setDetalleServicio(String detalleServicio) {
			this.detalleServicio = detalleServicio;
		}
		public String getObservaciones() {
			return observaciones;
		}
		public void setObservaciones(String observaciones) {
			this.observaciones = observaciones;
		}
		public String getActivo() {
			return activo;
		}
		public void setActivo(String activo) {
			this.activo = activo;
		}
		public String getIdIncidencia() {
			return idIncidencia;
		}
		public void setIdIncidencia(String idIncidencia) {
			this.idIncidencia = idIncidencia;
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
	   	
	
	
}
	   