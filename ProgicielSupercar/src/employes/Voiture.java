package employes;

import java.util.Date;

public class Voiture {

	private int No_voiture;
	private String Marque;
	private String Modele;
	private String Pays_dorigine;
	private String Intervalle_de_service;
	private String Couleur;
	private String Garentie;
	private String Transmission;
	private String Moteur;
	private int prix;
	private Date DateMiseEnVente ;
	
	public Voiture() {
		
	}
	public Voiture(int No_voiture, String Marque, String Modele, String Pays_dorigine,
			String Intervall_de_service, String Couleur, String Garentie, 
			String Transmission, String Moteur, int prix, Date DateMiseEnVente) {
		
	}
	public int getNo_voiture() {
		return No_voiture;
	}
	public void setNo_voiture(int no_voiture) {
		No_voiture = no_voiture;
	}
	public String getMarque() {
		return Marque;
	}
	public void setMarque(String marque) {
		Marque = marque;
	}
	public String getModele() {
		return Modele;
	}
	public void setModele(String modele) {
		Modele = modele;
	}
	public String getPays_dorigine() {
		return Pays_dorigine;
	}
	public void setPays_dorigine(String pays_dorigine) {
		Pays_dorigine = pays_dorigine;
	}
	public String getIntervalle_de_service() {
		return Intervalle_de_service;
	}
	public void setIntervall_de_service(String intervalle_de_service) {
		Intervalle_de_service = intervalle_de_service;
	}
	public String getCouleur() {
		return Couleur;
	}
	public void setCouleur(String couleur) {
		Couleur = couleur;
	}
	public String getGarentie() {
		return Garentie;
	}
	public void setGarentie(String garentie) {
		Garentie = garentie;
	}
	public String getTransmission() {
		return Transmission;
	}
	public void setTransmission(String transmission) {
		Transmission = transmission;
	}
	public String getMoteur() {
		return Moteur;
	}
	public void setMoteur(String moteur) {
		Moteur = moteur;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	public Date getDateMiseEnVente() {
		return DateMiseEnVente;
	}
	public void setDateMiseEnVente(Date dateMiseEnVente) {
		DateMiseEnVente = dateMiseEnVente;
	}
	
	
	
	
}
