package pe.gob.pj.csjar.tickets.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import pe.gob.pj.csjar.tickets.modelo.Dao;
import pe.gob.pj.csjar.tickets.modelo.EquipoDao;
import pe.gob.pj.csjar.tickets.modelo.IncidenciaDao;

/**
 * Servlet implementation class EquipoServlet
 */
@WebServlet("/Equipo.do")
public class EquipoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EquipoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accion = request.getParameter("accion");
		String idEquipo=request.getParameter("mIdEquipo");
		System.out.println("accion equipo "+accion+" parametro: "+idEquipo);
		int ok=0;
		if (accion.equals("recuperarPorIdEquipo")){
			ok=recuperarPorIdEquipo(request,response);			
		}else {if(accion.equals("recuperarPorEquipo")){
			//ok=recuperarPorEquipo(request,response);	
		}else{if(accion.equals("recuperarEquiposNoAsignados")){
			ok=recuperarEquiposNoAsignados(request,response);	
		}else{if(accion.equals("recuperarUltimoGrupo")){
			ok=recuperarUltimoGrupo(request,response);	
		}else{if(accion.equals("recuperarPorGrupo")){
			ok=recuperarPorGrupo(request,response);	
		}else{
			System.out.println("Recupera por todos los usuarios");
			ok=recuperarTodo(request,response);
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
		System.out.println("idEquipo: "+idEquipo + " accion: "+accion);
		if (idEquipo == 0 && accion.equals("grabar")){
			ok=insertar(request,response);
		}else if(idEquipo != 0 && accion.equals("grabar")){
			ok=actualizar(request,response);
		}else if(idEquipo != 0 && accion.equals("desasignarEquipo")){
			ok=desasignarEquipo(request,response);
		}else if(idEquipo != 0 && accion.equals("anular")){
			ok=anular(request,response);
		}
	}
	
	
	public int insertar(HttpServletRequest request, HttpServletResponse response) {

	 	int ok=0;
	 	String usuario = (String) request.getSession().getAttribute("usuario");
	 	String fecCompra = request.getParameter("mFecCompra");
	 	Timestamp fechaCompra = Timestamp.valueOf(fecCompra);
	 	Equipo eq=new Equipo();
	 	eq.setUsuarioReg(usuario);
	 	eq.setIdEquipo(request.getParameter("mIdEquipo"));
	 	eq.setGrupo(request.getParameter("mGrupo"));
	 	eq.setIdTipEq(request.getParameter("mTipEquipo"));
	 	eq.setIdMarca(request.getParameter("mIdMarca"));
	 	eq.setIdModelo(request.getParameter("mIdModelo"));
	 	eq.setSerie(request.getParameter("mSerie"));
	 	eq.setCodPatrimonial(request.getParameter("mPatrimonio"));
	 	eq.setFecCompra(fechaCompra);
	 	eq.setIdEstado(request.getParameter("mIdEstado"));
	 	eq.setActivo(request.getParameter("mActivo"));
		EquipoDao eqDao=new EquipoDao();
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
		ok=eqDao.insertar(eq);
		dao.desconectar();	
      return ok;
   }
	
	public int recuperarTodo(HttpServletRequest request, HttpServletResponse response) {
		  System.out.println("Vamos a recuperar todos los equipos");
		int ok=0;
		PrintWriter pw=null;
		List<Equipo> eqs=null;
		EquipoDao eqDao=new EquipoDao();
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
		eqs=eqDao.recuperarTodo();		
		
		try {
			pw = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		JSONObject json = new JSONObject();
		int rows = eqs.size();
		if (rows>0){//claveUsuario=usrs.get(0).getClave();	
			try{//Map mUsr;
				List lEqs = new ArrayList();	
				//System.out.println("Entro"+rows);
				for (int i=0 ; i<rows ; i++){//mUsr = new HashMap();			       
					lEqs.add(eqs.get(i));
				}
				json.put("equipos", lEqs);			    
				//json.put(lUsrs);
				//json.pu
			}catch (JSONException jse){
				//e1.printStackTrace();
			}
		}else{
			Map mEq;
			List lEqs = new ArrayList();			
			try {
				mEq = new HashMap();
				mEq.put("idEquipo", "" );				
				lEqs.add(mEq);				
				json.put("equipos1", lEqs);
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
	
	public int recuperarPorIdEquipo(HttpServletRequest request, HttpServletResponse response) {
		  System.out.println("Vamos a recuperar por idEquipos");
		int ok=0;
		PrintWriter pw=null;
		List<Equipo> eqs=null;
		String idEquipo = request.getParameter("mIdEquipo");
		EquipoDao eqDao=new EquipoDao();
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
		eqs=eqDao.recuperarPorIdEquipo(idEquipo);		
		
		try {
			pw = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		JSONObject json = new JSONObject();
		int rows = eqs.size();
		if (rows>0){//claveUsuario=usrs.get(0).getClave();	
			try{//Map mUsr;
				List lEqs = new ArrayList();	
				//System.out.println("Entro"+rows);
				for (int i=0 ; i<rows ; i++){//mUsr = new HashMap();			       
					lEqs.add(eqs.get(i));
				}
				json.put("equipos", lEqs);			    
				//json.put(lUsrs);
				//json.pu
			}catch (JSONException jse){
				//e1.printStackTrace();
			}
		}else{
			Map mEq;
			List lEqs = new ArrayList();			
			try {
				mEq = new HashMap();
				mEq.put("idEquipo", "" );				
				lEqs.add(mEq);				
				json.put("equipos1", lEqs);
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
	

	public int recuperarPorGrupo(HttpServletRequest request, HttpServletResponse response) {
		  System.out.println("Vamos a recuperar todos los equipos por Grupo");
		int ok=0;
		PrintWriter pw=null;
		List<Equipo> eqs=null;
		String grupo = request.getParameter("mGrupo");
		EquipoDao eqDao=new EquipoDao();
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
		eqs=eqDao.recuperarPorGrupo(grupo);		
		
		try {
			pw = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		JSONObject json = new JSONObject();
		int rows = eqs.size();
		if (rows>0){//claveUsuario=usrs.get(0).getClave();	
			try{//Map mUsr;
				List lEqs = new ArrayList();	
				//System.out.println("Entro"+rows);
				for (int i=0 ; i<rows ; i++){//mUsr = new HashMap();			       
					lEqs.add(eqs.get(i));
				}
				json.put("equipos", lEqs);			    
				//json.put(lUsrs);
				//json.pu
			}catch (JSONException jse){
				//e1.printStackTrace();
			}
		}else{
			Map mEq;
			List lEqs = new ArrayList();			
			try {
				mEq = new HashMap();
				mEq.put("idEquipo", "" );				
				lEqs.add(mEq);				
				json.put("equipos1", lEqs);
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
	
	public int recuperarEquiposNoAsignados(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Vamos a recuperar todos los equipos no asignados");
		int ok=0;
		PrintWriter pw=null;
		List<Equipo> eqs=null;
		EquipoDao eqDao=new EquipoDao();
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
		eqs=eqDao.recuperarEquiposNoAsignados();		
		
		try {
			pw = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		JSONObject json = new JSONObject();
		int rows = eqs.size();
		if (rows>0){//claveUsuario=usrs.get(0).getClave();	
			try{//Map mUsr;
				List lEqs = new ArrayList();	
				//System.out.println("Entro"+rows);
				for (int i=0 ; i<rows ; i++){//mUsr = new HashMap();			       
					lEqs.add(eqs.get(i));
				}
				json.put("equipos", lEqs);			    
				//json.put(lUsrs);
				//json.pu
			}catch (JSONException jse){
				//e1.printStackTrace();
			}
		}else{
			Map mEq;
			List lEqs = new ArrayList();			
			try {
				mEq = new HashMap();
				mEq.put("idEquipo", "" );				
				lEqs.add(mEq);				
				json.put("equipos1", lEqs);
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
	
	
	
	
	public int recuperarUltimoGrupo(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Vamos a recuperar el ultimo grupo");
		int ok=0;
		PrintWriter pw=null;
		List<Equipo> eqs=null;
		EquipoDao eqDao=new EquipoDao();
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
		eqs=eqDao.ultimoGrupo();		
		
		try {
			pw = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		JSONObject json = new JSONObject();
		int rows = eqs.size();
		if (rows>0){//claveUsuario=usrs.get(0).getClave();	
			try{//Map mUsr;
				List lEqs = new ArrayList();	
				//System.out.println("Entro"+rows);
				for (int i=0 ; i<rows ; i++){//mUsr = new HashMap();			       
					lEqs.add(eqs.get(i));
				}
				json.put("equipos", lEqs);			    
				//json.put(lUsrs);
				//json.pu
			}catch (JSONException jse){
				//e1.printStackTrace();
			}
		}else{
			Map mEq;
			List lEqs = new ArrayList();			
			try {
				mEq = new HashMap();
				mEq.put("idEquipo", "" );				
				lEqs.add(mEq);				
				json.put("equipos1", lEqs);
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
	
	
	public int desasignarEquipo(HttpServletRequest request, HttpServletResponse response) {
		int ok=0;
		System.out.println("Aqui desasignarEquipo");
	 	Equipo eq=new Equipo();
	 	eq.setIdEquipo(request.getParameter("mIdEquipo"));
	 	
	 	EquipoDao eqDao=new EquipoDao();
	 	
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
		ok=eqDao.desasignarEquipo(eq);
		dao.desconectar();	
      return ok;
	   }
	
	   
	public int actualizar(HttpServletRequest request, HttpServletResponse response) {
		int ok=0;
		System.out.println("Aqui actualiza");
	 	Equipo eq=new Equipo();
	 	String usuario = (String) request.getSession().getAttribute("usuario");
	 	String fecCompra = request.getParameter("mFecCompra");
	 	Timestamp fechaCompra = Timestamp.valueOf(fecCompra);
	 	eq.setUsuarioMod(usuario);
	 	eq.setIdEquipo(request.getParameter("mIdEquipo"));
	 	eq.setGrupo(request.getParameter("mGrupo"));
	 	eq.setIdTipEq(request.getParameter("mTipEquipo"));
	 	eq.setIdMarca(request.getParameter("mIdMarca"));
	 	eq.setIdModelo(request.getParameter("mIdModelo"));
	 	eq.setSerie(request.getParameter("mSerie"));
	 	eq.setCodPatrimonial(request.getParameter("mPatrimonio"));
	 	eq.setFecCompra(fechaCompra);
	 	eq.setIdEstado(request.getParameter("mIdEstado"));
	 	eq.setActivo(request.getParameter("mActivo"));
		EquipoDao eqDao=new EquipoDao();
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
		ok=eqDao.actualizar(eq);
		dao.desconectar();
		
      return ok;
	   }
	
	
	public int anular(HttpServletRequest request, HttpServletResponse response) {
		int ok=0;
		String usuario = (String) request.getSession().getAttribute("usuario");
	 	Incidencia inc=new Incidencia();
	 	inc.setIdIncidencia(request.getParameter("mIdEquipo"));
	 	inc.setMotivoAnul(request.getParameter("mMotivoAnular"));
	 	inc.setUsuarioAnul(usuario);
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
		ok=incDao.anular(inc);
		
		dao.desconectar();	
      return ok;
	   }
	
}
