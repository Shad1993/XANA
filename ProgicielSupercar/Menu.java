package comptable;



import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Shad\\eclipse-workspace\\PPE3_Reshad_comptable\\images\\LogoComptabilite.png"));
		setTitle("Comptable");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 664, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JLabel lblBienvenueSurLapplication = new JLabel("Bienvenue sur l'application comptable");
		lblBienvenueSurLapplication.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 29));
		lblBienvenueSurLapplication.setForeground(Color.CYAN);
		lblBienvenueSurLapplication.setBounds(125, 37, 417, 19);
		
		JButton btnNewButton = new JButton("Paiement Fournisseur");
		btnNewButton.setBounds(173, 211, 135, 88);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fournisseur frame = new fournisseur();
				frame.frmAdmin.setVisible(true);
			}
		});
		
		JButton btnVentes = new JButton("Voitures");
		btnVentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				voitures frame = new voitures();
				frame.frmAdmin.setVisible(true);
			}
		});
		btnVentes.setBounds(395, 211, 135, 88);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Shad\\eclipse-workspace\\PPE3_Reshad_comptable\\images\\image-fiche-collaborateur-comptable-1.jpg"));
		lblNewLabel.setBounds(10, 11, 632, 415);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Shad\\eclipse-workspace\\PPE3_Reshad_comptable\\images\\LogoComptabiliteGIFt.gif"));
		lblNewLabel_1.setBounds(125, 11, 433, 252);
		
		
		JButton btnVentes_1 = new JButton("Vente");
		btnVentes_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vente frame = new vente();
				frame.frmAdmin.setVisible(true);
				 
	                
				
			}
		});
		btnVentes_1.setBounds(173, 310, 135, 88);
		
		contentPane.add(lblBienvenueSurLapplication);
		
		contentPane.add(btnNewButton);
		contentPane.add(btnVentes_1);
		contentPane.add(btnVentes);
		contentPane.add(lblNewLabel_1);
		contentPane.add(lblNewLabel);
		
		
		
		
		
		
		
	}
}
