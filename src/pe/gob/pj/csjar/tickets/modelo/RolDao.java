package pe.gob.pj.csjar.tickets.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pe.gob.pj.csjar.tickets.controlador.Rol;

public class RolDao {
	   private Connection conexion;
	   public int setConexion(Connection cnn) {
			this.conexion = cnn;
			return 1;
	   }
	   
	  
	   public List<Rol> recuperarPorIdRol(String idRol) {
	   
	      return null;
	   }
	   
	 
	   public List<Rol> recuperarTodo() {
		   Rol rol=null;
		   List<Rol> lisRol=new ArrayList<Rol>();
		   	String sql = "SELECT * FROM gu_rol WHERE (activo='S' or isnull(activo,'N')='N')";
		    PreparedStatement ps;
		    ResultSet rs;	
		   // System.out.println(sql);
			try {
				ps = conexion.prepareStatement(sql);//ps.setString(1, u);				
			    rs = ps.executeQuery();
			   // System.out.println(sql);			
			    while(rs.next()) {// if (rs.next()){				   		    	
			    	rol=new Rol();			    	
			    	rol.setIdRol(rs.getInt("id_rol"));
			    	rol.setNomRol(rs.getString("nom_rol"));
			    	rol.setNivelAcceso(rs.getString("nivel_acceso"));
			    	rol.setDesRol(rs.getString("des_rol"));
			    	rol.setIdInstitucion(rs.getInt("id_institucion"));
			    	rol.setActivo(rs.getString("activo"));		    	
			    	lisRol.add(rol);			    	
			    }
			    rs.close();
			    ps.close();		    		    
			    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
	      return lisRol;
	   }
	   
	
	   public int insertar(Rol rol) {
	      // TODO: implement
	      return 0;
	   }
	   
	 
	   public int actualizar(Rol rol) {
	      // TODO: implement
	      return 0;
	   }

	}
