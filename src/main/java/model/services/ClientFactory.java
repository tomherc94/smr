package model.services;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import model.entities.Server;

public class ClientFactory extends Thread {

	
	public ClientFactory() {
		
	}
	
	@Override
	public void run() {
		ServerSocket servidor = null;

		try {
			servidor = new ServerSocket(12345);
			System.out.println("Porta 12345 aberta no Servidor " + InetAddress.getLocalHost().getHostAddress());
			System.out.println("Aguardando conexão do cliente...");
			
			while (servidor.isBound()) {
				Socket cliente = servidor.accept();
				System.out.println("Cliente 1");
				// Thread para tratar o cliente conectado
				Server tratamento = new Server(cliente);
				Thread t = new Thread(tratamento);

				// Inicia a thread para o cliente conectado
				t.start();
			}
			servidor.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	} 

}
