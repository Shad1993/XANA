package InterfaceFiche;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.example.utilities.DBUtil;
import com.example.constants.CRUDMode;
import com.example.constants.QueryStatement;
import com.example.db.ConnectionFactory;

import javax.swing.JButton;




public class gererFiche {

	private JFrame frame;
	private final JLayeredPane layeredPane = new JLayeredPane();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private final JLayeredPane layeredPane_1 = new JLayeredPane();
	
	@SuppressWarnings("rawtypes")
	private JComboBox cmbNoEmp;

	public static String noEmp;
	public static String idFiche;
	public static String bonus;
	public static String heureSup;
	public  static String deduction;
	public static String mois;
	public static String commission;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gererFiche window = new gererFiche();
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
	public gererFiche() throws SQLException {
		initialize();
		comboEmp();
		
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 657, 422);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		layeredPane.setBounds(152, 66, 445, 267);
		frame.getContentPane().add(layeredPane);
		
		cmbNoEmp = new JComboBox();
		cmbNoEmp.setBounds(151, 11, 167, 29);
		layeredPane.add(cmbNoEmp);
		
		JLabel lblNewLabel = new JLabel("No Employe");
		lblNewLabel.setBounds(10, 11, 103, 22);
		layeredPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Heure Suppl\u00E9mentaire");
		lblNewLabel_1.setBounds(10, 58, 116, 29);
		layeredPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Bonus");
		lblNewLabel_2.setBounds(10, 116, 48, 14);
		layeredPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Commission");
		lblNewLabel_3.setBounds(10, 158, 103, 22);
		layeredPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("D\u00E9duction");
		lblNewLabel_4.setBounds(10, 201, 82, 22);
		layeredPane.add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setBounds(151, 59, 133, 26);
		layeredPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(151, 107, 132, 26);
		layeredPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(151, 148, 133, 27);
		layeredPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(151, 199, 133, 26);
		layeredPane.add(textField_3);
		
		JButton btnRehcercher = new JButton("Rechercher");
		btnRehcercher.setBounds(329, 11, 106, 32);
		layeredPane.add(btnRehcercher);
		
		JButton btnGenereFiche = new JButton("G\u00E9nerer Fiche de paie");
		btnGenereFiche.setBounds(152, 344, 174, 31);
		frame.getContentPane().add(btnGenereFiche);
		layeredPane_1.setBounds(10, 66, 108, 132);
		frame.getContentPane().add(layeredPane_1);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(10, 11, 89, 31);
		layeredPane_1.add(btnAjouter);
		
		JButton butSupprimer = new JButton("Supprimer");
		butSupprimer.setBounds(10, 74, 89, 31);
		layeredPane_1.add(butSupprimer);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public void comboEmp() throws SQLException{
		
		 String searchQuery = "SELECT No_employe FROM employes";
			Connection connection = ConnectionFactory.getConnection();
			//PreparedStatement preparedStatement = connection.prepareStatement(QueryStatement.searchQuery);
	      java.sql.Statement  preparedStatement = connection.createStatement();

			@SuppressWarnings("unused")
			ResultSet resultSet = preparedStatement.executeQuery(searchQuery);
			
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
