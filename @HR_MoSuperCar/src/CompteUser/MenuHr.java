package CompteUser;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.example.model.Employe;

import CompteUser.InterfaceCompte;
import CompteUser.Login;
import Employe.gererEmployes;

import javax.swing.border.CompoundBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class MenuHr {

	private JFrame frame;
	private final JLayeredPane layeredPane = new JLayeredPane();
	private JButton btnRetourAdm;
	
	
	public void bye() {
		
		btnRetourAdm.setVisible(true);
		
		
	}
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String login) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuHr window = new MenuHr(login);
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
	public MenuHr(String login) {
		initialize(login);
		//btnRetourAdm.setVisible(false);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param login 
	 * 
	 * 
	 * 
	 */
	
    
   
	
	
	
	
	private void initialize(String login) {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(240, 255, 255));
		frame.getContentPane().setForeground(new Color(255, 248, 220));
		frame.setBackground(new Color(255, 69, 0));
		frame.setBounds(100, 100, 731, 476);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		layeredPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		layeredPane.setBounds(39, 204, 621, 201);
		frame.getContentPane().add(layeredPane);
		
		
         CompteAdmin A = new CompteAdmin();
 		try {
			A.DatabaseConnexionHR(login, null, null, frame);
			
			if (A.getTypeCompte().contains("Administrateur")){
	        	 
	        	//btnRetourAdm.setVisible(false);
				//JFrame frame = new JFrame("retour");
				//JOptionPane.showMessageDialog(frame,"Compte Utilisateur ajouté!");
				 btnRetourAdm = new JButton("Retour");
					btnRetourAdm.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							MenuHr.this.frame.setVisible(false);
							MenuAdm.main(login);
							
							
							
						}
					});
					btnRetourAdm.setBounds(292, 67, 118, 29);
					frame.getContentPane().add(btnRetourAdm);
	        	 
	        }
			
			
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

         
        // if (A.typeCompte.contains("Administrateur")) {
        	 
        	 //btnRetourAdm.setVisible(false);
        	 
        	 
        // }
		
		
		
		
		
		
		
		JButton btnGererEmploye = new JButton("G\u00E9rer Les Employ\u00E9es");
		btnGererEmploye.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			
				MenuHr.this.frame.setVisible(false);
				InterfaceEmployes.main(login);
				
				
				
				
			}
		});
		btnGererEmploye.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnGererEmploye.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnGererEmploye.setBounds(10, 21, 601, 35);
		layeredPane.add(btnGererEmploye);
		
		JButton btnGererDepartement = new JButton("G\u00E9rer Les D\u00E9partements");
		btnGererDepartement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				MenuHr.this.frame.setVisible(false);
				InterfaceDep.main(login);
				
				
				
				
			}
		});
		btnGererDepartement.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnGererDepartement.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnGererDepartement.setBounds(10, 80, 601, 40);
		layeredPane.add(btnGererDepartement);
		
		JButton btnCompte = new JButton("G\u00E9rer Les Comptes Utilisateurs");
		btnCompte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MenuHr.this.frame.setVisible(false);
				InterfaceCompte.main(login);
				
				
				
				
			}
		});
		btnCompte.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnCompte.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCompte.setBounds(10, 140, 601, 35);
		layeredPane.add(btnCompte);
		
		JLabel lblNewLabel = new JLabel("D\u00E9partement Des Resources Humaines");
		lblNewLabel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(165, 10, 362, 36);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("D\u00E9connexion");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// message de confirmation de sortir du programme
				 int x = JOptionPane.showConfirmDialog(null,"voulez-vous vraiment vous déconnecter??","fermer",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
				if(x == JOptionPane.YES_OPTION) { // Si choix "oui"
					
				 System.exit(0); // sortir du programme
				}else {
					
					System.out.println(""); // reste dans le programme
					
				}
				
				
				
			}
			
			
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(587, 10, 118, 29);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Menu");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 2, 48, 36);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
