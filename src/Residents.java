import java.awt.EventQueue;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;

public class Residents {

	public JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Residents window = new Residents();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection = null; 
	private JTextField textField_ID;
	private JTextField textField_Search;
	public void refreshTable() {
		
		try {
			String query = "select * from MaintenanceInfo";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		
		catch(Exception a) {
			
			a.printStackTrace();
			
		}
	}
	
	/**
	 * Create the application.
	 */
	public Residents() {
		connection = sqliteConnection.dbConnector();
		initialize();
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0,3,22));
		frame.setSize(1270,980);
		frame.setBackground(Color.BLUE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Maintenance");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Helvetica", Font.PLAIN, 22));
		lblNewLabel.setBounds(20, 63, 188, 30);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.hide();
		
		JButton btnPayments = new JButton("Invoice");
		btnPayments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Payments window = new Payments();
				window.frame.setVisible(true);
				frame.dispose();
				
			}
		});
		btnPayments.setFont(new Font("Helvetica", Font.PLAIN, 16));
		btnPayments.setForeground(Color.white);
		btnPayments.setBackground(Color.black);
		btnPayments.setBounds(195, 10, 165, 45);
		frame.getContentPane().add(btnPayments);
		
		JButton btnResidents = new JButton("Residents");
		btnResidents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				AllResidents window = new AllResidents();
				window.frame.setVisible(true);
			}
		});
		btnResidents.setFont(new Font("Helvetica", Font.PLAIN, 16));
		btnResidents.setForeground(Color.white);
		btnResidents.setBackground(Color.black);
		btnResidents.setBounds(375, 10, 165, 45);
		frame.getContentPane().add(btnResidents);
		
		JButton btnAlerts = new JButton("Covid Alerts");
		btnAlerts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				Alerts window = new Alerts();
				window.frame.setVisible(true);
			}
		});
		btnAlerts.setFont(new Font("Helvetica", Font.PLAIN, 16));
		btnAlerts.setForeground(Color.white);
		btnAlerts.setBackground(Color.black);
		btnAlerts.setBounds(555, 10, 165, 45);
		frame.getContentPane().add(btnAlerts);
		
		JButton btnSecurity = new JButton("Security");
		btnSecurity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				Security sec = new Security();
				sec.frame.setVisible(true);
			}
		});
		btnSecurity.setFont(new Font("Helvetica", Font.PLAIN, 16));
		btnSecurity.setForeground(Color.white);
		btnSecurity.setBackground(Color.black);
		btnSecurity.setBounds(735, 10, 165, 45);
		frame.getContentPane().add(btnSecurity);
		
		JButton btnBookings = new JButton("Bookings");
		btnBookings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				Reservations window = new Reservations();
				window.frame.setVisible(true);
			}
		});
		btnBookings.setFont(new Font("Helvetica", Font.PLAIN, 16));
		btnBookings.setForeground(Color.white);
		btnBookings.setBackground(Color.black);
		btnBookings.setBounds(915, 10, 165, 45);
		frame.getContentPane().add(btnBookings);
		
		JButton btnAccount = new JButton("Sign Out");
		btnAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				IA window = new IA();
				window.frame.setVisible(true);
			}
		});
		btnAccount.setFont(new Font("Helvetica", Font.PLAIN, 16));
		btnAccount.setForeground(Color.white);
		btnAccount.setBackground(Color.black);
		btnAccount.setBounds(1090, 10, 165, 45);
		frame.getContentPane().add(btnAccount);
		
		JTextField textField_Time = new JTextField();
		textField_Time.setBounds(1072, 155, 170, 40);
		frame.getContentPane().add(textField_Time);
		textField_Time.setColumns(10);
		textField_Time.hide();
		
		JTextField textField_House = new JTextField();
		textField_House.setColumns(10);
		textField_House.setBounds(1072, 309, 170, 40);
		frame.getContentPane().add(textField_House);
		textField_House.hide();
		
		JTextField textField_Complaint = new JTextField();
		textField_Complaint.setColumns(10);
		textField_Complaint.setBounds(1072, 256, 170, 40);
		frame.getContentPane().add(textField_Complaint);
		textField_Complaint.hide();
		
		JComboBox<String> comboBox_Status = new JComboBox<String>();
		comboBox_Status.setBackground(Color.WHITE);
		comboBox_Status.setForeground(Color.BLACK);
		comboBox_Status.setBounds(1072, 371, 170, 27);
		frame.getContentPane().add(comboBox_Status);
		comboBox_Status.addItem("      ");
		comboBox_Status.addItem("Pending");
		comboBox_Status.addItem("Resolved");
		comboBox_Status.hide();
		
		textField_ID = new JTextField();
		textField_ID.setColumns(10);
		textField_ID.setBounds(1072, 470, 170, 40);
		frame.getContentPane().add(textField_ID);
		textField_ID.hide();
		
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(1074, 422, 166, 31);
		frame.getContentPane().add(dateChooser);
		
		JComboBox<String> comboBox_Complaint = new JComboBox<String>();
		comboBox_Complaint.setBackground(Color.WHITE);
		comboBox_Complaint.setForeground(Color.BLACK);
		comboBox_Complaint.setBounds(1072, 207, 170, 27);
		frame.getContentPane().add(comboBox_Complaint);
		comboBox_Complaint.addItem("      ");
		comboBox_Complaint.addItem("Plumbing");
		comboBox_Complaint.addItem("Electrician");
		comboBox_Complaint.addItem("Carpenter");
		comboBox_Complaint.addItem("House Keeping");
		comboBox_Complaint.addItem("Emergency Services");
		comboBox_Complaint.addItem("Security");
		comboBox_Complaint.hide();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 105, 890, 725);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		
		table.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				
				int SelectedRows = table.getSelectedRow();
				
				String dateString = table.getModel().getValueAt(SelectedRows, 5).toString();
				
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				
				Date date = new Date();
				
				  try {
				        date = formatter.parse(dateString);
				        
				    } catch (java.text.ParseException exception_click) {
				        exception_click.printStackTrace();
				    }
						
				textField_Time.setText(table.getModel().getValueAt(SelectedRows, 0).toString());
				textField_Complaint.setText(table.getModel().getValueAt(SelectedRows, 2).toString());
				textField_House.setText(table.getModel().getValueAt(SelectedRows, 3).toString());
				comboBox_Complaint.setSelectedItem(table.getModel().getValueAt(SelectedRows, 1).toString());
				dateChooser.setDate(date);
				textField_ID.setText(table.getModel().getValueAt(SelectedRows, 6).toString());
				comboBox_Status.setSelectedItem(table.getModel().getValueAt(SelectedRows, 4).toString());
			}
			
		});
		scrollPane.setViewportView(table);
		scrollPane.hide();
		table.hide();
		
		JButton btnAdd = new JButton("Add Complaint");
		btnAdd.setForeground(Color.WHITE);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent c) {
				
				frame.dispose();
				AddComplaint complaint = new AddComplaint();
				complaint.frame.setVisible(true);
				
			}
		});
		
		btnAdd.setBounds(933, 600, 314, 53);
		frame.getContentPane().add(btnAdd);
		btnAdd.hide();
		
		JButton btnDelete = new JButton("Delete Complaint");
		btnDelete.setForeground(Color.WHITE);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query = "Delete from MaintenanceInfo where ID='"+textField_ID.getText()+"' ";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.execute();
					
					pst.close();
				}
				
				catch(Exception a) {
					
					a.printStackTrace();
				}
				
				refreshTable();

			}
		});
		btnDelete.setBounds(933, 670, 309, 53);
		frame.getContentPane().add(btnDelete);
		btnDelete.hide();
		
		JLabel lblNewLabel_1 = new JLabel("Complaint Info");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Helvetica", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(1044, 119, 117, 16);
		frame.getContentPane().add(lblNewLabel_1);
		lblNewLabel_1.hide();
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setForeground(Color.WHITE);
		lblTime.setBounds(952, 167, 61, 16);
		frame.getContentPane().add(lblTime);
		lblTime.hide();
		
		JLabel lblNewLabel_1_1 = new JLabel("Complaint");
		lblNewLabel_1_1.setBounds(952, 215, 82, 16);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		frame.getContentPane().add(lblNewLabel_1_1);
		lblNewLabel_1_1.hide();
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Allocated to");
		lblNewLabel_1_1_1.setBounds(952, 268, 106, 16);
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		lblNewLabel_1_1_1.hide();
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("House Number");
		lblNewLabel_1_1_1_1.setBounds(952, 321, 106, 16);
		lblNewLabel_1_1_1_1.setForeground(Color.WHITE);
		frame.getContentPane().add(lblNewLabel_1_1_1_1);
		lblNewLabel_1_1_1_1.hide();
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Status");
		lblNewLabel_1_1_1_1_1.setBounds(952, 375, 61, 16);
		lblNewLabel_1_1_1_1_1.setForeground(Color.WHITE);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_1);
		lblNewLabel_1_1_1_1_1.hide();
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Date");
		lblNewLabel_1_1_1_1_1_1.setBounds(952, 430, 61, 16);
		lblNewLabel_1_1_1_1_1_1.setForeground(Color.WHITE);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_1_1);
		lblNewLabel_1_1_1_1_1_1.hide();

		
		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Complaint ID");
		lblNewLabel_1_1_1_1_1_1_1.setBounds(952, 482, 106, 16);
		lblNewLabel_1_1_1_1_1_1_1.setForeground(Color.WHITE);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_1_1_1);
		lblNewLabel_1_1_1_1_1_1_1.hide();
		
		
		textField_Search = new JTextField();
		textField_Search.setBounds(549, 77, 130, 26);
		frame.getContentPane().add(textField_Search);
		textField_Search.setColumns(10);
		
		JButton btnSearch_1 = new JButton("Back");
		btnSearch_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				refreshTable();
				textField_Search.setText(null);
			}
		});
		btnSearch_1.setForeground(Color.WHITE);
		btnSearch_1.setBounds(793, 77, 124, 29);
		frame.getContentPane().add(btnSearch_1);
		
		JButton btnSearch = new JButton("Search");
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String search = textField_Search.getText();
				
				try {
					
					String sql = "select * from MaintenanceInfo where Time=? or Complaint=? or Help=? or House=? or Status=? or Date=?";
					PreparedStatement pst = connection.prepareStatement(sql);
					pst.setString(1, search);
					pst.setString(2, search);
					pst.setString(3, search);
					pst.setString(4, search);
					pst.setString(5, search);
					pst.setString(6, search);
					
					ResultSet rs = pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					while(rs.next()) {

						table.setModel(DbUtils.resultSetToTableModel(rs));
					}
					
					pst.close();
					
				}
				catch(Exception i) {
					JOptionPane.showMessageDialog(null, i);
				}
			}
		});
		
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setBounds(677, 77, 124, 29);
		frame.getContentPane().add(btnSearch);
		
		JButton btnEdit = new JButton("Update Complaint");
		btnEdit.setForeground(Color.WHITE);
		btnEdit.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String status = comboBox_Status.getSelectedItem().toString();
					String allocated = comboBox_Complaint.getSelectedItem().toString();
					
				    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					Date date = dateChooser.getDate();
					
					String query = "Update MaintenanceInfo set Time='"+textField_Time.getText()+"' , Help='"+textField_Complaint.getText()+"' , Complaint='"+allocated+"', "
									+ "House='"+textField_House.getText()+"' , Status='"+status+"', Date='"+formatter.format(date)+"' , "
											+ "ID='"+textField_ID.getText()+"' where ID='"+textField_ID.getText()+"' ";
					
					PreparedStatement pst = connection.prepareStatement(query);
				
					pst.execute();
					pst.close();
				}
				
				catch(Exception a) {
					
					a.printStackTrace();
					
				}
				refreshTable();
			
			}
		});
		
		btnEdit.setBounds(933, 530, 314, 53);
		frame.getContentPane().add(btnEdit);
		btnEdit.hide();
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setForeground(Color.CYAN);
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(922, 103, 340, 725);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.hide();
		
		lblNewLabel.show();
		textField_Time.show();
		textField_House.show();
		textField_Complaint.show();
		comboBox_Status.show();
		textField_ID.show();
		comboBox_Complaint.show();
		scrollPane.show();
		table.show();
		btnAdd.show();
		btnEdit.show();
		btnDelete.show();
		lblNewLabel_1.show();
		lblTime.show();
		lblNewLabel_1_1.show();
		lblNewLabel_1_1_1.show();
		lblNewLabel_1_1_1_1.show();
		lblNewLabel_1_1_1_1_1.show();
		lblNewLabel_1_1_1_1_1_1.show();
		lblNewLabel_1_1_1_1_1_1_1.show();
		btnNewButton.show();
		
		try {
			String query = "select * from MaintenanceInfo";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));

		}
		
		catch(Exception a) {
			
			a.printStackTrace();
			
		}
	
		JButton btnMaintenance = new JButton("Maintenance");
		btnMaintenance.setFont(new Font("Helvetica", Font.PLAIN, 16));
		btnMaintenance.setForeground(Color.white);
		btnMaintenance.setBackground(Color.black);
		btnMaintenance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel.show();
				textField_Time.show();
				textField_House.show();
				textField_Complaint.show();
				dateChooser.show();
				comboBox_Status.show();
				textField_ID.show();
				comboBox_Complaint.show();
				scrollPane.show();
				table.show();
				btnAdd.show();
				btnEdit.show();
				btnDelete.show();
				lblNewLabel_1.show();
				lblTime.show();
				lblNewLabel_1_1.show();
				lblNewLabel_1_1_1.show();
				lblNewLabel_1_1_1_1.show();
				lblNewLabel_1_1_1_1_1.show();
				lblNewLabel_1_1_1_1_1_1.show();
				lblNewLabel_1_1_1_1_1_1_1.show();
				
				try {
					String query = "select * from MaintenanceInfo";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
		
				}
				
				catch(Exception a) {
					
					a.printStackTrace();
					
				}
				  
			}
		});
		
		JLabel lblNewLabel_Image = new JLabel("");
		//lblNewLabel_Image.setIcon(new ImageIcon(IA.class.getResource("/image/download.png")));
		lblNewLabel_Image.setBackground(Color.cyan);
		lblNewLabel_Image.setBounds(0, 0, 1270, 851);
		frame.getContentPane().add(lblNewLabel_Image);
		
		btnMaintenance.setBounds(15, 10, 165, 45);
		frame.getContentPane().add(btnMaintenance);

	}
}
