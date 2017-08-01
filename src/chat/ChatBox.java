package chat;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Font;

public class ChatBox {
	JFrame frame;
	JTextArea chats;
	JScrollPane scrollPane;
	public ChatBox() {
		frame = new JFrame("Chats");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1500, 1500);
		frame.setVisible(true);
		chats = new JTextArea(5, 20);
		scrollPane = new JScrollPane(chats);
		chats.setEditable(false);
		chats.setVisible(true);
		frame.add(chats);
		chats.setFont(new Font("Serif", Font.PLAIN, 30));
		chats.setVisible(true);
	}
	public void addChat(String message) {
		chats.append(message + "\n");
	}
}
