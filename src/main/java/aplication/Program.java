package aplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Program {

	public static void main(String[] args) {

		try {
			ServerSocket servidor = new ServerSocket(12345);
			System.out.println("Porta 12345 aberta!");

			Socket cliente = servidor.accept();
			System.out.println("Nova conexão com o cliente " + cliente.getInetAddress().getHostAddress());

			InputStream in = cliente.getInputStream();
			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader reader = new BufferedReader(isr);
			String fName = reader.readLine();
			System.out.println(fName);
			File f1 = new File("logs\\" + fName);
			FileOutputStream out = new FileOutputStream(f1);

			int tamanho = 4096; // buffer de 4KB
			byte[] buffer = new byte[tamanho];
			int lidos = -1;
			while ((lidos = in.read(buffer, 0, tamanho)) != -1) {
				System.out.println(lidos);
				out.write(buffer, 0, lidos);
			}
			out.flush();
			out.close();

			
			/*Scanner s = new Scanner(cliente.getInputStream());
			while (s.hasNextLine()) {
				System.out.println(s.nextLine());
			}

			s.close();*/
			servidor.close();
			cliente.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
}
