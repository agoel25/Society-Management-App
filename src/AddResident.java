import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;

public class AddResident {

	public JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddResident window = new AddResident();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null; 
	private JTextField textField_6;
	/**
	 * Create the application.
	 */
	public AddResident() {
		connection = sqliteConnection.dbConnector();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(300, 300, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox<String> comboBox_Position = new JComboBox<String>();
		comboBox_Position.setBounds(195, 380, 170, 27);
		frame.getContentPane().add(comboBox_Position);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(198, 272, 170, 40);
		frame.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(198, 220, 170, 40);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(198, 163, 170, 40);
		frame.getContentPane().add(textField_2);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(198, 58, 170, 40);
		frame.getContentPane().add(textField_4);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(198, 111, 170, 40);
		frame.getContentPane().add(textField_3);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(198, 6, 170, 40);
		frame.getContentPane().add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(200, 328, 170, 40);
		frame.getContentPane().add(textField_6);
		
		JButton btnNewButton = new JButton("Done");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {

					String query = "insert into ResidentsInfo (FirstName,LastName,Username,HouseNumber,ResidentID,Email,Phone,Position) values (?,?,?,?,?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.setString(1, textField.getText());
					pst.setString(2, textField_1.getText());
					pst.setString(3, textField_2.getText());
					pst.setString(4, textField_3.getText());
					pst.setString(5, textField_4.getText());
					pst.setString(6, textField_5.getText());
					pst.setString(7, textField_6.getText());
					pst.setString(8, comboBox_Position.getSelectedItem().toString());
					
					pst.execute();
					
					frame.dispose();
					AllResidents py = new AllResidents();
					py.frame.setVisible(true);
				}
				catch(Exception fe) {
					fe.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(217, 426, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblPosition = new JLabel("Position");
		lblPosition.setForeground(Color.BLACK);
		lblPosition.setBounds(77, 384, 106, 16);
		frame.getContentPane().add(lblPosition);
		
		JLabel lblPhone = new JLabel("Phone Number");
		lblPhone.setForeground(Color.BLACK);
		lblPhone.setBounds(77, 331, 106, 16);
		frame.getContentPane().add(lblPhone);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setBounds(77, 279, 61, 16);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblID = new JLabel("Resident ID");
		lblID.setForeground(Color.BLACK);
		lblID.setBounds(77, 224, 106, 16);
		frame.getContentPane().add(lblID);
		
		JLabel lblHouse = new JLabel("House Number");
		lblHouse.setForeground(Color.BLACK);
		lblHouse.setBounds(77, 170, 94, 16);
		frame.getContentPane().add(lblHouse);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.BLACK);
		lblUsername.setBounds(77, 117, 106, 16);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblLast = new JLabel("Last Name");
		lblLast.setForeground(Color.BLACK);
		lblLast.setBounds(77, 64, 106, 16);
		frame.getContentPane().add(lblLast);
		
		JLabel lblFirst = new JLabel("First Name");
		lblFirst.setForeground(Color.BLACK);
		lblFirst.setBounds(77, 16, 106, 16);
		frame.getContentPane().add(lblFirst);
		
	}
}
