package chat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.ArrayList;

public class ChatBox {
	JFrame frame;
	ArrayList<JLabel> chats;
	
	public ChatBox() {
		frame = new JFrame("Chats");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1500, 1500);
		frame.setVisible(true);
		chats = new ArrayList<JLabel>();
	}
	public void addChat(String message) {
		chats.add(new JLabel(message));
		JLabel label = chats.get(chats.size()-1);
		label.setFont (label.getFont ().deriveFont (20.0f));
		for(int i = 0; i < chats.size(); i++) {
			System.out.println(chats.get(i));
			frame.getContentPane().add(chats.get(i));
		}
	}
}
