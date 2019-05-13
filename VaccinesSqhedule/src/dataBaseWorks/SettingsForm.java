package dataBaseWorks;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import diseasesAndVaccines.AddVaccineToDBForm;
import diseasesAndVaccines.InputDiseasesForm;
import loginWorks.MainMenuForm;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SettingsForm extends JFrame {

	private JPanel contentPane;
	private ResetDBService db = new ResetDBService();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String [] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SettingsForm frame = new SettingsForm();
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
	public SettingsForm() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 140, 291, 254);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Ρυθμίσεις");
		label.setFont(new Font("Arial", Font.BOLD, 15));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 11, 266, 30);
		contentPane.add(label);
		
		JButton resetDBBtn = new JButton("Καθαρισμός Βάσης Δεδομένων");
		resetDBBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String [] options = {"Εκκαθάριση", "Ακύρωση"};
				int del = 
						JOptionPane.showOptionDialog(null, "Θέλετε σίγουρα να διαγραφoύν όλα;","",JOptionPane.YES_NO_OPTION, 
								JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
				if(del == 0) {
					if(db.resetDB()) {
								JOptionPane.showMessageDialog(null,"Ολα τα στοιχεία της βάσης, έχουν διεγράφει");
					} else JOptionPane.showMessageDialog(null,"H εκκαθάριση, δεν έγινε κανονικά.","",JOptionPane.ERROR_MESSAGE);
				}
				SettingsForm.this.setVisible(false);
				MainMenuForm.main(null);
			}
		});
		resetDBBtn.setBounds(35, 52, 212, 30);
		contentPane.add(resetDBBtn);
		
		JButton exitBtn = new JButton("Επιστροφή");
		exitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				SettingsForm.this.setVisible(false);
				MainMenuForm.main(null);
			}
		});
		exitBtn.setBounds(35, 175, 212, 30);
		contentPane.add(exitBtn);
		
		JButton btnNewButton = new JButton("Καταχώρηση νέας κατάστασης");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					String pas = JOptionPane.showInputDialog("Δώστε το PassWord");
					while(!pas.equals("Sofitel04")) {
						JOptionPane.showMessageDialog(null,"Λάθος password.","",JOptionPane.ERROR_MESSAGE);
						pas = JOptionPane.showInputDialog("Δώστε το PassWord");
					}
					SettingsForm.this.setVisible(false);
					InputDiseasesForm.main(null);
				}catch(NullPointerException e) {
					SettingsForm.this.setVisible(false);
					MainMenuForm.main(null);
				}
			}
		});
		btnNewButton.setBounds(35, 93, 212, 30);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Καταχώρηση νέου εμβολίου");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String pas = JOptionPane.showInputDialog("Δώστε το PassWord");
					while(!pas.equals("Sofitel04")) {
						JOptionPane.showMessageDialog(null,"Λάθος password.","",JOptionPane.ERROR_MESSAGE);
						pas = JOptionPane.showInputDialog("Δώστε το PassWord");
					}
					SettingsForm.this.setVisible(false);
					AddVaccineToDBForm.main(null);
				}catch(NullPointerException x) {
					SettingsForm.this.setVisible(false);
					MainMenuForm.main(null);
				}
			}
		});
		btnNewButton_1.setBounds(35, 134, 212, 30);
		contentPane.add(btnNewButton_1);
	}
}
