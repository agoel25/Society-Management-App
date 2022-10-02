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

import java.util.regex.Matcher;
import java.util.regex.Pattern; 

public class Security {

	public JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Security window = new Security();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection = null; 
	private JTextField textField_Phone;
	private JTextField textField_TimeIn;
	private JTextField textField_Search;
	public void refreshTable() {
		
		try {
			String query = "select * from SecurityInfo";
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
	public Security() {
		connection = sqliteConnection.dbConnector();
		initialize();
	}
	
	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0,3,22));
		frame.setSize(1270,980);
		frame.setBackground(Color.BLUE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Security");
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
		
		JTextField textField_Name = new JTextField();
		textField_Name.setBounds(1072, 147, 170, 40);
		frame.getContentPane().add(textField_Name);
		textField_Name.setColumns(10);

		
		JTextField textField_Vehicle = new JTextField();
		textField_Vehicle.setColumns(10);
		textField_Vehicle.setBounds(1072, 196, 170, 40);
		frame.getContentPane().add(textField_Vehicle);

		
		JTextField textField_House = new JTextField();
		textField_House.setColumns(10);
		textField_House.setBounds(1072, 248, 170, 40);
		frame.getContentPane().add(textField_House);

		
		JTextField textField_TimeOut = new JTextField();
		textField_TimeOut.setColumns(10);
		textField_TimeOut.setBounds(1072, 410, 170, 40);
		frame.getContentPane().add(textField_TimeOut);

		
		textField_Phone = new JTextField();
		textField_Phone.setColumns(10);
		textField_Phone.setBounds(1072, 462, 170, 40);
		frame.getContentPane().add(textField_Phone);

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 105, 892, 730);
		frame.getContentPane().add(scrollPane);
		
		
		JComboBox<String> comboBox_Motive = new JComboBox<String>();
		comboBox_Motive.setBounds(1072, 309, 170, 27);
		frame.getContentPane().add(comboBox_Motive);
		comboBox_Motive.addItem("     ");
		comboBox_Motive.addItem("Guest");
		comboBox_Motive.addItem("Delivery");
		comboBox_Motive.addItem("Work");
		
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(1076, 524, 163, 26);
		frame.getContentPane().add(dateChooser);
		
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
					
					String sql = "select * from SecurityInfo where Name=? or Vehicle=? or House=? or Motive=? or TimeIn=? or TimeOut=? or Date=?";
					PreparedStatement pst = connection.prepareStatement(sql);
					pst.setString(1, search);
					pst.setString(2, search);
					pst.setString(3, search);
					pst.setString(4, search);
					pst.setString(5, search);
					pst.setString(6, search);
					pst.setString(7, search);
					
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
		
