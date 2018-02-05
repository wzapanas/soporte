package pe.gob.pj.csjar.tickets.controlador;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.gob.pj.csjar.tickets.modelo.IncidenciaDao;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		// pass the request along the filter chain
		 System.out.println("ENTRO AL FILTER");
		 /*  System.out.println("ENTRO AL FILTER");
	        String userID = new String("ABCD");
	        String userIDKey = new String("USER");
	        Integer intInterval=0;
	   	 */
		 
		 
	        if (request instanceof HttpServletRequest) {
	            // Si es http realizamos la validacion para verificar que el usuario
	            // se encuentre logueado
	       //     System.out.println("Es un HttpServletRequest");

	            // Obtenemos el HttpServletRequest y HttpServletResponse
	            HttpServletRequest httpRequest = (HttpServletRequest) request;
	            HttpServletResponse httpResponse = (HttpServletResponse) response;
	            
	         //   httpRequest.getSession();
	           // userID = (String)httpRequest.getSession().getAttribute(userIDKey);
	            //intInterval = httpRequest.getSession().getMaxInactiveInterval();
	            //getMaxInactiveInterval();
	            // Redirigimos al usuario a la pagina index.jsp
	            //System.out.println(userID);
	            //System.out.println(intInterval);
	            
	            
	            // Verificamos que el usuario se encuentre logueado
	            if (httpRequest.getSession().getAttribute("usuario") == null) {
	                // No hay usuario logueado, por lo tanto redirigimos al usuario
	                // a la página login.jsp para
	                // que se pueda loguear
	                //httpResponse.sendRedirect(httpResponse.encodeURL(httpRequest.getContextPath() + "/login.html"));
	                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.html");
	            }
	            
	            //System.out.println(httpRequest.getSession().getAttribute("usuario"));
	            
	        }
	        // Si el usuario estaba logueado seguimos con la secuencia de Filters
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
