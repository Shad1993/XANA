package InterfaceFiche;
import Fiche.*;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.awt.event.ActionEvent;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.SystemColor;



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
	public static String id_Fiche;






	
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
		frame.getContentPane().setBackground(SystemColor.inactiveCaption);
		frame.setBounds(100, 100, 657, 422);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		layeredPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		layeredPane.setBounds(152, 66, 479, 289);
		frame.getContentPane().add(layeredPane);
		
		cmbNoEmp = new JComboBox();
		cmbNoEmp.setBounds(151, 8, 167, 29);
		layeredPane.add(cmbNoEmp);
		
		JLabel lblNewLabel = new JLabel("No Employe");
		lblNewLabel.setBounds(10, 11, 131, 22);
		layeredPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Heure Suppl\u00E9mentaire");
		lblNewLabel_1.setBounds(10, 58, 131, 29);
		layeredPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Bonus");
		lblNewLabel_2.setBounds(10, 116, 115, 14);
		layeredPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Commission");
		lblNewLabel_3.setBounds(10, 158, 103, 22);
		layeredPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("D\u00E9duction");
		lblNewLabel_4.setBounds(10, 201, 82, 22);
		layeredPane.add(lblNewLabel_4);
		
		txtHeureSup = new JTextField();
		txtHeureSup.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtHeureSup.setBounds(150, 59, 133, 26);
		layeredPane.add(txtHeureSup);
		txtHeureSup.setColumns(10);
		
		txtBonus = new JTextField();
		txtBonus.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtBonus.setBounds(151, 107, 132, 26);
		layeredPane.add(txtBonus);
		txtBonus.setColumns(10);
		
		txtCommission = new JTextField();
		txtCommission.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtCommission.setColumns(10);
		txtCommission.setBounds(151, 148, 133, 27);
		layeredPane.add(txtCommission);
		
		txtDeduction = new JTextField();
		txtDeduction.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtDeduction.setColumns(10);
		txtDeduction.setBounds(151, 199, 133, 26);
		layeredPane.add(txtDeduction);
		
		JButton btnRehcercher = new JButton("Rechercher");
		btnRehcercher.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnRehcercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				no_Emp = cmbNoEmp.getSelectedItem().toString();
				 String searchQuery = "SELECT No_employe,Nom,Prenom,Titre,Salaire,Nodept,Nom_dept,Mois,HeureSup,Bonus,Commission,Deduction,Id_fch FROM employes E, departement D, fch_de_paie F WHERE E.Nodept = D.No_dept AND E.No_employe = F.No_Emp AND F.No_Emp = ?"; 
				 	 
				 		
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
							
							bonus = txtDeduction.getText();
							commission = txtCommission.getText();
							heureSup = txtHeureSup.getText();
							no_Emp = cmbNoEmp.getSelectedItem().toString();

						    Nom = resultSet.getString(2);
						    Prenom = resultSet.getString(3);
						    NoDep = resultSet.getString(6);
						    Dep = resultSet.getString(7);
						    Salaire= resultSet.getString(5);
						    mois  = resultSet.getString(8);
						    Titre  = resultSet.getString(4);
						    deduction  = resultSet.getString(12);
						    id_Fiche  = resultSet.getString(13);

						    






						    

							
						}
						
					    
						
					} catch (SQLException e1) {
						
						
						// TODO Auto-generated catch block
						
						e1.printStackTrace();
					}
			    
				

				     
				
				
				
				
				
				
				
				
				
			}
		});
		btnRehcercher.setBounds(363, 9, 106, 32);
		layeredPane.add(btnRehcercher);
		
		JButton btnGenereFiche = new JButton("G\u00E9nerer Fiche de paie");
		btnGenereFiche.setBounds(295, 247, 174, 31);
		layeredPane.add(btnGenereFiche);
		btnGenereFiche.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnGenereFiche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				 

					 JFileChooser dialog = new JFileChooser();
			         dialog.setSelectedFile(new File(Prenom +" "+ Nom+"-Fiche_de_paie"+".pdf"));
			         int dialogResult = dialog.showSaveDialog(null);
			         if (dialogResult==JFileChooser.APPROVE_OPTION){
			             String filePath = dialog.getSelectedFile().getPath();
			            
			         try {
			             
			             
			        	 double salTot = Double.parseDouble(Salaire);
				            
				            double deduc = Double.parseDouble(deduction);
				            double salBrut = salTot -deduc;
				            
				            double tax = 0.10 * salBrut;
				            double salNet = salBrut - tax;
				            
				            double com =Double.parseDouble(commission);
				            double  bon =Double.parseDouble(bonus);

				            double paieTot = salNet + com + bon;
				            
				            //double payTot = salNet+ bonus.valueOf(i)+ commission;
			        	
			             //String val = String.valueOf(x);
			             
			       
			            Document myDocument = new Document();
			            PdfWriter myWriter = PdfWriter.getInstance(myDocument, new FileOutputStream(filePath));

			            myDocument.open();
			            myDocument.add(new Paragraph("FICHE DE PAIE",FontFactory.getFont(FontFactory.TIMES_BOLD,20,Font.BOLD )));
			            myDocument.add(new Paragraph("Mois:"+ " "+mois,FontFactory.getFont(FontFactory.TIMES_BOLD,12,Font.ITALIC )));
			            myDocument.add(new Paragraph("No fiche:"+ " "+id_Fiche,FontFactory.getFont(FontFactory.TIMES_BOLD,12,Font.ITALIC )));
			            myDocument.add(new Paragraph("-------------------------------------------------------------------------------------------"));
			            myDocument.add((new Paragraph("DÉTAILS DE L'EMPLOYÉ(E)",FontFactory.getFont(FontFactory.TIMES_ROMAN,15,Font.BOLD))));
			            myDocument.add((new Paragraph("No Employé: "+no_Emp,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD))));
			            myDocument.add((new Paragraph("Nom: " + Prenom + " "+Nom,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD))));
			            myDocument.add((new Paragraph("Titre: "+Titre,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD))));
			            myDocument.add((new Paragraph("Département: "+NoDep+ " " +Dep,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD))));
			            myDocument.add(new Paragraph("Salaire de base: RS"+Salaire,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD)));

			            myDocument.add(new Paragraph("-------------------------------------------------------------------------------------------"));
			            myDocument.add(new Paragraph("À PAYER ",FontFactory.getFont(FontFactory.TIMES_ROMAN,15,Font.BOLD)));
			            myDocument.add(new Paragraph("Salaire Brut: RS "+ " "+ salBrut,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD)));
			            myDocument.add(new Paragraph("Salaire Net: RS"+ " "+ salNet,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD)));
			            myDocument.add(new Paragraph("Heures supplémentaires: "+ heureSup+" Heures",FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD)));
			            myDocument.add(new Paragraph("Bonus: Rs"+ " "+ bonus,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD)));
			            myDocument.add(new Paragraph("Commission: Rs"+ " "+ commission,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD)));
			            myDocument.add(new Paragraph("-------------------------------------------------------------------------------------------"));
			            myDocument.add(new Paragraph("DEDUCTION",FontFactory.getFont(FontFactory.TIMES_ROMAN,15,Font.BOLD)));
			            myDocument.add(new Paragraph("Détails de déduction: "+"Tax:",FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD)));
			            myDocument.add(new Paragraph("Déductions Totale : RS"+" " + deduction ,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD)));
			            myDocument.add(new Paragraph("-------------------------------------------------------------------------------------------"));
			            myDocument.add(new Paragraph("PAIEMENT TOTAL",FontFactory.getFont(FontFactory.TIMES_ROMAN,15,Font.BOLD)));
			            myDocument.add(new Paragraph("Totale : RS" +" " + paieTot,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD)));
			            myDocument.add(new Paragraph("-------------------------------------------------------------------------------------------"));
			            
			            
			            myDocument.newPage();
			            myDocument.close();  
			            JOptionPane.showMessageDialog(null,"Fiche de paie généré avec suuccès");
			            
			      }
			         catch(Exception e1){
			             JOptionPane.showMessageDialog(null,e1);
			          
			          
			      }
			      finally {
			             
			             try{
			               
			                 
			             }
			             catch(Exception e1){
			             JOptionPane.showMessageDialog(null,e1);
			          
			             }
			      }
			    } 
				
				
				
		
				
				
			}
		});
		layeredPane_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		layeredPane_1.setBounds(10, 66, 132, 204);
		frame.getContentPane().add(layeredPane_1);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
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
		btnAjouter.setBounds(10, 11, 112, 31);
		layeredPane_1.add(btnAjouter);
		
		JButton butSupprimer = new JButton("Supprimer");
		butSupprimer.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		butSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
	             JOptionPane.showMessageDialog(null,deduction);

				
				
				
			}
		});
		butSupprimer.setBounds(10, 74, 112, 31);
		layeredPane_1.add(butSupprimer);
		
		JButton btnNewButton = new JButton("Modifier");
		btnNewButton.setActionCommand("Modifier");
		btnNewButton.setBounds(10, 130, 112, 31);
		layeredPane_1.add(btnNewButton);
		
		JLabel lblNewLabel_5 = new JLabel("SuperCar");
		lblNewLabel_5.setBounds(10, 11, 108, 31);
		frame.getContentPane().add(lblNewLabel_5);
		
		JButton btnNewButton_1 = new JButton("Retour");
		btnNewButton_1.setBounds(528, 11, 89, 31);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_6 = new JLabel("Fiche de paie");
		lblNewLabel_6.setBounds(282, 0, 108, 31);
		frame.getContentPane().add(lblNewLabel_6);
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
