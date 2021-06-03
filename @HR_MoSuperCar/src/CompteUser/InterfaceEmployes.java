
package CompteUser;


 
import java.awt.Color;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import InterfaceFiche.GererFiche;
import connexionBDD.ConnectionFactory;
import employes.Employe;
import executeurOpSql.Executeur;
import operationSQL.Operation;
import operationSQL.RequeteStatement;

/**
 * Cette classe est l'interface qui gère les données des employés, chiffre et déchiffre leurs salaires
 * @author Lionel
 * @version finale
 *
 */
public class InterfaceEmployes {

	private JFrame frame;
	//private final JScrollPane scrollPane = new JScrollPane();
	public JTable table;
	//public JScrollPane scrollPane;
	private JTextField txtNoEmp;
	//private JTextField txtNomDep;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtNIC;
	private JTextField txtAdresse;
	private JTextField txtEmail;
	private JTextField txtNoContact;
	private JTextField txtSalaire;
	private JDateChooser dateChooserDOB ;
	private JTextFieldDateEditor editor;
	private JButton btnCreerCompte;
	private JButton btnAjouter;
	private JButton btnModifier;
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
	private JLabel lblNewLabel_5;
	private JButton btnNewButton;
	private JLabel lblNewLabel_6;
	private JButton button;
	private JButton btnRetourAdm;
	private JTextField txtDateEmbauche;
	private JTextField txtnoDep;
	public String saisieDOB;
	private JTextField txtNomDep;
	

		/**
		 * La méthode getter retourne la clé de déchiffrement masterKey
		 * @return masterkey
		 */
		public static Key getMasterKey() {
			return masterKey;
		}

		/**
		 * Méthode setter utlilise clé masterKey pour déchiffrer les salaires chiffrés par la méthode blowfish
		 * @param masterKey est la clé de déchiffrement des salaires
		 */
		public static void setMasterKey(Key masterKey) {
			InterfaceEmployes.masterKey = masterKey;
		}

		
		/**
		 * Cette Méthode déchiffre le fichier clé.crp lÃ  ou la clé hexadecimal est stocké en utilisant la méthode de déchiffremnt blowfish 
		 * 
		 * @throws IOException stop le programme en cas d'erreurs
		 */
		public static void decryptClef() throws IOException {
			String code = new String(Files.readAllBytes(Paths.get("clef.cryp")));
			byte[] clefDecode = Base64.getDecoder().decode(code);
			setMasterKey(new SecretKeySpec(clefDecode, 0, clefDecode.length, "blowfish"));
			
		}

		
		
		/**
		 * Méthode de chiffrement des salaires en octets par ApiBlowfish.decryptInByte et retourne la méthode crypter.doFinal(chaineEnClaire)
		 * @param chaineEnClaire stock le salaire en texte claire
		 * @param clef clé de chiffrement
		 * @return le résultat de chiffrement du salaire en format octet
		 * @throws Exception gère les erreurs 
		 * 
		 * 
		 * 
		 */
		public static byte[] encryptInByte(byte[] chaineEnClaire, Key clef) throws Exception {
			Cipher crypter = Cipher.getInstance("Blowfish");
			crypter.init(Cipher.ENCRYPT_MODE, clef);
			return crypter.doFinal(chaineEnClaire); 
		}

		
		
		/**
		 * Methode pour déchiffrer les octets retourne la chaine déchiffrée en octet
		 * @param chiffrement le salaire chiffré
		 * @param clef la clé de déchiffrement en octet
		 * @return reourne le salaire dÃ©chiffré en format octet
		 * @throws Exception gère les erreurs
		 * 
		 *
		 */
		public static byte[] decryptInByte(byte[] chiffrement, Key clef) throws Exception {
			Cipher decrypter = Cipher.getInstance("Blowfish");
			decrypter.init(Cipher.DECRYPT_MODE, clef);
			byte[] chaineDecrypter = decrypter.doFinal(chiffrement);
			return chaineDecrypter;
		}

		
	   
