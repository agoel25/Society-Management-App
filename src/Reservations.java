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

public class Reservations {

	public JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reservations window = new Reservations();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection = null; 
	private JTextField textField_Search;
	
	public void refreshTable() {
		
		try {
			String query = "select * from ReservationsInfo";
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
	public Reservations() {
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
		
		JLabel lblNewLabel = new JLabel("Reservations");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Helvetica", Font.PLAIN, 22));
		lblNewLabel.setBounds(20, 63, 188, 30);
		frame.getContentPane().add(lblNewLabel);
		
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
		btnAccount.setFont(new Font("Helvetica", Font.PLAIN, 16));
		btnAccount.setForeground(Color.white);
		btnAccount.setBackground(Color.black);
		btnAccount.setBounds(1090, 10, 165, 45);
		frame.getContentPane().add(btnAccount);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(1076, 416, 163, 26);
		frame.getContentPane().add(dateChooser);
		JTextField textField_ID = new JTextField();
		textField_ID.setBounds(1072, 155, 170, 40);
		frame.getContentPane().add(textField_ID);
		textField_ID.setColumns(10);
		
		JTextField textField_House = new JTextField();
		textField_House.setColumns(10);
		textField_House.setBounds(1072, 204, 170, 40);
		frame.getContentPane().add(textField_House);

		
		JTextField textField_Resident = new JTextField();
		textField_Resident.setColumns(10);
		textField_Resident.setBounds(1072, 256, 170, 40);
		frame.getContentPane().add(textField_Resident);
		
		JTextField textField_Time = new JTextField();
		textField_Time.setColumns(10);
		textField_Time.setBounds(1072, 356, 170, 40);
		frame.getContentPane().add(textField_Time);

		
		JComboBox<String> comboBox_Type = new JComboBox<String>();
		comboBox_Type.setBackground(Color.WHITE);
		comboBox_Type.setForeground(Color.BLACK);
		comboBox_Type.setBounds(1072, 317, 170, 27);
		frame.getContentPane().add(comboBox_Type);
		comboBox_Type.addItem("      ");
		comboBox_Type.addItem("Swimming Pool");
		comboBox_Type.addItem("Tennis Court");
		comboBox_Type.addItem("Badmintton Court");
		comboBox_Type.addItem("Basketball Court");
		comboBox_Type.addItem("MPH");
		comboBox_Type.addItem("Bowling Alley");
		comboBox_Type.addItem("Pool Table");
		
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
				
				textField_ID.setText(table.getModel().getValueAt(SelectedRows, 0).toString());
				textField_House.setText(table.getModel().getValueAt(SelectedRows, 1).toString());
				textField_Resident.setText(table.getModel().getValueAt(SelectedRows, 2).toString());
				comboBox_Type.setSelectedItem(table.getModel().getValueAt(SelectedRows, 3).toString());
				textField_Time.setText(table.getModel().getValueAt(SelectedRows, 4).toString());
				dateChooser.setDate(date);
			}
			
		});
		scrollPane.setViewportView(table);
		
