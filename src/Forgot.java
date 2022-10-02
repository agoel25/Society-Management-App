import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.JTable;

public class Forgot {

	public JFrame frame;
	private JTextField UsernameField;
	private JPasswordField PasswordField;
	private JPasswordField ConfirmPasswordField;
	private JTextField textField_FirstName;
	private JTextField textField_LastName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Forgot window = new Forgot();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null; 
	/**
	 * Create the application.
	 */
	public Forgot() {
		connection = sqliteConnection.dbConnector();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 3, 22));
		frame.setBackground(Color.BLUE);
		frame.setBounds(100, 100, 974, 618);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel SignUp = new JLabel("Forgot Password");
		SignUp.setFont(new Font("Helvetica", Font.PLAIN, 22));
		SignUp.setForeground(Color.WHITE);
		SignUp.setBounds(298, 30, 225, 30);
		frame.getContentPane().add(SignUp);
		
		JLabel txtUsername = new JLabel("3. What is your lucky number");
		txtUsername.setForeground(Color.WHITE);
		txtUsername.setBounds(298, 219, 279, 16);
		frame.getContentPane().add(txtUsername);
		
		UsernameField = new JTextField();
		UsernameField.setBounds(308, 238, 313, 40);
		frame.getContentPane().add(UsernameField);
		UsernameField.setColumns(10);
		
		textField_FirstName = new JTextField();
		textField_FirstName.setColumns(10);
		textField_FirstName.setBounds(308, 98, 313, 40);
		frame.getContentPane().add(textField_FirstName);

		
		textField_LastName = new JTextField();
		textField_LastName.setColumns(10);
		textField_LastName.setBounds(308, 163, 313, 40);
		frame.getContentPane().add(textField_LastName);

		
		JLabel txtPassword = new JLabel("Password");
		txtPassword.setForeground(Color.WHITE);
		txtPassword.setBounds(298, 338, 143, 16);
		frame.getContentPane().add(txtPassword);
		
		PasswordField = new JPasswordField();
		PasswordField.setBounds(308, 358, 313, 40);
		frame.getContentPane().add(PasswordField);
		
		JLabel txtConfirmPassword = new JLabel("Confirm Password");
		txtConfirmPassword.setForeground(Color.WHITE);
		txtConfirmPassword.setBounds(298, 399, 143, 16);
		frame.getContentPane().add(txtConfirmPassword);
		
		ConfirmPasswordField = new JPasswordField();
		ConfirmPasswordField.setBounds(308, 418, 313, 40);
		frame.getContentPane().add(ConfirmPasswordField);
		
		
		JLabel txtPassword_1 = new JLabel("After answering all three questions, please ");
		txtPassword_1.setForeground(Color.WHITE);
		txtPassword_1.setBounds(298, 290, 323, 16);
		frame.getContentPane().add(txtPassword_1);
		
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pass = new String(PasswordField.getPassword());
				String conPass = new String(ConfirmPasswordField.getPassword());
				
				if (textField_FirstName.getText().equals("admin") && textField_LastName.getText().equals("admin") && UsernameField.getText().equals("1")) {
					
					if (pass.equals(conPass)) {
						
						/*try {
							String query = "insert into ResidentsInfo (FirstName,LastName,Username,Password,HouseNumber) values (?,?,?,?,?)";
							PreparedStatement pst = connection.prepareStatement(query);
							
							pst.setString(4, pass);
							
							pst.execute();
							
						}
						
						catch(Exception a) {
							
							a.printStackTrace();
							
						}*/
						
						frame.dispose();
						IA window = new IA();
						window.frame.setVisible(true);
						
					}
					
					else {
						JOptionPane.showMessageDialog(null, "Passwords didn't match!", "Login Error", JOptionPane.ERROR_MESSAGE);
						PasswordField.setText(null);
						ConfirmPasswordField.setText(null);
					}
					
				}
				
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setBounds(345, 470, 125, 40);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNotAUser = new JButton("Cancel");
		btnNotAUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				IA.main(null);
				frame.dispose();

			}
			
		});
		btnNotAUser.setForeground(Color.WHITE);
		btnNotAUser.setBackground(Color.BLACK);
		btnNotAUser.setBounds(474, 470, 116, 40);
		frame.getContentPane().add(btnNotAUser);
		
		JLabel txtUsername_Name = new JLabel("1. What is your pet name?");
		txtUsername_Name.setForeground(Color.WHITE);
		txtUsername_Name.setBounds(298, 72, 245, 29);
		frame.getContentPane().add(txtUsername_Name);
		
		
		JLabel txtUsername_Name_1 = new JLabel("2. What is your maternal grandmother's first name?");
		txtUsername_Name_1.setForeground(Color.WHITE);
		txtUsername_Name_1.setBounds(298, 138, 357, 29);
		frame.getContentPane().add(txtUsername_Name_1);
		
		JLabel txtImage = new JLabel("");
		txtImage.setIcon(new ImageIcon(IA.class.getResource("/image/imageedit_1_8692064156.png")));
		txtImage.setBounds(-11, -12, 1000, 596);
		frame.getContentPane().add(txtImage);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setBounds(268, 16, 387, 525);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel txtPassword_1_1 = new JLabel("input your new password and confirm it:");
		txtPassword_1_1.setForeground(Color.WHITE);
		txtPassword_1_1.setBounds(298, 310, 323, 16);
		frame.getContentPane().add(txtPassword_1_1);
	}
}
