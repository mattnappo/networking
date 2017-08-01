package chat;

import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your username:");
		String username = scanner.next();
		SocketClient client = new SocketClient(username);
		
		
		while(true) {
			String message = "";
			System.out.println("Message: ");
			message = scanner.nextLine();
			System.out.println("message: " + message);
			client.chat(username, message);
		}
	}
}