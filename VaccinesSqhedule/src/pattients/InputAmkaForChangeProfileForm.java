package pattients;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataBaseWorks.ClearUncompletedVaccinesController;
import dataBaseWorks.RetrieveFromDB;
import loginWorks.MainMenuForm;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class InputAmkaForChangeProfileForm extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField amkaVal;
	private RetrieveFromDB retrieve = new RetrieveFromDB();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InputAmkaForChangeProfileForm dialog = new InputAmkaForChangeProfileForm();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InputAmkaForChangeProfileForm() {
		setBounds(600, 250, 249, 134);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 233, 96);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		// Labels //
		JLabel amkaLbl = new JLabel("AMKA");
		amkaLbl.setHorizontalAlignment(SwingConstants.CENTER);
		amkaLbl.setFont(new Font("Arial", Font.BOLD, 11));
		amkaLbl.setBounds(20, 0, 187, 25);
		amkaLbl.setVisible(true);
		contentPanel.add(amkaLbl);
			
		JLabel errorMes = new JLabel("Λάθος ΑΜΚΑ.Προσπαθήστε Ξανά");
		errorMes.setForeground(Color.RED);
		errorMes.setHorizontalAlignment(SwingConstants.CENTER);
		errorMes.setBounds(20, 5, 187, 20);
		errorMes.setVisible(false);
		contentPanel.add(errorMes);
		// end of labels //
		
		
		// Amka TextField //
		amkaVal = new JTextField();
		amkaVal.setHorizontalAlignment(SwingConstants.CENTER);
		amkaVal.setFont(new Font("Arial", Font.BOLD, 15));
		amkaVal.setBounds(47, 24, 138, 20);
		contentPanel.add(amkaVal);
		amkaVal.setColumns(10);
		// End Of Amka TextField //
		
		
			// Buttons //
		JButton saveBtn = new JButton("Συνέχεια");
		saveBtn.setBounds(20, 55, 89, 23);
		contentPanel.add(saveBtn);
		// if pressed then check AMKA and if no errors open ChangePattienProfileForm
		saveBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(amkaVal.getText().length() < 11) {
					InputAmkaForChangeProfileForm.this.setVisible(false);
					amkaVal.setText("");
					amkaLbl.setVisible(false);
					errorMes.setVisible(true);
					InputAmkaForChangeProfileForm.this.setVisible(true);
				}
				
							
				String sql = "select * from pattients where amka = '" + amkaVal.getText() + "'";
				if(retrieve.checkIfAmkaExist(sql)) {
					InputAmkaForChangeProfileForm.this.setVisible(false);
					ChangePattientsProfileForm.main(amkaVal.getText());
				}
				else {
						InputAmkaForChangeProfileForm.this.setVisible(false);
						amkaVal.setText("");
						amkaLbl.setVisible(false);
						errorMes.setVisible(true);
						InputAmkaForChangeProfileForm.this.setVisible(true);
				}
			}
		});
						
		JButton cancelBtn = new JButton("Ακύρωση");
		cancelBtn.setBounds(118, 55, 89, 23);
		contentPanel.add(cancelBtn);
		// if pressed exit to menu //
		cancelBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				InputAmkaForChangeProfileForm.this.setVisible(false);
				MainMenuForm.main(null);
			}
		});
			// End Of Buttons //
		
		
		// Check if user enters only integers for phone
		amkaVal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				try {
					if((arg0.getKeyCode() < 48 || arg0.getKeyCode() > 57) && arg0.getKeyCode() != 8 && arg0.getKeyCode() != 10 ) {
						JOptionPane.showMessageDialog(null,"Μή επιτρεπτός χαρακτήρας.","",JOptionPane.ERROR_MESSAGE);
						InputAmkaForChangeProfileForm.this.setVisible(false);
						InputAmkaForChangeProfileForm.main(null);
					}     	
				}finally {}
			}
			// Let user to enter only 11 ints
			@Override
			public void keyTyped(KeyEvent e) {
				if(amkaVal.getText().length() == 11) {
					getToolkit().beep();
					e.consume();
				}
			}
					
		});
	}
}
