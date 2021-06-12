package vente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;



public class Login extends JFrame implements ActionListener {


	private static final long serialVersionUID = 1L;
	JLabel user_label, password_label, message;
	JTextField userName_text;
	JPasswordField password_text;
	JButton submit, cancel;
	String[] nom;

	public Login() {
		getContentPane().setBackground(new Color(139, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Icon imgIcon = new ImageIcon(this.getClass().getResource("LogoVenteGIF.gif"));
		getContentPane().setLayout(null);
		JLabel label = new JLabel(imgIcon);
		label.setBounds(0, 0, 1102, 186);
		getContentPane().add(label);

		// Submit
		submit = new JButton("Se connecter");
		submit.setBounds(445, 477, 178, 51);
		getContentPane().add(submit);
		submit.setBackground(Color.LIGHT_GRAY);


		// Adding the listeners to components..
		submit.addActionListener(this);
		// Username Label

		user_label = new JLabel();
		user_label.setBounds(279, 246, 164, 42);
		getContentPane().add(user_label);
		user_label.setForeground(Color.LIGHT_GRAY);
		user_label.setBackground(Color.BLACK);
		user_label.setHorizontalAlignment(SwingConstants.CENTER);
		user_label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		user_label.setText("Nom d'utilisateur :");


		userName_text = new JTextField();
		userName_text.setBounds(505, 238, 287, 58);
		getContentPane().add(userName_text);
		userName_text.setBackground(Color.LIGHT_GRAY);
		userName_text.setFont(new Font("Tahoma", Font.PLAIN, 18));
		userName_text.setHorizontalAlignment(SwingConstants.CENTER);
		user_label.setLabelFor(userName_text);
		// Password Label
		password_label = new JLabel();
		password_label.setBounds(320, 345, 123, 22);
		getContentPane().add(password_label);
		password_label.setForeground(Color.LIGHT_GRAY);
		password_label.setHorizontalAlignment(SwingConstants.CENTER);
		password_label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		password_label.setText("Mot de passe : ");


		password_text = new JPasswordField();
		password_text.setBounds(505, 327, 287, 58);
		getContentPane().add(password_text);
		password_text.setBackground(Color.LIGHT_GRAY);
		password_text.setHorizontalAlignment(SwingConstants.CENTER);
		password_text.setFont(new Font("Tahoma", Font.PLAIN, 18));
		message = new JLabel();
		message.setBounds(0, 0, 106, 45);
		getContentPane().add(message);
		message.setForeground(Color.LIGHT_GRAY);
		message.setFont(new Font("Tahoma", Font.PLAIN, 16));
		message.setBackground(Color.DARK_GRAY);
		message.setHorizontalAlignment(SwingConstants.CENTER);
		setTitle("Connecter Vous !!!");
		setSize(1118,672);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Login();
	}


	@Override
	public void actionPerformed(ActionEvent ae) {

		// create our mysql database connection
		String myUrl = "jdbc:mysql://localhost/moloto";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(myUrl, "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String userName = userName_text.getText();
		String password = password_text.getText();
		// our SQL SELECT query. 
		// if you only need a few columns, specify them by name instead of using "*"
		String query = "SELECT * FROM utilisateur WHERE Nom = '"+userName+"'";

		// create the java statement
		Statement st = null;
		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// execute the query, and get a java resultset
		ResultSet rs = null;
		try {
			rs = st.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// iterate through the java resultset
		try {
			while (rs.next())
			{
				int id = rs.getInt("id");
				String Nom = rs.getString("Nom");
				String Email = rs.getString("Email");
				String Mdp = rs.getString("Mdp");
				String Role = rs.getString("role");

				if (userName.trim().equals(Nom)  && hashPassword(password).trim().equals(Mdp)) {
				
			
					MenuVente home = null;
					try {
						home = new MenuVente();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					home.frmDepartementsDeVente.setVisible(true);
					setVisible(false); 
					dispose(); 

				} else if (userName.trim().equals(Nom) && Role.equals("Vendeur") && hashPassword(password).trim().equals(Mdp)) {
					Menu page1 = new Menu();
					dispose();
				}
				// print the results
				System.out.format("%s, %s, %s, %s, %s,\n", id, Nom, Email, Mdp, Role);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String hashPassword(String charactere) {
		try {
			byte[] octet = charactere.getBytes();
			MessageDigest monHash = MessageDigest.getInstance("SHA");
			monHash.update(octet);
			byte[] condenser = monHash.digest();
			charactere = new BigInteger(condenser).toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return charactere;
	}


}