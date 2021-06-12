package CompteUser;



import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import CompteUser.MenuHr;


import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
/**
 * Cette classe est l'interface menu de l'administrateur
 * @author Lionel
 *
 */

public class MenuAdm {

	private JFrame frame;
	private final JLayeredPane layeredPane = new JLayeredPane();
	private final JLayeredPane layeredPane_1 = new JLayeredPane();
	private final JLayeredPane Employes = new JLayeredPane();
	/**
	 * Launch the application.
	 * @param login variable de type String qui stock le login de l'utilisateur connecté pour la session
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
	 * @param login variable de type String qui stock le login de l'utilisateur connecté pour la session

	 */
	public MenuAdm(String login) {
		initialize(login);
	}

	/**
	 * Initialise les contenus du  frame.
	 * @param login variable de type String qui stock le login de l'utilisateur connecté
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
		Employes.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		Employes.setBounds(105, 126, 499, 373);
		frame.getContentPane().add(Employes);
		
		JButton btnEmployes = new JButton("Employ\u00E9s");
		btnEmployes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MenuAdm.this.frame.setVisible(false);	
				
				InterfaceEmployes.main(login);
				
				
			}
		});
		btnEmployes.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEmployes.setBounds(25, 24, 442, 42);
		Employes.add(btnEmployes);
		
		JButton btnDeps = new JButton("D\u00E9partements");
		btnDeps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				MenuAdm.this.frame.setVisible(false);
				InterfaceDep.main(login);
				
				
			}
		});
		btnDeps.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDeps.setBounds(25, 135, 442, 42);
		Employes.add(btnDeps);
		
		JButton butComptes = new JButton("Comptes Utilisateurs");
		butComptes.setFont(new Font("Tahoma", Font.BOLD, 11));
		butComptes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MenuAdm.this.frame.setVisible(false);
				InterfaceCompte.main(login);
				
				
				
			}
		});
		butComptes.setBounds(25, 82, 442, 42);
		Employes.add(butComptes);
		
		JButton btnVente = new JButton("Menu Vente");
		btnVente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuAdm.this.frame.setVisible(false);
				MenuVente.main(login);
			}
		});
		btnVente.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnVente.setBounds(25, 188, 442, 42);
		Employes.add(btnVente);
		
		JButton btnMenuComptabilit = new JButton("Menu Comptabilit\u00E9");
		btnMenuComptabilit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMenuComptabilit.setBounds(25, 241, 442, 42);
		Employes.add(btnMenuComptabilit);
	}
}
