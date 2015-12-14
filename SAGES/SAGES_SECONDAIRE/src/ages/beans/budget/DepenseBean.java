package ages.beans.budget;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import utils.Service;

import ages.beans.GenericBean;


@ManagedBean(name="depenseBean")
@ViewScoped
public class DepenseBean extends GenericBean implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int iddepense;
	private String codedepense;
	private Date dateenreg;
	private float montant;
	private boolean supprime;
	private String description;
	private String typedepense;
	private String nomrespon;
	private String prenomrespon;
	
	private float totalCode;
	
	private float total;
	
	private List<DepenseBean> listeDep;
	
	@ManagedProperty(value="#{service}")
	protected Service service;
			
	public void setService(Service service) {
		this.service = service;
	}
	
	public int getIddepense() {
		return iddepense;
	}
	public void setIddepense(int iddepense) {
		this.iddepense = iddepense;
	}
	public String getCodedepense() {
		return codedepense;
	}
	public void setCodedepense(String codedepense) {
		this.codedepense = codedepense;
	}
	public Date getDateenreg() {
		return dateenreg;
	}
	public void setDateenreg(Date dateenreg) {
		this.dateenreg = dateenreg;
	}
	public float getMontant() {
		return montant;
	}
	public void setMontant(float montant) {
		this.montant = montant;
	}
	public boolean isSupprime() {
		return supprime;
	}
	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}
	public String getTypedepense() {
		return typedepense;
	}
	public void setTypedepense(String typedepense) {
		this.typedepense = typedepense;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNomrespon() {
		return nomrespon;
	}

	public void setNomrespon(String nomrespon) {
		this.nomrespon = nomrespon;
	}

	public String getPrenomrespon() {
		return prenomrespon;
	}

	public void setPrenomrespon(String prenomrespon) {
		this.prenomrespon = prenomrespon;
	}
	
	public float getTotalCode() {
		return totalCode;
	}

	public void setTotalCode(float totalCode) {
		this.totalCode = totalCode;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public List<DepenseBean> getListeDep() {
		return listeDep;
	}

	public void setListeDep(List<DepenseBean> listeDep) {
		this.listeDep = listeDep;
	}

	public void initialize(){
		
		if(this.iddepense!=0)
			this.service.initialiseDepenseBean(this);
	}
	
	@PostConstruct
	public void init(){
		setListeDep(this.service.listedepenses());
		total=0;
		
		for(int i=0;i<listeDep.size();i++){
			total+=listeDep.get(i).getMontant();
		}
		
	}

}
