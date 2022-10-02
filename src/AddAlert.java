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

public class AddAlert {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAlert window = new AddAlert();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null; 
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	/**
	 * Create the application.
	 */
	public AddAlert() {
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
		
		JLabel lblTitle = new JLabel("Add Alert");
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
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setForeground(Color.BLACK);
		lblStatus.setBounds(25, 329, 106, 16);
		frame.getContentPane().add(lblStatus);
		
		JDateChooser dateChooser_End = new JDateChooser();
		dateChooser_End.setBounds(145, 279, 170, 27);
		frame.getContentPane().add(dateChooser_End);
		
		JLabel lblDate = new JLabel("Quarantine End");
		lblDate.setForeground(Color.BLACK);
		lblDate.setBounds(25, 290, 106, 16);
		frame.getContentPane().add(lblDate);
		
		JDateChooser dateChooser_Start = new JDateChooser();
		dateChooser_Start.setBounds(145, 238, 170, 27);
		frame.getContentPane().add(dateChooser_Start);
		
		JLabel lblTime = new JLabel("Quarantine Start");
		lblTime.setForeground(Color.BLACK);
		lblTime.setBounds(25, 238, 120, 16);
		frame.getContentPane().add(lblTime);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(145, 174, 170, 40);
		frame.getContentPane().add(textField);
		
		JLabel lblResident = new JLabel("Resident Name\n");
		lblResident.setForeground(Color.BLACK);
		lblResident.setBounds(25, 134, 106, 16);
		frame.getContentPane().add(lblResident);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(145, 122, 170, 40);
		frame.getContentPane().add(textField_1);
		
		JLabel lblHouse = new JLabel("House Number");
		lblHouse.setForeground(Color.BLACK);
		lblHouse.setBounds(25, 186, 106, 16);
		frame.getContentPane().add(lblHouse);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(145, 73, 170, 40);
		frame.getContentPane().add(textField_2);
		
		JLabel lblID = new JLabel("Alert ID");
		lblID.setForeground(Color.BLACK);
		lblID.setBounds(25, 85, 106, 16);
		frame.getContentPane().add(lblID);
		
		
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
				    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					Date start = dateChooser_Start.getDate();
					Date end = dateChooser_End.getDate();
					String query = "insert into Alerts (House,Name,ID,Status,Start,End) values (?,?,?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.setString(1, textField.getText());
					pst.setString(2, textField_1.getText());
					pst.setString(3, textField_2.getText());
					pst.setString(5, formatter.format(start));
					pst.setString(6, formatter.format(end));
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
