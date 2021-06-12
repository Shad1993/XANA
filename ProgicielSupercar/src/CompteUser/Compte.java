package CompteUser;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import CompteUser.Compte;
import connexionBDD.ConnectionFactoryX;
import operationSQL.RequeteStatement;
import vente.MenuVente;


/**
 * Cette classe est utilisee pour instantier les objets comptes utilisateurs de tous types utilisateurs 
 * @author Lionel Perrine
 *
 */

public class Compte {

	public String idUser;
	public String noEmp;
	public String login;
	public String typeCompte;
	private String mdp;

	//public Compte() {

	//}

	//public Compte(String idUser,String noEmp, String email,String mdp) {
	//this.idUser = idUser;
	//this.noEmp = noEmp;
	//this.email = email;
	//this.mdp = mdp;

	//}

	/**
	 * Méthode getter
	 * @return retourne mot de passe
	 */
	public String getMdp() {
		return mdp;
	}


	/**
	 * Méthode setter
	 * @param mdpSaisie le mot de passe de de l'utilisateur
	 */

	public void setMdp(String mdpSaisie) {
		this.mdp = mdpSaisie;
	}

	/**
	 * Méthode getter
	 * @return retourne le variable le type compte de l'utilisateur de type String
	 */
	public String getTypeCompte() {
		return typeCompte;
	}


	/**
	 * Méthode Setter
	 * @param typeCompte variable de type String; le type de profil de l'utilisateur
	 */
	public void setTypeCompte(String typeCompte) {
		this.typeCompte = typeCompte;
	}




	/**
	 * Cette methode chiffre le mot de passe en texte claire
	 * @param chaineDeCarac variable de type String qui stock le mot de passe
	 * @return retourne la chaine de caractère chiffrée
	 */
	public String hashMdp(String chaineDeCarac) {
		try {
			byte[] dataBytes = chaineDeCarac.getBytes();
			MessageDigest hashDeMdp = MessageDigest.getInstance("SHA");
			hashDeMdp.update(dataBytes);
			byte[] data = hashDeMdp.digest();
			chaineDeCarac = new BigInteger(data).toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chaineDeCarac;
	}



}



/**
 * Class CompteUtilisateurAdmin hérite de la classe mere CompteUtilisateur
 *
 *
 */
class CompteAdmin extends Compte {


	/**
	 * Cette mÃ©thode affiche tous les comptes d'utilisateurs de la base de données par ordre décroissants
	 * @param table variable de type objet JTable
	 * @throws SQLException gere les erreurs sql
	 */
	public void getAllComptes(JTable table) throws SQLException{

		DefaultTableModel tableModel = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"id User", "No Employe", "Login", "mdp"

				});
		java.sql.Connection connection = ConnectionFactoryX.getConnection();
		PreparedStatement	preparedStatement = connection.prepareStatement(RequeteStatement.AFFICHE_COMPTES);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			String id  =   resultSet.getString("Id_User");
			String noEmp =   		resultSet.getString("No_emp");
			String login =   	resultSet.getString("login");
			String mdp =   		resultSet.getString("mdp");

			// create a single array of one row's worth of data
			String[] data = {id,noEmp, login, mdp,};

