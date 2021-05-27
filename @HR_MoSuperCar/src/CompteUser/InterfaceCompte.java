package CompteUser;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.example.db.ConnectionFactory;
import com.sun.jdi.connect.spi.Connection;


import javax.swing.JComboBox;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Pattern;
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
import java.awt.Font;
import javax.swing.JPanel;


public class InterfaceCompte {

	private JFrame frame;
	private JTextField txtidUser;
	private JTextField txtLogin;
	private JPasswordField txtMdp;

	private final JLayeredPane layeredPane = new JLayeredPane();
	private final JLayeredPane layeredPane_1 = new JLayeredPane();
	private final JScrollPane scrollPane = new JScrollPane();
	private JTable table;
	private static JTextField txtRechercher;
	public String mdp;
	public String confirmerMdp;
	public String noEmp;
	private JButton btnModifier;
	private JButton btnAjouter;

	
	CompteAdmin compte = new CompteAdmin ();
	
	   private static final String EMAIL_REGEX =
	            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*" +
	            "@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	 
	    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
	
	   
	    private static final String PASSWORD_REGEX =
	            "^(?=.*[0-9]).{6,}$";
	     
	        private static final Pattern PASSWORD_PATTERN =
	                Pattern.compile(PASSWORD_REGEX);
	
	
	@SuppressWarnings("rawtypes")
	private JComboBox cmbNoEmp;

	private static Connection connection;
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet = null;
	private JPasswordField txtConfirmerX;
	/**
	 * Launch the application.
	 */
	public static void main(String login) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceCompte window = new InterfaceCompte(login);
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
	public InterfaceCompte(String login) {
		initialize(login);
		btnModifier.setEnabled(false);
		//btnAjouter.setEnabled(false);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param login 
	 */
	private void initialize(String login) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(144, 238, 144));
		frame.setBounds(100, 100, 1230, 684);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		layeredPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		layeredPane.setBounds(10, 153, 468, 273);
		frame.getContentPane().add(layeredPane);
		
		JLabel lblNewLabel = new JLabel("No Employe");
		lblNewLabel.setBounds(10, 59, 96, 14);
		layeredPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Id User");
		lblNewLabel_1.setBounds(10, 20, 71, 14);
		layeredPane.add(lblNewLabel_1);
		
		txtidUser = new JTextField();
		txtidUser.setEditable(false);
		txtidUser.setBounds(164, 13, 117, 28);
		layeredPane.add(txtidUser);
		txtidUser.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setBounds(10, 105, 96, 14);
		layeredPane.add(lblNewLabel_2);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(164, 102, 242, 28);
		layeredPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Mot de passe");
		lblNewLabel_3.setBounds(10, 158, 96, 14);
		layeredPane.add(lblNewLabel_3);
		
		txtMdp = new JPasswordField();
		txtMdp.setBounds(163, 155, 235, 27);
		layeredPane.add(txtMdp);
		txtMdp.setColumns(10);
		
		cmbNoEmp = new JComboBox();
		cmbNoEmp.setFont(new Font("Tahoma", Font.BOLD, 13));
		cmbNoEmp.setBounds(164, 52, 242, 28);
		layeredPane.add(cmbNoEmp);
		
		JLabel lblNewLabel_5 = new JLabel("(Auto-G\u00E9n\u00E9r\u00E9)");
		lblNewLabel_5.setBounds(61, 20, 93, 14);
		layeredPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Confirmer mot de passe");
		lblNewLabel_6.setBounds(10, 211, 144, 28);
		layeredPane.add(lblNewLabel_6);
		
		txtConfirmerX = new JPasswordField();
		txtConfirmerX.setBounds(162, 215, 235, 24);
		layeredPane.add(txtConfirmerX);
		
