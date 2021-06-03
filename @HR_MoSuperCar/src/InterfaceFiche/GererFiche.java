package InterfaceFiche;

/**
 * Ce programme génère les fiches de paie des employés en format pdf
 * @author Lionel Perrine
 *
 */

import Fiche.*;
import connexionBDD.ConnectionFactoryX;
import executeurOpSql.Executeur;
import operationSQL.Operation;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.cj.x.protobuf.MysqlxCrud.Find;

import CompteUser.InterfaceCompte;
import CompteUser.InterfaceEmployes;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;


import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.SystemColor;

public class GererFiche {

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

	 private static final String MONTANT_REGEX =
	            "^([+-]?\\d*\\.?\\d*)$";

	 
	    private static final Pattern MONTANT_PATTERN = Pattern.compile(MONTANT_REGEX);


        FicheDePaie fiche = new FicheDePaie();//instantiation obj  de type fiche de paie


	
	Connection connection = ConnectionFactoryX.getConnection();
	//PreparedStatement preparedStatement = connection.prepareStatement(QueryStatement.searchQuery);
    java.sql.Statement  preparedStatement = connection.createStatement();
    private JTextField txtIdFiche;
    
    
    
	/**
	 * Launch the application.
	 * @param args l'argument du main
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GererFiche window = new GererFiche();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Créer l'application.
	 * @throws SQLException gère les erreurs SQL
	 * 
	 */
	public GererFiche() throws SQLException {
		initialize();
		comboEmp();
		
	}
	
	
	/**
	 * Cette Méthode récupère tous les infos de la fiche de paie saisies par l'utilisateurs  
	 */
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
	
