package pattients;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import diseasesAndVaccines.GetDiseaseListController;
import loginWorks.MainMenuForm;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;


public class AddNewPattientForm extends JFrame {

	private JPanel contentPane;
	private JTextField amkaVal;
	private JTextField phoneVal;
	private LocalDate date = LocalDate.now();
	private AddNewPattientController con = new AddNewPattientController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNewPattientForm frame = new AddNewPattientForm();
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
	public AddNewPattientForm() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 60, 505, 313);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		// AMKA //
		amkaVal = new JTextField();
		amkaVal.setFont(new Font("Arial", Font.PLAIN, 18));
		amkaVal.setHorizontalAlignment(SwingConstants.CENTER);
		// Check if user enters only integers for amka
		amkaVal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				try {
					if((arg0.getKeyCode() < 48 || arg0.getKeyCode() > 57) && arg0.getKeyCode() != 8 && arg0.getKeyCode() != 10 ) {
						JOptionPane.showMessageDialog(null,"Μή επιτρεπτός χαρακτήρας.","",JOptionPane.ERROR_MESSAGE);
						AddNewPattientForm.this.setVisible(false);
						AddNewPattientForm.main(null);
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
		
		amkaVal.setBounds(26, 84, 182, 30);
		contentPane.add(amkaVal);
		amkaVal.setColumns(10);
		
		JLabel amkaLbl = new JLabel("AMKA");
		amkaLbl.setFont(new Font("Arial", Font.BOLD, 10));
		amkaLbl.setBounds(26, 65, 86, 14);
		contentPane.add(amkaLbl);
		
		phoneVal = new JTextField();
		phoneVal.setHorizontalAlignment(SwingConstants.CENTER);
		phoneVal.setFont(new Font("Arial", Font.PLAIN, 18));
		phoneVal.setBounds(26, 162, 182, 30);
		contentPane.add(phoneVal);
		phoneVal.setColumns(10);
		
		// Phone Field //
		JLabel phoneLbl = new JLabel("Τηλέφωνο");
		phoneLbl.setFont(new Font("Arial", Font.BOLD, 10));
		phoneLbl.setBounds(26, 138, 133, 20);
		contentPane.add(phoneLbl);
		
		// Check if user enters only integers for phone
		phoneVal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				try {
					if((arg0.getKeyCode() < 48 || arg0.getKeyCode() > 57) && arg0.getKeyCode() != 8 && arg0.getKeyCode() != 10) {
						JOptionPane.showMessageDialog(null,"Μή επιτρεπτός χαρακτήρας.","",JOptionPane.ERROR_MESSAGE);
						AddNewPattientForm.this.setVisible(false);
						AddNewPattientForm.main(null);
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
		
		
		// Diseases List // 
		JComboBox diseaseListCombo = new JComboBox();
		diseaseListCombo.setFont(new Font("Arial", Font.PLAIN, 12));
		
		GetDiseaseListController disCon = new GetDiseaseListController();
		
		String [] a = new String[disCon.getDiseasesList().size()];
		disCon.getDiseasesList().toArray(a);
		diseaseListCombo.setModel(new DefaultComboBoxModel(a));//<-----All diseases List
		
		diseaseListCombo.setBounds(264, 127, 200, 30);
		diseaseListCombo.setSelectedItem(("Γενικός-Πληθυσμός"));
		contentPane.add(diseaseListCombo);
		
		
		JLabel diseaseListLbl = new JLabel("Νόσημα - Κατάσταση");
		diseaseListLbl.setHorizontalAlignment(SwingConstants.CENTER);
		diseaseListLbl.setFont(new Font("Arial", Font.BOLD, 10));
		diseaseListLbl.setBounds(264, 102, 200, 14);
		contentPane.add(diseaseListLbl);
		
		// check data and save all entered fields
		JButton saveAllButton = new JButton("Αποθήκευση καταχώρησης");
		saveAllButton.setFont(new Font("Arial", Font.PLAIN, 12));
		saveAllButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String ageString = null;
				int age = 0;
				String pattientsAge = null;
				try {
					if(con.checkIfAmkaExist(amkaVal.getText())){
						JOptionPane.showMessageDialog(null,"Αυτό το ΑΜΚΑ υπάρχει ήδη!!!","",JOptionPane.ERROR_MESSAGE);
						AddNewPattientForm.this.setVisible(false);
						AddNewPattientForm.main(null);
					}
					else{
						// get age from AMKA 
						int birthYearLast2Digits = Integer.parseInt(amkaVal.getText().substring(4, 6));
						String y = (date.getYear() + "").trim().substring(2);
						int nowYearsLast2Digits = Integer.parseInt(y);
						if(birthYearLast2Digits > nowYearsLast2Digits) { // if birthYear > nowYear then put 19..
							ageString = (birthYearLast2Digits + "").trim();
							ageString = 19 + ageString;
						}
						else {  // if birthYear <= nowYear then put 20...
							if(birthYearLast2Digits >= 0 && birthYearLast2Digits <= 9 ) { // if year is 2000 ... 2009 put 200 in front of StringYear 
								ageString = (birthYearLast2Digits + "").trim();
								ageString = 200 + ageString;
							}
							else { // else put 20 in front of stringYear
								  ageString = (birthYearLast2Digits + "").trim();
								  ageString = 20 + ageString;
							}
						}
					}
				
					age = date.getYear() - Integer.parseInt(ageString);
					pattientsAge = (age + "").trim();
					// get gender from AMKA
					String gender = null;
					int gend = Integer.parseInt(amkaVal.getText().substring(6, 10));
				
					if(gend % 2 == 0) {
						gender = "Γυναίκα";
					}
					else {gender = "Aνδρας";}
					
					con.createNewPattient(amkaVal.getText(), phoneVal.getText(), pattientsAge, // create new pattient in DB
							gender, diseaseListCombo.getSelectedItem().toString());
					con.addToDoVaccines(amkaVal.getText(), diseaseListCombo.getSelectedItem().toString(), pattientsAge); // add vaccinesToDo for this pattient
					AddNewPattientForm.this.setVisible(false);
					MainMenuForm.main(null);
				}catch(StringIndexOutOfBoundsException e) {
					AddNewPattientForm.this.setVisible(false);
					MainMenuForm.main(null);
				}
			}
		});
		
		
			
		saveAllButton.setBounds(26, 238, 200, 30);
		contentPane.add(saveAllButton);
		
		// Exit button //
		JButton cancelButton = new JButton("Aκύρωση - Επιστροφή");
		cancelButton.setFont(new Font("Arial", Font.PLAIN, 12));
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AddNewPattientForm.this.setVisible(false);
				MainMenuForm.main(null);
			}
		});
		cancelButton.setBounds(264, 238, 200, 30);
		contentPane.add(cancelButton);
		
		JLabel lblNewLabel = new JLabel("Προσθήκη νέου ασθενούς");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel.setBounds(264, 25, 205, 30);
		contentPane.add(lblNewLabel);
		
		
	}
}
