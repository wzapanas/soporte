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
import pe.gob.pj.csjar.tickets.modelo.TipIngresoDao;;

/**
 * Servlet implementation class TipIngresoServlet
 */
@WebServlet("/TipIngreso.do")
public class TipIngresoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TipIngresoServlet() {
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
		String IdTipIng=request.getParameter("IdTipIng");
		System.out.println("accion rol "+accion+" id tipIng: "+IdTipIng);
		int ok=0;
		
		if (Objects.isNull(accion)){
			accion="";
		}		
		if ( accion.equals("recuperarPorIdTipIng")){
		//	ok=recuperarPorIdRol(request,response);			
		}else{
			ok=recuperarTodo(request,response);
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
		List<TipoIngreso> tipIngs=null;
		TipIngresoDao tipIngDao=new TipIngresoDao();
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
		tipIngDao.setConexion(cnn);
		tipIngs=tipIngDao.recuperarTodo();		
			
		try {
			pw = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		JSONObject json = new JSONObject();
		int rows = tipIngs.size();
		if (rows>0){//claveUsuario=usrs.get(0).getClave();	
			try{//Map mUsr;
				List lTipIngs = new ArrayList();	
					//System.out.println("Entro"+rows);
					for (int i=0 ; i<rows ; i++){//mUsr = new HashMap();			       
						lTipIngs.add(tipIngs.get(i));
					System.out.println("cuantos: "+i);
					}
					
					json.put("tipIngs", lTipIngs);			    
					
				}catch (JSONException jse){
					//e1.printStackTrace();
				}
			}else{
				Map mTipIngs;
				List lTipIngs = new ArrayList();			
				try {
					mTipIngs = new HashMap();
					mTipIngs.put("idTipIng", "" );				
					lTipIngs.add(mTipIngs);				
					json.put("tipIngs1", lTipIngs);
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
