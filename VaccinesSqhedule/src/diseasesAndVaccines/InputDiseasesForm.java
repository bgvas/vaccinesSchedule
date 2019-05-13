package diseasesAndVaccines;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import loginWorks.MainMenuForm;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InputDiseasesForm extends JFrame {

	private JPanel contentPane;
	private JTextField diseaseVal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InputDiseasesForm frame = new InputDiseasesForm();
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
	public InputDiseasesForm() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(550, 80, 397, 292);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		diseaseVal = new JTextField();
		diseaseVal.setHorizontalAlignment(SwingConstants.CENTER);
		diseaseVal.setFont(new Font("Arial", Font.PLAIN, 18));
		diseaseVal.setBounds(79, 84, 222, 30);
		contentPane.add(diseaseVal);
		diseaseVal.setColumns(10);
		
		JLabel diseaseLbl = new JLabel("Καταχώριση Νοσήματος - Κατάστασης");
		diseaseLbl.setHorizontalAlignment(SwingConstants.CENTER);
		diseaseLbl.setFont(new Font("Arial", Font.BOLD, 15));
		diseaseLbl.setBounds(26, 31, 326, 30);
		contentPane.add(diseaseLbl);
		
		JButton saveButton = new JButton("Καταχώριση");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InputDiseases inputD = new InputDiseases();
				if(!inputD.checkIfDiseaseExist(diseaseVal.getText())) {
					if(inputD.sendDiseasesToDB(diseaseVal.getText()) && (!diseaseVal.getText().equals(" "))) {
						JOptionPane.showMessageDialog(null, "H εγγραφή σας αποθηκεύτηκε.");
						InputDiseasesForm.this.setVisible(false);
						MainMenuForm.main(null);
					}
					else JOptionPane.showMessageDialog(null, "H εγγραφή δεν αποθηκεύτηκε!!!",null, JOptionPane.ERROR_MESSAGE);
				}
				else JOptionPane.showMessageDialog(null, "H εγγραφή αυτή υπάρχει ήδη!!!",null, JOptionPane.ERROR_MESSAGE);
				InputDiseasesForm.this.setVisible(false);
				InputDiseasesForm.main(null);
			}
		});
			
		saveButton.setFont(new Font("Arial", Font.PLAIN, 15));
		saveButton.setBounds(99, 158, 182, 30);
		contentPane.add(saveButton);
		
		JButton btnNewButton_1 = new JButton("Ακύρωση");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InputDiseasesForm.this.setVisible(false);
				MainMenuForm.main(null);
			}
		});
		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 14));
		btnNewButton_1.setBounds(99, 199, 182, 30);
		contentPane.add(btnNewButton_1);
	}
}
