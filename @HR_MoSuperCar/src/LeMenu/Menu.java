package LeMenu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import CompteUser.InterfaceCompte;
import Departements.InterfaceDep;
import Employe.gererEmployes;

import javax.swing.border.CompoundBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu {

	private JFrame frame;
	private final JLayeredPane layeredPane = new JLayeredPane();
	
	
	public void bye() {
		
		Menu.this.frame.setVisible(false);
		
		
	}
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
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
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
		
		JButton btnGererEmploye = new JButton("G\u00E9rer Les Employ\u00E9es");
		btnGererEmploye.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			
				Menu.this.frame.setVisible(false);
				gererEmployes.main(null);
				
				
				
				
			}
		});
		btnGererEmploye.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnGererEmploye.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnGererEmploye.setBounds(10, 21, 601, 35);
		layeredPane.add(btnGererEmploye);
		
		JButton btnGererDepartement = new JButton("G\u00E9rer Les D\u00E9partements");
		btnGererDepartement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				Menu.this.frame.setVisible(false);
				InterfaceDep.main(null);
				
				
				
				
			}
		});
		btnGererDepartement.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnGererDepartement.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnGererDepartement.setBounds(10, 80, 601, 40);
		layeredPane.add(btnGererDepartement);
		
		JButton btnCompte = new JButton("G\u00E9rer Les Comptes Utilisateurs");
		btnCompte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Menu.this.frame.setVisible(false);
				InterfaceCompte.main(null);
				
				
				
				
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
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(587, 10, 118, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Menu");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 2, 48, 36);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
