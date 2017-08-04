package chat;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

public class ChatBox {
	JFrame frame;
	JTextArea chats;
	JScrollPane scrollPane;
	public ChatBox() {
		frame = new JFrame("Chats");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1500, 1500);
		frame.setVisible(true);
		chats = new JTextArea();
		scrollPane = new JScrollPane(chats);
		chats.setEditable(false);
		chats.setVisible(true);
		frame.add(chats);
		
		Font font;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("./trench.ttf").openStream());
			GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
			genv.registerFont(font);
			font = font.deriveFont(40f);
			chats.setFont(font);
		} catch (FontFormatException | IOException e1) {
			System.out.println("This program made an uh oh and has a boo boo now.");
		}   


		
		chats.setVisible(true);
		Color c = new Color(206, 255, 233);
	    chats.setBackground(c);
	}
	public void addChat(String message) {
		chats.append(message + "\n");
	}
	
	public static void main(String[] args) {
		ChatBox cb = new ChatBox();
		cb.chats.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\n Ut enim ad minim veniam, \nquis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat\\n. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
	}
}
