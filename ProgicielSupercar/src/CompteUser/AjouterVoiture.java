package CompteUser;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import executeurOpSql.DBUtil;
import employes.Voiture;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class AjouterVoiture {

	JFrame frame;
	private JTextField txtMarque;
	private JTextField txtModele;
	private JTextField txtPays;
	private JTextField txtService;
	private JTextField txtCouleur;
	private JTextField txtGarantie;
	private JTextField txtTransmission;
	private JTextField txtMoteur;
	private JTextField txtPrix;
	private JTextField txtDate;

	/**
	 * Launch the application.
	 */
	public static void main(String login) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjouterVoiture window = new AjouterVoiture(login);
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
	public AjouterVoiture(String login) throws SQLException {
		initialize(login);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize(String login) throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 1118,672);
		frame.getContentPane().setBackground(new Color(140, 0, 0));
		frame.getContentPane().setLayout(null);
		
		JLabel lblAjouter = new JLabel("Ajouter une voiture");
		lblAjouter.setForeground(Color.LIGHT_GRAY);
		lblAjouter.setBackground(Color.LIGHT_GRAY);
		lblAjouter.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblAjouter.setBounds(454, 0, 306, 30);
		frame.getContentPane().add(lblAjouter);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListVoiture voiture = null;
				try {
					voiture = new ListVoiture(login);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				voiture.frame.setVisible(true);
				frame.setVisible(false); //you can't see me!
				frame.dispose(); //Destroy the JFrame object
				
			}
		});
		btnRetour.setBounds(21, 48, 125, 45);
		frame.getContentPane().add(btnRetour);
		
		
		txtMarque = new JTextField();
		txtMarque.setBounds(385, 171, 120, 30);
		frame.getContentPane().add(txtMarque);
		txtMarque.setColumns(10);
		
		txtModele = new JTextField();
		txtModele.setBounds(385, 212, 120, 30);
		frame.getContentPane().add(txtModele);
		txtModele.setColumns(10);
		
		txtPays = new JTextField();
		txtPays.setBounds(385, 253, 120, 30);
		frame.getContentPane().add(txtPays);
		txtPays.setColumns(10);
		
		txtService = new JTextField();
		txtService.setBounds(385, 294, 120, 30);
		frame.getContentPane().add(txtService);
		txtService.setColumns(10);
		
		JLabel lblMarque = new JLabel("Marque");
		lblMarque.setHorizontalAlignment(SwingConstants.CENTER);
		lblMarque.setForeground(Color.LIGHT_GRAY);
		lblMarque.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMarque.setBounds(189, 169, 155, 30);
		frame.getContentPane().add(lblMarque);
		
		JLabel lblModele = new JLabel("Modele");
		lblModele.setHorizontalAlignment(SwingConstants.CENTER);
		lblModele.setForeground(Color.LIGHT_GRAY);
		lblModele.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblModele.setBounds(189, 210, 155, 30);
		frame.getContentPane().add(lblModele);
		
		JLabel lblSercice = new JLabel("Intervalle de service");
		lblSercice.setHorizontalAlignment(SwingConstants.CENTER);
		lblSercice.setForeground(Color.LIGHT_GRAY);
		lblSercice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSercice.setBounds(189, 292, 155, 30);
		frame.getContentPane().add(lblSercice);
		
		JLabel lblPays = new JLabel("Pays D'origine");
		lblPays.setHorizontalAlignment(SwingConstants.CENTER);
		lblPays.setForeground(Color.LIGHT_GRAY);
		lblPays.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPays.setBounds(189, 251, 155, 30);
		frame.getContentPane().add(lblPays);
		
		JLabel lblRemplireLeFormulaire = new JLabel("Remplissez le formulaire pour effectuer une Nouvelle Voiture");
		lblRemplireLeFormulaire.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemplireLeFormulaire.setForeground(Color.LIGHT_GRAY);
		lblRemplireLeFormulaire.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRemplireLeFormulaire.setBounds(324, 80, 487, 38);
		frame.getContentPane().add(lblRemplireLeFormulaire);
		
		JLabel lblCouleur = new JLabel("Couleur");
		lblCouleur.setHorizontalAlignment(SwingConstants.CENTER);
		lblCouleur.setForeground(Color.LIGHT_GRAY);
		lblCouleur.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCouleur.setBounds(189, 333, 155, 30);
		frame.getContentPane().add(lblCouleur);
		
		JLabel lblGarantie = new JLabel("Garantie");
		lblGarantie.setHorizontalAlignment(SwingConstants.CENTER);
		lblGarantie.setForeground(Color.LIGHT_GRAY);
		lblGarantie.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGarantie.setBounds(556, 169, 155, 30);
		frame.getContentPane().add(lblGarantie);
		
		txtCouleur = new JTextField();
		txtCouleur.setColumns(10);
		txtCouleur.setBounds(385, 335, 120, 30);
		frame.getContentPane().add(txtCouleur);
		
		txtGarantie = new JTextField();
		txtGarantie.setColumns(10);
		txtGarantie.setBounds(752, 171, 120, 30);
		frame.getContentPane().add(txtGarantie);
		
		txtTransmission = new JTextField();
		txtTransmission.setColumns(10);
		txtTransmission.setBounds(752, 210, 120, 30);
		frame.getContentPane().add(txtTransmission);
		
		txtMoteur = new JTextField();
		txtMoteur.setColumns(10);
		txtMoteur.setBounds(752, 251, 120, 30);
		frame.getContentPane().add(txtMoteur);
		
		txtPrix = new JTextField();
		txtPrix.setColumns(10);
		txtPrix.setBounds(752, 294, 120, 30);
		frame.getContentPane().add(txtPrix);
		
		txtDate = new JTextField();
		txtDate.setColumns(10);
		txtDate.setBounds(752, 335, 120, 30);
		frame.getContentPane().add(txtDate);
		
		JLabel lblTransmission = new JLabel("Transmission");
		lblTransmission.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransmission.setForeground(Color.LIGHT_GRAY);
		lblTransmission.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTransmission.setBounds(556, 208, 155, 30);
		frame.getContentPane().add(lblTransmission);
		
		JLabel lblMoteur = new JLabel("Moteur");
		lblMoteur.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoteur.setForeground(Color.LIGHT_GRAY);
		lblMoteur.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMoteur.setBounds(556, 249, 155, 30);
		frame.getContentPane().add(lblMoteur);
		
		JLabel lblDate = new JLabel("Date mise en vente");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setForeground(Color.LIGHT_GRAY);
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDate.setBounds(556, 331, 155, 30);
		frame.getContentPane().add(lblDate);
		
		JLabel lblPrix = new JLabel("Prix");
		lblPrix.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrix.setForeground(Color.LIGHT_GRAY);
		lblPrix.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrix.setBounds(556, 290, 155, 30);
		frame.getContentPane().add(lblPrix);
		
		JLabel lblEntrepot = new JLabel("Entrepot");
		lblEntrepot.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntrepot.setForeground(Color.LIGHT_GRAY);
		lblEntrepot.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEntrepot.setBounds(383, 389, 155, 30);
		frame.getContentPane().add(lblEntrepot);
		
		JComboBox comboBox = new JComboBox(DBUtil.getEntrepotComboBox());
		comboBox.setBounds(534, 390, 197, 32);
		frame.getContentPane().add(comboBox);
		frame.setTitle("Ajouter une Vente");
		frame.setSize(1118,672);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		JButton btnSoumettre = new JButton("Soumettre");
		btnSoumettre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Voiture voiture = new Voiture();
				voiture.setMarque(txtMarque.getText());
				voiture.setModele(txtModele.getText()) ;
				voiture.setPays_dorigine(txtPays.getText());
				voiture.setIntervall_de_service(txtService.getText()) ;
				voiture.setCouleur(txtCouleur.getText()) ;
				voiture.setGarentie(txtGarantie.getText()) ;
				voiture.setTransmission(txtTransmission.getText()) ;
				voiture.setMoteur(txtMoteur.getText()) ;
				voiture.setPrix(Integer.parseInt(txtPrix.getText())) ;
				java.sql.Date sqlDate = java.sql.Date.valueOf( txtDate.getText() );
				voiture.setDateMiseEnVente(sqlDate);
				int entrepot =  Integer.parseInt((comboBox.getSelectedItem().toString()).substring(0, 3).trim());
				
				try {
					if(	DBUtil.addVoiture(voiture, entrepot)) {
						
						ListVoiture voiture1 = null;
						try {
							voiture1 = new ListVoiture(login);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						voiture1.frame.setVisible(true);
						frame.setVisible(false); //you can't see me!
						frame.dispose(); //Destroy the JFrame object
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 

			}
		});
		btnSoumettre.setBounds(498, 486, 150, 45);
		frame.getContentPane().add(btnSoumettre);
		
	}
}
