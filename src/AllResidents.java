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

public class AllResidents {

	public JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllResidents window = new AllResidents();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public void refreshTable() {
		
		try {
			String query = "select FirstName,LastName,Username,HouseNumber,ResidentID,Email,Phone,Position from ResidentsInfo";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		
		catch(Exception a) {
			
			a.printStackTrace();
			
		}
	}
	
	Connection connection = null; 
	
	/**
	 * Create the application.
	 */
	public AllResidents() {
		connection = sqliteConnection.dbConnector();
		initialize();
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	
	private ImageIcon format = null; 
	
	public void initialize() {
		
		frame = new JFrame();
		table = new JTable();
		JLabel lblNewLabel_Image = new JLabel("");
		JLabel lblNewLabel = new JLabel("Residents");		
		JLabel lblNewLabel_1 = new JLabel("Resident Info");
		JLabel lblFirst = new JLabel("First Name");
		JLabel lblLast = new JLabel("Last Name");
		JLabel lblUsername = new JLabel("Username");
		JLabel lblHouse = new JLabel("House Number");
		JLabel lblID = new JLabel("Resident ID");
		JLabel lblEmail = new JLabel("Email");
		JLabel lblPhone = new JLabel("Phone Number");
		JLabel lblPosition = new JLabel("Position");
		JLabel image = new JLabel("");
		JButton btnNewButton = new JButton("");
		JButton btnMaintenance = new JButton("Maintenance");
		JButton btnResidents = new JButton("Residents");
		JButton btnPayments = new JButton("Invoice");
		JButton btnAlerts = new JButton("Covid Alerts");
		JButton btnSecurity = new JButton("Security");
		JButton btnBookings = new JButton("Bookings");
		JButton btnAdd = new JButton("Add Resident");
		JButton btnEdit = new JButton("Update Resident");
		JButton btnDelete = new JButton("Delete Resident");
		JButton btnSearch = new JButton("Search");
		JButton btnSearch_1 = new JButton("Back");
		JTextField textField_First = new JTextField();
		JTextField textField_Last = new JTextField();
		JTextField textField_Username = new JTextField();
		JTextField textField_Email = new JTextField();
		JTextField textField_Phone = new JTextField();
		JTextField textField_Search = new JTextField();
		JTextField textField_House = new JTextField();	
		JTextField textField_ID = new JTextField();
		JComboBox<String> comboBox_Position = new JComboBox<String>();
		JDesktopPane desktopPane = new JDesktopPane();
		JScrollPane scrollPane = new JScrollPane();		
		
		frame.getContentPane().setBackground(new Color(0,3,22));
		frame.setSize(1270,980);
		frame.setBackground(Color.BLUE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	
		btnNewButton.setForeground(Color.CYAN);
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(922, 103, 340, 725);
		frame.getContentPane().add(btnNewButton);
	
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Helvetica", Font.PLAIN, 22));
		lblNewLabel.setBounds(20, 63, 188, 30);
		frame.getContentPane().add(lblNewLabel);
		
		btnResidents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				refreshTable();
			}
		});
		btnResidents.setFont(new Font("Helvetica", Font.PLAIN, 16));
		btnResidents.setForeground(Color.white);
		btnResidents.setBackground(Color.black);
		btnResidents.setBounds(375, 10, 165, 45);
		frame.getContentPane().add(btnResidents);
		
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
		
		textField_First.setColumns(10);
		textField_First.setBounds(1068, 387, 170, 40);
		frame.getContentPane().add(textField_First);
		
		textField_Last.setColumns(10);
		textField_Last.setBounds(1068, 439, 170, 40);
		frame.getContentPane().add(textField_Last);
		
		textField_Username.setColumns(10);
		textField_Username.setBounds(1068, 491, 170, 40);
		frame.getContentPane().add(textField_Username);
		
		textField_Email.setColumns(10);
		textField_Email.setBounds(1068, 653, 170, 40);
		frame.getContentPane().add(textField_Email);
		
		textField_Phone.setColumns(10);
		textField_Phone.setBounds(1068, 705, 170, 40);
		frame.getContentPane().add(textField_Phone);
		
		scrollPane.setBounds(20, 105, 892, 656);
		frame.getContentPane().add(scrollPane);
		
		btnAdd.setForeground(Color.WHITE);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent c) {
				
				frame.dispose();
				AddResident res = new AddResident();
				res.frame.setVisible(true);
				
			}
		});
		
		btnAdd.setBounds(20, 773, 299, 53);
		frame.getContentPane().add(btnAdd);
		
		btnDelete.setForeground(Color.WHITE);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query = "Delete from ResidentsInfo where ResidentID='"+textField_ID.getText()+"' ";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.execute();
					
					pst.close();
					
					refreshTable();
				}
				
				catch(Exception a) {
					
					a.printStackTrace();
				}

			}
		});
		btnDelete.setBounds(319, 773, 299, 53);
		frame.getContentPane().add(btnDelete);

		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Helvetica", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(1044, 119, 117, 16);
		frame.getContentPane().add(lblNewLabel_1);

		lblFirst.setForeground(Color.WHITE);
		lblFirst.setBounds(948, 402, 106, 16);
		frame.getContentPane().add(lblFirst);
		
		lblLast.setBounds(948, 450, 106, 16);
		lblLast.setForeground(Color.WHITE);
		frame.getContentPane().add(lblLast);
		
		lblUsername.setBounds(948, 503, 106, 16);
		lblUsername.setForeground(Color.WHITE);
		frame.getContentPane().add(lblUsername);

		lblHouse.setBounds(948, 556, 94, 16);
		lblHouse.setForeground(Color.WHITE);
		frame.getContentPane().add(lblHouse);
		
		lblID.setBounds(948, 610, 106, 16);
		lblID.setForeground(Color.WHITE);
		frame.getContentPane().add(lblID);
		
		lblEmail.setBounds(948, 665, 61, 16);
		lblEmail.setForeground(Color.WHITE);
		frame.getContentPane().add(lblEmail);

		lblPhone.setBounds(948, 717, 106, 16);
		lblPhone.setForeground(Color.WHITE);
		frame.getContentPane().add(lblPhone);
		
		lblPosition.setForeground(Color.WHITE);
		lblPosition.setBounds(948, 770, 106, 16);
		frame.getContentPane().add(lblPosition);

		textField_House.setColumns(10);
		textField_House.setBounds(1068, 544, 170, 40);
		frame.getContentPane().add(textField_House);

		textField_ID.setColumns(10);
		textField_ID.setBounds(1068, 596, 170, 40);
		frame.getContentPane().add(textField_ID);

		comboBox_Position.setBounds(1068, 766, 170, 27);
		frame.getContentPane().add(comboBox_Position);
		comboBox_Position.addItem("      ");
		comboBox_Position.addItem("Resident");
		comboBox_Position.addItem("Owner");
		comboBox_Position.addItem("RWA Member");
		comboBox_Position.addItem("RWA Head");

		desktopPane.setBounds(999, 159, 179, 191);
		frame.getContentPane().add(desktopPane);

		image.setBounds(6, 6, 167, 179);
		desktopPane.add(image);
		
		textField_Search.setBounds(549, 77, 130, 26);
		frame.getContentPane().add(textField_Search);
		textField_Search.setColumns(10);

		btnSearch_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				refreshTable();
				textField_Search.setText(null);
			}
		});
		btnSearch_1.setForeground(Color.WHITE);
		btnSearch_1.setBounds(793, 77, 124, 29);
		frame.getContentPane().add(btnSearch_1);
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String search = textField_Search.getText();
				
				try {
					
					String sql = "select FirstName,LastName,Username,HouseNumber,ResidentID,Email,Phone,Position from ResidentsInfo where FirstName=? or LastName=? or Username=? or HouseNumber=? or ResidentID=? or Position=?";
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
				
		table.addMouseListener(new MouseAdapter(){
			
			public void mouseClicked(MouseEvent e) {
				
				int SelectedRows = table.getSelectedRow();
				
				textField_First.setText(table.getModel().getValueAt(SelectedRows, 0).toString());
				textField_Last.setText(table.getModel().getValueAt(SelectedRows, 1).toString());
				textField_Username.setText(table.getModel().getValueAt(SelectedRows, 2).toString());
				textField_House.setText(table.getModel().getValueAt(SelectedRows, 3).toString());
				textField_ID.setText(table.getModel().getValueAt(SelectedRows, 4).toString());
				textField_Email.setText(table.getModel().getValueAt(SelectedRows, 5).toString());
				textField_Phone.setText(table.getModel().getValueAt(SelectedRows, 6).toString());
				comboBox_Position.setSelectedItem(table.getModel().getValueAt(SelectedRows, 7).toString());
				
				try {
					
					String sql = "select Image from ResidentsInfo where ResidentID ='"+table.getModel().getValueAt(SelectedRows, 4).toString()+"' ";
					
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
		
		btnEdit.setForeground(Color.WHITE);
		btnEdit.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					if (textField_Phone.getText().length() != 10) {
						
						JOptionPane.showMessageDialog(null, "Phone Number Length is not 10");
						//throw new Exception("Incorrect Phone Length");
					}

					else if (textField_Phone.getText().isEmpty() || textField_First.getText().isEmpty() || textField_Last.getText().isEmpty() || textField_House.getText().isEmpty() || textField_Email.getText().isEmpty() || textField_ID.getText().isEmpty()) {
						
						JOptionPane.showMessageDialog(null, "Fields missing");
						//throw new Exception("Missing Fields");
					}
					
					
					else {
						
						String position = comboBox_Position.getSelectedItem().toString();
					
						String query = "Update ResidentsInfo set FirstName='"+textField_First.getText()+"',"
										+ "LastName='"+textField_Last.getText()+"',"
										+ "Username='"+textField_Username.getText()+"',"
										+ "HouseNumber='"+textField_House.getText()+"',"
										+ "Email='"+textField_Email.getText()+"',"
										+ "Phone='"+textField_Phone.getText()+"',"
										+ "Position='"+position+"'"
										+ "where ResidentID='"+textField_ID.getText()+"' ";
					
						PreparedStatement pst = connection.prepareStatement(query);
				
						pst.execute();
						pst.close();
					}
				}
				
				catch(Exception a) {
					
					a.printStackTrace();
					
				}
				refreshTable();
			
			}
		});
		
		btnEdit.setBounds(619, 773, 299, 53);
		frame.getContentPane().add(btnEdit);
		
		btnNewButton.setForeground(Color.CYAN);
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(922, 103, 340, 725);
		frame.getContentPane().add(btnNewButton);
	
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
		
		//lblNewLabel_Image.setIcon(new ImageIcon(IA.class.getResource("/image/download.png")));
		lblNewLabel_Image.setBackground(Color.cyan);
		lblNewLabel_Image.setBounds(6, 49, 1270, 851);
		frame.getContentPane().add(lblNewLabel_Image);
		
		btnMaintenance.setBounds(15, 10, 165, 45);
		frame.getContentPane().add(btnMaintenance);
		
		try {
			String query = "select FirstName,LastName,Username,HouseNumber,ResidentID,Email,Phone,Position from ResidentsInfo";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));

		}
		
		catch(Exception a) {
			
			a.printStackTrace();
			
		}

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
