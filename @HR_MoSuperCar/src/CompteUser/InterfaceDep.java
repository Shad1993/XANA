package CompteUser;


 
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.example.db.ConnectionFactory;
import com.example.utilities.DBUtil;

import Departements.Dep;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Font;


/**
 * Cette Classe est utilisée pour créer/gérer les informations des départements
 * @author Lionel
 *
 */
public class InterfaceDep {

	private JFrame frame;
	private JTextField txtnoDep;
	private JTextField txtDep;
	private final JLayeredPane layeredPane = new JLayeredPane();
	private JTextField txtAdresse;
	private final JScrollPane scrollPane = new JScrollPane();
	private static JTextField txtRecherche;
	private JTextField txtContact;
	private JTable table;
	public String comparMdp;
	private JButton btnModifier;
	private JButton btnAjouter;
	
	Dep dept = new Dep();

	/**
	 * Launch the application.
	  * @param login Stock le login de l'utilisateur connecté pour mainternir la session
	 */
	public static void main(String login) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceDep window = new InterfaceDep(login);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * CrÃ©er the l'application
	 * @param login Stock le login de l'utilisateur connecté pour mainternir la session
	 */
	public InterfaceDep(String login) {
		initialize(login);
		btnModifier.setEnabled(false);//Désactiver bouton Mise à jour

	}

	/**
	 * Initialise les contenus du frame.
	 * @param login variable de type String qui stock le login de l'utilisateur connecté
	 */
	private void initialize(String login) {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.ORANGE);
		frame.setBackground(SystemColor.inactiveCaption);
		frame.setBounds(100, 100, 940, 570);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		layeredPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		layeredPane.setBounds(10, 337, 337, 155);
		frame.getContentPane().add(layeredPane);

		CompteAdmin A = new CompteAdmin();
		
