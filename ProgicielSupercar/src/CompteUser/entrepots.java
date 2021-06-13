package CompteUser;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.text.SimpleDateFormat;

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
//import com.mysql.jdbc.PreparedStatement;


import net.proteanit.sql.DbUtils;
//import com.toedter.calendar.JDateChooser;

import CompteUser.MenuAdm;

import javax.swing.JComboBox;
import java.awt.Color;

public class entrepots {

	private JFrame frmAdmin;
	private JTextField NDC;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String login) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					entrepots window = new entrepots(login);
					window.frmAdmin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public entrepots(String login) {
		initialize(login);
		Connect();
		table_load();
		loadvoitures();
		
		
	}

	Connection con;
	java.sql.PreparedStatement pst;
	ResultSet rs;
	Statement st;
	JComboBox comboBox = new JComboBox();
    public void loadvoitures ()
    {
   
    try{
        con = DriverManager.getConnection("jdbc:mysql://localhost/moloto", "root",
				"");
      st = con.createStatement();
      String s = "select * from voitures";
      rs = st.executeQuery(s);
        while(rs.next())
        {
        	
        	comboBox.addItem(rs.getString(1));
        }
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, "ERREUR ");
    }finally{
        try{
            st.close();
            rs.close();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erreur Fermeture");
        }
    }
    
    }
    
   
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
		    pst = con.prepareStatement("select * from entrepot");
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
		frmAdmin.setTitle("Admin");
		frmAdmin.setBounds(100, 100, 561, 465);
		frmAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblGestionDesCommandes = new JLabel("Gestion de l'entrepot");
		
		JLabel lblFormulaire = new JLabel("Formulaire");
		
		JLabel lblNewLabel = new JLabel("Identifiant entrepot");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 11));
		JLabel show_validation_here = new JLabel("");
		show_validation_here.setForeground(Color.RED);
		
		JLabel qu = new JLabel("");
		qu.setForeground(Color.RED);
		
		NDC = new JTextField();
		NDC.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				try {
					int i= Integer.parseInt(NDC.getText());
					show_validation_here.setText("");
				} catch (NumberFormatException e1){
					show_validation_here.setText("inserez un entier");
				}
			}
			
		});
		NDC.setColumns(10);
		
	
		JLabel P = new JLabel("Nom");
		P.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 11));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblNumEmployee = new JLabel("Adresse");
		lblNumEmployee.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 11));
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblQuantite = new JLabel("Quantite");
		lblQuantite.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 11));
		
		textField_2 = new JTextField();
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					int i= Integer.parseInt(textField_2.getText());
					qu.setText("e");
				} catch (NumberFormatException e1){
					qu.setText("inserez un entier");
				}
			}
		});
		textField_2.setColumns(10);
		
		textField_5 = new JTextField();
		JLabel lblNumeroEmployee = new JLabel("Numero Voiture");
		lblNumeroEmployee.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 11));
		
		JButton btnNewButton = new JButton("Inserer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String NDCs,produit,dates,datereque,numemp,quantite,numeroemp,idf;
				
				
				NDCs = NDC.getText();
				produit = textField.getText();
				numemp = textField_1.getText();
				
				quantite = textField_2.getText();
				
				numeroemp = (String)comboBox.getSelectedItem();
				
				
						
			
			
				 try {
					pst = con.prepareStatement("insert into entrepot(Id_Entrepot ,Nom,Adresse,NoVoiture ,Quantite)values(?,?,?,?,?)");
					pst.setString(1, NDCs);
					pst.setString(2, produit);
					pst.setString(3, numemp);
					
					
					pst.setString(4, quantite);
					pst.setString(5, numeroemp);
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Donees inserer avec succes!!!!!");
					table_load();
						           
					NDC.setText("");
					textField.setText("");
					textField_1.setText("");
					NDC.requestFocus();
				   }
			 
				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
				}
			}
			
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Recherche", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnMaj = new JButton("MAJ");
		btnMaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String NDCs,produit,dates,datereque,numemp,quantite,numeroemp,idf;
				
				
				NDCs = textField_5.getText();
				produit = textField.getText();
				numemp = textField_1.getText();
				
				quantite = textField_2.getText();
				
				numeroemp = (String)comboBox.getSelectedItem();
				
				
				
				 try {
						pst = con.prepareStatement("update entrepot set  Nom=?, Adresse=?,NoVoiture =?,Quantite=? WHERE Id_Entrepot=?");
						
						pst.setString(1, produit);
						pst.setString(2, numemp);
						
						pst.setString(3, quantite);
						pst.setString(4, numeroemp);
						pst.setString(5, NDCs);
					
			            pst.executeUpdate();
			            JOptionPane.showMessageDialog(null, "MAJ Reussite!!!!!");
			            table_load();
			           
			            NDC.setText("");
						textField.setText("");
						textField_1.setText("");
						NDC.requestFocus();
					}

		            catch (SQLException e1) {
						
						e1.printStackTrace();
					}
			}
			
		});
		
		JButton btnEffacer_1 = new JButton("Enlever");
		btnEffacer_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid;
				
				bid  = textField_5.getText();
				
				 try {
						pst = con.prepareStatement("delete from commandes where No_cmde =?");
				
			            pst.setString(1, bid);
			            pst.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Effacer!!!!!");
			            table_load();
			           
			         	NDC.setText("");
		            	textField.setText("");
		            	textField_1.setText("");
		            	textField_2.setText("");
					}

		            catch (SQLException e1) {
						
		            	JOptionPane.showMessageDialog(null, e1);
					}
			}
		});
		
		JLabel show = new JLabel("");
		show.setForeground(Color.RED);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				entrepots.this.frmAdmin.setVisible(false);
				MenuAdm.main(login);
				
			}
		});
		
		
		
		
		
		
		
	
		
		
		GroupLayout groupLayout = new GroupLayout(frmAdmin.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(10)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(NDC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(P, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
												.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNumEmployee, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
												.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(show)
												.addComponent(show_validation_here))
											.addGap(34)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(btnNewButton)
												.addComponent(btnMaj, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnEffacer_1, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
											.addGap(49))
										.addGroup(groupLayout.createSequentialGroup()
											.addContainerGap()
											.addComponent(lblQuantite, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)))
									.addGroup(groupLayout.createSequentialGroup()
										.addContainerGap()
										.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(13)
										.addComponent(qu)
										.addGap(143)))
								.addGroup(groupLayout.createSequentialGroup()
									.addContainerGap()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(comboBox, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNumeroEmployee, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(34)
									.addComponent(lblFormulaire))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(10)
									.addComponent(lblNewLabel)))
							.addGap(59)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(185)
					.addComponent(lblGestionDesCommandes)
					.addPreferredGap(ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
					.addComponent(btnRetour, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(26))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(11, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGestionDesCommandes)
						.addComponent(btnRetour))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblFormulaire)
							.addGap(18)
							.addComponent(lblNewLabel))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 313, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(1)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(NDC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(show)
										.addComponent(show_validation_here))
									.addGap(8)
									.addComponent(P)
									.addGap(7)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(8)
									.addComponent(lblNumEmployee)
									.addGap(7)
									.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnNewButton)
									.addGap(18)
									.addComponent(btnMaj)
									.addGap(18)
									.addComponent(btnEffacer_1)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblQuantite)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(qu))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNumeroEmployee)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(39))
		);
		
		
		textField_5.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
			          
		            String id = textField_5.getText();

		                pst = con.prepareStatement("select * from entrepot where Id_Entrepot= ?");
		                pst.setString(1, id);
		                ResultSet rs = pst.executeQuery();

		            if(rs.next()==true)
		            {
		              
		                String NDC1 = rs.getString(1);
		                String produit1 = rs.getString(2);
		                String numemp1 = rs.getString(3);
		                String quan = rs.getString(5);
		             

		             
		               

		                
		                
						
						
					
						
						NDC.setText(NDC1);
						textField.setText(produit1);
						textField_1.setText(numemp1);
						
						textField_2.setText(quan);
						
						
		                
		                
		            }   
		            else
		            {
		            	NDC.setText("");
		            	textField.setText("");
		            	textField_1.setText("");
		            	textField_2.setText("");
		                 
		            }
		            


		        } 
			
			 catch (SQLException ex) {
		           
		        }
			}
		});
		panel.add(textField_5);
		textField_5.setColumns(10);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		frmAdmin.getContentPane().setLayout(groupLayout);
	}
}
