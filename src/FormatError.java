import javax.swing.JOptionPane;

public class FormatError extends RuntimeException{
	
	public FormatError(String message) {
		
		JOptionPane.showMessageDialog(null, message);

	}
	
	@Override 
    public synchronized Throwable fillInStackTrace() {
        return null;
    }

}


