package chat;

import javax.swing.JOptionPane;

public class InputBox {
	
	public String register() {
		String username = JOptionPane.showInputDialog("Username: ");
		return username;
	}
	
	public String getMessage() {
		String message = JOptionPane.showInputDialog("Message: ");
		return message;
	}
	
}
