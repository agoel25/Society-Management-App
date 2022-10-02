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
import com.toedter.calendar.JDateChooser;

public class AddComplaint {

	public JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddComplaint window = new AddComplaint();
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
	public AddComplaint() {
		connection = sqliteConnection.dbConnector();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 340, 475);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("Add Complaint");
		lblTitle.setBounds(122, 18, 131, 16);
		frame.getContentPane().add(lblTitle);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setBounds(33, 75, 61, 16);
		frame.getContentPane().add(lblTime);
		
		JLabel lblNewLabel_1_1 = new JLabel("Complaint");
		lblNewLabel_1_1.setBounds(33, 123, 82, 16);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Allocated to");
		lblNewLabel_1_1_1.setBounds(33, 176, 106, 16);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("House Number");
		lblNewLabel_1_1_1_1.setBounds(33, 229, 106, 16);
		frame.getContentPane().add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Status");
		lblNewLabel_1_1_1_1_1.setBounds(33, 283, 61, 16);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Date");
		lblNewLabel_1_1_1_1_1_1.setBounds(33, 338, 61, 16);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_1_1);
		
		textField = new JTextField();
		textField.setBounds(151, 63, 152, 40);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(151, 217, 152, 40);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(151, 164, 152, 40);
		frame.getContentPane().add(textField_2);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(151, 279, 170, 27);
		frame.getContentPane().add(comboBox);
		comboBox.addItem("      ");
		comboBox.addItem("Pending");
		comboBox.addItem("Completed");
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(155, 322, 155, 37);
		frame.getContentPane().add(dateChooser);
		
		JComboBox<String> comboBox_Complaint = new JComboBox<String>();
		comboBox_Complaint.setBounds(151, 119, 170, 27);
		frame.getContentPane().add(comboBox_Complaint);
		comboBox_Complaint.addItem("      ");
		comboBox_Complaint.addItem("Plumbing");
		comboBox_Complaint.addItem("Electrician");
		comboBox_Complaint.addItem("Carpenter");
		comboBox_Complaint.addItem("House Keeping");
		comboBox_Complaint.addItem("Emergency Services");
		comboBox_Complaint.addItem("Security");
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
				    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					Date date = dateChooser.getDate();
					String query = "insert into MaintenanceInfo (Time,Complaint,Help,House,Status,Date) values (?,?,?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.setString(1, textField.getText());
					pst.setObject(2, comboBox_Complaint.getSelectedItem());
					pst.setString(3, textField_2.getText());
					pst.setString(4, textField_1.getText());
					pst.setString(6, formatter.format(date));
					pst.setObject(5, comboBox.getSelectedItem());

					
					pst.execute();
					
					frame.dispose();
					Residents resident = new Residents();
					resident.frame.setVisible(true);
				}
				
				catch(Exception a) {
					
					a.printStackTrace();
					
				}
				
			}
		});
		btnDone.setBounds(47, 380, 117, 37);
		frame.getContentPane().add(btnDone);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnCancel.setBounds(176, 380, 117, 37);
		frame.getContentPane().add(btnCancel);
	
				
	}
}
