package chat;

//import javax.swing.JDialog;
//import javax.swing.JLabel;
import javax.swing.JOptionPane;
//import javax.swing.JTextArea;

public class InputBox {
	
	//JOptionPane box;
	
	public String getInput(String prompt) {
		String inp = JOptionPane.showInputDialog(prompt);
		return inp;
		/*
		box = new JOptionPane();
		JTextArea textarea = new JTextArea();
		JLabel label = new JLabel(prompt);
		box.add(textarea);
		box.add(label);
		return textarea.getText();
		
		*/
	}
		
}
