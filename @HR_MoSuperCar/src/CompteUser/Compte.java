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

import com.example.constants.QueryStatement;
import com.example.db.ConnectionFactory;

import CompteUser.Compte;
import LeMenu.Menu;
import LeMenu.MenuAdm;

public class Compte {
	
	public String idUser;
	public String noEmp;
	public String email;
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
	
	public String getMdp() {
		return mdp;
	}

	
	public void setMdp(String mdpSaisie) {
		this.mdp = mdpSaisie;
	}
	
	public String getTypeCompte() {
		return typeCompte;
	}

	
	public void setTypeCompte(String typeCompte) {
		this.typeCompte = typeCompte;
	}

	
	
	//Methode d'encrytion SHA Utilisée
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


	 // Class CompteUtilisateurAdmin herite de la classe mère CompteUtilisateur
 class CompteAdmin extends Compte {
		
	
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
		 String noEmp =   		resultSet.getString("No_emp");
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
		
		 //getFicheInfos();
			
			try {	
				 String insertCompte = "INSERT INTO compteutilisateur(email,mdp,No_emp) VALUES(?,?,?)";
					java.sql.Connection connection = ConnectionFactory.getConnection();
					//PreparedStatement preparedStatement = connection.prepareStatement(QueryStatement.searchQuery);
					
					PreparedStatement preparedStatement = connection.prepareStatement(insertCompte);
					//preparedStatement.setString(1, idUser);
					
					preparedStatement.setString(1, email);
					preparedStatement.setString(2, hashMdp(getMdp())); // compte.getMdp
					preparedStatement.setString(3, noEmp);
					preparedStatement.executeUpdate();


					
					JFrame frame = new JFrame("retour");
					JOptionPane.showMessageDialog(frame,"Compte Utilisateur ajouté!");

	
				}
				
				catch(SQLException e1) {
				JFrame frame = new JFrame("retour");

				//JOptionPane.showMessageDialog(frame,"cet utilsateur possède déjà un compte...");
				JOptionPane.showMessageDialog(frame,e1);

						
				}	
			
		
	}
	
	
	// methode pour etablir une connexion avec la bdd et comparer le mdp saisie avec le mdp dans la bdd
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
					    	
					    	//JOptionPane.showMessageDialog(frame, "Bienvenu!! vous vous êtes connecter en tant que "+" "+ type);
						
					    	//frame.setVisible(false);
					    	//Menu.main(null);
						
						//}else if(type == "Administrateur") {

							//compte.typeCompte = rs.getString("Titre");

							//JOptionPane.showMessageDialog(frame, "Bienvenu!! vous vous êtes connecter en tant que "+" "+ type);
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
							.prepareStatement("SELECT Titre, email, mdp FROM employes E, compteutilisateur C WHERE E.No_employe = C.No_emp AND C.email = ? AND C.mdp =?");

					st.setString(1, login);
					st.setString(2, getMdp());

					ResultSet rs = st.executeQuery();
					if (rs.next()) {
						setTypeCompte(rs.getString("Titre"));
						frame.setVisible(false);
						System.out.print(login);
						MenuHr.main(login);
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
							.prepareStatement("SELECT Titre, email, mdp FROM employes E, compteutilisateur C WHERE E.No_employe = C.No_emp AND C.email = ?");

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
	
