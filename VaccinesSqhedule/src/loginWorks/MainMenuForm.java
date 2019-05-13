package loginWorks;



import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataBaseWorks.DataBaseCreation;
import dataBaseWorks.SettingsForm;
import diseasesAndVaccines.InputAmkaForAddVacinationForm;
import diseasesAndVaccines.ListWithAllPattientsToDOVaccinesForm;
import pattients.AddNewPattientForm;
import pattients.InputAmkaForChangeProfileForm;
import pattients.InputAmkaForVaccinationsCalendarForm;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemTray;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenuForm extends JFrame {

	private JPanel contentPane;
	private static String user = null;

	/**
	 * Launch the application.
	 */
	public static void main(String [] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenuForm frame = new MainMenuForm();
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
	public MainMenuForm() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 635, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Κεντρικό μενού επιλογών");
		label.setFont(new Font("Arial", Font.BOLD, 15));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 11, 582, 30);
		contentPane.add(label);
		
		JButton addNewPattientBut = new JButton("Καταχώρηση νέου ασθενούς");
		addNewPattientBut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				MainMenuForm.this.setVisible(false);
				AddNewPattientForm.main(null);
			}
		});
		addNewPattientBut.setFont(new Font("Arial", Font.PLAIN, 12));
		addNewPattientBut.setBounds(30, 52, 275, 50);
		contentPane.add(addNewPattientBut);
		
		JButton changeProfileBut = new JButton("Αλλαγή στοιχείων ασθενούς");
		changeProfileBut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		changeProfileBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainMenuForm.this.setVisible(false);
				InputAmkaForChangeProfileForm.main(null);
			}
		});
		changeProfileBut.setFont(new Font("Arial", Font.PLAIN, 12));
		changeProfileBut.setBounds(315, 52, 277, 50);
		contentPane.add(changeProfileBut);
		
		JButton addVaccineBut = new JButton("Καταχώρηση ολοκληρωμένου εμβολιασμού");
		addVaccineBut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainMenuForm.this.setVisible(false);
				InputAmkaForAddVacinationForm.main(null);
			}
		});
		addVaccineBut.setFont(new Font("Arial", Font.PLAIN, 12));
		addVaccineBut.setBounds(30, 113, 275, 50);
		contentPane.add(addVaccineBut);
		
		JButton vaccinesCalendarBut = new JButton("Ημερολόγιο εμβολιασμών");
		vaccinesCalendarBut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainMenuForm.this.setVisible(false);
				InputAmkaForVaccinationsCalendarForm.main(null);
			}
		});
		vaccinesCalendarBut.setFont(new Font("Arial", Font.PLAIN, 12));
		vaccinesCalendarBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		vaccinesCalendarBut.setBounds(315, 113, 277, 50);
		contentPane.add(vaccinesCalendarBut);
		
		JButton vaccinesDoneBut = new JButton("Λίστα εμβολιασμών όλων των ασθενών");
		vaccinesDoneBut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainMenuForm.this.setVisible(false);
				ListWithAllPattientsToDOVaccinesForm.main(null);
			}
		});
		vaccinesDoneBut.setFont(new Font("Arial", Font.PLAIN, 12));
		vaccinesDoneBut.setBounds(30, 174, 275, 50);
		contentPane.add(vaccinesDoneBut);
		
		JButton settingsBut = new JButton("Ρυθμίσεις");
		settingsBut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainMenuForm.this.setVisible(false);
				SettingsForm.main(null);
			}
		});
		settingsBut.setFont(new Font("Arial", Font.PLAIN, 12));
		settingsBut.setBounds(315, 174, 277, 50);
		contentPane.add(settingsBut);
		
		// exit button
		JButton exit = new JButton("Εξοδος");
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		exit.setFont(new Font("Arial", Font.PLAIN, 12));
		exit.setBounds(490, 255, 102, 30);
		contentPane.add(exit);
	}
}
