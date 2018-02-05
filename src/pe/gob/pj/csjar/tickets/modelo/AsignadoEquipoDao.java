package pe.gob.pj.csjar.tickets.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pe.gob.pj.csjar.tickets.controlador.AsignadoEquipo;
import pe.gob.pj.csjar.tickets.controlador.Usuario;

public class AsignadoEquipoDao {
	   private Connection conexion;
	   
	   public int setConexion(Connection cnn) {
			this.conexion = cnn;
	      return 1;
	   }
	   public List<AsignadoEquipo> recuperarPorIdEquipo(String idEquipo) {
		   System.out.println("Se fue por aqui");
		   AsignadoEquipo asigEq=null;
		   List<AsignadoEquipo> lisUsr=new ArrayList<AsignadoEquipo>();
		   	String sql = "SELECT id_asignado,id_equipo,grupo,secuencia,nom_equipo,dir_ip,activo FROM ge_asignado_equipo WHERE activo='S' AND id_equipo=?";
		    PreparedStatement ps;
		    ResultSet rs;	
		   // System.out.println(sql);
			try {
				ps = conexion.prepareStatement(sql);
				ps.setString(1, idEquipo);				
			    rs = ps.executeQuery();
			   // System.out.println(sql);
			    if (rs.next()){			    	
			    	asigEq=new AsignadoEquipo();			    	
			    	asigEq.setIdAsignado(rs.getString("id_asignado"));
			    	asigEq.setIdEquipo(rs.getString("id_equipo"));
			    	asigEq.setSecuencia(rs.getString("secuencia"));
			    	asigEq.setGrupo(rs.getInt("grupo"));
			    	asigEq.setNomEquipo(rs.getString("nom_equipo"));
			    	asigEq.setDirIp(rs.getString("dir_ip"));			    	
			    	asigEq.setActivo(rs.getString("activo"));
			    	//System.out.println(sql);
			    	lisUsr.add(asigEq);			    	
			    	}
			    rs.close();
			    ps.close();		    		    
			    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
	      return lisUsr;
	      
		   }

	   public List<Usuario> recuperarPorUsuario(String u) {
		   Usuario usr=null;
		   List<Usuario> lisUsr=new ArrayList<Usuario>();;
		   	String sql = "SELECT * FROM gu_usuario WHERE activo='S' AND usuario=?";
		    PreparedStatement ps;
		    ResultSet rs;	
		   // System.out.println(sql);
			try {
				ps = conexion.prepareStatement(sql);
				ps.setString(1, u);				
			    rs = ps.executeQuery();
			   // System.out.println(sql);
			    if (rs.next()){			    	
			    	usr=new Usuario();			    	
			    	usr.setIdUsuario(rs.getInt("id_usuario"));
			    	usr.setUsuario(rs.getString("usuario"));
			    	usr.setApePaterno(rs.getString("ape_paterno"));
			    	usr.setApeMaterno(rs.getString("ape_materno"));
			    	usr.setNombres(rs.getString("nombres"));
			    	usr.setDni(rs.getString("dni"));			    	
			    	usr.setEmail(rs.getString("email"));
			    	usr.setClave(rs.getString("clave"));
			    	usr.setIdRol(rs.getInt("id_rol"));
			    	usr.setIdDependencia(rs.getInt("id_dependencia"));
			    	usr.setActivo(rs.getString("activo"));
			    	//System.out.println(sql);
			    	lisUsr.add(usr);			    	
			    	}
			    rs.close();
			    ps.close();		    		    
			    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
	      return lisUsr;
	   }
	   
	   
	   public List<Usuario> recuperarTodo() {
		   System.out.println("Deberia entrar aqui");
		   Usuario usr=null;
		   List<Usuario> lisUsr=new ArrayList<Usuario>();
		   	String sql = "SELECT gu_usuario.*,gu_rol.nom_rol,gu_dependencia.nom_dep FROM gu_usuario INNER JOIN gu_rol ON (gu_rol.id_rol=gu_usuario.id_rol), gu_usuario INNER JOIN gu_dependencia ON (gu_dependencia.id_dependencia=gu_usuario.id_dependencia) WHERE (gu_usuario.activo='S')";
		    PreparedStatement ps;
		    ResultSet rs;	
		   // System.out.println(sql);
			try {
				ps = conexion.prepareStatement(sql);//ps.setString(1, u);				
			    rs = ps.executeQuery();
			   // System.out.println(sql);
			    while(rs.next()) {// if (rs.next()){				   		    	
			    	usr=new Usuario();			    	
			    	usr.setIdUsuario(rs.getInt("id_usuario"));
			    	usr.setUsuario(rs.getString("usuario"));
			    	usr.setApePaterno(rs.getString("ape_paterno"));
			    	usr.setApeMaterno(rs.getString("ape_materno"));
			    	usr.setNombres(rs.getString("nombres"));
			    	usr.setDni(rs.getString("dni"));			    	
			    	usr.setEmail(rs.getString("email"));
			    	usr.setClave(rs.getString("clave"));
			    	usr.setIdRol(rs.getInt("id_rol"));
			    	usr.setIdDependencia(rs.getInt("id_dependencia"));
			    	usr.setActivo(rs.getString("activo"));
			    	usr.setNomRol(rs.getString("nom_rol"));
			    	usr.setNomDep(rs.getString("nom_dep"));
			    	//System.out.println(sql);
			    	lisUsr.add(usr);			    	
			    }
			    rs.close();
			    ps.close();		    		    
			    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
			System.out.println("se genera la lista");
	      return lisUsr;
	   }
	   
	   public int insertar(AsignadoEquipo asigEq) {
		   PreparedStatement ps;
		   ResultSet rs;
		   int ok=0;
		   String sql = "INSERT INTO ge_asignado_equipo (id_asignado,id_equipo,grupo,nom_equipo,dir_ip,activo)"  
		   			+" VALUES (?,?,?,?,?,?)";		  
		   try {
			   ps = conexion.prepareStatement(sql);	   
			   ps.setString(1,asigEq.getIdAsignado());
			   ps.setString(2,asigEq.getIdEquipo());
			   ps.setInt(3,asigEq.getGrupo());
			   ps.setString(4,asigEq.getNomEquipo());
			   ps.setString(5,asigEq.getDirIp());
			   ps.setString(6,"S");	
			   ps.executeUpdate();
			   ok=1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	      return ok;
	   }
	   
	   public int actualizar(AsignadoEquipo asigEq) {
		   PreparedStatement ps;
		   ResultSet rs;
		   int ok=0;
		   String sql = "UPDATE ge_asignado_equipo SET id_asignado=?,grupo=?"  
		   			+" WHERE (id_equipo=?)";		  
		   try {
			   ps = conexion.prepareStatement(sql);	   
			   ps.setString(1,asigEq.getIdAsignado());
			   ps.setInt(2,asigEq.getGrupo());
			   ps.setString(3,asigEq.getIdEquipo());
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
	   
	   
	   public int borrarAsignadoEquipo(AsignadoEquipo asigEq) {
		   PreparedStatement ps;
		   ResultSet rs;
		   int ok=0;
		   String sql = "DELETE FROM ge_asignado_equipo"  
		   			+" WHERE id_equipo=?";		  
		   try {
			   ps = conexion.prepareStatement(sql);	   
			   ps.setString(1,asigEq.getIdEquipo());
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
	}