		JButton btnAdd = new JButton("Add Visitor");
		btnAdd.setForeground(Color.WHITE);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent c) {
				
				try {
					
				    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

					Date date = dateChooser.getDate();
					
					
					String[] timeInParts = textField_TimeIn.getText().split(":");
					String hoursIn = timeInParts[0];
					String minsIn = timeInParts[1];
					
					String[] timeOutParts = textField_TimeOut.getText().split(":");
					String hoursOut = timeOutParts[0];
					String minsOut = timeOutParts[1];
					
					String query = "insert into SecurityInfo (Name,Vehicle,House,Motive,TimeIn,TimeOut,Phone,Date) values (?,?,?,?,?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					
					if (textField_Name.getText().equals("") || textField_Vehicle.getText().equals("") || textField_House.getText().equals("")
						|| comboBox_Motive.getSelectedItem().toString().equals("") || textField_TimeIn.getText().equals("") || textField_TimeOut.getText().equals("")           
						|| textField_Phone.getText().equals("") || formatter.format(date).toString().equals("")) {
						
						throw new MissingFields("Fields left empty");
					}
							
					else if (Integer.parseInt(hoursIn) > 23 || Integer.parseInt(minsIn) > 59 || Integer.parseInt(hoursIn) < 0 || 
							Integer.parseInt(minsIn) < 0 || textField_TimeOut.getText().contains(":") == false || Integer.parseInt(hoursOut) > 23 
							|| Integer.parseInt(minsOut) > 59 || Integer.parseInt(hoursOut) < 0 || Integer.parseInt(minsOut) < 0) {
						
						throw new FormatError("Invalid Time Entered");
					}
					
					else if (textField_TimeIn.getText().contains(":") == false) {
						throw new FormatError("Invalid Time Entered");
					}
					
					else if (textField_Phone.getText().length() != 10) {
						throw new FormatError("Invalid Phone Number Entered");
					}
					
					else if (textField_Phone.getText().matches("^[6-9]\\d{9}$") == false) {
						throw new FormatError("Invalid Phone Number Entered");
					}
					
					else {
						pst.setString(1, textField_Name.getText());
						pst.setString(2, textField_Vehicle.getText());
						pst.setString(3, textField_House.getText());
						pst.setObject(4, comboBox_Motive.getSelectedItem());
						pst.setString(5, textField_TimeIn.getText());
						pst.setString(6, textField_TimeOut.getText());
						pst.setString(7, textField_Phone.getText());
						pst.setString(8, formatter.format(date));
					
						pst.execute();
					
						refreshTable();
					}
				}
				
				catch(Exception a) {
					
					a.printStackTrace();
					
				}
				
			}
		});
		
		btnAdd.setBounds(952, 585, 299, 53);
		frame.getContentPane().add(btnAdd);
		
		JLabel lblNewLabel_1 = new JLabel("Add a Visitor");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Helvetica", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(1044, 119, 117, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.WHITE);
		lblName.setBounds(952, 159, 106, 16);
		frame.getContentPane().add(lblName);

		
		JLabel lblVehicle = new JLabel("Vehicle Number");
		lblVehicle.setBounds(952, 207, 106, 16);
		lblVehicle.setForeground(Color.WHITE);
		frame.getContentPane().add(lblVehicle);

		
		JLabel lblHouse = new JLabel("Visiting House");
		lblHouse.setBounds(952, 260, 106, 16);
		lblHouse.setForeground(Color.WHITE);
		frame.getContentPane().add(lblHouse);

		
		JLabel lblMotive = new JLabel("Motive");
		lblMotive.setBounds(952, 313, 94, 16);
		lblMotive.setForeground(Color.WHITE);
		frame.getContentPane().add(lblMotive);

		
		JLabel lblTimeIn = new JLabel("Time In");
		lblTimeIn.setBounds(952, 367, 106, 16);
		lblTimeIn.setForeground(Color.WHITE);
		frame.getContentPane().add(lblTimeIn);

		
		JLabel lblTimeOut = new JLabel("Time Out");
		lblTimeOut.setBounds(952, 422, 61, 16);
		lblTimeOut.setForeground(Color.WHITE);
		frame.getContentPane().add(lblTimeOut);


		
		JLabel lblPhone = new JLabel("Phone Number");
		lblPhone.setBounds(952, 474, 106, 16);
		lblPhone.setForeground(Color.WHITE);
		frame.getContentPane().add(lblPhone);

		
		JLabel lblDate = new JLabel("Date");
		lblDate.setForeground(Color.WHITE);
		lblDate.setBounds(952, 527, 106, 16);
		frame.getContentPane().add(lblDate);
		
		
		textField_TimeIn = new JTextField();
		textField_TimeIn.setColumns(10);
		textField_TimeIn.setBounds(1072, 353, 170, 40);
		frame.getContentPane().add(textField_TimeIn);
				
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int SelectedRows = table.getSelectedRow();
				
				String dateString = table.getModel().getValueAt(SelectedRows, 8).toString();
				
			    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
			    
			    Date date = new Date();
			    
			    try {
			        date = formatter.parse(dateString);
			        
			    } catch (java.text.ParseException p) {
			        p.printStackTrace();
			    }
				
				textField_Name.setText(table.getModel().getValueAt(SelectedRows, 1).toString());
				textField_Vehicle.setText(table.getModel().getValueAt(SelectedRows, 2).toString());
				textField_House.setText(table.getModel().getValueAt(SelectedRows,3).toString());
				comboBox_Motive.setSelectedItem(table.getModel().getValueAt(SelectedRows, 4).toString());
				textField_TimeIn.setText(table.getModel().getValueAt(SelectedRows, 5).toString());
				textField_TimeOut.setText(table.getModel().getValueAt(SelectedRows, 6).toString());
				textField_Phone.setText(table.getModel().getValueAt(SelectedRows, 7).toString());
				dateChooser.setDate(date);
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setForeground(Color.CYAN);
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(924, 105, 340, 723);
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
		lblNewLabel_Image.setBounds(6, 49, 1270, 851);
		frame.getContentPane().add(lblNewLabel_Image);
		
		btnMaintenance.setBounds(15, 10, 165, 45);
		frame.getContentPane().add(btnMaintenance);
		
		try {
			String query = "select * from SecurityInfo";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));

		}
		
		catch(Exception a) {
			
			a.printStackTrace();
			
		}
		
		JButton btnPayments = new JButton("Invoice");
		btnPayments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				Payments pays = new Payments();
				pays.frame.setVisible(true);

				  
			}
		});
		
		btnPayments.setFont(new Font("Helvetica", Font.PLAIN, 16));
		btnPayments.setForeground(Color.white);
		btnPayments.setBackground(Color.black);
		btnPayments.setBounds(195, 10, 165, 45);
		frame.getContentPane().add(btnPayments);
		
	}
}
