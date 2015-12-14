package utils.impression;

import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperRunManager;

public class Generate {


	
	public Generate(){
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void  print() {
	
			
			try {
				
				 FacesContext facesContext = FacesContext.getCurrentInstance();
			        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
			        InputStream reportStream = facesContext.getExternalContext().getResourceAsStream("/Report/user.jasper");

			        ServletOutputStream servletOutputStream = response.getOutputStream();

			     
			        facesContext.responseComplete();
			        response.setContentType("application/pdf");

			        JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream,
			                new HashMap(),new net.sf.jasperreports.engine.JREmptyDataSource());

			       
			        servletOutputStream.flush();
			        servletOutputStream.close();
			
			
			} catch (IOException e) {
			System.out.print(e.getMessage());

			}
			
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

	
	}

}

