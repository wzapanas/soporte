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
import pe.gob.pj.csjar.tickets.modelo.DependenciaDao;

/**
 * Servlet implementation class DependenciaServlet
 */
@WebServlet("/Dependencia.do")
public class DependenciaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DependenciaServlet() {
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
		String idDependencia=request.getParameter("IdDependencia");
		System.out.println("accion rol "+accion+" id dependencia: "+idDependencia);
		int ok=0;
		
		if (Objects.isNull(accion)){
			accion="";
		}		
		if ( accion.equals("recuperarPorIdDependencia")){
		//	ok=recuperarPorIdRol(request,response);			
		}else if (accion.equals("recuperarUbicacion")){
			ok=recuperarUbicacion(request,response);
		}else if (accion.equals("recuperarDepAdm")){
			ok=recuperarDepAdm(request,response);
		}else if (accion.equals("recuperarDepJuric")){
			ok=recuperarDepJuric(request,response);
		}else{
				ok=recuperarDependencia(request,response);
			}
		}
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	 public int recuperarTodo(HttpServletRequest request, HttpServletResponse response) {
		int ok=0;
		PrintWriter pw=null;
		List<Dependencia> deps=null;
		DependenciaDao depDao=new DependenciaDao();
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
		depDao.setConexion(cnn);
		deps=depDao.recuperarTodo();		
			
		try {
			pw = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		JSONObject json = new JSONObject();
		int rows = deps.size(); 
		if (rows>0){//claveUsuario=usrs.get(0).getClave();	
			try{//Map mUsr;
				List lDeps = new ArrayList();	
					//System.out.println("Entro"+rows);
					for (int i=0 ; i<rows ; i++){//mUsr = new HashMap();			       
						lDeps.add(deps.get(i));
					}
					json.put("deps", lDeps);			    
					
				}catch (JSONException jse){
					//e1.printStackTrace();
				}
			}else{
				Map mDep;
				List lDeps = new ArrayList();			
				try {
					mDep = new HashMap();
					mDep.put("idDependencia", "" );				
					lDeps.add(mDep);				
					json.put("deps1", lDeps);
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
	
	 
	 
	 public int recuperarDependencia(HttpServletRequest request, HttpServletResponse response) {
			int ok=0;
			PrintWriter pw=null;
			List<Dependencia> deps=null;
			DependenciaDao depDao=new DependenciaDao();
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
			depDao.setConexion(cnn);
			deps=depDao.recuperarDependencia();		
				
			try {
				pw = response.getWriter();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
			JSONObject json = new JSONObject();
			int rows = deps.size(); 
			if (rows>0){//claveUsuario=usrs.get(0).getClave();	
				try{//Map mUsr;
					List lDeps = new ArrayList();	
						//System.out.println("Entro"+rows);
						for (int i=0 ; i<rows ; i++){//mUsr = new HashMap();			       
							lDeps.add(deps.get(i));
						}
						json.put("deps", lDeps);			    
						
					}catch (JSONException jse){
						//e1.printStackTrace();
					}
				}else{
					Map mDep;
					List lDeps = new ArrayList();			
					try {
						mDep = new HashMap();
						mDep.put("idDependencia", "" );				
						lDeps.add(mDep);				
						json.put("deps1", lDeps);
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
	 
	 public int recuperarDepAdm(HttpServletRequest request, HttpServletResponse response) {
			int ok=0;
			PrintWriter pw=null;
			List<Dependencia> deps=null;
			DependenciaDao depDao=new DependenciaDao();
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
			depDao.setConexion(cnn);
			deps=depDao.recuperarDepAdm();		
				
			try {
				pw = response.getWriter();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
			JSONObject json = new JSONObject();
			int rows = deps.size(); 
			if (rows>0){//claveUsuario=usrs.get(0).getClave();	
				try{//Map mUsr;
					List lDeps = new ArrayList();	
						//System.out.println("Entro"+rows);
						for (int i=0 ; i<rows ; i++){//mUsr = new HashMap();			       
							lDeps.add(deps.get(i));
						}
						json.put("deps", lDeps);			    
						
					}catch (JSONException jse){
						//e1.printStackTrace();
					}
				}else{
					Map mDep;
					List lDeps = new ArrayList();			
					try {
						mDep = new HashMap();
						mDep.put("idDependencia", "" );				
						lDeps.add(mDep);				
						json.put("deps1", lDeps);
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
	 
	 public int recuperarDepJuric(HttpServletRequest request, HttpServletResponse response) {
			int ok=0;
			PrintWriter pw=null;
			List<Dependencia> deps=null;
			DependenciaDao depDao=new DependenciaDao();
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
			depDao.setConexion(cnn);
			deps=depDao.recuperarDepJuric();		
				
			try {
				pw = response.getWriter();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
			JSONObject json = new JSONObject();
			int rows = deps.size(); 
			if (rows>0){//claveUsuario=usrs.get(0).getClave();	
				try{//Map mUsr;
					List lDeps = new ArrayList();	
						//System.out.println("Entro"+rows);
						for (int i=0 ; i<rows ; i++){//mUsr = new HashMap();			       
							lDeps.add(deps.get(i));
						}
						json.put("deps", lDeps);			    
						
					}catch (JSONException jse){
						//e1.printStackTrace();
					}
				}else{
					Map mDep;
					List lDeps = new ArrayList();			
					try {
						mDep = new HashMap();
						mDep.put("idDependencia", "" );				
						lDeps.add(mDep);				
						json.put("deps1", lDeps);
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
	 
	 
	 public int recuperarUbicacion(HttpServletRequest request, HttpServletResponse response) {
			int ok=0;
			PrintWriter pw=null;
			List<Dependencia> deps=null;
			DependenciaDao depDao=new DependenciaDao();
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
			depDao.setConexion(cnn);
			deps=depDao.recuperarUbicacion();		
				
			try {
				pw = response.getWriter();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
			JSONObject json = new JSONObject();
			int rows = deps.size(); 
			if (rows>0){//claveUsuario=usrs.get(0).getClave();	
				try{//Map mUsr;
					List lDeps = new ArrayList();	
						//System.out.println("Entro"+rows);
						for (int i=0 ; i<rows ; i++){//mUsr = new HashMap();			       
							lDeps.add(deps.get(i));
						}
						json.put("deps", lDeps);			    
						
					}catch (JSONException jse){
						//e1.printStackTrace();
					}
				}else{
					Map mDep;
					List lDeps = new ArrayList();			
					try {
						mDep = new HashMap();
						mDep.put("idDependencia", "" );				
						lDeps.add(mDep);				
						json.put("deps1", lDeps);
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
		

}