package chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SocketServer {

	Socket client;
	ServerSocket server;
	int port;
	BufferedReader in;
	PrintWriter out;
	ArrayList<Connection> clients;
	ArrayList<String> msgs;
	ArrayList<Thread> readers;

	public SocketServer(int sentPort) {
		port = sentPort;
		clients = new ArrayList<Connection>();
		msgs = new ArrayList<String>(); // each time a message is recieved, add it to this arrayList
		readers = new ArrayList<Thread>();
		//writing thread in here

		Thread writer = new Thread() {
			public void run() {
				int msgsSent = 0;
				ArrayList<String> queue = new ArrayList<String>();
				while(true) {
					try {
						sleep(16);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					if(msgs.size() > msgsSent) {
						for(int i = msgsSent; i < msgs.size(); i++) {
							queue.add(msgs.get(i));
						}
						msgsSent = msgs.size();
					}
					if(queue.size() > 0) {
						String msg = pop(queue);
						for(int i = 0; i < clients.size(); i++) {
							clients.get(i).out.println(msg);
						}

					}
				}
			}
		};
		writer.start();
	}

	public String pop(ArrayList<String> queue) {
		if(queue.size() != 0) {
			String head = queue.get(0);
			queue.remove(0);
			return head;
		} else {
			return "";
		}
	}
	public Thread newReadThread(Connection pClient) {
		Thread read = new Thread() {
			public void run() {
				while(true) {
					try {
						//Read a line from the client

						String line = pClient.in.readLine();
						if(line!=null) {
							msgs.add(line);
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
			server = new ServerSocket(port);
			System.out.println("Server: Socket created");
		} catch (java.io.IOException e) {
			System.out.println("Could not listen on port " + port + " : " + e);
			System.exit(-1);
		}
		//Creates connection and accepts client
		while(true) {
			try{
			    client = server.accept();
				PrintWriter out = new PrintWriter(client.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				Connection newClient = new Connection(out, in);
				clients.add(newClient);
				Thread thread = newReadThread(newClient);
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
