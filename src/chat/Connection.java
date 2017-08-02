package chat;

import java.io.BufferedReader;
import java.io.PrintWriter;
public class Connection {
	
	PrintWriter out;
	BufferedReader in;

	
	public Connection(PrintWriter sendOut, BufferedReader sendIn) {
		out = sendOut;
		in = sendIn;
	}
}
