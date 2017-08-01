package chat;

import java.io.BufferedReader;
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
	
	public SocketClient() {
		InputBox inputBox = new InputBox();
		users = new ArrayList<String>();
		username = inputBox.register();
		users.add(username);
		connect();
		while(true) {
			out.println(username + ": " + inputBox.getMessage());
		}
	}
	public void connect() {
		try {
			host = "127.0.0.1";
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
	
	public void chat(String user, String message) {
		
	}
}
