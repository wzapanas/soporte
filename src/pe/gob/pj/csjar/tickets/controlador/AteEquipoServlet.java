package pe.gob.pj.csjar.tickets.controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.gob.pj.csjar.tickets.modelo.Dao;
import pe.gob.pj.csjar.tickets.modelo.IncidenciaDao;

/**
 * Servlet implementation class AteEquipoServlet
 */
@WebServlet("/AteEquipo.do")
public class AteEquipoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AteEquipoServlet() {
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
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		Integer ok=0;
		String accion=request.getParameter("accion");
		
		//String idIncidencia= request.getParameter("mIdIncidencia");
		int idIncidencia= Integer.parseInt(request.getParameter("mIdIncidencia"));
		System.out.println("idInci: "+idIncidencia + "accion: "+accion);

		
		if (idIncidencia == 0 && accion.equals("grabar")){
			ok=insertar(request,response);
		}else if(idIncidencia != 0 && accion.equals("grabar")){
			//ok=actualizar(request,response);
		}else if(idIncidencia != 0 && accion.equals("anular")){
			//ok=anular(request,response);
		}
	}
	
	
	public int insertar(HttpServletRequest request, HttpServletResponse response) {

	 	int ok=0;
	 	String usuario = (String) request.getSession().getAttribute("usuario");
	 	String fechaHora = request.getParameter("mFecha");
	 	System.out.println("qqqqq"+fechaHora);
	 	Timestamp fechaIng = Timestamp.valueOf(fechaHora);
	 	
	 	System.out.println("qqqqq"+fechaHora);
	 	
	 	Incidencia inc=new Incidencia();
	 	inc.setUsuarioReg(usuario);
	 	inc.setIdIncidencia(request.getParameter("mIdIncidencia"));
	 	inc.setNumIncidencia(Integer.parseInt(request.getParameter("mNumIncidencia")));
	 	inc.setFechaIng(fechaIng);
	 	inc.setIdTipIng(request.getParameter("mIdTipIng"));
	 	inc.setIdDependencia(Integer.parseInt(request.getParameter("mIdDependencia")));
	 	inc.setIdUbicacion(Integer.parseInt(request.getParameter("mIdUbicacion")));
	 	inc.setIdSolicitante(Integer.parseInt(request.getParameter("mIdSolicitante")));
	 	inc.setDetIncidencia(request.getParameter("mDetIncidencia"));
	 	inc.setIdUsuario(Integer.parseInt(request.getParameter("mIdUsuario")));							
		inc.setActivo(request.getParameter("mActivo"));				
		
		IncidenciaDao incDao=new IncidenciaDao();
		
		ServletContext ctx=request.getServletContext();
		String usuarioBD=ctx.getInitParameter("UsuarioBD");
		String claveBD=ctx.getInitParameter("ClaveBD");
		String urlBD=ctx.getInitParameter("UrlBD");
		String driverBD=ctx.getInitParameter("DriverBD");
						
		Dao dao=new Dao();
		dao.setUsuarioBd(usuarioBD);
		dao.setClaveBd(claveBD);
		dao.setUrl(urlBD);
		dao.setDriver(driverBD);						
		ok=dao.conectar();
		Connection cnn=dao.getConexion();
		incDao.setConexion(cnn);
		ok=incDao.insertar(inc);
		
		//System.out.println("usuario:"+usr.getUsuario()+" apepat:"+usr.getApeMaterno()+"accion:"+accion);
		dao.desconectar();	
      return ok;
   }
	
	

}
