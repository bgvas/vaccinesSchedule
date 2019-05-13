package loginWorks;

/**
 * @author Vasileios Georgoulas
 */

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import dataBaseWorks.ChangePassWordController;
import dataBaseWorks.DataBaseCreation;

import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.NoSuchFileException;

import javax.swing.SwingConstants;

public class LoginForm extends JFrame {

	private JPanel contentPane;
	private JTextField userVal;
	private JPasswordField pasVal;
	private LoginController con = new LoginController();
	private ChangePassWordController changePass = new ChangePassWordController();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataBaseCreation.dataBaseCreation();
					LoginForm frame = new LoginForm();
					frame.setVisible(true);
				} catch (Exception e) {
					System.out.println("opened allready");
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginForm() {
		setTitle("Eßóïäïò ÅöáñìïãÞò");
		setBackground(Color.LIGHT_GRAY);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(550, 100, 321, 268);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		loginPanel.setBounds(10, 11, 295, 218);
		contentPane.add(loginPanel);
		loginPanel.setLayout(null);
		
		userVal = new JTextField();
		userVal.setFont(new Font("Arial", Font.PLAIN, 18));
		userVal.setBounds(70, 48, 154, 30);
		loginPanel.add(userVal);
		userVal.setColumns(10);
		
		pasVal = new JPasswordField();
		pasVal.setFont(new Font("Arial", Font.PLAIN, 18));
		pasVal.setColumns(10);
		pasVal.setBounds(70, 113, 154, 30);
		loginPanel.add(pasVal);
		
		
				
		JButton btnNewButton = new JButton("Åßóïäïò");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					
					if(con.checkUserAuth(con.toHex(userVal.getText()), con.toHex(pasVal.getText()))){
						
						LoginForm.this.setVisible(false);
						MainMenuForm.main(null);
					}
					else {
						JOptionPane.showMessageDialog(null,"Ôá óôïé÷åßá äåí åßíáé óùóôÜ!!!","",JOptionPane.ERROR_MESSAGE);
						userVal.setText("");
						pasVal.setText("");
					}
					
				} catch (Exception e) {
					
				}
			}
		});
		btnNewButton.setBounds(70, 154, 154, 30);
		loginPanel.add(btnNewButton);
		JLabel lblNewLabel = new JLabel("Ïíïìá ×ñÞóôç");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel.setBounds(70, 22, 127, 25);
		loginPanel.add(lblNewLabel);
		
		JLabel label = new JLabel("Êùäéêüò ×ñÞóôç");
		label.setFont(new Font("Arial", Font.BOLD, 15));
		label.setBounds(70, 89, 127, 25);
		loginPanel.add(label);
		
		
		// If forgot password 
		textField = new JTextField();
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String cryptUser = null;
				String user;
				try {
					user = (JOptionPane.showInputDialog("Äþóôå ôï üíïìá ÷ñÞóôç")).trim();
					cryptUser = con.toHex(user);
				} catch (Exception e) {
					LoginForm.main(null);
				}
				if(changePass.checkIfUserExist(cryptUser)){		// check if user exist
					try {
						String pas1 = JOptionPane.showInputDialog("Äþóôå ôï íÝï PassWord");
						if(!pas1.equals("")) {						// check if first password is not null
							String pas2 = JOptionPane.showInputDialog("Äþóôå îáíÜ ôï íÝï PassWord");
							if(pas1.equals(pas2)) {	// check if pass1 is the same with pass2
								try {
									if(changePass.changePassWord(cryptUser, con.toHex(pas2))) { // send password for DB saving
										JOptionPane.showMessageDialog(null,"H áëëáãÞ ôïõ êùäéêïý Ýãéíå êáíïíéêÜ");
										LoginForm.this.setVisible(false);
										LoginForm.main(null);
									}
								} catch (Exception e) {
									LoginForm.main(null);
								} 
							}
						}
					}catch(NullPointerException e) {
						LoginForm.main(null);
					}
				} else {
					LoginForm.this.setVisible(false);
					LoginForm.main(null);
				}
			}
		});
		textField.setForeground(Color.BLUE);
		textField.setEditable(false);
		textField.setFont(new Font("Arial", Font.PLAIN, 10));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(10, 187, 275, 20);
		textField.setText("E÷ù îå÷Üóåé ôïí êùäéêü");
		loginPanel.add(textField);
		textField.setColumns(10);
	}
}
