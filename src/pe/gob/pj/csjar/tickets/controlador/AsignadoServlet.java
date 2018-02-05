package pe.gob.pj.csjar.tickets.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import pe.gob.pj.csjar.tickets.modelo.AsignadoDao;
import pe.gob.pj.csjar.tickets.modelo.AsignadoEquipoDao;
import pe.gob.pj.csjar.tickets.modelo.Dao;
import pe.gob.pj.csjar.tickets.modelo.EquipoDao;

/**
 * Servlet implementation class AsignadoServlet
 */
@WebServlet("/Asignado.do")
public class AsignadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List lista = new ArrayList();
	List listEq = new ArrayList();
	List listAux = new ArrayList();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsignadoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		System.out.println("accion equipo "+accion);
		int ok=0;
		if (accion.equals("recuperarPorIdAsignado")){
			System.out.println("recuperarPorIdAsignado");
			ok=recuperarPorIdAsignado(request,response);			
		}else {if(accion.equals("recuperarAsignacion")){
			System.out.println("recuperarAsignacion");
			ok=recuperarAsignacion(request,response);	
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
		Integer ok=0;
		String accion=request.getParameter("accion");
		//String idIncidencia= request.getParameter("mIdIncidencia");
		int idAsignado= Integer.parseInt(request.getParameter("mIdAsignado"));
		System.out.println("idAsig: "+idAsignado + "accion: "+accion);
		if (idAsignado == 0 && accion.equals("grabar")){
			ok=insertar(request,response);
		}else if(idAsignado != 0 && accion.equals("grabar")){
			//ok=actualizar(request,response);
		}else if(idAsignado != 0 && accion.equals("anular")){
			//ok=anular(request,response);
		}
	}		
	
	public int recuperarPorIdAsignado(HttpServletRequest request, HttpServletResponse response) {
		int ok=0;
		PrintWriter pw=null;
		System.out.println("Recuperando por Id Asignacion");
		List<Equipo> eq=null;
		String idAsignado=request.getParameter("IdAsignado");
		System.out.println(" parametro: "+idAsignado);
		EquipoDao EqDao=new EquipoDao();		
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
		EqDao.setConexion(cnn);
		eq=EqDao.recuperarPorIdAsignado(idAsignado);
		
		try {
			pw = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JSONObject json = new JSONObject();
		int rows = eq.size();
		if (rows>0){//claveUsuario=usrs.get(0).getClave();	
			try{//Map mUsr;
						
			    List lEq = new ArrayList();	
			    System.out.println("Entro"+rows);
			    for (int i=0 ; i<rows ; i++){//mUsr = new HashMap();			       
			    	lEq.add(eq.get(i));
			    }
			    System.out.println("Que paso");
			    json.put("equipos", lEq);			    
			    //json.put(lUsrs);
			    //json.pu
			}
			catch (JSONException jse)
			{
		
			}
		}else{
			Map mEq;
			List lEq = new ArrayList();			
			try {
				mEq = new HashMap();
				mEq.put("idEquipo", "" );				
				lEq.add(mEq);				
				json.put("Equipos1", lEq);
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
	
	public int recuperarAsignacion(HttpServletRequest request, HttpServletResponse response) {
		int ok=0;
		PrintWriter pw=null;
		System.out.println("Recuperando por Id Asignacion");
		List<Asignado> Asig=null;
		String idAsignado=request.getParameter("IdAsignado");
		System.out.println(" parametro: "+idAsignado);
		AsignadoDao AsigDao=new AsignadoDao();		
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
		AsigDao.setConexion(cnn);
		Asig=AsigDao.recuperarAsignacion(idAsignado);
		
		try {
			pw = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JSONObject json = new JSONObject();
		int rows = Asig.size();
		if (rows>0){//claveUsuario=usrs.get(0).getClave();	
			try{//Map mUsr;
						
			    List lAsig = new ArrayList();	
			    System.out.println("Entro"+rows);
			    for (int i=0 ; i<rows ; i++){//mUsr = new HashMap();			       
			    	lAsig.add(Asig.get(i));
			    }
			    System.out.println("Que paso");
			    json.put("asignados", lAsig);			    
			    //json.put(lUsrs);
			    //json.pu
			}
			catch (JSONException jse)
			{
		
			}
		}else{
			Map mAsig;
			List lAsig = new ArrayList();			
			try {
				mAsig = new HashMap();
				mAsig.put("idAsignado", "" );				
				lAsig.add(mAsig);				
				json.put("asignados1", lAsig);
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
	
	public int insertar(HttpServletRequest request, HttpServletResponse response) {
	 	int ok=0;
	 	String usuario = (String) request.getSession().getAttribute("usuario");
	 	String fecAsig = request.getParameter("mFecAsig");
	 	Timestamp fechaAsig = Timestamp.valueOf(fecAsig);
	 	Asignado asig=new Asignado();
		AsignadoDao asigDao=new AsignadoDao();
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
		asigDao.setConexion(cnn);
	 	asig.setUsuarioReg(usuario);
	 	String idAsignado = asigDao.generarNuevaIdAsignado();
	 	asig.setIdAsignado(idAsignado);
	 	asig.setIdSolicitante(Integer.parseInt(request.getParameter("mIdSolicitante")));
	 	asig.setIdDependencia(Integer.parseInt(request.getParameter("mIdDependencia")));
	 	asig.setIdCargo(request.getParameter("mIdCargo"));
	 	asig.setFecAsigna(fechaAsig);
	 	asig.setPiso(request.getParameter("mPiso"));
	 	asig.setNroOficina(request.getParameter("mNroOficina"));
	 	asig.setObservacion(request.getParameter("mObservacion"));
	 	asig.setActivo(request.getParameter("mActivo"));
	 	ok=asigDao.insertar(asig);
	 	
	 	String grupoEq = request.getParameter("mGrupo");
	 	AsignadoEquipo asigEq=new AsignadoEquipo();
	 	AsignadoEquipoDao asigEqDao=new AsignadoEquipoDao();
		asigEqDao.setConexion(cnn);
	 	
	 	String JSONEquipos=request.getParameter("equipos");
	 	System.out.println(JSONEquipos);
        JSONArray jsonarray;
        
        Equipo eq = new Equipo();
        EquipoDao eqDao = new EquipoDao();
        eqDao.setConexion(cnn);
		try {
			jsonarray = new JSONArray(JSONEquipos);
			JSONObject objeto = null;
			String nomEq,dirIp;
			
			for (int i = 0; i < jsonarray.length(); i++) {
				objeto= jsonarray.getJSONObject(i);
				asigEq.setIdAsignado(idAsignado);
				asigEq.setIdEquipo(objeto.getString("idEquipo"));
				asigEq.setGrupo(Integer.parseInt(grupoEq));
				nomEq = objeto.getString("nomEquipo");
				if(nomEq=="-") nomEq="";
				asigEq.setNomEquipo(nomEq);
				dirIp = objeto.getString("dirIp");
				if(dirIp=="-") dirIp="";
				asigEq.setDirIp(dirIp);
				//Actualizar grupo de Equipos
				eq.setIdEquipo(objeto.getString("idEquipo"));
				eq.setGrupo(grupoEq);
				//System.out.println("idAsignado "+idAsignado+" idEquipo "+idEquipo+" grupo"+grupo+" nomEquipo "+nomEquipo+" dirIp "+dirIp);
				ok=asigEqDao.insertar(asigEq);
				ok=eqDao.actualizarGrupo(eq);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	 	dao.desconectar();
      return ok;
   }
}
