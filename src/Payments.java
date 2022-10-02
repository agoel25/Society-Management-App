

import java.awt.EventQueue;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;   

public class Payments {

	public JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payments window = new Payments();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection = null; 
	private JTextField textField_Alert;
	private JTextField textField_Search;
	private JTextField textField_Fine;
	
	public void refreshTable() {
		
		try {
			String query = "select * from PaymentsInfo";
			
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			rs.close();
		}
		
		catch(Exception a) {
			
			a.printStackTrace();
			
		}
	}
	
	/**
	 * Create the application.
	 */
	public Payments() {
		connection = sqliteConnection.dbConnector();
		initialize();
	}
	
	public int fineCalc(String PaymentID) {
		
		int fineAmt = 0;
		
		String query = "select * from PaymentsInfo";
		try {
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			Calendar cal = Calendar.getInstance();
		    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
			String dueDate = rs.getString(6);
			java.util.Date dateDue = formatter.parse(dueDate);
			java.util.Date rightNow = cal.getTime();
			long daysDifference = rightNow.getTime() - dateDue.getTime();
			int daysNumber = (int) TimeUnit.DAYS.convert(daysDifference, TimeUnit.MILLISECONDS) +1; 
			
			rs.close();
			    
			    if (dateDue.before(rightNow)) {
			    	fineAmt = (int) (100 * Math.pow((1 + (0.013 / 365)), (365*daysNumber)));
			    	try {			    		
			    		String fine = "Update PaymentsInfo set Fine=? where PaymentID=?";
			
						PreparedStatement pstFine = connection.prepareStatement(fine);
						
						pstFine.setInt(1, fineAmt);
						pstFine.setString(2, PaymentID);
		
						pstFine.execute();
						pstFine.close();
			    	} catch (Exception f) {
			    		f.printStackTrace();
			    	}
			    }
			    else {
			    	fineAmt = 0;
			    }
		}
		catch(Exception a) {
			
			a.printStackTrace();
		}
		return fineAmt; 
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
		
		JLabel lblNewLabel = new JLabel("Monthly Invoice");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Helvetica", Font.PLAIN, 22));
		lblNewLabel.setBounds(20, 63, 198, 30);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.hide();
		
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
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(1073, 408, 161, 26);
		frame.getContentPane().add(dateChooser);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(1072, 457, 161, 26);
		frame.getContentPane().add(dateChooser_1);
		
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
		
		JTextField textField_ID = new JTextField();
		textField_ID.setBounds(1068, 137, 170, 40);
		frame.getContentPane().add(textField_ID);
		textField_ID.setColumns(10);
		textField_ID.hide();
		
		JTextField textField_House = new JTextField();
		textField_House.setColumns(10);
		textField_House.setBounds(1068, 186, 170, 40);
		frame.getContentPane().add(textField_House);
		textField_House.hide();
		
		JTextField textField_Resident = new JTextField();
		textField_Resident.setColumns(10);
		textField_Resident.setBounds(1068, 238, 170, 40);
		frame.getContentPane().add(textField_Resident);
		textField_Resident.hide();
		
		JComboBox<String> comboBox_Status = new JComboBox<String>();
		comboBox_Status.setBackground(Color.WHITE);
		comboBox_Status.setForeground(Color.BLACK);
		comboBox_Status.setBounds(1068, 353, 170, 27);
		frame.getContentPane().add(comboBox_Status);
		comboBox_Status.addItem("      ");
		comboBox_Status.addItem("Pending");
		comboBox_Status.addItem("Completed");
		comboBox_Status.hide();
		
		JComboBox<String> comboBox_Type = new JComboBox<String>();
		comboBox_Type.setBackground(Color.WHITE);
		comboBox_Type.setForeground(Color.BLACK);
		comboBox_Type.setBounds(1068, 299, 170, 27);
		frame.getContentPane().add(comboBox_Type);
		comboBox_Type.addItem("      ");
		comboBox_Type.addItem("Maintenance Bill");
		comboBox_Type.addItem("Electricity Bill");
		comboBox_Type.addItem("Water Bill");

		comboBox_Type.hide();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 105, 890, 734);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter(){
			
			public void mouseClicked(MouseEvent e) {
				
				int SelectedRows = table.getSelectedRow();
				
				String start = table.getModel().getValueAt(SelectedRows, 5).toString();
				String end = table.getModel().getValueAt(SelectedRows, 6).toString();
						
			    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
			    
			    Date due = new Date();
			    Date recieve = new Date();
			    
			    try {
			        due = formatter.parse(start);
			        recieve = formatter.parse(end);
			        
			    } catch (java.text.ParseException p) {
			        p.printStackTrace();
			    }
				
				textField_ID.setText(table.getModel().getValueAt(SelectedRows, 0).toString());
				textField_House.setText(table.getModel().getValueAt(SelectedRows, 1).toString());
				textField_Resident.setText(table.getModel().getValueAt(SelectedRows, 2).toString());
				comboBox_Type.setSelectedItem(table.getModel().getValueAt(SelectedRows, 3).toString());
				comboBox_Status.setSelectedItem(table.getModel().getValueAt(SelectedRows, 4).toString());
				dateChooser.setDate(due);
				dateChooser_1.setDate(recieve);
				textField_Alert.setText(table.getModel().getValueAt(SelectedRows, 7).toString());
			}
			
		});
		scrollPane.setViewportView(table);
		scrollPane.hide();
		table.hide();
		
		JButton btnAdd = new JButton("Add Invoice");
		btnAdd.setForeground(Color.WHITE);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent c) {
				
				frame.dispose();
				AddPayment pays = new AddPayment();
				pays.frame.setVisible(true);
				
			}
		});
		
		btnAdd.setBounds(932, 720, 314, 53);
		frame.getContentPane().add(btnAdd);
		btnAdd.hide();
		
		JLabel lblNewLabel_1 = new JLabel("Invoice Info");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Helvetica", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(1044, 119, 117, 16);
		frame.getContentPane().add(lblNewLabel_1);
		lblNewLabel_1.hide();
		
		JLabel lblPaymentID = new JLabel("Payment ID");
		lblPaymentID.setForeground(Color.WHITE);
		lblPaymentID.setBounds(948, 149, 106, 16);
		frame.getContentPane().add(lblPaymentID);
		lblPaymentID.hide();
		
		JLabel lblHouse = new JLabel("House Number");
		lblHouse.setBounds(948, 197, 106, 16);
		lblHouse.setForeground(Color.WHITE);
		frame.getContentPane().add(lblHouse);
		lblHouse.hide();
		
		JLabel lblResident = new JLabel("Resident");
		lblResident.setBounds(948, 250, 106, 16);
		lblResident.setForeground(Color.WHITE);
		frame.getContentPane().add(lblResident);
		lblResident.hide();
		
		JLabel lblType = new JLabel("Payment Type");
		lblType.setBounds(948, 303, 94, 16);
		lblType.setForeground(Color.WHITE);
		frame.getContentPane().add(lblType);
		lblType.hide();
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(948, 357, 61, 16);
		lblStatus.setForeground(Color.WHITE);
		frame.getContentPane().add(lblStatus);
		lblStatus.hide();
		
		JLabel lblDue = new JLabel("Due Date");
		lblDue.setBounds(948, 412, 61, 16);
		lblDue.setForeground(Color.WHITE);
		frame.getContentPane().add(lblDue);
		lblDue.hide();

		
		JLabel lblReceive = new JLabel("Date Received ");
		lblReceive.setBounds(948, 464, 106, 16);
		lblReceive.setForeground(Color.WHITE);
		frame.getContentPane().add(lblReceive);
		lblReceive.hide();
		
		JLabel lblAlert = new JLabel("Alert");
		lblAlert.setForeground(Color.WHITE);
		lblAlert.setBounds(948, 517, 106, 16);
		frame.getContentPane().add(lblAlert);
		
		JButton btnNewButton_1 = new JButton("Generate Fines");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query = "Select PaymentID from PaymentsInfo";
					PreparedStatement pst = connection.prepareStatement(query);
					
					ResultSet rs = pst.executeQuery();
				
					String ID = rs.getString(1);
					
					fineCalc(ID);
					
					refreshTable();
					
					rs.close();
					
				}catch(Exception s) {
					s.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setBounds(363, 66, 117, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		textField_Alert = new JTextField();
		textField_Alert.setColumns(10);
		textField_Alert.setBounds(1068, 504, 170, 40);
		frame.getContentPane().add(textField_Alert);
		
		textField_Search = new JTextField();
		textField_Search.setBounds(549, 77, 130, 26);
		frame.getContentPane().add(textField_Search);
		
		JLabel lblFine = new JLabel("Fine");
		lblFine.setForeground(Color.WHITE);
		lblFine.setBounds(948, 570, 106, 16);
		frame.getContentPane().add(lblFine);
		
		textField_Fine = new JTextField();
		textField_Fine.setColumns(10);
		textField_Fine.setBounds(1068, 556, 170, 40);
		frame.getContentPane().add(textField_Fine);
		
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
					
					String sql = "select * from PaymentsInfo where PaymentID=? or House=? or Resident=? or Type=? or DueDate=? or Alert=?";
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
					rs.close();
					
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
					
					String status = comboBox_Status.getSelectedItem().toString();
					String type = comboBox_Type.getSelectedItem().toString();
					
				    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					Date due = dateChooser.getDate();
					Date recieve = dateChooser_1.getDate();
					
					String query = "Update PaymentsInfo set PaymentID='"+textField_ID.getText()+"',"
							        + "House='"+textField_House.getText()+"',"
									+ "Resident='"+textField_Resident.getText()+"',"
									+ "Type='"+type+"' , "
									+ "DueDate='"+formatter.format(due)+"',"
									+ "Status='"+status+"', "
									+ "DateReceived='"+formatter.format(recieve)+"',"
									+ "Alert='"+textField_Alert.getText()+"' "
									+ "where PaymentID='"+textField_ID.getText()+"'";
					
					PreparedStatement pst = connection.prepareStatement(query);
				
					pst.execute();
					
					refreshTable();
					
					pst.close();
				}
				
				catch(Exception a) {
					
					a.printStackTrace();
					
				}
			
			}
		});
		
		btnEdit.setBounds(932, 644, 314, 53);
		frame.getContentPane().add(btnEdit);
		btnEdit.hide();
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setForeground(Color.CYAN);
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(915, 94, 340, 740);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.hide();
	
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
		
		JButton btnNewButton_PDF = new JButton("Generate PDF");
		btnNewButton_PDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
				String path = "";
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int x = fileChooser.showSaveDialog(btnNewButton_PDF);
				
				if (x == JFileChooser.APPROVE_OPTION) {
					path = fileChooser.getSelectedFile().getPath();
				}
				
				Document doc = new Document();
				
				PdfWriter.getInstance(doc, new FileOutputStream(path));
				
				doc.open();
				
				int SelectedRows = table.getSelectedRow();
				
				String ID = table.getModel().getValueAt(SelectedRows, 0).toString();
				String House = table.getModel().getValueAt(SelectedRows, 1).toString();
				String Resident = table.getModel().getValueAt(SelectedRows, 2).toString();
				String Type = table.getModel().getValueAt(SelectedRows, 3).toString();
				String Status = table.getModel().getValueAt(SelectedRows, 4).toString();
				String Due = table.getModel().getValueAt(SelectedRows, 5).toString();
				String Rec = table.getModel().getValueAt(SelectedRows, 6).toString();
				String Alert = table.getModel().getValueAt(SelectedRows, 7).toString();
				String Fine = table.getModel().getValueAt(SelectedRows, 8).toString();
				
				Paragraph title = new Paragraph("PAYMENT INFORMATION");
				Paragraph para = new Paragraph("*************************************************************************************************\n");
				Paragraph paraID = new Paragraph("                                 Payment ID =  " + ID + "\n");
				Paragraph para1 = new Paragraph("*************************************************************************************************\n");
				Paragraph paraHouse = new Paragraph("                                 House Number =  " + House + "\n");
				Paragraph para2 = new Paragraph("*************************************************************************************************\n");
				Paragraph paraResident = new Paragraph("                                 Resident Name =  " + Resident + "\n");
				Paragraph para3 = new Paragraph("*************************************************************************************************\n");
				Paragraph paraType = new Paragraph("                                 Payment Type =  " + Type + "\n");
				Paragraph para4 = new Paragraph("*************************************************************************************************\n");
				Paragraph paraStatus = new Paragraph("                                 Status =  " + Status + "\n");
				Paragraph para5 = new Paragraph("*************************************************************************************************\n");
				Paragraph paraDue = new Paragraph("                                 DueDate =  " + Due + "\n");
				Paragraph para6 = new Paragraph("*************************************************************************************************\n");
				Paragraph paraRec = new Paragraph("                                 Date Recieved =  " + Rec + "\n");
				Paragraph para7 = new Paragraph("*************************************************************************************************\n");
				Paragraph paraAlert = new Paragraph("                                 Alert =  " + Alert + "\n");
				Paragraph para8 = new Paragraph("*************************************************************************************************\n");
				Paragraph paraFine = new Paragraph("                                 Fine Amount =  " + Fine + "\n");
				Paragraph para9 = new Paragraph("*************************************************************************************************\n");
				
				doc.add(title);
				doc.add(para);
				doc.add(paraID);
				doc.add(para1);
				doc.add(paraHouse);
				doc.add(para2);
				doc.add(paraResident);
				doc.add(para3);
				doc.add(paraType);
				doc.add(para4);
				doc.add(paraStatus);
				doc.add(para5);
				doc.add(paraDue);
				doc.add(para6);
				doc.add(paraRec);
				doc.add(para7);
				doc.add(paraAlert);
				doc.add(para8);
				doc.add(paraFine);
				doc.add(para9);

				doc.close();
				
				}
				
				catch (Exception pdf){
					pdf.printStackTrace();
				}
			}
		});
		btnNewButton_PDF.setForeground(Color.WHITE);
		btnNewButton_PDF.setBounds(230, 66, 117, 29);
		frame.getContentPane().add(btnNewButton_PDF);
		
		JLabel lblNewLabel_Image = new JLabel("");
		//lblNewLabel_Image.setIcon(new ImageIcon(IA.class.getResource("/image/download.png")));
		lblNewLabel_Image.setBackground(Color.cyan);
		lblNewLabel_Image.setBounds(0, 0, 1270, 851);
		frame.getContentPane().add(lblNewLabel_Image);
		
		btnMaintenance.setBounds(15, 10, 165, 45);
		frame.getContentPane().add(btnMaintenance);
		
		lblNewLabel.show();
		textField_ID.show();
		textField_House.show();
		textField_Resident.show();
		comboBox_Status.show();
		comboBox_Type.show();
		scrollPane.show();
		table.show();
		btnAdd.show();
		btnEdit.show();
		lblNewLabel_1.show();
		lblPaymentID.show();
		lblHouse.show();
		lblResident.show();
		lblType.show();
		lblStatus.show();
		lblDue.show();
		lblReceive.show();
		btnNewButton.show();
		
		try {
			String query = "select * from PaymentsInfo";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();

		}
		
		catch(Exception a) {
			
			a.printStackTrace();
			
		}
		JButton btnPayments = new JButton("Invoice");
		btnPayments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel.show();
				textField_ID.show();
				textField_House.show();
				textField_Resident.show();
				dateChooser.show();
				comboBox_Status.show();
				dateChooser_1.show();
				comboBox_Type.show();
				scrollPane.show();
				table.show();
				btnAdd.show();
				btnEdit.show();
				lblNewLabel_1.show();
				lblPaymentID.show();
				lblHouse.show();
				lblResident.show();
				lblType.show();
				lblStatus.show();
				lblDue.show();
				lblReceive.show();
				
				try {
					String query = "select * from PaymentsInfo";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					rs.close();
				}
				
				catch(Exception a) {
					
					a.printStackTrace();
					
				}
				  
			}
		});
		
		btnPayments.setFont(new Font("Helvetica", Font.PLAIN, 16));
		btnPayments.setForeground(Color.white);
		btnPayments.setBackground(Color.black);
		btnPayments.setBounds(195, 10, 165, 45);
		frame.getContentPane().add(btnPayments);
		
	}
}
