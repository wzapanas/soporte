package pe.gob.pj.csjar.tickets.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import java.io.*;
import java.util.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer.*;
import javax.servlet.ServletResponse;
import java.sql.*;


import pe.gob.pj.csjar.tickets.controlador.Atencion;
import pe.gob.pj.csjar.tickets.controlador.Incidencia;

public class AtencionDao {
	private Connection conexion;
	   public int setConexion(Connection cnn) {
			this.conexion = cnn;
	      return 1;
	   }
	   
	   
	   public int insertar(Atencion ate) {
		   PreparedStatement ps;
		   ResultSet rs;
		   Calendar fecha = Calendar.getInstance();
	       int anio = fecha.get(Calendar.YEAR);
	       System.out.println("Este es el año"+anio);

	       
		   int ok=0;
		   String sql = "INSERT INTO gi_atencion (id_atencion,fecha_termino,id_tip_atencion,id_tip_servicio,id_estado_servicio,detalle_servicio,observaciones,id_incidencia,usuario_reg,activo,anio_atencion,num_atencion)"  
		   			+" VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";	
		   
		   String idAtencion = String.valueOf(generarNuevaIdAtencion());
		   ate.setIdAtencion(idAtencion);
		   try {
			   ps = conexion.prepareStatement(sql);	   
			   ps.setString(1,idAtencion);
			   ps.setTimestamp(2,ate.getFechaTermino());
			   ps.setString(3,ate.getIdTipAtencion());
			   ps.setString(4,ate.getIdTipServicio());
			   ps.setString(5,ate.getIdEstadoServicio());
			   ps.setString(6,ate.getDetalleServicio());
			   ps.setString(7,ate.getObservaciones());
			   ps.setString(8,ate.getIdIncidencia());
			   ps.setString(9,ate.getUsuarioReg());
			   ps.setString(10,ate.getActivo());
			   ps.setInt(11,anio);
			   ps.setInt(12,generarNuevaAtencion());
			   ps.executeUpdate();
			   ok=1;
			   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    
		  //System.out.println(sql);
	      return ok;
	   }   
	   
	   
	   
	   public String generarNuevaIdAtencion() {   
		   String maxID = "";
		   Calendar fecha = Calendar.getInstance();
	       int anio = fecha.get(Calendar.YEAR);
	       System.out.println("Este es el año"+anio);
		   try {
			   PreparedStatement ps;
			   ResultSet rs;
			 
			   String sql = "SELECT ISNULL(MAX(num_atencion),0) as num_ate FROM gi_atencion WHERE (anio_atencion =?)  ";
			   ps = conexion.prepareStatement(sql);//ps.setString(1, u);
			   ps.setInt(1,anio);	
			   rs = ps.executeQuery();
			   while(rs.next()){
				   
				   maxID = String.valueOf((rs.getInt("num_ate")+1));
				   
			   }
			   System.out.println("Este es es año: "+anio+ " Este es id "+maxID);
			   maxID = String.valueOf(anio) + String.format("%05d",Integer.parseInt(maxID));
			   
			   
		} catch (Exception e) {
			// TODO: handle exception

			System.out.println("Error en la consulta");
			e.printStackTrace();
			
			
		}

	      return maxID;
	   }
	   
	   
	   public List<Atencion> recuperarPorIdAtencion(String idIncidencia,String idAtencion) {
		   System.out.println("A RECUPERAR ATENCIONES");
		   Atencion ate=null;
		   List<Atencion> lisAte=new ArrayList<Atencion>();
		   	String sql = "SELECT gi_atencion.*,gi_ate_equipo.id_tip_equipo,gi_ate_equipo.id_marca,gi_ate_equipo.id_modelo,gi_ate_equipo.serie FROM gi_atencion INNER JOIN gi_ate_equipo ON gi_atencion.id_atencion= gi_ate_equipo.id_atencion WHERE gi_atencion.activo='S' AND gi_atencion.id_incidencia=? AND gi_atencion.id_atencion=?";
		    
		   	PreparedStatement ps;
		    ResultSet rs;	
		   // System.out.println(sql);
			try {
				ps = conexion.prepareStatement(sql);
				ps.setString(1, idIncidencia);
				ps.setString(2, idAtencion);
			    rs = ps.executeQuery();
			   // System.out.println(sql);
			    if (rs.next()){			    	
			    	ate=new Atencion();			    	
			    	ate.setFechaTermino(rs.getTimestamp("fecha_termino"));
			    	ate.setIdAtencion(rs.getString("id_atencion"));
			    	ate.setIdTipAtencion(rs.getString("id_tip_atencion"));
			    	ate.setIdTipServicio(rs.getString("id_tip_servicio"));
			    	ate.setIdEstadoServicio(rs.getString("id_estado_servicio"));			    	
			    	ate.setDetalleServicio(rs.getString("detalle_servicio"));
			    	ate.setObservaciones(rs.getString("observaciones"));
			    	ate.setActivo(rs.getString("activo"));
			    	ate.setIdIncidencia(rs.getString("id_incidencia"));
			    	ate.setUsuarioReg(rs.getString("usuario_reg"));
			    	ate.setFechaReg(rs.getTimestamp("fecha_reg"));
			    	ate.setUsuarioMod(rs.getString("usuario_mod"));
			    	ate.setFechaMod(rs.getTimestamp("fecha_mod"));
			    	ate.setMotivoMod(rs.getString("motivo_mod"));
			    	ate.setUsuarioAnul(rs.getString("usuario_anul"));
			    	ate.setFechaAnul(rs.getTimestamp("fecha_anul"));
			    	ate.setMotivoAnul(rs.getString("motivo_anul"));
			    	ate.setAuxFecha(((rs.getString("fecha_termino")).toString()));
			   /////////////// ATENCION EQUIPO /////////////////// 	
			    	ate.setIdTipEquipo(rs.getString("id_tip_equipo"));
			    	ate.setIdMarca(rs.getString("id_marca"));
			    	ate.setIdModelo(rs.getString("id_modelo"));
			    	ate.setSerie(rs.getString("serie"));
			    	//System.out.println(sql);
			    	lisAte.add(ate);
			    	
			    	}
			    rs.close();
			    ps.close();	
			    System.out.println("Este es la lista "+lisAte);
			    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
	      return lisAte;
		   }

	   
	   public int actualizar(Atencion ate) {
		   	   PreparedStatement ps;
		   	   ResultSet rs;
			   int ok=0;
			   java.util.Date d1 = new java.util.Date();
		       java.sql.Timestamp fechaMod = new java.sql.Timestamp(d1.getTime());
			   String sql = "UPDATE gi_atencion SET fecha_termino=?,id_tip_atencion=?,id_tip_servicio=?,id_estado_servicio=?,detalle_servicio=?,observaciones=?,id_incidencia=?,usuario_reg=?,usuario_mod=?,fecha_mod=?,activo=?"  
			   			+" WHERE (id_atencion=?)";
			   
			   try {
				   ps = conexion.prepareStatement(sql);	   
				   ps.setTimestamp(1,ate.getFechaTermino());
				   ps.setString(2,ate.getIdTipAtencion());
				   ps.setString(3,ate.getIdTipServicio());
				   ps.setString(4,ate.getIdEstadoServicio());
				   ps.setString(5,ate.getDetalleServicio());
				   ps.setString(6,ate.getObservaciones());
				   ps.setString(7,ate.getIdIncidencia());
				   ps.setString(8,ate.getUsuarioReg());
				   ps.setString(9,ate.getUsuarioMod());
				   ps.setTimestamp(10,fechaMod);
				   ps.setString(11,ate.getActivo());
				   ps.setString(12,ate.getIdAtencion());
				   ps.executeUpdate();
				   ok=1;
			   
			   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    
		  //System.out.println(sql);
	      return ok;
	   }
	   
	   
	   
	   public List<Atencion> recuperarAtencion(String idIncidencia) {
		   Atencion ate=null;
		   List<Atencion> lisAte=new ArrayList<Atencion>();
		   	String sql = "SELECT gi_atencion.* FROM gi_atencion WHERE gi_atencion.id_incidencia =? AND (activo='S');";
		    PreparedStatement ps;
		    
		    ResultSet rs;	
		   // System.out.println(sql);
			try {
				ps = conexion.prepareStatement(sql);//ps.setString(1, u);
				ps.setString(1,idIncidencia);
			    rs = ps.executeQuery();
			   // System.out.println(sql);
			    while(rs.next()) {// if (rs.next()){				   		    	
			    	ate=new Atencion();			    	
			    	ate.setFechaTermino(rs.getTimestamp("fecha_termino"));
			    	ate.setIdAtencion(rs.getString("id_atencion"));
			    	ate.setIdTipAtencion(rs.getString("id_tip_atencion"));
			    	ate.setIdTipServicio(rs.getString("id_tip_servicio"));
			    	ate.setIdEstadoServicio(rs.getString("id_estado_servicio"));			    	
			    	ate.setDetalleServicio(rs.getString("detalle_servicio"));
			    	ate.setObservaciones(rs.getString("observaciones"));
			    	ate.setActivo(rs.getString("activo"));
			    	ate.setIdIncidencia(rs.getString("id_incidencia"));
			    	ate.setUsuarioReg(rs.getString("usuario_reg"));
			    	ate.setFechaReg(rs.getTimestamp("fecha_reg"));
			    	ate.setUsuarioMod(rs.getString("usuario_mod"));
			    	ate.setFechaMod(rs.getTimestamp("fecha_mod"));
			    	ate.setMotivoMod(rs.getString("motivo_mod"));
			    	ate.setUsuarioAnul(rs.getString("usuario_anul"));
			    	ate.setFechaAnul(rs.getTimestamp("fecha_anul"));
			    	ate.setMotivoAnul(rs.getString("motivo_anul"));
			    	//System.out.println(sql);
			    	lisAte.add(ate);			    	
			    }
			    rs.close();
			    ps.close();		    		    
			    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
			
			//System.out.println("Este es el max " +generarNuevaIncidencia());
	      return lisAte;
	   }
	   
	   
	   
	   public int anular(Atencion ate) {
		   System.out.println("Ingresa para anular");
		   PreparedStatement ps;
		   ResultSet rs;
		   int ok=0;
		   java.util.Date d1 = new java.util.Date();
	       java.sql.Timestamp fechaAnul = new java.sql.Timestamp(d1.getTime());
	       
		   String sql = "UPDATE gi_atencion SET usuario_anul=?,fecha_anul=?,motivo_anul=?,activo=?"  
		   			+" WHERE (id_atencion=?) AND (id_incidencia=?)";
		   
		   try {
			   ps = conexion.prepareStatement(sql);	   
			   ps.setString(1,ate.getUsuarioAnul());
			   ps.setTimestamp(2,fechaAnul);
			   ps.setString(3,ate.getMotivoAnul());
 			   ps.setString(4,ate.getActivo());
 			   ps.setString(5,ate.getIdAtencion());
			   ps.setString(6,ate.getIdIncidencia());
			   ps.executeUpdate();		
			   System.out.println(sql);
			   ok=1;
			   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    
		  //System.out.println(sql);
	      return ok;
	   }
	   

	   
	   public int generarNuevaAtencion() {   
		   int maxID = 0;
		   Calendar fecha = Calendar.getInstance();
	       int anio = fecha.get(Calendar.YEAR);
	       System.out.println("Este es el año"+anio);
		   try {
			   
			   PreparedStatement ps;
			   ResultSet rs;
			 
			   String sql = "SELECT MAX(num_atencion) as num_ate FROM gi_atencion WHERE (anio_atencion =?) ";
			   ps = conexion.prepareStatement(sql);//ps.setString(1, u);
			   ps.setInt(1,anio);	
			   rs = ps.executeQuery();
			   while(rs.next()){
				   maxID = (rs.getInt("num_ate")+1);
			   }
			   System.out.println("");
			   
		} catch (Exception e) {
			// TODO: handle exception

			System.out.println("Error en la consulta");
			e.printStackTrace();
		}
	      return maxID;
	   }
	   
	   
	   public String obtenerAtencion(String idIncidencia) {
		   String idAtencion = null;
			try {
				
			    PreparedStatement ps;
			    ResultSet rs;
			   	String sql = "SELECT id_atencion FROM gi_atencion WHERE id_incidencia = ? AND (activo='S');";
				ps = conexion.prepareStatement(sql);
				ps.setString(1,idIncidencia);	
				rs = ps.executeQuery();
				while(rs.next()){
					idAtencion = rs.getString("id_atencion");
				   }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	      return idAtencion;
	   }   
	   
	   public List<Atencion> ImprimirAtencion(String idIncidencia,String idAtencion) {
		   System.out.println("A RECUPERAR ATENCIONES");
		   Atencion ate=null;
		   List<Atencion> lisAte=new ArrayList<Atencion>();
		   	String sql = "SELECT gi_atencion.fecha_termino,gi_atencion.detalle_servicio,gi_atencion.observaciones,gi_incidencia.fecha_ing,gi_incidencia.det_incidencia,gu_usuario.nombres,gu_usuario.ape_paterno,gu_usuario.ape_materno,(SELECT LIST(gs_tab_items.nom_corto) FROM gs_tab_items,gi_atencion WHERE gi_atencion.id_incidencia=gi_incidencia.id_incidencia AND gi_atencion.id_tip_atencion=gs_tab_items.id_item AND gs_tab_items.id_tabla='TIPO_ATENCION')AS tipo_atencion,(SELECT LIST(gs_tab_items.nom_corto) FROM gs_tab_items,gi_atencion WHERE gi_atencion.id_incidencia=gi_incidencia.id_incidencia AND gi_atencion.id_estado_servicio=gs_tab_items.id_item AND gs_tab_items.id_tabla='TIPO_ESTADO')AS estado,(SELECT LIST(gs_tab_items.nom_corto) FROM gs_tab_items,gi_atencion WHERE gi_atencion.id_incidencia=gi_incidencia.id_incidencia AND gi_atencion.id_tip_servicio=gs_tab_items.id_item AND gs_tab_items.id_tabla='TIPO_SERVICIO')AS servicio,(SELECT LIST(gs_tab_items.nom_corto) FROM gs_tab_items,gi_atencion WHERE gi_atencion.id_incidencia=gi_incidencia.id_incidencia AND gi_incidencia.id_tip_ing=gs_tab_items.id_item AND gs_tab_items.id_tabla='TIPO_INGRESO')AS ingreso,(SELECT LIST(gs_tab_items.nom_corto) FROM gs_tab_items,gi_atencion,gi_ate_equipo WHERE gi_atencion.id_incidencia=gi_incidencia.id_incidencia AND gi_atencion.id_atencion=gi_ate_equipo.id_atencion AND gi_ate_equipo.id_tip_equipo=gs_tab_items.id_item AND gs_tab_items.id_tabla='TIPO_EQUIPO')AS equipo,(SELECT LIST(gs_tab_items.nom_corto) FROM gs_tab_items,gi_atencion,gi_ate_equipo WHERE gi_atencion.id_incidencia=gi_incidencia.id_incidencia AND gi_atencion.id_atencion=gi_ate_equipo.id_atencion AND gi_ate_equipo.id_marca=gs_tab_items.id_item AND gs_tab_items.id_tabla='MARCA')AS marca,(SELECT LIST(gs_tab_items.nom_corto) FROM gs_tab_items,gi_atencion,gi_ate_equipo WHERE gi_atencion.id_incidencia=gi_incidencia.id_incidencia AND gi_atencion.id_atencion=gi_ate_equipo.id_atencion AND gi_ate_equipo.id_modelo=gs_tab_items.id_item AND gs_tab_items.id_tabla='MODELO')AS modelo,gi_ate_equipo.serie FROM gi_ate_equipo INNER JOIN gi_atencion ON gi_ate_equipo.id_atencion = gi_atencion.id_atencion INNER JOIN gi_incidencia ON gi_atencion.id_incidencia = gi_incidencia.id_incidencia INNER JOIN gu_usuario ON gu_usuario.id_usuario = gi_incidencia.id_usuario WHERE gi_atencion.activo='S' AND gi_atencion.id_atencion=?";
		   	PreparedStatement ps;
		    ResultSet rs;
		    String usuario = ""; 
		   // System.out.println(sql);
			try {
				ps = conexion.prepareStatement(sql);
				ps.setString(1, idAtencion);
			    rs = ps.executeQuery();
			   // System.out.println(sql);
			    if (rs.next()){			    	
			    	ate=new Atencion();			    	
			    	ate.setFechaTermino(rs.getTimestamp("fecha_termino"));
			    	ate.setFechaInicio(rs.getTimestamp("fecha_ing"));
			    	ate.setTipo_ingreso(rs.getString("ingreso"));
			    	usuario = rs.getString("nombres")+" "+ rs.getString("ape_paterno") +" "+ rs.getString("ape_materno");
			    	ate.setUsuario(usuario);
			    	ate.setDet_incidencia(rs.getString("det_incidencia"));
			    	ate.setTipo_atencion(rs.getString("tipo_atencion"));
			    	ate.setTipo_servicio(rs.getString("servicio"));
			    	ate.setEquipo(rs.getString("equipo"));
			    	ate.setMarca(rs.getString("marca"));
			    	ate.setModelo(rs.getString("modelo"));
			    	ate.setSerie(rs.getString("serie"));
			    	ate.setEstado_equipo(rs.getString("estado"));
			    	ate.setDetalleServicio(rs.getString("detalle_servicio"));
			    	ate.setObservaciones(rs.getString("observaciones"));
			    	lisAte.add(ate);
			    	}
			    rs.close();
			    ps.close();	
			    System.out.println("Este es la lista "+lisAte);
			    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
	      return lisAte;
	      
		   }
	   
	   
	   
}

