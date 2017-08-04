package chat;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class CInterface {
	JTextArea chats;
	JButton send;
	JTextField input;
    JPanel cards;
    final static String BUTTONPANEL = "";
    ButtonListener buttonListener;
    public CInterface() {
    	init();
    	buttonListener = new ButtonListener();
    	send.addActionListener(buttonListener);
    }
    public void modFont(javax.swing.JComponent element) {
    	Font font;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("./trench.ttf").openStream());
			GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
			genv.registerFont(font);
			font = font.deriveFont(40f);
			element.setFont(font);
		} catch (FontFormatException | IOException e1) {
			System.out.println("Could not create GraphicsEnviornment.");
		}
    }
    public void addComponentToPane(Container pane) {
        chats = new JTextArea();
        chats.setPreferredSize(new Dimension(1500, 1500));
        chats.setEditable(false);
        modFont(chats);
		Color c = new Color(206, 255, 233);
	    chats.setBackground(c);
        JPanel sendBar = new JPanel();
        input = new JTextField();
        input.setColumns(50);
        modFont(input);
        sendBar.add(input);
        send = new JButton("Send");
        modFont(send);
        sendBar.add(send);
        
        cards = new JPanel(new CardLayout());
        cards.add(sendBar, BUTTONPANEL);

        pane.add(chats, BorderLayout.PAGE_START);
        pane.add(cards, BorderLayout.CENTER);
    }

    public void init() {
        JFrame frame = new JFrame("Chat Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentToPane(frame.getContentPane());
        frame.getRootPane().setDefaultButton(send);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void addChat(String message) {
		chats.append(message + "\n");
	}

	String returnerVal = null;
	public class ButtonListener implements ActionListener {
        public void actionPerformed(final ActionEvent ev) {
        	if(input.getText() != "") {
				String msg = input.getText();
				input.setText(null);
				returnerVal = msg;
		
			} else {
				returnerVal = null;
			}
        }
    }
	
	public String readChat() {
		if(returnerVal != null) {
			String temp = returnerVal;
			returnerVal = null;
			System.out.println(returnerVal);
			return temp;
		} else {
			return returnerVal;
		}
	}
	//public static void main(String[] args) {
		//create
	//}
}
