import java.awt.EventQueue;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;

import java.util.Date;
import java.text.Format;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Alerts {
	
	public Date date;
	public JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Alerts window = new Alerts();
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
			String query = "select ID,House,Name,Start,End,Status from Alerts";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		
		catch(Exception a) {
			
			a.printStackTrace();
			
		}
	}
	
	private ImageIcon format = null; 
	/**
	 * Create the application.
	 */
	public Alerts() {
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
		
		JLabel lblNewLabel = new JLabel("Covid-19 Alerts");
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
		
		JTextField textField_ID = new JTextField();
		textField_ID.setBounds(1072, 333, 170, 40);
		frame.getContentPane().add(textField_ID);
		textField_ID.setColumns(10);
		
		JTextField textField_House = new JTextField();
		textField_House.setColumns(10);
		textField_House.setBounds(1072, 382, 170, 40);
		frame.getContentPane().add(textField_House);

		
		JTextField textField_Resident = new JTextField();
		textField_Resident.setColumns(10);
		textField_Resident.setBounds(1072, 434, 170, 40);
		frame.getContentPane().add(textField_Resident);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(999, 130, 179, 191);
		frame.getContentPane().add(desktopPane);
		
		JLabel image = new JLabel("");
		image.setBounds(6, 6, 167, 179);
		desktopPane.add(image);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(1072, 589, 170, 27);
		frame.getContentPane().add(comboBox);
		comboBox.addItem("Positive");
		comboBox.addItem("Negative");
		
		JDateChooser dateChooser_Start = new JDateChooser();
		dateChooser_Start.setBounds(1072, 498, 170, 27);
		frame.getContentPane().add(dateChooser_Start);
		
		JDateChooser dateChooser_End = new JDateChooser();
		dateChooser_End.setBounds(1072, 539, 170, 27);
		frame.getContentPane().add(dateChooser_End);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 105, 890, 725);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter(){
			
			public void mouseClicked(MouseEvent e) {
				
				int SelectedRows = table.getSelectedRow();
				
				String start = table.getModel().getValueAt(SelectedRows, 3).toString();
				String end = table.getModel().getValueAt(SelectedRows, 4).toString();
						
			    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
			    
			    Date dateStart = new Date();
			    Date dateEnd = new Date();
			    
			    try {
			        dateStart = formatter.parse(start);
			        dateEnd = formatter.parse(end);
			        
			    } catch (java.text.ParseException p) {
			        p.printStackTrace();
			    }
				
				textField_ID.setText(table.getModel().getValueAt(SelectedRows, 0).toString());
				textField_House.setText(table.getModel().getValueAt(SelectedRows, 1).toString());
				textField_Resident.setText(table.getModel().getValueAt(SelectedRows, 2).toString());
				dateChooser_Start.setDate(dateStart);
				dateChooser_End.setDate(dateEnd);
				comboBox.setSelectedItem(table.getModel().getValueAt(SelectedRows, 3).toString());
				
				try {
					String sql = "select Image from Alerts where ID ='"+table.getModel().getValueAt(SelectedRows, 0).toString()+"' ";
					
					PreparedStatement pst = connection.prepareStatement(sql);
					
					ResultSet rs = pst.executeQuery();
					

					byte[]imagedata = rs.getBytes("image");
					format = new ImageIcon(imagedata);
					image.setIcon(format);
					
					
					pst.execute();
					
				}catch(Exception s) {
					s.printStackTrace();
				}
			}
			
		});
		scrollPane.setViewportView(table);
		
		JButton btnAdd = new JButton("Add Covid Patient");
		btnAdd.setForeground(Color.WHITE);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent c) {
				
				frame.dispose();
				AddPayment pays = new AddPayment();
				pays.frame.setVisible(true);
				
			}
		});
		
		btnAdd.setBounds(941, 693, 314, 53);
		frame.getContentPane().add(btnAdd);

		
		JLabel lblNewLabel_1 = new JLabel("Alerts Info");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Helvetica", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(1044, 107, 117, 16);
		frame.getContentPane().add(lblNewLabel_1);

		
		JLabel lblID = new JLabel("Alert ID");
		lblID.setForeground(Color.WHITE);
		lblID.setBounds(952, 345, 106, 16);
		frame.getContentPane().add(lblID);
		
		JLabel lblHouse = new JLabel("House Number");
		lblHouse.setBounds(952, 393, 106, 16);
		lblHouse.setForeground(Color.WHITE);
		frame.getContentPane().add(lblHouse);
		
		JLabel lblResident = new JLabel("Resident Name\n");
		lblResident.setBounds(952, 446, 106, 16);
		lblResident.setForeground(Color.WHITE);
		frame.getContentPane().add(lblResident);
		
		JLabel lblTime = new JLabel("Quarantine Start");
		lblTime.setBounds(952, 498, 120, 16);
		lblTime.setForeground(Color.WHITE);
		frame.getContentPane().add(lblTime);

		
		JLabel lblDate = new JLabel("Quarantine End");
		lblDate.setBounds(952, 550, 106, 16);
		lblDate.setForeground(Color.WHITE);
		frame.getContentPane().add(lblDate);
		
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setBounds(952, 589, 106, 16);
		frame.getContentPane().add(lblStatus);
		
		JButton btnAdd_Delete = new JButton("Delete Patient");
		btnAdd_Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query = "Delete from Alerts where ID='"+textField_ID.getText()+"' ";
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
		btnAdd_Delete.setBounds(941, 758, 314, 53);
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
					
					String sql = "select ID,House,Name,Start,End,Status from Alerts where Name=? or House=? or Status=? or Start=? or End=?";
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
		
		JButton btnEdit = new JButton("Update Details");
		btnEdit.setForeground(Color.WHITE);
		btnEdit.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String status = comboBox.getSelectedItem().toString();
					
				    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					Date Start = dateChooser_Start.getDate();
					Date End = dateChooser_End.getDate();
					
					
					String query = "Update Alerts set ID='"+textField_ID.getText()+"',"
									+ "House='"+textField_House.getText()+"',"
									+ "Name='"+textField_Resident.getText()+"',"
									+ "Status='"+status+"' , "
									+ "Start='"+formatter.format(Start).toString()+"',"
									+ "End='"+formatter.format(End).toString()+"'"
									+ "where ID='"+textField_ID.getText()+"'";
					
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
		
		btnEdit.setBounds(941, 628, 314, 53);
		frame.getContentPane().add(btnEdit);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setForeground(Color.CYAN);
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(922, 97, 340, 731);
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
