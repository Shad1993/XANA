package Fiche;

public class FicheDePaie {
	
	
	private String idFiche;
	public String mois;
	public String heureSup;
	public String bonus;
	public String commission;
	public String deduction;
	private String no_Emp;
	
		
	
	
	public FicheDePaie() {
		
		
		
		
	}
	
	public FicheDePaie(String idFiche,String mois,String heureSup,String bonus,String commission,String no_Emp) {
		
		//constructeur
		this.idFiche = idFiche;
		this.mois = mois;
		this.heureSup = heureSup;
		this.bonus = bonus;
		this.commission = commission;
		this.no_Emp = no_Emp;
		
		
		
		
	}
	
	public String get_idFiche(){
		
		return idFiche;	
	}
	
	public void set_idFiche(String idFiche) {
		
		this.idFiche = idFiche;
		
	}
	
	public String get_NoEmp(){
		
		return no_Emp;	
	}
	
	public void set_noEmp(String no_Emp) {
		
		this.no_Emp = no_Emp;
	}
	
	
	
	
 
	

}
