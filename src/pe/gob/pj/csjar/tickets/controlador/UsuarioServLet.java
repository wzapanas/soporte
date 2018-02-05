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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import pe.gob.pj.csjar.tickets.modelo.Dao;
import pe.gob.pj.csjar.tickets.modelo.UsuarioDao;

/**
 * Servlet implementation class UsuarioServLet
 */
public class UsuarioServLet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioServLet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String accion = request.getParameter("accion");
		String idUsuario=request.getParameter("IdUsuario");
		System.out.println("accion usuario "+accion+" parametro: "+idUsuario);
		int ok=0;
		
		if (Objects.isNull(accion)){
			accion="";
		}
		
		
		if (accion.equals("recuperarPorIdUsuario")){
			ok=recuperarPorIdUsuario(request,response);			
		}else {if(accion.equals("recuperarPorUser")){
			ok=recuperarPorUser(request,response);	
		}else{if(accion.equals("recuperarIdRol")){
			ok=recuperarIdRol(request,response);	
		}else{
			System.out.println("Recupera por todos los usuarios");
			ok=recuperarTodo(request,response);
			
		}
		}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		Integer ok=0;
		String accion = request.getParameter("accion");
		Integer idUsuario=Integer.parseInt(request.getParameter("mIdUsuario"));
		System.out.println("idUsu: "+idUsuario);
		
		
		if (idUsuario == 0 && accion.equals("grabar")){
			ok=insertar(request,response);
		}else if(idUsuario != 0 && accion.equals("grabar")){
			ok=actualizar(request,response);
		}else if(idUsuario != 0 && accion.equals("anular")){
			ok=anular(request,response);
		}
		
		
	}
	public int actualizar(HttpServletRequest request, HttpServletResponse response) {
		int ok=0;
		System.out.println("Aqui estas");
	 	Usuario usr=new Usuario();
		usr.setIdUsuario(Integer.parseInt(request.getParameter("mIdUsuario")));
		usr.setUsuario(request.getParameter("mUsuario"));		
		usr.setApePaterno(request.getParameter("mApePaterno"));
		usr.setApeMaterno(request.getParameter("mApeMaterno"));
		usr.setNombres(request.getParameter("mNombres"));
		usr.setDni(request.getParameter("mDni"));
		usr.setEmail(request.getParameter("mEmail"));
		usr.setIdRol(Integer.parseInt(request.getParameter("mIdRol")));				
		usr.setIdDependencia(Integer.parseInt(request.getParameter("mIdDependencia")));				
		usr.setActivo(request.getParameter("mActivo"));	
		usr.setClave(request.getParameter("mtxtClave"));			
		String accion = request.getParameter("accion");
		
		UsuarioDao usrDao=new UsuarioDao();
		
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
		usrDao.setConexion(cnn);
		ok=usrDao.actualizar(usr);
		
		//System.out.println("usuario:"+usr.getUsuario()+" apepat:"+usr.getApeMaterno()+"accion:"+accion);
		dao.desconectar();	
      return ok;
	   }
	   /** @param usr
	    * @pdOid 304cdbc4-5cbf-4f03-bd03-a3bb83cb6e00 */
	
	 public int insertar(HttpServletRequest request, HttpServletResponse response) {
		 	int ok=0;
		 	Usuario usr=new Usuario();
			usr.setIdUsuario(Integer.parseInt(request.getParameter("mIdUsuario")));
			usr.setUsuario(request.getParameter("mUsuario"));		
			usr.setApePaterno(request.getParameter("mApePaterno"));
			usr.setApeMaterno(request.getParameter("mApeMaterno"));
			usr.setNombres(request.getParameter("mNombres"));
			usr.setDni(request.getParameter("mDni"));
			usr.setEmail(request.getParameter("mEmail"));
			usr.setIdRol(Integer.parseInt(request.getParameter("mIdRol")));				
			usr.setIdDependencia(Integer.parseInt(request.getParameter("mIdDependencia")));				
			usr.setActivo(request.getParameter("mActivo"));	
			usr.setClave(request.getParameter("mtxtClave"));			
			String accion = request.getParameter("accion");
			
			UsuarioDao usrDao=new UsuarioDao();
			
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
			usrDao.setConexion(cnn);
			ok=usrDao.insertar(usr);
			
			//System.out.println("usuario:"+usr.getUsuario()+" apepat:"+usr.getApeMaterno()+"accion:"+accion);
			dao.desconectar();	
	      return ok;
	   }
	 
	 
	 
	 
	 public int anular(HttpServletRequest request, HttpServletResponse response) {
			int ok=0;
		 	Usuario usr=new Usuario();
			usr.setIdUsuario(Integer.parseInt(request.getParameter("mIdUsuario")));			
			usr.setActivo(request.getParameter("mActivo"));	
			
			UsuarioDao usrDao=new UsuarioDao();
			
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
			usrDao.setConexion(cnn);
			ok=usrDao.anular(usr);
			
			//System.out.println("usuario:"+usr.getUsuario()+" apepat:"+usr.getApeMaterno()+"accion:"+accion);
			dao.desconectar();	
	      return ok;
		   }
	 
	 
	 public int recuperarPorIdUsuario(HttpServletRequest request, HttpServletResponse response) {
		int ok=0;
		PrintWriter pw=null;
		List<Usuario> usrs=null;
		String idUsuario=request.getParameter("IdUsuario");
		//usr.setIdUsuario(Integer.parseInt(request.getParameter("mIdUsuario")));
		System.out.println(" parametro: "+idUsuario);
		UsuarioDao usrDao=new UsuarioDao();		
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
		usrDao.setConexion(cnn);
		usrs=usrDao.recuperarPorIdUsuario(idUsuario);
		System.out.println("Manda el id mal");
		
		try {
			pw = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JSONObject json = new JSONObject();
		int rows = usrs.size();
		if (rows>0){//claveUsuario=usrs.get(0).getClave();	
			try{//Map mUsr;
						
			    List lUsrs = new ArrayList();	
			    System.out.println("Entro"+rows);
			    System.out.println("aQUI"+rows);
			    for (int i=0 ; i<rows ; i++){//mUsr = new HashMap();			       
			    	lUsrs.add(usrs.get(i));
			    }
			    System.out.println("Que paso");
			    json.put("usuarios", lUsrs);			    
			    //json.put(lUsrs);
			    //json.pu
			}
			catch (JSONException jse)
			{
		
			}
		}else{
			Map mUsr;
			List lUsrs = new ArrayList();			
			try {
				mUsr = new HashMap();
				mUsr.put("idUsuario", "" );				
				lUsrs.add(mUsr);				
				json.put("usuarios1", lUsrs);
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
	 
	  public int recuperarTodo(HttpServletRequest request, HttpServletResponse response) {
		  System.out.println("Vamos a recuperar todos los usuarios");
		int ok=0;
		PrintWriter pw=null;
		List<Usuario> usrs=null;
		UsuarioDao usrDao=new UsuarioDao();
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
		usrDao.setConexion(cnn);
		usrs=usrDao.recuperarTodo();		
		
		try {
			pw = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		JSONObject json = new JSONObject();
		int rows = usrs.size();
		if (rows>0){//claveUsuario=usrs.get(0).getClave();	
			try{//Map mUsr;
				List lUsrs = new ArrayList();	
				//System.out.println("Entro"+rows);
				for (int i=0 ; i<rows ; i++){//mUsr = new HashMap();			       
					lUsrs.add(usrs.get(i));
				}
				json.put("usuarios", lUsrs);			    
				//json.put(lUsrs);
				//json.pu
			}catch (JSONException jse){
				//e1.printStackTrace();
			}
		}else{
			Map mUsr;
			List lUsrs = new ArrayList();			
			try {
				mUsr = new HashMap();
				mUsr.put("idUsuario", "" );				
				lUsrs.add(mUsr);				
				json.put("usuarios1", lUsrs);
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
	  
	  
	
	  
	  
	  public int recuperarPorUser(HttpServletRequest request, HttpServletResponse response) {
		int ok=0;
		PrintWriter pw=null;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String user1 = (String)httpRequest.getSession().getAttribute("usuario");
	
		JSONObject json = new JSONObject();

				List lUsrs = new ArrayList();				       
					lUsrs.add(user1);
				try {
					json.put("user", lUsrs);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			    
				System.out.println("Recuperado "+json);
				
		pw.println(json);
		System.out.println("Imprimir "+json);

		return ok;
	   }
	  
	  
	  public int recuperarIdRol(HttpServletRequest request, HttpServletResponse response) {
			int ok=0;
			PrintWriter pw=null;
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			String user1 = (String) httpRequest.getSession().getAttribute("usuario");
			String idRol = (String) httpRequest.getSession().getAttribute("idRol");
			System.out.println("ESTES ES EL ID_ROL"+idRol);
			try {
				pw = response.getWriter();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JSONObject json = new JSONObject();
				List lIdRol = new ArrayList();				       
					lIdRol.add(idRol);
				try {
					json.put("idRol", lIdRol);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			    
				System.out.println("Recuperado idRol"+json);		
				pw.println(json);
		return ok;
		}

}
