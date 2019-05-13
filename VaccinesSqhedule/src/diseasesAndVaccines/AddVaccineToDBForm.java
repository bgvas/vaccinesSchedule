package diseasesAndVaccines;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import loginWorks.MainMenuForm;
import pattients.AddNewPattientForm;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddVaccineToDBForm extends JFrame {

	private JPanel contentPane;
	private JTextField vaccineVal;
	private JTextField freqVal;
	private JTextField ageVal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddVaccineToDBForm frame = new AddVaccineToDBForm();
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
	public AddVaccineToDBForm() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 100, 450, 234);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// vaccine textField
		vaccineVal = new JTextField();
		vaccineVal.setFont(new Font("Arial", Font.PLAIN, 15));
		vaccineVal.setHorizontalAlignment(SwingConstants.CENTER);
		vaccineVal.setBounds(29, 52, 195, 30);
		contentPane.add(vaccineVal);
		vaccineVal.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Εμβόλιο");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel.setBounds(29, 27, 110, 23);
		contentPane.add(lblNewLabel);
		// end of vaccine Textfield
		
		
		
		// diseases list
		JComboBox diseaseBox = new JComboBox();
		
		
		AddVaccineController con = new AddVaccineController();
		String[] a = new String [con.getDiseaseList().size()];
		con.getDiseaseList().toArray(a);
		diseaseBox.setModel(new DefaultComboBoxModel(a));
		diseaseBox.setSelectedItem("Γενικός Πληθυσμός");
		diseaseBox.setFont(new Font("Arial", Font.PLAIN, 10));
		diseaseBox.setBounds(29, 116, 195, 30);
		
		contentPane.add(diseaseBox);
		
		JLabel lblNewLabel_1 = new JLabel("Νόσημα - Κατάσταση");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(29, 93, 146, 23);
		contentPane.add(lblNewLabel_1);
		
		freqVal = new JTextField();
		freqVal.setFont(new Font("Arial", Font.PLAIN, 15));
		freqVal.setHorizontalAlignment(SwingConstants.CENTER);
		freqVal.setBounds(343, 77, 34, 30);
		contentPane.add(freqVal);
		freqVal.setColumns(10);
		// Check if user enters only integers for age
		freqVal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				try {
					if((arg0.getKeyCode() < 48 || arg0.getKeyCode() > 57) && arg0.getKeyCode() != 8 && arg0.getKeyCode() != 10) {
						JOptionPane.showMessageDialog(null,"Μή επιτρεπτός χαρακτήρας.","",JOptionPane.ERROR_MESSAGE);
						AddVaccineToDBForm.this.setVisible(false);
						AddVaccineToDBForm.main(null);
					}     	
				}finally {}
			}
			// Let user to enter only 2 ints
			@Override
			public void keyTyped(KeyEvent e) {
				if(freqVal.getText().length() == 2) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("Συχνότητα");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(251, 77, 90, 31);
		contentPane.add(lblNewLabel_2);
		
		// save button //
		JButton saveBtn = new JButton("Αποθήκευση");
		saveBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int age = Integer.parseInt(ageVal.getText());
				try {
					if(!vaccineVal.getText().equals("")) {
						if(con.saveVaccine(vaccineVal.getText(), diseaseBox.getSelectedItem().toString(), freqVal.getText(), age)) {
							JOptionPane.showMessageDialog(null,"H εγγραφή αποθηκεύτηκε κανονικά");
							//AddVaccineToDBForm.this.setVisible(false);
							//MainMenuForm.main(null);
						}
						else {
							JOptionPane.showMessageDialog(null,"Η εγγραφή δεν αποθηκεύτηκε!!!.","",JOptionPane.ERROR_MESSAGE);
							AddVaccineToDBForm.this.setVisible(false);
							MainMenuForm.main(null);
						}
					}
					else JOptionPane.showMessageDialog(null,"Συπληρώσετε όλα τα στοιχεία!!!.","",JOptionPane.ERROR_MESSAGE);
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null,"Συπληρώσετε όλα τα στοιχεία!!!.","",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		saveBtn.setBounds(242, 114, 171, 30);
		contentPane.add(saveBtn);
		// end of save button //
		
		
		// Cancel Button //
		JButton cancelBtn = new JButton("Ακύρωση");
		cancelBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AddVaccineToDBForm.this.setVisible(false);
				MainMenuForm.main(null);
			}
		});
		cancelBtn.setBounds(241, 151, 172, 30);
		contentPane.add(cancelBtn);
		
		JLabel lblNewLabel_3 = new JLabel("Καταχώρηση Εμβολίου");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_3.setBounds(242, 11, 182, 30);
		contentPane.add(lblNewLabel_3);
		
		ageVal = new JTextField();
		ageVal.setFont(new Font("Arial", Font.PLAIN, 10));
		ageVal.setBounds(29, 175, 39, 20);
		contentPane.add(ageVal);
		ageVal.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Ηλικία");
		lblNewLabel_4.setBounds(29, 157, 46, 14);
		contentPane.add(lblNewLabel_4);
		// end of cancel Button //
	}
}
