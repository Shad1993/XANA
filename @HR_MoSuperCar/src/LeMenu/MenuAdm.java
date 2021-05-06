package LeMenu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import CompteUser.MenuHr;
import Employe.gererEmployes;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MenuAdm {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuAdm window = new MenuAdm();
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
	public MenuAdm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
		
	
		
		
		
		
		
		JButton btnHR = new JButton("Menu Des Ressources Humaines");
		btnHR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MenuAdm.this.frame.setVisible(false);
				MenuHr.main(null);
				
				
			}
		});
		btnHR.setBounds(105, 104, 499, 40);
		frame.getContentPane().add(btnHR);
		
		JButton btnMenuComptable = new JButton("Menu Comptable");
		btnMenuComptable.setBounds(105, 242, 499, 40);
		frame.getContentPane().add(btnMenuComptable);
		
		JButton btnMenuVente = new JButton("Menu Vente");
		btnMenuVente.setBounds(105, 309, 499, 40);
		frame.getContentPane().add(btnMenuVente);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(581, 11, 106, 34);
		frame.getContentPane().add(btnNewButton);
	}
}
