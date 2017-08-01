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
	
	public SocketClient(String username) {
		users = new ArrayList<String>();
		users.add(username);
		connect();
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
		out.println(user + ": " + message);
	}
}
