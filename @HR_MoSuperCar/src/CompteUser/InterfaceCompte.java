package CompteUser;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.example.db.ConnectionFactory;
import com.sun.jdi.connect.spi.Connection;

import Departements.Dep;
import LeMenu.Menu;

import javax.swing.JComboBox;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InterfaceCompte {

	private JFrame frame;
	private JTextField txtidUser;
	private JTextField txtEmail;
	private JTextField txtMdp;
	private final JLayeredPane layeredPane = new JLayeredPane();
	private final JLayeredPane layeredPane_1 = new JLayeredPane();
	private final JScrollPane scrollPane = new JScrollPane();
	private JTable table;
	private static JTextField txtRechercher;
	
	Compte compte = new Compte();
	
	@SuppressWarnings("rawtypes")
	private JComboBox cmbNoEmp;

	private static Connection connection;
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceCompte window = new InterfaceCompte();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfaceCompte() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(144, 238, 144));
		frame.setBounds(100, 100, 921, 490);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		layeredPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		layeredPane.setBounds(10, 97, 367, 193);
		frame.getContentPane().add(layeredPane);
		
		
		
		
		
		
		JLabel lblNewLabel = new JLabel("No Employe");
		lblNewLabel.setBounds(10, 11, 96, 14);
		layeredPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Id User");
		lblNewLabel_1.setBounds(10, 55, 71, 14);
		layeredPane.add(lblNewLabel_1);
		
		txtidUser = new JTextField();
		txtidUser.setBounds(163, 52, 117, 28);
		layeredPane.add(txtidUser);
		txtidUser.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Emaill");
		lblNewLabel_2.setBounds(10, 105, 96, 14);
		layeredPane.add(lblNewLabel_2);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(164, 102, 170, 28);
		layeredPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Mot de passe");
		lblNewLabel_3.setBounds(10, 158, 96, 14);
		layeredPane.add(lblNewLabel_3);
		
		txtMdp = new JTextField();
		txtMdp.setBounds(163, 155, 117, 27);
		layeredPane.add(txtMdp);
		txtMdp.setColumns(10);
		
		cmbNoEmp = new JComboBox();
		

		
		cmbNoEmp.setBounds(163, 7, 120, 28);
		layeredPane.add(cmbNoEmp);
		
		try {
			comboEmp();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		layeredPane_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		
		layeredPane_1.setBounds(10, 291, 367, 149);
		frame.getContentPane().add(layeredPane_1);
		
		JButton btnAjouter = new JButton("Ajouter Compte");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				getCompteInfos();
				
				compte.addDep();
				
				
				
				
				
			}
		});
		btnAjouter.setBounds(10, 13, 347, 23);
		layeredPane_1.add(btnAjouter);
		
		JButton bntEffacerChamps = new JButton("Effacer Champs");
		bntEffacerChamps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null, compte.mdp);

				
				
			}
		});
		bntEffacerChamps.setBounds(10, 47, 347, 23);
		layeredPane_1.add(bntEffacerChamps);
		
		JButton btnModifier = new JButton("Modifier Compte");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				compte.idUser = txtidUser.getText();
			    getCompteInfos();
				 String updateQuery = "UPDATE compteutilisateur SET No_employe =?, email =?, mdp =? WHERE Id_User =?";
				try {
					java.sql.Connection connection = ConnectionFactory.getConnection();
				
			         PreparedStatement  ps= connection.prepareStatement(updateQuery);
			         
			         ps.setString(1, compte.noEmp);
			         ps.setString(2,compte.email);
			         ps.setString(3,compte.mdp);
			         ps.setString(4, compte.idUser);

			       
			         
			         
					//boolean error = false;

					
					
					//if (controleSaisie(error) == false) {
					
			         ps.executeUpdate();
						JFrame frame = new JFrame("retour");
						
						JOptionPane.showMessageDialog(frame,"Compte Utilisateur Modifié)");
						
						compte.getAllComptes(table);
						
						//txtNomDep.setVisible(false);

					
		        //}
					
					
				} catch (SQLException e1) {
					JFrame frame = new JFrame("error");
					JOptionPane.showMessageDialog(frame, e1);
					e1.printStackTrace();
				}
							
				
				
				
				
				
				
				
				
				
				
				
				
			}
		});
		btnModifier.setBounds(10, 81, 347, 23);
		layeredPane_1.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer Compte");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
				compte.idUser = txtidUser.getText();
				//getEmpInfos();
				 String delQuery = "DELETE FROM compteutilisateur WHERE Id_User =?";
				try {
					java.sql.Connection connection = ConnectionFactory.getConnection();
				
			         PreparedStatement  ps= connection.prepareStatement(delQuery);

			         ps.setString(1, compte.idUser);
			        // ps.setString(2,Prenom);
			        // ps.setString(3, NIC);
			        // ps.setString(4,DOB);
			        // ps.setString(5, Sexe);
			        // ps.setString(6,Adresse);
			        // ps.setString(7,Email);
			        // ps.setString(8, NoContact);
			        // ps.setString(9,Titre);
			        // ps.setString(10, Salaire);
			        // ps.setString(11,Embauche);
			        // ps.setString(12,Commission);
			        // ps.setString(13,Dep);
			        // ps.setString(14,NoEmp);
			         
			         
					//boolean error = false;

					
					
					//if (controleSaisie(error) == false) {
					
			         ps.executeUpdate();
						JFrame frame = new JFrame("retour");
						
						JOptionPane.showMessageDialog(frame,"compte utilisateur supprimé");
						
                          compte.getAllComptes(table);	// mise à jour de la table > affichage					

					
		        //}
					
					
				} catch (SQLException e1) {
					JFrame frame = new JFrame("error");
					JOptionPane.showMessageDialog(frame, e1);
					e1.printStackTrace();
				}
					
				
				
				
				
				
				
				
				
				
				
			}
		});
		btnSupprimer.setBounds(10, 115, 347, 23);
		layeredPane_1.add(btnSupprimer);
		
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.setBounds(566, 55, 142, 31);
		frame.getContentPane().add(btnRechercher);
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(387, 97, 508, 343);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				int i = table.getSelectedRow();
		        TableModel model = table.getModel();
		        
		          //Display Slected Row In JTexteFields

		        txtidUser.setText(model.getValueAt(i,0).toString());
		        
			    cmbNoEmp.setSelectedItem(model.getValueAt(i,1).toString());
		        
		        txtEmail.setText(model.getValueAt(i,2).toString());

		        
		        txtMdp.setText(model.getValueAt(i,3).toString());

				
				
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		
		
          Compte compte = new Compte();
		
		    try {
			    compte.getAllComptes(table);
		    } catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		 }
		
		
		
		
		
		
		
		txtRechercher = new JTextField();
		txtRechercher.setBounds(387, 55, 169, 29);
		frame.getContentPane().add(txtRechercher);
		txtRechercher.setColumns(10);
		
		JButton btnNewButton = new JButton("Retour");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				InterfaceCompte.this.frame.setVisible(false);
				Menu.main(null);
				
				
			}
		});
		btnNewButton.setBounds(782, 11, 113, 31);
		frame.getContentPane().add(btnNewButton);
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					findCompte();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
	}
	

	@SuppressWarnings("unchecked")
	public void comboEmp() throws SQLException{
		
		 String searchQuery = "SELECT No_employe FROM employes";
		java.sql.Connection	 connection = ConnectionFactory.getConnection();
			//PreparedStatement preparedStatement = connection.prepareStatement(QueryStatement.searchQuery);
	        Statement preparedStatement = connection.createStatement();

			@SuppressWarnings("unused")
			ResultSet resultSet = preparedStatement.executeQuery(searchQuery);
			
			cmbNoEmp.addItem("");
		try {
			
			while (resultSet.next()) {
				
				   cmbNoEmp.addItem(resultSet.getString("No_employe"));
				   
			}
		}				
			
		catch(SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
					
			}	
		
				    
	       
		
	}
	
	
	
	
	public void getCompteInfos() {
		
		//dept.noDep  = txtnoDep.getText();
	    compte.idUser =	txtidUser.getText();
		compte.email  = txtEmail.getText();
		compte.mdp  = txtMdp.getText();
		compte.noEmp = cmbNoEmp.getSelectedItem().toString();
		
			
	}
	
	// Méthode qui recoit valeur rechercher par paramètre (val)
				public static ArrayList<Compte> allComptes(String val) throws SQLException {
					//val ="10";
			        String searchQuery = "SELECT* FROM compteutilisateur WHERE CONCAT (`Id_User`,`email`,`mdp`,`No_employe`) LIKE'%"+val+"%'";
					java.sql.Connection connection = ConnectionFactory.getConnection();
					//PreparedStatement preparedStatement = connection.prepareStatement(QueryStatement.searchQuery);
			      java.sql.Statement  preparedStatement = connection.createStatement();

					ResultSet resultSet = preparedStatement.executeQuery(searchQuery);
					
					
					ArrayList<Compte> compteList = new ArrayList<Compte>();
					
				if (!resultSet.isBeforeFirst() ) { // si pas de résultat
					
					JFrame frame = new JFrame("0 résultat");
					JOptionPane.showMessageDialog(frame,"Aucun résultat obtenu..");
					txtRechercher.requestFocusInWindow();
					
					txtRechercher.requestFocusInWindow(); //place curseur dans txtbox rechercher
					txtRechercher.setText("");//effacer textbox recherche

					
				}else {
					
					while (resultSet.next()) {
						Compte compte = new Compte();
						
						String id = resultSet.getString(1);
						String email = resultSet.getString(2);
						String mdp = resultSet.getString(3);
						String no_Emp = resultSet.getString(4);
						
						compte.idUser = id;
						compte.email = email;
						compte.mdp = mdp;
						compte.noEmp = no_Emp;
						
				

							
						compteList.add(compte);
					}
					
					
				}

					return compteList;
					
				   
				}
	
	
	
	
				 public void findCompte() throws SQLException
				 
				    {
				        ArrayList<Compte> compte = allComptes(txtRechercher.getText());
				        DefaultTableModel model = new DefaultTableModel();
				        model.setColumnIdentifiers(new Object[]{"Id compte", "Email", "MDP","No Emp"});
				        Object[] row = new Object[4];
				        
				        for(int i = 0; i < compte.size(); i++)
				        {
				            row[0] = compte.get(i).idUser;
				            row[1] = compte.get(i).email;
				            row[2] = compte.get(i).mdp;
				            row[3] = compte.get(i).noEmp;
				           
				          
				            model.addRow(row);
				        }
				       table.setModel(model);
				       
				    }
			
			
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
