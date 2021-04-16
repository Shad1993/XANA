package InterfaceFiche;
import Fiche.*;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

//import com.example.constants.CRUDMode;
//import com.example.constants.QueryStatement;
import com.example.db.ConnectionFactory;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;




public class gererFiche {

	private JFrame frame;
	private final JLayeredPane layeredPane = new JLayeredPane();
	private JTextField txtHeureSup;
	private JTextField txtBonus;
	private JTextField txtCommission;
	private JTextField txtDeduction;
	private final JLayeredPane layeredPane_1 = new JLayeredPane();
	
	@SuppressWarnings("rawtypes")
	private JComboBox cmbNoEmp;

	public static String no_Emp;
	public static String idFiche;
	public static String bonus;
	public static String heureSup;
	public  static String deduction;
	public static String mois;
	public static String commission;
	
	public static String Nom;
	public static String Prenom;
	public static String NoDep ;
	public static String Dep;
	public static String Salaire;
	public static String Titre;





	
	Connection connection = ConnectionFactory.getConnection();
	//PreparedStatement preparedStatement = connection.prepareStatement(QueryStatement.searchQuery);
    java.sql.Statement  preparedStatement = connection.createStatement();
    
    
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gererFiche window = new gererFiche();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public gererFiche() throws SQLException {
		initialize();
		comboEmp();
		
	}
	
	
	
	
	//private static FicheDePaie ficheInfos(CRUDMode mode) {
		//FicheDePaie fiche = new FicheDePaie();
		
		// opérations

		//CRUDMode.UPDATE n'a pas marché a été enlevé, autre soulution trouvée.. dans bouton Modifié
		//if (mode.equals(CRUDMode.ADD) || mode.equals(CRUDMode.DELETE)) {
			
			//if (mode.equals(CRUDMode.DELETE)) {
				//fiche.set_idFiche(idFiche);
			//}
			
			
		// fonctions set permettant de manipuler les variables privés
			//fiche.set_Bonus(bonus);
			//fiche.set_Commission(commission);
			//fiche.set_Deduction(deduction);
			//fiche.set_heureSup(heureSup);
			//fiche.set_idFiche(idFiche);
			//fiche.set_Mois(mois);
			//fiche.set_noEmp(no_Emp);
		
	  // }
		//return fiche;
		

	//}
	
	public void getFicheInfos() {
		
		no_Emp = cmbNoEmp.getSelectedItem().toString();
		heureSup = txtHeureSup.getText();
		bonus= txtBonus.getText();
		deduction = txtDeduction.getText();
		commission = txtCommission.getText();
	    
		// obtenir Mois actuel
		Calendar mCalendar = Calendar.getInstance();    
		String month = mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
	
	     mois = month;
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 657, 422);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		layeredPane.setBounds(152, 66, 445, 267);
		frame.getContentPane().add(layeredPane);
		
		cmbNoEmp = new JComboBox();
		cmbNoEmp.setBounds(151, 11, 167, 29);
		layeredPane.add(cmbNoEmp);
		
		JLabel lblNewLabel = new JLabel("No Employe");
		lblNewLabel.setBounds(10, 11, 103, 22);
		layeredPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Heure Suppl\u00E9mentaire");
		lblNewLabel_1.setBounds(10, 58, 116, 29);
		layeredPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Bonus");
		lblNewLabel_2.setBounds(10, 116, 48, 14);
		layeredPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Commission");
		lblNewLabel_3.setBounds(10, 158, 103, 22);
		layeredPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("D\u00E9duction");
		lblNewLabel_4.setBounds(10, 201, 82, 22);
		layeredPane.add(lblNewLabel_4);
		
		txtHeureSup = new JTextField();
		txtHeureSup.setBounds(151, 59, 133, 26);
		layeredPane.add(txtHeureSup);
		txtHeureSup.setColumns(10);
		
		txtBonus = new JTextField();
		txtBonus.setBounds(151, 107, 132, 26);
		layeredPane.add(txtBonus);
		txtBonus.setColumns(10);
		
		txtCommission = new JTextField();
		txtCommission.setColumns(10);
		txtCommission.setBounds(151, 148, 133, 27);
		layeredPane.add(txtCommission);
		
		txtDeduction = new JTextField();
		txtDeduction.setColumns(10);
		txtDeduction.setBounds(151, 199, 133, 26);
		layeredPane.add(txtDeduction);
		
