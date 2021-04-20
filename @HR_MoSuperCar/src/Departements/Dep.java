package Departements;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.constants.CRUDMode;
import com.example.db.ConnectionFactory;
import com.example.model.Employe;
import com.sun.jdi.connect.spi.Connection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.example.constants.QueryStatement;




public class Dep {
	
	//attributs public
	public String noDep;
	public String dep;
	public String adresse;
	public String noContact;
	
	
    public Dep() {
		
		
	}
    
    public Dep(String noDep,String dep,String adresse,String noContact) {
    	//constructeur
    	this.noDep = noDep;
    	this.dep = dep;
    	this.adresse = adresse;
    	this.noContact = noContact;
		
	}
	
    public void getAllDep(JTable table) throws SQLException{

		DefaultTableModel tableModel = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"No Departement", "Nom", "No contact", "Adressse"
					
				});
		java.sql.Connection connection = ConnectionFactory.getConnection();
		PreparedStatement	preparedStatement = connection.prepareStatement(QueryStatement.AFFICHER_DEPARTEMENT);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while (resultSet.next()) {
         String noDep  =   resultSet.getString("No_dept");
		 String nomDep =   		resultSet.getString("Nom_dept");
		 String adresse  =   	resultSet.getString("NoContact");
		 String noContact =   		resultSet.getString("AdresseDep");

		    // create a single array of one row's worth of data
		  String[] data = {noDep,nomDep, adresse, noContact,};
                        
		    // and add this row of data into the table model
		    tableModel.addRow(data);
		}
		
		table.setModel(tableModel);
      }
    
    public void addDep() {
		
		 //getFicheInfos();
			
			try {	
				 String insertDep = "INSERT INTO departement(No_dept,Nom_dept,NoContact,AdresseDep) VALUES(?,?,?,?)";
					java.sql.Connection connection = ConnectionFactory.getConnection();
					//PreparedStatement preparedStatement = connection.prepareStatement(QueryStatement.searchQuery);
			     
					
					PreparedStatement preparedStatement = connection.prepareStatement(insertDep);
					preparedStatement.setString(1, noDep);
					preparedStatement.setString(2, dep);
					preparedStatement.setString(3, noContact);
					preparedStatement.setString(4, adresse);
					preparedStatement.executeUpdate();


					
					JFrame frame = new JFrame("retour");
					JOptionPane.showMessageDialog(frame,"Departement ajouté");

	
				}
				
				catch(SQLException e1) {
				JOptionPane.showMessageDialog(null, e1);
						
				}	
			
		
	}
	
	
}
