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
import employes.Entrepot;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AjouterEntrepot {

	JFrame frame;
	private JTextField txtNom;
	private JTextField txtAdresse;
	private JTextField txtEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String login) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjouterEntrepot window = new AjouterEntrepot(login);
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
	public AjouterEntrepot(String login) {
		initialize(login);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String login) {
		frame = new JFrame();
		frame.setBounds(100, 100, 1118,672);
		frame.getContentPane().setBackground(new Color(140, 0, 0));
		frame.getContentPane().setLayout(null);
		
		JLabel lblAjouter = new JLabel("Ajouter un entrepot");
		lblAjouter.setForeground(Color.LIGHT_GRAY);
		lblAjouter.setBackground(Color.LIGHT_GRAY);
		lblAjouter.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblAjouter.setBounds(446, 0, 306, 30);
		frame.getContentPane().add(lblAjouter);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ListEntrepot entrepot = null;
				try {
					entrepot = new ListEntrepot(login);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				entrepot.frame.setVisible(true);
				frame.setVisible(false); //you can't see me!
				frame.dispose(); //Destroy the JFrame object
			}
		});
		btnRetour.setBounds(21, 48, 125, 45);
		frame.getContentPane().add(btnRetour);
		
		
		txtNom = new JTextField();
		txtNom.setBounds(543, 173, 208, 38);
		frame.getContentPane().add(txtNom);
		txtNom.setColumns(10);
		
		txtAdresse = new JTextField();
		txtAdresse.setBounds(544, 241, 208, 38);
		frame.getContentPane().add(txtAdresse);
		txtAdresse.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(544, 307, 208, 38);
		frame.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nom de l'entrepot");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(378, 175, 155, 30);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNumeroEmploye = new JLabel("Adresse");
		lblNumeroEmploye.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroEmploye.setForeground(Color.LIGHT_GRAY);
		lblNumeroEmploye.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNumeroEmploye.setBounds(366, 243, 155, 30);
		frame.getContentPane().add(lblNumeroEmploye);
		
		JLabel lblQuantit = new JLabel("Email");
		lblQuantit.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantit.setForeground(Color.LIGHT_GRAY);
		lblQuantit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblQuantit.setBounds(366, 309, 155, 30);
		frame.getContentPane().add(lblQuantit);
		
		
		
		JButton btnSoumettre = new JButton("Soumettre");
		btnSoumettre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Entrepot entrepot =new Entrepot();
				entrepot.setNom(txtNom.getText());
				entrepot.setAdresse(txtAdresse.getText());
				entrepot.setEmail(txtEmail.getText());
				
				try {
					if(	DBUtil.addEntrepot(entrepot)) {
						ListEntrepot Entrepot = null;
						try {
							Entrepot = new ListEntrepot(login);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						Entrepot.frame.setVisible(true);
						frame.setVisible(false); //you can't see me!
						frame.dispose(); //Destroy the JFrame object
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnSoumettre.setBounds(502, 472, 150, 45);
		frame.getContentPane().add(btnSoumettre);
		
		
		
		
		
		JLabel lblRemplireLeFormulaire = new JLabel("Remplissez le formulaire pour effectuer une nouvelle entrepot");
		lblRemplireLeFormulaire.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemplireLeFormulaire.setForeground(Color.LIGHT_GRAY);
		lblRemplireLeFormulaire.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRemplireLeFormulaire.setBounds(297, 106, 487, 38);
		frame.getContentPane().add(lblRemplireLeFormulaire);
		frame.setTitle("Ajouter une Vente");
		frame.setSize(1118,672);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
