package operationSQL;


public class QueryStatement {
	//Vente
	public final static String ADD_VENTE = "INSERT INTO vente(NoClient,No_Emp,Quantite,NoVoiture,Statut) VALUES(?,?,?,?, 'En Attente')";
	public final static String UPDATE = "UPDATE vente SET No_vente=?, Quantite=?,NoVoiture=?,No_Emp=?,NoClient=?, Statut=? where No_vente=?";
	public final static String DELETE = "DELETE FROM vente WHERE No_vente=?";
	public final static String LIST = "SELECT distinct(No_vente),NoClient,clients.Nom,"
			+ "vente.No_Emp,employes.Nom,voitures.Modele,voitures.Marque,Quantite,NoVoiture, vente.Statut"
			+ " FROM vente, clients,voitures,employes "
			+ "WHERE vente.No_Emp=employes.No_employe AND vente.NoVoiture=voitures.No_voiture AND vente.NoClient= clients.No_client AND vente.Statut != 'Terminez' ";
	public final static String SELECT = "SELECT * FROM vente WHERE No_vente=?";
	public static final String LIST_ARCHIVEVENTE = "SELECT distinct(No_vente),NoClient,clients.Nom,"
			+ "vente.No_Emp,employes.Nom,voitures.Modele,voitures.Marque,Quantite,NoVoiture, vente.Statut"
			+ " FROM vente, clients,voitures,employes "
			+ "WHERE vente.No_Emp=employes.No_employe AND vente.NoVoiture=voitures.No_voiture AND vente.NoClient= clients.No_client AND vente.Statut='Terminez'";
	//Entrepot
	public final static String LIST_ENTREPOT = "SELECT * FROM entrepot";
	public final static String ADD_ENTREPOT = "INSERT INTO entrepot(Nom,Adresse, Email) VALUES(?,?,?)";
	public final static String UPDATE_ENTREPOT = "UPDATE entrepot SET Id_Entrepot=?, Nom=?,Adresse=?,Email=? WHERE Id_Entrepot=?";
	public final static String DELETE_ENTREPOT = "DELETE FROM entrepot WHERE Id_Entrepot=?";
	public static final String LIST_ENTREPOTCOMBOBOX ="SELECT concat(Id_Entrepot , '  -' , Nom) FROM entrepot";
	
	// Client
	public final static String LIST_CLIENT = "SELECT * FROM clients";
	public final static String ADD_CLIENT = "INSERT INTO clients(Nom,Prenom,NCI,Adresse, AdresseEmail,No_contact) VALUES(?,?,?,?,?,?)";
	public final static String UPDATE_CLIENT = "UPDATE clients SET No_client=?, Nom=?,Prenom=?,NCI=?,Adresse=?,AdresseEmail=?,No_contact=? WHERE No_client=?";
	public final static String DELETE_CLIENT = "DELETE FROM clients WHERE No_client=?";
	public static final String LIST_CLIENTCOMBOBOX ="SELECT concat(No_client , '  -' , Nom,'-',Prenom) FROM clients";
	public static final String LIST_CLIENTCOMBOBOXSELECT ="SELECT concat(No_client , '  -' , Nom,'-',Prenom) FROM clients WHERE No_client = ?";

	//Voiture
	public final static String LIST_VOITURE = "SELECT * FROM voitures";
	public final static String ADD_VOITURE = "INSERT INTO voitures(Marque, Modele, Pays_dorigine, Intervalle_de_service, Couleur, Garantie, "
			+ "Transmission, Moteur, Prix, DateMiseEnVente,Entrepot,Statut) VALUES(?,?,?,?,?,?,?,?,?,?,?,'Disponible')";
	public final static String UPDATE_VOITURE = "UPDATE voitures SET No_voiture=?, Marque=?, Modele=?, Pays_dorigine=?, Intervalle_de_service=?, "
			+ "Couleur=?, Garantie=?, Transmission=?, Moteur=?, Prix=?, DateMiseEnVente=?  WHERE No_voiture=?";
	public final static String UPDATE_VOITUREVENDU = "UPDATE voitures SET Statut='Vendu'  WHERE No_voiture=?";
	public final static String DELETE_VOITURE = "DELETE FROM voitures WHERE No_voiture=?";
	public static final String LIST_VOITURECOMBOBOX ="SELECT concat(No_voiture , '  -' , Marque,'-',Modele) FROM voitures";
	public static final String LIST_VOITURECOMBOBOXSELECT ="SELECT concat(No_voiture , '  -' , Marque,'-',Modele) FROM voitures WHERE No_voiture = ?";
	public static final String LIST_PRIXCOMBOBOXSELECT ="SELECT Prix FROM voitures WHERE No_voiture = ?";


	//Employé
	public static final String LIST_EMPCOMBOBOX ="SELECT concat(No_employe , '  -' , Nom,'-',Prenom) FROM employes";
	public static final String LIST_EMPCOMBOBOXSELECT ="SELECT concat(No_employe , '  -' , Nom,'-',Prenom) FROM employes WHERE No_employe = ?";

	//Stock
	public static final String LIST_STOCK = "SELECT voitures.No_voiture, entrepot.Id_Entrepot,voitures.Marque, voitures.Modele,entrepot.Nom, Entrepot.Email "
			+ "FROM voitures,entrepot "
			+ "WHERE voitures.Entrepot=entrepot.Id_entrepot AND voitures.Statut= 'Disponible'";

	//Tableau de bord
	public static final String NO_ATTENTE = "SELECT Count(*) FROM vente WHERE Statut ='En Attente'";
	public static final String NO_COURS = "SELECT Count(*) FROM vente WHERE Statut ='En Cours'";
	public static final String NO_TERMINEZ = "SELECT Count(*) FROM vente WHERE Statut ='Terminez'";

	
}	