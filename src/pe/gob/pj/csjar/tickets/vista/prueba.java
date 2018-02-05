package pe.gob.pj.csjar.tickets.vista;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import pe.gob.pj.csjar.tickets.controlador.Usuario;
import pe.gob.pj.csjar.tickets.modelo.Dao;
import pe.gob.pj.csjar.tickets.modelo.UsuarioDao;

//no fun
public class prueba {

	public static void main(String[] args) {
		int ok=0;
		Dao dao=new Dao();		
		ok=dao.conectar();
		Connection cnn=dao.getConexion();
		
		List<Usuario> usrs;
		UsuarioDao usrDao=new UsuarioDao();
		usrDao.setConexion(cnn);
		usrs=usrDao.recuperarPorUsuario("ADMIN");
		
		System.out.println("Apellidos y Nombres: "+usrs.get(0).getApePaterno());
			
		
		try {
			int aByte = System.in.read();
			byte [] buffer = new byte[10];
			System.in.read(buffer);
			dao.desconectar();
			System.in.read(buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
