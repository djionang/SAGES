package ages.beans.etablissement.classe;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import net.sf.jasperreports.engine.JRException;

import utils.Service;
import ages.beans.GenericBean;

@ManagedBean(name="listClasseBean")
@RequestScoped
public class ListClasseBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	// declaration du service
	@ManagedProperty(value="#{service}")
	protected Service service;
	
	private List<ClasseBean> classes;
	
	private List<ClasseBean> classesoptions;
	
	private String codeoption;
	
	private int total;
	
	public List<ClasseBean> getClasses() {
		return classes;
	}
	public void setClasses(List<ClasseBean> classes) {
		this.classes = classes;
	}
	
	
	public void setService(Service service) {
		this.service = service;
	}
	public String getCodeoption() {
		return codeoption;
	}
	public void setCodeoption(String codeoption) {
		this.codeoption = codeoption;
	}
	
	public List<ClasseBean> getClassesoptions() {
		return classesoptions;
	}
	public void setClassesoptions(List<ClasseBean> classesoptions) {
		this.classesoptions = classesoptions;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	@PostConstruct
	public void initClasses(){
		classes=this.service.listeclasses();
		
		total=0;
		
		for(int i=0;i<classes.size();i++){
			total+=classes.get(i).getContenanceActuelle();
		}
	}
	
	public void loadClasses(){
		if(codeoption!=null && ! codeoption.isEmpty())
		classesoptions=this.service.listeClassesOptions(codeoption);
	}
	
	public String imprimeretatversement(){
		try {
			this.service.imprimeretatversement();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

}
