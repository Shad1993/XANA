package CompteUser;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import employes.Entrepot;
import executeurOpSql.DBUtil;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;



public class ListEntrepot {

	JFrame frame;
	private JTable table;

	private JLabel lblNewLabel;
	private JButton btsEffacer;
	private JButton btnMiseajour;
	private JTextField txtNoEntrepot;
	private JTextField txtEmail;
	private JTextField txtNomEntrepot;
	private JTextField txtAdresse;
	private JLabel lblNewLabel_1_7;
	private JLabel lblNewLabel_1_8;
	private JLabel lblNewLabel_1_9;
	/**
	 * Launch the application.
	 */
	public static void main(String login) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListEntrepot window = new ListEntrepot(login);
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
	public ListEntrepot(String login) throws SQLException {
		initialize(login);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize(String login) throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(140, 0, 0));
		frame.setTitle("Entrepots");
		frame.setSize(1118,672);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Voir les Entrepots");
		lblNewLabel.setBounds(0, 0, 1102, 30);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		frame.getContentPane().add(lblNewLabel);
		
		
		
		
		
		
		JButton btnAjouter = new JButton("Ajout\u00E9 un entrepot");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AjouterEntrepot ajouter = new  AjouterEntrepot(login);
				ajouter.frame.setVisible(true);
				frame.setVisible(false); //you can't see me!
				frame.dispose(); //Destroy the JFrame object
			}
		});
		btnAjouter.setBounds(513, 81, 157, 46);
		frame.getContentPane().add(btnAjouter);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuVente home = null;
				try {
					home = new MenuVente(login);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				home.frmDepartementsDeVente.setVisible(true);
				frame.setVisible(false); //you can't see me!
				frame.dispose(); //Destroy the JFrame object
			}
		});
		btnRetour.setBounds(21, 48, 125, 45);
		frame.getContentPane().add(btnRetour);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(368, 167, 709, 390);
		frame.getContentPane().add(scrollPane);
		
		DBUtil affichageEntrepot = new DBUtil();
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model= (DefaultTableModel)table.getModel();
				int selectedRowIndex=table.getSelectedRow();
				txtNoEntrepot.setText(model.getValueAt(selectedRowIndex, 0).toString());
				txtNomEntrepot.setText(model.getValueAt(selectedRowIndex, 1).toString());
				txtAdresse.setText(model.getValueAt(selectedRowIndex, 2).toString());
				txtEmail.setText(model.getValueAt(selectedRowIndex, 3).toString());

			}
		});
		table.setBackground(Color.LIGHT_GRAY);
		table.setFillsViewportHeight(true);
		table.setRowHeight(30);
		table.setRowMargin(6);
		table.setOpaque(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setCellSelectionEnabled(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		affichageEntrepot.getEntrepot(table);
		
		scrollPane.setViewportView(table);
		
		txtNoEntrepot = new JTextField();
		txtNoEntrepot.setEditable(false);
		txtNoEntrepot.setBounds(183, 208, 150, 27);
		frame.getContentPane().add(txtNoEntrepot);
		txtNoEntrepot.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(183, 385, 150, 27);
		frame.getContentPane().add(txtEmail);
		
		
		txtNomEntrepot = new JTextField();
		txtNomEntrepot.setBounds(183, 269, 150, 27);
		frame.getContentPane().add(txtNomEntrepot);
		txtNomEntrepot.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Numero de l'entrepot");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(21, 210, 152, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtAdresse = new JTextField();
		txtAdresse.setBounds(183, 329, 150, 27);
		frame.getContentPane().add(txtAdresse);
		txtAdresse.setColumns(10);
		
		lblNewLabel_1_7 = new JLabel("Nom de l'entrpot");
		lblNewLabel_1_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_7.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_7.setBounds(33, 270, 126, 14);
		frame.getContentPane().add(lblNewLabel_1_7);
		
		lblNewLabel_1_8 = new JLabel("Adresse");
		lblNewLabel_1_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_8.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_8.setBounds(47, 331, 99, 14);
		frame.getContentPane().add(lblNewLabel_1_8);
		
		lblNewLabel_1_9 = new JLabel("Email");
		lblNewLabel_1_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_9.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_9.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_9.setBounds(21, 383, 152, 22);
		frame.getContentPane().add(lblNewLabel_1_9);
		
		btsEffacer = new JButton("Effacer le rang selecter");
		btsEffacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Entrepot entrepot = new Entrepot();
				entrepot.setId_Entrepot(Integer.parseInt(txtNoEntrepot.getText()));
				try {
					DBUtil.deleteEntrepot(entrepot);
					affichageEntrepot.getEntrepot(table);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btsEffacer.setBounds(874, 81, 168, 46);
		frame.getContentPane().add(btsEffacer);
		
		btnMiseajour = new JButton("Mise a jour");
		btnMiseajour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 

				Entrepot entrepot =new Entrepot();
				entrepot.setId_Entrepot(Integer.parseInt(txtNoEntrepot.getText()));
				
				entrepot.setNom(txtNomEntrepot.getText());
				entrepot.setAdresse(txtAdresse.getText());
				entrepot.setEmail(txtEmail.getText());
				try {
					DBUtil.updateEntrepot(entrepot);
					affichageEntrepot.getEntrepot(table);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnMiseajour.setBounds(692, 81, 157, 46);
		frame.getContentPane().add(btnMiseajour);
		
		

		final JTextField txtRecherche = new JTextField();
		txtRecherche.setHorizontalAlignment(SwingConstants.CENTER);
		txtRecherche.setBounds(301, 86, 157, 36);
		frame.getContentPane().add(txtRecherche);
		txtRecherche.setColumns(10);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Recherche : ");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_1_1_1.setBounds(208, 91, 103, 22);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		txtRecherche.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				DefaultTableModel tables = (DefaultTableModel)table.getModel();
				String search = txtRecherche.getText().toLowerCase();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(tables);
				table.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(search));
			}
		});
		
		
	}
}
