import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import java.awt.Font;
import javax.swing.JPasswordField;


import java.sql.*;
import javax.swing.*;

public class IA {

	public JFrame frame;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IA window = new IA();
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
	public IA() {
		initialize();
		connection = sqliteConnection.dbConnector();
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
		
		JLabel lblLogIn = new JLabel("Log In");
		lblLogIn.setFont(new Font("Helvetica", Font.PLAIN, 22));
		lblLogIn.setForeground(Color.WHITE);
		lblLogIn.setBounds(335, 140, 100, 30);
		frame.getContentPane().add(lblLogIn);
		
		JLabel lblConfirmPassword = new JLabel("Password");
		lblConfirmPassword.setForeground(Color.WHITE);
		lblConfirmPassword.setBounds(337, 266, 143, 16);
		frame.getContentPane().add(lblConfirmPassword);
		
		JLabel label_2 = new JLabel("Username");
		label_2.setForeground(Color.WHITE);
		label_2.setBounds(337, 186, 90, 16);
		frame.getContentPane().add(label_2);
		
		txtUsername = new JTextField();
		txtUsername.setBackground(UIManager.getColor("Button.background"));
		txtUsername.setColumns(10);
		txtUsername.setBounds(335, 206, 305, 47);
		frame.getContentPane().add(txtUsername);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(335, 286, 305, 47);
		frame.getContentPane().add(txtPassword);
		
		JButton btnNewButton = new JButton("Log In");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String password = new String(txtPassword.getPassword());
				String username = txtUsername.getText();
				
				try {
					if (password.equals("")) {
						JOptionPane.showMessageDialog(null, "Please enter Password");
					}
					
					else if (username.equals("")) {
						JOptionPane.showMessageDialog(null,"Please enter Username");
					}
					
					String query = "select * from ResidentsInfo where username=? and password=? ";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, username); 
					pst.setString(2, password); 
					
					ResultSet rs = pst.executeQuery();
					
					int count = 0;
					
					while (rs.next()) {
						count++;
					}
					
					if (count == 1) {
						frame.dispose();
						Residents resident = new Residents();
						resident.frame.setVisible(true);
					}
					
					else if (count > 1) {
						JOptionPane.showMessageDialog(null, "Duplicate Username and Password");
					}
					
					else {
						JOptionPane.showMessageDialog(null, "Duplicate Username or Password");
					}
					rs.close();
					pst.close();
				}
				catch(Exception a) {	
					JOptionPane.showMessageDialog(null, a);	
				}
			}
			
		});
		
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setBounds(335, 387, 100, 40);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnForgotPassword = new JButton("Forgot Password?");
		btnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				Forgot.main(null);
			}
		});
		btnForgotPassword.setForeground(Color.WHITE);
		btnForgotPassword.setBackground(Color.BLACK);
		btnForgotPassword.setBounds(511, 345, 133, 40);
		frame.getContentPane().add(btnForgotPassword);
		
		JButton btnNotAUser = new JButton("Not a User? Sign up");
		btnNotAUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				SignUp.main(null);
			}
			
		});
		btnNotAUser.setForeground(Color.WHITE);
		btnNotAUser.setBackground(Color.BLACK);
		btnNotAUser.setBounds(511, 387, 133, 40);
		frame.getContentPane().add(btnNotAUser);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setBounds(283, 105, 409, 363);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(IA.class.getResource("/image/imageedit_1_8692064156.png")));
		lblNewLabel_1.setBounds(0, 0, 1000, 596);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
