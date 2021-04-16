package com.example.constants;

public class QueryStatement {
	
	public final static String ADD_EMPLOYE = "INSERT INTO employes(Nom,Prenom,NIC,DOB,Sexe,Adresse,AdresseEmail,No_contact,Titre,Salaire,DateDembauche,Comission,Nodept) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
	
	//public final static String UPDATE_EMPLOYE_QUERY = "UPDATE employes SET Nom=?,Prenom=?,NIC=?,DOB=?,Sexe=?,Adresse=?,AdresseEmail=?,No_contact=?,Titre=?,Salaire=?,DateDembauche=?,Comission=?,Nodept=? where No_employe=?";
	public final static String UPDATE_EMPLOYE = "UPDATE employes SET Nom=?,Prenom=?,NIC=?,DOB=?,Sexe=?,Adresse=?,AdresseEmail=?,No_contact=?,Titre=?,Salaire=?,DateDembauche=?,Comission=?,Nodept=? WHERE No_employe=?";

	public final static String DELETE_EMPLOYE_QUERY = "DELETE FROM employes WHERE No_employe=?";
	
	public final static String SELECT_ALL_EMPLOYES_QUERY = "SELECT No_employe,Nom,Prenom,NIC,DOB,Sexe,Adresse,AdresseEmail,No_contact,Titre,Salaire,DateDembauche,Comission,Nodept,Nom_dept\r\n" + 
			" FROM employes E, departement D\r\n" + 
			" WHERE E.Nodept = D.No_dept ORDER BY No_employe ASC";
	
	public final static String SELECT_DEPT_QUERY = "SELECT Nom_dept,No_dept FROM departement";

	public final static String Add_FICHE = "INSERT INTO fch_de_paie(Mois,HeureSup,Bonus,Commision,Deduction,No_Emp) VALUES(?,?,?,?,?,?)";
	
	public final static String DELETE_FICHE = "DELETE FROM fch_de_paie WHERE idFiche=?";
	
	public  final static String SELECT_NO_EMP = "SELECT No_employe FROM g employes";
	
	
}
