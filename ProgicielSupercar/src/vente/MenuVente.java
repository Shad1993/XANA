package vente;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPanel;
import executeurOpSql.DBUtil;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MenuVente {

	JFrame frmDepartementsDeVente;
	private JTextField txtAttente;
	private JTextField txtCours;
	private JTextField txtTerminez;

	/**
	 * Launch the application.
	 */
	public static void main(String login) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuVente window = new MenuVente();
					window.frmDepartementsDeVente.setVisible(true);
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
	public MenuVente() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frmDepartementsDeVente = new JFrame();
		frmDepartementsDeVente.setTitle("Departements de Vente");
		frmDepartementsDeVente.setBounds(100, 100, 1118,672);
		frmDepartementsDeVente.getContentPane().setBackground(new Color(128, 0, 0));
		frmDepartementsDeVente.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Voir les vente");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListDesVentes home = null;
				try {
					home = new ListDesVentes();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				home.frame.setVisible(true);
				frmDepartementsDeVente.setVisible(false); //you can't see me!
				frmDepartementsDeVente.dispose(); //Destroy the JFrame object
				
			}
		});
		btnNewButton.setBounds(334, 186, 144, 70);
		frmDepartementsDeVente.getContentPane().add(btnNewButton);
		frmDepartementsDeVente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Icon imgIcon = new ImageIcon(this.getClass().getResource("LogoVenteGIF.gif"));
		JLabel Label_img = new JLabel(imgIcon);
		Label_img.setBounds(313, 11, 475, 150);
		frmDepartementsDeVente.getContentPane().add(Label_img);
		
		JButton btnNewButton_2 = new JButton("Client");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ListClient client = null;
				try {
					client = new ListClient();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				client.frame.setVisible(true);
				frmDepartementsDeVente.setVisible(false); //you can't see me!
				frmDepartementsDeVente.dispose(); //Destroy the JFrame object
				
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2.setBounds(74, 186, 144, 70);
		frmDepartementsDeVente.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_4 = new JButton("Deconnecter!!!");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Login login = null;
				login = new Login();
				frmDepartementsDeVente.setVisible(false); //you can't see me!
				frmDepartementsDeVente.dispose(); //Destroy the JFrame object
				
			
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_4.setBounds(206, 515, 144, 70);
		frmDepartementsDeVente.getContentPane().add(btnNewButton_4);
		
		JButton btnVoiture = new JButton("Voiture");
		btnVoiture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListVoiture voiture = null;
				try {
					voiture = new ListVoiture();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				voiture.frame.setVisible(true);
				frmDepartementsDeVente.setVisible(false); //you can't see me!
				frmDepartementsDeVente.dispose(); //Destroy the JFrame object
				
			}
		});
		btnVoiture.setBounds(74, 290, 144, 70);
		frmDepartementsDeVente.getContentPane().add(btnVoiture);
		btnVoiture.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(153, 102, 102));
		panel_1.setBounds(562, 221, 498, 291);
		frmDepartementsDeVente.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		txtAttente = new JTextField(DBUtil.getTBattente());
		txtAttente.setEditable(false);
		txtAttente.setHorizontalAlignment(SwingConstants.CENTER);
		txtAttente.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtAttente.setBounds(26, 168, 122, 71);
		panel_1.add(txtAttente);
		txtAttente.setColumns(10);
		
		txtCours = new JTextField(DBUtil.getTBcours());
		txtCours.setEditable(false);
		txtCours.setHorizontalAlignment(SwingConstants.CENTER);
		txtCours.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtCours.setBounds(191, 168, 122, 71);
		panel_1.add(txtCours);
		txtCours.setColumns(10);
		
		txtTerminez = new JTextField(DBUtil.getTBterminez());
		txtTerminez.setEditable(false);
		txtTerminez.setHorizontalAlignment(SwingConstants.CENTER);
		txtTerminez.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtTerminez.setColumns(10);
		txtTerminez.setBounds(354, 168, 122, 71);
		panel_1.add(txtTerminez);
		
		JLabel lblNewLabel = new JLabel("Vente en attente:");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 86, 171, 54);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(" Vente\r\n en cours:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(191, 86, 122, 54);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Vente\r\n terminez:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(354, 86, 122, 54);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Tableau de bord");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(0, 2, 498, 24);
		panel_1.add(lblNewLabel_3);
		
		JButton btnNewButton_1 = new JButton("Entrepot");
		btnNewButton_1.setBounds(334, 290, 144, 70);
		frmDepartementsDeVente.getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			ListEntrepot entrepot = null;
			try {
				entrepot = new ListEntrepot();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			entrepot.frame.setVisible(true);
			frmDepartementsDeVente.setVisible(false); //you can't see me!
			frmDepartementsDeVente.dispose(); //Destroy the JFrame object
		}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnNewButton_3 = new JButton("Stock");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListStock stock = null;
				try {
					stock = new ListStock();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				stock.frame.setVisible(true);
				frmDepartementsDeVente.setVisible(false); //you can't see me!
				frmDepartementsDeVente.dispose(); //Destroy the JFrame object
			}
		});
		btnNewButton_3.setBounds(74, 403, 144, 70);
		frmDepartementsDeVente.getContentPane().add(btnNewButton_3);
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnNewButton_3_1 = new JButton("Archive Vente");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListArchiveVoiture arhiveVoiture = null;
				try {
					arhiveVoiture = new ListArchiveVoiture();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				arhiveVoiture.frame.setVisible(true);
				frmDepartementsDeVente.setVisible(false); //you can't see me!
				frmDepartementsDeVente.dispose(); //Destroy the JFrame object
			}
		});
		btnNewButton_3_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_3_1.setBounds(334, 403, 144, 70);
		frmDepartementsDeVente.getContentPane().add(btnNewButton_3_1);
		
		
	}
}
