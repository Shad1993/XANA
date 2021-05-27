package CompteUser;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import CompteUser.MenuHr;
import Employe.gererEmployes;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.EtchedBorder;

public class MenuAdm {

	private JFrame frame;
	private final JLayeredPane layeredPane = new JLayeredPane();
	private final JLayeredPane layeredPane_1 = new JLayeredPane();
	private final JLayeredPane Employés = new JLayeredPane();
	/**
	 * Launch the application.
	 */
	public static void main(String login) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuAdm window = new MenuAdm(login);
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
	public MenuAdm(String login) {
		initialize(login);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param login 
	 */
	private void initialize(String login) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 99, 71));
		frame.setBackground(new Color(255, 99, 71));
		frame.setBounds(100, 100, 713, 549);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAdmin = new JLabel("Menu Admin");
		lblAdmin.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdmin.setBounds(210, 24, 296, 40);
		frame.getContentPane().add(lblAdmin);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnQuitter.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// message de confirmation de sortir du programme
				 int x = JOptionPane.showConfirmDialog(null,"voulez-vous vraiment quitter ??","fermer",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
				if(x == JOptionPane.YES_OPTION) { // Si choix "oui"
					
				 System.exit(0); // sortir du programme
				}else {
					
					System.out.println(""); // reste dans le programme
					
				}
				
			}
		});
		btnQuitter.setBounds(581, 11, 106, 34);
		frame.getContentPane().add(btnQuitter);
		Employés.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		Employés.setBounds(105, 126, 499, 373);
		frame.getContentPane().add(Employés);
		
		JButton btnEmployes = new JButton("Employ\u00E9s");
		btnEmployes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MenuAdm.this.frame.setVisible(false);	
				
				InterfaceEmployes.main(login);
				
				
			}
		});
		btnEmployes.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEmployes.setBounds(25, 24, 442, 42);
		Employés.add(btnEmployes);
		
		JButton btnDeps = new JButton("D\u00E9partements");
		btnDeps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				MenuAdm.this.frame.setVisible(false);
				InterfaceDep.main(login);
				
				
			}
		});
		btnDeps.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDeps.setBounds(25, 135, 442, 42);
		Employés.add(btnDeps);
		
		JButton butComptes = new JButton("Comptes Utilisateurs");
		butComptes.setFont(new Font("Tahoma", Font.BOLD, 11));
		butComptes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MenuAdm.this.frame.setVisible(false);
				InterfaceCompte.main(login);
				
				
				
			}
		});
		butComptes.setBounds(25, 82, 442, 42);
		Employés.add(butComptes);
	}
}