		JButton btnAdd = new JButton("Add Reservation");
		btnAdd.setForeground(Color.WHITE);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent c) {
				
				frame.dispose();
				AddPayment pays = new AddPayment();
				pays.frame.setVisible(true);
				
			}
		});
		
		btnAdd.setBounds(933, 533, 314, 53);
		frame.getContentPane().add(btnAdd);

		
		JLabel lblNewLabel_1 = new JLabel("Reservation Info");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Helvetica", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(1044, 119, 117, 16);
		frame.getContentPane().add(lblNewLabel_1);

		
		JLabel lblID = new JLabel("Reservation ID");
		lblID.setForeground(Color.WHITE);
		lblID.setBounds(952, 167, 106, 16);
		frame.getContentPane().add(lblID);
		
		JLabel lblHouse = new JLabel("House Number");
		lblHouse.setBounds(952, 215, 106, 16);
		lblHouse.setForeground(Color.WHITE);
		frame.getContentPane().add(lblHouse);
		
		JLabel lblResident = new JLabel("Resident");
		lblResident.setBounds(952, 268, 106, 16);
		lblResident.setForeground(Color.WHITE);
		frame.getContentPane().add(lblResident);

		
		JLabel lblType = new JLabel("Booking");
		lblType.setBounds(952, 321, 94, 16);
		lblType.setForeground(Color.WHITE);
		frame.getContentPane().add(lblType);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setBounds(952, 368, 61, 16);
		lblTime.setForeground(Color.WHITE);
		frame.getContentPane().add(lblTime);

		
		JLabel lblDate = new JLabel("Date ");
		lblDate.setBounds(952, 420, 106, 16);
		lblDate.setForeground(Color.WHITE);
		frame.getContentPane().add(lblDate);
		
		JButton btnAdd_Delete = new JButton("Delete Reservation");
		btnAdd_Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query = "Delete from ReservationsInfo where ID='"+textField_ID.getText()+"' ";
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
		btnAdd_Delete.setForeground(Color.WHITE);
		btnAdd_Delete.setBounds(933, 598, 314, 53);
		frame.getContentPane().add(btnAdd_Delete);
		
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
					
					String sql = "select * from ReservationsInfo where Resident=? or Booking=? or House=? or Time=? or Date=?";
					PreparedStatement pst = connection.prepareStatement(sql);
					pst.setString(1, search);
					pst.setString(2, search);
					pst.setString(3, search);
					pst.setString(4, search);
					pst.setString(5, search);
					
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
		
		JButton btnEdit = new JButton("Update Reservation");
		btnEdit.setForeground(Color.WHITE);
		btnEdit.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				try {
				    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				    
					Date date = dateChooser.getDate();
					
					String type = comboBox_Type.getSelectedItem().toString();
					
					String query = "Update ReservationsInfo set ID='"+textField_ID.getText()+"',"
									+ "House='"+textField_House.getText()+"',"
									+ "Resident='"+textField_Resident.getText()+"',"
									+ "Booking='"+type+"',"
									+ "Time='"+textField_Time.getText()+"',"
									+ "Date='"+formatter.format(date)+"'"
									+ "where ID='"+textField_ID.getText()+"' ";
					
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
		
		btnEdit.setBounds(933, 468, 314, 53);
		frame.getContentPane().add(btnEdit);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setForeground(Color.CYAN);
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(922, 103, 340, 725);
		frame.getContentPane().add(btnNewButton);
	
		JButton btnMaintenance = new JButton("Maintenance");
		btnMaintenance.setFont(new Font("Helvetica", Font.PLAIN, 16));
		btnMaintenance.setForeground(Color.white);
		btnMaintenance.setBackground(Color.black);
		btnMaintenance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Residents window = new Residents();
				window.frame.setVisible(true);
				  
			}
		});
		
		JLabel lblNewLabel_Image = new JLabel("");
		//lblNewLabel_Image.setIcon(new ImageIcon(IA.class.getResource("/image/download.png")));
		lblNewLabel_Image.setBackground(Color.cyan);
		lblNewLabel_Image.setBounds(0, 0, 1270, 851);
		frame.getContentPane().add(lblNewLabel_Image);
		
		btnMaintenance.setBounds(15, 10, 165, 45);
		frame.getContentPane().add(btnMaintenance);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		refreshTable();
		
		
		JButton btnPayments = new JButton("Invoice");
		btnPayments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				  
			}
		});
		
		btnPayments.setFont(new Font("Helvetica", Font.PLAIN, 16));
		btnPayments.setForeground(Color.white);
		btnPayments.setBackground(Color.black);
		btnPayments.setBounds(195, 10, 165, 45);
		frame.getContentPane().add(btnPayments);
	}
}
