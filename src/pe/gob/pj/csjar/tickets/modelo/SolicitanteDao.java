package pe.gob.pj.csjar.tickets.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import pe.gob.pj.csjar.tickets.controlador.Incidencia;
import pe.gob.pj.csjar.tickets.controlador.Solicitante;

public class SolicitanteDao {
	   private Connection conexion;
	   public int setConexion(Connection cnn) {
			this.conexion = cnn;
			return 1;
	   }
	   
	 
	 
	   public List<Solicitante> recuperarTodo() {
		   Solicitante soli=null;
		   List<Solicitante> liSol=new ArrayList<Solicitante>();
		   	String sql = "SELECT * FROM gi_solicitante WHERE activo='S' ORDER BY nombres, ape_paterno, ape_materno";
		    PreparedStatement ps;
		    ResultSet rs;	
		   // System.out.println(sql);
			try {
				ps = conexion.prepareStatement(sql);//ps.setString(1, u);				
			    rs = ps.executeQuery();
			   // System.out.println(sql);			
			    while(rs.next()) {// if (rs.next()){				   		    	
			    	soli=new Solicitante();			    	
			    	soli.setIdSolicitante(rs.getInt("id_solicitante"));
			    	soli.setNombres(rs.getString("nombres"));
			    	soli.setApePaterno(rs.getString("ape_paterno"));
			    	soli.setApeMaterno(rs.getString("ape_materno"));
			    	soli.setIdDependencia(rs.getInt("id_dependencia"));
			    	soli.setActivo(rs.getString("activo"));		    	
			    	liSol.add(soli);			    	
			    }
			    rs.close();
			    ps.close();		    		    
			    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
	      return liSol;
	   }
	   
	
	   
	   
	   public int actualizar(Solicitante soli){
		   PreparedStatement ps;
		   ResultSet rs;
		   int ok=0;
		   String sql = "UPDATE gi_solicitante SET ape_paterno=?,ape_materno=?,nombres=?,id_dependencia=?,activo=?"  
		   			+" WHERE (id_solicitante=?)";
		   try {
			   ps = conexion.prepareStatement(sql);	   
			   ps.setString(1,soli.getApePaterno());
			   ps.setString(2,soli.getApeMaterno());
			   ps.setString(3,soli.getNombres());
			   ps.setInt(4,soli.getIdDependencia());
 			   ps.setString(5,soli.getActivo());
			   ps.setInt(6,soli.getIdSolicitante());
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
	   
	   
	   
	   public int insertar(Solicitante soli) {
		   PreparedStatement ps;
		   ResultSet rs;
		   int ok=0;
		   String sql = "INSERT INTO gi_solicitante (id_solicitante,ape_paterno,ape_materno,nombres,id_dependencia,activo)"  
		   			+" VALUES (?,?,?,?,?,?)";	
		   int s = generarIdSolicitante()+1;
		   System.out.print("Este es el id"+s);
		   try {
			   ps = conexion.prepareStatement(sql);	   
			   ps.setInt(1,s);
			   ps.setString(2,soli.getApePaterno());
			   ps.setString(3,soli.getApeMaterno());
			   ps.setString(4,soli.getNombres());
			   ps.setInt(5,soli.getIdDependencia());
			   ps.setString(6,soli.getActivo());			   
			   ps.executeUpdate();
			   ok=1;
		   } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			  //System.out.println(sql);
		      return ok;
		   }
	   
	   public int generarIdSolicitante() {   
		   int maxID = 0;
	       System.out.println("");
		   try {
			   PreparedStatement ps;
			   ResultSet rs;
			   String sql = "SELECT ISNULL(MAX(id_solicitante),0) as id FROM gi_solicitante";
			   ps = conexion.prepareStatement(sql);//ps.setString(1, u);	
			   rs = ps.executeQuery();
			   while(rs.next()){
				   maxID = (rs.getInt("id")+1);
			   }
			   System.out.println("");
			   
		} catch (Exception e) {
			// TODO: handle exception

			System.out.println("Error en la consulta");
			e.printStackTrace();
		}
	      return maxID;
	   }
	   
	   
	   
	   public List<Solicitante> recuperarPorIdSolicitante(String idSolicitante) {
		   Solicitante sol=null;
		   List<Solicitante> lisSol=new ArrayList<Solicitante>();
		   	String sql = "SELECT id_solicitante, nombres,ape_materno,ape_paterno,id_dependencia, activo FROM gi_solicitante WHERE activo='S' AND id_solicitante=?";
		    PreparedStatement ps;
		    ResultSet rs;	
		   // System.out.println(sql);
			try {
				ps = conexion.prepareStatement(sql);
				ps.setString(1, idSolicitante);				
			    rs = ps.executeQuery();
			   // System.out.println(sql);
			    if (rs.next()){			    	
			    	sol=new Solicitante();			    	
			    	sol.setIdSolicitante(rs.getInt("id_solicitante"));
			    	sol.setNombres(rs.getString("nombres"));
			    	sol.setApeMaterno(rs.getString("ape_materno"));
			    	sol.setApePaterno(rs.getString("ape_paterno"));
			    	sol.setIdDependencia(rs.getInt("id_dependencia"));
			    	sol.setActivo(rs.getString("activo"));			    	
			    	//System.out.println(sql);
			    	lisSol.add(sol);			    	
			    	}
			    rs.close();
			    ps.close();		    		    
			    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
	      return lisSol;
	      
		   }
	   
	   
	   
	   
	   public int anular(Solicitante sol) {
		   System.out.println("Ingresa para anualar el solicitante");
		   PreparedStatement ps;
		   ResultSet rs;
		   int ok=0;
		   String sql = "UPDATE gi_solicitante SET activo=?"  
		   			+" WHERE (id_solicitante=?)";
		   
		   try {
			   ps = conexion.prepareStatement(sql);	   
			   ps.setString(1,sol.getActivo());
 			   ps.setInt(2,sol.getIdSolicitante());
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
	   

	}
