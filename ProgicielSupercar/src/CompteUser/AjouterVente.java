package vente;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import executeurOpSql.DBUtil;
import executeurOpSql.MailUtil;
import employes.Vente;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AjouterVente {

	JFrame frame;
	private JTextField txtPrix;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjouterVente window = new AjouterVente();
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
	public AjouterVente() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 1118,672);
		frame.getContentPane().setBackground(new Color(140, 0, 0));
		frame.getContentPane().setLayout(null);
		
		JLabel lblAjouter = new JLabel("Ajouter une vente");
		lblAjouter.setForeground(Color.LIGHT_GRAY);
		lblAjouter.setBackground(Color.LIGHT_GRAY);
		lblAjouter.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblAjouter.setBounds(456, 0, 196, 30);
		frame.getContentPane().add(lblAjouter);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListDesVentes home = null;
				try {
					home = new ListDesVentes();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				home.frame.setVisible(true);
				frame.setVisible(false); //you can't see me!
				frame.dispose(); //Destroy the JFrame object
			}
		});
		btnRetour.setBounds(21, 48, 125, 45);
		frame.getContentPane().add(btnRetour);
		
	
		

	

		
		
		JLabel lblNewLabel = new JLabel("Client");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(378, 175, 155, 30);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNumeroEmploye = new JLabel("Employ\u00E9e");
		lblNumeroEmploye.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroEmploye.setForeground(Color.LIGHT_GRAY);
		lblNumeroEmploye.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNumeroEmploye.setBounds(378, 243, 155, 30);
		frame.getContentPane().add(lblNumeroEmploye);
		
		JLabel lblNumeroVoiture = new JLabel("Voiture");
		lblNumeroVoiture.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroVoiture.setForeground(Color.LIGHT_GRAY);
		lblNumeroVoiture.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNumeroVoiture.setBounds(378, 309, 155, 30);
		frame.getContentPane().add(lblNumeroVoiture);
		
		JLabel lblPrix = new JLabel("Prix");
		lblPrix.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrix.setForeground(Color.LIGHT_GRAY);
		lblPrix.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrix.setBounds(378, 372, 155, 30);
		frame.getContentPane().add(lblPrix);
		
		JLabel lblRemplireLeFormulaire = new JLabel("Remplissez le formulaire pour effectuer une vente");
		lblRemplireLeFormulaire.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemplireLeFormulaire.setForeground(Color.LIGHT_GRAY);
		lblRemplireLeFormulaire.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRemplireLeFormulaire.setBounds(305, 106, 469, 38);
		frame.getContentPane().add(lblRemplireLeFormulaire);
		
		System.out.println();
		txtPrix = new JTextField();
		txtPrix.setBounds(576, 374, 150, 30);
		frame.getContentPane().add(txtPrix);
		txtPrix.setColumns(10);
		final JComboBox comboBox = new JComboBox(DBUtil.getVoitureComboBox());
		comboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					txtPrix.setText(DBUtil.getPrix(Integer.parseInt(comboBox.getSelectedItem().toString().substring(0, 3))));
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		txtPrix.setText("hell");
		comboBox.setBounds(576, 311, 150, 30);
		frame.getContentPane().add(comboBox);
		final JComboBox comboBox_1 = new JComboBox(DBUtil.getClientComboBox());
		comboBox_1.setBounds(576, 177, 150, 30);
		frame.getContentPane().add(comboBox_1);
		
		final JComboBox comboBox_2 = new JComboBox(DBUtil.getEmpComboBox());
		comboBox_2.setBounds(576, 245, 150, 30);
		frame.getContentPane().add(comboBox_2);
		
		System.out.println(DBUtil.getPrix(Integer.parseInt(comboBox.getSelectedItem().toString().substring(0, 3))));
		txtPrix = new JTextField();
		txtPrix.setEditable(false);
		txtPrix.setBounds(576, 374, 150, 30);
		frame.getContentPane().add(txtPrix);
		txtPrix.setColumns(10);
		txtPrix.setText(DBUtil.getPrix(Integer.parseInt(comboBox.getSelectedItem().toString().substring(0, 3))));
		
		
		JButton btnSoumettre = new JButton("Soumettre");
		btnSoumettre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println(checkQuantite(txtPrix.getText()));
				
				String client= (comboBox_1.getSelectedItem().toString()).substring(0, 3);
				String employe = (comboBox_2.getSelectedItem().toString()).substring(0, 3);
				int quantite = Integer.parseInt(txtPrix.getText());
				String voiture =  (comboBox.getSelectedItem().toString()).substring(0, 3);
			
				int clientInt = Integer.parseInt(client.trim()) ;
				int employeInt = Integer.parseInt(employe.trim()) ;
				int voitureInt = Integer.parseInt(voiture.trim()) ;
				
				Vente vente = new Vente();	
				vente.setNoClient(clientInt);
				vente.setNo_Emp(employeInt);
				vente.setQuantite(quantite);
				vente.setNoVoiture(voitureInt);
				
				System.out.println(vente.getNo_Emp());
				String html= "<h1>Supercar Departement de Venter</h1><br><br>"
						+ "<p>Chere "+comboBox_1.getSelectedItem().toString().substring(3)
								+ "Votre Demande a été effectuer"+"</p>" ; 
				
				try {
					DBUtil.addVente(vente);

					MailUtil.sendMail("umarpanchoo1@gmail.com",html);
					
					ListDesVentes home = null;
					try {
						home = new ListDesVentes();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					home.frame.setVisible(true);
					frame.setVisible(false); 
					frame.dispose(); 
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				if(checkQuantite(txtPrix.getText())== true) {
			
					
					System.out.println("nice");
				}
				
				
			}
		});
		btnSoumettre.setBounds(502, 472, 150, 45);
		frame.getContentPane().add(btnSoumettre);
		frame.setTitle("Ajouter une Vente");
		frame.setSize(1118,672);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

	public boolean checkQuantite(String pass) {
		String regex = "^(?=.*[0-9])";
				
			
				

		// Compile the ReGex
		Pattern p = Pattern.compile(regex);

		// If the password is empty
		// return false
		if (pass == null) {
			return false;
		}

		// Pattern class contains matcher() method
		// to find matching between given password
		// and regular expression.
		Matcher m = p.matcher(pass);

		// Return if the password
		// matched the ReGex
		return m.matches();


	}
}
