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
 * Cette classe ex�cute certaines op�ration sql en r�cup�rant les op�rations de la calsse CRUDMode et les requ�tes de la classe QueryStatement 
 * @author Lionel
 *
 */
public class Executeur {
	private static Connection connection;
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet = null;
	
	
	
	//private static List<Employe> employeList = new ArrayList<Employe>();
	
	

	/**
	 * @param employe objet de la classe m�re employ� qui r�cup�re les valeurs de ses attributs pour la cr�ation d'un employ�
	 * @throws SQLException g�re les erreurs sql
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
	 * M�thode pour les modifications des donn�s des employ�s
	 * @param employe objet de la classe m�re employ� qui r�cup�re les valeurs de ses attributs pour la modification des donn�s d'un employ�
	 * @throws SQLException g�re les erreurs sql
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
	 * Cette m�thode r�cup�re l'identifiant de l'employ� � supprimmer
	 * @param employe objet de la classe m�re employ� 
	 * @throws SQLException g�re les erreurs sql
	 */
	public static void deleteEmploye(Employe employe) throws SQLException {
		connection = ConnectionFactoryX.getConnection();
		preparedStatement = connection.prepareStatement(RequeteStatement.DELETE_EMPLOYE_QUERY);

		setPreparedStatementProperties(employe.getNo_employe());
		preparedStatement.executeUpdate();

		closeConnections();
	}
	
	
	
	
	
	
	//Affiche �lments dans combobox
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
			
	

	
		/**Cette m�thode augmente le performance des requ�tes sql en la r�utilisant
		 * @param strArgs variable longueur du tableau of de type string 
		 * @throws SQLException g�re les erreurs sql
		 */
		private static void setPreparedStatementProperties(String... strArgs)throws SQLException {  
			for (int i = 0; i < strArgs.length; i++) {
				preparedStatement.setString(i + 1, strArgs[i]);
			}
		}
	
	
	
		/**
		 * Cette m�thode ferme la connexion apr�s l'ex�cution d'une requ�te
		 * @throws SQLException g�re les erreurs sql
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
		 * M�thode qui ajoute fiche de paie en r�cup�rant les valeurs des attributs de l'objet saisies
		 * @param fiche objet de la classe m�re FicheDepaie
		 * @throws SQLException g�re les erreurs sql
		 */
		public static void addFiche(FicheDePaie fiche) throws SQLException {
			connection = ConnectionFactoryX.getConnection();
			preparedStatement = connection.prepareStatement(RequeteStatement.Add_FICHE);
			setPreparedStatementProperties(fiche.get_NoEmp(),// attribut priv�
	                                       fiche.get_Bonus(),
	                                       fiche.get_Commission(),
	                                       fiche.get_heureSup(),
	                                       fiche.get_Mois(),
	                                       fiche.get_Deduction()
	                                    
	                                       );
			
			preparedStatement.executeUpdate();

			closeConnections();
		}
		
		

		//M�thode qui �fface constructeur
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