		try {
			comboEmp();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		layeredPane_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		
		layeredPane_1.setBounds(10, 437, 468, 197);
		frame.getContentPane().add(layeredPane_1);
		
		btnAjouter = new JButton("Ajouter Compte");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				getCompteInfos();
				//compte.setMdp(mdp);
			
				//Controle de saisie
				 if (!EMAIL_PATTERN.matcher(compte.login).matches() ){
					 
					 JFrame frame = new JFrame("retour");
					 JOptionPane.showMessageDialog(frame,"ERREUR EMAIL INVALIDE");
					 txtLogin.requestFocusInWindow();
						
				 }else if (!PASSWORD_PATTERN.matcher(compte.getMdp()).matches()) {
					 
					 
					 JFrame frame = new JFrame("retour");
					 JOptionPane.showMessageDialog(frame,"ERREUR MDP INVALIDE");
					 txtMdp.requestFocusInWindow();
					 
					 
				 }	else if (compte.noEmp.isEmpty()) {
					 
					 JFrame frame = new JFrame("retour");
					 JOptionPane.showMessageDialog(frame,"VOUS N'AVEZ PAS SELECTIONNER UN ID");
					 
				 }else if  (!mdp.toString().equals(confirmerMdp.toString()))  {
					 
					 JFrame frame = new JFrame("retour");
					 JOptionPane.showMessageDialog(frame,"Les 2 mdp ne correspondent pas..");
					     
				 }else {
					 
					compte.addCompte();

						try {
							compte.getAllComptes(table);
							SwingUtilities.updateComponentTreeUI(frame);

						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					
				}
					 
		  }		
			
		});
		btnAjouter.setBounds(10, 11, 448, 35);
		layeredPane_1.add(btnAjouter);
		
		JButton bntEffacerChamps = new JButton("Effacer Champs");
		bntEffacerChamps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAjouter.setEnabled(true);
						txtidUser.setText("");
						txtLogin.setText("");
						txtMdp.setText("");
						cmbNoEmp.setSelectedIndex(0);
						//txtidUser.requestFocusInWindow();
				
			}
		});
		bntEffacerChamps.setBounds(10, 57, 448, 36);
		layeredPane_1.add(bntEffacerChamps);
		
		 btnModifier = new JButton("Modifier Compte");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				compte.idUser = txtidUser.getText();
			    getCompteInfos();
				 String updateQuery = "UPDATE compteutilisateur SET No_emp =?, login =?, mdp =? WHERE Id_User =?";
				try {
					java.sql.Connection connection = ConnectionFactory.getConnection();
				
			         PreparedStatement  ps= connection.prepareStatement(updateQuery);
			         
			         ps.setString(1, compte.noEmp);
			         ps.setString(2,compte.login);
			         ps.setString(3,compte.hashMdp(compte.getMdp()));// compte.getMdp
			         ps.setString(4, compte.idUser);
			         ps.executeUpdate();
						
						JOptionPane.showMessageDialog(frame,"Compte Utilisateur Modifié)");
						
						compte.getAllComptes(table);	
					
				} catch (SQLException e1) {
					JFrame frame = new JFrame("error");
					JOptionPane.showMessageDialog(frame, e1);
					e1.printStackTrace();
				}
								
			}
		});
		btnModifier.setBounds(10, 104, 448, 35);
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
			         ps.executeUpdate();
						JFrame frame = new JFrame("retour");
						
						JOptionPane.showMessageDialog(frame,"compte utilisateur supprimé");
						
                          compte.getAllComptes(table);	// mise à jour de la table > affichage					
	
				} catch (SQLException e1) {
					JFrame frame = new JFrame("error");
					JOptionPane.showMessageDialog(frame, e1);
					e1.printStackTrace();
				}
						
			}
		});
		btnSupprimer.setBounds(10, 150, 448, 36);
		layeredPane_1.add(btnSupprimer);
		
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.setBounds(667, 112, 142, 31);
		frame.getContentPane().add(btnRechercher);
		scrollPane.setFont(new Font("Tahoma", Font.BOLD, 12));
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(488, 153, 716, 358);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnModifier.setEnabled(true);
				
				int i = table.getSelectedRow();
		        TableModel model = table.getModel();
		          //Display Slected Row In JTexteFields
		        ///"id User", "No Employe", "Login", "mdp"
		        txtidUser.setText(model.getValueAt(i,0).toString());
			    cmbNoEmp.setSelectedItem(model.getValueAt(i,1).toString());
		        txtLogin.setText(model.getValueAt(i,2).toString());
		        txtMdp.setText(model.getValueAt(i,3).toString());
	
			}
		});
		scrollPane.setViewportView(table);
		    try {
			    compte.getAllComptes(table);
		    } catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		 }
		
		txtRechercher = new JTextField();
		txtRechercher.setBounds(387, 113, 256, 29);
		frame.getContentPane().add(txtRechercher);
		txtRechercher.setColumns(10);
		
		CompteAdmin A = new CompteAdmin() ;
		
		try {
			A.DatabaseConnexionHR(login, null, null, frame);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if(A.getTypeCompte().contains("HR Manager")) {
			
			JButton btnRetourMenuHr = new JButton("Menu pricipal");
			btnRetourMenuHr.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				InterfaceCompte.this.frame.setVisible(false);
					 MenuHr.main(login);
					
				}
			});
			btnRetourMenuHr.setBounds(782, 11, 174, 31);
			frame.getContentPane().add(btnRetourMenuHr);
				
		}else {
			
			JButton btnRetourAdm = new JButton("Retour au menu Admin");
			btnRetourAdm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					InterfaceCompte.this.frame.setVisible(false);
					
					
		
					MenuAdm.main(login);
				}
			});
			btnRetourAdm.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			btnRetourAdm.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnRetourAdm.setBounds(1032, 15, 161, 38);
			frame.getContentPane().add(btnRetourAdm);
				
		}
			
		JLabel lblNewLabel_4 = new JLabel("Compte Utilisateurs");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_4.setBounds(539, 0, 222, 44);
		frame.getContentPane().add(lblNewLabel_4);
		
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
		 String searchQuery = "SELECT No_employe,Prenom,Nom FROM employes ORDER BY No_employe DESC";
		java.sql.Connection	 connection = ConnectionFactory.getConnection();
			//PreparedStatement preparedStatement = connection.prepareStatement(QueryStatement.searchQuery);
	        Statement preparedStatement = connection.createStatement();

			@SuppressWarnings("unused")
			ResultSet resultSet = preparedStatement.executeQuery(searchQuery);
			
			cmbNoEmp.addItem("");
		try {
			
			while (resultSet.next()) {
				   cmbNoEmp.addItem(resultSet.getString("No_employe") + "--" + resultSet.getString("Nom")+ "--"+ resultSet.getString("Prenom")); 
			}
		}				
			
		catch(SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
					
		}	
			
	}
	

	public void getCompteInfos() {
	    compte.idUser =	txtidUser.getText();
		compte.login  = txtLogin.getText();
		mdp = String.copyValueOf(txtMdp.getPassword());
		compte.setMdp(mdp);
		confirmerMdp = 	String.copyValueOf(txtConfirmerX.getPassword());
		noEmp = cmbNoEmp.getSelectedItem().toString();
		
	try {
		String input_string = noEmp;
		int number_output = Integer.parseInt(input_string.replaceAll("[^0-9]", ""));
		noEmp = String.valueOf(number_output);
		compte.noEmp = noEmp;	
		 
	}catch(Exception e) {
		
		JOptionPane.showMessageDialog(frame, "ERREUR, selcetionnez un département..");

		
	}
	}
	
	// Méthode qui recoit valeur rechercher par paramètre (val)
				public static ArrayList<Compte> allComptes(String val) throws SQLException {
					//val ="10";
			        String searchQuery = "SELECT* FROM compteutilisateur WHERE CONCAT (`Id_User`,`login`,`mdp`,`No_emp`) LIKE'%"+val+"%'";
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
						String login = resultSet.getString(2);
						String mdp = resultSet.getString(3);
						String no_Emp = resultSet.getString(4);
						compte.idUser = id;
						compte.login = login;
						//mdp = compte.getMdp();
						compte.setMdp(mdp);
						compte.noEmp = no_Emp;
						compteList.add(compte);
					}
						
				}

					return compteList;
					
				}
	
				 public void findCompte() throws SQLException {
				        ArrayList<Compte> compte = allComptes(txtRechercher.getText());
				        DefaultTableModel model = new DefaultTableModel();
				        model.setColumnIdentifiers(new Object[]{"Id compte", "No Emp", "Login","mdp"});
				        Object[] row = new Object[4];
				        //"id User", "No Employe", "Login", "mdp"
				        for(int i = 0; i < compte.size(); i++) {
				            row[0] = compte.get(i).idUser;
				            row[1] = compte.get(i).noEmp;
				            row[2] = compte.get(i).login;
				            row[3] = compte.get(i).getMdp();
				            model.addRow(row);
				        }
				       table.setModel(model);
				       
				    }
}