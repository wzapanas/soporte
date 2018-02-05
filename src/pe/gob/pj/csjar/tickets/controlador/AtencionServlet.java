package pe.gob.pj.csjar.tickets.controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer.*;

import javax.servlet.ServletResponse;
import java.sql.*;

import pe.gob.pj.csjar.tickets.modelo.AteEquipoDao;
import pe.gob.pj.csjar.tickets.modelo.AtencionDao;
import pe.gob.pj.csjar.tickets.modelo.Dao;
import pe.gob.pj.csjar.tickets.modelo.IncidenciaDao;

/**
 * Servlet implementation class AtencionServlet
 */
@WebServlet("/Atencion.do")
public class AtencionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AtencionServlet() {
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
		System.out.println("accion______:"+accion);
	//	String idIncidencia=request.getParameter("IdIncidencia");
		String fInicio = request.getParameter("fInicio");
		String fFin = request.getParameter("fFin");
		int ok=0;
		if (Objects.isNull(accion)){
			accion="";
		}
		if ( accion.equals("recuperarPorIdAtencion")){
			System.out.println("recuperarPorIdAtencion");
			ok=recuperarPorIdAtencion(request,response);	
		}else{if ( accion.equals("recuperarAtenciones")){
				ok=recuperarAtencion(request,response);	
			}
			else{if(accion.equals("pdf")){
				System.out.println("Generar PDF");
				generarPDF(request,response);
			}
			}
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		Integer ok=0;
		String accion=request.getParameter("accion");
		int idAtencion= Integer.parseInt(request.getParameter("mIdAtencion"));
		System.out.println("idAtencion: "+idAtencion + "accion: "+accion);
		if (idAtencion == 0 && accion.equals("grabar")){
			ok=insertar(request,response);
		}else if(idAtencion != 0 && accion.equals("grabar")){
			System.out.println("ACTUALIZAR");
			ok=actualizar(request,response);
		}else if(idAtencion != 0 && accion.equals("anular")){
			System.out.println("ANULAR");
			ok=anular(request,response);
		}else if(idAtencion != 0 && accion.equals("pdf")){
			//System.out.println("Generar PDF");
			//generarPDF(request,response);
		}
	}
	
	
	
	public int insertar(HttpServletRequest request, HttpServletResponse response) {

	 	int ok=0;
	 	String usuario = (String) request.getSession().getAttribute("usuario");
	 	String fechaTerm = request.getParameter("mFechaTerm");
	 	System.out.println("qqqqq"+fechaTerm);
	 	Timestamp feTerm = Timestamp.valueOf(fechaTerm);
	 	
	 	System.out.println("qqqqq"+fechaTerm);
	 	
	 	Atencion ate = new Atencion();
	 	ate.setUsuarioReg(usuario);
	 	ate.setIdIncidencia(request.getParameter("mIdIncidencia"));
	 	ate.setIdAtencion(request.getParameter("mIdAtencion"));
	 	ate.setFechaTermino(feTerm);
	 	ate.setIdTipAtencion(request.getParameter("mTipoAtencion"));
	 	ate.setIdTipServicio(request.getParameter("mTipoServicio"));
	 	ate.setIdEstadoServicio(request.getParameter("mTipoEstado"));
	 	ate.setDetalleServicio(request.getParameter("mDetServicio"));
	 	ate.setObservaciones(request.getParameter("mObservaciones"));
	 	ate.setActivo(request.getParameter("mActivo"));	
	 	
	 	
	 	ServletContext ctx=request.getServletContext();
		String usuarioBD=ctx.getInitParameter("UsuarioBD");
		String claveBD=ctx.getInitParameter("ClaveBD");
		String urlBD=ctx.getInitParameter("UrlBD");
		String driverBD=ctx.getInitParameter("DriverBD");
	 	
	 	AtencionDao ateDao = new AtencionDao();
	 	Dao dao=new Dao();
		dao.setUsuarioBd(usuarioBD);
		dao.setClaveBd(claveBD);
		dao.setUrl(urlBD);
		dao.setDriver(driverBD);						
		ok=dao.conectar();
		Connection cnn=dao.getConexion();
		ateDao.setConexion(cnn);
		ok=ateDao.insertar(ate);
		
		AteEquipoDao atEqDao = new AteEquipoDao();
		
		String idAte = ate.getIdAtencion();
	 	AteEquipo atEq=new AteEquipo();
	 	atEq.setIdAtencion(idAte);
	 	atEq.setIdTipEquipo(request.getParameter("mTipoEquipo"));
	 	//atEq.setIdAtencion(request.getParameter("mIdAtencion"));
	 	atEq.setIdMarca(request.getParameter("mMarca"));
	 	atEq.setIdModelo(request.getParameter("mModelo"));
	 	atEq.setSerie(request.getParameter("mSerie"));
	 	
	 	atEqDao.setConexion(cnn);
	 	ok=atEqDao.insertar(atEq);
		//System.out.println("usuario:"+usr.getUsuario()+" apepat:"+usr.getApeMaterno()+"accion:"+accion);
		dao.desconectar();	
      return ok;
   }
	
