package vente;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import executeurOpSql.DBUtil;

public class ListStock {

	JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListStock window = new ListStock();
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
	public ListStock() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 1039, 565);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setSize(1118,672);
		frame.getContentPane().setBackground(new Color(140, 0, 0));
		frame.setTitle("Stock des voitures");
		
		
		JLabel lblNewLabel = new JLabel("Voir le Stock");
		lblNewLabel.setBounds(0, 0, 1102, 30);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		frame.getContentPane().add(lblNewLabel);
		
		
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
		scrollPane.setBounds(58, 143, 968, 446);
		frame.getContentPane().add(scrollPane);
		
		DBUtil affichageStock = new DBUtil();
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model= (DefaultTableModel)table.getModel();
				int selectedRowIndex=table.getSelectedRow();


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
		affichageStock.getStock(table);
		
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Faire une recherche : ");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_1_1_1.setBounds(331, 91, 222, 22);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		final JTextField txtRecherche = new JTextField();
		txtRecherche.setHorizontalAlignment(SwingConstants.CENTER);
		txtRecherche.setBounds(536, 86, 157, 36);
		frame.getContentPane().add(txtRecherche);
		txtRecherche.setColumns(10);
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
