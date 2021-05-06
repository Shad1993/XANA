package com.example.utilities;
import Fiche.*;
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

import com.example.constants.QueryStatement;
import com.example.db.ConnectionFactory;
import com.example.model.Employe;

import Departements.Dep;


public class DBUtil {
	private static Connection connection;
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet = null;
	
	
	
	//private static List<Employe> employeList = new ArrayList<Employe>();
	
	

	public static void addEmploye(Employe employe) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.ADD_EMPLOYE);
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
	

	
	public static void updateEmploye(Employe employe) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.UPDATE_EMPLOYE);
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
	
	
	
	public static void deleteEmploye(Employe employe) throws SQLException {
		connection = ConnectionFactory.getConnection();
		preparedStatement = connection.prepareStatement(QueryStatement.DELETE_EMPLOYE_QUERY);

		setPreparedStatementProperties(employe.getNo_employe());
		preparedStatement.executeUpdate();

		closeConnections();
	}
	
	
	
	
	
	
	//Affiche élments dans combobox
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public void populate(JComboBox combo) throws SQLException {
				DefaultComboBoxModel Model = (DefaultComboBoxModel) combo.getModel();
				preparedStatement = connection.prepareStatement(QueryStatement.SELECT_DEPT_QUERY);
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
			
	
	// @param variable length array of strings as student properties
	
		private static void setPreparedStatementProperties(String... strArgs) throws SQLException {
			for (int i = 0; i < strArgs.length; i++) {
				preparedStatement.setString(i + 1, strArgs[i]);
			}
		}
	
	
	
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
		
		//Méthode qui ajoute fiche de paie
		public static void addFiche(FicheDePaie fiche) throws SQLException {
			connection = ConnectionFactory.getConnection();
			preparedStatement = connection.prepareStatement(QueryStatement.Add_FICHE);
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
			connection = ConnectionFactory.getConnection();
			preparedStatement = connection.prepareStatement(QueryStatement.DELETE_FICHE);

			setPreparedStatementProperties(fiche.get_idFiche());
			preparedStatement.executeUpdate();

			closeConnections();
		}
		
		//Affiche No Emp dans combocox
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public void afficheNoEmp(JComboBox combox) throws SQLException {
			DefaultComboBoxModel Modelx = (DefaultComboBoxModel) combox.getModel();
			preparedStatement = connection.prepareStatement(QueryStatement.SELECT_NO_EMP);
			resultSet = preparedStatement.executeQuery();

			Modelx.addElement("");
						
			while (resultSet.next()) {
			
			   Modelx.addElement(resultSet.getString("No_employe"));
			  
			    
			    combox.setModel(Modelx);

			
			}
		}
		
	
		public static void addDepartement(Dep dept)throws SQLException {
			
			connection = ConnectionFactory.getConnection();
			preparedStatement = connection.prepareStatement(QueryStatement.ADD_EMPLOYE);
			setPreparedStatementProperties(dept.noDep,
	                                       dept.dep,
	                                       dept.adresse, 	 
	                                       dept.noContact);
			preparedStatement.executeUpdate();

			closeConnections();
			
			
		}

}
