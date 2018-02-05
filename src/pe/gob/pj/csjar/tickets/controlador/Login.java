package pe.gob.pj.csjar.tickets.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.gob.pj.csjar.tickets.modelo.Dao;
import pe.gob.pj.csjar.tickets.modelo.UsuarioDao;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);}
		
		
		List<Usuario> usrs=null; //Se crea una lista para los usuarios
		UsuarioDao usrDao=new UsuarioDao(); //Instancia la clase "UsuarioDao" (Recuperar,Insertar,Actualizar)
		String claveUsuario;
		String idRol;
		String usuario = request.getParameter("txtUsuario");//Se extrae el valor del formulario
		String claveIngresado = request.getParameter("txtClave");
		int ok=0; //No hay conexion con la base de datos
		ServletContext ctx=request.getServletContext();
		String usuarioBD=ctx.getInitParameter("UsuarioBD");
		String claveBD=ctx.getInitParameter("ClaveBD");
		String urlBD=ctx.getInitParameter("UrlBD");
		String driverBD=ctx.getInitParameter("DriverBD");
						
		Dao dao=new Dao(); //Se instancia la clase "Dao" (Conexion)
		dao.setUsuarioBd(usuarioBD);
		dao.setClaveBd(claveBD);
		dao.setUrl(urlBD);
		dao.setDriver(driverBD);						
		ok=dao.conectar();
		Connection cnn=dao.getConexion(); //direcion del url la bd y el usuario
		usrDao.setConexion(cnn); // Pasa la direccion de la url, la bd y le usuario 
		usrs=usrDao.recuperarPorUsuario(usuario); //Llama la funcion de repuperarPor Usuario y lo asigna a la lista
		
		PrintWriter pw= response.getWriter(); // Indica que esto aparecera en consola o navegador
		if (usrs.size()==1){
			claveUsuario=usrs.get(0).getClave();
			idRol= String.valueOf(usrs.get(0).getIdRol());
			if(claveUsuario.equals(claveIngresado)){
				pw.println("Bienvenido...:"+usrs.get(0).getNombres());	
				request.getSession().setAttribute("usuario", usuario);
				request.getSession().setAttribute("idRol", idRol);
				response.sendRedirect(response.encodeURL(request.getContextPath() + "/pages/menu.jsp"));
			}else{
				pw.println("Usuario no Autorizado...");
			}	
		}else{
			pw.println("Usuario no Registrado...");
		}
		dao.desconectar();
	
	

	}

}
