package pe.gob.pj.csjar.tickets.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pe.gob.pj.csjar.tickets.controlador.TabItems;;

public class TabItemsDao {
	
	
	private Connection conexion;
	   public int setConexion(Connection cnn) {
			this.conexion = cnn;
			return 1;
	   }
	   
	  
	   public List<TabItems> recuperarPorIdTabla(String idTabla) {
		   TabItems tItems=null;
		   List<TabItems> lisItems=new ArrayList<TabItems>();;
		   	String sql = "SELECT * FROM gs_tab_items WHERE activo='S' AND id_tabla=?;";
		    PreparedStatement ps;
		    ResultSet rs;	
		   // System.out.println(sql);
			try {
				ps = conexion.prepareStatement(sql);
				ps.setString(1, idTabla);				
			    rs = ps.executeQuery();
			   // System.out.println(sql);
			    while (rs.next()){			    	
			    	tItems=new TabItems();			    	
			    	tItems.setIdTabla(rs.getString("id_tabla"));
			    	tItems.setIdItem(rs.getString("id_item"));
			    	tItems.setNomCorto(rs.getString("nom_corto"));
			    	tItems.setNomLargo(rs.getString("nom_largo"));
			    	tItems.setPrerelacion(rs.getDouble("prelacion"));
			    	tItems.setNivel(rs.getInt("nivel"));			    	
			    	tItems.setIdItemSup(rs.getString("id_item_sup"));
			    	tItems.setActivo(rs.getString("activo"));
			    	//System.out.println(sql);
			    	lisItems.add(tItems);			    	
			    	}
			    rs.close();
			    ps.close();		    		    
			    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
	      return lisItems;
	      
	   }
	   
	 
	  
	   
	
	   public int insertar(TabItems item) {
	      // TODO: implement
	      return 0;
	   }
	   
	 
	   public int actualizar(TabItems item) {
	      // TODO: implement
	      return 0;
	   }

}
