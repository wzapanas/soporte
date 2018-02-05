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
import pe.gob.pj.csjar.tickets.modelo.TabItemsDao;

/**
 * Servlet implementation class TabItemsServlet
 */
@WebServlet("/TabItems.do")
public class TabItemsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TabItemsServlet() {
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
		System.out.println("accion:"+accion);
		
		int ok=0;
		if (Objects.isNull(accion)){
			accion="";
		}

		if ( accion.equals("recuperarPorIdTabla")){
			System.out.println("Elige por id tabla");
			ok=recuperarPorIdTabla(request,response);	
		}else{
			//ok=recuperarTodo(request,response,fInicio,fFin);
		}

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	public int recuperarPorIdTabla(HttpServletRequest request, HttpServletResponse response) {
		int ok=0;
		PrintWriter pw=null;
		System.out.println("Buscando por Id tabla");
		List<TabItems> tItems=null;
		String idTabla=request.getParameter("IdTabla");
		TabItemsDao tItemsDao=new TabItemsDao();		
		ServletContext ctx=request.getServletContext();
		String usuarioBD=ctx.getInitParameter("UsuarioBD");
		String claveBD=ctx.getInitParameter("ClaveBD");
		String urlBD=ctx.getInitParameter("UrlBD");
		String driverBD=ctx.getInitParameter("DriverBD");
		System.out.println("sstabla"+idTabla);
		Dao dao=new Dao();
		dao.setUsuarioBd(usuarioBD);
		dao.setClaveBd(claveBD);
		dao.setUrl(urlBD);
		dao.setDriver(driverBD);						
		ok=dao.conectar();
		Connection cnn=dao.getConexion();
		tItemsDao.setConexion(cnn);
		tItems=tItemsDao.recuperarPorIdTabla(idTabla);
		
		try {
			pw = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JSONObject json = new JSONObject();
		int rows = tItems.size();
		if (rows>0){//claveUsuario=usrs.get(0).getClave();	
			try{//Map mUsr;
						
			    List lInci = new ArrayList();	
			    System.out.println("Entro"+rows);
			    for (int i=0 ; i<rows ; i++){//mUsr = new HashMap();			       
			    	lInci.add(tItems.get(i));
			    }
			    System.out.println("Que paso");
			    json.put("tipItems", lInci);			    
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
				mInc.put("idTabla", "" );				
				lInci.add(mInc);				
				json.put("tipItems", lInci);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//pw.println("No hay ressultados...");
		}
		
		
		System.out.println("ssssss"+json);
		dao.desconectar();		
		pw.println(json);
		//System.out.println("Entro GET ok");
	      
      return ok;
	   }
}
