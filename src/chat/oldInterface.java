package chat;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class oldInterface {

	public static boolean RIGHT_TO_LEFT = false;
	JTextArea chats;
	JTextField input;
	JButton send;
	ButtonListener buttonListener = new ButtonListener();
	public oldInterface() {
		createAndShowGUI();
        send.addActionListener(buttonListener);
	}
    public void addComponentsToPane(Container pane) {
        
        if (!(pane.getLayout() instanceof BorderLayout)) {
            pane.add(new JLabel("Container doesn't use BorderLayout!"));
            return;
        }
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(java.awt.ComponentOrientation.RIGHT_TO_LEFT);
        }
        
        chats = new JTextArea();
        chats.setPreferredSize(new Dimension(1500, 1500));
        chats.setEditable(false);
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
		Color c = new Color(206, 255, 233);
	    chats.setBackground(c);
	    pane.add(chats, BorderLayout.CENTER);
        

		
        
        send = new JButton("Send");
        Font font1;
		try {
			font1 = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("./trench.ttf").openStream());
			GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
			genv.registerFont(font1);
			font1 = font1.deriveFont(40f);
			send.setFont(font1);
		} catch (FontFormatException | IOException e1) {
			System.out.println("This program made an uh oh and has a boo boo now.");
		}
        pane.add(send, BorderLayout.LINE_END);
        
	    input = new JTextField();  
	    Font font3;
		try {
			font3 = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("./trench.ttf").openStream());
			GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
			genv.registerFont(font3);
			font3 = font3.deriveFont(40f);
			input.setFont(font3);
		} catch (FontFormatException | IOException e1) {
			System.out.println("This program made an uh oh and has a boo boo now.");
		}
		
		pane.add(input, BorderLayout.PAGE_END);
        
    }
    
    private void createAndShowGUI() {
        JFrame frame = new JFrame("Chats");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentsToPane(frame.getContentPane());
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
		/*
		 send.addActionListener(new ActionListener() {
		 
			//@Override			
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == send) {
					if(input.getText() != "") {
						String msg = input.getText();
						input.setText(null);
						returnerVal = msg;
				
					} else {
						returnerVal = null;
			   
					}
				}
			}
		});
		if(returnerVal != null) {
			System.out.println(returnerVal);
			return returnerVal;
		} else {
			return returnerVal;
		}
	}
	*/
}