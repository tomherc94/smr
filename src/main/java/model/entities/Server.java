package model.entities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Server extends Thread {

	private Socket client;

	public Server() {

	}

	public Server(Socket cliente) {
		this.client = cliente;
	}

	@Override
	public void run() {
		System.out.println("Nova conexão com o cliente " + client.getInetAddress().getHostAddress());

		try {
			InputStream in = client.getInputStream();
			do {
				
				InputStreamReader isr = new InputStreamReader(in);
				BufferedReader reader = new BufferedReader(isr);
				String fName = reader.readLine();
				System.out.println(fName);
				File f1 = new File("logs\\logCurrent" + client.getInetAddress().getHostAddress() + ".txt");
				FileOutputStream out = new FileOutputStream(f1, false);
				int tamanho = 1024; // buffer de 4KB
				byte[] buffer = new byte[tamanho];
				int lidos = -1;
				//while ((lidos = in.read(buffer, 0, tamanho)) != -1) { //veriricar essa linha
				lidos = in.read(buffer, 0, tamanho);
					//System.out.println(lidos);
					out.write(buffer, 0, lidos);
					out.flush();
					Thread.sleep(10000);
				//}
				
				//in.close();
				out.close();
			
							
			}while(client.isConnected());
			this.client.close();

			// servidor.close();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}

	}
}
