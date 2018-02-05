package pe.gob.pj.csjar.tickets.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import pe.gob.pj.csjar.tickets.controlador.Asignado;
import pe.gob.pj.csjar.tickets.controlador.Usuario;

public class AsignadoDao {
	   private Connection conexion;
	   
	   public int setConexion(Connection cnn) {
			this.conexion = cnn;
	      return 1;
	   }
	 
	   
	   public int actualizar(Usuario usr) {
		   PreparedStatement ps;
		   ResultSet rs;
		   int ok=0;
		   String sql = "UPDATE gu_usuario SET usuario=?,ape_paterno=?,ape_materno=?,nombres=?,dni=?,email=?,clave=?,id_rol=?,id_dependencia=?,activo=?"  
		   			+" WHERE (id_usuario=?)";		  
		   try {
			   ps = conexion.prepareStatement(sql);	   
			   ps.setString(1,usr.getUsuario());
			   ps.setString(2,usr.getApePaterno());
			   ps.setString(3,usr.getApeMaterno());
			   ps.setString(4,usr.getNombres());
			   ps.setString(5,usr.getDni());
			   ps.setString(6,usr.getEmail());
			   ps.setString(7,usr.getClave());
			   ps.setInt(8,usr.getIdRol());
			   ps.setInt(9,usr.getIdDependencia());
			   ps.setString(10,usr.getActivo());
			   ps.setInt(11,usr.getIdUsuario());
			   //ps.setDouble(11,(double) usr.getIdUsuario());

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
	   
	   
	   public int anular(Usuario usr) {
		   PreparedStatement ps;
		   ResultSet rs;
		   int ok=0;
		   String sql = "UPDATE gu_usuario SET activo=?"  
		   			+" WHERE (id_usuario=?)";		  
		   try {
			   ps = conexion.prepareStatement(sql);	   
			   ps.setString(1,usr.getActivo());
			   ps.setInt(2,usr.getIdUsuario());
			   //ps.setDouble(11,(double) usr.getIdUsuario());

			   ps.executeUpdate();		
			   ok=1;
			   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    
		  //System.out.println(sql);
	      return ok;
	   }
	   
	   public List<Asignado> recuperarAsignacion(String idAsignado) {
		   Asignado asig=null;
		   List<Asignado> lisAsig=new ArrayList<Asignado>();
		   	String sql = "SELECT id_asignado,fec_asigna,id_dependencia,id_cargo,id_solicitante,nro_oficina,ultimo,piso,observacion,activo FROM ge_asignado_a WHERE ge_asignado_a.activo='S' AND ge_asignado_a.id_asignado=?";
		    PreparedStatement ps;
		    ResultSet rs;	
		   // System.out.println(sql);
			try {
				ps = conexion.prepareStatement(sql);
				ps.setString(1, idAsignado);				
			    rs = ps.executeQuery();
			   // System.out.println(sql);
			    if (rs.next()){			    	
			    	asig=new Asignado();			    	
			    	asig.setIdAsignado(rs.getString("id_asignado"));
			    	asig.setFecAsigna(rs.getTimestamp("fec_asigna"));
			    	asig.setIdDependencia(rs.getInt("id_dependencia"));
			    	asig.setIdCargo(rs.getString("id_cargo"));
			    	asig.setIdSolicitante(rs.getInt("id_solicitante"));
			    	asig.setNroOficina(rs.getString("nro_oficina"));			    	
			    	asig.setUltimo(rs.getString("ultimo"));
			    	asig.setPiso(rs.getString("piso"));
			    	asig.setObservacion(rs.getString("observacion"));
			    	asig.setActivo(rs.getString("activo"));
			    	asig.setAuxFecAsigna(rs.getString("fec_asigna"));
			    	//System.out.println(sql);
			    	lisAsig.add(asig);			    	
			    	}
			    rs.close();
			    ps.close();		    		    
			    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
	      return lisAsig;
	   }

	   public int insertar(Asignado asig) {
		   PreparedStatement ps;
		   ResultSet rs;
		   Calendar fecha = Calendar.getInstance();
	       int anio = fecha.get(Calendar.YEAR);
		   int ok=0;
		   String sql = "INSERT INTO ge_asignado_a (id_asignado,fec_asigna,id_dependencia,id_cargo,id_solicitante,nro_oficina,ultimo,piso,observacion,usuario_reg,anio_asignado,activo)"  
		   			+" VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";		  
		   try {
			   ps = conexion.prepareStatement(sql);	   
			   ps.setString(1,asig.getIdAsignado());
			   ps.setTimestamp(2,asig.getFecAsigna());
			   ps.setInt(3,asig.getIdDependencia());
			   ps.setString(4,asig.getIdCargo());
			   ps.setInt(5,asig.getIdSolicitante());
			   ps.setString(6,asig.getNroOficina());
			   ps.setString(7,asig.getUltimo());
			   ps.setString(8,asig.getPiso());
			   ps.setString(9,asig.getObservacion());
			   ps.setString(10,asig.getUsuarioReg());
			   ps.setInt(11,anio);
			   ps.setString(12,asig.getActivo());			   
			   ps.executeUpdate();
			   ok=1;
			   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    
		  //System.out.println(sql);
	      return ok;
	   }	   
	   

		public String generarNuevaIdAsignado() {   
			   String maxID = "";
			   Calendar fecha = Calendar.getInstance();
		    int anio = fecha.get(Calendar.YEAR);
			   try {
				   PreparedStatement ps;
				   ResultSet rs;
				   String sql = "SELECT ISNULL(MAX(id_asignado),0) as idAsignado FROM ge_asignado_a WHERE (anio_asignado =?)  ";
				   ps = conexion.prepareStatement(sql);//ps.setString(1, u);
				   ps.setInt(1,anio);	
				   rs = ps.executeQuery();
				   while(rs.next()){
					   maxID = String.valueOf((rs.getInt("idAsignado")+1));
				   }
				   System.out.println("Este es es año: "+anio+ " Este es id "+maxID);
				   //maxID = String.valueOf(anio) + String.format("%05d",Integer.parseInt(maxID));
				   maxID = String.format("%05d",Integer.parseInt(maxID));
				   System.out.println("Id Asignado es: "+maxID);
			} catch (Exception e) {
				System.out.println("Id Asignado es: "+maxID);
				e.printStackTrace();
			}
		   return maxID;
		}
	   
}