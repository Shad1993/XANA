package Fiche;

public class FicheDePaie {
	
	
	private String idFiche;
	private String mois;
	private String heureSup;
	private String bonus;
	private String commission;
	private String deduction;
	private String no_Emp;
	
		
	
	
	public FicheDePaie() {
		
		
	}
	
	public FicheDePaie(String idFiche,String mois,String heureSup,String bonus,String commission,String deduction,String no_Emp) {
		
		//constructeur
		this.idFiche = idFiche;
		this.mois = mois;
		this.heureSup = heureSup;
		this.bonus = bonus;
		this.commission = commission;
		this.no_Emp = no_Emp;
		this.deduction = deduction;
		
		
		
		
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
	
    public String get_heureSup(){
		
		return heureSup;	
	}
	
	public void set_heureSup(String heureSup) {
		
		this.heureSup = heureSup;
	}
	
	 public String get_Bonus(){
			
			return bonus;	
	 }
		
	public void set_Bonus(String bonus) {
			
			this.bonus = bonus;
	}
	
	 public String get_Commission(){
			
			return commission;	
	 }
		
	public void set_Commission(String commission) {
			
			this.commission = commission;
	}
	
	public String get_Mois(){
		
		return mois;	
	}
	
	public void set_Mois(String mois) {
		
		this.mois = mois;
	}
	
	public String get_Deduction(){
		
		return deduction;	
	}
	
	public void set_Deduction(String deduction) {
		
		this.deduction = deduction;
	}
	
	
	
	

	
	
	
 
	

}
