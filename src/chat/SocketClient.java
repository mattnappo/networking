package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class SocketClient {	
	
	Socket socket;
	int port;
	String host;
	PrintWriter out;
	BufferedReader in;
	String username;
	ArrayList<String> users;
	InputBox inputBox;
	ChatBox chatBox = new ChatBox();
	ArrayList<String> chats;
	
	public SocketClient() {
		inputBox = new InputBox();
		users = new ArrayList<String>();
		chats = new ArrayList<String>();
		username = inputBox.getInput("Username: ");
		users.add(username);
		
		connect();
		
		Thread read = new Thread() {
			public void run() {
				while(true) {
					String line;
					try {
						line = in.readLine();
						chats.add(line);
						chatBox.addChat(line);
					} catch (IOException e) {
						System.out.println("Error reading.");
						System.exit(-1);
					}
				}
			}
		};
		
		
		startChat();

	}
	

	
	public void startChat() {
		while(true) {
			out.println(username + ": " + inputBox.getInput("Message: "));
		}
	}
	public void connect() {
		try {
			host = "localhost";
			port = 8000;
			socket = new Socket(host, port);
			System.out.println("Client: Connection established");
			
			out = new PrintWriter(socket.getOutputStream(), true);
		    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    System.out.println("Client: Created I/O");
		    
		} catch (java.net.UnknownHostException e) {
			System.out.println("Unknown host: " + host + ":" + port);
			System.exit(-1);
		} catch (java.io.IOException e) {
			System.out.println("No I/O " + port);
			System.exit(-1);
		}
	}
	
}
