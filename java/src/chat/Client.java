package chat;

public class Client {

	public static void main(String[] args) {
		new SocketClient(args[0], Integer.parseInt(args[1]));
	}
}