package pe.gob.pj.csjar.tickets.modelo;

import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import pe.gob.pj.csjar.tickets.controlador.Equipo;
import pe.gob.pj.csjar.tickets.controlador.Incidencia;

public class EquipoDao {
	   private Connection conexion;
	   
	   public int setConexion(Connection cnn) {
			this.conexion = cnn;
	      return 1;
	   }
	   public List<Equipo> recuperarPorIdEquipo(String idEquipo) {
		   System.out.println("Se fue por aqui");
		   Equipo eq=null;
		   List<Equipo> lisEq=new ArrayList<Equipo>();
		   	String sql = "SELECT id_equipo,id_local,id_tipo_eq,id_marca,id_modelo,cod_patrimonial,grupo,serie,id_estado,fec_compra,activo FROM ge_equipo WHERE activo='S' AND id_equipo=?";
		    PreparedStatement ps;
		    ResultSet rs;	
		   // System.out.println(sql);
			try {
				ps = conexion.prepareStatement(sql);
				ps.setString(1, idEquipo);				
			    rs = ps.executeQuery();
			   // System.out.println(sql);
			    if (rs.next()){			    	
			    	eq=new Equipo();	
			    	eq.setIdEquipo(rs.getString("id_equipo"));
			    	eq.setIdTipEq(rs.getString("id_tipo_eq"));
			    	eq.setIdMarca(rs.getString("id_marca"));
			    	eq.setIdModelo(rs.getString("id_modelo"));
			    	eq.setIdLocal(rs.getInt("id_local"));
			    	eq.setCodPatrimonial(rs.getString("cod_patrimonial"));
			    	eq.setGrupo(rs.getString("grupo"));
			    	eq.setSerie(rs.getString("serie"));			    	
			    	eq.setIdEstado(rs.getString("id_estado"));
			    	eq.setFecCompra(rs.getTimestamp("fec_compra"));
			    	eq.setAuxFecCompra(((rs.getString("fec_compra")).toString()));
			    	eq.setActivo(rs.getString("activo"));
			    	lisEq.add(eq);		    	
			    	}
			    rs.close();
			    ps.close();		    		    
			    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
	      return lisEq;
		   }

	   public List<Equipo> recuperarTodo() {
		   System.out.println("Deberia entrar aqui");
		   Equipo eq=null;
		   List<Equipo> lisEq=new ArrayList<Equipo>();
		   	String sql = "SELECT ge_equipo.id_equipo,(SELECT ge_asignado_equipo.id_asignado FROM ge_asignado_equipo WHERE ge_asignado_equipo.id_equipo=ge_equipo.id_equipo) AS id_asignado,tip_eq.nom_corto AS equipo,mar_eq.nom_corto AS marca,mod_eq.nom_corto AS modelo,tip_est.nom_corto AS estado,ge_equipo.cod_patrimonial,ge_equipo.serie,ge_equipo.grupo FROM ge_equipo INNER JOIN gs_tab_items tip_eq ON (tip_eq.id_item = ge_equipo.id_tipo_eq) AND (tip_eq.id_tabla='TIPO_EQUIPO') INNER JOIN gs_tab_items mar_eq ON (mar_eq.id_item = ge_equipo.id_marca) AND (mar_eq.id_tabla='MARCA') INNER JOIN gs_tab_items mod_eq ON (mod_eq.id_item = ge_equipo.id_modelo) AND (mod_eq.id_tabla='MODELO') INNER JOIN gs_tab_items tip_est ON (tip_est.id_item = ge_equipo.id_estado) AND (tip_est.id_tabla='ESTADO_EQUIPO') WHERE (ge_equipo.activo='S')";
		    PreparedStatement ps;
		    ResultSet rs;	
		   // System.out.println(sql);
			try {
				ps = conexion.prepareStatement(sql);//ps.setString(1, u);				
			    rs = ps.executeQuery();
			   // System.out.println(sql);
			    while(rs.next()) {// if (rs.next()){				   		    	
			    	eq=new Equipo();
			    	eq.setIdEquipo(rs.getString("id_equipo"));
			    	eq.setTipEquipo(rs.getString("equipo"));
			    	eq.setMarca(rs.getString("marca"));
			    	eq.setModelo(rs.getString("modelo"));
			    	eq.setCodPatrimonial(rs.getString("cod_patrimonial"));
			    	eq.setGrupo(rs.getString("grupo"));
			    	eq.setSerie(rs.getString("serie"));			    	
			    	eq.setEstado(rs.getString("estado"));
			    	eq.setIdAsignado(rs.getString("id_asignado"));
			    	eq.setAsignado(rs.getString("id_asignado"));
			    	//System.out.println(sql);
			    	lisEq.add(eq);	
			    }
			    rs.close();
			    ps.close();   		    
			    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
			System.out.println("se genera la lista");
	      return lisEq;
	   }

	   public int insertar(Equipo eq) {
		   PreparedStatement ps;
		   ResultSet rs;
		   int ok=0;
		   String sql = "INSERT INTO ge_equipo (id_equipo,id_tipo_eq,id_marca,id_modelo,cod_patrimonial,grupo,serie,id_estado,usuario_reg,activo,fec_compra)"  
		   			+" VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		   String idEquipo = String.valueOf(generarNuevaIdEquipo());
		   eq.setIdEquipo(idEquipo);
		   try {
			   ps = conexion.prepareStatement(sql);	   
			   ps.setString(1,eq.getIdEquipo());
			   ps.setString(2,eq.getIdTipEq());
			   ps.setString(3,eq.getIdMarca());
			   ps.setString(4,eq.getIdModelo());
			   ps.setString(5,eq.getCodPatrimonial());
			   ps.setString(6,eq.getGrupo());
			   ps.setString(7,eq.getSerie());
			   ps.setString(8,eq.getIdEstado());
			   ps.setString(9,eq.getUsuarioReg());
			   ps.setString(10,eq.getActivo());	
			   ps.setTimestamp(11,eq.getFecCompra());	
			   ps.executeUpdate();
			   ok=1;
			   
		} catch (SQLException e) {
			e.printStackTrace();
		}
	      return ok;
	   }
	   
	   public int actualizar(Equipo eq) {
		   PreparedStatement ps;
		   ResultSet rs;
		   int ok=0;
		   java.util.Date d1 = new java.util.Date();
	       java.sql.Timestamp fechaMod = new java.sql.Timestamp(d1.getTime());
		   String sql = "UPDATE ge_equipo SET id_tipo_eq=?,id_marca=?,id_modelo=?,cod_patrimonial=?,grupo=?,serie=?,id_estado=?,usuario_mod=?,activo=?,fec_compra=?,fecha_mod=?" 
		   			+" WHERE (id_equipo=?)";		  
		   try {
			   ps = conexion.prepareStatement(sql);	   
			   ps.setString(1,eq.getIdTipEq());
			   ps.setString(2,eq.getIdMarca());
			   ps.setString(3,eq.getIdModelo());
			   ps.setString(4,eq.getCodPatrimonial());
			   ps.setString(5,eq.getGrupo());
			   ps.setString(6,eq.getSerie());
			   ps.setString(7,eq.getIdEstado());
			   ps.setString(8,eq.getUsuarioMod());
			   ps.setString(9,eq.getActivo());
			   ps.setTimestamp(10,eq.getFecCompra());
			   ps.setTimestamp(11,fechaMod);
			   ps.setString(12,eq.getIdEquipo());
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
	   
	   
	   
	   public int actualizarGrupo(Equipo eq) {
		   PreparedStatement ps;
		   ResultSet rs;
		   int ok=0;
		   String sql = "UPDATE ge_equipo SET grupo=?" 
		   			+" WHERE (id_equipo=?)";		  
		   try {
			   ps = conexion.prepareStatement(sql);	   
			   ps.setString(1,eq.getGrupo());
			   ps.setString(2,eq.getIdEquipo());
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
	   
	   public int quitarGrupo(Equipo eq) {
		   PreparedStatement ps;
		   ResultSet rs;
		   int ok=0;
		   String sql = "UPDATE ge_equipo SET grupo=?" 
		   			+" WHERE (id_equipo=?)";		  
		   try {
			   ps = conexion.prepareStatement(sql);	   
			   ps.setString(1,"0");
			   ps.setString(2,eq.getIdEquipo());
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
	   
	   public int anular(Equipo eq) {
		   PreparedStatement ps;
		   ResultSet rs;
		   int ok=0;
		   String sql = "UPDATE gu_usuario SET activo=?"  
		   			+" WHERE (id_usuario=?)";		  
		   try {
			   ps = conexion.prepareStatement(sql);	   
			   ps.setString(1,eq.getActivo());
			   ps.setString(2,eq.getIdEquipo());
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
	   
	   
	   public List<Equipo> recuperarPorGrupo(String grupo) {
		   System.out.println("Recuperando por Grupo");
		   Equipo eq=null;
		   List<Equipo> lisEq=new ArrayList<Equipo>();
		   	String sql = "SELECT ge_equipo.id_equipo,ge_equipo.grupo,tip_eq.nom_corto AS equipo,mar_eq.nom_corto AS marca,mod_eq.nom_corto AS modelo,tip_est.nom_corto AS estado,ge_equipo.cod_patrimonial,ge_equipo.serie FROM ge_equipo INNER JOIN gs_tab_items tip_eq ON (tip_eq.id_item = ge_equipo.id_tipo_eq) AND (tip_eq.id_tabla='TIPO_EQUIPO' ) INNER JOIN gs_tab_items mar_eq ON (mar_eq.id_item = ge_equipo.id_marca) AND (mar_eq.id_tabla='MARCA') INNER JOIN gs_tab_items mod_eq ON (mod_eq.id_item = ge_equipo.id_modelo) AND (mod_eq.id_tabla='MODELO') INNER JOIN gs_tab_items tip_est ON (tip_est.id_item = ge_equipo.id_estado) AND (tip_est.id_tabla='TIPO_ESTADO') WHERE (ge_equipo.activo='S') AND (ge_equipo.grupo=?) AND ge_equipo.grupo!=null AND ge_equipo.grupo!=0";
		    PreparedStatement ps;
		    ResultSet rs;	
		   // System.out.println(sql);
			try {
				ps = conexion.prepareStatement(sql);//ps.setString(1, u);	
				ps.setString(1, grupo);	
			    rs = ps.executeQuery();
			   // System.out.println(sql);
			    while(rs.next()) {// if (rs.next()){				   		    	
			    	eq=new Equipo();
			    	eq.setIdEquipo(rs.getString("id_equipo"));
			    	eq.setTipEquipo(rs.getString("equipo"));
			    	eq.setMarca(rs.getString("marca"));
			    	eq.setModelo(rs.getString("modelo"));
			    	eq.setCodPatrimonial(rs.getString("cod_patrimonial"));
			    	eq.setGrupo(rs.getString("grupo"));
			    	eq.setSerie(rs.getString("serie"));			    	
			    	eq.setEstado(rs.getString("estado"));
			    	//System.out.println(sql);
			    	lisEq.add(eq);			    	
			    }
			    rs.close();
			    ps.close();   		    
			    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
			System.out.println("se genera la lista");
	      return lisEq;
	   }
	   
	   public List<Equipo> recuperarPorIdAsignado(String idAsignado) {
		   System.out.println("Recuperando por Grupo");
		   Equipo eq=null;
		   List<Equipo> lisEq=new ArrayList<Equipo>();
		   	String sql = "SELECT ge_equipo.id_equipo,ge_equipo.grupo,tip_eq.nom_corto AS equipo,mar_eq.nom_corto AS marca,mod_eq.nom_corto AS modelo,tip_est.nom_corto AS estado,ge_equipo.cod_patrimonial,ge_equipo.serie,ge_asignado_equipo.nom_equipo,ge_asignado_equipo.dir_ip,ge_asignado_equipo.id_asignado  FROM ge_equipo INNER JOIN gs_tab_items tip_eq ON (tip_eq.id_item = ge_equipo.id_tipo_eq) AND (tip_eq.id_tabla='TIPO_EQUIPO' ) INNER JOIN gs_tab_items mar_eq ON (mar_eq.id_item = ge_equipo.id_marca) AND (mar_eq.id_tabla='MARCA') INNER JOIN gs_tab_items mod_eq ON (mod_eq.id_item = ge_equipo.id_modelo) AND (mod_eq.id_tabla='MODELO') INNER JOIN gs_tab_items tip_est ON (tip_est.id_item = ge_equipo.id_estado) AND (tip_est.id_tabla='ESTADO_EQUIPO') INNER JOIN ge_asignado_equipo ON ge_asignado_equipo.id_equipo=ge_equipo.id_equipo WHERE (ge_equipo.activo='S') AND (ge_asignado_equipo.activo='S') AND ge_asignado_equipo.id_asignado=?";
		    PreparedStatement ps;
		    ResultSet rs;	
		   // System.out.println(sql);
			try {
				ps = conexion.prepareStatement(sql);//ps.setString(1, u);	
				ps.setString(1, idAsignado);	
			    rs = ps.executeQuery();
			   // System.out.println(sql);
			    while(rs.next()) {// if (rs.next()){				   		    	
			    	eq=new Equipo();
			    	eq.setIdEquipo(rs.getString("id_equipo"));
			    	eq.setTipEquipo(rs.getString("equipo"));
			    	eq.setMarca(rs.getString("marca"));
			    	eq.setModelo(rs.getString("modelo"));
			    	eq.setCodPatrimonial(rs.getString("cod_patrimonial"));
			    	eq.setGrupo(rs.getString("grupo"));
			    	eq.setSerie(rs.getString("serie"));			    	
			    	eq.setEstado(rs.getString("estado"));
			    	eq.setNomEquipo(rs.getString("nom_equipo"));
			    	eq.setDirIp(rs.getString("dir_ip"));
			    	//System.out.println(sql);
			    	lisEq.add(eq);			    	
			    }
			    rs.close();
			    ps.close();   		    
			    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
			System.out.println("se genera la lista");
	      return lisEq;
	   }
	   
	   public List<Equipo> recuperarEquiposNoAsignados() {
		   System.out.println("Recuperando por Equipos no asignados");
		   Equipo eq=null;
		   List<Equipo> lisEq=new ArrayList<Equipo>();
		   	String sql = "SELECT ge_equipo.id_equipo,(SELECT ge_asignado_equipo.nom_equipo FROM ge_asignado_equipo WHERE ge_asignado_equipo.id_equipo=ge_equipo.id_equipo),(SELECT ge_asignado_equipo.dir_ip FROM ge_asignado_equipo WHERE ge_asignado_equipo.id_equipo=ge_equipo.id_equipo),ge_equipo.grupo,tip_eq.nom_corto AS equipo,mar_eq.nom_corto AS marca,mod_eq.nom_corto AS modelo,tip_est.nom_corto AS estado,ge_equipo.cod_patrimonial,ge_equipo.serie,(SELECT ge_asignado_equipo.id_asignado FROM ge_asignado_equipo WHERE ge_asignado_equipo.id_equipo=ge_equipo.id_equipo) AS id_asignado FROM ge_equipo INNER JOIN gs_tab_items tip_eq ON (tip_eq.id_item = ge_equipo.id_tipo_eq) AND (tip_eq.id_tabla='TIPO_EQUIPO' ) INNER JOIN gs_tab_items mar_eq ON (mar_eq.id_item = ge_equipo.id_marca) AND (mar_eq.id_tabla='MARCA') INNER JOIN gs_tab_items mod_eq ON (mod_eq.id_item = ge_equipo.id_modelo) AND (mod_eq.id_tabla='MODELO') INNER JOIN gs_tab_items tip_est ON (tip_est.id_item = ge_equipo.id_estado) AND (tip_est.id_tabla='ESTADO_EQUIPO') WHERE (ge_equipo.activo='S') AND id_asignado IS NULL";
		    PreparedStatement ps;
		    ResultSet rs;	
		   // System.out.println(sql);
			try {
				ps = conexion.prepareStatement(sql);//ps.setString(1, u);	
			    rs = ps.executeQuery();
			   // System.out.println(sql);
			    while(rs.next()) {// if (rs.next()){				   		    	
			    	eq=new Equipo();			  
			    	eq.setIdEquipo(rs.getString("id_equipo"));
			    	eq.setTipEquipo(rs.getString("equipo"));
			    	eq.setMarca(rs.getString("marca"));
			    	eq.setModelo(rs.getString("modelo"));
			    	eq.setCodPatrimonial(rs.getString("cod_patrimonial"));
			    	eq.setGrupo(rs.getString("grupo"));
			    	eq.setSerie(rs.getString("serie"));			    	
			    	eq.setEstado(rs.getString("estado"));
			    	eq.setNomEquipo(rs.getString("nom_equipo"));
			    	eq.setDirIp(rs.getString("dir_ip"));
			    	//System.out.println(sql);
			    	lisEq.add(eq);			    	
			    }
			    rs.close();
			    ps.close();   		    
			    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
			System.out.println("se genera la lista");
	      return lisEq;
	   }
	   
	   
	   public int desasignarEquipo(Equipo eq) {
		   System.out.println("Veamos q pasa aqui");
		   PreparedStatement ps;
		   ResultSet rs;
		   int ok=0;
		   String sql = "UPDATE ge_equipo SET grupo=?"  
		   			+" WHERE (id_equipo=?)";		  
		   try {
			   ps = conexion.prepareStatement(sql);	   
			   ps.setString(1,"0");
			   ps.setString(2,eq.getIdEquipo());
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
	   
	   public List<Equipo> ultimoGrupo() {
		   System.out.println("Recuperando el ultimo grupo");
		   Equipo eq=null;
		   List<Equipo> lisEq=new ArrayList<Equipo>();
		   	String sql = "SELECT ISNULL(MAX(ge_equipo.grupo),0) as ultimoGrupo FROM ge_equipo";
		    PreparedStatement ps;
		    ResultSet rs;	
		   // System.out.println(sql);
			try {
				ps = conexion.prepareStatement(sql);//ps.setString(1, u);	
			    rs = ps.executeQuery();
			   // System.out.println(sql);
			    while(rs.next()) {// if (rs.next()){				   		    	
			    	eq=new Equipo();			  
			    	eq.setUltimoGrupo(rs.getString("ultimoGrupo"));
			    	lisEq.add(eq);			    	
			    }
			    rs.close();
			    ps.close();   		    
			    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
			System.out.println("se genera la el ultimo grupo");
	      return lisEq;
	   }
	   
	   public String generarNuevaIdEquipo() {   
		   String maxID = "";
		   try {
			   PreparedStatement ps;
			   ResultSet rs;
			   String sql = "SELECT ISNULL(MAX(CONVERT(INTEGER,ge_equipo.id_equipo)),0) as idEquipo FROM ge_equipo";
			   ps = conexion.prepareStatement(sql);//ps.setString(1, u);
			   rs = ps.executeQuery();
			   while(rs.next()){
				   maxID = String.valueOf((rs.getInt("idEquipo")+1));
			   }
			   System.out.println("Este es id "+maxID);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error en la consulta");
			e.printStackTrace();
		}
	      return maxID;
	   }
	}