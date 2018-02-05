package pe.gob.pj.csjar.tickets.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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

import pe.gob.pj.csjar.tickets.modelo.Dao;
import pe.gob.pj.csjar.tickets.modelo.IncidenciaDao;
import pe.gob.pj.csjar.tickets.modelo.SolicitanteDao;
import pe.gob.pj.csjar.tickets.modelo.UsuarioDao;

/**
 * Servlet implementation class SolicitanteServlet
 */
@WebServlet("/Solicitante.do")
public class SolicitanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SolicitanteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String accion = request.getParameter("accion");
		String idSolicitante=request.getParameter("IdSolicitante");
		System.out.println("accion solicitante "+accion+" id solicitante: "+idSolicitante);
		System.out.println("Entro");
		int ok=0;
		
		if (Objects.isNull(accion)){
			accion="";
		}		
		if ( accion.equals("recuperarPorIdSolicitante")){
			ok=recuperarPorIdSolicitante(request,response);			
		}else{
			ok=recuperarTodo(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		Integer ok=0;
		String accion = request.getParameter("accion");
		Integer idSoli=Integer.parseInt(request.getParameter("sIdSol"));
		System.out.println("ID SOLICITANTE: "+idSoli);
		System.out.println("solo: "+idSoli);
		if (idSoli == 0 && accion.equals("grabar")){
			System.out.println("Esta Grabando");
			ok=insertar(request,response);
		}else if(idSoli != 0 && accion.equals("grabar")){
			//ok=actualizar(request,response);
			System.out.println("Esta Grabando");
			ok=actualizar(request,response);
		}else if(idSoli != 0 && accion.equals("anular")){
			ok=anular(request,response);
		}
	}
	
	 public int recuperarTodo(HttpServletRequest request, HttpServletResponse response) {
		int ok=0;
		PrintWriter pw=null;
		List<Solicitante> sols=null;
		SolicitanteDao solDao=new SolicitanteDao();
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
		solDao.setConexion(cnn);
		sols=solDao.recuperarTodo();		
			
		try {
			pw = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		JSONObject json = new JSONObject();
		int rows = sols.size();
		if (rows>0){//claveUsuario=usrs.get(0).getClave();	
			try{//Map mUsr;
				List lSols = new ArrayList();	
					//System.out.println("Entro"+rows);
					for (int i=0 ; i<rows ; i++){//mUsr = new HashMap();			       
						lSols.add(sols.get(i));
					}
					json.put("sols", lSols);			    
					
				}catch (JSONException jse){
					//e1.printStackTrace();
				}
			}else{
				Map mSol;
				List lSols = new ArrayList();			
				try {
					mSol = new HashMap();
					mSol.put("idSolicitante", "" );				
					lSols.add(mSol);				
					json.put("sols1", lSols);
				} catch (JSONException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//pw.println("No hay ressultados...");
			}
			dao.desconectar();		
			pw.println(json);
			//System.out.println("Entro GET ok");

			return ok;
	   }
	 
	 public int insertar(HttpServletRequest request, HttpServletResponse response) {
		 	int ok=0;
		 	Solicitante soli=new Solicitante();
		 	soli.setIdSolicitante(Integer.parseInt(request.getParameter("sIdSol")));	
		 	System.out.print(request.getParameter("sIdSol"));
		 	soli.setApePaterno(request.getParameter("sApePaterno"));
		 	System.out.print(request.getParameter("sApePaterno"));
		 	soli.setApeMaterno(request.getParameter("sApeMaterno"));
		 	System.out.print(request.getParameter("sApeMaterno"));
		 	soli.setNombres(request.getParameter("sNombres"));	
		 	System.out.print(request.getParameter("sNombres"));
		 	soli.setIdDependencia(Integer.parseInt(request.getParameter("sIdDependencia")));	
		 	System.out.print(request.getParameter("sIdDependencia"));
		 	soli.setActivo(request.getParameter("sActivo"));		
		 	System.out.print(request.getParameter("sActivo"));
			String accion = request.getParameter("accion");
			System.out.print(accion);
			
			SolicitanteDao solDao=new SolicitanteDao();
			
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
			solDao.setConexion(cnn);
			ok=solDao.insertar(soli);
			dao.desconectar();	
	      return ok;
	   }
	
	 
	 
	 public int recuperarPorIdSolicitante(HttpServletRequest request, HttpServletResponse response) {
			int ok=0;
			PrintWriter pw=null;
			System.out.println("Buscando por Id SOLICITANTE");
			List<Solicitante> sol=null;
			String idSolicitante=request.getParameter("IdSolicitante");
			//usr.setIdUsuario(Integer.parseInt(request.getParameter("mIdUsuario")));
			System.out.println(" parametro: "+idSolicitante);
			SolicitanteDao IncDao=new SolicitanteDao();		
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
			IncDao.setConexion(cnn);
			sol=IncDao.recuperarPorIdSolicitante(idSolicitante);
			
			try {
				pw = response.getWriter();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JSONObject json = new JSONObject();
			int rows = sol.size();
			if (rows>0){//claveUsuario=usrs.get(0).getClave();	
				try{//Map mUsr;
							
				    List lInci = new ArrayList();	
				    System.out.println("Entro"+rows);
				    for (int i=0 ; i<rows ; i++){//mUsr = new HashMap();			       
				    	lInci.add(sol.get(i));
				    }
				    System.out.println("Que paso");
				    json.put("solicitantes", lInci);			    
				    //json.put(lUsrs);
				    //json.pu
				}
				catch (JSONException jse)
				{
			
				}
			}else{
				Map mInc;
				List lInci = new ArrayList();			
				try {
					mInc = new HashMap();
					mInc.put("idSolicitante", "" );				
					lInci.add(mInc);				
					json.put("solicitantes1", lInci);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//pw.println("No hay ressultados...");
			}
			System.out.println("Este es el estado "+json);
			dao.desconectar();		
			pw.println(json);
			//System.out.println("Entro GET ok");
		      
	      return ok;
		   }
	 
	 
	 public int actualizar(HttpServletRequest request, HttpServletResponse response) {
		 int ok=0;
		 	Solicitante soli=new Solicitante();
		 	soli.setIdSolicitante(Integer.parseInt(request.getParameter("sIdSol")));	
		 	soli.setApePaterno(request.getParameter("sApePaterno"));
		 	soli.setApeMaterno(request.getParameter("sApeMaterno"));
		 	soli.setNombres(request.getParameter("sNombres"));	
		 	soli.setIdDependencia(Integer.parseInt(request.getParameter("sIdDependencia")));	
		 	soli.setActivo(request.getParameter("sActivo"));		
			String accion = request.getParameter("accion");
			System.out.print(accion);
			SolicitanteDao solDao=new SolicitanteDao();
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
			solDao.setConexion(cnn);
			ok=solDao.actualizar(soli);
			dao.desconectar();	
	      return ok;
	 }
	 
	 
	 public int anular(HttpServletRequest request, HttpServletResponse response) {
			int ok=0;
			Solicitante sol=new Solicitante();
			sol.setIdSolicitante(Integer.parseInt(request.getParameter("sIdSol")));
			sol.setActivo(request.getParameter("anulActivo"));						
			SolicitanteDao SolDao=new SolicitanteDao();
			
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
			SolDao.setConexion(cnn);
			ok=SolDao.anular(sol);
			
			dao.desconectar();	
	      return ok;
		   }
		

}
