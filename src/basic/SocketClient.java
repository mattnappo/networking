package basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {	
	
	Socket socket;
	int port;
	String host;
	PrintWriter out;
	BufferedReader in;
	
	public void connect() {
		try {
			host = "127.0.0.1";
			port = 8000;
			socket = new Socket(host, port);
			System.out.println("Client: Connection established");
			
			out = new PrintWriter(socket.getOutputStream(), true);
		    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    System.out.println("Client: Created I/O");
		    
		    out.println("hey");
		    String line = in.readLine(); //Read string from server
		    System.out.println(line);
		    
		} catch (java.net.UnknownHostException e) {
			System.out.println("Unknown host: " + host + ":" + port);
		} catch (java.io.IOException e) {
			System.out.println("No I/O " + port);
		}
		

	}
}
