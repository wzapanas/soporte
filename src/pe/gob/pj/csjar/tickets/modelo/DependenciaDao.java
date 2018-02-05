package pe.gob.pj.csjar.tickets.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pe.gob.pj.csjar.tickets.controlador.Dependencia;

public class DependenciaDao {
	private Connection conexion;
	   public int setConexion(Connection cnn) {
			this.conexion = cnn;
			return 1;
	   }
	   
	  
	   public List<Dependencia> recuperarPorIdDependencia(String idDependencia) {
	   
	      return null;
	   }
	   
	 
	   public List<Dependencia> recuperarTodo() {
		   System.out.print("Entro aqui");
		   Dependencia dep=null;
		   List<Dependencia> lisDep=new ArrayList<Dependencia>();
		   	String sql = "SELECT * FROM gu_dependencia WHERE (activo='S' or isnull(activo,'N')='N')";
		    PreparedStatement ps;
		    ResultSet rs;	
		   // System.out.println(sql);
			try {
				ps = conexion.prepareStatement(sql);//ps.setString(1, u);				
			    rs = ps.executeQuery();
			   // System.out.println(sql);			
			    while(rs.next()) {// if (rs.next()){				   		    	
			    	dep=new Dependencia();			    	
			    	dep.setIdDependencia(rs.getInt("id_dependencia"));
			    	dep.setNomDep(rs.getString("nom_dep"));
			    	dep.setDesDep(rs.getString("des_dep"));
			    	dep.setNivel(rs.getInt("nivel"));
			    	dep.setIdDepSup(rs.getInt("id_dep_sup"));
			    	dep.setIdLocal(rs.getInt("id_local"));
			    	dep.setActivo(rs.getString("activo"));		    	
			    	lisDep.add(dep);			    	
			    }
			    rs.close();
			    ps.close();		    		    
			    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
	      return lisDep;
	   }
	   
	   
	   public List<Dependencia> recuperarDependencia() {
		   System.out.print("Entro aqui");
		   Dependencia dep=null;
		   List<Dependencia> lisDep=new ArrayList<Dependencia>();
		   	String sql = "SELECT * FROM gu_dependencia WHERE (activo='S') AND nivel = 5";
		    PreparedStatement ps;
		    ResultSet rs;	
		   // System.out.println(sql);
			try {
				ps = conexion.prepareStatement(sql);//ps.setString(1, u);				
			    rs = ps.executeQuery();
			   // System.out.println(sql);			
			    while(rs.next()) {// if (rs.next()){				   		    	
			    	dep=new Dependencia();			    	
			    	dep.setIdDependencia(rs.getInt("id_dependencia"));
			    	dep.setNomDep(rs.getString("nom_dep"));
			    	dep.setDesDep(rs.getString("des_dep"));
			    	dep.setNivel(rs.getInt("nivel"));
			    	dep.setIdDepSup(rs.getInt("id_dep_sup"));
			    	dep.setIdLocal(rs.getInt("id_local"));
			    	dep.setActivo(rs.getString("activo"));		    	
			    	lisDep.add(dep);			    	
			    }
			    rs.close();
			    ps.close();		    		    
			    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
	      return lisDep;
	   }
	   
	   public List<Dependencia> recuperarDepAdm() {
		   System.out.print("Entro aqui");
		   Dependencia dep=null;
		   List<Dependencia> lisDep=new ArrayList<Dependencia>();
		   	String sql = "SELECT id_dependencia, nom_dep FROM gu_dependencia WHERE id_dep_sup ='5' AND activo='S'";
		    PreparedStatement ps;
		    ResultSet rs;	
		   // System.out.println(sql);
			try {
				ps = conexion.prepareStatement(sql);//ps.setString(1, u);				
			    rs = ps.executeQuery();
			   // System.out.println(sql);			
			    while(rs.next()) {// if (rs.next()){				   		    	
			    	dep=new Dependencia();			    	
			    	dep.setIdDependencia(rs.getInt("id_dependencia"));
			    	dep.setNomDep(rs.getString("nom_dep"));		    	
			    	lisDep.add(dep);			    	
			    }
			    rs.close();
			    ps.close();		    		    
			    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
	      return lisDep;
	   }
	   
	   public List<Dependencia> recuperarDepJuric() {
		   System.out.print("Entro aqui");
		   Dependencia dep=null;
		   List<Dependencia> lisDep=new ArrayList<Dependencia>();
		   	String sql = "SELECT id_dependencia, nom_dep FROM gu_dependencia WHERE id_dep_sup ='4' AND activo='S'";
		    PreparedStatement ps;
		    ResultSet rs;	
		   // System.out.println(sql);
			try {
				ps = conexion.prepareStatement(sql);//ps.setString(1, u);				
			    rs = ps.executeQuery();
			   // System.out.println(sql);			
			    while(rs.next()) {// if (rs.next()){				   		    	
			    	dep=new Dependencia();			    	
			    	dep.setIdDependencia(rs.getInt("id_dependencia"));
			    	dep.setNomDep(rs.getString("nom_dep"));	    	
			    	lisDep.add(dep);			    	
			    }
			    rs.close();
			    ps.close();		    		    
			    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
	      return lisDep;
	   }
	   
	   public List<Dependencia> recuperarUbicacion() {
		   System.out.print("Entro aqui");
		   Dependencia dep=null;
		   List<Dependencia> lisDep=new ArrayList<Dependencia>();
		   	String sql = "SELECT * FROM gu_dependencia WHERE (activo='S') AND nivel = 10";
		    PreparedStatement ps;
		    ResultSet rs;	
		   // System.out.println(sql);
			try {
				ps = conexion.prepareStatement(sql);//ps.setString(1, u);				
			    rs = ps.executeQuery();
			   // System.out.println(sql);			
			    while(rs.next()) {// if (rs.next()){				   		    	
			    	dep=new Dependencia();			    	
			    	dep.setIdDependencia(rs.getInt("id_dependencia"));
			    	dep.setNomDep(rs.getString("nom_dep"));
			    	dep.setDesDep(rs.getString("des_dep"));
			    	dep.setNivel(rs.getInt("nivel"));
			    	dep.setIdDepSup(rs.getInt("id_dep_sup"));
			    	dep.setIdLocal(rs.getInt("id_local"));
			    	dep.setActivo(rs.getString("activo"));		    	
			    	lisDep.add(dep);			    	
			    }
			    rs.close();
			    ps.close();		    		    
			    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
	      return lisDep;
	   }
	   
	   
	   
	
	   public int insertar(Dependencia dep) {
	      // TODO: implement
	      return 0;
	   }
	   
	 
	   public int actualizar(Dependencia dep) {
	      // TODO: implement
	      return 0;
	   }

	}