		JButton btnRehcercher = new JButton("Rechercher");
		btnRehcercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				no_Emp = cmbNoEmp.getSelectedItem().toString();
				 String searchQuery = "SELECT DISTINCT No_employe,Nom,Prenom,Titre,Salaire,Nodept,Nom_dept,Mois,HeureSup,Bonus,Commission,Deduction FROM employes E, departement D, fch_de_paie F WHERE E.Nodept = D.No_dept AND E.No_employe = F.No_Emp AND F.No_Emp = ?"; 
				 	 
				 		
					Connection connection = null;
					try {
						connection = ConnectionFactory.getConnection();
					
						PreparedStatement	preparedStatement = connection.prepareStatement(searchQuery);
						
						preparedStatement.setString(1, no_Emp); 
						
						ResultSet resultSet = preparedStatement.executeQuery();
						
						if (resultSet.next()) {
							
							txtHeureSup.setText(resultSet.getString("HeureSup"));
							txtBonus.setText(resultSet.getString("Bonus"));
							txtCommission.setText(resultSet.getString("Commission"));
							txtDeduction.setText(resultSet.getString("Deduction"));
						   
						    Nom = resultSet.getString(2);
						    Prenom = resultSet.getString(3);
						    NoDep =resultSet.getString(6);
						    Dep =resultSet.getString(7);
						    Salaire= resultSet.getString(5);
						    mois  =resultSet.getString(8);
						    Titre  =resultSet.getString(4);





						    

							
						}
						
					    
						
					} catch (SQLException e1) {
						
						
						// TODO Auto-generated catch block
						
						e1.printStackTrace();
					}
			    
				

				      JFileChooser dialog = new JFileChooser();
			          // dialog.setSelectedFile(new File(Prenom +" "+ Nom+"-Salary Slip"+".pdf"));
			           
				
				
				
				
				
				
				
				
				
				
			}
		});
		btnRehcercher.setBounds(329, 11, 106, 32);
		layeredPane.add(btnRehcercher);
		
		JButton btnGenereFiche = new JButton("G\u00E9nerer Fiche de paie");
		btnGenereFiche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				 // Nom = txtDeduction.getText();

				   JFrame frame = new JFrame("retour");
					JOptionPane.showMessageDialog(frame,Titre);

				
				
				
				
				
				
				
				
			}
		});
		btnGenereFiche.setBounds(152, 344, 174, 31);
		frame.getContentPane().add(btnGenereFiche);
		layeredPane_1.setBounds(10, 66, 108, 132);
		frame.getContentPane().add(layeredPane_1);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
              
				       getFicheInfos();// appel méthode pour affecter valeurs dans les variables
				       
				        FicheDePaie fiche = new FicheDePaie();//instantiation obj  de type fiche de paie
				        
				        //manipulation avec des propriétés privées avec méthode set
				        fiche.set_Bonus(bonus);
						fiche.set_Commission(commission);
						fiche.set_Deduction(deduction);
						fiche.set_heureSup(heureSup);
						fiche.set_idFiche(idFiche);
						fiche.set_Mois(mois);
						fiche.set_noEmp(no_Emp);

				        fiche.addFiche();// appel méthode de la classe mère FiecheDePaie
				        				 // ppour insertion
				
				        //DBUTIL n'a pas marché
				//getFicheInfos();

				//if (controleSaisie(error) == false) {
				//try {
						//DBUtil.addFiche(ficheInfos(CRUDMode.ADD));
						
						//JFrame frame = new JFrame("retour");
						//JOptionPane.showMessageDialog(frame, "Employé ajouté");
						
						//clearChamps();							
					//} catch (SQLException e1) {
						//JFrame frame = new JFrame("error");
						//JOptionPane.showMessageDialog(frame,e1);
					//}
					//refreshTable();
			//}
				
				
				
			}
		});
		btnAjouter.setBounds(10, 11, 89, 31);
		layeredPane_1.add(btnAjouter);
		
		JButton butSupprimer = new JButton("Supprimer");
		butSupprimer.setBounds(10, 74, 89, 31);
		layeredPane_1.add(butSupprimer);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public void comboEmp() throws SQLException{
		
		 String searchQuery = "SELECT No_employe FROM employes";
			 connection = ConnectionFactory.getConnection();
			//PreparedStatement preparedStatement = connection.prepareStatement(QueryStatement.searchQuery);
	        preparedStatement = connection.createStatement();

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
	
	
	
	
	
	
	
	
	
	
	
}