			// and add this row of data into the table model
			tableModel.addRow(data);
		}

		table.setModel(tableModel);
	}

	/**
	 * Cette methode execute l'operation sql d'insertion pour la creation d'un compte utilisateur dans la base de donnees
	 */
	public void addCompte() {
		//getFicheInfos();
		try {	
			String insertCompte = "INSERT INTO compteutilisateur(login,mdp,No_emp) VALUES(?,?,?)";
			java.sql.Connection connection = ConnectionFactoryX.getConnection();
			//PreparedStatement preparedStatement = connection.prepareStatement(QueryStatement.searchQuery);
			PreparedStatement preparedStatement = connection.prepareStatement(insertCompte);
			//preparedStatement.setString(1, idUser);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, hashMdp(getMdp())); // compte.getMdp
			preparedStatement.setString(3, noEmp);
			preparedStatement.executeUpdate();
			JFrame frame = new JFrame("retour");
			JOptionPane.showMessageDialog(frame,"Compte Utilisateur ajoutï¿½ï¿½ï¿½!");
		}
		catch(SQLException e1) {
			JFrame frame = new JFrame("retour");

			JOptionPane.showMessageDialog(frame,"cet utilsateur possï¿½ï¿½ï¿½de dï¿½ï¿½ï¿½jï¿½ï¿½ï¿½ un compte...");
			//JOptionPane.showMessageDialog(frame,e1);
		}	
	}



	/**
	 * methode pour etablir une connexion avec la bdd et compare le mdp saisie avec le mdp dans la bdd
	 * @param login variable de type String qui stock le login de l'utilisateur connectÃ© pour maintenir la session
	 * @param password variable type String qui est le mot de passe de l'utilisateur
	 * @param type vairiable type String; le type de compte/profil d'utilisateur
	 * @param frame object type JFrame
	 * @throws SQLException gere les erreurs sql
	 */
	public void DatabaseConnexionHR(String login, String password, String type, JFrame frame) throws SQLException {
		//CompteAdmin compte = new CompteAdmin();

		//try {
		//Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/moloto", "root",
		//	"");

		//PreparedStatement st = (PreparedStatement) 
		//con.prepareStatement("SELECT Titre, email, mdp FROM employes E, compteutilisateur C WHERE E.No_employe = C.No_emp AND C.email = ? AND C.mdp =? AND E.Titre =? ");

		//st.setString(1, login);
		//st.setString(2, getMdp());
		//st.setString(3, type);


		//ResultSet rs = st.executeQuery();
		//type = rs.getString(1);

		//if (rs.next()){
		//setTypeCompte(rs.getString("typeUtilisateur"));
		//setTypeCompte(rs.getString("typeUtilisateur"));


		//if(type == "HR Manager") {

		//JOptionPane.showMessageDialog(frame, "Bienvenu!! vous vous ï¿½ï¿½ï¿½tes connecter en tant que "+" "+ type);

		//frame.setVisible(false);
		//Menu.main(null);

		//}else if(type == "Administrateur") {

		//compte.typeCompte = rs.getString("Titre");

		//JOptionPane.showMessageDialog(frame, "Bienvenu!! vous vous ï¿½ï¿½ï¿½tes connecter en tant que "+" "+ type);
		//frame.setVisible(false);

		//MenuAdm.main(null);
		//System.out.print(login);
		//MenuHr.main(login);

		//}


		// else  {


		//JOptionPane.showMessageDialog(frame, "Identifiant ou mdp invalide....");

		//}


		//} catch (Exception e) {
		//JOptionPane.showMessageDialog(frame,"Erreur sql");

		//}

		if (type == "login") {
			try {
				Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/moloto", "root",
						"");

				PreparedStatement st = (PreparedStatement) con
						.prepareStatement("SELECT Titre, login, mdp FROM employes E, compteutilisateur C WHERE E.No_employe = C.No_emp AND C.login = ? AND C.mdp =?");

				st.setString(1, login);
				st.setString(2, getMdp());

				ResultSet rs = st.executeQuery();
				if (rs.next()) {
					setTypeCompte(rs.getString("Titre"));

					CompteAdmin c = new CompteAdmin();

					c.DatabaseConnexionHR(login, null, null, frame);

					if(c.getTypeCompte().contains("Administrateur")) {
						JOptionPane.showMessageDialog(frame, "Bienvenu!! vous vous connecté connecté en tant qu' "+" "+ c.getTypeCompte());

						frame.setVisible(false);
						//System.out.print(login);
						MenuAdm.main(login);


						//Ressource humaine
					}else if (c.getTypeCompte().contains("Administrateur")){
						JOptionPane.showMessageDialog(frame, "Bienvenu!! vous vous êres connecté en tant que "+" "+ c.getTypeCompte());

						frame.setVisible(false);
						//System.out.print(login);
						MenuHr.main(login);
						//Vente
					}else if (c.getTypeCompte().contains("Vendeur")) {
						JOptionPane.showMessageDialog(frame, "Bienvenu!! vous vous êres connecté en tant que "+" "+ c.getTypeCompte());

						frame.setVisible(false);
						//System.out.print(login);
						MenuVente.main(login);
					}
					//comptabilite
					else if (c.getTypeCompte().contains("Administrateur")) {
						JOptionPane.showMessageDialog(frame, "Bienvenu!! vous vous êres connecté en tant que "+" "+ c.getTypeCompte());

						frame.setVisible(false);
						//System.out.print(login);
						MenuHr.main(login);
					}

				} else {
					JOptionPane.showMessageDialog(frame, "Identifiant ou mdp invalide....");
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(frame,e);
			}
		} else {
			try {
				Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/moloto", "root",
						"");

				PreparedStatement st = (PreparedStatement) con
						.prepareStatement("SELECT Titre, login, mdp FROM employes E, compteutilisateur C WHERE E.No_employe = C.No_emp AND C.login = ?");

				st.setString(1, login);

				ResultSet rs = st.executeQuery();
				if (rs.next()) {
					setTypeCompte(rs.getString("Titre"));

				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(frame, e);
			}
		}





	}


}

