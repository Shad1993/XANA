package Admin;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

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

public class commandes {

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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					commandes window = new commandes();
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
	public commandes() {
		initialize();
		Connect();
		table_load();
		loademp();
		loadfour();
		
	}

	Connection con;
	java.sql.PreparedStatement pst;
	ResultSet rs;
	Statement st;
	JComboBox comboBox = new JComboBox();
	
	JComboBox comboBox_1 = new JComboBox();
    public void loademp()
    {
   
    try{
      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppe3_supercar", "btsmc0_reshado", "yOQDkp@?7G}A");
      st = con.createStatement();
      String s = "select * from employes";
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
    
    public void loadfour()
    {
   
    try{
      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppe3_supercar", "btsmc0_reshado", "yOQDkp@?7G}A");
      st = con.createStatement();
      String s = "select * from fournisseur";
      rs = st.executeQuery(s);
        while(rs.next())
        {
        	comboBox_1.addItem(rs.getString(1));
        }
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, "ERREURR");
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
	            Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppe3_supercar", "btsmc0_reshado", "yOQDkp@?7G}A");
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
		    pst = con.prepareStatement("select * from commandes");
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
	 */
	private void initialize() {
		frmAdmin = new JFrame();
		frmAdmin.setTitle("Admin");
		frmAdmin.setBounds(100, 100, 561, 500);
		frmAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblGestionDesCommandes = new JLabel("Gestion des commandes");
		
		JLabel lblFormulaire = new JLabel("Formulaire");
		
		JLabel lblNewLabel = new JLabel("Numero de commande");
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
		
	
		JLabel P = new JLabel("Produit");
		P.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 11));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblNumEmployee = new JLabel("Num employee");
		lblNumEmployee.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 11));
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		
		
		
		
		JLabel lblNumDate = new JLabel(" Date");
		lblNumDate.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 11));
		
		JDateChooser dateChooser = new JDateChooser();
		
		
		
		JLabel lblDateRequise = new JLabel(" Date Requise");
		lblDateRequise.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 11));
		
		JDateChooser dateChooser_1 = new JDateChooser();
		
		JLabel lblQuantite = new JLabel("Quantite");
		lblQuantite.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 11));
		
		textField_2 = new JTextField();
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					int i= Integer.parseInt(textField_2.getText());
					qu.setText("");
				} catch (NumberFormatException e1){
					qu.setText("inserez un entier");
				}
			}
		});
		textField_2.setColumns(10);
		
		textField_5 = new JTextField();
		JLabel lblNumeroEmployee = new JLabel("Numero employee");
		lblNumeroEmployee.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 11));
		
		JLabel lblIdFournisseur = new JLabel("ID Fournisseur");
		lblIdFournisseur.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 11));
		
		JButton btnNewButton = new JButton("Inserer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String NDCs,produit,dates,datereque,numemp,quantite,numeroemp,idf;
				
				
				NDCs = NDC.getText();
				produit = textField.getText();
				numemp = textField_1.getText();
				dates  = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
				
				datereque = ((JTextField)dateChooser_1.getDateEditor().getUiComponent()).getText();
				quantite = textField_2.getText();
				
				numeroemp = (String)comboBox.getSelectedItem();
				
				idf  = (String)comboBox_1.getSelectedItem();
						
			
			
				 try {
					pst = con.prepareStatement("insert into commandes(No_cmde,Produit,NoEmploye,date_cmde,date_requise,Quantite,No_employe,IdFournisseur)values(?,?,?,?,?,?,?,?)");
					pst.setString(1, NDCs);
					pst.setString(2, produit);
					pst.setString(3, numemp);
					try {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

						String date = sdf.format(dateChooser.getDate());

						pst.setString(4,date);;
					} catch (Exception e1) {
					    System.out.println(e1);
					};
					
					try {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

						String date1 = sdf.format(dateChooser_1.getDate());

						pst.setString(5,date1);;
					} catch (Exception e1) {
					    System.out.println(e1);
					}
					
					pst.setString(6, quantite);
					pst.setString(7, numeroemp);
					pst.setString(8, idf);
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
									
					JOptionPane.showMessageDialog(null, e1);
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
				dates  = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
				
				datereque = ((JTextField)dateChooser_1.getDateEditor().getUiComponent()).getText();
				quantite = textField_2.getText();
				
				numeroemp = (String)comboBox.getSelectedItem();
				
				idf  = (String)comboBox_1.getSelectedItem();
				
				 try {
						pst = con.prepareStatement("update commandes set Produit=?,NoEmploye=?,date_cmde=?,date_requise=?,Quantite=?,No_employe=?,IdFournisseur=? where No_cmde =?");
						
						pst.setString(1, produit);
						pst.setString(2, numemp);
						try {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

							String date = sdf.format(dateChooser.getDate());

							pst.setString(3,date);;
						} catch (Exception e1) {
						    System.out.println(e1);
						}
						try {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

							String date1 = sdf.format(dateChooser_1.getDate());

							pst.setString(4,date1);;
						} catch (Exception e1) {
						    System.out.println(e1);
						}
						;
						pst.setString(5, quantite);
						pst.setString(6, numeroemp);
						pst.setString(7, idf);
						pst.setString(8, NDCs);
			            pst.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Record Update!!!!!");
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
						
						e1.printStackTrace();
					}
			}
		});
		
		JLabel show = new JLabel("");
		show.setForeground(Color.RED);
		
		
		
		
		
	
		
		
		GroupLayout groupLayout = new GroupLayout(frmAdmin.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(185)
							.addComponent(lblGestionDesCommandes))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblNumeroEmployee, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupLayout.createSequentialGroup()
											.addContainerGap()
											.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(10)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(NDC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(P, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
												.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNumEmployee, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
												.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNumDate, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
												.addComponent(lblDateRequise, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
												.addComponent(lblQuantite, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
												.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblIdFournisseur, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)))
										.addGroup(groupLayout.createSequentialGroup()
											.addContainerGap()
											.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addContainerGap()
											.addComponent(dateChooser_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(show)
												.addComponent(show_validation_here))
											.addGap(34)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(btnNewButton)
												.addComponent(btnMaj, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnEffacer_1, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)))
										.addComponent(qu))))
							.addGap(49)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(34)
									.addComponent(lblFormulaire))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(10)
									.addComponent(lblNewLabel)))
							.addGap(59)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(11, Short.MAX_VALUE)
					.addComponent(lblGestionDesCommandes)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblFormulaire)
							.addGap(18)
							.addComponent(lblNewLabel))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
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
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(8)
							.addComponent(lblNumDate)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblDateRequise)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dateChooser_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblQuantite)
							.addGap(6)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(qu))
							.addGap(7)
							.addComponent(lblNumeroEmployee)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblIdFournisseur))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 313, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNewButton)
							.addGap(18)
							.addComponent(btnMaj)
							.addGap(18)
							.addComponent(btnEffacer_1)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		
		textField_5.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
			          
		            String id = textField_5.getText();

		                pst = con.prepareStatement("select * from commandes where No_cmde = ?");
		                pst.setString(1, id);
		                ResultSet rs = pst.executeQuery();

		            if(rs.next()==true)
		            {
		              
		                String NDC1 = rs.getString(1);
		                String produit1 = rs.getString(2);
		                String numemp1 = rs.getString(3);
		                String quan = rs.getString(6);
		             

		             
		               

		                
		            	
		                String dateValue = rs.getString(4); // What ever column
		                java.util.Date date11;
						try {
							date11 = new SimpleDateFormat("dd-MM-yyyy").parse(dateValue);
							dateChooser.setDate(date11);
						} catch (java.text.ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						String dateValue2 = rs.getString(5); // What ever column
		                java.util.Date date2;
						try {
							date2 = new SimpleDateFormat("dd-MM-yyyy").parse(dateValue2);
							dateChooser_1.setDate(date2);
						} catch (java.text.ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

		                
						
						
					
						
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
