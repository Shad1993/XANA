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

import employes.Voiture;
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



public class ListVoiture {

	JFrame frame;
	private JTable table;

	private JLabel lblNewLabel;
	private JButton btsEffacer;
	private JButton btnMiseajour;
	private JTextField txtMarque;
	private JTextField txtService;
	private JTextField txtModele;
	private JTextField txtPays;
	private JLabel lblNewLabel_1_7;
	private JLabel lblNewLabel_1_8;
	private JLabel lblNewLabel_1_9;
	private JLabel lblCouleur;
	private JLabel lblGarantie;
	private JLabel lblTransmission;
	private JLabel lblMoteur;
	private JLabel lblPrix;
	private JLabel lblDateMiseEn;
	private JTextField txtCouleur;
	private JTextField txtGarantie;
	private JTextField txtTransmission;
	private JTextField txtMoteur;
	private JTextField txtPrix;
	private JTextField txtDate;
	private JTextField txtId;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListVoiture window = new ListVoiture();
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
	public ListVoiture() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 1400, 672);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(140, 0, 0));
		frame.setTitle("Entrepots");
		frame.setSize(1272,672);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Voir les voitures");
		lblNewLabel.setBounds(0, 0, 1102, 30);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		frame.getContentPane().add(lblNewLabel);
		
		
		
		
		
		
		JButton btnAjouter = new JButton("Ajout\u00E9 une voiture");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AjouterVoiture ajouter = null;
				try {
					ajouter = new  AjouterVoiture();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
		scrollPane.setBounds(368, 167, 860, 390);
		frame.getContentPane().add(scrollPane);
		
		DBUtil affichageVoiture = new DBUtil();
		table = new JTable();
		affichageVoiture.getVoiture(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model= (DefaultTableModel)table.getModel();
				int selectedRowIndex=table.getSelectedRow();
				txtId.setText(model.getValueAt(selectedRowIndex, 0).toString());
				txtMarque.setText(model.getValueAt(selectedRowIndex, 1).toString());
				txtModele.setText(model.getValueAt(selectedRowIndex, 2).toString());
				txtPays.setText(model.getValueAt(selectedRowIndex, 3).toString());
				txtService.setText(model.getValueAt(selectedRowIndex, 4).toString());
				txtCouleur.setText(model.getValueAt(selectedRowIndex, 5).toString());
				txtGarantie.setText(model.getValueAt(selectedRowIndex, 6).toString());
				txtTransmission.setText(model.getValueAt(selectedRowIndex, 7).toString());
				txtMoteur.setText(model.getValueAt(selectedRowIndex, 8).toString());
				txtPrix.setText(model.getValueAt(selectedRowIndex, 9).toString());
				txtDate.setText(model.getValueAt(selectedRowIndex, 10).toString());

			}
		});
		table.setBackground(Color.LIGHT_GRAY);
		table.setFillsViewportHeight(true);
		table.setRowHeight(30);
		table.setRowMargin(6);
		table.setOpaque(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setCellSelectionEnabled(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		affichageVoiture.getVoiture(table);
		
		scrollPane.setViewportView(table);
		
		txtMarque = new JTextField();
		txtMarque.setBounds(183, 213, 150, 27);
		frame.getContentPane().add(txtMarque);
		txtMarque.setColumns(10);
		
		txtService = new JTextField();
		txtService.setColumns(10);
		txtService.setBounds(183, 329, 150, 27);
		frame.getContentPane().add(txtService);
		
		
		txtModele = new JTextField();
		txtModele.setBounds(183, 253, 150, 27);
		frame.getContentPane().add(txtModele);
		txtModele.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Marque");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(21, 213, 152, 22);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtPays = new JTextField();
		txtPays.setBounds(183, 291, 150, 27);
		frame.getContentPane().add(txtPays);
		txtPays.setColumns(10);
		
		lblNewLabel_1_7 = new JLabel("Modele");
		lblNewLabel_1_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_7.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_7.setBounds(21, 253, 152, 22);
		frame.getContentPane().add(lblNewLabel_1_7);
		
		lblNewLabel_1_8 = new JLabel("Pays D'origine");
		lblNewLabel_1_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_8.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_8.setBounds(21, 291, 152, 23);
		frame.getContentPane().add(lblNewLabel_1_8);
		
		lblNewLabel_1_9 = new JLabel("Interval de service");
		lblNewLabel_1_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_9.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_9.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_9.setBounds(21, 329, 152, 22);
		frame.getContentPane().add(lblNewLabel_1_9);
		
		btsEffacer = new JButton("Effacer le rang selecter");
		btsEffacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Voiture voiture = new Voiture();
				voiture.setNo_voiture(Integer.parseInt(txtId.getText()));
				try {
					DBUtil.deleteVoiture(voiture);
					affichageVoiture.getVoiture(table);
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
				Voiture voiture = new Voiture();
				voiture.setNo_voiture(Integer.parseInt(txtId.getText()));
				voiture.setMarque(txtMarque.getText());
				voiture.setModele(txtModele.getText()) ;
				voiture.setPays_dorigine(txtPays.getText());
				voiture.setIntervall_de_service(txtService.getText()) ;
				voiture.setCouleur(txtCouleur.getText()) ;
				voiture.setGarentie(txtGarantie.getText()) ;
				voiture.setTransmission(txtTransmission.getText()) ;
				voiture.setMoteur(txtMoteur.getText()) ;
				voiture.setPrix(Integer.parseInt(txtPrix.getText())) ;
			   
		
					java.sql.Date sqlDate = java.sql.Date.valueOf( txtDate.getText() );
		 
				voiture.setDateMiseEnVente(sqlDate);
				
				
				try {
					DBUtil.updateVoiture(voiture);
					affichageVoiture.getVoiture(table);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnMiseajour.setBounds(692, 81, 157, 46);
		frame.getContentPane().add(btnMiseajour);
		
		lblCouleur = new JLabel("Couleur");
		lblCouleur.setHorizontalAlignment(SwingConstants.CENTER);
		lblCouleur.setForeground(Color.LIGHT_GRAY);
		lblCouleur.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCouleur.setBounds(21, 367, 152, 22);
		frame.getContentPane().add(lblCouleur);
		
		lblGarantie = new JLabel("Garantie");
		lblGarantie.setHorizontalAlignment(SwingConstants.CENTER);
		lblGarantie.setForeground(Color.LIGHT_GRAY);
		lblGarantie.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGarantie.setBounds(21, 405, 152, 22);
		frame.getContentPane().add(lblGarantie);
		
		lblTransmission = new JLabel("Transmission");
		lblTransmission.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransmission.setForeground(Color.LIGHT_GRAY);
		lblTransmission.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTransmission.setBounds(21, 444, 152, 22);
		frame.getContentPane().add(lblTransmission);
		
		lblMoteur = new JLabel("Moteur");
		lblMoteur.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoteur.setForeground(Color.LIGHT_GRAY);
		lblMoteur.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMoteur.setBounds(21, 481, 152, 22);
		frame.getContentPane().add(lblMoteur);
		
		lblPrix = new JLabel("Prix");
		lblPrix.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrix.setForeground(Color.LIGHT_GRAY);
		lblPrix.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrix.setBounds(21, 519, 152, 22);
		frame.getContentPane().add(lblPrix);
		
		lblDateMiseEn = new JLabel("Date mise en vente");
		lblDateMiseEn.setHorizontalAlignment(SwingConstants.CENTER);
		lblDateMiseEn.setForeground(Color.LIGHT_GRAY);
		lblDateMiseEn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDateMiseEn.setBounds(21, 557, 152, 22);
		frame.getContentPane().add(lblDateMiseEn);
		
		txtCouleur = new JTextField();
		txtCouleur.setColumns(10);
		txtCouleur.setBounds(183, 367, 150, 27);
		frame.getContentPane().add(txtCouleur);
		
		txtGarantie = new JTextField();
		txtGarantie.setColumns(10);
		txtGarantie.setBounds(183, 405, 150, 27);
		frame.getContentPane().add(txtGarantie);
		
		txtTransmission = new JTextField();
		txtTransmission.setColumns(10);
		txtTransmission.setBounds(183, 443, 150, 27);
		frame.getContentPane().add(txtTransmission);
		
		txtMoteur = new JTextField();
		txtMoteur.setColumns(10);
		txtMoteur.setBounds(183, 481, 150, 27);
		frame.getContentPane().add(txtMoteur);
		
		txtPrix = new JTextField();
		txtPrix.setColumns(10);
		txtPrix.setBounds(183, 519, 150, 27);
		frame.getContentPane().add(txtPrix);
		
		txtDate = new JTextField();
		txtDate.setColumns(10);
		txtDate.setBounds(183, 557, 150, 27);
		frame.getContentPane().add(txtDate);
		

		
		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.setBounds(183, 176, 150, 27);
		frame.getContentPane().add(txtId);
		
		JLabel lblNewLabel_1_1 = new JLabel("Id");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_1_1.setBounds(21, 179, 152, 22);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		
		
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
