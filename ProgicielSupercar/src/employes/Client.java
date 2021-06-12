package employes;

public class Client {
	
	private int No_client;
	private String Nom;
	private String Prenom;
	private String NCI;
	private String Adresse;
	private String AdresseEmail;
	private int No_contact;

	public Client() {
		
	}
	
	public Client(int No_client, String Nom, String Prenom, String NCI, String Adresse, String AdresseEmail, int No_contact) {
		this.No_client =No_client;
		this.Nom = Nom;
		this.Prenom =Prenom;
		this.NCI=NCI;
		this.Adresse = Adresse;
		this.AdresseEmail=AdresseEmail;
		this.No_contact=No_contact;
	}

	public int getNo_client() {
		return No_client;
	}

	public void setNo_client(int no_client) {
		No_client = no_client;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getPrenom() {
		return Prenom;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}

	public String getNCI() {
		return NCI;
	}

	public void setNCI(String nCI) {
		NCI = nCI;
	}

	public String getAdresse() {
		return Adresse;
	}

	public void setAdresse(String adresse) {
		Adresse = adresse;
	}

	public String getAdresseEmail() {
		return AdresseEmail;
	}

	public void setAdresseEmail(String adresseEmail) {
		AdresseEmail = adresseEmail;
	}

	public int getNo_contact() {
		return No_contact;
	}

	public void setNo_contact(int no_contact) {
		No_contact = no_contact;
	}





}