	public void EffaceChamps() {
		
		txtDeduction.setText("");
		txtBonus.setText("");
		txtCommission.setText("");
		txtHeureSup.setText("");
		
	}
	
	
	/**
	 * Initialize les contenus du frame
	 * @throws SQLException : stop le programme en cas d'erreurs sql
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(238, 232, 170));
		frame.setBounds(100, 100, 773, 442);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		layeredPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		layeredPane.setBounds(152, 66, 570, 289);
		frame.getContentPane().add(layeredPane);
		
		cmbNoEmp = new JComboBox();
		cmbNoEmp.setBounds(151, 8, 262, 29);
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
				Calendar mCalendar = Calendar.getInstance();    
				String month = mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
			
			     String moisActuel = month;
			
			  try {
				no_Emp = cmbNoEmp.getSelectedItem().toString();
				  String input_string = no_Emp;
				  int number_output = Integer.parseInt(input_string.replaceAll("[^0-9]", ""));
				  no_Emp = String.valueOf(number_output);
				  System.out.print(no_Emp);
			  }catch(Exception ex) {
				  
					JOptionPane.showMessageDialog(null,"ERREUR!! vous n'avez pas sélectionné le numéro de l'employé");

				  
			  }
				 String searchQuery = "SELECT No_employe,Nom,Prenom,Titre,Salaire,Nodept,Nom_dept,Mois,HeureSup,Bonus,Commission,Deduction,Id_fch FROM employes E, departement D, fch_de_paie F WHERE E.Nodept = D.No_dept AND E.No_employe = F.No_Emp AND F.No_Emp = ? AND Mois = ?"; 
				 	 
				 		
					Connection connection = null;
					try {
						connection = ConnectionFactoryX.getConnection();
					
						PreparedStatement	preparedStatement = connection.prepareStatement(searchQuery);
						
						preparedStatement.setString(1, no_Emp); 
						preparedStatement.setString(2, moisActuel); 

						
						ResultSet resultSet = preparedStatement.executeQuery();
						
						if (resultSet.next()) {
							
							txtHeureSup.setText(resultSet.getString("HeureSup"));
							txtBonus.setText(resultSet.getString("Bonus"));
							txtCommission.setText(resultSet.getString("Commission"));
							txtDeduction.setText(resultSet.getString("Deduction"));
							
							  System.out.print(resultSet.getString("HeureSup"));

							
							bonus = txtDeduction.getText();
							commission = txtCommission.getText();
							heureSup = txtHeureSup.getText();
							no_Emp = cmbNoEmp.getSelectedItem().toString();
                          //gererEmployes E = new gererEmployes();
                          try {
							InterfaceEmployes.decryptClef();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                          
						    Nom = resultSet.getString(2);
						    Prenom = resultSet.getString(3);
						    NoDep = resultSet.getString(6);
						    Dep = resultSet.getString(7);
						    Salaire = InterfaceEmployes.decryptInString(resultSet.getString(5),InterfaceEmployes.getMasterKey());
						    mois  = resultSet.getString(8);
						    Titre  = resultSet.getString(4);
						    deduction  = resultSet.getString(12);
						    id_Fiche  = resultSet.getString(13);	
						    
						    txtIdFiche.setText(id_Fiche);
						    

							
						}else {
							JOptionPane.showMessageDialog(null, "Aucun résultat rétrouvé");

						    EffaceChamps();
						
							
						}
							
					} catch (SQLException e1) {
						
						
						// TODO Auto-generated catch block
						
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    
				

				     
				
				
				
				
				
				
				
				
				
			}
		});
		btnRehcercher.setBounds(445, 6, 115, 32);
		layeredPane.add(btnRehcercher);
		
		JButton btnGenereFiche = new JButton("G\u00E9nerer Fiche de paie");
		btnGenereFiche.setBounds(386, 201, 174, 31);
		layeredPane.add(btnGenereFiche);
		btnGenereFiche.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JButton btnGenereToutes = new JButton("Générer toutes les fiches de paie");
		btnGenereToutes.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnGenereToutes.setBounds(329, 247, 231, 31);
		layeredPane.add(btnGenereToutes);
		btnGenereToutes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// obtenir Mois actuel
				Calendar mCalendar = Calendar.getInstance();    
				String month = mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
			
			     String moisActuel = month;
				 String searchQuery = "SELECT No_employe,Nom,Prenom,Titre,Salaire,Nodept,Nom_dept,Mois,HeureSup,Bonus,Commission,Deduction,Id_fch FROM employes E, departement D, fch_de_paie F WHERE E.Nodept = D.No_dept AND E.No_employe = F.No_Emp AND Mois = ?"; 
				 	 
				 		
					Connection connection = null;
					try {
						connection = ConnectionFactoryX.getConnection();
					
						PreparedStatement	preparedStatement = connection.prepareStatement(searchQuery);
						
						preparedStatement.setString(1, moisActuel); 
						
						ResultSet resultSet = preparedStatement.executeQuery();
						
						while (resultSet.next()) {
							
							heureSup = (resultSet.getString("HeureSup"));
							bonus = (resultSet.getString("Bonus"));
							commission = (resultSet.getString("Commission"));
							deduction =(resultSet.getString("Deduction"));
							no_Emp =(resultSet.getString("No_employe"));

							
							  //System.out.print(resultSet.getString("HeureSup"));

							
							//bonus = txtDeduction.getText();
							//commission = txtCommission.getText();
							//heureSup = txtHeureSup.getText();
							//no_Emp = cmbNoEmp.getSelectedItem().toString();
                        //gererEmployes E = new gererEmployes();
                        try {
							InterfaceEmployes.decryptClef();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                        
						    Nom = resultSet.getString(2);
						    Prenom = resultSet.getString(3);
						    NoDep = resultSet.getString(6);
						    Dep = resultSet.getString(7);
						    Salaire = InterfaceEmployes.decryptInString(resultSet.getString(5),InterfaceEmployes.getMasterKey());
						    mois  = resultSet.getString(8);
						    Titre  = resultSet.getString(4);
						    deduction  = resultSet.getString(12);
						    id_Fiche  = resultSet.getString(13);	
						    
						    txtIdFiche.setText(id_Fiche);

							
							  /**
							   * JFileChooser dialog = new JFileChooser();
							   *   dialog.setSelectedFile(new File(Prenom +" "+ Nom+"-Fiche_de_paie"+".pdf"));
					         int dialogResult = dialog.showSaveDialog(null);
					         if (dialogResult==JFileChooser.APPROVE_OPTION){
					            String filePath = dialog.getSelectedFile().getPath()
							   */
							;
							 
