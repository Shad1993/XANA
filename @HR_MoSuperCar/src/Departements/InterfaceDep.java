package Departements;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class InterfaceDep {

	private JFrame frame;
	private JTextField txtnoDep;
	private JTextField txtDep;
	private final JLayeredPane layeredPane = new JLayeredPane();
	private JTextField txtAdresse;
	private final JScrollPane scrollPane = new JScrollPane();
	private JTextField txtRecherche;
	private final JTable table = new JTable();
	private JTextField txtContact;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceDep window = new InterfaceDep();
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
	public InterfaceDep() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 786, 471);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		layeredPane.setBounds(10, 340, 323, 81);
		frame.getContentPane().add(layeredPane);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(10, 11, 108, 23);
		layeredPane.add(btnAjouter);
		
		JButton btnInit = new JButton("Mise \u00E0 jour");
		btnInit.setBounds(207, 11, 106, 23);
		layeredPane.add(btnInit);
		
		JButton btnSupprimer = new JButton("Effacer champs");
		btnSupprimer.setBounds(10, 45, 108, 23);
		layeredPane.add(btnSupprimer);
		
		JButton btnModifier = new JButton("Supprimer");
		btnModifier.setBounds(207, 45, 106, 23);
		layeredPane.add(btnModifier);
		scrollPane.setBounds(357, 107, 384, 220);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
		
		txtRecherche = new JTextField();
		txtRecherche.setBounds(425, 69, 179, 27);
		frame.getContentPane().add(txtRecherche);
		txtRecherche.setColumns(10);
		
		JButton btnRechercher = new JButton("Rechercehr");
		btnRechercher.setBounds(629, 71, 112, 23);
		frame.getContentPane().add(btnRechercher);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBounds(10, 109, 323, 220);
		frame.getContentPane().add(layeredPane_1);
		
		JLabel lblNewLabel = new JLabel("No D\u00E9partement");
		lblNewLabel.setBounds(10, 11, 96, 20);
		layeredPane_1.add(lblNewLabel);
		
		txtnoDep = new JTextField();
		txtnoDep.setBounds(126, 8, 69, 27);
		layeredPane_1.add(txtnoDep);
		txtnoDep.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nom D\u00E9partement");
		lblNewLabel_1.setBounds(10, 49, 96, 21);
		layeredPane_1.add(lblNewLabel_1);
		
		txtDep = new JTextField();
		txtDep.setBounds(126, 46, 160, 27);
		layeredPane_1.add(txtDep);
		txtDep.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("NoContact");
		lblNewLabel_2.setBounds(10, 144, 61, 14);
		layeredPane_1.add(lblNewLabel_2);
		
		JLabel tblAdresse = new JLabel("Adresse");
		tblAdresse.setBounds(10, 95, 96, 24);
		layeredPane_1.add(tblAdresse);
		
		txtAdresse = new JTextField();
		txtAdresse.setBounds(126, 94, 187, 27);
		layeredPane_1.add(txtAdresse);
		txtAdresse.setColumns(10);
		
		txtContact = new JTextField();
		txtContact.setBounds(126, 141, 123, 27);
		layeredPane_1.add(txtContact);
		txtContact.setColumns(10);
	}
}
