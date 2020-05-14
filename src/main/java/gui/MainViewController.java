package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import aplication.Main;
import gui.util.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.ClientFactory;
import model.services.ClienteService;
import model.services.LogService;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemInicio;
	
	@FXML
	private MenuItem menuItemCliente;

	@FXML
	private MenuItem menuItemLog;

	@FXML
	private MenuItem menuItemSobre;

	@FXML
	private Button btLigar;
	
	@FXML
	private Button btDesligar;
	
	@FXML
	public void onBtLigarAction(ActionEvent event) {
		Thread factory = new ClientFactory();
		
		factory.start();

	}
	
	@FXML
	public void onBtDesligarAction(ActionEvent event) {
		System.exit(0);
	}
	
	@FXML
	public void onMenuItemInicioAction() {
		loadView("/gui/MainView.fxml", x -> {
		});
	}
	
	@FXML
	public void onMenuItemClienteAction(ActionEvent event) {
		loadView("/gui/ClienteList.fxml", (ClienteListController controller) -> {
			controller.setClienteService(new ClienteService());
			controller.updateTableView();
		});
	}

	@FXML
	public void onMenuItemLogAction() {
		loadView("/gui/LogList.fxml", (LogListController controller) -> {
			controller.setLogService(new LogService());
			controller.updateTableView();
		});
	}

	@FXML
	public void onMenuItemSobreAction() {
		loadView("/gui/SobreView.fxml", x -> {
		});
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}

	protected synchronized <T> void loadView(String absoluteName, Consumer<T> initializeAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();

			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());

			T controller = loader.getController();
			initializeAction.accept(controller);

		} catch (IOException e) {
			Alerts.showAlert("IOException", "Erro ao carregar a página", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
	}

}
