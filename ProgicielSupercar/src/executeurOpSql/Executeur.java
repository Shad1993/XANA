package executeurOpSql;
import Fiche.*;
import connexionBDD.ConnectionFactoryX;
import employes.Employe;
import operationSQL.RequeteStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Departements.Dep;


/**
 * Cette classe exécute certaines opération sql en récupérant les opérations de la calsse CRUDMode et les requêtes de la classe QueryStatement 
 * @author Lionel
 *
 */
public class Executeur {
	private static Connection connection;
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet = null;
	
	
	
	//private static List<Employe> employeList = new ArrayList<Employe>();
	
	

	/**
	 * @param employe objet de la classe mère employé qui récupère les valeurs de ses attributs pour la création d'un employé
	 * @throws SQLException gère les erreurs sql
	 */
	public static void addEmploye(Employe employe) throws SQLException {
		connection = ConnectionFactoryX.getConnection();
		preparedStatement = connection.prepareStatement(RequeteStatement.ADD_EMPLOYE);
		setPreparedStatementProperties(employe.getNom(),
                                       employe.getPrenom(),
                                       employe.getNIC(), 	
                                       employe.getDOB(), 	
                                       employe.getSexe(), 
                                       employe.getAdresse(), 
                                       employe.getEmail(), 
                                       employe.getNo_contact(), 	
                                       employe.getTitre(), 
                                       employe.getSalaire(), 
                                       employe.getDateDembauche(), 
                                       employe.getComission(), 
                                       employe.getNo_dept());
		preparedStatement.executeUpdate();

		closeConnections();
	}
	

	
	/**
	 * Méthode pour les modifications des donnés des employés
	 * @param employe objet de la classe mère employé qui récupère les valeurs de ses attributs pour la modification des donnés d'un employé
	 * @throws SQLException gère les erreurs sql
	 */
	public static void updateEmploye(Employe employe) throws SQLException {
		connection = ConnectionFactoryX.getConnection();
		preparedStatement = connection.prepareStatement(RequeteStatement.UPDATE_EMPLOYE);
		setPreparedStatementProperties(employe.getNom(),
                                       employe.getPrenom(),
                                       employe.getNIC(), 	
                                       employe.getDOB(), 	
                                       employe.getSexe(), 
                                       employe.getAdresse(), 
                                       employe.getEmail(), 
                                       employe.getNo_contact(), 	
                                       employe.getTitre(), 
                                       employe.getSalaire(), 
                                       employe.getDateDembauche(), 
                                       employe.getComission(), 
                                       employe.getNo_employe(), 
                                       employe.getNo_dept());
		preparedStatement.executeUpdate();

		closeConnections();
	}
	
	
	
	/**
	 * Cette méthode récupère l'identifiant de l'employé à supprimmer
	 * @param employe objet de la classe mère employé 
	 * @throws SQLException gère les erreurs sql
	 */
	public static void deleteEmploye(Employe employe) throws SQLException {
		connection = ConnectionFactoryX.getConnection();
		preparedStatement = connection.prepareStatement(RequeteStatement.DELETE_EMPLOYE_QUERY);

		setPreparedStatementProperties(employe.getNo_employe());
		preparedStatement.executeUpdate();

		closeConnections();
	}
	
	
	
	
	
	
	//Affiche élments dans combobox
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public void populate(JComboBox combo) throws SQLException {
				DefaultComboBoxModel Model = (DefaultComboBoxModel) combo.getModel();
				preparedStatement = connection.prepareStatement(RequeteStatement.SELECT_DEPT_QUERY);
				resultSet = preparedStatement.executeQuery();

				Model.addElement("");
							
				while (resultSet.next()) {
					//Model.addElement(resultSet.getString("name"));
				   // String name = resultSet.getString("name");
				   // String age = resultSet.getString("age");
				   Model.addElement(resultSet.getString("No_dept"));
				   //+ "-"+ resultSet.getString("No_dept")
				    
				    
				    combo.setModel(Model);

				
				}
			}
			
	

	
		/**Cette méthode augmente le performance des requêtes sql en la réutilisant
		 * @param strArgs variable longueur du tableau of de type string 
		 * @throws SQLException gère les erreurs sql
		 */
		private static void setPreparedStatementProperties(String... strArgs)throws SQLException {  
			for (int i = 0; i < strArgs.length; i++) {
				preparedStatement.setString(i + 1, strArgs[i]);
			}
		}
	
	
	
		/**
		 * Cette méthode ferme la connexion après l'exécution d'une requête
		 * @throws SQLException gére les erreurs sql
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

	//pour fiche de paie
		
		
		/**
		 * Méthode qui ajoute fiche de paie en récupérant les valeurs des attributs de l'objet saisies
		 * @param fiche objet de la classe mère FicheDepaie
		 * @throws SQLException gère les erreurs sql
		 */
		public static void addFiche(FicheDePaie fiche) throws SQLException {
			connection = ConnectionFactoryX.getConnection();
			preparedStatement = connection.prepareStatement(RequeteStatement.Add_FICHE);
			setPreparedStatementProperties(fiche.get_NoEmp(),// attribut privé
	                                       fiche.get_Bonus(),
	                                       fiche.get_Commission(),
	                                       fiche.get_heureSup(),
	                                       fiche.get_Mois(),
	                                       fiche.get_Deduction()
	                                    
	                                       );
			
			preparedStatement.executeUpdate();

			closeConnections();
		}
		
		

		//Méthode qui éfface constructeur
		public static void deleteFiche(FicheDePaie fiche) throws SQLException {
			connection = ConnectionFactoryX.getConnection();
			preparedStatement = connection.prepareStatement(RequeteStatement.DELETE_FICHE);

			setPreparedStatementProperties(fiche.get_idFiche());
			preparedStatement.executeUpdate();

			closeConnections();
		}
		
		//Affiche No Emp dans combocox
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public void afficheNoEmp(JComboBox combox) throws SQLException {
			DefaultComboBoxModel Modelx = (DefaultComboBoxModel) combox.getModel();
			preparedStatement = connection.prepareStatement(RequeteStatement.SELECT_NO_EMP);
			resultSet = preparedStatement.executeQuery();

			Modelx.addElement("");
						
			while (resultSet.next()) {
			
			   Modelx.addElement(resultSet.getString("No_employe"));
			  
			    
			    combox.setModel(Modelx);

			
			}
		}
		
	
		public static void addDepartement(Dep dept)throws SQLException {
			
			connection = ConnectionFactoryX.getConnection();
			preparedStatement = connection.prepareStatement(RequeteStatement.ADD_EMPLOYE);
			setPreparedStatementProperties(dept.noDep,
	                                       dept.dep,
	                                       dept.adresse, 	 
	                                       dept.noContact);
			preparedStatement.executeUpdate();

			closeConnections();
			
			
		}

}
