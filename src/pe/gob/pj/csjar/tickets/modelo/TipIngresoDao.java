package pe.gob.pj.csjar.tickets.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pe.gob.pj.csjar.tickets.controlador.TipoIngreso;

public class TipIngresoDao {
	private Connection conexion;
	   public int setConexion(Connection cnn) {
			this.conexion = cnn;
			return 1;
	   }
	   
	  
	   public List<TipoIngreso> recuperarPorIdTipIng(String idTipIng) {
	   
	      return null;
	   }
	   
	 
	   public List<TipoIngreso> recuperarTodo() {
		   TipoIngreso tipIng=null;
		   List<TipoIngreso> lisTipIng=new ArrayList<TipoIngreso>();
		   	String sql = "SELECT * FROM gi_tipo_ingreso WHERE (activo='S' or isnull(activo,'N')='N')";
		    PreparedStatement ps;
		    ResultSet rs;	
		   // System.out.println(sql);
			try {
				ps = conexion.prepareStatement(sql);//ps.setString(1, u);				
			    rs = ps.executeQuery();
			   // System.out.println(sql);			
			    while(rs.next()) {// if (rs.next()){				   		    	
			    	tipIng=new TipoIngreso();			    	
			    	tipIng.setIdTipIng(rs.getInt("id_tip_ing"));
			    	tipIng.setTipoIngreso(rs.getString("tipo_ingreso"));
			    	tipIng.setActivo(rs.getString("activo"));		    	
			    	lisTipIng.add(tipIng);			    	
			    }
			    rs.close();
			    ps.close();		    		    
			    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
	      return lisTipIng;
	   }
	   
	
	   public int insertar(TipoIngreso tipIng) {
	      // TODO: implement
	      return 0;
	   }
	   
	 
	   public int actualizar(TipoIngreso tipIng) {
	      // TODO: implement
	      return 0;
	   }

	}
