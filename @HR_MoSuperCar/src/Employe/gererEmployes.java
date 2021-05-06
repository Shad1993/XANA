package Employe;

import java.awt.EventQueue;

import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.ScrollPane;
import javax.swing.JScrollPane;

import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.example.utilities.DBUtil;
import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.example.constants.CRUDMode;
import com.example.constants.QueryStatement;
import com.example.db.ConnectionFactory;
import com.example.model.Employe;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import java.awt.SystemColor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import CompteUser.Compte;
import CompteUser.InterfaceCompte;
import InterfaceFiche.gererFiche;
import LeMenu.Menu;

import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Component;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;


import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.MessageDigest;//
import java.util.Base64;//
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;//



public class gererEmployes {

	private JFrame frame;
	//private final JScrollPane scrollPane = new JScrollPane();
	public JTable table;
	//public JScrollPane scrollPane;
	private JTextField txtNoEmp;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtNIC;
	private JTextField txtAdresse;
	private JTextField txtEmail;
	private JTextField txtNoContact;
	private JTextField txtSalaire;
	private JDateChooser dateChooserDOB ;
	private JDateChooser dateChooserAnne ;
	private JTextFieldDateEditor editor;
	private JTextFieldDateEditor editorx;
	private JButton btnAjouter;
	private JButton btnModifier;
	private JButton btnEffChamps;
	private JButton btnRetour1;
	private static Key masterKey;

	private static Connection connection;
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet = null;

	

	@SuppressWarnings("rawtypes")
	private JComboBox cmbTitre;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbSexe;

	
	private JTextField txtCommission;
	
	@SuppressWarnings("rawtypes")
	private JComboBox cmbDep;
	private static String NoEmp;
	private static String Nom;
	private static String Prenom;
	private static String NIC;
	private static String DOB;
	private static String Adresse;
	private static String Email;
	private static String NoContact;
	private static String Embauche;
	private static String Salaire;
	private static String Titre;
	private static String Commission;
	private static String Dep;
	//private static String NomDep;
	private static String Sexe;
	
	private final JLayeredPane layeredPane = new JLayeredPane();
	private final JLayeredPane layeredPane_1 = new JLayeredPane();
	private static JTextField txtRechercher;
	private JTextField txtNomDep;
	private JLabel lblNewLabel_5;
	private JButton btnNewButton;
	private JLabel lblNewLabel_6;
	private JButton button;
	private JButton btnRetour;
	
	// méthodes getter et setter pour accèder aux variables privés masterKey
		public static Key getMasterKey() {
			return masterKey;
		}

		public static void setMasterKey(Key masterKey) {
			gererEmployes.masterKey = masterKey;
		}

		
		//Méthode qui va décrypter le fichier clef.crp là ou la clef hexadecimal est stocké
		public static void decryptClef() throws IOException {
			String code = new String(Files.readAllBytes(Paths.get("clef.cryp")));
			byte[] clefDecode = Base64.getDecoder().decode(code);
			setMasterKey(new SecretKeySpec(clefDecode, 0, clefDecode.length, "blowfish"));
			//masterkey de blowfish utilisant methode set masterkey
		}

		
		
		//methode de chiffrement des donées en octets par ApiBlowfish.decryptInByte
		public static byte[] encryptInByte(byte[] chaineEnClaire, Key clef) throws Exception {

			Cipher crypter = Cipher.getInstance("Blowfish");

			crypter.init(Cipher.ENCRYPT_MODE, clef);

			return crypter.doFinal(chaineEnClaire); // retourne au format tableau d'octets
		}

		

		//Methode pour déchiffrer les octets par la même faç de la méthode précédente
		//retourne le dechiffremnt en octet
		public static byte[] decryptInByte(byte[] chiffrement, Key clef) throws Exception {

			Cipher decrypter = Cipher.getInstance("Blowfish");

			decrypter.init(Cipher.DECRYPT_MODE, clef);

			byte[] chaineDecrypter = decrypter.doFinal(chiffrement);

			return chaineDecrypter;
		}

		
	   // methode pour chiffrer les chaines de caractères
		public static String encryptInString(String chaineEnClair, Key clef) throws Exception {

			byte[] chaine = chaineEnClair.getBytes();

			chaine = encryptInByte(chaine, clef);

			return Base64.getEncoder().encodeToString(chaine);

		}
		
