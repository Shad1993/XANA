package vente;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


import executeurOpSql.DBUtil;
import employes.Client;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AjouterClient {

	JFrame frame;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtNCI;
	private JTextField txtAdresse;
	private JTextField txtEmail;
	private JTextField txtNumero;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjouterClient window = new AjouterClient();
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
	public AjouterClient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1118,672);
		frame.getContentPane().setBackground(new Color(140, 0, 0));
		frame.getContentPane().setLayout(null);
		
		JLabel lblAjouter = new JLabel("Ajouter un client");
		lblAjouter.setForeground(Color.LIGHT_GRAY);
		lblAjouter.setBackground(Color.LIGHT_GRAY);
		lblAjouter.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblAjouter.setBounds(446, 0, 306, 30);
		frame.getContentPane().add(lblAjouter);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListClient client = null;
				try {
					client = new ListClient();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				client.frame.setVisible(true);
				frame.setVisible(false); //you can't see me!
				frame.dispose(); //Destroy the JFrame object
				
			}
		});
		btnRetour.setBounds(21, 48, 125, 45);
		frame.getContentPane().add(btnRetour);
		
		JButton btnSoumettre = new JButton("Soumettre");
		btnSoumettre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client client = new Client();
				
				
				client.setNom(txtNom.getText());
				client.setPrenom(txtPrenom.getText());
				client.setNCI(txtNCI.getText());
				client.setAdresse(txtAdresse.getText());
				client.setAdresseEmail(txtEmail.getText());
				client.setNo_contact(Integer.parseInt(txtNumero.getText()));
				
				
				try {
					if(	DBUtil.addClient(client)) {
						ListClient client1 = null;
						try {
							client1 = new ListClient();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						client1.frame.setVisible(true);
						frame.setVisible(false); //you can't see me!
						frame.dispose(); //Destroy the JFrame object
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnSoumettre.setBounds(503, 511, 150, 45);
		frame.getContentPane().add(btnSoumettre);
		
		txtNom = new JTextField();
		txtNom.setBounds(576, 160, 120, 30);
		frame.getContentPane().add(txtNom);
		txtNom.setColumns(10);
		
		txtPrenom = new JTextField();
		txtPrenom.setBounds(576, 210, 120, 30);
		frame.getContentPane().add(txtPrenom);
		txtPrenom.setColumns(10);
		
		txtNCI = new JTextField();
		txtNCI.setBounds(576, 260, 120, 30);
		frame.getContentPane().add(txtNCI);
		txtNCI.setColumns(10);
		
		txtAdresse = new JTextField();
		txtAdresse.setBounds(576, 310, 120, 30);
		frame.getContentPane().add(txtAdresse);
		txtAdresse.setColumns(10);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setHorizontalAlignment(SwingConstants.CENTER);
		lblNom.setForeground(Color.LIGHT_GRAY);
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNom.setBounds(380, 158, 155, 30);
		frame.getContentPane().add(lblNom);
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrenom.setForeground(Color.LIGHT_GRAY);
		lblPrenom.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrenom.setBounds(380, 208, 155, 30);
		frame.getContentPane().add(lblPrenom);
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdresse.setForeground(Color.LIGHT_GRAY);
		lblAdresse.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAdresse.setBounds(380, 310, 155, 30);
		frame.getContentPane().add(lblAdresse);
		
		JLabel lblNCI = new JLabel("NCI");
		lblNCI.setHorizontalAlignment(SwingConstants.CENTER);
		lblNCI.setForeground(Color.LIGHT_GRAY);
		lblNCI.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNCI.setBounds(380, 258, 155, 30);
		frame.getContentPane().add(lblNCI);
		
		JLabel lblRemplireLeFormulaire = new JLabel("Remplissez le formulaire pour effectuer une Nouvelle Client");
		lblRemplireLeFormulaire.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemplireLeFormulaire.setForeground(Color.LIGHT_GRAY);
		lblRemplireLeFormulaire.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRemplireLeFormulaire.setBounds(297, 106, 487, 38);
		frame.getContentPane().add(lblRemplireLeFormulaire);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setForeground(Color.LIGHT_GRAY);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(380, 358, 155, 30);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblNumeroContact = new JLabel("Numero contact");
		lblNumeroContact.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroContact.setForeground(Color.LIGHT_GRAY);
		lblNumeroContact.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNumeroContact.setBounds(380, 408, 155, 30);
		frame.getContentPane().add(lblNumeroContact);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(576, 360, 120, 30);
		frame.getContentPane().add(txtEmail);
		
		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		txtNumero.setBounds(576, 410, 120, 30);
		frame.getContentPane().add(txtNumero);
		frame.setTitle("Ajouter une Vente");
		frame.setSize(1118,672);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
