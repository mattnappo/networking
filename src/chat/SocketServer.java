package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class SocketServer {
	
	Socket client;
	ServerSocket server;
	int port;
	BufferedReader in;
	PrintWriter out;
	ArrayList<Connection> clients;
	ArrayList<String> msgs;
	
	public SocketServer() {
		clients = new ArrayList<Connection>();
		msgs = new ArrayList<String>(); // each time a message is recieved, add it to this arrayList
		
		//writing thread in here
		
		Thread writer = new Thread() {
			public void run() {
				int msgsSent = 0;
				PriorityQueue<String> queue = new PriorityQueue<String>();
				while(true) {
					if(msgs.size() > msgsSent) {
						try {
							for(int i = 0; i < clients.size(); i++) {
								String line = clients.get(i).in.readLine();
								queue.add(line);
							}
						} catch (IOException e) {
							System.out.println("Could not read.");
						}
					}
					if(queue != null) {
						String msg = queue.poll();
						for(int i = 0; i < clients.size(); i++) {
							clients.get(i).out.println(msg);
						}
					}
				}
			}
		};
	}
	void newThread() {
		Thread t = new Thread() {
			public void run() {
				
				try {
					out = new PrintWriter(client.getOutputStream(), true);
					in = new BufferedReader(new InputStreamReader(client.getInputStream()));
					System.out.println("Created IO.");
				} catch(java.io.IOException e) {
					System.out.println("Could not create IO.");
					System.exit(-1);
				}		
				
				int msgsSent = 0;
				while(true) {
			    	try{
			    		//Read a line from the client
			    		String line = in.readLine();
			    		if(line!=null) {
			    			if(msgsSent < msgs.size()) {
				    			msgs.add(line);
				    			msgsSent++;
				    			out.println(line);	
			    			}
			    		}
			    	} catch (java.io.IOException e) {
			    		System.out.println("Read failed");
			    		System.exit(-1);
			    	}
			    }
			}
		};
		clients.add(t);
		t.start();
		
	}
	
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
		while(true) {
			//Creates connection and accepts client
			try{
			    client = server.accept();
			    System.out.println("Server: Accepted client");
			    newThread();
			} catch (java.io.IOException e) {
			    System.out.println("Accept failed: " + port );
			    System.exit(-1);
			}
		}
  
	}
	
}
