package diseasesAndVaccines;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import loginWorks.MainMenuForm;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddVaccinationDoneForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private static String amka;
	
	/**
	 * Launch the application.
	 */
	public static void main(String  args) {
		amka = args;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddVaccinationDoneForm frame = new AddVaccinationDoneForm();
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
	public AddVaccinationDoneForm() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 100, 401, 287);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Καταχώρηση ολοκληρωμένου εμβολιασμού");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 365, 30);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText(amka);
		textField.setFont(new Font("Arial", Font.PLAIN, 18));
		textField.setEditable(false);
		textField.setBounds(106, 77, 185, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("ΑΜΚΑ");
		lblNewLabel_1.setBounds(106, 52, 46, 14);
		contentPane.add(lblNewLabel_1);
		VaccinationsDoneController con = new VaccinationsDoneController();
		String [] b = new String [con.vaccinesToDo(amka).size()];
		con.vaccinesToDo(amka).toArray(b);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(b));
		comboBox.setFont(new Font("Arial", Font.PLAIN, 13));
		comboBox.setBounds(106, 139, 185, 30);
		
		contentPane.add(comboBox);
		
		JLabel vacLbl = new JLabel("Εμβόλιο");
		vacLbl.setBounds(106, 118, 46, 14);
		contentPane.add(vacLbl);
		
		JButton btnNewButton = new JButton("Αποθήκευση");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					if(comboBox.getSelectedIndex() == -1) {
						AddVaccinationDoneForm.this.setVisible(false);
						MainMenuForm.main(null);
					}
					if(con.vaccinesDone(amka, comboBox.getSelectedItem().toString())) {
						JOptionPane.showMessageDialog(null,"Η εγγραφή αποθηκεύτηκε κανονικά");
						AddVaccinationDoneForm.this.setVisible(false);
						MainMenuForm.main(null);
					}
					else {
						JOptionPane.showMessageDialog(null,"H εγγραφή δεν αποθηκεύτηκε κανονικά!!!","",JOptionPane.ERROR_MESSAGE);
						AddVaccinationDoneForm.this.setVisible(false);
						MainMenuForm.main(null);
					}
				}catch(NullPointerException e) {
					
				}
			}
		});
		btnNewButton.setBounds(79, 189, 107, 30);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("Ακύρωση");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AddVaccinationDoneForm.this.setVisible(false);
				MainMenuForm.main(null);
			}
		});
		button.setBounds(206, 189, 107, 30);
		contentPane.add(button);
	}
}