		// Méthode pour déchiffrer chaine de caractères qui retourne  decryptée claire en chaine
		public static String decryptInString(String chiffrement, Key clef) throws Exception {


			//chaine décodé en base64
			byte[] decrypter = Base64.getDecoder().decode(chiffrement);

			decrypter = decryptInByte(decrypter, clef);

			return new String(decrypter); 
		}
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gererEmployes window = new gererEmployes();
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
	public gererEmployes()  throws SQLException{
		initialize();
		comboDep();
		//btnAjouter.setEnabled(false);//Désactiver bouton insertion
		btnModifier.setEnabled(false);//Désactiver bouton Mise à jour
		
		button = new JButton("Fiche de paie");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				//gererEmployes.this.frame.setVisible(false);
				gererFiche.main(null);
				
				
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setBounds(1101, 29, 199, 39);
		layeredPane_1.add(button);
		
		lblNewLabel_5 = new JLabel("SuperCar");
		lblNewLabel_5.setFont(new Font("Yu Gothic UI", Font.ITALIC, 18));
		lblNewLabel_5.setBounds(24, 11, 111, 34);
		frame.getContentPane().add(lblNewLabel_5);
		
		btnNewButton = new JButton("Déconnexion");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBackground(new Color(127, 255, 0));
		btnNewButton.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton.setBounds(1481, 13, 111, 34);
		frame.getContentPane().add(btnNewButton);
		
		lblNewLabel_6 = new JLabel("Departement Des Ressources Humaines");
		lblNewLabel_6.setBackground(new Color(192, 192, 192));
		lblNewLabel_6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblNewLabel_6.setFont(new Font("Palatino Linotype", Font.BOLD, 18));
		lblNewLabel_6.setBounds(686, 11, 335, 48);
		frame.getContentPane().add(lblNewLabel_6);
		
		btnRetour1 = new JButton("Retour");
		btnRetour1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 
				 Menu.main(null);
				 gererEmployes.this.frame.setVisible(false);

				
			}
		});
		btnRetour1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRetour1.setBounds(1324, 13, 111, 32);
		frame.getContentPane().add(btnRetour1);
	
		//checkAnn();
	}
	
	private static Employe empInfos(CRUDMode mode) {
		Employe employe = new Employe();
		
		// opérations

		//CRUDMode.UPDATE n'a pas marché a été enlevé, autre soulution trouvée.. dans bouton Modifié
		if (mode.equals(CRUDMode.ADD) || mode.equals(CRUDMode.DELETE)) {
			
			if (mode.equals(CRUDMode.DELETE)) {
				employe.setNo_employe(NoEmp);
			}
			
			
		// fonctions set permettant de manipuler les variables privés
			employe.setNo_employe(NoEmp);
			employe.setNom(Nom);
			employe.setPrenom(Prenom);
			employe.setNIC(NIC);
			employe.setDOB(DOB);
			employe.setSexe(Sexe);
			employe.setAdresse(Adresse);
			employe.setEmail(Email);
			employe.setNo_contact(NoContact);
			employe.setDateDembauche(Embauche);
			employe.setTitre(Titre);
			employe.setComission(Commission);
			employe.setNo_dept(Dep);
			//employe.setNo_dept(NomDep);
			employe.setSalaire(Salaire);

		
	   }
		return employe;
		
			//[A-Za-zÀ-ȕ]([\\w -]*[A-Za-zÀ-ȕ])

	}
	
	
	private boolean verification(boolean alerte) {
		frame = new JFrame();
		if (Pattern.matches("[1-9]+", NoEmp) == false || NoEmp.equalsIgnoreCase("")) {
			txtNoEmp.setText("");
			JOptionPane.showMessageDialog(frame, "ERREUR, INCID INVALIDE");
			alerte = true;
		}
		return alerte;
	}
	
	
	
	
	private boolean controleSaisie(boolean SignalErreur) {
		frame = new JFrame();
		if (Pattern.matches("[0-9]{7,}", NoContact) == false || NoContact.equalsIgnoreCase("")) {

			JOptionPane.showMessageDialog(frame, "ERREUR, No contact INVALIDE");
			txtNoContact.setBackground(new Color(255, 186, 186));
			txtNoContact.requestFocusInWindow();
			
			SignalErreur = true;
			
		//^(0|[1-9]\d*)(\.\d+)?$
		} else if (Pattern.matches("[a-zA-ZÀ-ÿ0-9]+(([',. -][a-zA-ZÀ-ÿ ])?[a-zA-ZÀ-ÿ]*)*$", Adresse) == false || Adresse.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(frame, "ERREUR, ADRESSE INVALIDE");
			txtAdresse.setBackground(new Color(255, 186, 186));
			txtAdresse.requestFocusInWindow();
			
			SignalErreur = true;
	
		}else if (Pattern.matches("[a-zA-ZÀ-ÿ]+(([',. -][a-zA-ZÀ-ÿ ])?[a-zA-ZÀ-ÿ]*)*$", Nom) == false || Nom.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(frame, "ERREUR, NOM INVALIDE");
			txtNom.setBackground(new Color(255, 186, 186));
			txtNom.requestFocusInWindow();
			SignalErreur = true;
	
		}else if (Pattern.matches("[a-zA-ZÀ-ÿ]+(([',. -][a-zA-ZÀ-ÿ ])?[a-zA-ZÀ-ÿ]*)*$", Prenom) == false || Prenom.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(frame, "ERREUR, PRENOM INVALIDE");
			
			txtPrenom.setBackground(new Color(255, 186, 186));
			txtPrenom.requestFocusInWindow();
			SignalErreur = true;
			
			
	//[A-Za-z]
		}else if (Pattern.matches("^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{14}$", NIC) == false || NIC.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(frame, "ERREUR, NIC INVALIDE");
			txtNIC.setBackground(new Color(255, 186, 186));
			txtNIC.requestFocusInWindow();
			SignalErreur = true;
	
		}else if (Pattern.matches("\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$", Email) == false || Email.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(frame, "ERREUR, Email INVALIDE!!");
			txtEmail.setBackground(new Color(255, 186, 186));
			txtEmail.requestFocusInWindow();
			SignalErreur = true;
			
		}else if (Pattern.matches("^(0|[1-9]\\d*)(\\.\\d+)?$", Salaire) == false ||Salaire.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(frame, "ERREUR,SALAIRE INVALIDE");
			txtSalaire.setBackground(new Color(255, 186, 186));
			txtSalaire.requestFocusInWindow();
			SignalErreur = true;
			
		}else if (Pattern.matches("^(0|[1-9]\\d*)(\\.\\d+)?$", Commission) == false ||Commission.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(frame, "ERREUR! COMMISSION INVALIDE!");
			txtCommission.setBackground(new Color(255, 186, 186));
			txtCommission.requestFocusInWindow();
			SignalErreur = true;
		}
		
	     
        if (DOB.isEmpty()) {
			
			JFrame frame = new JFrame("erreur");
			JOptionPane.showMessageDialog(frame,"Entrez une date de naissance");
			dateChooserDOB.setBackground(new Color(255, 186, 186));
			
		}
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy"); 
        
	    Date date1 = new Date();  
        String dateNow1 = formatter.format(date1);
        
	    int dateNowInt1 = Integer.parseInt(dateNow1);
        
        
		// Vérifier si l'année est numérique
   
		String saisieDOB = DOB.substring(0,4);    //input string
		 
	
		 int dateIntDOB = Integer.parseInt(saisieDOB); //convertion de l'année string en Integer
		 
		 if(dateIntDOB % 1 != 0 || dateIntDOB > dateNowInt1) {
			 

		    	JFrame frame = new JFrame("erreur");
				JOptionPane.showMessageDialog(frame,"Erreur date de n'aissance");	
				SignalErreur = true;

			 	
		 }
   
		 
		 

		  Embauche = ((JTextField)dateChooserAnne.getDateEditor().getUiComponent()).getText();
		  
		  if (Embauche.isEmpty()) {
				
				JFrame frame = new JFrame("erreur");
				JOptionPane.showMessageDialog(frame,"Entrez une date de d'embauche");			
		  }
		  
		  SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy"); 
		    Date date2 = new Date();  
	        String dateNow2 = formatter2.format(date2);
	        
		    int dateNowInt2 = Integer.parseInt(dateNow2);
		  
			
			String dateEmb = Embauche.substring(0,4);    //input string
			 
			int dateInt2= Integer.parseInt(dateEmb); //convertion de l'année string en Integer
			
			   
			// Condition pour interdir les année < ou > à l'année actuelle
			  if(dateInt2 > dateNowInt2 || dateInt2 < dateNowInt2  ){
				    
			    	JFrame frame = new JFrame("erreur");
					JOptionPane.showMessageDialog(frame, "l'année d'embauche ne peut être supérieure ou infèrieure à l'année actuelle!");	
					SignalErreur = true;
					
					//changer la couleur du datechooser en rouge (warning)
					editorx.setBackground(new Color(255, 186, 186));
					
			  }
		
		
				Dep	=	cmbDep.getSelectedItem().toString();
				
		
		
		
		return SignalErreur;
	}	

	
	
	public void getEmpInfos() 							{
		//try {
			//decryptClef();
		//} catch (IOException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		//}

	    //NoEmp	= txtNoEmp.getText();
		Nom		= txtNom.getText();
		Prenom	= txtPrenom.getText();
		NIC		= txtNIC.getText();
	    //DOB		= txtDOB.getText();
		DOB = ((JTextField)dateChooserDOB.getDateEditor().getUiComponent()).getText();
		Sexe = cmbSexe.getSelectedItem().toString();
		Adresse	= txtAdresse.getText();
		Email	= txtEmail.getText();
		NoContact	= txtNoContact.getText();
		Titre = cmbTitre.getSelectedItem().toString();
		Salaire = txtSalaire.getText();
		Dep = cmbDep.getSelectedItem().toString();
	
			//Salaire = encryptInString(Salaire,getMasterKey());
		
	 	if (Salaire.length() > 10) {
	   		
		   	 
	    	JFrame frame = new JFrame("erreur");
			JOptionPane.showMessageDialog(frame,"Salaire invalide!! 7 chiffres max");
	   		
	   		txtCommission.setText("");
	   		
	   		
	   		
	   	}
	 	
	 	
		
	 	
		Commission	=	txtCommission.getText();
		
	   	if (Commission.length() > 8) {
	   		
	   	 
	    	JFrame frame = new JFrame("erreur");
			JOptionPane.showMessageDialog(frame,"Commision invalide!!le format doit être: 00.00");
	   		
	   		txtCommission.setText("");
	   		
	   		
	   		
	   	}

		
		
				//if (Dep.isEmpty()) {
					
					//JFrame frame = new JFrame("erreur");
					//JOptionPane.showMessageDialog(frame,"Entrez un departement");
			   					
					
				//}
				
				//String input = Dep;     //input string
				//String noDep = "";     //initialization
				 
				//if (input.length() > 4) 
				//{
				   // noDep = input.substring(input.length() - 2);// recupère le noEmp dan cobobox Dep-noDep

				    //Dep = noDep;
				    
				//} 
				//else
				//{
				   // noDep = input;
				//}
		
		
	}
	
	// méthode qui éfface tous les champs pour insertion
	public void effaceChamps() {
		
	
	
		btnAjouter.setEnabled(true);// activer buton ajouter pour insertion
		
		 txtNoEmp.setText("");
		 txtNom.setText("");
		 txtPrenom.setText("");
		 txtAdresse.setText("");
		 txtNIC.setText("");
		 txtNoContact.setText("");
		 txtSalaire.setText("");
		 txtNomDep.setVisible(false);// champ departement invisible
		 txtEmail.setText("");
		 txtCommission.setText("");
		 dateChooserDOB.setCalendar(null);
		 dateChooserAnne.setCalendar(null);
		 cmbTitre.setSelectedIndex(0);
		 cmbSexe.setSelectedIndex(0);
		 cmbDep.setSelectedIndex(0);	
		 
		 txtNom.requestFocusInWindow();// place le curseeur sur le champ Nom
		
	}
	

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	//@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(100, 100, 1642, 876);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			
		});
		scrollPane.setBounds(new Rectangle(397, 159, 1195, 515));
		
        
		frame.getContentPane().add(scrollPane);
		
		
		
		//table.setModel(new DefaultTableModel());//y avait erreur NullPointer
		//cmbDep = new JComboBox();
		table = new JTable();
		table.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		table.setAlignmentX(Component.RIGHT_ALIGNMENT);
		table.setShowVerticalLines(false);
		table.setUpdateSelectionOnSort(false);
		table.setSurrendersFocusOnKeystroke(true);
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		table.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("null")
			@Override
			public void mouseClicked(MouseEvent e) {
				
		        txtNomDep.setVisible(true);

			     btnAjouter.setEnabled(false); //Activer bouton d'insertion
			     btnModifier.setEnabled(true); // Ativer bouton Mise à jour
				   
				int i = table.getSelectedRow();
		        TableModel model = table.getModel();
		        
		          //Display Slected Row In JTexteFields
		        txtNoEmp.setText(model.getValueAt(i,0).toString());

		        txtNom.setText(model.getValueAt(i,1).toString());
		        
		        txtPrenom.setText(model.getValueAt(i,2).toString());

		        txtNIC.setText(model.getValueAt(i,3).toString());
		        
			    cmbDep.setSelectedItem(model.getValueAt(i,13).toString());

		        txtNomDep.setText(model.getValueAt(i,14).toString());

		        
			    cmbSexe.setSelectedItem(model.getValueAt(i,5).toString());
			    
			    
			    
				//String tc = table.getModel().getValueAt(i, 13).toString();
				//String tc2 = table.getModel().getValueAt(i, 14).toString();

				//String X = tc + "-" +  tc2;
					//cmbDep.setSelectedItem(X);

		        txtAdresse.setText(model.getValueAt(i,6).toString());
		        
		        txtEmail.setText(model.getValueAt(i,7).toString());
		        
		        txtNoContact.setText(model.getValueAt(i,8).toString());
		        
				cmbTitre.setSelectedItem(model.getValueAt(i,9).toString());
		       txtSalaire.setText(model.getValueAt(i,10).toString());
		        txtCommission.setText(model.getValueAt(i,12).toString());
		        txtCommission.setText(model.getValueAt(i,12).toString());
		        
		        
		        Date date = null;
				try {
					date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(i, 4));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
		         dateChooserDOB.setDate(date);

		         Date date2 = null;
					try {
						date2 = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(i, 11));
					} catch (ParseException e1) {
					 //TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        
			         dateChooserAnne.setDate(date2);
		        
		      
		        
				
			}
		});
		table.setBackground(new Color(255, 250, 205));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));

		scrollPane.setViewportView(table);
		
		//Affiche tous les employés
		//DBUtil affichage = new DBUtil();
		//affichage.getAllEmployees(table);
               getAllEmployees(table);
		layeredPane.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		layeredPane.setBounds(6, 159, 381, 515);
		frame.getContentPane().add(layeredPane);
		
		JLabel lblNewLabel = new JLabel("No Employ\u00E9");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBackground(UIManager.getColor("List.selectionBackground"));
		lblNewLabel.setForeground(SystemColor.desktop);
		lblNewLabel.setBounds(10, 11, 80, 17);
		layeredPane.add(lblNewLabel);
		
		txtNoEmp = new JTextField();
		txtNoEmp.setEditable(false);
		txtNoEmp.setForeground(Color.RED);
		txtNoEmp.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtNoEmp.setBounds(131, 7, 61, 25);
		layeredPane.add(txtNoEmp);
		txtNoEmp.setColumns(10);

		JLabel lblNom = new JLabel("Nom");
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNom.setBounds(10, 39, 61, 34);
		layeredPane.add(lblNom);
		
		txtNom = new JTextField();
		txtNom.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtNom.setBounds(131, 46, 241, 25);
		layeredPane.add(txtNom);
		txtNom.setColumns(10);
		
		JLabel lblPrnom = new JLabel("Pr\u00E9nom");
		lblPrnom.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrnom.setBounds(10, 84, 61, 20);
		layeredPane.add(lblPrnom);
		
		txtPrenom = new JTextField();
		txtPrenom.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtPrenom.setBounds(131, 82, 241, 25);
		layeredPane.add(txtPrenom);
		txtPrenom.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("NIC");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 121, 48, 14);
		layeredPane.add(lblNewLabel_1);
		
		txtNIC = new JTextField();
		txtNIC.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtNIC.setBounds(131, 115, 228, 28);
		layeredPane.add(txtNIC);
		txtNIC.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("DOB");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 158, 48, 14);
		layeredPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Sexe");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(10, 195, 48, 14);
		layeredPane.add(lblNewLabel_3);
		
		cmbSexe = new JComboBox();
		cmbSexe.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbSexe.setModel(new DefaultComboBoxModel(new String[] {"", "Homme", "Femme"}));
		cmbSexe.setBounds(131, 189, 96, 26);
		layeredPane.add(cmbSexe);
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAdresse.setBounds(10, 230, 87, 17);
		layeredPane.add(lblAdresse);
		
		txtAdresse = new JTextField();
		txtAdresse.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtAdresse.setBounds(131, 227, 241, 24);
		layeredPane.add(txtAdresse);
		txtAdresse.setColumns(10);
		
		JLabel lblAdresseEmail = new JLabel("Adresse Email");
		lblAdresseEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAdresseEmail.setBounds(10, 258, 87, 17);
		layeredPane.add(lblAdresseEmail);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtEmail.setBounds(131, 258, 241, 25);
		layeredPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblNoContactl = new JLabel("No contact");
		lblNoContactl.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNoContactl.setBounds(10, 296, 87, 20);
		layeredPane.add(lblNoContactl);
		
		txtNoContact = new JTextField();
		layeredPane.setLayer(txtNoContact, 0);
		txtNoContact.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtNoContact.setBounds(131, 294, 175, 25);
		layeredPane.add(txtNoContact);
		txtNoContact.setColumns(10);
		
		JLabel lblTitre = new JLabel("Titre");
		lblTitre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTitre.setBounds(10, 335, 48, 14);
		layeredPane.add(lblTitre);
		
		cmbTitre = new JComboBox();
		cmbTitre.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbTitre.setBounds(129, 330, 197, 25);
		layeredPane.add(cmbTitre);
		cmbTitre.setModel(new DefaultComboBoxModel(new String[] {"", "Administrateur", "HR Manager", "Manager", "Vendeur", "Comptable", "Formateur", "Mécanicien", "Chauffeur", "Nettoyeur", "Stagiaire"}));
		
		JLabel lblNewLabel_4 = new JLabel("Departement");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(10, 366, 134, 17);
		layeredPane.add(lblNewLabel_4);
		
		cmbDep = new JComboBox();
		cmbDep.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbDep.setBounds(129, 362, 80, 25);
		layeredPane.add(cmbDep);
		
		JLabel lblSalaire = new JLabel("Salaire");
		lblSalaire.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSalaire.setBounds(10, 407, 88, 17);
		layeredPane.add(lblSalaire);
		
		txtSalaire = new JTextField();
		txtSalaire.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtSalaire.setBounds(131, 403, 159, 25);
		layeredPane.add(txtSalaire);
		txtSalaire.setColumns(10);
		
		JLabel lblCommission = new JLabel("Commission");
		lblCommission.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCommission.setBounds(10, 441, 96, 14);
		layeredPane.add(lblCommission);
		
		txtCommission = new JTextField();
		txtCommission.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtCommission.setBounds(131, 436, 159, 25);
		layeredPane.add(txtCommission);
		txtCommission.setColumns(10);
		
		JLabel lblDateDembauche = new JLabel("Date d'embauche");
		lblDateDembauche.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDateDembauche.setBounds(10, 477, 121, 27);
		layeredPane.add(lblDateDembauche);
		
		dateChooserDOB = new JDateChooser();
		dateChooserDOB.setBounds(131, 153, 138, 25);
		layeredPane.add(dateChooserDOB);
		
		// Interdir saisie directement dans datechooser
		editor = (JTextFieldDateEditor) dateChooserDOB.getDateEditor();
		editor.setEditable(false);		
		dateChooserDOB.setDateFormatString("YYYY-MM-dd"); //changer le format en aaaa-mm-jj
		
		dateChooserAnne = new JDateChooser();
		dateChooserAnne.setBounds(131, 477, 140, 27);
		
		// Interdir saisie directement dans datechooser
		 editorx =(JTextFieldDateEditor) dateChooserAnne.getDateEditor();
		editorx.setEditable(false);		

		layeredPane.add(dateChooserAnne);
		
		dateChooserAnne.setDateFormatString("YYYY-MM-dd"); //changer le format en aaaa-mm-jj
		
		txtNomDep = new JTextField();
		txtNomDep.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtNomDep.setEditable(false);
        txtNomDep.setVisible(false);
		txtNomDep.setBounds(219, 362, 140, 25);
		layeredPane.add(txtNomDep);
		txtNomDep.setColumns(10);

		layeredPane_1.setForeground(new Color(0, 0, 128));
		layeredPane_1.setBackground(new Color(0, 0, 128));
		layeredPane_1.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(64, 64, 64)));
		layeredPane_1.setBounds(6, 702, 1310, 97);
		frame.getContentPane().add(layeredPane_1);
		
		
		 btnAjouter = new JButton("Ajouter Employé(e)");
		 btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					decryptClef();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				boolean error = false;
				getEmpInfos();

				if (controleSaisie(error) == false) {
				try {
					
						Salaire = encryptInString(Salaire,getMasterKey());
					
						
						
						DBUtil.addEmploye(empInfos(CRUDMode.ADD));
						
						JFrame frame = new JFrame("retour");
						JOptionPane.showMessageDialog(frame, "Employé ajouté");
						
						//clearChamps();							
					} catch (SQLException e1) {
						JFrame frame = new JFrame("error");
						JOptionPane.showMessageDialog(frame, e1);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					 try {
						getAllEmployees(table);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					gererEmployes.this.frame.setVisible(false);

					frame = new JFrame("Créer compte");
					if (JOptionPane.showConfirmDialog(frame, "Voulez-vous Créer un compte utilisateur pour le nouveau employé??", "",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
						
						
						InterfaceCompte.main(null);
					
						
						
						
					}
					
				
			}
				
				
				
				
				
				
				
				
				
			}
		});
		btnAjouter.setBounds(10, 32, 193, 32);
		layeredPane_1.add(btnAjouter);
		
		btnModifier = new JButton("Mettre à jour");
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 try {
				decryptClef();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
				NoEmp = txtNoEmp.getText();
				getEmpInfos();
				 String UpdateQuery = "UPDATE employes SET Nom=?,Prenom=?,NIC=?,DOB=?,Sexe=?,Adresse=?,AdresseEmail=?,No_contact=?,Titre=?,Salaire=?,DateDembauche=?,Comission=?,Nodept=? WHERE No_employe=?";
				try {
					Connection connection = ConnectionFactory.getConnection();
				
			         PreparedStatement  ps= connection.prepareStatement(UpdateQuery);

			         ps.setString(1, Nom);
			         ps.setString(2,Prenom);
			         ps.setString(3, NIC);
			         ps.setString(4,DOB);
			         ps.setString(5, Sexe);
			         ps.setString(6,Adresse);
			         ps.setString(7,Email);
			         ps.setString(8, NoContact);
			         ps.setString(9,Titre);
			         ps.setString(10, encryptInString(Salaire,getMasterKey()));
			         ps.setString(11,Embauche);
			         ps.setString(12,Commission);
			         ps.setString(13,Dep);
			         ps.setString(14,NoEmp);
			         
			         
					boolean error = false;

					
					
					if (controleSaisie(error) == false) {
					
			         ps.executeUpdate();
						JFrame frame = new JFrame("retour");
						
						JOptionPane.showMessageDialog(frame,"Employé(e) modifié(e)");
						getAllEmployees(table);
						
						txtNomDep.setVisible(false);

					
				    }
					
					
				} catch (SQLException e1) {
					JFrame frame = new JFrame("error");
					JOptionPane.showMessageDialog(frame, e1);
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnModifier.setBounds(504, 32, 205, 32);
		layeredPane_1.add(btnModifier);
		
		JButton btnEffChamps = new JButton("Effacer tous les champs");
		btnEffChamps.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEffChamps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//btnEffChamps.setEnabled(false);
				
				 //appel méthode
				 effaceChamps();
				 
				
				
				
				
			}
		});
		btnEffChamps.setBounds(242, 32, 229, 32);
		layeredPane_1.add(btnEffChamps);
		
		JButton btnSupprimer = new JButton("Suppimer Employé(e)");
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSupprimer.setBounds(745, 32, 193, 32);
		layeredPane_1.add(btnSupprimer);
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				boolean error = false;

			    NoEmp = txtNoEmp.getText();
				
				if( NoEmp.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Vous n'avez rien inséré! Inserez l'INCID!!");

					
				
				}
			
			   if (verification(error) == false) {
				 try {
						int confirm = JOptionPane.showConfirmDialog(null,"voulez-vous vraiment effacer cet enregistrement?","fermer",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
						
					if (confirm == JOptionPane.YES_OPTION) {
						DBUtil.deleteEmploye(empInfos(CRUDMode.DELETE));
						JFrame frame = new JFrame("retour");
						JOptionPane.showMessageDialog(frame, "Employé(e) supprimé(e)");
						effaceChamps();
					}else {
						
						System.out.println(""); // reste dans le programme

						
				    }
						
					} catch (SQLException e1) {
						JFrame frame = new JFrame("error");
						JOptionPane.showMessageDialog(frame, e1);
					}
				 
					refreshTable();
				
				
				
			   }
				
				
			}
		});
		
		txtRechercher = new JTextField();
		txtRechercher.addFocusListener(new FocusAdapter() {
			
			
			
			
		});
		txtRechercher.setBounds(397, 106, 331, 29);
		frame.getContentPane().add(txtRechercher);
		txtRechercher.setColumns(10);
		
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.setBackground(new Color(224, 255, 255));
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					findEmploye();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRechercher.setBounds(730, 106, 111, 29);
		frame.getContentPane().add(btnRechercher);

		//checkAnn();
	
	}
	
	//public void comboDept() throws SQLException{
		//try {
			
			//jc.setModel(new DefaultJComboBoxModel());
			
			//DBUtil afficheMoi = new DBUtil();
			//affichagex.populate(comboBox, table, 0, "view");
			
			//afficheMoi.populate(cmbDep);
		//}
		
		//catch(SQLException e1) {
		//JOptionPane.showMessageDialog(null, e1);
		//		
		//}
		
		
		
	//}
	
	
	public void refreshTable() {
		
		//try {
			
			//table.setModel(new DefaultTableModel());
			
			//DBUtil affichage = new DBUtil();
			//affichage.getAllEmployees(table);
			
		//}
		
		//catch(SQLException e1) {
		//JOptionPane.showMessageDialog(null, e1);
				
		//}
			
     }
	
	
	// Méthode qui recoit valeur rechercher par paramètre (val)
		public static ArrayList<Employe> AllEmployes(String val) throws SQLException {
	        String searchQuery = "SELECT No_employe,Nom,Prenom,NIC,DOB,Sexe,Adresse,AdresseEmail,No_contact,Titre,Salaire,DateDembauche,Comission,Nodept,Nom_dept FROM employes E, departement D WHERE E.Nodept = D.No_dept AND CONCAT(`Nom`,`Prenom`,`No_employe`,`Nodept`,`Titre`,`Nom_dept`,`Adresse`) LIKE'%"+val+"%'";
			Connection connection = ConnectionFactory.getConnection();
			//PreparedStatement preparedStatement = connection.prepareStatement(QueryStatement.searchQuery);
	      java.sql.Statement  preparedStatement = connection.createStatement();

			ResultSet resultSet = preparedStatement.executeQuery(searchQuery);
			
			
			ArrayList<Employe> employeList = new ArrayList<Employe>();
			
		if (!resultSet.isBeforeFirst() ) { // si pas de résultat
			
			JFrame frame = new JFrame("0 résultat");
			JOptionPane.showMessageDialog(frame,"Aucun résultat obtenu..");
			
			 txtRechercher.requestFocusInWindow(); //place curseur dans txtbox rechercher
			 txtRechercher.setText("");//effacer textbox recherche

			
		}else {
			
			while (resultSet.next()) {
				Employe employe = new Employe();
				
				try {
					decryptClef();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//salaire = decryptInString(rs.getString("salaire"), getMasterKey());
				
				employe.setNo_employe(resultSet.getString(1));
				employe.setNom(resultSet.getString(2));
				employe.setPrenom(resultSet.getString(3));
				employe.setNIC(resultSet.getString(4));
				employe.setDOB(resultSet.getString(5));
				employe.setSexe(resultSet.getString(6));
				employe.setAdresse(resultSet.getString(7));
				employe.setEmail(resultSet.getString(8));
				employe.setNo_contact(resultSet.getString(9));
				employe.setTitre(resultSet.getString(10));
				try {
					employe.setSalaire(decryptInString(resultSet.getString(11),getMasterKey()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				employe.setDateDembauche(resultSet.getString(12));
				employe.setComission(resultSet.getString(13));
				employe.setNo_dept(resultSet.getString(14));
				employe.setNom_dept(resultSet.getString(15));

					
				employeList.add(employe);
			}
			
			
		}

			return employeList;
			
		
		}

		
		//Affiche résultats
		 public void findEmploye() throws SQLException
		 
		    {
		        ArrayList<Employe> employe = AllEmployes(txtRechercher.getText());
		        DefaultTableModel model = new DefaultTableModel();
		        model.setColumnIdentifiers(new Object[]{"No_employe", "Nom", "Prenom", "NIC", "DOB", "Sexe","Adresse", "AdresseEmail","No_contact","Titre","Salaire","DateDembauche","Comission",
						"No_departement","Departement"});
		        Object[] row = new Object[15];
		        
		        for(int i = 0; i < employe.size(); i++)
		        {
		            row[0] = employe.get(i).getNo_employe();
		            row[1] = employe.get(i).getNom();
		            row[2] = employe.get(i).getPrenom();
		            row[3] = employe.get(i).getNIC();
		            row[4] = employe.get(i).getDOB();
		            row[5] = employe.get(i).getSexe();
		            row[6] = employe.get(i).getAdresse();
		            row[7] = employe.get(i).getEmail();
		            row[8] = employe.get(i).getNo_contact();
		            row[9] = employe.get(i).getTitre();
		            row[10] = employe.get(i).getSalaire();
		            row[11] = employe.get(i).getDateDembauche();
		            row[12] = employe.get(i).getComission() ;
		            row[13] = employe.get(i).getNo_dept();
		            row[14] = employe.get(i).getNom_dept();

		          
		            model.addRow(row);
		        }
		       table.setModel(model);
		       
		    }
		 
		 
		 
		 public void getAllEmployees(JTable table) throws SQLException{
			 
				try {
					decryptClef();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				DefaultTableModel tableModel = new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"No_employe", "Nom", "Prenom", "NIC", "DOB", "Sexe","Adresse", "AdresseEmail","No_contact","Titre","Salaire","DateDembauche","Comission",
							"No_departement","Departement"
						});
				connection = ConnectionFactory.getConnection();
				preparedStatement = connection.prepareStatement(QueryStatement.SELECT_ALL_EMPLOYES_QUERY);
				
				resultSet = preparedStatement.executeQuery();
				
				while (resultSet.next()) {
		         String No_employe  =   resultSet.getString("No_employe");
				 String Nom =   		resultSet.getString("Nom");
				 String Prenom  =   	resultSet.getString("Prenom");
				 String NIC =   		resultSet.getString("NIC");
				 String DOB =   		resultSet.getString("DOB");
				 String Sexe    =  		resultSet.getString("Sexe");
				 String Adresse =   	resultSet.getString("Adresse");
				 String Email =  resultSet.getString("AdresseEmail");
				 String No_contact  =   resultSet.getString("No_contact");
				 String Titre   =   	resultSet.getString("Titre");
				 String Salaire;
				try {
					Salaire = decryptInString(resultSet.getString("Salaire"),getMasterKey());
					String DateDembauche = resultSet.getString("DateDembauche");
					 String Comission   =   resultSet.getString("Comission");
					 String No_dept =   	resultSet.getString("Nodept");
					 
					 String Nom_dept =   	resultSet.getString("Nom_dept");


					    // create a single array of one row's worth of data
					String[] data = {No_employe, Nom, Prenom, NIC, DOB, Sexe,Adresse,Email,No_contact,
			                        Titre,Salaire,DateDembauche,Comission,No_dept,Nom_dept};
						
					
					 // and add this row of data into the table model
				    tableModel.addRow(data);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 

				   
				}
				
				table.setModel(tableModel);
		}
		 
		 
		 
		 
		 
		 @SuppressWarnings("unchecked")
			public void comboDep() throws SQLException{
				
				 String searchQuery = "SELECT No_dept FROM departement ORDER BY No_dept DESC";
				java.sql.Connection	 connection = ConnectionFactory.getConnection();
					//PreparedStatement preparedStatement = connection.prepareStatement(QueryStatement.searchQuery);
			        Statement preparedStatement = connection.createStatement();

					@SuppressWarnings("unused")
					ResultSet resultSet = preparedStatement.executeQuery(searchQuery);
					
					cmbDep.addItem("");
				try {
					
					while (resultSet.next()) {
						
						   cmbDep.addItem(resultSet.getString("No_dept"));
						   
					}
				}				
					
				catch(SQLException e1) {
					JOptionPane.showMessageDialog(null, e1);
							
					}	
				
						    
			       
				
			}
		 
		 
		 
		 
}