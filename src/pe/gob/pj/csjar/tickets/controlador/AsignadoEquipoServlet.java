package pe.gob.pj.csjar.tickets.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import pe.gob.pj.csjar.tickets.modelo.AsignadoEquipoDao;
import pe.gob.pj.csjar.tickets.modelo.Dao;
import pe.gob.pj.csjar.tickets.modelo.EquipoDao;
import pe.gob.pj.csjar.tickets.modelo.IncidenciaDao;

/**
 * Servlet implementation class AsignadoEquipoServlet
 */
@WebServlet("/AsignadoEquipo.do")
public class AsignadoEquipoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsignadoEquipoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		System.out.println("accion equipo "+accion);
		int ok=0;
		if (accion.equals("recuperarPorIdEquipo")){
			System.out.println("recuperarPorIdEquipo");
			ok=recuperarPorIdEquipo(request,response);			
		}else {if(accion.equals("recuperarAsignacion")){
			System.out.println("recuperarAsignacion");
			//ok=recuperarAsignacion(request,response);	
		}else{if(accion.equals("recuperarEquiposNoAsignados")){
			//ok=recuperarEquiposNoAsignados(request,response);	
		}else{if(accion.equals("recuperarUltimoGrupo")){
			//ok=recuperarUltimoGrupo(request,response);	
		}else{if(accion.equals("recuperarPorGrupo")){
			//ok=recuperarPorGrupo(request,response);	
		}else{
			System.out.println("Recupera por todos los usuarios");
			//ok=recuperarTodo(request,response);
			}
		}
		}
		}
		}
	}		


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer ok=0;
		String accion=request.getParameter("accion");
		int idEquipo= Integer.parseInt(request.getParameter("mIdEquipo"));
		System.out.println("idInci: "+idEquipo + "accion: "+accion);

		
		if (idEquipo == 0 && accion.equals("grabar")){
			//ok=insertar(request,response);
		}else if(idEquipo != 0 && accion.equals("grabar")){
			//ok=actualizar(request,response);
		}else if(idEquipo != 0 && accion.equals("asignarEquipo")){
			ok=asignarEquipo(request,response);
		}else if(idEquipo != 0 && accion.equals("desasignarEquipo")){
			ok=desasignarEquipo(request,response);
		}else if(idEquipo != 0 && accion.equals("anular")){
			//ok=anular(request,response);
		}
	}
	
	public int recuperarPorIdEquipo(HttpServletRequest request, HttpServletResponse response) {
		int ok=0;
		PrintWriter pw=null;
		System.out.println("Recuperando por Id Equipo");
		List<AsignadoEquipo> asigEq=null;
		String idEquipo=request.getParameter("mIdEquipo");
		System.out.println(" parametro: "+idEquipo);
		AsignadoEquipoDao AsigEqDao=new AsignadoEquipoDao();		
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
		AsigEqDao.setConexion(cnn);
		asigEq=AsigEqDao.recuperarPorIdEquipo(idEquipo);
		
		try {
			pw = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JSONObject json = new JSONObject();
		int rows = asigEq.size();
		if (rows>0){//claveUsuario=usrs.get(0).getClave();	
			try{//Map mUsr;
						
			    List lAsigEq = new ArrayList();	
			    System.out.println("Entro"+rows);
			    for (int i=0 ; i<rows ; i++){//mUsr = new HashMap();			       
			    	lAsigEq.add(asigEq.get(i));
			    }
			    System.out.println("Que paso");
			    json.put("asigEqs", lAsigEq);			    
			    //json.put(lUsrs);
			    //json.pu
			}
			catch (JSONException jse)
			{
		
			}
		}else{
			Map mEq;
			List lAsigEq = new ArrayList();			
			try {
				mEq = new HashMap();
				mEq.put("idEquipo", "" );				
				lAsigEq.add(mEq);				
				json.put("asigEqs1", lAsigEq);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//pw.println("No hay ressultados...");
		}
		System.out.println("Recuperado "+json);
		dao.desconectar();		
		pw.println(json);
		//System.out.println("Entro GET ok");
	      
      return ok;
	   }	
	
	public int asignarEquipo(HttpServletRequest request, HttpServletResponse response) {
		int ok=0;
		System.out.println("Aqui asignarEquipo");
	 	AsignadoEquipo asigEq=new AsignadoEquipo();
	 	Equipo eq=new Equipo();
	 	String grupo = request.getParameter("mGrupo");
	 	String idAsignado = request.getParameter("mIdAsignado");
	 	System.out.println("Este es es el idAsigando "+idAsignado);
	 	String idEquipo = request.getParameter("mEquipo");
	 	
	 	eq.setIdEquipo(request.getParameter("mIdEquipo"));
	 	asigEq.setIdEquipo(request.getParameter("mIdEquipo"));
	 	asigEq.setIdAsignado(request.getParameter("mIdAsignado"));
	 	eq.setGrupo(request.getParameter("mGrupo"));
	 	asigEq.setGrupo(Integer.parseInt(request.getParameter("mGrupo")));
		EquipoDao eqDao=new EquipoDao();
		AsignadoEquipoDao asigEqDao=new AsignadoEquipoDao();
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
		eqDao.setConexion(cnn);	
		asigEqDao.setConexion(cnn);	
		ok=eqDao.actualizarGrupo(eq);
		ok=asigEqDao.insertar(asigEq);
		dao.desconectar();
		
      return ok;
	   }
	
	public int desasignarEquipo(HttpServletRequest request, HttpServletResponse response) {
		int ok=0;
		System.out.println("Aqui des desasignarEquipo");
	 	AsignadoEquipo asigEq=new AsignadoEquipo();
	 	Equipo eq=new Equipo();
	 	eq.setIdEquipo(request.getParameter("mIdEquipo"));
	 	asigEq.setIdEquipo(request.getParameter("mIdEquipo"));
		EquipoDao eqDao=new EquipoDao();
		AsignadoEquipoDao asigEqDao=new AsignadoEquipoDao();
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
		eqDao.setConexion(cnn);	
		asigEqDao.setConexion(cnn);	
		ok=eqDao.quitarGrupo(eq);
		ok=asigEqDao.borrarAsignadoEquipo(asigEq);
		dao.desconectar();
      return ok;
	   }
}
