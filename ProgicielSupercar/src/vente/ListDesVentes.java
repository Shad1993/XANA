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
import employes.Vente;
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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;



public class ListDesVentes {

	JFrame frame;
	private JTable table;

	private JLabel lblNewLabel;
	private JButton btsEffacer;
	private JButton btnMiseajour;
	private JTextField txtNoVente;
	private JTextField txtQuantite;
	private JLabel lblNewLabel_1_3;
	private JLabel lblNewLabel_1_4;
	private JLabel lblNewLabel_1_5;
	private JLabel lblNewLabel_1_6;
	private JTextField txtNomClient;
	private JTextField txtNomEmploye;
	private JLabel lblNewLabel_1_7;
	private JLabel lblNewLabel_1_8;
	private JLabel lblNewLabel_1_9;
	private JTextField txtMarque;
	private JTextField txtModele;
	private JComboBox comboBox;
	private JComboBox comboBoxClient;
	private JComboBox comboBoxEmploye;
	private JComboBox comboBoxVoiture;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListDesVentes window = new ListDesVentes();
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
	public ListDesVentes() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(140, 0, 0));
		frame.setTitle("Departement Vente");
		frame.setSize(1206,672);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Voir les vente");
		lblNewLabel.setBounds(0, 0, 1102, 30);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		frame.getContentPane().add(lblNewLabel);
		

		JButton btnAjouter = new JButton("Ajout\u00E9 une vente");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AjouterVente home = null;
				try {
					home = new AjouterVente();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				home.frame.setVisible(true);
				frame.setVisible(false); //you can't see me!
				frame.dispose(); //Destroy the JFrame object
			}
		});
		btnAjouter.setBounds(543, 81, 157, 46);
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
		scrollPane.setBounds(361, 157, 807, 390);
		frame.getContentPane().add(scrollPane);
		
		DBUtil affichage = new DBUtil();
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
		
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model= (DefaultTableModel)table.getModel();
				int selectedRowIndex=table.getSelectedRow();
				comboBox.setSelectedItem(model.getValueAt(selectedRowIndex, 0).toString());
				txtNoVente.setText(model.getValueAt(selectedRowIndex, 1).toString());
				
				try {
					comboBoxClient.setSelectedItem(DBUtil.getClientComboSelected( Integer.parseInt(model.getValueAt(selectedRowIndex, 2).toString())));
				} catch (NumberFormatException | SQLException e1) {
					e1.printStackTrace();
				}
				txtNomClient.setText(model.getValueAt(selectedRowIndex, 3).toString());
				try {
					comboBoxEmploye.setSelectedItem(DBUtil.getEmployeComboSelected( Integer.parseInt(model.getValueAt(selectedRowIndex, 4).toString())));
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				txtNomEmploye.setText(model.getValueAt(selectedRowIndex, 5).toString());
				txtQuantite.setText(model.getValueAt(selectedRowIndex, 6).toString());				
				try {
					comboBoxVoiture.setSelectedItem(DBUtil.getVoitureComboSelected( Integer.parseInt(model.getValueAt(selectedRowIndex, 7).toString())));
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				txtMarque.setText(model.getValueAt(selectedRowIndex, 8).toString());
				txtModele.setText(model.getValueAt(selectedRowIndex, 9).toString());
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
		affichage.getVente(table);
		
		scrollPane.setViewportView(table);
		
		txtNoVente = new JTextField();
		txtNoVente.setEditable(false);
		txtNoVente.setBounds(183, 189, 160, 27);
		frame.getContentPane().add(txtNoVente);
		txtNoVente.setColumns(10);
		
		txtQuantite = new JTextField();
		txtQuantite.setColumns(10);
		txtQuantite.setBounds(183, 389, 160, 27);
		frame.getContentPane().add(txtQuantite);
		
		
		comboBoxClient = new JComboBox(DBUtil.getClientComboBox());
		comboBoxClient.setBounds(183, 227, 160, 27);
		frame.getContentPane().add(comboBoxClient);
		
		comboBoxEmploye = new JComboBox(DBUtil.getEmpComboBox());
		comboBoxEmploye.setBounds(183, 306, 160, 27);
		frame.getContentPane().add(comboBoxEmploye);
		
		comboBoxVoiture = new JComboBox(DBUtil.getVoitureComboBox());
		comboBoxVoiture.setBounds(183, 427, 160, 27);
		frame.getContentPane().add(comboBoxVoiture);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Numero de Vente");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(21, 192, 152, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Quantit\u00E9");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(29, 390, 117, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		lblNewLabel_1_3 = new JLabel("Employe\u00E9 en charge");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_3.setBounds(10, 346, 163, 22);
		frame.getContentPane().add(lblNewLabel_1_3);
		
		lblNewLabel_1_4 = new JLabel("Numero Voiture");
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_4.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_4.setBounds(21, 432, 145, 14);
		frame.getContentPane().add(lblNewLabel_1_4);
		
		lblNewLabel_1_5 = new JLabel("Marque");
		lblNewLabel_1_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_5.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_5.setBounds(45, 474, 85, 14);
		frame.getContentPane().add(lblNewLabel_1_5);
		
		lblNewLabel_1_6 = new JLabel("Modele");
		lblNewLabel_1_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_6.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_6.setBounds(29, 517, 112, 14);
		frame.getContentPane().add(lblNewLabel_1_6);
		
		txtNomClient = new JTextField();
		txtNomClient.setEditable(false);
		txtNomClient.setBounds(183, 268, 160, 27);
		frame.getContentPane().add(txtNomClient);
		txtNomClient.setColumns(10);
		
		txtNomEmploye = new JTextField();
		txtNomEmploye.setEditable(false);
		txtNomEmploye.setText("");
		txtNomEmploye.setBounds(183, 349, 160, 27);
		frame.getContentPane().add(txtNomEmploye);
		txtNomEmploye.setColumns(10);
		
		lblNewLabel_1_7 = new JLabel("Numero Client");
		lblNewLabel_1_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_7.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_7.setBounds(34, 231, 126, 14);
		frame.getContentPane().add(lblNewLabel_1_7);
		
		lblNewLabel_1_8 = new JLabel("Nom Client");
		lblNewLabel_1_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_8.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_8.setBounds(45, 269, 99, 14);
		frame.getContentPane().add(lblNewLabel_1_8);
		
		lblNewLabel_1_9 = new JLabel("Numero Employe\u00E9");
		lblNewLabel_1_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_9.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_9.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_9.setBounds(21, 305, 152, 22);
		frame.getContentPane().add(lblNewLabel_1_9);
		
		txtMarque = new JTextField();
		txtMarque.setEditable(false);
		txtMarque.setColumns(10);
		txtMarque.setBounds(183, 473, 160, 27);
		frame.getContentPane().add(txtMarque);
		
		txtModele = new JTextField();
		txtModele.setEditable(false);
		txtModele.setColumns(10);
		txtModele.setBounds(183, 516, 160, 27);
		frame.getContentPane().add(txtModele);
		
		JLabel lblNewLabel_1_2 = new JLabel("Statut");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_1_2.setBounds(21, 157, 152, 14);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "En Attente", "En Cours", "Terminez"}));
		comboBox.setBounds(183, 151, 160, 27);
		frame.getContentPane().add(comboBox);
		

		btsEffacer = new JButton("Effacer le rang selecter");
		btsEffacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vente vente = new Vente();
				vente.setNo_vente(Integer.parseInt(txtNoVente.getText()));
				try {
					DBUtil.deleteVente(vente);
					affichage.getVente(table);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btsEffacer.setBounds(947, 81, 168, 46);
		frame.getContentPane().add(btsEffacer);
		
		
		btnMiseajour = new JButton("Mise a jour");
		btnMiseajour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Vente vente = new Vente();
				vente.setNo_vente(Integer.parseInt(txtNoVente.getText()));
				vente.setQuantite(Integer.parseInt(txtQuantite.getText()));
				vente.setNoVoiture(Integer.parseInt(comboBoxVoiture.getSelectedItem().toString().substring(0, 3).trim()));
				vente.setNo_Emp(Integer.parseInt(comboBoxVoiture.getSelectedItem().toString().substring(0, 3).trim()));
			
				vente.setNoClient(Integer.parseInt(comboBoxClient.getSelectedItem().toString().substring(0, 3).trim()));
				vente.setStatut(comboBox.getSelectedItem().toString());
				System.out.println(vente.getNoVoiture());
				try {
					DBUtil.updateVente(vente);
					affichage.getVente(table);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnMiseajour.setBounds(745, 81, 157, 46);
		frame.getContentPane().add(btnMiseajour);
		
			
		
//Recherche
		final JTextField txtRecherche = new JTextField();
		txtRecherche.setHorizontalAlignment(SwingConstants.CENTER);
		txtRecherche.setBounds(359, 86, 157, 36);
		frame.getContentPane().add(txtRecherche);
		txtRecherche.setColumns(10);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Recherche : ");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_1_1_1.setBounds(246, 91, 103, 22);
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
