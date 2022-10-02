import javax.swing.JOptionPane;

public class MissingFields extends Exception{
	
	public MissingFields(String message) {
		super(message);
		try {
			JOptionPane.showMessageDialog(null, message);
		}
		finally {
			JOptionPane.showMessageDialog(null, message);
		}
	}

}

