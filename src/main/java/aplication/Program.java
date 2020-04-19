package aplication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {

		try {
			ServerSocket servidor = new ServerSocket(12345);
			System.out.println("Porta 12345 aberta!");

			Socket cliente = servidor.accept();
			System.out.println("Nova conexão com o cliente " + cliente.getInetAddress().getHostAddress());

			Scanner s = new Scanner(cliente.getInputStream());
			while (s.hasNextLine()) {
				System.out.println(s.nextLine());
			}

			s.close();
			servidor.close();
			cliente.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
}
