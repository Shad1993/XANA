package CompteUser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.example.constants.QueryStatement;
import com.example.db.ConnectionFactory;

public class Compte {
	
	public String idUser;
	public String noEmp;
	public String email;
	public String mdp;
	
	
	public Compte() {
		
		
		
		
	}
	
	public Compte(String idUser,String noEmp, String email,String mdp) {
		this.idUser = idUser;
		this.noEmp = noEmp;
		this.email = email;
		this.mdp = mdp;
		
		
	}
	
	
	public void getAllComptes(JTable table) throws SQLException{

		DefaultTableModel tableModel = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"id User", "No Employe", "Email", "mdp"
					
				});
		java.sql.Connection connection = ConnectionFactory.getConnection();
		PreparedStatement	preparedStatement = connection.prepareStatement(QueryStatement.AFFICHE_COMPTES);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while (resultSet.next()) {
         String id  =   resultSet.getString("Id_User");
		 String noEmp =   		resultSet.getString("No_employe");
		 String email =   	resultSet.getString("email");
		 String mdp =   		resultSet.getString("mdp");

		    // create a single array of one row's worth of data
		  String[] data = {id,noEmp, email, mdp,};
                        
		    // and add this row of data into the table model
		    tableModel.addRow(data);
		}
		
		table.setModel(tableModel);
      }
	
	public void addCompte() {
		Compte compte = new Compte();
		 //getFicheInfos();
			
			try {	
				 String insertCompte = "INSERT INTO compteutilisateur(email,mdp,No_employe) VALUES(?,?,?)";
					java.sql.Connection connection = ConnectionFactory.getConnection();
					//PreparedStatement preparedStatement = connection.prepareStatement(QueryStatement.searchQuery);
			     
					
					PreparedStatement preparedStatement = connection.prepareStatement(insertCompte);
					//preparedStatement.setString(1, idUser);
					preparedStatement.setString(1, email);
					preparedStatement.setString(2, mdp); // compte.getMdp
					preparedStatement.setString(3, noEmp);
					preparedStatement.executeUpdate();


					
					JFrame frame = new JFrame("retour");
					JOptionPane.showMessageDialog(frame,"Compte Utilisateur ajouté!");

	
				}
				
				catch(SQLException e1) {
				JOptionPane.showMessageDialog(null, e1);
						
				}	
			
		
	}

	public static void main(Object object) {
		// TODO Auto-generated method stub
		 Compte.main(null);
	}
	
	

}
