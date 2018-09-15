package chat;

public class Server {

	public static void main(String[] args) {
		int port = Integer.parseInt(args[0]);
		SocketServer server = new SocketServer(port);
		server.listenSocket();
	}
}