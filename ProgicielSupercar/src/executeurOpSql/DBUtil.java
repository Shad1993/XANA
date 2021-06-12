package executeurOpSql;

/*
 * @author Bryan Bataluna
 * @edited-by Sam Ong
 * 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import operationSQL.QueryStatement;
import connexionBDD.ConnectionFactory;
import employes.Client;
import employes.Entrepot;
import employes.Vente;
import employes.Voiture;


public class DBUtil {

	private static Connection connection;
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet = null;
	private static List<Vente> venteList = new ArrayList<Vente>();
	static String ids;
	/**
	 * 
	 * @param vente
	 * @throws SQLException
	 */
	//Fonction pour la page de Vente
	public static void addVente(Vente vente) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.ADD_VENTE);

		setPreparedStatementPropertiesAddV( 
				vente.getNoClient(),
				vente.getNo_Emp(), 
				vente.getQuantite(),
				vente.getNoVoiture());


		preparedStatement.executeUpdate();

		closeConnections();
	}
	/**
	 * 
	 * @param vente
	 * @throws SQLException
	 */
	public static void updateVente(Vente vente) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.UPDATE);

		setPreparedStatementPropertiesUpdateVente(vente.getNo_vente(), 
				vente.getQuantite(),
				vente.getNoVoiture(), 
				vente.getNo_Emp(), 
				vente.getNoClient(),
				vente.getStatut());
		preparedStatement.executeUpdate();

		closeConnections();
	}
	/**
	 * 
	 * @param vente
	 * @throws SQLException
	 */
	public static void deleteVente(Vente vente) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.DELETE);

		setPreparedStatementProperties(vente.getNo_vente() );
		preparedStatement.executeUpdate();

		closeConnections();
	}
	/**
	 * 
	 * @param table
	 * @throws SQLException
	 */
	public void getVente(JTable table) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.LIST);

		resultSet = preparedStatement.executeQuery();

		venteList.clear();
		DefaultTableModel tableModel = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Statut","No Vente", "Numero Client","Nom Client","Numero Employeé","Nom Employeé","Quantite","Numero voiture", "Marque", "Modele"
				});
		while (resultSet.next()) {
			Vente vente = new Vente();
			String statut = (resultSet.getString("vente.Statut"));

			String idVente = Integer.toString(resultSet.getInt("No_vente"));
			String idClient = Integer.toString(resultSet.getInt("NoClient"));
			String nomClient = (resultSet.getString("clients.Nom"));
			String idEmp = Integer.toString(resultSet.getInt("vente.No_Emp"));
			String nomEmp = (resultSet.getString("employes.Nom"));
			String quantite= Integer.toString(resultSet.getInt("Quantite"));
			String voiture = Integer.toString(resultSet.getInt("NoVoiture"));
			String marque = (resultSet.getString("voitures.Marque"));
			String modele = (resultSet.getString("voitures.Modele"));
			venteList.add(vente);
			String[] data = {statut,idVente, idClient, nomClient, idEmp, nomEmp,quantite,voiture,marque,modele};
			System.out.println("working?");
			// and add this row of data into the table model
			tableModel.addRow(data);
		}
		table.setModel(tableModel);
	}

	/**
	 * 
	 * @param entrepot
	 * @return
	 * @throws SQLException
	 */
	//Fonction pour la page Entrepot
	public static boolean addEntrepot(Entrepot entrepot) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.ADD_ENTREPOT);

		setPreparedStatementProperties( 
				entrepot.getNom(),
				entrepot.getAdresse(),
				entrepot.getEmail());

		preparedStatement.executeUpdate();

		closeConnections();
		return true;
	}
	/**
	 * 
	 * @param entrepot
	 * @throws SQLException
	 */
	public static void updateEntrepot(Entrepot entrepot) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.UPDATE_ENTREPOT);

		setPreparedStatementPropertiesUpdateEntrepot( 
				entrepot.getId_Entrepot(),
				entrepot.getNom(),
				entrepot.getAdresse(), 
				entrepot.getEmail());
		preparedStatement.executeUpdate();
		closeConnections();
	}
	/**
	 * 
	 * @param entrepot
	 * @throws SQLException
	 */
	public static void deleteEntrepot(Entrepot entrepot) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.DELETE_ENTREPOT);

		setPreparedStatementProperties(entrepot.getId_Entrepot() );
		preparedStatement.executeUpdate();

		closeConnections();
	}
	/**
	 * 
	 * @param table
	 * @throws SQLException
	 */
	public void getEntrepot(JTable table) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.LIST_ENTREPOT);
		resultSet = preparedStatement.executeQuery();
		DefaultTableModel tableModel = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"No Entrepot", "Nom ","Adresse","Email"
				});
		while (resultSet.next()) {

			String idEnt = Integer.toString(resultSet.getInt("Id_Entrepot"));
			String nomEnt = (resultSet.getString("Nom"));
			String adresseEnt= (resultSet.getString("Adresse"));
			String emailEnt = (resultSet.getString("Email"));
			String[] data = {idEnt, nomEnt, adresseEnt, emailEnt};
			System.out.println("working?");
			// and add this row of data into the table model
			tableModel.addRow(data);
		}
		table.setModel(tableModel);
	}


	//Fonction pour la page Voiture
	/**
	 * 
	 * @param voiture
	 * @param entrepot
	 * @return
	 * @throws SQLException
	 */
	public static boolean addVoiture(Voiture voiture, int entrepot) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.ADD_VOITURE);

		setPreparedStatementPropertiesAjouterVoiture( 
				voiture.getMarque(),
				voiture.getModele() ,
				voiture.getPays_dorigine() ,
				voiture.getIntervalle_de_service() ,
				voiture.getCouleur() ,
				voiture.getGarentie() ,
				voiture.getTransmission() ,
				voiture.getMoteur() ,
				voiture.getPrix() ,
				voiture.getDateMiseEnVente(),
				entrepot

				);

		preparedStatement.executeUpdate();

		closeConnections();
		return true;
	}
	/**
	 * 
	 * @param voiture
	 * @throws SQLException
	 */
	public static void updateVoiture(Voiture voiture) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.UPDATE_VOITURE);

		setPreparedStatementPropertiesUpdateVoiture( 
				voiture.getNo_voiture(),
				voiture.getMarque(),
				voiture.getModele() ,
				voiture.getPays_dorigine() ,
				voiture.getIntervalle_de_service() ,
				voiture.getCouleur() ,
				voiture.getGarentie() ,
				voiture.getTransmission() ,
				voiture.getMoteur() ,
				voiture.getPrix() ,
				voiture.getDateMiseEnVente()
				);
		preparedStatement.executeUpdate();
		closeConnections();
	}

	/**
	 * 
	 * @param voiture
	 * @throws SQLException
	 */
	public static void deleteVoiture(Voiture voiture) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.DELETE_VOITURE);

		setPreparedStatementProperties(voiture.getNo_voiture() );
		preparedStatement.executeUpdate();

		closeConnections();
	}
	/**
	 * 
	 * @param table
	 * @throws SQLException
	 */
	public void getVoiture(JTable table) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.LIST_VOITURE);

		resultSet = preparedStatement.executeQuery();


		DefaultTableModel tableModel = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Id ", "Marque","Modele","Pays D'origine","Service","Couleur","Garantie","Transmission","Moteur","Prix", "Date Mise en vente"
				});
		while (resultSet.next()) {

			String id = Integer.toString(resultSet.getInt("No_voiture"));
			String marque = (resultSet.getString("Marque"));
			String modele= (resultSet.getString("Modele"));
			String pays = (resultSet.getString("Pays_dorigine"));
			String service = (resultSet.getString("Intervalle_de_service"));
			String couleur = (resultSet.getString("Couleur"));
			String transmission =(resultSet.getString("Transmission"));
			String garantie =(resultSet.getString("Garantie"));
			String moteur = (resultSet.getString("Moteur"));
			String prix = (resultSet.getString("Prix"));
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
			String date = formatter.format(resultSet.getDate("DateMiseEnVente"));  
			String[] data = {id, marque, modele, pays, service, couleur, transmission,garantie, moteur, prix, date};
			// and add this row of data into the table model
			tableModel.addRow(data);
		}
		table.setModel(tableModel);
	}
	/**
	 * 
	 * @param idValue
	 * @return
	 * @throws SQLException
	 */
	public static String getVoitureComboSelected(int idValue) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.LIST_VOITURECOMBOBOXSELECT);

		setPreparedStatementProperties(idValue);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			ids = resultSet.getString(1);
		}
		return  ids;

	}

	// Fonction pour la page Client
	/**
	 * 
	 * @param client
	 * @return
	 * @throws SQLException
	 */
	public static boolean addClient(Client client) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.ADD_CLIENT);

		setPreparedStatementPropertiesAjouterClient( 
				client.getNo_client(),
				client.getNom(),
				client.getPrenom(),
				client.getNCI(),
				client.getAdresse(),
				client.getAdresseEmail(),
				client.getNo_contact()


				);

		preparedStatement.executeUpdate();

		closeConnections();
		return true;
	}
	/**
	 * 
	 * @param client
	 * @throws SQLException
	 */
	public static void updateClient(Client client) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.UPDATE_CLIENT);

		setPreparedStatementPropertiesUpdateClient( 
				client.getNo_client(),
				client.getNom(),
				client.getPrenom(),
				client.getNCI(),
				client.getAdresse(),
				client.getAdresseEmail(),
				client.getNo_contact()
				);
		preparedStatement.executeUpdate();
		closeConnections();
	}
	/**
	 * 
	 * @param client
	 * @throws SQLException
	 */
	public static void deleteClient(Client client) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.DELETE_CLIENT);

		setPreparedStatementProperties(client.getNo_client() );
		preparedStatement.executeUpdate();

		closeConnections();
	}

	/**
	 * 
	 * @param table
	 * @throws SQLException
	 */
	public void getClient(JTable table) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.LIST_CLIENT);

		resultSet = preparedStatement.executeQuery();


		DefaultTableModel tableModel = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"No Client", "Nom ","Prenom","NCI","Adresse","Email","No contact"
				});
		while (resultSet.next()) {

			String id = Integer.toString(resultSet.getInt("No_client"));
			String nom = (resultSet.getString("Nom"));
			String prenom= (resultSet.getString("Prenom"));
			String NCI = (resultSet.getString("NCI"));
			String adresse = (resultSet.getString("Adresse"));
			String email = (resultSet.getString("AdresseEmail"));
			String noContact = Integer.toString(resultSet.getInt("No_contact"));

			String[] data = {id, nom, prenom, NCI, adresse, email, noContact};
			System.out.println("working?");

			// and add this row of data into the table model
			tableModel.addRow(data);
		}
		table.setModel(tableModel);
	}
	/**
	 * 
	 * @param idValue
	 * @return
	 * @throws SQLException
	 */
	public static String getClientComboSelected(int idValue) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.LIST_CLIENTCOMBOBOXSELECT);

		setPreparedStatementProperties(idValue);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			ids = resultSet.getString(1);
		}
		return  ids;

	}




	/**
	 * 
	 * @param table
	 * @throws SQLException
	 */
	public void getStock(JTable table) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.LIST_STOCK);

		resultSet = preparedStatement.executeQuery();

		DefaultTableModel tableModel = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Id Entrepot", "Nom Entrepot ","Email","Numero voiture","Marque","Modele"
				});
		while (resultSet.next()) {

			String idEntrepot = Integer.toString(resultSet.getInt("entrepot.Id_Entrepot"));
			String nom = (resultSet.getString("entrepot.Nom"));
			String email= (resultSet.getString("entrepot.Email"));
			String noEntrepot = Integer.toString(resultSet.getInt("voitures.No_voiture"));
			String marque = (resultSet.getString("voitures.Marque"));
			String modele = (resultSet.getString("voitures.Modele"));
			String[] data = {idEntrepot, nom, email, noEntrepot, marque, modele};

			// and add this row of data into the table model
			tableModel.addRow(data);
		}
		table.setModel(tableModel);
	}

	//Archive vente
	/**
	 * 
	 * @param table
	 * @throws SQLException
	 */
	public void getArchiveVente(JTable table) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.LIST_ARCHIVEVENTE);

		resultSet = preparedStatement.executeQuery();

		DefaultTableModel tableModel = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Statut","No Vente", "Numero Client","Nom Client","Numero Employeé","Nom Employeé","Quantite","Numero voiture", "Marque", "Modele"
				});
		while (resultSet.next()) {
			Vente vente = new Vente();
			String statut = (resultSet.getString("vente.Statut"));
			String idVente = Integer.toString(resultSet.getInt("No_vente"));
			String idClient = Integer.toString(resultSet.getInt("NoClient"));
			String nomClient = (resultSet.getString("clients.Nom"));
			String idEmp = Integer.toString(resultSet.getInt("vente.No_Emp"));
			String nomEmp = (resultSet.getString("employes.Nom"));
			String quantite= Integer.toString(resultSet.getInt("Quantite"));
			String voiture = Integer.toString(resultSet.getInt("NoVoiture"));
			String marque = (resultSet.getString("voitures.Marque"));
			String modele = (resultSet.getString("voitures.Modele"));
			venteList.add(vente);
			String[] data = {statut,idVente, idClient, nomClient, idEmp, nomEmp,quantite,voiture,marque,modele};
			System.out.println("working?");
			// and add this row of data into the table model
			tableModel.addRow(data);
		}
		table.setModel(tableModel);
	}

	/**
	 * 
	 * @param idValue
	 * @return
	 * @throws SQLException
	 */
	public static String getEmployeComboSelected(int idValue) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.LIST_EMPCOMBOBOXSELECT);

		setPreparedStatementProperties(idValue);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			ids = resultSet.getString(1);
		}
		return  ids;

	}
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Vector getEntrepotComboBox() throws SQLException {

		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.LIST_ENTREPOTCOMBOBOX);
		resultSet = preparedStatement.executeQuery();
		Vector v = new Vector();

		while (resultSet.next()) {
			ids = resultSet.getString(1);
			v.add(ids);
		}
		return  v;
	}
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Vector getClientComboBox() throws SQLException {

		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.LIST_CLIENTCOMBOBOX);
		resultSet = preparedStatement.executeQuery();
		Vector v = new Vector();

		while (resultSet.next()) {
			ids = resultSet.getString(1);
			v.add(ids);
		}
		return  v;


	}
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Vector getVoitureComboBox() throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.LIST_VOITURECOMBOBOX);
		resultSet = preparedStatement.executeQuery();
		Vector v = new Vector();
		while (resultSet.next()) {
			ids = resultSet.getString(1);
			v.add(ids);
		}
		return  v;
	}
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Vector getEmpComboBox() throws SQLException {

		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.LIST_EMPCOMBOBOX);
		resultSet = preparedStatement.executeQuery();
		Vector v = new Vector();

		while (resultSet.next()) {
			ids = resultSet.getString(1);
			v.add(ids);
		}
		return  v;


	}
	// Tableau de Bord
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static String getTBattente() throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.NO_ATTENTE);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			String s = resultSet.getString(1);
			return  s;
		}
		return  null;
	}
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static String getTBcours() throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.NO_COURS);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			String s = resultSet.getString(1);
			return  s;
		}
		return  null;
	}
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static String getTBterminez() throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.NO_TERMINEZ);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			String s = resultSet.getString(1);
			return  s;
		}
		return  null;
	}
	public static String getPrix(int i) throws SQLException {

		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.LIST_PRIXCOMBOBOXSELECT);
		resultSet = preparedStatement.executeQuery();
		System.out.println("nice");
		setPreparedStatementPropertiesAjouterVoiture( i);

		preparedStatement.executeUpdate();
		while (resultSet.next()) {
			System.out.println("nice2");

			ids = resultSet.getString(1);
		}
		System.out.println("nice43");

		return  ids;


	}

	private static void setPreparedStatementPropertiesAjouterVoiture(int i) throws SQLException {
		preparedStatement.setInt( 1, i);
		
	}
	/**
	 * 
	 * @throws SQLException
	 */
	private static void closeConnections() throws SQLException {
		if (resultSet != null) {
			resultSet.close();
		}
		if (preparedStatement != null) {
			preparedStatement.close();
		}
		if (connection != null) {
			connection.close();
		}
	}

	// @param variable length array of strings as student properties
	private static void setPreparedStatementProperties(int... strArgs) throws SQLException {
		for (int i = 0; i < strArgs.length; i++) {
			preparedStatement.setInt(i + 1, strArgs[i]);
		}
	}
	// @param variable length array of strings as student properties
	private static void setPreparedStatementProperties(String... strArgs) throws SQLException {
		for (int i = 0; i < strArgs.length; i++) {
			preparedStatement.setString(i + 1, strArgs[i]);
		}
	}

	private static void setPreparedStatementPropertiesAddV(int noClient, int no_Emp, int quantite, int noVoiture) throws SQLException {

		preparedStatement.setInt( 1, noClient);
		preparedStatement.setInt( 2, no_Emp);
		preparedStatement.setInt( 3, quantite);
		preparedStatement.setInt( 4, noVoiture);


	}
	private static void setPreparedStatementPropertiesAjouterVoiture( String marque, String modele,
			String pays_dorigine, String intervalle_de_service, String couleur, String garantie, String transmission,
			String moteur, int prix, Date dateMiseEnVente, int entrepot) throws SQLException {
		preparedStatement.setString( 1, marque);
		preparedStatement.setString( 2, modele);
		preparedStatement.setString( 3, pays_dorigine);
		preparedStatement.setString( 4, intervalle_de_service);
		preparedStatement.setString( 5, couleur);
		preparedStatement.setString( 6, garantie);
		preparedStatement.setString( 7, transmission);		
		preparedStatement.setString( 8, moteur);
		preparedStatement.setInt( 9, prix);
		preparedStatement.setDate( 10, (java.sql.Date) dateMiseEnVente);	
		preparedStatement.setInt( 11, entrepot);
	}
	private static void setPreparedStatementPropertiesUpdateVoiture(int i, String marque, String modele, String pays_dorigine,
			String intervalle_de_service, String couleur, String garentie, String transmission, String moteur, int prix,
			Date dateMiseEnVente) throws SQLException {
		// TODO Auto-generated method stub
		preparedStatement.setInt( 1, i);
		preparedStatement.setString( 2, marque);
		preparedStatement.setString( 3, modele);
		preparedStatement.setString( 4, pays_dorigine);
		preparedStatement.setString( 5, intervalle_de_service);
		preparedStatement.setString( 6, couleur);
		preparedStatement.setString( 7, garentie);
		preparedStatement.setString( 8, transmission);		
		preparedStatement.setString( 9, moteur);
		preparedStatement.setInt( 10, prix);
		preparedStatement.setDate( 11, (java.sql.Date) dateMiseEnVente);
		preparedStatement.setInt( 12, i);
	}

	private static void setPreparedStatementPropertiesUpdateClient( int numClient, String nom, String prenom, String nci,
			String adresse, String adresseEmail, int no_contact) throws SQLException {
		// TODO Auto-generated method stub
		preparedStatement.setInt( 1, numClient);
		preparedStatement.setString( 2, nom);
		preparedStatement.setString( 3, prenom);
		preparedStatement.setString( 4, nci);
		preparedStatement.setString( 5, adresse);
		preparedStatement.setString( 6, adresseEmail);
		preparedStatement.setInt( 7, no_contact);
		preparedStatement.setInt( 8, numClient);

	}
	private static void setPreparedStatementPropertiesUpdateVente(int no_vente, int quantite, int noVoiture, int no_Emp,
			int noClient, String statut) throws SQLException {
		preparedStatement.setInt( 1, no_vente);
		preparedStatement.setInt( 2, quantite);
		preparedStatement.setInt( 3, noVoiture);
		preparedStatement.setInt( 4, no_Emp);
		preparedStatement.setInt( 5, noClient);
		preparedStatement.setString( 6, statut);
		preparedStatement.setInt( 7, no_vente);

	}

	private static void setPreparedStatementPropertiesAjouterClient(int no_client, String nom, String prenom, String nci,
			String adresse, String adresseEmail, int no_contact) throws SQLException {
		preparedStatement.setString( 1, nom);
		preparedStatement.setString( 2, prenom);
		preparedStatement.setString( 3, nci);
		preparedStatement.setString( 4, adresse);
		preparedStatement.setString( 5, adresseEmail);
		preparedStatement.setInt( 6, no_contact);

	}
	private static void setPreparedStatementPropertiesUpdateEntrepot(int id_Entrepot, String nom, String adresse, String email) throws SQLException {
		// TODO Auto-generated method stub
		preparedStatement.setInt( 1, id_Entrepot);
		preparedStatement.setString( 2, nom);
		preparedStatement.setString( 3, adresse);
		preparedStatement.setString( 4, email);
		preparedStatement.setInt( 5, id_Entrepot);
	}

}