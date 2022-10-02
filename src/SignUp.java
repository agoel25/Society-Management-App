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

public class SignUp {

	public JFrame frame;
	private JTextField UsernameField;
	private JTextField ResidenceField;
	private JPasswordField PasswordField;
	private JPasswordField ConfirmPasswordField;
	private JPasswordField CodeField;
	private JTextField textField_FirstName;
	private JTextField textField_LastName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp window = new SignUp();
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
	public SignUp() {
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
		
		JLabel SignUp = new JLabel("Sign Up");
		SignUp.setFont(new Font("Helvetica", Font.PLAIN, 22));
		SignUp.setForeground(Color.WHITE);
		SignUp.setBounds(343, 30, 100, 30);
		frame.getContentPane().add(SignUp);
		
		JLabel txtUsername = new JLabel("Username");
		txtUsername.setForeground(Color.WHITE);
		txtUsername.setBounds(343, 219, 90, 16);
		frame.getContentPane().add(txtUsername);
		
		UsernameField = new JTextField();
		UsernameField.setBounds(343, 238, 245, 40);
		frame.getContentPane().add(UsernameField);
		UsernameField.setColumns(10);
		
		JLabel lblEmail = new JLabel("Residence");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setBounds(343, 279, 143, 16);
		frame.getContentPane().add(lblEmail);
		
		ResidenceField = new JTextField();
		ResidenceField.setColumns(10);
		ResidenceField.setBounds(343, 298, 245, 40);
		frame.getContentPane().add(ResidenceField);

		
		JLabel txtPassword = new JLabel("Password");
		txtPassword.setForeground(Color.WHITE);
		txtPassword.setBounds(343, 339, 143, 16);
		frame.getContentPane().add(txtPassword);
		
		PasswordField = new JPasswordField();
		PasswordField.setBounds(343, 358, 245, 40);
		frame.getContentPane().add(PasswordField);
		
		JLabel txtConfirmPassword = new JLabel("Confirm Password");
		txtConfirmPassword.setForeground(Color.WHITE);
		txtConfirmPassword.setBounds(343, 399, 143, 16);
		frame.getContentPane().add(txtConfirmPassword);
		
		ConfirmPasswordField = new JPasswordField();
		ConfirmPasswordField.setBounds(343, 418, 245, 40);
		frame.getContentPane().add(ConfirmPasswordField);
		
		JButton btnNewButton = new JButton("Sign Up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String code = new String(CodeField.getPassword());
				String pass = new String(PasswordField.getPassword());
				String conPass = new String(ConfirmPasswordField.getPassword());
				
				if (code.equals("2506")) {

					//if (PasswordField.getPassword().toString() == ConfirmPasswordField.getPassword().toString()) {
					
					if (pass.equals(conPass)) {
						
						try {
							String query = "insert into ResidentsInfo (FirstName,LastName,Username,Password,HouseNumber) values (?,?,?,?,?)";
							PreparedStatement pst = connection.prepareStatement(query);
							
							pst.setString(1, textField_FirstName.getText());
							pst.setString(2, textField_LastName.getText());
							pst.setString(3, UsernameField.getText());
							pst.setString(4, new String(PasswordField.getPassword()));
							pst.setString(5, ResidenceField.getText());

							pst.execute();
								
							pst.close();
							
						}
						
						catch(Exception a) {
							
							a.printStackTrace();
							
						}
						
						
						frame.dispose();
						IA window = new IA();
						window.frame.setVisible(true);
						
					}
					
					else {
						JOptionPane.showMessageDialog(null, "Passwords didn't match!", "Login Error", JOptionPane.ERROR_MESSAGE);
						CodeField.setText(null);
						PasswordField.setText(null);
						ConfirmPasswordField.setText(null);
					}
					
				}
				
				else {
					JOptionPane.showMessageDialog(null, "Invalid Authorization Code", "Login Error", JOptionPane.ERROR_MESSAGE);
					CodeField.setText(null);
				}
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setBounds(343, 527, 125, 40);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNotAUser = new JButton("Exit");
		btnNotAUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame Frame = new JFrame("Exit");
				if (JOptionPane.showConfirmDialog( Frame,"Confirm if you want to EXIT","Palm Manager",
			            JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
			            System.exit(0);
			}
			
		});
		btnNotAUser.setForeground(Color.WHITE);
		btnNotAUser.setBackground(Color.BLACK);
		btnNotAUser.setBounds(472, 527, 116, 40);
		frame.getContentPane().add(btnNotAUser);
		
		JRadioButton SelectManagement = new JRadioButton("Management");
		SelectManagement.setForeground(Color.WHITE);
		SelectManagement.setBounds(447, 470, 116, 23);
		frame.getContentPane().add(SelectManagement);
		
		CodeField = new JPasswordField();
		CodeField.setBounds(510, 492, 78, 26);
		frame.getContentPane().add(CodeField);
		
		JLabel txtCode = new JLabel("Code:");
		txtCode.setForeground(UIManager.getColor("Button.highlight"));
		txtCode.setBounds(475, 497, 36, 16);
		frame.getContentPane().add(txtCode);
		
		JLabel txtUsername_Name = new JLabel("First Name");
		txtUsername_Name.setForeground(Color.WHITE);
		txtUsername_Name.setBounds(343, 72, 90, 29);
		frame.getContentPane().add(txtUsername_Name);
		
		textField_FirstName = new JTextField();
		textField_FirstName.setColumns(10);
		textField_FirstName.setBounds(343, 98, 245, 40);
		frame.getContentPane().add(textField_FirstName);

		
		textField_LastName = new JTextField();
		textField_LastName.setColumns(10);
		textField_LastName.setBounds(343, 167, 245, 40);
		frame.getContentPane().add(textField_LastName);
		
		
		JLabel txtUsername_Name_1 = new JLabel("Last Name");
		txtUsername_Name_1.setForeground(Color.WHITE);
		txtUsername_Name_1.setBounds(343, 141, 90, 29);
		frame.getContentPane().add(txtUsername_Name_1);
		
		JLabel txtImage = new JLabel("");
		txtImage.setIcon(new ImageIcon(IA.class.getResource("/image/imageedit_1_8692064156.png")));
		txtImage.setBounds(-11, -12, 1000, 596);
		frame.getContentPane().add(txtImage);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setBounds(268, 16, 387, 568);
		frame.getContentPane().add(btnNewButton_1);
	}
}
