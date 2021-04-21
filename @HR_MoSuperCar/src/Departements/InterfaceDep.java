package Departements;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.example.db.ConnectionFactory;
import com.example.utilities.DBUtil;

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
	
	
	
	//private static String noDept ;
    //private  static String dep;
    //private static String noContact;
   // private static String adresse;
    
	Dep dept = new Dep();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceDep window = new InterfaceDep();
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
	public InterfaceDep() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.inactiveCaption);
		frame.setBackground(SystemColor.inactiveCaption);
		frame.setBounds(100, 100, 940, 536);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		layeredPane.setBounds(10, 371, 323, 81);
		frame.getContentPane().add(layeredPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
		        //txtNomDep.setVisible(true);

			     //btnAjouter.setEnabled(false); //Activer bouton d'insertion
			     //btnModifier.setEnabled(true); // Ativer bouton Mise à jour
				   
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
		
		
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				getDepInfos();
				
				dept.addDep();
				refreshTable();
				//Dep dept = new Dep();
				
				//dept.noDep = Dep;
				
				
			}
		});
		btnAjouter.setBounds(10, 11, 108, 23);
		layeredPane.add(btnAjouter);
		
		JButton btnInit = new JButton("Mise \u00E0 jour");
		btnInit.addActionListener(new ActionListener() {
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

			       
			         
			         
					//boolean error = false;

					
					
					//if (controleSaisie(error) == false) {
					
			         ps.executeUpdate();
						JFrame frame = new JFrame("retour");
						
						JOptionPane.showMessageDialog(frame,"Département Modifié)");
						refreshTable();
						
						//txtNomDep.setVisible(false);

					
		        //}
					
					
				} catch (SQLException e1) {
					JFrame frame = new JFrame("error");
					JOptionPane.showMessageDialog(frame, e1);
					e1.printStackTrace();
				}
							
				
			}
		});
		btnInit.setBounds(207, 11, 106, 23);
		layeredPane.add(btnInit);
		
		JButton btnSupprimer = new JButton("Effacer champs");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtnoDep.setText("");
				txtDep.setText("");
				txtAdresse.setText("");
				txtContact.setText("");
				txtnoDep.requestFocusInWindow();


				
				
				
				
			}
		});
		btnSupprimer.setBounds(10, 45, 108, 23);
		layeredPane.add(btnSupprimer);
		
		JButton btnModifier = new JButton("Supprimer");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				dept.noDep = txtnoDep.getText();
				//getEmpInfos();
				 String delQuery = "DELETE FROM departement WHERE No_dept =?";
				try {
					Connection connection = ConnectionFactory.getConnection();
				
			         PreparedStatement  ps= connection.prepareStatement(delQuery);

			         ps.setString(1, dept.noDep);
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
			         
			         
					boolean error = false;

					
					
					//if (controleSaisie(error) == false) {
					
			         ps.executeUpdate();
						JFrame frame = new JFrame("retour");
						
						JOptionPane.showMessageDialog(frame,"Département Effacé)");
						refreshTable();
						
						//txtNomDep.setVisible(false);

					
		        //}
					
					
				} catch (SQLException e1) {
					JFrame frame = new JFrame("error");
					JOptionPane.showMessageDialog(frame, e1);
					e1.printStackTrace();
				}
					
				
			}
		});
		btnModifier.setBounds(207, 45, 106, 23);
		layeredPane.add(btnModifier);
		scrollPane.setBounds(345, 140, 528, 220);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
		
		txtRecherche = new JTextField();
		txtRecherche.setBounds(347, 102, 179, 27);
		frame.getContentPane().add(txtRecherche);
		txtRecherche.setColumns(10);
		
		JButton btnRechercher = new JButton("Rechercher");
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
		btnRechercher.setBounds(536, 95, 112, 34);
		frame.getContentPane().add(btnRechercher);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBounds(10, 140, 323, 220);
		frame.getContentPane().add(layeredPane_1);
		
		JLabel lblNewLabel = new JLabel("No D\u00E9partement");
		lblNewLabel.setBounds(10, 11, 96, 20);
		layeredPane_1.add(lblNewLabel);
		
		txtnoDep = new JTextField();
		txtnoDep.setBounds(126, 8, 69, 27);
		layeredPane_1.add(txtnoDep);
		txtnoDep.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nom D\u00E9partement");
		lblNewLabel_1.setBounds(10, 49, 96, 21);
		layeredPane_1.add(lblNewLabel_1);
		
		txtDep = new JTextField();
		txtDep.setBounds(126, 46, 160, 27);
		layeredPane_1.add(txtDep);
		txtDep.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("NoContact");
		lblNewLabel_2.setBounds(10, 144, 61, 14);
		layeredPane_1.add(lblNewLabel_2);
		
		JLabel tblAdresse = new JLabel("Adresse");
		tblAdresse.setBounds(10, 95, 96, 24);
		layeredPane_1.add(tblAdresse);
		
		txtAdresse = new JTextField();
		txtAdresse.setBounds(126, 94, 187, 27);
		layeredPane_1.add(txtAdresse);
		txtAdresse.setColumns(10);
		
		txtContact = new JTextField();
		txtContact.setBounds(126, 141, 123, 27);
		layeredPane_1.add(txtContact);
		txtContact.setColumns(10);
	}
	
	
	public void getDepInfos() {
		
		//dept.noDep  = txtnoDep.getText();
	    dept.dep =	txtDep.getText();
		dept.adresse = txtAdresse.getText();
		dept.noContact  = txtContact.getText();
		
			
	}
	
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
	
	// Méthode qui recoit valeur rechercher par paramètre (val)
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
					String adresseX = resultSet.getString(3);
					String noContactX = resultSet.getString(4);
					
					dept.noDep = noDep;
					dept.dep= nomDep;
					dept.adresse= adresseX;
					dept.noContact =noContactX;
					
			

						
					depList.add(dept);
				}
				
				
			}

				return depList;
				
			   
			}

			
			//Affiche résultats
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
