package diseasesAndVaccines;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.AbstractListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataBaseWorks.RetrieveFromDB;
import loginWorks.MainMenuForm;
import pattients.GetAllPattientsToDoVaccinesController;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;
import java.awt.List;
import java.awt.Panel;
import java.time.LocalDate;
import java.awt.Color;
import java.awt.Label;
import javax.swing.JScrollPane;
import java.awt.Button;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListWithAllPattientsToDOVaccinesForm extends JFrame {

	private JPanel contentPane;
	private GetAllPattientsToDoVaccinesController con = new GetAllPattientsToDoVaccinesController();
	private JTextField dateVal;
	private LocalDate date = LocalDate.now();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListWithAllPattientsToDOVaccinesForm frame = new ListWithAllPattientsToDOVaccinesForm();
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
	public ListWithAllPattientsToDOVaccinesForm() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(580, 100, 342, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Αναγκαίοι εμβολιασμοί, τρέχοντος έτους");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 11, 306, 14);
		contentPane.add(lblNewLabel);
		
		Label amkaLbl = new Label("ΑΜΚΑ");
		amkaLbl.setAlignment(Label.CENTER);
		amkaLbl.setBounds(10, 63, 84, 22);
		contentPane.add(amkaLbl);
		
		Label vaccineLbl = new Label("Εμβόλιο");
		vaccineLbl.setAlignment(Label.CENTER);
		vaccineLbl.setBounds(128, 63, 171, 22);
		contentPane.add(vaccineLbl);
		RetrieveFromDB db = new RetrieveFromDB();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 91, 306, 220);
		contentPane.add(scrollPane);
		
		// Vaccines To Do List
		JList list = new JList();
		scrollPane.setViewportView(list);
		list.setAlignmentX(CENTER_ALIGNMENT);
		list.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		list.setFont(new Font("Arial", Font.PLAIN, 13));
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return con.getAllVaccinesForAllPattientsThisYear().size();
			}
			
			public Object getElementAt(int index) {
				int indexA = con.getAllVaccinesForAllPattientsThisYear().get(index).indexOf(12);
				String amka = con.getAllVaccinesForAllPattientsThisYear().get(index).substring(0, 12);
				String b = con.getAllVaccinesForAllPattientsThisYear().get(index).substring(12);
				String result =  amka + "               " + b + " ";
				return result;
			}
			
		});
		// return button
		Button button = new Button("Επιστροφή");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ListWithAllPattientsToDOVaccinesForm.this.setVisible(false);
				MainMenuForm.main(null);
			}
		});
		button.setBounds(99, 317, 124, 32);
		contentPane.add(button);
		
		dateVal = new JTextField();
		dateVal.setFont(new Font("Arial", Font.BOLD, 12));
		dateVal.setHorizontalAlignment(SwingConstants.CENTER);
		dateVal.setEditable(false);
		dateVal.setBounds(10, 36, 306, 20);
		dateVal.setText(date.getMonthValue() + "/" + date.getYear());
		contentPane.add(dateVal);
		dateVal.setColumns(10);
	}
}
