package pe.gob.pj.csjar.tickets.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pe.gob.pj.csjar.tickets.controlador.Equipo;
import pe.gob.pj.csjar.tickets.controlador.Local;

public class LocalDao {
	   private Connection conexion;
	   public int setConexion(Connection cnn) {
			this.conexion = cnn;
	      return 1;
	   }

	   
	   public List<Local> recuperarTodo() {
		   System.out.println("Deberia recuperar todo los locales");
		   Local loc=null;
		   List<Local> lisLoc=new ArrayList<Local>();
		   	String sql = "SELECT id_local, nom_local, des_local, direccion,id_dist_instit,activo FROM gu_local WHERE activo='S'";
		    PreparedStatement ps;
		    ResultSet rs;	
			try {
				ps = conexion.prepareStatement(sql);			
			    rs = ps.executeQuery();
			    while(rs.next()) {				   		    	
			    	loc=new Local();
			    	loc.setIdLocal(rs.getInt("id_local"));
			    	loc.setNomLocal(rs.getString("nom_local"));
			    	loc.setDesLocal(rs.getString("des_local"));
			    	loc.setDireccion(rs.getString("direccion"));
			    	loc.setIdDistInstit(rs.getInt("id_dist_instit"));			    	
			    	loc.setActivo(rs.getString("activo"));
			    	lisLoc.add(loc);	
			    }
			    rs.close();
			    ps.close();   		    
			    } catch (SQLException e) {
				e.printStackTrace();
			    }
			System.out.println("se genera la lista");
	      return lisLoc;
	   }
	   
}
