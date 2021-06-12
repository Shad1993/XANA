/**
 * 
 */
package employes;

/**
 * @author Umar
 *
 */
public class Entrepot {
	
	private int id_Entrepot;
	private String Nom;
	private String Adresse;
	private String Email;

	public Entrepot() {
		
	}
	public Entrepot(int id_Entrepot, String Nom, String Adresse, String Email) {
		this.id_Entrepot = id_Entrepot;
		this.Nom = Nom;
		this.Adresse = Adresse;
		this.Email = Email;
	}
	public int getId_Entrepot() {
		return id_Entrepot;
	}
	public void setId_Entrepot(int id_Entrepot) {
		this.id_Entrepot = id_Entrepot;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getAdresse() {
		return Adresse;
	}
	public void setAdresse(String adresse) {
		Adresse = adresse;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	
	

	
	
	
	
}
