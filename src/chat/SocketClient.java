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
	CInterface inter;
	ArrayList<String> chats;

	public SocketClient() {
		InputBox inputBox = new InputBox();
		inter = new CInterface();
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
						if(chats.size() == 12) {
							chats = new ArrayList<String>();
							inter.clear();
						}
						chats.add(line);
						inter.addChat(line);
					} catch (IOException e) {
						System.out.println("Error reading.");
						System.exit(-1);
					}
				}
			}
		};
		read.start();
		startChat();

	}
	public void startChat() {
		while(true) {
			String rLine = inter.readChat();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(rLine != null) {
				out.println(username + ": " + rLine);
			}
		}
	}
	public void connect() {
		try {
			host = "localhost";
			port = 8000;
			socket = new Socket(host, port);
			System.out.println("Connection established");

			out = new PrintWriter(socket.getOutputStream(), true);
		    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    System.out.println("Created IO");

		} catch (java.net.UnknownHostException e) {
			System.out.println("Unknown host: " + host + ":" + port);
			System.exit(-1);
		} catch (java.io.IOException e) {
			System.out.println("No IO " + port);
			System.exit(-1);
		}
	}
}
