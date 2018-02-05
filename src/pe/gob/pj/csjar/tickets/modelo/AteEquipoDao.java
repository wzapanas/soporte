package pe.gob.pj.csjar.tickets.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import pe.gob.pj.csjar.tickets.controlador.AteEquipo;
import pe.gob.pj.csjar.tickets.controlador.Atencion;

public class AteEquipoDao {
	private Connection conexion;
	   public int setConexion(Connection cnn) {
			this.conexion = cnn;
	      return 1;
	   }
	   
	   
	   public int insertar(AteEquipo atEq) {
		   PreparedStatement ps;
		   ResultSet rs;
		   

	       String activo = "S";
		   int ok=0;
		   String sql = "INSERT INTO gi_ate_equipo (num_item,id_tip_equipo,id_marca,id_modelo,serie,id_atencion,activo)"  
		   			+" VALUES (?,?,?,?,?,?,?)";	
		   
		   String idAtencion = atEq.getIdAtencion();
		   int numItem = generarNuevaIdAteEquipo(idAtencion);
		   System.out.println("Atencion------------- "+idAtencion);
		   System.out.println("numItem------------- "+numItem);
		   try {
			   ps = conexion.prepareStatement(sql);
			   ps.setInt(1,numItem);
			   ps.setString(2,atEq.getIdTipEquipo());
			   ps.setString(3,atEq.getIdMarca());
			   ps.setString(4,atEq.getIdModelo());
			   ps.setString(5,atEq.getSerie());
			   ps.setString(6,idAtencion);
			   ps.setString(7,activo);
			   
			   ps.executeUpdate();
			   ok=1;
			   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    
		  //System.out.println(sql);
	      return ok;
	   }   
	   
	   
	   
	   public int generarNuevaIdAteEquipo(String id) {   
		   int maxID = 0;
		   
		   try {
			   
			   PreparedStatement ps = null;
			   ResultSet rs;
			 
			   String sql = "SELECT ISNULL(MAX(num_item),0) as id_ate FROM gi_ate_equipo WHERE id_atencion=?";
			   ps = conexion.prepareStatement(sql);//ps.setString(1, u);
			   ps.setString(1,id);
			   
			   rs = ps.executeQuery();
			   
			   while(rs.next()){
				   maxID = (rs.getInt("id_ate")+1);
			   }
			   System.out.println("zzzzz" +maxID);
			   
		} catch (Exception e) {
			// TODO: handle exception

			System.out.println("Error en la consulta");
			e.printStackTrace();
		}
	      return maxID;
	   }
	   
	   
	   
	   public List<AteEquipo> recuperarPorAteEq(String idAtencion) {
		   System.out.println("A RECUPERAR ATENCIONES DE EQUIPO");
		   AteEquipo ateEq=null;
		   List<AteEquipo> lisAteEq=new ArrayList<AteEquipo>();
		   	String sql = "SELECT * FROM gi_ate_equipo WHERE activo='S' AND id_atencion = ?";
		    PreparedStatement ps;
		    ResultSet rs;	
		   // System.out.println(sql);
			try {
				ps = conexion.prepareStatement(sql);
				ps.setString(1, idAtencion);
			    rs = ps.executeQuery();
			   // System.out.println(sql);
			    if (rs.next()){			    	
			    	ateEq=new AteEquipo();			    	
			    	ateEq.setNumItem(rs.getInt("numItem"));
			    	ateEq.setIdTipEquipo(rs.getString("idTipEquipo"));
			    	ateEq.setIdMarca(rs.getString("idMarca"));
			    	ateEq.setIdModelo(rs.getString("idModelo"));
			    	ateEq.setSerie(rs.getString("serie"));			    	
			    	ateEq.setActivo(rs.getString("activo"));
			    	ateEq.setIdAtencion(rs.getString("idAtencion"));
			    	//System.out.println(sql);
			    	lisAteEq.add(ateEq);
			    	
			    	}
			    rs.close();
			    ps.close();	
			    System.out.println("Este es la lista "+lisAteEq);
			    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
	      return lisAteEq;
	      
		   }
	   
	   
	   public int actualizar(AteEquipo atEq) {
		   PreparedStatement ps;
		   ResultSet rs;
		   
		   int ok=0;
		   String sql = "UPDATE gi_ate_equipo SET id_tip_equipo=?,id_marca=?,id_modelo=?,serie=?,activo=?"  
				   +" WHERE id_atencion=?";

		   try {
			   ps = conexion.prepareStatement(sql);
			   ps.setString(1,atEq.getIdTipEquipo());
			   ps.setString(2,atEq.getIdMarca());
			   ps.setString(3,atEq.getIdModelo());
			   ps.setString(4,atEq.getSerie());
			   ps.setString(5,atEq.getActivo());
			   ps.setString(6,atEq.getIdAtencion());
			   
			   ps.executeUpdate();
			   ok=1;
			   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    
		  //System.out.println(sql);
	      return ok;
	   }   
	   
	   
	  
}
