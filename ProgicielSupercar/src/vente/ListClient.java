package vente;

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

import employes.Client;

import executeurOpSql.DBUtil;


import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class ListClient {

	JFrame frame;
	private JTable table;

	private JLabel lblNewLabel;
	private JButton btsEffacer;
	private JButton btnMiseajour;
	private JTextField txtNoClient;
	private JTextField txtNIC;
	private JTextField txtNomClient;
	private JTextField txtPrenom;
	private JLabel lblNewLabel_1_7;
	private JLabel lblNewLabel_1_8;
	private JLabel lblNewLabel_1_9;
	private JTextField txtAdresse;
	private JTextField txtEmail;
	private JTextField txtNumero;
	private JTextField txtRecherche;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListClient window = new ListClient();
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
	public ListClient() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frame =  new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(140, 0, 0));
		frame.setTitle("Clients");
		frame.setSize(1118,672);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Voir les Clients");
		lblNewLabel.setBounds(0, 0, 1102, 30);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		frame.getContentPane().add(lblNewLabel);
		
		
		JButton btnAjouter = new JButton("Ajout\u00E9 un Client");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AjouterClient ajouterClient = new AjouterClient();
				ajouterClient.frame.setVisible(true);
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
					home = new MenuVente();
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
		
		DBUtil affichageClient = new DBUtil();
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model= (DefaultTableModel)table.getModel();
				int selectedRowIndex=table.getSelectedRow();
				txtNoClient.setText(model.getValueAt(selectedRowIndex, 0).toString());
				txtNomClient.setText(model.getValueAt(selectedRowIndex, 1).toString());
				txtPrenom.setText(model.getValueAt(selectedRowIndex, 2).toString());
				txtNIC.setText(model.getValueAt(selectedRowIndex, 3).toString());
				txtAdresse.setText(model.getValueAt(selectedRowIndex, 4).toString());
				txtEmail.setText(model.getValueAt(selectedRowIndex, 5).toString());
				txtNumero.setText(model.getValueAt(selectedRowIndex, 6).toString());

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
		affichageClient.getClient(table);
		
		scrollPane.setViewportView(table);
		
		txtNoClient = new JTextField();
		txtNoClient.setEditable(false);
		txtNoClient.setBounds(183, 208, 150, 27);
		frame.getContentPane().add(txtNoClient);
		txtNoClient.setColumns(10);
		
		txtNIC = new JTextField();
		txtNIC.setColumns(10);
		txtNIC.setBounds(183, 385, 150, 27);
		frame.getContentPane().add(txtNIC);
		
		
		
		txtNomClient = new JTextField();
		txtNomClient.setBounds(183, 269, 150, 27);
		frame.getContentPane().add(txtNomClient);
		txtNomClient.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Numero Client");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(21, 210, 152, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtPrenom = new JTextField();
		txtPrenom.setBounds(183, 329, 150, 27);
		frame.getContentPane().add(txtPrenom);
		txtPrenom.setColumns(10);
		
		lblNewLabel_1_7 = new JLabel("Nom");
		lblNewLabel_1_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_7.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_7.setBounds(33, 270, 126, 14);
		frame.getContentPane().add(lblNewLabel_1_7);
		
		lblNewLabel_1_8 = new JLabel("Prenom");
		lblNewLabel_1_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_8.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_8.setBounds(47, 331, 99, 14);
		frame.getContentPane().add(lblNewLabel_1_8);
		
		lblNewLabel_1_9 = new JLabel("NIC");
		lblNewLabel_1_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_9.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_9.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_9.setBounds(21, 383, 152, 22);
		frame.getContentPane().add(lblNewLabel_1_9);
		
		txtAdresse = new JTextField();
		txtAdresse.setColumns(10);
		txtAdresse.setBounds(183, 439, 150, 27);
		frame.getContentPane().add(txtAdresse);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(183, 490, 150, 27);
		frame.getContentPane().add(txtEmail);
		
		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		txtNumero.setBounds(183, 542, 150, 27);
		frame.getContentPane().add(txtNumero);
		
		JLabel lblNewLabel_1_9_1 = new JLabel("Adresse");
		lblNewLabel_1_9_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_9_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_9_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_9_1.setBounds(21, 445, 152, 22);
		frame.getContentPane().add(lblNewLabel_1_9_1);
		
		JLabel lblNewLabel_1_9_2 = new JLabel("Email");
		lblNewLabel_1_9_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_9_2.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_9_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_9_2.setBounds(21, 496, 152, 22);
		frame.getContentPane().add(lblNewLabel_1_9_2);
		
		JLabel lblNewLabel_1_9_3 = new JLabel("Numero contact");
		lblNewLabel_1_9_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_9_3.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_9_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_9_3.setBounds(21, 548, 152, 22);
		frame.getContentPane().add(lblNewLabel_1_9_3);
		
		//Mise a jour
		btnMiseajour = new JButton("Mise a jour");
		btnMiseajour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Client client = new Client();
				client.setNo_client(Integer.parseInt(txtNoClient.getText()));
				client.setNom(txtNomClient.getText());
				client.setPrenom(txtPrenom.getText());
				client.setNCI(txtNIC.getText());
				client.setAdresse(txtAdresse.getText());
				client.setAdresseEmail(txtEmail.getText());
				client.setNo_contact(Integer.parseInt(txtNumero.getText()));
				
				try {
					DBUtil.updateClient(client);
					affichageClient.getClient(table);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnMiseajour.setBounds(692, 81, 157, 46);
		frame.getContentPane().add(btnMiseajour);
			
		//Bouton Supprimer
		btsEffacer = new JButton("Supprimer le rang selecter");
		btsEffacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Client client = new Client();
				client.setNo_client(Integer.parseInt(txtNoClient.getText()));
				try {
					DBUtil.deleteClient(client);
					affichageClient.getClient(table);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btsEffacer.setBounds(874, 81, 168, 46);
		frame.getContentPane().add(btsEffacer);
		

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
