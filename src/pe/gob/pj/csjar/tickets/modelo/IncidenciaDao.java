package pe.gob.pj.csjar.tickets.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.sun.org.apache.xml.internal.security.utils.SignerOutputStream;

import pe.gob.pj.csjar.tickets.controlador.Incidencia;

public class IncidenciaDao {
	private Connection conexion;
	   
	   public int setConexion(Connection cnn) {
			this.conexion = cnn;
	      return 1;
	   }
	   public List<Incidencia> recuperarPorIdIncidencia(String idIncidencia) {
		   System.out.println("A recuperar la lista por ID");
		   Incidencia inci=null;
		   List<Incidencia> lisInc=new ArrayList<Incidencia>();
		   	String sql = "SELECT * FROM gi_incidencia WHERE activo='S' AND id_incidencia=?";
		    PreparedStatement ps;
		    ResultSet rs;	
			try {
				ps = conexion.prepareStatement(sql);
				ps.setString(1, idIncidencia);				
			    rs = ps.executeQuery();
			   // System.out.println(sql);
			    if (rs.next()){			    	
			    	inci=new Incidencia();			    	
			    	inci.setIdIncidencia(rs.getString("id_incidencia"));
			    	inci.setNumIncidencia(rs.getInt("num_incidencia"));
			    	inci.setAnioIncidencia(rs.getInt("anio_incidencia"));
			    	inci.setFechaIng(rs.getTimestamp("fecha_ing"));
			    	inci.setAuxFecha(rs.getString("fecha_ing"));
			    	inci.setIdTipIng(rs.getString("id_tip_ing"));
			    	inci.setIdDependencia(rs.getInt("id_dependencia"));			    	
			    	inci.setIdSolicitante(rs.getInt("id_solicitante"));
			    	inci.setDetIncidencia(rs.getString("det_incidencia"));
			    	inci.setIdUsuario(rs.getInt("id_usuario"));
			    	inci.setIdUbicacion(rs.getInt("id_ubicacion"));
			    	inci.setIdSituacionTicket(rs.getString("id_situacion_ticket"));
			    	inci.setActivo(rs.getString("activo"));
			    	lisInc.add(inci);			    	
			    	}	
			    rs.close();
			    ps.close();		    		    
			    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
	      return lisInc;
	      
		   }

	   public List<Incidencia> recuperarPorIncidencia(String u) {
		   Incidencia inci=null;
		   List<Incidencia> lisInc=new ArrayList<Incidencia>();
		   String sql = "SELECT * FROM gi_incidencia WHERE activo='S' AND id_incidencia=?";
		    PreparedStatement ps;
		    ResultSet rs;	
		   // System.out.println(sql);
			try {
				ps = conexion.prepareStatement(sql);
				ps.setString(1, u);				
			    rs = ps.executeQuery();
			   // System.out.println(sql);
			    if (rs.next()){			    	
			    	inci=new Incidencia();			    	
			    	inci.setIdIncidencia(rs.getString("id_incidencia"));
			    	inci.setNumIncidencia(rs.getInt("num_incidencia"));
			    	inci.setAnioIncidencia(rs.getInt("anio_incidencia"));
			    	inci.setFechaIng(rs.getTimestamp("fecha_ing"));
			    	inci.setIdTipIng(rs.getString("id_tip_ing"));
			    	inci.setIdDependencia(rs.getInt("id_dependencia"));			    	
			    	inci.setIdSolicitante(rs.getInt("id_solicitante"));
			    	inci.setDetIncidencia(rs.getString("det_incidencia"));
			    	inci.setIdUsuario(rs.getInt("id_usuario"));
			    	inci.setUsuarioReg(rs.getString("usuario_reg"));
			    	inci.setFechaReg(rs.getTimestamp("fecha_reg"));
			    	inci.setUsuarioMod(rs.getString("usuario_mod"));
			    	inci.setFechaMod(rs.getTimestamp("fecha_mod"));
			    	inci.setMotivoMod(rs.getString("motivo_mod"));
			    	inci.setUsuarioAnul(rs.getString("usuario_anul"));
			    	inci.setFechaAnul(rs.getTimestamp("fecha_anul"));
			    	inci.setMotivoAnul(rs.getString("motivo_anul"));
			    	inci.setActivo(rs.getString("activo"));
			    	//System.out.println(sql);
			    	lisInc.add(inci);			    	
			    	}
			    rs.close();
			    ps.close();		    		    
			    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
	      return lisInc;
	   }
	   
	   
	   public List<Incidencia> recuperarTodo() {
		   Incidencia inci=null;
		   List<Incidencia> lisInc=new ArrayList<Incidencia>();;
		   	String sql = "SELECT * FROM gi_incidencia WHERE (activo='S' or isnull(activo,'N')='N')";
		    PreparedStatement ps;
		    ResultSet rs;	
		   // System.out.println(sql);
			try {
				ps = conexion.prepareStatement(sql);//ps.setString(1, u);				
			    rs = ps.executeQuery();
			   // System.out.println(sql);
			    while(rs.next()) {// if (rs.next()){				   		    	
			    	inci=new Incidencia();			    	
			    	inci.setIdIncidencia(rs.getString("id_incidencia"));
			    	inci.setNumIncidencia(rs.getInt("num_incidencia"));
			    	inci.setAnioIncidencia(rs.getInt("anio_incidencia"));
			    	inci.setFechaIng(rs.getTimestamp("fecha_ing"));
			    	inci.setIdTipIng(rs.getString("id_tip_ing"));
			    	inci.setIdDependencia(rs.getInt("id_dependencia"));	
			    	inci.setIdSolicitante(rs.getInt("id_solicitante"));
			    	inci.setDetIncidencia(rs.getString("det_incidencia"));
			    	inci.setIdUsuario(rs.getInt("id_usuario"));
			    	inci.setUsuarioReg(rs.getString("usuario_reg"));
			    	inci.setFechaReg(rs.getTimestamp("fecha_reg"));
			    	inci.setUsuarioMod(rs.getString("usuario_mod"));
			    	inci.setFechaMod(rs.getTimestamp("fecha_mod"));
			    	inci.setMotivoMod(rs.getString("motivo_mod"));
			    	inci.setUsuarioAnul(rs.getString("usuario_anul"));
			    	inci.setFechaAnul(rs.getTimestamp("fecha_anul"));
			    	inci.setMotivoAnul(rs.getString("motivo_anul"));
			    	inci.setActivo(rs.getString("activo"));
			    	//System.out.println(sql);
			    	lisInc.add(inci);			    	
			    }
			    rs.close();
			    ps.close();		    		    
			    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
			System.out.println("Este es el max " +generarNuevaIncidencia());
	      return lisInc;
	   }
	   
public List<Incidencia> recuperarPorFechas(Timestamp fechaInicio, Timestamp fechaFin) {
		   
		   Incidencia inci=null;
		   List<Incidencia> lisInc=new ArrayList<Incidencia>();
		   	String sql = "SELECT gi_incidencia.id_incidencia, gi_incidencia.num_incidencia, gi_incidencia.anio_incidencia, gi_incidencia.fecha_ing, gi_incidencia.det_incidencia, gi_incidencia.id_situacion_ticket,(SELECT gs_tab_items.nom_corto FROM gs_tab_items WHERE gs_tab_items.id_tabla='SITUACION_TICKET' AND gs_tab_items.activo='S' AND gs_tab_items.id_item=gi_incidencia.id_situacion_ticket) AS situacion_ticket, gu_dependencia.nom_dep, gi_solicitante.nombres,gi_solicitante.ape_paterno,gi_solicitante.ape_materno,gu_usuario.nombres AS gu_usuario_nombres,gu_usuario.ape_paterno AS gu_usuario_ape_paterno,gu_usuario.ape_materno AS gu_usuario_ape_materno,gi_incidencia.activo,gs_tab_items.nom_corto AS tip_ingreso FROM gi_incidencia INNER JOIN gu_dependencia ON gi_incidencia.id_dependencia = gu_dependencia.id_dependencia INNER JOIN gi_solicitante ON gi_incidencia.id_solicitante = gi_solicitante.id_solicitante INNER JOIN gu_usuario ON gi_incidencia.id_usuario = gu_usuario.id_usuario INNER JOIN gs_tab_items ON gs_tab_items.id_item=gi_incidencia.id_tip_ing AND gs_tab_items.id_tabla='TIPO_INGRESO' WHERE (gi_incidencia.activo='S')";
		    PreparedStatement ps;
		    ResultSet rs;	
		   // System.out.println(sql);

		    //java.sql.Date sqlInicio = new java.sql.Date(fechaInicio.getTime());
		    //java.sql.Date sqlFin = new java.sql.Date(fechaFin.getTime());
			try {
				ps = conexion.prepareStatement(sql);//ps.setString(1, u);	
				//ps.setTimestamp(1,fechaInicio);	
				//ps.setTimestamp(2,fechaFin);	
			    rs = ps.executeQuery();
			   // System.out.println(sqlInicio);
			   // System.out.println(sqlFin);
			    
			    while(rs.next()) {// if (rs.next()){				   		    	
			    	inci=new Incidencia();			    	
			    	inci.setIdIncidencia(rs.getString("id_incidencia"));
			    	inci.setNumIncidencia(rs.getInt("num_incidencia"));
			    	inci.setAnioIncidencia(rs.getInt("anio_incidencia"));
			    	inci.setFechaIng(rs.getTimestamp("fecha_ing"));
			    	inci.setDetIncidencia(rs.getString("det_incidencia"));
			    	inci.setActivo(rs.getString("activo"));
			    	inci.setTipoIngreso(rs.getString("tip_ingreso"));
			    	inci.setNomDep(rs.getString("nom_dep"));
			    	inci.setNombres(rs.getString("nombres"));
			    	inci.setApePaterno(rs.getString("ape_paterno"));
			    	inci.setApeMaterno(rs.getString("ape_materno"));
			    	inci.setNombresU(rs.getString("gu_usuario_nombres"));
			    	inci.setApePaternoU(rs.getString("gu_usuario_ape_paterno"));
			    	inci.setApeMaternoU(rs.getString("gu_usuario_ape_materno"));
			    	inci.setAuxFecha(rs.getString("fecha_ing"));
			    	inci.setIdSituacionTicket(rs.getString("id_situacion_ticket"));
			    	inci.setSituacionTicket(rs.getString("situacion_ticket"));
			    	//inci.setIdAtencion(rs.getString("atencion"));
			    	//System.out.println(sql);
			    	lisInc.add(inci);			    	
			    }
			    rs.close();
			    ps.close();		
			    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }

	      return lisInc;
		   }
	   

public List<Incidencia> recuperarPorFechasInciAte (Timestamp fechaInicio, Timestamp fechaFin) {
	   
	Incidencia inci=null;
	   List<Incidencia> lisInc=new ArrayList<Incidencia>();;
	   	String sql = "SELECT gi_incidencia.id_incidencia,gi_incidencia.num_incidencia,gi_incidencia.anio_incidencia,gi_incidencia.fecha_ing,gi_incidencia.det_incidencia,gu_dependencia.nom_dep,gi_solicitante.nombres,gi_solicitante.ape_paterno,gi_solicitante.ape_materno,gu_usuario.nombres AS gu_usuario_nombres,gu_usuario.ape_paterno AS gu_usuario_ape_paterno,gu_usuario.ape_materno AS gu_usuario_ape_materno,gi_incidencia.activo,(SELECT LIST(gs_tab_items.nom_corto) FROM gs_tab_items,gi_atencion WHERE gi_atencion.id_incidencia=gi_incidencia.id_incidencia AND gi_atencion.id_estado_servicio=gs_tab_items.id_item AND gs_tab_items.id_tabla='TIPO_ESTADO')AS estado FROM gi_incidencia INNER JOIN gu_dependencia ON gi_incidencia.id_dependencia = gu_dependencia.id_dependencia INNER JOIN gi_solicitante ON gi_incidencia.id_solicitante = gi_solicitante.id_solicitante INNER JOIN gu_usuario ON gi_incidencia.id_usuario = gu_usuario.id_usuario WHERE (gi_incidencia.activo='S') ORDER BY estado ASC;";
	    PreparedStatement ps;
	    ResultSet rs;
	    String estado,boton;
		try {
			ps = conexion.prepareStatement(sql);//ps.setString(1, u);	
		    rs = ps.executeQuery();
		    while(rs.next()) {// if (rs.next()){				   		    	
		    	inci=new Incidencia();			    	
		    	inci.setIdIncidencia(rs.getString("id_incidencia"));
		    	inci.setNumIncidencia(rs.getInt("num_incidencia"));
		    	inci.setAnioIncidencia(rs.getInt("anio_incidencia"));
		    	inci.setFechaIng(rs.getTimestamp("fecha_ing"));
		    	inci.setDetIncidencia(rs.getString("det_incidencia"));
		    	inci.setTipoIngreso("telefono");
		    	inci.setNomDep(rs.getString("nom_dep"));
		    	inci.setNombres(rs.getString("nombres"));
		    	inci.setApePaterno(rs.getString("ape_paterno"));
		    	inci.setApeMaterno(rs.getString("ape_materno"));
		    	inci.setNombresU(rs.getString("gu_usuario_nombres"));
		    	inci.setApePaternoU(rs.getString("gu_usuario_ape_paterno"));
		    	inci.setApeMaternoU(rs.getString("gu_usuario_ape_materno"));
		    	inci.setAuxFecha(((rs.getString("fecha_ing")).toString()));
		    	inci.setEstado(rs.getString("estado"));
		    	lisInc.add(inci);			    	
		    }
		    rs.close();
		    ps.close();		
		    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }

   return lisInc;
	   }


	   
	   public int actualizar(Incidencia inc) {
		   System.out.println("Ingresa para actualizar");
		   PreparedStatement ps;
		   ResultSet rs;
		   int ok=0;
		   java.util.Date d1 = new java.util.Date();
	       java.sql.Timestamp fechaMod = new java.sql.Timestamp(d1.getTime());
		   String sql = "UPDATE gi_incidencia SET id_incidencia=?,num_incidencia=?,id_tip_ing=?,id_dependencia=?,id_ubicacion=?,id_solicitante=?,det_incidencia=?,id_usuario=?,usuario_mod=?,fecha_mod=?,activo=?,id_situacion_ticket=?"  
		   			+" WHERE (id_incidencia=?)";
		   
		   try {
			   ps = conexion.prepareStatement(sql);	   
			   ps.setString(1,inc.getIdIncidencia());
			   ps.setInt(2,inc.getNumIncidencia());
			   ps.setString(3,inc.getIdTipIng());
			   ps.setInt(4,inc.getIdDependencia());
			   ps.setInt(5,inc.getIdUbicacion());
			   ps.setInt(6,inc.getIdSolicitante());
			   ps.setString(7,inc.getDetIncidencia());
			   ps.setInt(8,inc.getIdUsuario());
			   ps.setString(9,inc.getUsuarioMod());
			   ps.setTimestamp(10,fechaMod);
 			   ps.setString(11,inc.getActivo());
 			   ps.setString(12,inc.getIdSituacionTicket());
			   ps.setString(13,inc.getIdIncidencia());

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
	   
	   public int insertar(Incidencia inc) {
		   PreparedStatement ps;
		   ResultSet rs;
		   Calendar fecha = Calendar.getInstance();
	       int anio = fecha.get(Calendar.YEAR);
	       System.out.println("Este es el año"+anio);
		   int ok=0;
		   String sql = "INSERT INTO gi_incidencia (id_incidencia,num_incidencia,anio_incindencia,fecha_ing,id_tip_ing,id_dependencia,id_ubicacion,id_solicitante,det_incidencia,id_usuario,usuario_reg ,activo,id_situacion_ticket)"  
		   			+" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";		  
		   try {
			   ps = conexion.prepareStatement(sql);	   
			   ps.setString(1,generarNuevaIDIncidencia());
			   ps.setInt(2,generarNuevaIncidencia());
			   ps.setInt(3,anio);
			   ps.setTimestamp(4,inc.getFechaIng());
			   ps.setString(5,inc.getIdTipIng());
			   ps.setInt(6,inc.getIdDependencia());
			   ps.setInt(7,inc.getIdUbicacion());
			   ps.setInt(8,inc.getIdSolicitante());
			   ps.setString(9,inc.getDetIncidencia());
			   ps.setInt(10,inc.getIdUsuario());
			   ps.setString(11,inc.getUsuarioReg());
			   ps.setString(12,inc.getActivo());
			   ps.setString(13,inc.getIdSituacionTicket());
			   ps.executeUpdate();
			   ok=1;
			   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    
		  //System.out.println(sql);
	      return ok;
	   }
	   
	   
	   
	   public int anular(Incidencia inc) {
		   System.out.println("Ingresa para actualizar");
		   PreparedStatement ps;
		   ResultSet rs;
		   int ok=0;
		   java.util.Date d1 = new java.util.Date();
	       java.sql.Timestamp fechaAnul = new java.sql.Timestamp(d1.getTime());
	       
		   String sql = "UPDATE gi_incidencia SET usuario_anul=?,fecha_anul=?,motivo_anul=?,activo=?"  
		   			+" WHERE (id_incidencia=?)";
		   
		   try {
			   ps = conexion.prepareStatement(sql);	   
			   ps.setString(1,inc.getUsuarioAnul());
			   ps.setTimestamp(2,fechaAnul);
			   ps.setString(3,inc.getMotivoAnul());
 			   ps.setString(4,inc.getActivo());
			   ps.setString(5,inc.getIdIncidencia());

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
	   
	   
	   public int generarNuevaIncidencia() {   
		   int maxID = 0;
		   Calendar fecha = Calendar.getInstance();
	       int anio = fecha.get(Calendar.YEAR);
	       System.out.println("Este es el año"+anio);
		   try {
			   
			   PreparedStatement ps;
			   ResultSet rs;
			 
			   String sql = "SELECT MAX(num_incidencia) as num_inci FROM gi_incidencia WHERE (anio_incindencia =?) ";
			   ps = conexion.prepareStatement(sql);//ps.setString(1, u);
			   ps.setInt(1,anio);	
			   rs = ps.executeQuery();
			   while(rs.next()){
				   maxID = (rs.getInt("num_inci")+1);
			   }
			   System.out.println("");
			   
		} catch (Exception e) {
			// TODO: handle exception

			System.out.println("Error en la consulta");
			e.printStackTrace();
		}
	      return maxID;
	   }
	   
	   public String generarNuevaIDIncidencia() {   
		   String maxID = "";
		   Calendar fecha = Calendar.getInstance();
	       int anio = fecha.get(Calendar.YEAR);
	       System.out.println("Este es el año"+anio);
		   try {
			   PreparedStatement ps;
			   ResultSet rs;
			   String sql = "SELECT ISNULL(MAX(num_incidencia),0) as num_inci FROM gi_incidencia WHERE (anio_incindencia =?)  ";
			   ps = conexion.prepareStatement(sql);//ps.setString(1, u);
			   ps.setInt(1,anio);	
			   rs = ps.executeQuery();
			   while(rs.next()){
				   maxID = String.valueOf((rs.getInt("num_inci")+1));
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
   
	   public List<Incidencia> recuperarEstadoPorIncidencia(String idIncidencia) {
		   Incidencia inci=null;
		   List<Incidencia> lisInc=new ArrayList<Incidencia>();;
		   	String sql = "SELECT gi_incidencia.id_incidencia,gi_incidencia.num_incidencia,gi_incidencia.anio_incidencia,(SELECT LIST(gs_tab_items.nom_corto) FROM gs_tab_items,gi_atencion WHERE gi_atencion.id_incidencia=gi_incidencia.id_incidencia AND gi_atencion.id_estado_servicio=gs_tab_items.id_item AND gs_tab_items.id_tabla='TIPO_ESTADO')AS estado FROM gi_incidencia INNER JOIN gu_dependencia ON gi_incidencia.id_dependencia = gu_dependencia.id_dependencia INNER JOIN gi_solicitante ON gi_incidencia.id_solicitante = gi_solicitante.id_solicitante INNER JOIN gu_usuario ON gi_incidencia.id_usuario = gu_usuario.id_usuario WHERE (gi_incidencia.activo='S') AND gi_incidencia.id_incidencia =? ORDER BY estado asc";
		    PreparedStatement ps;
		    ResultSet rs;	
		   // System.out.println(sql);
			try {
				ps = conexion.prepareStatement(sql);
				ps.setString(1, idIncidencia);				
			    rs = ps.executeQuery();
			   // System.out.println(sql);
			    if (rs.next()){			    	
			    	inci=new Incidencia();			    	
			    	inci.setIdIncidencia(rs.getString("id_incidencia"));
			    	inci.setNumIncidencia(rs.getInt("num_incidencia"));
			    	inci.setAnioIncidencia(rs.getInt("anio_incidencia"));
			    	//inci.setEstado(rs.getString("estado"));
			    	//System.out.println(sql);
			    	lisInc.add(inci);			    	
			    	}
			    rs.close();
			    ps.close();		    		    
			    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
	      return lisInc;
	      
		   }
}
	   