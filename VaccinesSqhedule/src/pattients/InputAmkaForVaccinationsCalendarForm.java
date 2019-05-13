package pattients;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dataBaseWorks.ClearUncompletedVaccinesController;
import dataBaseWorks.RetrieveFromDB;
import loginWorks.MainMenuForm;

public class InputAmkaForVaccinationsCalendarForm extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JTextField amkaVal;
	private VaccinationCalendarPerPattientContoller con = new VaccinationCalendarPerPattientContoller();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InputAmkaForVaccinationsCalendarForm frame = new InputAmkaForVaccinationsCalendarForm();
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
	public InputAmkaForVaccinationsCalendarForm() {
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
		// if pressed then check AMKA and if no errors open VaccinationCalendarPerPattientForm
		saveBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(amkaVal.getText().length() < 11) {
					InputAmkaForVaccinationsCalendarForm.this.setVisible(false);
					amkaVal.setText("");
					amkaLbl.setVisible(false);
					errorMes.setVisible(true);
					InputAmkaForVaccinationsCalendarForm.this.setVisible(true);
				}
				
				ClearUncompletedVaccinesController delUnVac = new ClearUncompletedVaccinesController();
				
				// checκ if amka exist
				if(con.checkIfAmkaExist(amkaVal.getText())) {
					delUnVac.clearUncompletedVaccines(amkaVal.getText()); // delete in vaccinationToDo old uncompleted vaccines
					InputAmkaForVaccinationsCalendarForm.this.setVisible(false);
					VaccinationCalendarPerPattientForm.main(amkaVal.getText());
				}
				else {
					InputAmkaForVaccinationsCalendarForm.this.setVisible(false);
						amkaVal.setText("");
						amkaLbl.setVisible(false);
						errorMes.setVisible(true);
						InputAmkaForVaccinationsCalendarForm.this.setVisible(true);
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
				InputAmkaForVaccinationsCalendarForm.this.setVisible(false);
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
						InputAmkaForVaccinationsCalendarForm.this.setVisible(false);
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