		try {
			A.DatabaseConnexionHR(login, null, null, frame);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		   if (A.getTypeCompte().contains("Administrateur")) {
			   
				JButton btnRetourAdm = new JButton("Retour au menu Admin");
				btnRetourAdm.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				btnRetourAdm.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						InterfaceDep.this.frame.setVisible(false);
						MenuAdm.main(login);
					}
				});
				btnRetourAdm.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnRetourAdm.setBounds(735, 15, 179, 33);
				frame.getContentPane().add(btnRetourAdm);
			     
		   }else {
			   
				JButton btnMenuHr = new JButton("Retour au menu");
				btnMenuHr.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						InterfaceDep.this.frame.setVisible(false);
						MenuHr.main(login);	
					}
				});
				btnMenuHr.setFont(new Font("Tahoma", Font.BOLD, 11));
				btnMenuHr.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				btnMenuHr.setBounds(744, 11, 151, 36);
				frame.getContentPane().add(btnMenuHr);   
		   }
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			/**
			 *cette méthode affiche l'enregistrement sélectionné de la table dans les champs en cliquant
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				
				btnModifier.setEnabled(true);
				btnAjouter.setVisible(false);
		        //txtNomDep.setVisible(true);

			     //btnAjouter.setEnabled(false); //Activer bouton d'insertion
			     //btnModifier.setEnabled(true); // Ativer bouton Mise ï¿½ jour
				   
				int i = table.getSelectedRow();
		        TableModel model = table.getModel();
		        
		          //Display Slected Row In JTexteFields
		        txtnoDep.setText(model.getValueAt(i,0).toString());
		        txtDep.setText(model.getValueAt(i,1).toString());
		        txtContact.setText(model.getValueAt(i,2).toString());
		        txtAdresse.setText(model.getValueAt(i,3).toString());
				
			}
		});
		
		
		Dep afficherDep = new Dep();
		try {
			afficherDep.getAllDep(table);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAjouter.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getDepInfos();
				boolean error = false;

				if (controleSaisie(error) ==false) {
					
					dept.addDep();// ajouté enregistrement.
				
					try {
						dept.getAllDep(table);// refresh table > affichage
						refreshChamps();
					} 	catch (SQLException e1) {
					
						e1.printStackTrace();
					}
			
				}
					
			}
		});
		btnAjouter.setBounds(10, 11, 317, 29);
		layeredPane.add(btnAjouter);
		
		JButton btnEfffacerChamps = new JButton("Effacer champs");
		btnEfffacerChamps.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEfffacerChamps.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnEfffacerChamps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAjouter.setVisible(true);// activer buton ajouter pour insertion

				txtnoDep.setText("");
				txtDep.setText("");
				txtAdresse.setText("");
				txtContact.setText("");
				txtnoDep.requestFocusInWindow();
			}
		});
		btnEfffacerChamps.setBounds(10, 45, 317, 29);
		layeredPane.add(btnEfffacerChamps);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSupprimer.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnSupprimer.setBounds(10, 115, 317, 29);
		layeredPane.add(btnSupprimer);
		
		btnModifier = new JButton("Mise \u00E0 jour");
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnModifier.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnModifier.setBounds(10, 81, 317, 29);
		layeredPane.add(btnModifier);
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dept.noDep = txtnoDep.getText();
			    getDepInfos();
				 String updateQuery = "UPDATE departement SET Nom_dept =?, AdresseDep =?, NoContact =? WHERE No_dept =?";
				try {
					Connection connection = ConnectionFactory.getConnection();
				
			         PreparedStatement  ps= connection.prepareStatement(updateQuery);
			         
			         ps.setString(1, dept.dep);
			         ps.setString(2,dept.adresse);
			         ps.setString(3,dept.noContact);
			         ps.setString(4, dept.noDep);
					boolean error = false;

					if (controleSaisie(error) == false) {
					
			            ps.executeUpdate();
						JFrame frame = new JFrame("retour");
						JOptionPane.showMessageDialog(frame,"Dï¿½partement Modifiï¿½)");
						refreshTable();	
						refreshChamps();
		            }
						
				} catch (SQLException e1) {
					JFrame frame = new JFrame("error");
					JOptionPane.showMessageDialog(frame, e1);
					e1.printStackTrace();
				}
								
			}
		});
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dept.noDep = txtnoDep.getText();
				//getEmpInfos();
				 String delQuery = "DELETE FROM departement WHERE No_dept =?";
				try {
					Connection connection = ConnectionFactory.getConnection();
			         PreparedStatement  ps= connection.prepareStatement(delQuery);
			         ps.setString(1, dept.noDep);
			         ps.executeUpdate();
			         						
					 JOptionPane.showMessageDialog(frame,"Dï¿½partement Effacï¿½)");
                     dept.getAllDep(table);						
						//txtNomDep.setVisible(false);
					
				} catch (SQLException e1) {
					JFrame frame = new JFrame("error");
					JOptionPane.showMessageDialog(frame, e1);
					e1.printStackTrace();
				}
					
				
			}
		});
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(345, 140, 569, 220);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
		
		txtRecherche = new JTextField();
		txtRecherche.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtRecherche.setBounds(347, 102, 250, 27);
		frame.getContentPane().add(txtRecherche);
		txtRecherche.setColumns(10);
		
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRechercher.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					findDepartement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRechercher.setBounds(625, 102, 112, 27);
		frame.getContentPane().add(btnRechercher);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		layeredPane_1.setBounds(10, 140, 337, 185);
		frame.getContentPane().add(layeredPane_1);
		
		JLabel lblNewLabel = new JLabel("No D\u00E9partement");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 11, 124, 20);
		layeredPane_1.add(lblNewLabel);
		
		txtnoDep = new JTextField();
		txtnoDep.setEnabled(false);
		txtnoDep.setBounds(144, 8, 69, 27);
		layeredPane_1.add(txtnoDep);
		txtnoDep.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nom D\u00E9partement");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 49, 126, 21);
		layeredPane_1.add(lblNewLabel_1);
		
		txtDep = new JTextField();
		txtDep.setBounds(146, 46, 160, 27);
		layeredPane_1.add(txtDep);
		txtDep.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("NoContact");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 127, 115, 21);
		layeredPane_1.add(lblNewLabel_2);
		
		JLabel tblAdresse = new JLabel("Adresse");
		tblAdresse.setFont(new Font("Tahoma", Font.BOLD, 12));
		tblAdresse.setBounds(10, 81, 126, 24);
		layeredPane_1.add(tblAdresse);
		
		txtAdresse = new JTextField();
		txtAdresse.setBounds(146, 84, 187, 27);
		layeredPane_1.add(txtAdresse);
		txtAdresse.setColumns(10);
		
		txtContact = new JTextField();
		txtContact.setBounds(144, 124, 123, 27);
		layeredPane_1.add(txtContact);
		txtContact.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("D\u00E9partements");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(419, 11, 151, 27);
		frame.getContentPane().add(lblNewLabel_3);
		
	}
	
	/**
	 * Méthode pour le controle de saisie qui retourne le variable SignalErrreur en cau d'erreur de saisie
	 * @param SignalErreur variable de type booléen qui est rerourné en cas d'erreurs
	 * @return retourne SignalErreur
	 */
	private boolean controleSaisie(boolean SignalErreur) {
		//frame = new JFrame();
		if (Pattern.matches("[0-9]{7,}", dept.noContact) == false || dept.noContact.equalsIgnoreCase("")) {

			JOptionPane.showMessageDialog(frame, "ERREUR, No contact INVALIDE");
			txtContact.setBackground(new Color(255, 186, 186));
			txtContact.requestFocusInWindow();
			
			SignalErreur = true;
			
		//^(0|[1-9]\d*)(\.\d+)?$
		} else if (Pattern.matches("[a-zA-ZÀ-ÿ0-9]+(([',. -][a-zA-ZÀ-ÿ ])?[a-zA-ZÀ-ÿ]*)*$", dept.adresse) == false || dept.adresse.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(frame, "ERREUR, ADRESSE INVALIDE");
			txtAdresse.setBackground(new Color(255, 186, 186));
			txtAdresse.requestFocusInWindow();
			
			SignalErreur = true;
	
		}else if (Pattern.matches("[a-zA-ZÀ-ÿ]+(([',. -][a-zA-ZÀ-ÿ ])?[a-zA-ZÀ-ÿ]*)*$", dept.dep) == false || dept.dep.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(frame, "ERREUR, NOM INVALIDE");
			txtDep.setBackground(new Color(255, 186, 186));
			txtDep.requestFocusInWindow();
			SignalErreur = true;
	
		}
		
		return SignalErreur;
	}	
	
	

	/**
	 * Cette méthode Initialise les champs après une opération sql
	 */
	public void refreshChamps () {
		txtDep.setBackground(new Color(255, 255, 255));
		txtContact.setBackground(new Color(255, 255, 255));
		txtAdresse.setBackground(new Color(255, 255, 255));
	}
	
	
	/**
	 * Méthode qui récupère les saisies de l'utilisateur des champs
	 * 
	 */
	public void getDepInfos() { 
		//dept.noDep  = txtnoDep.getText();
	    dept.dep =	txtDep.getText();
		dept.adresse = txtAdresse.getText();
		dept.noContact  = txtContact.getText();		
	}
	
	/**
	 * Méthode qui affiche les données mises À jour dans la table
	 */
	public void refreshTable() {
		
		try {
			
			table.setModel(new DefaultTableModel());
			
			Dep affichage = new Dep();
			affichage.getAllDep(table);
			
		}
		
		catch(SQLException e1) {
		JOptionPane.showMessageDialog(null, e1);
				
		}
			
     }
	

			/**
			 * Méthode qui reçoit valeur recherché par paramètre (val)
			 * @param val variable de type String qui reçoit le saisie du département recherché
			 * @return retourne le résultat obtenus en forme de tableau d'odjet deptList
			 * @throws SQLException gère les erreurs sql
			 */
			public static ArrayList<Dep> allDepts(String val) throws SQLException {
				//val ="10";
		        String searchQuery = "SELECT* FROM departement WHERE CONCAT (`No_dept`,`Nom_dept`,`NoContact`,`AdresseDep`) LIKE'%"+val+"%'";
				java.sql.Connection connection = ConnectionFactory.getConnection();
				//PreparedStatement preparedStatement = connection.prepareStatement(QueryStatement.searchQuery);
		      java.sql.Statement  preparedStatement = connection.createStatement();

				ResultSet resultSet = preparedStatement.executeQuery(searchQuery);
				
				
				ArrayList<Dep> depList = new ArrayList<Dep>();
				
			if (!resultSet.isBeforeFirst() ) { // si pas de résultat
				
				JFrame frame = new JFrame("0 résultat");
				JOptionPane.showMessageDialog(frame,"Aucun résultat obtenu..");
				txtRecherche.requestFocusInWindow();
				
				txtRecherche.requestFocusInWindow(); //place curseur dans txtbox rechercher
				txtRecherche.setText("");//effacer textbox recherche

				
			}else {
				
				while (resultSet.next()) {
					Dep dept = new Dep();
					
					String noDep = resultSet.getString(1);
					String nomDep = resultSet.getString(2);
					String noContactX = resultSet.getString(3);
					String adresseX = resultSet.getString(4);
					
					dept.noDep = noDep;
					dept.dep= nomDep;
					dept.adresse= adresseX;
					dept.noContact =noContactX;
					
					depList.add(dept);
				}
					
			}

				return depList;
				
			}

			
			 /**
			  * Cette méthode affchiche les résultats obtenus dans la table
			 * @throws SQLException gère les erreurs sql
			 */
			public void findDepartement() throws SQLException 
			 
			    {
			        ArrayList<Dep> dept = allDepts(txtRecherche.getText());
			        DefaultTableModel model = new DefaultTableModel();
			        model.setColumnIdentifiers(new Object[]{"No Departement", "Departement", "No contact","Adresse"});
			        Object[] row = new Object[4];
			        
			        for(int i = 0; i < dept.size(); i++)
			        {
			            row[0] = dept.get(i).noDep;
			            row[1] = dept.get(i).dep;
			            row[2] = dept.get(i).noContact;
			            row[3] = dept.get(i).adresse;
			           
			          
			            model.addRow(row);
			        }
			       table.setModel(model);
			       
			    }
		
}
