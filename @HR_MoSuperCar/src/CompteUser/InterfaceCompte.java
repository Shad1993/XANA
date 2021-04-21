package CompteUser;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.example.db.ConnectionFactory;
import com.sun.jdi.connect.spi.Connection;

import javax.swing.JComboBox;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class InterfaceCompte {

	private JFrame frame;
	private JTextField txtidUser;
	private JTextField txtEmail;
	private JTextField txtMdp;
	private final JLayeredPane layeredPane = new JLayeredPane();
	private final JLayeredPane layeredPane_1 = new JLayeredPane();
	private final JScrollPane scrollPane = new JScrollPane();
	private JTable table;
	private JTextField txtRehercher;
	
	@SuppressWarnings("rawtypes")
	private JComboBox cmbNoEmp;

	private static Connection connection;
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceCompte window = new InterfaceCompte();
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
	public InterfaceCompte() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(UIManager.getColor("activeCaption"));
		frame.setBounds(100, 100, 921, 490);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		layeredPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		layeredPane.setBounds(10, 97, 367, 183);
		frame.getContentPane().add(layeredPane);
		
		
		
		
		
		
		JLabel lblNewLabel = new JLabel("No Employe");
		lblNewLabel.setBounds(10, 11, 96, 14);
		layeredPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Id User");
		lblNewLabel_1.setBounds(10, 55, 71, 14);
		layeredPane.add(lblNewLabel_1);
		
		txtidUser = new JTextField();
		txtidUser.setBounds(163, 52, 117, 20);
		layeredPane.add(txtidUser);
		txtidUser.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Emaill");
		lblNewLabel_2.setBounds(10, 105, 96, 14);
		layeredPane.add(lblNewLabel_2);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(164, 102, 170, 20);
		layeredPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Mot de passe");
		lblNewLabel_3.setBounds(10, 158, 96, 14);
		layeredPane.add(lblNewLabel_3);
		
		txtMdp = new JTextField();
		txtMdp.setBounds(163, 155, 117, 20);
		layeredPane.add(txtMdp);
		txtMdp.setColumns(10);
		
		cmbNoEmp = new JComboBox();
		

		
		cmbNoEmp.setBounds(163, 7, 120, 22);
		layeredPane.add(cmbNoEmp);
		
		try {
			comboEmp();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		layeredPane_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		
		layeredPane_1.setBounds(10, 291, 367, 149);
		frame.getContentPane().add(layeredPane_1);
		
		JButton btnAjouter = new JButton("Ajouter Compte");
		btnAjouter.setBounds(10, 13, 347, 23);
		layeredPane_1.add(btnAjouter);
		
		JButton bntEffacerChamps = new JButton("Effacer Champs");
		bntEffacerChamps.setBounds(10, 47, 347, 23);
		layeredPane_1.add(bntEffacerChamps);
		
		JButton btnModifier = new JButton("Modifier Compte");
		btnModifier.setBounds(10, 81, 347, 23);
		layeredPane_1.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer Compte");
		btnSupprimer.setBounds(10, 115, 347, 23);
		layeredPane_1.add(btnSupprimer);
		
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.setBounds(566, 55, 142, 31);
		frame.getContentPane().add(btnRechercher);
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(387, 97, 508, 343);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		
          Compte compte = new Compte();
		
		    try {
			    compte.getAllComptes(table);
		    } catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		 }
		
		
		
		
		
		
		
		txtRehercher = new JTextField();
		txtRehercher.setBounds(387, 55, 169, 29);
		frame.getContentPane().add(txtRehercher);
		txtRehercher.setColumns(10);
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}
	

	@SuppressWarnings("unchecked")
	public void comboEmp() throws SQLException{
		
		 String searchQuery = "SELECT No_employe FROM employes";
		java.sql.Connection	 connection = ConnectionFactory.getConnection();
			//PreparedStatement preparedStatement = connection.prepareStatement(QueryStatement.searchQuery);
	        Statement preparedStatement = connection.createStatement();

			@SuppressWarnings("unused")
			ResultSet resultSet = preparedStatement.executeQuery(searchQuery);
			
			cmbNoEmp.addItem("");
		try {
			
			while (resultSet.next()) {
				
				   cmbNoEmp.addItem(resultSet.getString("No_employe"));
				   
			}
		}				
			
		catch(SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
					
			}	
		
				    
	       
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
