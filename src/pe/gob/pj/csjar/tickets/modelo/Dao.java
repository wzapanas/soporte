package pe.gob.pj.csjar.tickets.modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Driver;
public class Dao {
	private String usuarioBd="dba";
	private String claveBd="sql";
	private String url="jdbc:sybase:Tds:127.0.0.1:2638?ServiceName=tickets";
	private String driver="com.sybase.jdbc3.jdbc.SybDriver";
	private Connection conexion;
	public int conectar() {
		int ok=0;
		try{
			DriverManager.registerDriver((Driver)Class.forName(driver).newInstance());
			System.out.println("Driver Loaded");
			conexion = DriverManager.getConnection(url,usuarioBd,claveBd);
			DriverManager.registerDriver((Driver)Class.forName(driver).newInstance());
			if (conexion != null )
                ok=1;
		  }catch(SQLException sqlex){
			  System.out.println("SQL Exception No Conecta a Base de Datos");
			  sqlex.printStackTrace();			  
		  }catch(Exception e){
			System.out.println("Exception No Conecta a Base de Datos");
			e.printStackTrace();
		}		
		return ok;
	   }
	   public int desconectar() {
		   int ok=0;
		   try {
			conexion.close();
			ok=1;
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	      return ok;
	   }
	public String getUsuarioBd() {
		return usuarioBd;
	}
	public void setUsuarioBd(String usuarioBd) {
		this.usuarioBd = usuarioBd;
	}
	public String getClaveBd() {
		return claveBd;
	}
	public void setClaveBd(String claveBd) {
		this.claveBd = claveBd;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public Connection getConexion() {
		return conexion;
	}
	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}
}
