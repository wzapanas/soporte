package pe.gob.pj.csjar.tickets.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
import pe.gob.pj.csjar.tickets.modelo.LocalDao;

/**
 * Servlet implementation class LocalServlet
 */
@WebServlet("/Local.do")
public class LocalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LocalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Territorio de las localidades");
		String accion = request.getParameter("accion");
		String idLocal=request.getParameter("mIdLocal");
		System.out.println("accion local "+accion+" parametro: "+idLocal);
		int ok=0;
		if (accion.equals("recuperarPorIdEquipo")){
			//ok=recuperarPorIdEquipo(request,response);			
		}else {if(accion.equals("recuperarPorEquipo")){
			//ok=recuperarPorEquipo(request,response);	
		}else{if(accion.equals("recuperarEquiposNoAsignados")){
			//ok=recuperarEquiposNoAsignados(request,response);	
		}else{if(accion.equals("recuperarUltimoGrupo")){
			//ok=recuperarUltimoGrupo(request,response);	
		}else{if(accion.equals("recuperarPorGrupo")){
			//ok=recuperarPorGrupo(request,response);	
		}else{if(accion.equals("recuperarTodo")){
			System.out.println("Recupera por todos los locales");
			ok=recuperarTodo(request,response);
			}
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

	}
	
	public int recuperarTodo(HttpServletRequest request, HttpServletResponse response) {
		  System.out.println("Vamos a recuperar todos los locales");
		int ok=0;
		PrintWriter pw=null;
		List<Local> loc=null;
		LocalDao locDao=new LocalDao();
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
		locDao.setConexion(cnn);
		loc=locDao.recuperarTodo();		
		
		try {
			pw = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		JSONObject json = new JSONObject();
		int rows = loc.size();
		if (rows>0){//claveUsuario=usrs.get(0).getClave();	
			try{//Map mUsr;
				List lLoc = new ArrayList();	
				//System.out.println("Entro"+rows);
				for (int i=0 ; i<rows ; i++){//mUsr = new HashMap();			       
					lLoc.add(loc.get(i));
				}
				json.put("locales", lLoc);			    
				//json.put(lUsrs);
				//json.pu
			}catch (JSONException jse){
				//e1.printStackTrace();
			}
		}else{
			Map mLoc;
			List lLoc = new ArrayList();			
			try {
				mLoc = new HashMap();
				mLoc.put("idLocal", "" );				
				lLoc.add(mLoc);				
				json.put("locales1", lLoc);
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
	

}
