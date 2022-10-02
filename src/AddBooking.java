import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import net.proteanit.sql.DbUtils;

import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

public class AddBooking {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBooking window = new AddBooking();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null; 
	private JTextField textField_2;
	private JTextField textField_1;
	private JTextField textField;
	/**
	 * Create the application.
	 */
	public AddBooking() {
		connection = sqliteConnection.dbConnector();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setBounds(100, 100, 340, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("Add Booking");
		lblTitle.setBounds(122, 18, 131, 16);
		frame.getContentPane().add(lblTitle);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnCancel.setBounds(175, 379, 117, 37);
		frame.getContentPane().add(btnCancel);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(145, 329, 170, 27);
		frame.getContentPane().add(comboBox);
		
		JLabel lblStatus = new JLabel("Booking");
		lblStatus.setForeground(Color.BLACK);
		lblStatus.setBounds(25, 329, 106, 16);
		frame.getContentPane().add(lblStatus);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(145, 279, 170, 27);
		frame.getContentPane().add(dateChooser);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setForeground(Color.BLACK);
		lblDate.setBounds(25, 290, 106, 16);
		frame.getContentPane().add(lblDate);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(145, 174, 170, 40);
		frame.getContentPane().add(textField_2);
		
		JLabel lblResident = new JLabel("House");
		lblResident.setForeground(Color.BLACK);
		lblResident.setBounds(25, 134, 106, 16);
		frame.getContentPane().add(lblResident);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(145, 122, 170, 40);
		frame.getContentPane().add(textField_1);
		
		JLabel lblHouse = new JLabel("Time");
		lblHouse.setForeground(Color.BLACK);
		lblHouse.setBounds(25, 186, 106, 16);
		frame.getContentPane().add(lblHouse);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(145, 73, 170, 40);
		frame.getContentPane().add(textField);
		
		JLabel lblID = new JLabel("Resident");
		lblID.setForeground(Color.BLACK);
		lblID.setBounds(25, 85, 106, 16);
		frame.getContentPane().add(lblID);
		
		
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
				    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					Date date = dateChooser.getDate();
					String query = "insert into Alerts (House,Resident,Time,Date,Booking) values (?,?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.setString(1, textField.getText());
					pst.setString(2, textField_1.getText());
					pst.setString(3, textField_2.getText());
					pst.setString(4, formatter.format(date));
					pst.setString(4, comboBox.getSelectedItem().toString());
					
					pst.execute();
					
					frame.dispose();
					Alerts py = new Alerts();
					py.frame.setVisible(true);
				}
				
				catch(Exception a) {
					
					a.printStackTrace();
					
				}
				
			}
		});
		btnDone.setBounds(50, 379, 117, 37);
		frame.getContentPane().add(btnDone);
				
	}
}
