package CompteUser;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Employe.gererEmployes;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;



public class Login {

	private JFrame frame;

	private JTextField txtLogin;
	private JPasswordField txtMdp;
	private JComboBox cmbType;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().setBackground(SystemColor.inactiveCaption);
		frame.setBounds(100, 100, 434, 463);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblTitle = new JLabel("Connectez-Vous");
		lblTitle.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 391, 64);
		frame.getContentPane().add(lblTitle);

		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBounds(10, 131, 75, 32);
		frame.getContentPane().add(lblLogin);

		JLabel lblPwd = new JLabel("MDP");
		lblPwd.setHorizontalAlignment(SwingConstants.CENTER);
		lblPwd.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPwd.setBounds(10, 251, 56, 32);
		frame.getContentPane().add(lblPwd);
		txtLogin = new JTextField();
		txtLogin.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					CompteAdmin login = new CompteAdmin();
					login.email = txtLogin.getText();
					login.setMdp((String.copyValueOf(txtMdp.getPassword())));
					login.setMdp(login.hashMdp(login.getMdp()));
					try {
						//login.DatabaseConnexionHR(login.email, login.getMdp(), cmbType.getSelectedItem().toString(), Login.this.frame);
						login.DatabaseConnexionHR(login.email, login.getMdp(), "login", Login.this.frame);

					} catch (Exception sqlException) {
						sqlException.printStackTrace();
					}
				}
			}
		});
		txtLogin.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtLogin.setBounds(145, 137, 253, 26);
		frame.getContentPane().add(txtLogin);
		txtLogin.setColumns(10);

		JButton btnConnexion = new JButton("connexion");
		btnConnexion.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnConnexion.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CompteAdmin login = new CompteAdmin();
				login.email = txtLogin.getText();
				login.setMdp((String.copyValueOf(txtMdp.getPassword())));
				login.setMdp(login.hashMdp(login.getMdp()));
				try {
					//login.DatabaseConnexionHR(login.email, login.getMdp(), cmbType.getSelectedItem().toString(), Login.this.frame);
					login.DatabaseConnexionHR(login.email, login.getMdp(), "login", Login.this.frame);

				} catch (Exception sqlException) {
					sqlException.printStackTrace();
				}
			}
		});
		btnConnexion.setBounds(20, 353, 381, 42);
		frame.getContentPane().add(btnConnexion);
		txtMdp = new JPasswordField();
		txtMdp.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtMdp.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		txtMdp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		///////////	///////////////////////////		/////////////////////////////////////////////////
					CompteAdmin login = new CompteAdmin();
					login.email = txtLogin.getText();
					login.setMdp((String.copyValueOf(txtMdp.getPassword())));
					login.setMdp(login.hashMdp(login.getMdp()));
					try {
						//login.DatabaseConnexionHR(login.email, login.getMdp(), cmbType.getSelectedItem().toString(), Login.this.frame);
						login.DatabaseConnexionHR(login.email, login.getMdp(), "login", Login.this.frame);

					} catch (Exception sqlException) {
						sqlException.printStackTrace();
					}
				}
			}
		});
		txtMdp.setBounds(145, 255, 215, 26);
		frame.getContentPane().add(txtMdp);
		
		JLabel lblType = new JLabel("Type Utilisateur");
		lblType.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblType.setHorizontalAlignment(SwingConstants.CENTER);
		lblType.setBounds(22, 197, 111, 20);
		frame.getContentPane().add(lblType);
		
		cmbType = new JComboBox();
		cmbType.setModel(new DefaultComboBoxModel(new String[] {"Administrateur", "HR Manager", "Comptable", "Vendeur"}));
		cmbType.setBounds(145, 197, 195, 26);
		frame.getContentPane().add(cmbType);
	}
}
