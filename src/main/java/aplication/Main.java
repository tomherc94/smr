package aplication;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import model.entities.Server;

public class Main extends Application {

	private static Scene mainScene;

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			ScrollPane scrollPane = loader.load();

			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);

			mainScene = new Scene(scrollPane);
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("Sistema de Monitoramento de Redes");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Scene getMainScene() {
		return mainScene;
	}

	public static void main(String[] args) {
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
		launch(args);
	}
}