	public int actualizar(HttpServletRequest request, HttpServletResponse response) {
		int ok=0;
	 	String usuario = (String) request.getSession().getAttribute("usuario");
	 	String fechaTerm = request.getParameter("mFechaTerm");
	 	System.out.println("qqqqq"+fechaTerm);
	 	Timestamp feTerm = Timestamp.valueOf(fechaTerm);
	 	
	 	System.out.println("qqqqq"+fechaTerm);
	 	
	 	Atencion ate = new Atencion();
	 	ate.setUsuarioMod(usuario);
	 	ate.setIdIncidencia(request.getParameter("mIdIncidencia"));
	 	ate.setIdAtencion(request.getParameter("mIdAtencion"));
	 	ate.setFechaTermino(feTerm);
	 	ate.setIdTipAtencion(request.getParameter("mTipoAtencion"));
	 	ate.setIdTipServicio(request.getParameter("mTipoServicio"));
	 	ate.setIdEstadoServicio(request.getParameter("mTipoEstado"));
	 	ate.setDetalleServicio(request.getParameter("mDetServicio"));
	 	ate.setObservaciones(request.getParameter("mObservaciones"));
	 	ate.setActivo(request.getParameter("mActivo"));	
	 	
	 	
	 	ServletContext ctx=request.getServletContext();
		String usuarioBD=ctx.getInitParameter("UsuarioBD");
		String claveBD=ctx.getInitParameter("ClaveBD");
		String urlBD=ctx.getInitParameter("UrlBD");
		String driverBD=ctx.getInitParameter("DriverBD");
	 	
	 	AtencionDao ateDao = new AtencionDao();
	 	Dao dao=new Dao();
		dao.setUsuarioBd(usuarioBD);
		dao.setClaveBd(claveBD);
		dao.setUrl(urlBD);
		dao.setDriver(driverBD);						
		ok=dao.conectar();
		Connection cnn=dao.getConexion();
		ateDao.setConexion(cnn);
		ok=ateDao.actualizar(ate);
		
		AteEquipoDao atEqDao = new AteEquipoDao();
		
		String idAte = ate.getIdAtencion();
	 	AteEquipo atEq=new AteEquipo();
	 	atEq.setIdAtencion(idAte);
	 	atEq.setIdTipEquipo(request.getParameter("mTipoEquipo"));
	 	//atEq.setIdAtencion(request.getParameter("mIdAtencion"));
	 	atEq.setIdMarca(request.getParameter("mMarca"));
	 	atEq.setIdModelo(request.getParameter("mModelo"));
	 	atEq.setSerie(request.getParameter("mSerie"));
	 	
	 	atEqDao.setConexion(cnn);
	 	ok=atEqDao.actualizar(atEq);
		
		//System.out.println("usuario:"+usr.getUsuario()+" apepat:"+usr.getApeMaterno()+"accion:"+accion);
		dao.desconectar();	
      return ok;
	   }
	
	
	
	
	public int recuperarPorIdAtencion(HttpServletRequest request, HttpServletResponse response) {
		int ok=0;
		PrintWriter pw=null;
		List<Atencion> ate=null;
		//List<AteEquipo> ateEq=null;
		String idIncidencia=request.getParameter("IdIncidencia");
		String idAtencion=request.getParameter("IdAtencion");
		//usr.setIdUsuario(Integer.parseInt(request.getParameter("mIdUsuario")));
		System.out.println(" parametro: "+idIncidencia);
		System.out.println(" parametro: "+idAtencion);
		
		AtencionDao AteDao=new AtencionDao();	
		//AteEquipoDao AteEqDao=new AteEquipoDao();
		
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
		AteDao.setConexion(cnn);
		//AteEqDao.setConexion(cnn);
		ate=AteDao.recuperarPorIdAtencion(idIncidencia,idAtencion);
		//ateEq=AteEqDao.recuperarPorAteEq(idAtencion);
		try {
			pw = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JSONObject json = new JSONObject();
		int rows = ate.size();
		if (rows>0){//claveUsuario=usrs.get(0).getClave();	
			try{//Map mUsr;
						
			    List lAte = new ArrayList();	
			    System.out.println("Entro"+rows);
			    for (int i=0 ; i<rows ; i++){//mUsr = new HashMap();			       
			    	lAte.add(ate.get(i));
			    }
			    System.out.println("Que paso");
			    json.put("atenciones", lAte);			    
			    //json.put(lUsrs);
			    //json.pu
			}
			catch (JSONException jse)
			{
		
			}
		}else{
			Map mAte;
			List lAte = new ArrayList();			
			try {
				mAte = new HashMap();
				mAte.put("atenciones", "" );				
				lAte.add(mAte);				
				json.put("atenciones1", lAte);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("ID____ATENCION "+json);
		
		dao.desconectar();		
		pw.println(json);
	      
      return ok;
	   }
	
	
	public int recuperarAtencion(HttpServletRequest request, HttpServletResponse response) {
		
		//System.out.println("Entra a Fecha");  
        //System.out.println("f1"+fechaInicio+" "+fechaFin);
		int ok=0;
		PrintWriter pw=null;
		List<Atencion> ate=null;
		String idIncidencia=request.getParameter("IdIncidencia");
		AtencionDao ateDao = new AtencionDao();
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
		ateDao.setConexion(cnn);
		ate=ateDao.recuperarAtencion(idIncidencia);		
		//System.out.println("Mira"+inci);  
		try {
			pw = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		JSONObject json = new JSONObject();
		int rows = ate.size();
		
		System.out.println("Rows"+rows);
		if (rows>0){//claveUsuario=usrs.get(0).getClave();	
			try{//Map mUsr;
				List lInci = new ArrayList();	
				//System.out.println("Entro"+rows);
				for (int i=0 ; i<rows ; i++){//mUsr = new HashMap();			       
					lInci.add(ate.get(i));
				}
				json.put("atenciones", lInci);			    
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
				mInc.put("atenciones", "" );				
				lInci.add(mInc);				
				json.put("atenciones1", lInci);
			} catch (JSONException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//pw.println("No hay ressultados...");
		}
		
		System.out.println("Atenciones "+json);
		dao.desconectar();		
		pw.println(json);
		//System.out.println("Entro GET ok");
	
		return ok;
	   }
	
	public int anular(HttpServletRequest request, HttpServletResponse response) {
		int ok=0;
		String usuario = (String) request.getSession().getAttribute("usuario");
	 	Atencion ate=new Atencion();
	 	ate.setIdIncidencia(request.getParameter("mIdIncidencia"));
	 	String mIdIncidencia = request.getParameter("mIdIncidencia");

	 	ate.setMotivoAnul(request.getParameter("mMotivoAnular"));
	 	String MotivoAnular = request.getParameter("mIdIncidencia");
	 	ate.setIdAtencion(request.getParameter("mIdAtencion"));
	 	ate.setUsuarioAnul(usuario);
	 	ate.setActivo(request.getParameter("mActivo"));	
	 	String IdIncidencia = request.getParameter("mIdIncidencia");
		AtencionDao ateDao=new AtencionDao();
		
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
		ateDao.setConexion(cnn);
		ok=ateDao.anular(ate);
		
		dao.desconectar();	
      return ok;
	   }
	
	
	
	public static void generarPDF(HttpServletRequest request, HttpServletResponse response) {
		
        try {
        	int ok=0;
        	String IdAtencion;
        	String IdIncidencia = request.getParameter("mIdIncidencia");
        	
    		ServletContext ctx=request.getServletContext();
			String usuarioBD=ctx.getInitParameter("UsuarioBD");
			String claveBD=ctx.getInitParameter("ClaveBD");
			String urlBD=ctx.getInitParameter("UrlBD");
			String driverBD=ctx.getInitParameter("DriverBD");
			AtencionDao ateDao=new AtencionDao();				
			Dao dao=new Dao();
			dao.setUsuarioBd(usuarioBD);
			dao.setClaveBd(claveBD);
			dao.setUrl(urlBD);
			dao.setDriver(driverBD);						
			ok=dao.conectar();
			Connection cnn=dao.getConexion();
			ateDao.setConexion(cnn);
			IdAtencion = ateDao.obtenerAtencion(IdIncidencia);
			System.out.println("este _ "+IdAtencion);
			
			
    		ServletOutputStream outputstream = null;
    		
			JasperReport jasperReport;
			
			List<Atencion> listaAtencion = new ArrayList<Atencion>();
     		listaAtencion = ateDao.ImprimirAtencion(IdIncidencia,IdAtencion);
     		JRBeanCollectionDataSource coleccion_datos = new JRBeanCollectionDataSource(listaAtencion);
			byte[] bytes;
			File reportfile = new File (request.getRealPath("informe.jasper"));
			jasperReport=(JasperReport)JRLoader.loadObject(reportfile);
	        Map<String,Object> parameter = new HashMap<String,Object>();
	        
			bytes = JasperRunManager.runReportToPdf(jasperReport, parameter, coleccion_datos);
			
			response.setContentType("application/pdf");
	        response.setContentLength(bytes.length);
	        try {
				outputstream = response.getOutputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        try {
				outputstream.write(bytes,0,bytes.length);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        try {
				outputstream.flush();
				
				System.out.print("FLUSH");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        try {
				outputstream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }		
}