		/**
		 * cette mméthode est utilisée pour chiffrer les chaines de caractères (salaire)
		 * @param chaineEnClair variable de type String qui stock le salaire de l'employé en claire
		 * @param clef clé de chiffrement
		 * @return retourne le résultat qui est le chiffrement de salaire de type String
		 * @throws Exception gère les erreurs
		 * 
		 */
		public static String encryptInString(String chaineEnClair, Key clef) throws Exception {
			byte[] chaine = chaineEnClair.getBytes();
			chaine = encryptInByte(chaine, clef);
			return Base64.getEncoder().encodeToString(chaine);
		}
		
		
		/**
		 * Méthode pour déchiffrer chaine de caractères (salaire chiffré) et qui retourne  la chaine decrypté
		 * @param chiffrement variable de type String ; le salaire chiffré
		 * @param clef clÃéde déchiffrement
		 * @return retourne le salaire déchiffré de type String
		 * @throws Exception gère les erreurs
		 * 
		 */
		public static String decryptInString(String chiffrement, Key clef) throws Exception {
			//chaine dÃ©codÃ© en base64
			byte[] decrypter = Base64.getDecoder().decode(chiffrement);
			decrypter = decryptInByte(decrypter, clef);
			return new String(decrypter); 
		}
	

	/**
	 * Démarrer l'application.
	 */
	/**
	 * @param login le stock le login de l'utilisateur connecté pour maintenir la sesion
	 */
	
	public static void main(String login) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceEmployes window = new InterfaceEmployes(login);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Création de l'application.
	 * @throws SQLException gère les erreurs sql
	 * @param login stock le login de l'utilsateur connecté
	 */

