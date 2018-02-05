package pe.gob.pj.csjar.tickets.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import pe.gob.pj.csjar.tickets.modelo.Dao;
import pe.gob.pj.csjar.tickets.modelo.IncidenciaDao;
import pe.gob.pj.csjar.tickets.modelo.UsuarioDao;


/**
 * Servlet implementation class IncidenciaServlet
 */
//@WebServlet("/IncidenciaServlet")
public class IncidenciaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncidenciaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		System.out.println("accion:"+accion);
	//	String idIncidencia=request.getParameter("IdIncidencia");
		
		String fInicio = request.getParameter("fInicio");
		String fFin = request.getParameter("fFin");
		int ok=0;
		if (Objects.isNull(accion)){
			accion="";
		}

		if ( accion.equals("recuperarPorIdIncidencia")){
			System.out.println("Funcion recuperarPorIdIncidencia");
			ok=recuperarPorIdIncidencia(request,response);
		}
		else{
			if ( accion.equals("recuperarAtenciones")){
				ok=recuperarPorFechaAtencion(request,response,fInicio,fFin);	
			}
			else{
				if ( accion.equals("recuperarEstadoPorIncidencia")){
					ok=recuperarEstadoPorIncidencia(request,response);	
				}
			else{
				if ( accion.equals("recuperarPorFechasInciAte")){
					ok=recuperarPorFechasInciAte(request,response,fInicio,fFin);	
				}
				else{
					ok=recuperarPorFecha(request,response,fInicio,fFin);
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
		int idIncidencia= Integer.parseInt(request.getParameter("mIdIncidencia"));
		System.out.println("idInci: "+idIncidencia + "accion: "+accion);
		if (idIncidencia == 0 && accion.equals("grabar")){
			ok=insertar(request,response);
		}else if(idIncidencia != 0 && accion.equals("grabar")){
			ok=actualizar(request,response);
		}else if(idIncidencia != 0 && accion.equals("anular")){
			ok=anular(request,response);
		}
	}
	
	
	public int actualizar(HttpServletRequest request, HttpServletResponse response) {
		int ok=0;
		System.out.println("Aqui actualiza");
	 	Incidencia inc=new Incidencia();
	 	String usuario = (String) request.getSession().getAttribute("usuario");
	 	inc.setUsuarioMod(usuario);
	 	inc.setIdIncidencia(request.getParameter("mIdIncidencia"));
	 	String IdIncidencia = request.getParameter("mIdIncidencia");
	 	inc.setNumIncidencia(Integer.parseInt(request.getParameter("mNumIncidencia")));
	 	String NumIncidencia = request.getParameter("mNumIncidencia");
	 	inc.setIdTipIng(request.getParameter("mIdTipIng"));
	 	String IdTipIng = request.getParameter("mIdTipIng");
	 	inc.setIdDependencia(Integer.parseInt(request.getParameter("mIdDependencia")));
	 	inc.setIdUbicacion(Integer.parseInt(request.getParameter("mIdUbicacion")));
	 	String IdDependecia = request.getParameter("mIdDependencia");
	 	inc.setIdSolicitante(Integer.parseInt(request.getParameter("mIdSolicitante")));
	 	inc.setDetIncidencia(request.getParameter("mDetIncidencia"));
	 	inc.setIdUsuario(Integer.parseInt(request.getParameter("mIdUsuario")));							
	 	inc.setIdSituacionTicket(request.getParameter("mIdSiTicket"));							
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
		ok=incDao.actualizar(inc);
		
		//System.out.println("usuario:"+usr.getUsuario()+" apepat:"+usr.getApeMaterno()+"accion:"+accion);
		dao.desconectar();	
      return ok;
	   }
	   /** @param usr
	    * @pdOid 304cdbc4-5cbf-4f03-bd03-a3bb83cb6e00 */
	public int insertar(HttpServletRequest request, HttpServletResponse response) {

	 	int ok=0;
	 	String usuario = (String) request.getSession().getAttribute("usuario");
	 	String fechaHora = request.getParameter("mFecha");
	 	Timestamp fechaIng = Timestamp.valueOf(fechaHora);
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
	 	inc.setIdSituacionTicket(request.getParameter("mIdSiTicket"));							
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
	
	public int anular(HttpServletRequest request, HttpServletResponse response) {
		int ok=0;
		String usuario = (String) request.getSession().getAttribute("usuario");
	 	Incidencia inc=new Incidencia();
	 	inc.setIdIncidencia(request.getParameter("mIdIncidencia"));
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
	
	public int recuperarPorIdIncidencia(HttpServletRequest request, HttpServletResponse response) {
		int ok=0;
		PrintWriter pw=null;
		System.out.println("Buscando por Id Incidencia");
		List<Incidencia> inci=null;
		String idIncidencia=request.getParameter("IdIncidencia");
		IncidenciaDao IncDao=new IncidenciaDao();		
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
		inci=IncDao.recuperarPorIdIncidencia(idIncidencia);
		try {
			pw = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JSONObject json = new JSONObject();
		int rows = inci.size();
		if (rows>0){//claveUsuario=usrs.get(0).getClave();	
			try{//Map mUsr;
						
			    List lInci = new ArrayList();	
			    System.out.println("Entro"+rows);
			    for (int i=0 ; i<rows ; i++){//mUsr = new HashMap();			       
			    	lInci.add(inci.get(i));
			    }
			    System.out.println("Que paso");
			    json.put("incidencias", lInci);			    
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
				mInc.put("idIncidencia", "" );				
				lInci.add(mInc);				
				json.put("incidencias1", lInci);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//pw.println("No hay ressultados...");
		}
		System.out.println("Json De Edit Incidencia "+json);
		dao.desconectar();		
		pw.println(json);
		//System.out.println("Entro GET ok");
	      
      return ok;
	   }
	
	 
	  public int recuperarPorFecha(HttpServletRequest request, HttpServletResponse response,String strfechaInicio,String strfechaFin) {
		
		  
		System.out.println("Entra a Fecha");  

		String horaInicio = "00:00:00";
		String horaFin = "23:59:59"; 
		
		
        Timestamp fechaInicio = Timestamp.valueOf(strfechaInicio + " "+horaInicio);
        Timestamp fechaFin = Timestamp.valueOf(strfechaFin + " "+horaFin);
        
        System.out.println("f1"+fechaInicio+" "+fechaFin);
		
		int ok=0;
		PrintWriter pw=null;
		List<Incidencia> inci=null;
		IncidenciaDao incDao = new IncidenciaDao();
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
		inci=incDao.recuperarPorFechas(fechaInicio,fechaFin);		
		//System.out.println("Mira"+inci);  
		try {
			pw = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		JSONObject json = new JSONObject();
		int rows = inci.size();
		
		System.out.println("Rows"+rows);
		if (rows>0){//claveUsuario=usrs.get(0).getClave();	
			try{//Map mUsr;
				List lInci = new ArrayList();	
				//System.out.println("Entro"+rows);
				for (int i=0 ; i<rows ; i++){//mUsr = new HashMap();			       
					lInci.add(inci.get(i));
				}
				json.put("incidencias", lInci);			    
				//json.put(lUsrs);
				//json.pu
				System.out.println("Bien");
			}catch (JSONException jse){
				//e1.printStackTrace();
			}
		}else{
			Map mInc;
			List lInci = new ArrayList();			
			try {
				mInc = new HashMap();
				mInc.put("idIncidencia", "" );				
				lInci.add(mInc);				
				json.put("incidencias1", lInci);
			} catch (JSONException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//pw.println("No hay ressultados...");
		}
		
		System.out.println("Incidencias "+json);
		dao.desconectar();		
		pw.println(json);
		//System.out.println("Entro GET ok");
	
		return ok;
	   }
	  
	  
	  public int recuperarPorFechaAtencion(HttpServletRequest request, HttpServletResponse response,String strfechaInicio,String strfechaFin) {
			
		  
			System.out.println("Entra a Fecha");  

			String horaInicio = "00:00:00";
			String horaFin = "23:59:59"; 
			
			
	        Timestamp fechaInicio = Timestamp.valueOf(strfechaInicio + " "+horaInicio);
	        Timestamp fechaFin = Timestamp.valueOf(strfechaFin + " "+horaFin);
	        
	        System.out.println("f1"+fechaInicio+" "+fechaFin);
			
			int ok=0;
			PrintWriter pw=null;
			List<Incidencia> inci=null;
			IncidenciaDao incDao = new IncidenciaDao();
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
			inci=incDao.recuperarPorFechas(fechaInicio,fechaFin);		
			//System.out.println("Mira"+inci);  
			try {
				pw = response.getWriter();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
			JSONObject json = new JSONObject();
			int rows = inci.size();
			
			System.out.println("Rows"+rows);
			if (rows>0){//claveUsuario=usrs.get(0).getClave();	
				try{//Map mUsr;
					List lInci = new ArrayList();	
					//System.out.println("Entro"+rows);
					for (int i=0 ; i<rows ; i++){//mUsr = new HashMap();			       
						lInci.add(inci.get(i));
					}
					json.put("incidencias", lInci);			    
					//json.put(lUsrs);
					//json.pu
					System.out.println("Bien");
				}catch (JSONException jse){
					//e1.printStackTrace();
				}
			}else{
				Map mInc;
				List lInci = new ArrayList();			
				try {
					mInc = new HashMap();
					mInc.put("idIncidencia", "" );				
					lInci.add(mInc);				
					json.put("incidencias1", lInci);
				} catch (JSONException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//pw.println("No hay ressultados...");
			}
			
			System.out.println("Incidencias "+json);
			dao.desconectar();		
			pw.println(json);
			//System.out.println("Entro GET ok");
		
			return ok;
		   }
	  
	  
	  public int recuperarPorFechasInciAte(HttpServletRequest request, HttpServletResponse response,String strfechaInicio,String strfechaFin) {
			
		  	System.out.println("Recupera incidencias y atenciones");
			System.out.println("Entra a Fecha");  

			String horaInicio = "00:00:00";
			String horaFin = "23:59:59"; 
			
			
	        Timestamp fechaInicio = Timestamp.valueOf(strfechaInicio + " "+horaInicio);
	        Timestamp fechaFin = Timestamp.valueOf(strfechaFin + " "+horaFin);
	        
	        System.out.println("f1"+fechaInicio+" "+fechaFin);
			
			int ok=0;
			PrintWriter pw=null;
			List<Incidencia> inci=null;
			IncidenciaDao incDao = new IncidenciaDao();
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
			inci=incDao.recuperarPorFechasInciAte(fechaInicio,fechaFin);		
			//System.out.println("Mira"+inci);  
			try {
				pw = response.getWriter();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
			JSONObject json = new JSONObject();
			int rows = inci.size();
			
			System.out.println("Rows"+rows);
			if (rows>0){//claveUsuario=usrs.get(0).getClave();	
				try{//Map mUsr;
					List lInci = new ArrayList();	
					//System.out.println("Entro"+rows);
					for (int i=0 ; i<rows ; i++){//mUsr = new HashMap();			       
						lInci.add(inci.get(i));
					}
					json.put("incidencias", lInci);			    
					//json.put(lUsrs);
					//json.pu
					System.out.println("Bien");
				}catch (JSONException jse){
					//e1.printStackTrace();
				}
			}else{
				Map mInc;
				List lInci = new ArrayList();			
				try {
					mInc = new HashMap();
					mInc.put("idIncidencia", "" );				
					lInci.add(mInc);				
					json.put("incidencias1", lInci);
				} catch (JSONException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//pw.println("No hay ressultados...");
			}
			
			System.out.println("Incidencias "+json);
			dao.desconectar();		
			pw.println(json);
			//System.out.println("Entro GET ok");
		
			return ok;
		   }
	  
	  public int recuperarEstadoPorIncidencia(HttpServletRequest request, HttpServletResponse response) {
			int ok=0;
			PrintWriter pw=null;
			System.out.println("Buscando por Id Incidencia");
			List<Incidencia> inci=null;
			String idIncidencia=request.getParameter("idIncidencia");
			//usr.setIdUsuario(Integer.parseInt(request.getParameter("mIdUsuario")));
			System.out.println(" parametro: "+idIncidencia);
			IncidenciaDao IncDao=new IncidenciaDao();		
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
			inci=IncDao.recuperarEstadoPorIncidencia(idIncidencia);
			
			try {
				pw = response.getWriter();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JSONObject json = new JSONObject();
			int rows = inci.size();
			if (rows>0){//claveUsuario=usrs.get(0).getClave();	
				try{//Map mUsr;
							
				    List lInci = new ArrayList();	
				    System.out.println("Entro"+rows);
				    for (int i=0 ; i<rows ; i++){//mUsr = new HashMap();			       
				    	lInci.add(inci.get(i));
				    }
				    System.out.println("Que paso");
				    json.put("incidencias", lInci);			    
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
					mInc.put("idIncidencia", "" );				
					lInci.add(mInc);				
					json.put("incidencias1", lInci);
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
	  
}