					        String filePath = "C:\\Users\\user\\Documents\\@FicheDePaie\\" + Prenom +" "+ Nom+"-Fiche_de_paie-" + mois + ".pdf";
					            
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
					            
					          
					          
								
								fiche.set_noEmp(no_Emp);
					            myDocument.open();
					            myDocument.add(new Paragraph("-----------------------------Supercar LTD------------------------------",FontFactory.getFont(FontFactory.TIMES_BOLD,20,Font.BOLD)));
					            myDocument.add(new Paragraph("FICHE DE PAIE",FontFactory.getFont(FontFactory.TIMES_BOLD,17,Font.BOLD )));
					            myDocument.add(new Paragraph("Mois:"+ " "+mois,FontFactory.getFont(FontFactory.TIMES_BOLD,12,Font.ITALIC )));
					            myDocument.add(new Paragraph("No fiche:"+ " "+"0000"+id_Fiche,FontFactory.getFont(FontFactory.TIMES_BOLD,12,Font.ITALIC )));
					            myDocument.add(new Paragraph("-------------------------------------------------------------------------------------------"));
					            myDocument.add((new Paragraph("DÉTAILS DE L'EMPLOYÉ(E)",FontFactory.getFont(FontFactory.TIMES_ROMAN,15,Font.BOLD))));
					            myDocument.add((new Paragraph("No Employé: "+no_Emp,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD))));
					            myDocument.add((new Paragraph("Nom: " + Prenom + " "+Nom,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD))));
					            myDocument.add((new Paragraph("Titre: "+Titre,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD))));
					            myDocument.add((new Paragraph("Département: "+NoDep+ " " +Dep,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD))));
					            myDocument.add(new Paragraph("Salaire de base: RS"+ " " +Salaire,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD)));

					            myDocument.add((new Paragraph("-------------------------------------------------------------------------------------------")));
					            myDocument.add((new Paragraph("À PAYER:",FontFactory.getFont(FontFactory.TIMES_ROMAN,15,Font.BOLD))));
					            myDocument.add(new Paragraph("Salaire Brut: RS "+ " "+ salBrut,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD)));
					            myDocument.add(new Paragraph("Salaire Net: RS"+ " "+ salNet,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD)));
					            myDocument.add(new Paragraph("Heures supplémentaires: "+ heureSup+" Heures",FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD)));
					            myDocument.add(new Paragraph("Bonus: Rs"+ " "+ bonus,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD)));
					            myDocument.add(new Paragraph("Commission: Rs"+ " "+ commission,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD)));
					            myDocument.add(new Paragraph("-------------------------------------------------------------------------------------------"));
					            myDocument.add(new Paragraph("DÉDUCTION",FontFactory.getFont(FontFactory.TIMES_ROMAN,15,Font.BOLD)));
					            myDocument.add(new Paragraph("Détails de déduction: "+"Tax:",FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD)));
					            myDocument.add(new Paragraph("Déductions Totale : RS"+" " + deduction ,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD)));
					            myDocument.add(new Paragraph("-------------------------------------------------------------------------------------------"));
					            myDocument.add(new Paragraph("PAIEMENT TOTAL",FontFactory.getFont(FontFactory.TIMES_ROMAN,15,Font.BOLD)));
					            myDocument.add(new Paragraph("Totale : RS" +" " + paieTot,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD)));
					            myDocument.add(new Paragraph("-------------------------------------------------------------------------------------------"));
					            
					            
					            myDocument.newPage();
					            myDocument.close();  
					            
					            JOptionPane op = new JOptionPane("Fiches de paie générées avec succès", JOptionPane.INFORMATION_MESSAGE);
				                JDialog dialogx = op.createDialog("server self-test exception");
				        
				                 // Create a new timer
				        Timer timer = new Timer();
				 
				                 // Perform this task after 30 seconds
				        timer.schedule(new TimerTask() {
				            public void run() {
				                dialogx.setVisible(false);
				                dialogx.dispose();
				            }
				        }, 3000);
				 
				        dialogx.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				        dialogx.setAlwaysOnTop(true);
				        dialogx.setModal(false);
				        dialogx.setVisible(true);
				    

					            
					        
					         }catch(Exception e1){
					             JOptionPane.showMessageDialog(null,e1);
					          
					          
					         } finally {
					     
					             
					             try{
					          
					                 }catch(Exception e1){
					                   JOptionPane.showMessageDialog(null,e1);
					          
					             }
					         }
					    
						    
						  	
						}	
					}
					 catch (SQLException e1) {
							
				
						
						
						// TODO Auto-generated catch block
						
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    

			}
		});
		btnGenereFiche.addActionListener(new ActionListener() {
			/**
			 *
			 */
			public void actionPerformed(ActionEvent e) {
				
					 JFileChooser dialog = new JFileChooser();
			         dialog.setSelectedFile(new File(Prenom +" "+ Nom+"-Fiche_de_paie" + mois+".pdf"));
			         int dialogResult = dialog.showSaveDialog(null);
			         if (dialogResult==JFileChooser.APPROVE_OPTION){
			             String filePath = dialog.getSelectedFile().getPath();
			            
			         try {
			             
			             
			        	 double salTot = Double.parseDouble(Salaire);
				            
				            double deduc = Double.parseDouble(deduction);
				            double salBrut = salTot - deduc;
				            
				            double tax = 0.10 * salBrut;
				            double salNet = salBrut - tax;
				            
				            double com = Double.parseDouble(commission);
				            double  bon = Double.parseDouble(bonus);

				            double paieTot = salNet + com + bon;
				            
				            //double payTot = salNet+ bonus.valueOf(i)+ commission;
			        	
			             //String val = String.valueOf(x);
			             
			       
			            Document myDocument = new Document();
			            PdfWriter myWriter = PdfWriter.getInstance(myDocument, new FileOutputStream(filePath));
			            
			            no_Emp = cmbNoEmp.getSelectedItem().toString();
						  String input_string = no_Emp;
						  int number_output = Integer.parseInt(input_string.replaceAll("[^0-9]", ""));
						  no_Emp = String.valueOf(number_output);
						  System.out.print(no_Emp);
			          
						
						fiche.set_noEmp(no_Emp);
			            myDocument.open();
			            myDocument.add(new Paragraph("-----------------------------Supercar LTD------------------------------",FontFactory.getFont(FontFactory.TIMES_BOLD,20,Font.BOLD)));
			            myDocument.add(new Paragraph("FICHE DE PAIE",FontFactory.getFont(FontFactory.TIMES_BOLD,20,Font.BOLD )));
			            myDocument.add(new Paragraph("Mois:"+ " "+mois,FontFactory.getFont(FontFactory.TIMES_BOLD,12,Font.ITALIC )));
			            myDocument.add(new Paragraph("No fiche:"+ " "+"0000"+id_Fiche,FontFactory.getFont(FontFactory.TIMES_BOLD,12,Font.ITALIC )));
			            myDocument.add(new Paragraph("-------------------------------------------------------------------------------------------"));
			            myDocument.add((new Paragraph("DÉTAILS DE L'EMPLOYÉ(E)",FontFactory.getFont(FontFactory.TIMES_ROMAN,15,Font.BOLD))));
			            myDocument.add((new Paragraph("No Employé: "+no_Emp,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD))));
			            myDocument.add((new Paragraph("Nom: " + Prenom + " "+Nom,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD))));
			            myDocument.add((new Paragraph("Titre: "+Titre,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD))));
			            myDocument.add((new Paragraph("Département: "+NoDep+ " " +Dep,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD))));
			            myDocument.add(new Paragraph("Salaire de base: RS"+ " " +Salaire,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD)));

			            myDocument.add((new Paragraph("-------------------------------------------------------------------------------------------")));
			            myDocument.add((new Paragraph("À PAYER:",FontFactory.getFont(FontFactory.TIMES_ROMAN,15,Font.BOLD))));
			            myDocument.add(new Paragraph("Salaire Brut: RS "+ " "+ salBrut,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD)));
			            myDocument.add(new Paragraph("Salaire Net: RS"+ " "+ salNet,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD)));
			            myDocument.add(new Paragraph("Heures supplémentaires: "+ heureSup+" Heures",FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD)));
			            myDocument.add(new Paragraph("Bonus: Rs"+ " "+ bonus,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD)));
			            myDocument.add(new Paragraph("Commission: Rs"+ " "+ commission,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD)));
			            myDocument.add(new Paragraph("-------------------------------------------------------------------------------------------"));
			            myDocument.add(new Paragraph("DÉDUCTION",FontFactory.getFont(FontFactory.TIMES_ROMAN,15,Font.BOLD)));
			            myDocument.add(new Paragraph("Détails de déduction: "+"Tax:",FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD)));
			            myDocument.add(new Paragraph("Déductions Totale : RS"+" " + deduction ,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD)));
			            myDocument.add(new Paragraph("-------------------------------------------------------------------------------------------"));
			            myDocument.add(new Paragraph("PAIEMENT TOTAL",FontFactory.getFont(FontFactory.TIMES_ROMAN,15,Font.BOLD)));
			            myDocument.add(new Paragraph("Totale : RS" +" " + paieTot,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD)));
			            myDocument.add(new Paragraph("-------------------------------------------------------------------------------------------"));
			            
			            
			            myDocument.newPage();
			            myDocument.close();  
			            JOptionPane.showMessageDialog(null,"Fiche de paie génère avec succès");
			            
			      }catch(Exception e1){
			        JOptionPane.showMessageDialog(null,e1);
			         
			          
			          
			      }
			      finally {
			             
			             try{
			               
			                 
			             }catch(Exception e1){
				             JOptionPane.showMessageDialog(null,e1);
					          
					     }
			             
			      }
			    } 
				
						
				
			}
		});
		layeredPane_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		layeredPane_1.setBounds(10, 66, 132, 247);
		frame.getContentPane().add(layeredPane_1);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				       getFicheInfos();// appel méthode pour affecter valeurs dans les variables
				       
				        
				        //manipulation avec des propriétés privées avec méthode set
				      

						//boolean error = false;

				//if (controleSaisie(error) == false) { // s'il n'y a pas d'erreur
						idFiche = txtIdFiche.getText();

					    fiche.set_Bonus(bonus);
						fiche.set_Commission(commission);
						fiche.set_Deduction(deduction);
						fiche.set_heureSup(heureSup);
						fiche.set_idFiche(idFiche);
						fiche.set_Mois(mois);
						
						try {
						  String input_string = no_Emp;
						  int number_output = Integer.parseInt(input_string.replaceAll("[^0-9]", ""));
						  no_Emp = String.valueOf(number_output);
						
						  fiche.set_noEmp(no_Emp);
						}catch(Exception e1) {
							
							 JOptionPane.showMessageDialog(frame,"ERREUR.. REMPLISSEZ TOUS LES CHAMPS..");

		
						}
						
						 if (!MONTANT_PATTERN.matcher(fiche.get_Bonus()).matches() ){
							 
							 JOptionPane.showMessageDialog(frame,"ERREUR.. MONTANT BONUS INVALIDE");
							 txtBonus.requestFocusInWindow();
								
						 }else if(!MONTANT_PATTERN.matcher(fiche.get_Deduction()).matches()) {
							 
							 JFrame frame = new JFrame("retour");
							 JOptionPane.showMessageDialog(frame,"ERREUR MONTANT DEDUCTION INVALIDE");
							 txtDeduction.requestFocusInWindow();
							 
						 }else if (!MONTANT_PATTERN.matcher(fiche.get_heureSup()).matches())                                     {
							 
							 JFrame frame = new JFrame("retour");
							 JOptionPane.showMessageDialog(frame,"ERREUR MONTANT HEURE SUP INVALIDE");
							 txtHeureSup.requestFocusInWindow();
							 
						 }else if (!MONTANT_PATTERN.matcher(fiche.get_Commission()).matches()) {
							 
							 JFrame frame = new JFrame("retour");
							 JOptionPane.showMessageDialog(frame,"ERREUR MONTANT COMMISSION INVALIDE");
							 txtCommission.requestFocusInWindow();
							 
						 } else if (no_Emp.isEmpty()){
							 
							 JFrame frame = new JFrame("retour");
							 JOptionPane.showMessageDialog(frame,"ERREUR VOUS N'AVEZ PAS SELECTIONNER UN ID");
							 
							 
						 } else {
						 
						
							 
							 fiche.addFiche();// appel méthode de la classe mère FiecheDePaie
							 EffaceChamps();
							 JOptionPane.showMessageDialog(frame,"recherchez la fiche nouvelle fiche de paie pour la générée..");

						 }
							 
						
						
				

			
			    //}
				
			}
		});
		btnAjouter.setBounds(10, 11, 112, 31);
		layeredPane_1.add(btnAjouter);
		
		JButton butSupprimer = new JButton("Supprimer");
		butSupprimer.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		butSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
	             //JOptionPane.showMessageDialog(null,deduction);
				idFiche = txtIdFiche.getText();

				 String delQuery = "DELETE FROM fch_de_paie WHERE Id_fch =?";
				try {
					Connection connection = ConnectionFactoryX.getConnection();
				
			         PreparedStatement  ps= connection.prepareStatement(delQuery);

			         ps.setString(1, idFiche);
			         
					 int confirm = JOptionPane.showConfirmDialog(null,"voulez-vous vraiment effacer cet enregistrement?","fermer",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
						if (confirm == JOptionPane.YES_OPTION) {
					
							ps.executeUpdate();
						
						JOptionPane.showMessageDialog(frame,"Fiche de paie supprimmée)");
                      
                         EffaceChamps();
						}else {
							
							System.out.print("");
							
						}
	
					
				} catch (SQLException e1) {
					JFrame frame = new JFrame("error");
					JOptionPane.showMessageDialog(frame, e1);
					e1.printStackTrace();
				}
				
				
			}
		});
		butSupprimer.setBounds(10, 74, 112, 31);
		layeredPane_1.add(butSupprimer);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			try {	
				//no_Emp = cmbNoEmp.getSelectedItem().toString();
				idFiche = txtIdFiche.getText();
				getFicheInfos();
				
			}catch(Exception ex) {
				
				JOptionPane.showMessageDialog(frame,"EURREUR!! Les champs sont vides...");

			}
				
				
				
				
				
				 String UpdateQuery = "UPDATE fch_de_paie SET Mois=?,HeureSup=?,Bonus=?,Commission=?,Deduction=? WHERE Id_fch=?";
				try {
					Connection connection = ConnectionFactoryX.getConnection();
				
			         PreparedStatement  ps= connection.prepareStatement(UpdateQuery);

			         ps.setString(1, mois);
			         ps.setString(2,heureSup);
			         ps.setString(3, bonus);
			         ps.setString(4,commission);
			         ps.setString(5, deduction);
			         ps.setString(6,idFiche);
			        	
					
			             ps.executeUpdate();
						
						JOptionPane.showMessageDialog(frame,"Employé(e) modifié(e)");
						
							
					
				} catch (SQLException e1) {
					JFrame frame = new JFrame("error");
					JOptionPane.showMessageDialog(frame, e1);
					e1.printStackTrace();
				}
					
			}
		});
		btnModifier.setActionCommand("Modifier");
		btnModifier.setBounds(10, 145, 112, 31);
		layeredPane_1.add(btnModifier);
		
		JButton btnEffaceChamps = new JButton("Effacer Champs");
		btnEffaceChamps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EffaceChamps();
			}
		});
		btnEffaceChamps.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnEffaceChamps.setBounds(10, 205, 112, 31);
		layeredPane_1.add(btnEffaceChamps);
		
		JButton btnNewButton_1 = new JButton("Retour");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GererFiche.this.frame.setVisible(false);
				
				
			}
		});
		btnNewButton_1.setBounds(658, 11, 89, 31);
		frame.getContentPane().add(btnNewButton_1);
		
		txtIdFiche = new JTextField();
		txtIdFiche.setBounds(24, 412, 96, 20);
		frame.getContentPane().add(txtIdFiche);
		txtIdFiche.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Fiche De Paie");
		lblNewLabel_5.setBounds(355, 0, 96, 36);
		frame.getContentPane().add(lblNewLabel_5);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public void comboEmp() throws SQLException{
		
		 String searchQuery = "SELECT No_employe,Prenom,Nom FROM employes";
			 connection = ConnectionFactoryX.getConnection();
			//PreparedStatement preparedStatement = connection.prepareStatement(QueryStatement.searchQuery);
	        preparedStatement = connection.createStatement();

			@SuppressWarnings("unused")
			ResultSet resultSet = preparedStatement.executeQuery(searchQuery);
			
			cmbNoEmp.addItem("");
		try {
			
			while (resultSet.next()) {
				
				   cmbNoEmp.addItem(resultSet.getString("No_employe")+ "--" + resultSet.getString("Prenom")+ "--" + resultSet.getString("Nom"));
				   
			}
		}				
			
		catch(SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
					
			}	
		
				    
	       
		
	}
}
