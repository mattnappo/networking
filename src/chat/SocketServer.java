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
	ArrayList<Thread> readers;
	
	public SocketServer() {
		clients = new ArrayList<Connection>();
		msgs = new ArrayList<String>(); // each time a message is recieved, add it to this arrayList
		readers = new ArrayList<Thread>();
		//writing thread in here
		
		Thread writer = new Thread() {
			public void run() {
				System.out.println("writer thread is running");
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
							System.out.println("Could not read");
						}	
					}
					if(queue != null) {
						String msg = queue.poll();
						for(int i = 0; i < clients.size(); i++) {
							clients.get(i).out.println(msg);
						}
						msgsSent++;
					}
				}
			}
		};
		writer.start();
	}

	public Thread newReadThread() {
		Thread read = new Thread() {
			public void run() {
				while(true) {
					try {
						//Read a line from the client
						for(int i = 0; i < clients.size(); i++) {
							String line = clients.get(i).in.readLine();
							if(line!=null) {
								msgs.add(line);
								System.out.println("MSGS: " + msgs);
								clients.get(i).out.println(line);
							}
						}
					} catch (java.io.IOException e) {
						System.out.println("Read failed");
						System.exit(-1);
					}
				}
			}
		};
		return read;
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
		//Creates connection and accepts client
		while(true) {
			try{
			    client = server.accept();
				PrintWriter out = new PrintWriter(client.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				clients.add(new Connection(out, in));
				Thread thread = newReadThread();
				readers.add(thread);
				thread.start();
			    System.out.println("Accepted client");
			    System.out.println("Created IO");
			} catch (java.io.IOException e) {
			    System.out.println("Accept failed: " + port );
			    System.exit(-1);
			}
		}
  
	}
	
}
