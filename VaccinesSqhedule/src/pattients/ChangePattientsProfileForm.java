package pattients;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import loginWorks.MainMenuForm;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;

public class ChangePattientsProfileForm extends JFrame {

	private JPanel contentPane;
	private JTextField amkaVal;
	private JTextField phoneVal;
	private JTextField ageVal;
	private JLabel ageLbl;
	private JTextField nowDisVal;
	private static String amka;
	private ChangePattientsProfileController con = new ChangePattientsProfileController();
	private String nowDisease;
	private JTextField genderVal;
	
	/**
	 * Launch the application.
	 */
	public static void main(String args) {
		amka = args;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePattientsProfileForm frame = new ChangePattientsProfileForm();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	/**
	 * Create the frame.
	 */
	public ChangePattientsProfileForm() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 60, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		// Amka field
		amkaVal = new JTextField();
		amkaVal.setText(amka);
		amkaVal.setFont(new Font("Arial", Font.BOLD, 20));
		amkaVal.setHorizontalAlignment(SwingConstants.CENTER);
		amkaVal.setEditable(false);
		amkaVal.setBounds(37, 75, 167, 36);
		contentPane.add(amkaVal);
		amkaVal.setColumns(10);
		
		JLabel amkaLbl = new JLabel("AMKA");
		amkaLbl.setFont(new Font("Arial", Font.BOLD, 10));
		amkaLbl.setBounds(37, 50, 58, 27);
		contentPane.add(amkaLbl);
		// End Of Amka Field// 
		
		
		// phone text field
		phoneVal = new JTextField();
		phoneVal.setText(con.getAllPatientsFromDB(amka).get(0).getPhone());
		phoneVal.setHorizontalAlignment(SwingConstants.CENTER);
		phoneVal.setFont(new Font("Arial", Font.PLAIN, 18));
		phoneVal.setBounds(37, 161, 138, 27);
		contentPane.add(phoneVal);
		phoneVal.setColumns(10);
		// Check if user enters only integers
		phoneVal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				try {
					if((arg0.getKeyCode() < 48 || arg0.getKeyCode() > 57) && arg0.getKeyCode() != 8 && arg0.getKeyCode() != 10  ) {
						JOptionPane.showMessageDialog(null,"Μή επιτρεπτός χαρακτήρας.","",JOptionPane.ERROR_MESSAGE);
					}     	
				}finally {}
			}
			// Let user to enter only 10 ints
			@Override
			public void keyTyped(KeyEvent e) {
				if(phoneVal.getText().length() == 10) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		JLabel phoneLbl = new JLabel("Τηλέφωνο");
		phoneLbl.setFont(new Font("Arial", Font.BOLD, 10));
		phoneLbl.setBounds(37, 136, 89, 27);
		contentPane.add(phoneLbl);
		// end of Phone TextField
				
		// age textField //
		ageVal = new JTextField();
		ageVal.setHorizontalAlignment(SwingConstants.CENTER);
		ageVal.setText(con.getAllPatientsFromDB(amka).get(0).getAge() + "");
		ageVal.setFont(new Font("Arial", Font.PLAIN, 18));
		ageVal.setColumns(10);
		ageVal.setBounds(37, 221, 39, 27);
		ageVal.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(ageVal);
		
		// Check if user enters only integers
		ageVal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				try {
					if((arg0.getKeyCode() < 48 || arg0.getKeyCode() > 57) && arg0.getKeyCode() != 8 && arg0.getKeyCode() != 10 ) {
						JOptionPane.showMessageDialog(null,"Μή επιτρεπτός χαρακτήρας.","",JOptionPane.ERROR_MESSAGE);
					}     	
				}finally {}
			}
			// Let user to enter only 2 ints
			@Override
			public void keyTyped(KeyEvent e) {
				if(ageVal.getText().length() == 2) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		ageLbl = new JLabel("Ηλικία");
		ageLbl.setFont(new Font("Arial", Font.BOLD, 10));
		ageLbl.setBounds(37, 199, 58, 27);
		contentPane.add(ageLbl);
		// end of age Textfield
		
		
		// Change Disease
		JComboBox disListval = new JComboBox();
		JLabel changeDisLbl = new JLabel("Αλλαγή κατάστασης - πάθησης");
		changeDisLbl.setFont(new Font("Arial", Font.BOLD, 10));
		changeDisLbl.setHorizontalAlignment(SwingConstants.CENTER);
		changeDisLbl.setBounds(268, 165, 161, 14);
		disListval.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(changeDisLbl);
		String [] a = new String[con.allDiseasesList().size()];
		con.allDiseasesList().toArray(a);
		disListval.setFont(new Font("Arial", Font.PLAIN, 12));
		disListval.setModel(new DefaultComboBoxModel(a)); // diseasesList 
		disListval.setBounds(268, 182, 161, 20);
		contentPane.add(disListval);
		// end of Change disease
		
		// now disease textField
		nowDisVal = new JTextField();
		nowDisVal.setFont(new Font("Arial", Font.PLAIN, 12));
		nowDisVal.setHorizontalAlignment(SwingConstants.CENTER);
		nowDisease = con.getNowDisease(amka);
		disListval.setSelectedItem(nowDisease);
		nowDisVal.setText(nowDisease);
		nowDisVal.setEditable(false);
		nowDisVal.setBounds(268, 121, 161, 27);
		contentPane.add(nowDisVal);
		nowDisVal.setColumns(10);
		JLabel disLbl = new JLabel("Παρούσα κατάσταση - πάθηση");
		disLbl.setFont(new Font("Arial", Font.BOLD, 10));
		disLbl.setHorizontalAlignment(SwingConstants.CENTER);
		disLbl.setBounds(262, 103, 167, 14);
		contentPane.add(disLbl);
		
		
		// Buttons
		JButton cancelButton = new JButton("Ακύρωση - Επιστροφή");
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ChangePattientsProfileForm.this.setVisible(false);
				MainMenuForm.main(null);
			}
		});
		cancelButton.setBounds(268, 288, 161, 30);
		contentPane.add(cancelButton);
		
		
		JButton saveButton = new JButton("Αποθήκευση αλλαγών");
		saveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!ageVal.getText().equals("")) {
					if(con.updatePattientsElements(
							amka, 
							phoneVal.getText(), 
							ageVal.getText(), 
							disListval.getSelectedItem().toString(), nowDisease
							)) {
									ChangePattientsProfileForm.this.setVisible(false);
									MainMenuForm.main(null);
					}
				}
				else {
					JOptionPane.showMessageDialog(null,"H ηλικία, δεν μπορεί να είναι κενή.","",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		saveButton.setBounds(268, 233, 161, 30);
		contentPane.add(saveButton);
		
		JButton delButton = new JButton("Διαγραφή ασθενούς");
		delButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String [] options = {"Διαγραφή", "Ακύρωση"};
				int del = 
						JOptionPane.showOptionDialog(null, "Θέλετε σίγουρα να διαγραφεί;","",JOptionPane.YES_NO_OPTION, 
								JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
				if(del == 0 && con.deletePattient(amka)) {
					JOptionPane.showMessageDialog(null,"O ασθενής, διεγράφει");
				}
				else JOptionPane.showMessageDialog(null,"H διαγραφή δεν έγινε κανονικά.","",JOptionPane.ERROR_MESSAGE);
				ChangePattientsProfileForm.this.setVisible(false);
				MainMenuForm.main(null);
			}
		});
		
		delButton.setForeground(Color.RED);
		delButton.setFont(new Font("Arial", Font.BOLD, 10));
		delButton.setBounds(37, 288, 138, 30);
		contentPane.add(delButton);
		
		JLabel lblNewLabel = new JLabel("Τροποποίηση Στοιχείων");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel.setBounds(259, 11, 185, 27);
		contentPane.add(lblNewLabel);
		
		genderVal = new JTextField();
		genderVal.setHorizontalAlignment(SwingConstants.CENTER);
		genderVal.setFont(new Font("Arial", Font.PLAIN, 18));
		genderVal.setEditable(false);
		genderVal.setBounds(118, 221, 86, 27);
		contentPane.add(genderVal);
		genderVal.setText(con.getAllPatientsFromDB(amka).get(0).getGender());
		genderVal.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Φύλο");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 10));
		lblNewLabel_1.setBounds(118, 205, 46, 14);
		contentPane.add(lblNewLabel_1);
		// end of buttons
		
	}
}
