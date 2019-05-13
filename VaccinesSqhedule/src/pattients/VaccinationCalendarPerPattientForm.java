package pattients;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import loginWorks.MainMenuForm;

import javax.swing.JTabbedPane;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.ScrollPane;
import java.awt.List;
import java.awt.Label;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.DropMode;
import java.awt.Point;
import java.awt.TextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

public class VaccinationCalendarPerPattientForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private static String amka = null;
	private VaccinationCalendarPerPattientContoller con = new VaccinationCalendarPerPattientContoller();
	private JTextField ageVal;
	private JTextField phoneVal;
	private JTextField genderVal;
	private LocalDate date = LocalDate.now();

	/**
	 * Launch the application.
	 */
	public static void main(String args) {
		amka = args;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VaccinationCalendarPerPattientForm frame = new VaccinationCalendarPerPattientForm();
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
	public VaccinationCalendarPerPattientForm() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 100, 513, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label label = new Label("Εικόνα εμβολιασμών");
		label.setAlignment(Label.CENTER);
		label.setFont(new Font("Arial", Font.BOLD, 15));
		label.setBounds(321, 10, 172, 22);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("AMKA");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel.setBounds(22, 10, 111, 22);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setText(amka);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Arial", Font.PLAIN, 18));
		textField.setBounds(22, 30, 172, 30);
		contentPane.add(textField);
		
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Ολοκληρωμένοι εμβολιασμοί");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_1.setBounds(22, 137, 172, 22);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Πάθηση - Κατάσταση");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_2.setBounds(304, 64, 149, 22);
		contentPane.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setText(con.getNowDisease(amka));
		textField_1.setHorizontalAlignment(SwingConstants.LEFT);
		textField_1.setFont(new Font("Arial", Font.PLAIN, 14));
		textField_1.setEditable(false);
		textField_1.setBounds(304, 84, 177, 30);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_1 = new JLabel("Μελλοντικοί εμβολιασμοί");
		label_1.setFont(new Font("Arial", Font.BOLD, 11));
		label_1.setBounds(275, 137, 172, 22);
		contentPane.add(label_1);
		
		// Εxit Button //
		JButton btnNewButton = new JButton("Επιστροφή");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				VaccinationCalendarPerPattientForm.this.setVisible(false);
				MainMenuForm.main(null);
				
			}
		});
		btnNewButton.setBounds(186, 382, 140, 39);
		contentPane.add(btnNewButton);
		
		// Done vaccines List //
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 156, 208, 201);
		contentPane.add(scrollPane);
		
		// List of VaccinesDone
		JList list = new JList();
		list.setFont(new Font("Arial", Font.PLAIN, 13));
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return con.ListOfVaccinesDone(amka).size();
			}
			public Object getElementAt(int index) {
				return con.ListOfVaccinesDone(amka).get(index);
			}
		});
		scrollPane.setViewportView(list);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(275, 158, 206, 199);
		contentPane.add(scrollPane_1);
		
		// List of vaccines ToDo
		JList list_1 = new JList();
		scrollPane_1.setViewportView(list_1);
		list_1.setFont(new Font("Arial", Font.PLAIN, 13));
		list_1.setModel(new AbstractListModel() {
			public int getSize() {
				return con.listOfToDoVaccines(amka).size();
			}
			public Object getElementAt(int index) {
				int indexOfSpace = con.listOfToDoVaccines(amka).get(index).indexOf(" ");
				String y = con.listOfToDoVaccines(amka).get(index).substring(indexOfSpace + 1);
				int year = Integer.parseInt(y);
				if(year == date.getYear()) { // red fontColor for vaccines that must done this year
					list_1.setForeground(Color.RED);
				}
				else list_1.setForeground(Color.BLACK);
				return con.listOfToDoVaccines(amka).get(index);
			}
		}); 
		
		
		ageVal = new JTextField();
		ageVal.setHorizontalAlignment(SwingConstants.CENTER);
		ageVal.setFont(new Font("Arial", Font.PLAIN, 14));
		ageVal.setEditable(false);
		ageVal.setBounds(22, 84, 47, 30);
		contentPane.add(ageVal);
		ageVal.setColumns(10);
		String age = (con.getPattient(amka).getAge() + "").trim();
		ageVal.setText(age);
		
		phoneVal = new JTextField();
		phoneVal.setHorizontalAlignment(SwingConstants.CENTER);
		phoneVal.setFont(new Font("Arial", Font.PLAIN, 14));
		phoneVal.setEditable(false);
		phoneVal.setBounds(159, 85, 104, 29);
		contentPane.add(phoneVal);
		phoneVal.setColumns(10);
		phoneVal.setText(con.getPattient(amka).getPhone());
		
		JLabel ageLbl = new JLabel("Ηλικία");
		ageLbl.setFont(new Font("Arial", Font.BOLD, 11));
		ageLbl.setBounds(22, 71, 46, 14);
		contentPane.add(ageLbl);
		
		JLabel phoneLbl = new JLabel("Τηλέφωνο");
		phoneLbl.setFont(new Font("Arial", Font.BOLD, 11));
		phoneLbl.setBounds(159, 71, 83, 14);
		contentPane.add(phoneLbl);
		
		genderVal = new JTextField();
		genderVal.setHorizontalAlignment(SwingConstants.CENTER);
		genderVal.setFont(new Font("Arial", Font.PLAIN, 14));
		genderVal.setEditable(false);
		genderVal.setBounds(79, 84, 70, 30);
		contentPane.add(genderVal);
		genderVal.setColumns(10);
		genderVal.setText(con.getPattient(amka).getGender());
		
		JLabel genderLbl = new JLabel("Φύλο");
		genderLbl.setFont(new Font("Arial", Font.BOLD, 11));
		genderLbl.setBounds(79, 71, 70, 14);
		contentPane.add(genderLbl);
		
		
		
	}
}
