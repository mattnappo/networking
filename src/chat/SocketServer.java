package chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
	
	Socket client;
	ServerSocket server;
	int port;
	BufferedReader in;
	PrintWriter out;
	
	public void listenSocket(){
		
		//Creates server socket
		try{
			port = 8000;
			server = new ServerSocket(port);
			System.out.println("Server: Socket created");
		} catch (java.io.IOException e) {
			System.out.println("Could not listen on port " + port);
			System.exit(-1);
		}
		
		//Creates connection and accepts client
		try{
		    client = server.accept();
		    System.out.println("Server: Accepted client");
		} catch (java.io.IOException e) {
		    System.out.println("Accept failed: " + port );
		    System.exit(-1);
		}

		//Sets up the input and output streams
		try {
			out = new PrintWriter(client.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			System.out.println("Server: Created I/O");
		} catch(java.io.IOException e) {
			System.out.println("Could not read.");
			System.exit(-1);
		}
		
		//Actually listen for requests
		String line;
		while(true){
	    	try{
	    		//Read a line from the client
	    		line = in.readLine();
	    		if(line!=null) {
	    			out.println(line);
	    		}
	    	} catch (java.io.IOException e) {
	    		System.out.println("Read failed");
	    		System.exit(-1);
	    	}
	    }
	    
	}
	
}
