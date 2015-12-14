package ressources;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import org.apache.log4j.PropertyConfigurator;

/**
 * Servlet implementation class Log4jLoader
 * 
 * initialise la configuration de Log4j, au démarrage de l'application
 */

public class Log4jLoaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Log4jLoaderServlet() {
        super();
    }

	/**
	 * Executée a l'initialisation de la servlet
	 */
	public void init(ServletConfig config) throws ServletException {
		String cheminWebApp = getServletContext().getRealPath("/");    
		String cheminLogConfig = cheminWebApp + getInitParameter("log4j-fichier-config");
		String cheminLog = cheminWebApp + getInitParameter("log4j-chemin-log");
		String cheminInfo = cheminWebApp + getInitParameter("log4j-chemin-info");
				
		System.setProperty( "log.chemin", cheminLog );
		System.setProperty( "log.chemininfo", cheminInfo );
		 
		if (cheminLogConfig != null) {
			PropertyConfigurator.configure(cheminLogConfig);
		}   
	}
}
