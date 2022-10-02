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

public class AddPayment {

	public JFrame frame;
	private JTextField textField_House;
	private JTextField textField_Resident;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPayment window = new AddPayment();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null; 
	private JTextField textField_Alert;
	private JComboBox<String> comboBox_Type;
	private JTextField textField;
	/**
	 * Create the application.
	 */
	public AddPayment() {
		connection = sqliteConnection.dbConnector();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 340, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("Add Payment");
		lblTitle.setBounds(122, 18, 131, 16);
		frame.getContentPane().add(lblTitle);
		
		JLabel lblHouse = new JLabel("House Number");
		lblHouse.setBounds(33, 75, 106, 16);
		frame.getContentPane().add(lblHouse);
		
		JLabel lblResident = new JLabel("Resident");
		lblResident.setBounds(33, 123, 82, 16);
		frame.getContentPane().add(lblResident);
		
		JLabel lblType = new JLabel("Payment Type");
		lblType.setBounds(33, 176, 106, 16);
		frame.getContentPane().add(lblType);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(33, 229, 106, 16);
		frame.getContentPane().add(lblStatus);
		
		JLabel lblDue = new JLabel("Due Date");
		lblDue.setBounds(33, 283, 61, 16);
		frame.getContentPane().add(lblDue);
		
		JLabel lblReceive = new JLabel("Date Received ");
		lblReceive.setBounds(33, 338, 106, 16);
		frame.getContentPane().add(lblReceive);
		
		JLabel lblAlert = new JLabel("Alert");
		lblAlert.setBounds(33, 390, 61, 16);
		frame.getContentPane().add(lblAlert);
		
		textField_House = new JTextField();
		textField_House.setBounds(151, 63, 170, 40);
		frame.getContentPane().add(textField_House);
		textField_House.setColumns(10);
		
		textField_Resident = new JTextField();
		textField_Resident.setColumns(10);
		textField_Resident.setBounds(151, 111, 170, 40);
		frame.getContentPane().add(textField_Resident);
		
		JComboBox<String> comboBox_Status = new JComboBox<String>();
		comboBox_Status.setBounds(151, 225, 170, 27);
		frame.getContentPane().add(comboBox_Status);
		comboBox_Status.addItem("      ");
		comboBox_Status.addItem("Pending");
		comboBox_Status.addItem("Completed");
		
		comboBox_Type = new JComboBox<String>();
		comboBox_Type.setBounds(151, 172, 170, 27);
		frame.getContentPane().add(comboBox_Type);
		comboBox_Type.addItem("      ");
		comboBox_Type.addItem("Check");
		comboBox_Type.addItem("Cash");
		comboBox_Type.addItem("Bank Transfer");
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(151, 328, 161, 26);
		frame.getContentPane().add(dateChooser_1);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(151, 273, 161, 26);
		frame.getContentPane().add(dateChooser);
		
		textField_Alert = new JTextField();
		textField_Alert.setColumns(10);
		textField_Alert.setBounds(151, 378, 170, 40);
		frame.getContentPane().add(textField_Alert);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(151, 439, 170, 40);
		frame.getContentPane().add(textField);
	
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
				    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					Date due = dateChooser.getDate();
					Date recieve = dateChooser_1.getDate();
					String query = "insert into PaymentsInfo (House,Resident,Type,Status,DueDate,DateReceived,Alert,Fine) values (?,?,?,?,?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.setString(1, textField_House.getText());
					pst.setString(2, textField_Resident.getText());
					pst.setObject(3, comboBox_Type.getSelectedItem());
					pst.setObject(4, comboBox_Status.getSelectedItem());
					pst.setString(5, formatter.format(due));
					pst.setString(6, formatter.format(recieve));
					pst.setString(7, textField_Alert.getText());
					pst.setString(8, textField.getText());

					
					pst.execute();
					
					frame.dispose();
					Payments py = new Payments();
					py.frame.setVisible(true);
				}
				
				catch(Exception a) {
					
					a.printStackTrace();
					
				}
				
			}
		});
		btnDone.setBounds(50, 579, 117, 37);
		frame.getContentPane().add(btnDone);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnCancel.setBounds(179, 579, 117, 37);
		frame.getContentPane().add(btnCancel);
		
		JLabel lblFine = new JLabel("Fine");
		lblFine.setBounds(33, 451, 61, 16);
		frame.getContentPane().add(lblFine);
		
				
	}
}