	public InterfaceEmployes(String login) throws SQLException {
		initialize(login);
		txtNomDep.setVisible(false);
		comboDep(); 
		
		//btnAjouter.setEnabled(false);//Désactiver bouton insertion
		btnModifier.setEnabled(false);//Désactiver bouton Mise Ã  jour
		
		button = new JButton("Fiche de paie");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//gererEmployes.this.frame.setVisible(false);
				GererFiche.main(null);
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setBounds(1140, 32, 199, 36);
		layeredPane_1.add(button);
		
		btnCreerCompte = new JButton("Cr\u00E9er Compte Utilisateur");
		btnCreerCompte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfaceCompte.main(login);
				
				InterfaceEmployes.this.frame.setVisible(false);
			}
		});
		btnCreerCompte.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCreerCompte.setBounds(912, 33, 205, 32);
		layeredPane_1.add(btnCreerCompte);
		
		lblNewLabel_5 = new JLabel("SuperCar");
		lblNewLabel_5.setFont(new Font("Yu Gothic UI", Font.ITALIC, 18));
		lblNewLabel_5.setBounds(24, 11, 111, 34);
		frame.getContentPane().add(lblNewLabel_5);
		
		btnNewButton = new JButton("D\u00E9connexion");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// message de confirmation de sortir du programme
				 int x = JOptionPane.showConfirmDialog(null,"voulez-vous vraiment quitter ??","fermer",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
				if(x == JOptionPane.YES_OPTION) { // Si choix "oui"
					
				 System.exit(0); // sortir du programme
				}else {
					
					System.out.println(""); // reste dans le programme
					
				}
				
				
			}
		});
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
		
		txtnoDep = new JTextField();
		txtnoDep.setEnabled(false);
		txtnoDep.setVisible(false);
		txtnoDep.setBounds(211, 850, 96, 20);
		frame.getContentPane().add(txtnoDep);
		txtnoDep.setColumns(10);
		
	}
	
	/**
	 * Cette méthode exécute les opérations SQL pour la gestions des données des employés et retourne l'object employé de la classe mère Employes
	 * @param mode variable qui stock le mode d'opération SQL:ADD,DELETE,EDIT etc..
	 * 
	 */
	private static Employe empInfos(Operation mode) { 
		Employe employe = new Employe();
		// opÃ©rations

		//CRUDMode.UPDATE n'a pas marché a été enlevé, autre soulution trouvée.. dans bouton Modifier
		if (mode.equals(Operation.ADD) || mode.equals(Operation.DELETE)) {
			
			if (mode.equals(Operation.DELETE)) {
				employe.setNo_employe(NoEmp);
			}
			
		// fonctions set permettant de manipuler les variables privÃ©s
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
	

	/**
	 * Méthode pour le controle de saisie qui retourne le variable SignalErrreur en cau d'erreur de saisie
	 * @param SignalErreur variable de type booléen qui est retourné en cas d'erreurs
	 * @return SignalErreur
	 */
	private boolean controleSaisie(boolean SignalErreur) {
		//frame = new JFrame();
		if (Pattern.matches("[0-9]{7,}", NoContact) == false || NoContact.equalsIgnoreCase("")|| NoContact.isEmpty()) {

			JOptionPane.showMessageDialog(frame, "ERREUR, No contact INVALIDE");
			txtNoContact.setBackground(new Color(255, 186, 186));
			txtNoContact.requestFocusInWindow();
			SignalErreur = true;
			
		//^(0|[1-9]\d*)(\.\d+)?$
		} else if (Pattern.matches("[a-zA-ZÀ-ÿ0-9]+(([',. -][a-zA-ZÀ-ÿ ])?[a-zA-ZÀ-ÿ]*)*$", Adresse) == false || Adresse.equalsIgnoreCase("")|| Adresse.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "ERREUR, ADRESSE INVALIDE");
			txtAdresse.setBackground(new Color(255, 186, 186));
			txtAdresse.requestFocusInWindow();
			
			SignalErreur = true;
	
		}else if (Pattern.matches("[a-zA-ZÀ-ÿ]+(([',. -][a-zA-ZÀ-ÿ ])?[a-zA-ZÀ-ÿ]*)*$", Nom) == false || Nom.equalsIgnoreCase("")|| Nom.isEmpty()){
			JOptionPane.showMessageDialog(frame, "ERREUR, NOM INVALIDE");
			txtNom.setBackground(new Color(255, 186, 186));
			txtNom.requestFocusInWindow();
			SignalErreur = true;
	
		}else if (Pattern.matches("[a-zA-ZÀ-ÿ]+(([',. -][a-zA-ZÀ-ÿ ])?[a-zA-ZÀ-ÿ]*)*$", Nom) == false || Nom.equalsIgnoreCase("")|| Nom.isEmpty()){
			JOptionPane.showMessageDialog(frame, "ERREUR, PRENOM INVALIDE");
			
			txtPrenom.setBackground(new Color(255, 186, 186));
			txtPrenom.requestFocusInWindow();
			SignalErreur = true;
			
		}else if (Pattern.matches("^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{14}$", NIC) == false || NIC.equalsIgnoreCase("") || NIC.isEmpty()|| NIC.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "ERREUR, NIC INVALIDE");
			txtNIC.setBackground(new Color(255, 186, 186));
			txtNIC.requestFocusInWindow();
			SignalErreur = true;
	
		}else if (Pattern.matches("\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$", Email) == false || Email.equalsIgnoreCase("")|| Email.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "ERREUR, Email INVALIDE!!");
			txtEmail.setBackground(new Color(255, 186, 186));
			txtEmail.requestFocusInWindow();
			SignalErreur = true;
			
		}else if (Pattern.matches("^(0|[1-9]\\d*)(\\.\\d+)?$", Salaire) == false ||Salaire.equalsIgnoreCase("")|| Salaire.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "ERREUR,SALAIRE INVALIDE");
			txtSalaire.setBackground(new Color(255, 186, 186));
			txtSalaire.requestFocusInWindow();
			SignalErreur = true;
			
		}else if (Pattern.matches("^(0|[1-9]\\d*)(\\.\\d+)?$", Commission) == false ||Commission.equalsIgnoreCase("")|| Commission.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "ERREUR! COMMISSION INVALIDE!");
			txtCommission.setBackground(new Color(255, 186, 186));
			txtCommission.requestFocusInWindow();
			SignalErreur = true;
			
		}else  if (DOB.isEmpty()) {
			JFrame frame = new JFrame("erreur");
			JOptionPane.showMessageDialog(frame,"Entrez une date de naissance");
			dateChooserDOB.setBackground(new Color(255, 186, 186));
			JOptionPane.showMessageDialog(frame, "InsrÃ©rez une date de naissance!");
			SignalErreur = true;

		} 
			
		
		
	     
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy"); 
	    Date date1 = new Date();  
        String dateNow1 = formatter.format(date1);
	    int dateNowInt1 = Integer.parseInt(dateNow1);
        
		// VÃ©rifier si l'annÃ©e est numÃ©rique
	   try {
		  saisieDOB = DOB.substring(0,4);    //input string
	   }catch(Exception e)   {
			
		   JOptionPane.showMessageDialog(frame,"Erreur date de n'aissance");	
			SignalErreur = true;
			
		}
	   
	   try {
		 int dateIntDOB = Integer.parseInt(saisieDOB); //convertion de l'annÃ©e string en Integer
	  
	
		 if(dateIntDOB % 1 != 0 || dateIntDOB > dateNowInt1) {
			
		    	JFrame frame = new JFrame("erreur");
				JOptionPane.showMessageDialog(frame,"Erreur date de n'aissance");	
				SignalErreur = true;	 	
		 }
   
      }catch(Exception e) {
		   
		   
		   
	   }
		
		
	
				//Dep	=	cmbDep.getSelectedItem().toString();
				
		return SignalErreur;
	}	

	
	/**
	 * cette méthode récupère tous les infos employés saisies  de l'ulitisateur pour la création / modification des infos d'un employé
	 * 
	 */
	public void getEmpInfos() 							{
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
		//Salaire = encryptInString(Salaire,getMasterKey());
		
	 	if (Salaire.length() > 10) {
	   			 
			JOptionPane.showMessageDialog(frame,"Salaire invalide!! 7 chiffres max");
	   		txtCommission.setText("");
	   		
	   	}
	 	
	 	
		Commission	=	txtCommission.getText();
		
	   	if (Commission.length() > 8) {
	    	JFrame frame = new JFrame("erreur");
			JOptionPane.showMessageDialog(frame,"Commision invalide!!le format doit Ãªtre: 00.00");
	   		txtCommission.setText("");
	   		
	   	}
	
	}
	
		/**
		 * Méthode qui va extraire le l'identifiant du département dans le menu déroulant
		 */
		public void extractNoDep() {
		
			 Dep = cmbDep.getSelectedItem().toString();
			 // String noDep = "";
			 // noDep = Dep;
			  //Dep = noDep.substring(0,2);
			  
			  String input_string = Dep;
			  int number_output = Integer.parseInt(input_string.replaceAll("[^0-9]", ""));
			  Dep = String.valueOf(number_output);
			  
		}
	
	
	/**
	 * Cette méthode initialise les champs après les insertions/suppréssions/modifications des données
	 */
	public void refreshChamps () {
		txtNIC.setBackground(new Color(255, 255, 255));
		txtNom.setBackground(new Color(255, 255, 255));
		txtPrenom.setBackground(new Color(255, 255, 255));
		txtNoContact.setBackground(new Color(255, 255, 255));
		txtAdresse.setBackground(new Color(255, 255, 255));
		txtSalaire.setBackground(new Color(255, 255, 255));
		txtCommission.setBackground(new Color(255, 255, 255));
		
	}
	
	
	
	
	/**
	 * Méthode qui éfface tous les champs pour insertion
	 */
	public void effaceChamps() {
		btnAjouter.setEnabled(true);// activer buton ajouter pour insertion
		 txtNoEmp.setText("");
		 txtNom.setText("");
		 txtPrenom.setText("");
		 txtAdresse.setText("");
		 txtNIC.setText("");
		 txtNoContact.setText("");
		 txtSalaire.setText("");
		 //txtNomDep.setVisible(false);// champ departement invisible
		 txtEmail.setText("");
		 txtCommission.setText("");
		 dateChooserDOB.setCalendar(null);
		 txtDateEmbauche.setText("");
		 cmbTitre.setSelectedIndex(0);
		 cmbSexe.setSelectedIndex(0);
		 cmbDep.setSelectedIndex(0);	
		 txtNom.requestFocusInWindow();// place le curseeur sur le champ Nom
		 

	}
	

	/**
	 * Initialize les contenus du frame,et gère les accès dependant du profil de l'utilisateur (affiche les boutons pour retourner au menu dépendant du type d'utilisateur connecté)
	 * @param login stock le login de l'utilisateur connecté
	 * @throws SQLException gère les erreurs sql
	 */
	//@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize(String login) throws SQLException {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(100, 100, 1611, 878);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		CompteAdmin A = new CompteAdmin();
		
		A.DatabaseConnexionHR(login, null, null, frame);
		if(A.getTypeCompte().contains("HR Manager")) {
			
			btnRetour1 = new JButton("Retour");
			btnRetour1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 MenuHr.main(login);
					 InterfaceEmployes.this.frame.setVisible(false);	
				}
			});
			btnRetour1.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnRetour1.setBounds(1324, 13, 120, 32);
			frame.getContentPane().add(btnRetour1);
			
				
		}else {
			
			btnRetourAdm = new JButton("Retour au menu admin");
			btnRetourAdm.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			btnRetourAdm.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnRetourAdm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					InterfaceEmployes.this.frame.setVisible(false);

					MenuAdm.main(login);
				}
			});
			btnRetourAdm.setBounds(1384, 85, 208, 29);
			frame.getContentPane().add(btnRetourAdm);
					
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			
		});
		scrollPane.setBounds(new Rectangle(391, 159, 1201, 515));
		
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
			/**
			 *cette méthode affiche les données dans les champs quand l'utilisateur clique sur en enregistrement d'un employé qui affiche dans las table et active le boutton modification de données
			 */
			@SuppressWarnings({ "null", "unchecked" })
			@Override
			public void mouseClicked(MouseEvent e) {
			     btnAjouter.setEnabled(false); //Activer bouton d'insertion
			     btnModifier.setEnabled(true); // Ativer bouton Mise Ã  jour  
				int i = table.getSelectedRow();
		        TableModel model = table.getModel();
		        
		          //Display Slected Row In JTexteFields
		        txtNoEmp.setText(model.getValueAt(i,0).toString());
		        txtNom.setText(model.getValueAt(i,1).toString());
		        txtPrenom.setText(model.getValueAt(i,2).toString());
		        txtNIC.setText(model.getValueAt(i,3).toString());
			    txtnoDep.setText(model.getValueAt(i,13).toString());
			    cmbDep.setSelectedItem(model.getValueAt(i,13).toString());
		        txtNomDep.setText(model.getValueAt(i,14).toString());
			    cmbSexe.setSelectedItem(model.getValueAt(i,5).toString());
		        txtAdresse.setText(model.getValueAt(i,6).toString());
		        txtEmail.setText(model.getValueAt(i,7).toString());
		        txtNoContact.setText(model.getValueAt(i,8).toString());
				cmbTitre.setSelectedItem(model.getValueAt(i,9).toString());
		        txtSalaire.setText(model.getValueAt(i,10).toString());
		        txtCommission.setText(model.getValueAt(i,12).toString());
		        txtCommission.setText(model.getValueAt(i,12).toString());
		        txtDateEmbauche.setText(model.getValueAt(i, 11).toString());
		        //cmbDep.removeAllItems();
		        String X = txtnoDep.getText();
		       String Y = txtNomDep.getText();
		         
		         String Z = X + "_" + Y ;
		        // comboBoxc.getModel().setSelectedItem(Z);
		         cmbDep.getModel().setSelectedItem(Z);
		         
		        Date date = null;
				try {
					date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(i, 4));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
		         dateChooserDOB.setDate(date);
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
		layeredPane.setBounds(6, 159, 382, 515);
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
		
		JLabel lblNewLabel_1 = new JLabel("No carte d'identitÃ©");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 121, 121, 14);
		layeredPane.add(lblNewLabel_1);
		
		txtNIC = new JTextField();
		txtNIC.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtNIC.setBounds(131, 115, 228, 28);
		layeredPane.add(txtNIC);
		txtNIC.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Date de naissance");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 158, 121, 14);
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
		cmbTitre.setModel(new DefaultComboBoxModel(new String[] {"", "Administrateur", "HR Manager", "Manager", "Vendeur", "Comptable", "Formateur", "MÃ©canicien", "Chauffeur", "Nettoyeur", "Stagiaire"}));
		
		JLabel lblNewLabel_4 = new JLabel("Departement");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(10, 366, 134, 17);
		layeredPane.add(lblNewLabel_4);
		
		cmbDep = new JComboBox();
		cmbDep.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbDep.setBounds(129, 362, 197, 25);
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
		dateChooserDOB.setDateFormatString("YYYY-MM-dd");
		
		txtDateEmbauche = new JTextField();
		txtDateEmbauche.setEditable(false);
		txtDateEmbauche.setForeground(new Color(60, 179, 113));
		txtDateEmbauche.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtDateEmbauche.setBounds(131, 476, 134, 25);
		layeredPane.add(txtDateEmbauche);
		txtDateEmbauche.setColumns(10);
		
		txtNomDep = new JTextField();
		txtNomDep.setBounds(275, 481, 96, 20);
		layeredPane.add(txtNomDep);
		txtNomDep.setColumns(10);

		layeredPane_1.setForeground(new Color(0, 0, 128));
		layeredPane_1.setBackground(new Color(0, 0, 128));
		layeredPane_1.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(64, 64, 64)));
		layeredPane_1.setBounds(6, 689, 1349, 97);
		frame.getContentPane().add(layeredPane_1);
		
		
		 btnAjouter = new JButton("Ajouter Employ\u00E9(e)");
		 btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				  LocalDateTime now = LocalDateTime.now();  
				  //System.out.println(dtf.format(now)); 
				
				  Embauche = dtf.format(now);
				  
				try {
					decryptClef();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				boolean error = false;
				extractNoDep();// appel méthode extraction de l'identifiant du département sélectionné

				getEmpInfos();

				if (controleSaisie(error) == false) {
				try {
					
						Salaire = encryptInString(Salaire,getMasterKey());
					
					
						 						
						Executeur.addEmploye(empInfos(Operation.ADD));
						
						//JFrame frame = new JFrame("retour");
						JOptionPane.showMessageDialog(frame, "Employé(e) ajouté(e)");
						getAllEmployees(table);
						refreshChamps();
						
						InterfaceEmployes.this.frame.repaint();
						

						if (JOptionPane.showConfirmDialog(null, "Voulez-vous Créer un compte d'utilisateur pour cet(te) Employé(e)?", "Compte",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
							InterfaceEmployes.this.frame.setVisible(false);
							    InterfaceCompte.main(login);

							}else {
								
								System.out.print("");
								
							}
						
						

						
						//SwingUtilities.updateComponentTreeUI(frame);
						//clearChamps();							
					} catch (SQLException e1) {
						JFrame frame = new JFrame("error");
						JOptionPane.showMessageDialog(frame, Dep);
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
						
			}
				 	
			}
		});
		btnAjouter.setBounds(10, 32, 193, 32);
		layeredPane_1.add(btnAjouter);
		
		btnModifier = new JButton("Mettre Ã  jour");
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
				extractNoDep();
			 //Dep = cmbDep.getSelectedItem().toString();
			 // String noDep = "";
			 // noDep = Dep;
			  //Dep = noDep.substring(0,2);
			  
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
			         ps.setString(11,txtDateEmbauche.getText());
			         ps.setString(12,Commission);
			         ps.setString(13,Dep);
			         ps.setString(14,NoEmp);
			         
			         
					boolean error = false;
	
					if (controleSaisie(error) == false) {
						 
			         ps.executeUpdate();
				
						JOptionPane.showMessageDialog(null,"Modification terminée..");
						getAllEmployees(table);
						refreshChamps();
						effaceChamps();
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
		btnModifier.setBounds(480, 32, 205, 32);
		layeredPane_1.add(btnModifier);
		
		JButton btnEffChamps = new JButton("Effacer tous les champs");
		btnEffChamps.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEffChamps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 //appel méthode
				 effaceChamps();
				
			}
		});
		btnEffChamps.setBounds(225, 32, 229, 32);
		layeredPane_1.add(btnEffChamps);
		
		JButton btnSupprimer = new JButton("Suppimer Employ\u00E9(e)");
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSupprimer.setBounds(709, 32, 193, 32);
		layeredPane_1.add(btnSupprimer);
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean error = false;
			    NoEmp = txtNoEmp.getText();
				
				if( NoEmp.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Vous n'avez rien sélectionné pour supprimmer");

				}
			
			   if (verification(error) == false) {
				 try {
						int confirm = JOptionPane.showConfirmDialog(null,"voulez-vous vraiment supprimmer cet enregistrement?","fermer",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
						
					if (confirm == JOptionPane.YES_OPTION) {
						Executeur.deleteEmploye(empInfos(Operation.DELETE));
						JFrame frame = new JFrame("retour");
						JOptionPane.showMessageDialog(frame, "Employé(e) supprimé(e)");
						getAllEmployees(table);
						
						frame.setVisible(false);
						frame.setVisible(true);

						effaceChamps();
					}else {
						
						System.out.println(""); // reste dans le programme

						
				    }
						
					} catch (SQLException e1) {
						JFrame frame = new JFrame("error");
						JOptionPane.showMessageDialog(frame, e1);
					}
				 
			   }
				
				
			}
		});
		
		txtRechercher = new JTextField();
		txtRechercher.addFocusListener(new FocusAdapter() {
					
		});
		txtRechercher.setBounds(391, 120, 331, 29);
		frame.getContentPane().add(txtRechercher);
		txtRechercher.setColumns(10);
		
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.setFont(new Font("Tahoma", Font.BOLD, 12));
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
		btnRechercher.setBounds(744, 119, 173, 29);
		frame.getContentPane().add(btnRechercher);

		//checkAnn();
	}
	
	// MÃ©thode qui recoit valeur rechercher par paramÃ¨tre (val)
		public static ArrayList<Employe> AllEmployes(String val) throws SQLException {
	        String searchQuery = "SELECT No_employe,Nom,Prenom,NIC,DOB,Sexe,Adresse,AdresseEmail,No_contact,Titre,Salaire,DateDembauche,Comission,Nodept,Nom_dept FROM employes E, departement D WHERE E.Nodept = D.No_dept AND CONCAT(`Nom`,`Prenom`,`No_employe`,`Nodept`,`Titre`,`Nom_dept`,`Adresse`) LIKE'%"+val+"%'";
			Connection connection = ConnectionFactory.getConnection();
			//PreparedStatement preparedStatement = connection.prepareStatement(QueryStatement.searchQuery);
	      java.sql.Statement  preparedStatement = connection.createStatement();

			ResultSet resultSet = preparedStatement.executeQuery(searchQuery);
			
			ArrayList<Employe> employeList = new ArrayList<Employe>();
			
		if (!resultSet.isBeforeFirst() ) { // si pas de rÃ©sultat
			
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

		
	
		 /**
		  * Cette méthode récupère  le saisie de l'utilisateur pour rechercher un employé et affiche le résultat dans la table
		 * @throws SQLException interrompre le programme en cas d'erreur SQL
		 */
		public void findEmploye() throws SQLException {
		        ArrayList<Employe> employe = AllEmployes(txtRechercher.getText());
		        DefaultTableModel model = new DefaultTableModel();
		        model.setColumnIdentifiers(new Object[]{"No_employe", "Nom", "Prenom", "NIC", "DOB", "Sexe","Adresse", "AdresseEmail","No_contact","Titre","Salaire","DateDembauche","Comission",
						"No_departement","Departement"});
		        Object[] row = new Object[15];
		        
		        for(int i = 0; i < employe.size(); i++) {
		        
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
		 
		 
		 
		 /**
		  * Cette méthode affiche toutes les données des employés de la base de données dans la table
		 * @param table la table d'objet ou les info des employés sont affiché
		 * @throws SQLException interrompre le programme en cas d'erreur sql
		 */
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
				preparedStatement = connection.prepareStatement(RequeteStatement.SELECT_ALL_EMPLOYES_QUERY);
				
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
		 

		 /**
		  * cette méthode affiche tous les départements de la base de données(No département et nom département)  dans le menu déroulant
		 * @throws SQLException gère les erreurs sql
		 */
		@SuppressWarnings("unchecked")
			public void comboDep() throws SQLException{
				
				 String searchQuery = "SELECT No_dept, Nom_dept FROM departement ORDER BY No_dept DESC";
				java.sql.Connection	 connection = ConnectionFactory.getConnection();
					//PreparedStatement preparedStatement = connection.prepareStatement(QueryStatement.searchQuery);
			        Statement preparedStatement = connection.createStatement();

					@SuppressWarnings("unused")
					ResultSet resultSet = preparedStatement.executeQuery(searchQuery);
					
					cmbDep.addItem("");
				try {
					
					while (resultSet.next()) {
						
						   cmbDep.addItem(resultSet.getString("No_dept") + "_" + resultSet.getString("Nom_dept"));
						   
					}
				}				
					
				catch(SQLException e1) {
					JOptionPane.showMessageDialog(null, e1);
							
					}	
			}
}