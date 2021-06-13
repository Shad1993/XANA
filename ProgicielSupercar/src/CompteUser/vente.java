package CompteUser;


import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.mysql.jdbc.PreparedStatement;


import net.proteanit.sql.DbUtils;


import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.Toolkit;

public class vente extends JFrame {
	private JPanel contentPane;
	 JFrame frmAdmin;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String login) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vente window = new vente(login);
					window.frmAdmin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param login 
	 */
	public vente(String login) {
		initialize(login);
		Connect();
		table_load();
		
		
		
	}
	 private void fetchTotal() {
	       
	         
	      
	        
	        try 
	    	{
		    pst = con.prepareStatement("SELECT SUM(ve.Quantite * v.prix) AS Total FROM voitures v,vente ve WHERE ve.NoVoiture=v.No_voiture");
		    rs = pst.executeQuery();
		    while (rs.next()) {
            	String Total= rs.getString("Total");
            	textField.setText(Total);
            }
		} 
	    	catch (SQLException e) 
	    	 {
	    		e.printStackTrace();
		  } 
	        
	    }
	 private void fetchTotalA() {
	       
         
	      
	        
	        try 
	    	{
		    pst = con.prepareStatement("SELECT SUM(ve.Quantite * v.prix) AS Total FROM voitures v,vente ve WHERE ve.NoVoiture=v.No_voiture AND ve.date_Vente >= Date_add(Now(),interval - 12 month)");
		    rs = pst.executeQuery();
		    while (rs.next()) {
         	String Total= rs.getString("Total");
         	textField_3.setText(Total);
         }
		} 
	    	catch (SQLException e) 
	    	 {
	    		e.printStackTrace();
		  } 
	        
	    }
	 
	 private void fetchTotalM() {
	     
	        try 
	    	{
		    pst = con.prepareStatement("SELECT SUM(ve.Quantite * v.prix) AS Total FROM voitures v,vente ve WHERE ve.NoVoiture=v.No_voiture AND MONTH(ve.date_Vente) = MONTH(CURRENT_DATE()) AND YEAR(ve.date_Vente) = YEAR(CURRENT_DATE())");
		    rs = pst.executeQuery();
		    while (rs.next()) {
      	String Total= rs.getString("Total");
      	textField_1.setText(Total);
      }
		} 
	    	catch (SQLException e) 
	    	 {
	    		e.printStackTrace();
		  } 
	        
	    }
	 
	 private void fetchTotalH() {
	     
	        try 
	    	{
		    pst = con.prepareStatement("SELECT SUM(ve.Quantite * v.prix) AS Total FROM voitures v,vente ve WHERE ve.NoVoiture=v.No_voiture AND ve.date_Vente >= DATE_SUB(NOW(), INTERVAL 1 WEEK) GROUP BY WEEK(ve.date_Vente)");
		    rs = pst.executeQuery();
		    while (rs.next()) {
   	String Total= rs.getString("Total");
   	textField_4.setText(Total);
   }
		} 
	    	catch (SQLException e) 
	    	 {
	    		e.printStackTrace();
		  } 
	        
	    }
	 
	 private void fetchTotalJ() {
	     
	        try 
	    	{
		    pst = con.prepareStatement("SELECT SUM(ve.Quantite * v.prix) AS Total FROM voitures v,vente ve WHERE ve.NoVoiture=v.No_voiture AND ve.date_Vente= CURRENT_DATE");
		    rs = pst.executeQuery();
		    while (rs.next()) {
	String Total= rs.getString("Total");
	textField_2.setText(Total);
}
		} 
	    	catch (SQLException e) 
	    	 {
	    		e.printStackTrace();
		  } 
	        
	    }
	Connection con;
	java.sql.PreparedStatement pst;
	ResultSet rs;
	Statement st;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private final Action action = new SwingAction();
  
    
    
	 public void Connect()
	    {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moloto", "root", "");
	        }
	        catch (ClassNotFoundException ex) 
	        {
	          ex.printStackTrace();
	        }
	        catch (SQLException ex) 
	        {
	        	   ex.printStackTrace();
	        }
 
	    }
	
	
		public void table_load()
	    {
	    	try 
	    	{
		    pst = con.prepareStatement("SELECT v.No_vente AS No_vente,v.Quantite AS Quantite,vo.Prix AS Prix,vo.Modele AS Modele FROM vente v,voitures vo");
		    rs = pst.executeQuery();
		    table.setModel(DbUtils.resultSetToTableModel(rs));
		} 
	    	catch (SQLException e) 
	    	 {
	    		e.printStackTrace();
		  } 
	    }
		public void table_loadAnnuel()
	    {
	    	try 
	    	{
		    pst = con.prepareStatement("SELECT v.No_vente AS No_vente,v.Quantite AS Quantite,vo.Prix AS Prix,vo.Modele AS Modele FROM vente v,voitures vo WHERE vo.No_voiture=v.NoVoiture AND v.date_Vente >= Date_add(Now(),interval - 12 month)");
		    rs = pst.executeQuery();
		    table.setModel(DbUtils.resultSetToTableModel(rs));
		} 
	    	catch (SQLException e) 
	    	 {
	    		e.printStackTrace();
		  } 
	    }
		
		public void table_loadJ()
	    {
	    	try 
	    	{
		    pst = con.prepareStatement("SELECT v.No_vente AS No_vente,v.Quantite AS Quantite,vo.Prix AS Prix,vo.Modele AS Modele FROM vente v,voitures vo WHERE vo.No_voiture=v.NoVoiture AND v.date_Vente= CURRENT_DATE");
		    rs = pst.executeQuery();
		    table.setModel(DbUtils.resultSetToTableModel(rs));
		} 
	    	catch (SQLException e) 
	    	 {
	    		e.printStackTrace();
		  } 
	    }
		
		public void table_loadM()
	    {
	    	try 
	    	{
		    pst = con.prepareStatement("SELECT v.No_vente AS No_vente,v.Quantite AS Quantite,vo.Prix AS Prix,vo.Modele AS Modele FROM vente v,voitures vo WHERE vo.No_voiture=v.NoVoiture AND MONTH(v.date_Vente) = MONTH(CURRENT_DATE()) AND YEAR(v.date_Vente) = YEAR(CURRENT_DATE())");
		    rs = pst.executeQuery();
		    table.setModel(DbUtils.resultSetToTableModel(rs));
		} 
	    	catch (SQLException e) 
	    	 {
	    		e.printStackTrace();
		  } 
	    }
		
		public void table_loadH()
	    {
	    	try 
	    	{
		    pst = con.prepareStatement("SELECT  v.No_vente AS No_vente,v.Quantite AS Quantite,vo.Prix AS Prix,vo.Modele AS Modele FROM vente v,voitures vo WHERE vo.No_voiture=v.NoVoiture AND v.date_Vente >= DATE_SUB(NOW(), INTERVAL 1 WEEK) GROUP BY WEEK(v.date_Vente)");
		    rs = pst.executeQuery();
		    table.setModel(DbUtils.resultSetToTableModel(rs));
		} 
	    	catch (SQLException e) 
	    	 {
	    		e.printStackTrace();
		  } 
	    }
		
		
	/**
	 * Initialize the contents of the frame.
	 * @param login 
	 */
	private void initialize(String login) {
		frmAdmin = new JFrame();
		frmAdmin.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Shad\\Desktop\\logoppe3\\Logo.png"));
		frmAdmin.setTitle("Vente");
		frmAdmin.setBounds(100, 100, 561, 541);
		frmAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblGestionDesCommandes = new JLabel("Bilan des Vente");
		lblGestionDesCommandes.setForeground(Color.DARK_GRAY);
		JLabel show_validation_here = new JLabel("");
		show_validation_here.setForeground(Color.RED);
		
		JLabel qu = new JLabel("");
		qu.setForeground(Color.RED);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel show = new JLabel("");
		show.setForeground(Color.RED);
		
		JButton btnTotal = new JButton("Tout");
		btnTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fetchTotal();
        		
	            setVisible(true);
			}
		});
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnAnnuel = new JButton("Annuel");
		btnAnnuel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_loadAnnuel();
			}
		});
		
		JButton btnMensuel = new JButton("Mensuel");
		btnMensuel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_loadM();
			}
		});
		
		JButton btnJournalier = new JButton("Journalier");
		btnJournalier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_loadJ();
			}
		});
		
		JButton btnHebdoadaire = new JButton("Hebdoadaire");
		btnHebdoadaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_loadH();
			}
		});
		
		JButton btnRafraichir = new JButton("Tout Voir");
		btnRafraichir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_load();
			}
		});
		
		JButton btnTotalJournalier = new JButton("Total Journalier");
		
		JButton btnTotalMensuel = new JButton("Mensuel");
		btnTotalMensuel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fetchTotalM();
			}
		});
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JButton btnTotal_1_1 = new JButton("Journalier");
		btnTotal_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fetchTotalJ() ;
			}
		});
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Total");
		
		JButton btnAnnuel_1 = new JButton("Annuel");
		btnAnnuel_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fetchTotalA();
			}
		});
		
		JButton btnHebdo = new JButton("Hebdo");
		btnHebdo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fetchTotalH();
			}
		});
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		
		
		
		
	
		
		
		GroupLayout groupLayout = new GroupLayout(frmAdmin.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addContainerGap()
									.addComponent(btnJournalier, GroupLayout.PREFERRED_SIZE, 76, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addContainerGap()
									.addComponent(btnHebdoadaire, GroupLayout.PREFERRED_SIZE, 76, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(9)
									.addComponent(btnAnnuel, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addContainerGap()
									.addComponent(btnRafraichir, GroupLayout.PREFERRED_SIZE, 76, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addContainerGap()
									.addComponent(btnMensuel, GroupLayout.PREFERRED_SIZE, 76, Short.MAX_VALUE)))
							.addGap(4)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(show)
										.addComponent(show_validation_here))
									.addGap(65)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(btnTotalJournalier, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(152)
													.addComponent(lblNewLabel))
												.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
													.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
															.addComponent(btnTotal_1_1, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(ComponentPlacement.RELATED)
															.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
														.addGroup(groupLayout.createSequentialGroup()
															.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
																.addComponent(btnTotal, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(btnTotalMensuel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
															.addPreferredGap(ComponentPlacement.RELATED)
															.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addComponent(textField_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
																.addComponent(textField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))))
													.addGap(18)
													.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
															.addComponent(btnAnnuel_1, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(ComponentPlacement.RELATED)
															.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
														.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
															.addComponent(btnHebdo, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(ComponentPlacement.RELATED)
															.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))))))))
								.addComponent(qu)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(223)
							.addComponent(lblGestionDesCommandes)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(82)
							.addComponent(btnAnnuel)
							.addGap(18)
							.addComponent(btnMensuel)
							.addGap(18)
							.addComponent(btnJournalier)
							.addGap(18)
							.addComponent(btnHebdoadaire)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(show)
										.addComponent(show_validation_here))
									.addGap(81))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addComponent(btnRafraichir)
									.addGap(111)
									.addComponent(btnTotalJournalier))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(19)
							.addComponent(lblGestionDesCommandes)
							.addGap(18)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnTotal)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAnnuel_1)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(6)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnTotalMensuel)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnHebdo)
								.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnTotal_1_1)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addComponent(qu)
					.addContainerGap())
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		frmAdmin.getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		frmAdmin.setJMenuBar(menuBar);
		
		JMenu mnAcceuil = new JMenu("Menu");
		menuBar.add(mnAcceuil);
		
		JMenuItem mntmAide = new JMenuItem("Aide");
		mntmAide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					 JOptionPane.showMessageDialog(null, "Connectez vous a :'https://ppe3reshad.moodlecloud.com/my/'");
			}
		});
		mnAcceuil.add(mntmAide);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
