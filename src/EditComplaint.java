import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
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
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class EditComplaint {

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
					EditComplaint window = new EditComplaint();
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
	public EditComplaint() {
		connection = sqliteConnection.dbConnector();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 340, 490);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("Edit Complaint");
		lblTitle.setBounds(112, 11, 131, 16);
		frame.getContentPane().add(lblTitle);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setBounds(31, 103, 61, 16);
		frame.getContentPane().add(lblTime);
		
		JLabel lblNewLabel_1_1 = new JLabel("Complaint");
		lblNewLabel_1_1.setBounds(31, 151, 82, 16);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Help Needed");
		lblNewLabel_1_1_1.setBounds(31, 204, 106, 16);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("House Number");
		lblNewLabel_1_1_1_1.setBounds(31, 257, 106, 16);
		frame.getContentPane().add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Status");
		lblNewLabel_1_1_1_1_1.setBounds(31, 311, 61, 16);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Date");
		lblNewLabel_1_1_1_1_1_1.setBounds(31, 366, 61, 16);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_1_1);
		
		textField = new JTextField();
		textField.setBounds(149, 91, 152, 40);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(149, 245, 152, 40);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(149, 192, 152, 40);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(149, 139, 152, 40);
		frame.getContentPane().add(textField_3);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(149, 354, 152, 40);
		frame.getContentPane().add(textField_5);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(149, 307, 152, 27);
		frame.getContentPane().add(comboBox);
		comboBox.addItem("      ");
		comboBox.addItem("Pending");
		comboBox.addItem("Completed");
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {			
					
					/*
					String query = "update connector set SNo=?, Time=?, Complaint=?, Help=?, House=?, Status=?, Date=? where SNo=?";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.setString(1, textField.getText());
					pst.setString(2, textField_3.getText());
					pst.setString(3, textField_2.getText());
					pst.setString(4, textField_1.getText());
					pst.setString(6, textField_5.getText());
					pst.setObject(5, comboBox.getSelectedItem());
					
					*/
					
					String query = "update MaintenanceInfo set ID='"+textField_6.getText()+"' ,Time='"+textField.getText()+"' ,Complaint='"+textField_3.getText()+"' ,Help='"+textField_2.getText()+"' , House='"+textField_1.getText()+"' ,Status='"+textField_4.getText()+"' ,Date='"+textField_5.getText()+"' where ID='"+textField_6.getText()+"' ";
					
					PreparedStatement pst = connection.prepareStatement(query);
					
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
		btnDone.setBounds(45, 408, 117, 37);
		frame.getContentPane().add(btnDone);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnCancel.setBounds(174, 408, 117, 37);
		frame.getContentPane().add(btnCancel);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(149, 39, 152, 40);
		frame.getContentPane().add(textField_6);
		
		JLabel lblSno = new JLabel("SNo");
		lblSno.setBounds(31, 51, 61, 16);
		frame.getContentPane().add(lblSno);

	}
}
