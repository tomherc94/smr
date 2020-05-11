package gui;


import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import aplication.Main;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Client;
import model.services.ClienteService;

public class ClienteListController implements Initializable, DataChangeListener {

	private ClienteService service;

	@FXML
	private TableView<Client> tableViewCliente;

	@FXML
	private TableColumn<Client, Integer> tableColumnId;

	@FXML
	private TableColumn<Client, String> tableColumnIp;

	@FXML
	private TableColumn<Client, String> tableColumnSO;

	@FXML
	private TableColumn<Client, String> tableColumnStatus;

	@FXML
	private TableColumn<Client, Client> tableColumnREMOVE;

	private ObservableList<Client> obsList;

	public void setClienteService(ClienteService service) {
		this.service = service;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNode();

	}

	// padrao para iniciar o comportamento das colunas
	private void initializeNode() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnIp.setCellValueFactory(new PropertyValueFactory<>("ip"));
		tableColumnSO.setCellValueFactory(new PropertyValueFactory<>("sysOp"));
		tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));


		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewCliente.prefHeightProperty().bind(stage.heightProperty());
	}

	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Client> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewCliente.setItems(obsList);
		initRemoveButtons();
	}

	@Override
	public void onDataChanged() {
		updateTableView();
	}

	

	private void initRemoveButtons() {
		tableColumnREMOVE.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnREMOVE.setCellFactory(param -> new TableCell<Client, Client>() {
			private final Button button = new Button("remover");

			@Override
			protected void updateItem(Client obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> removeEntity(obj));
			}
		});
	}

	private void removeEntity(Client obj) {
		Optional<ButtonType> result = Alerts.showConfirmation("Confirmacao", "Deseja deletar o item selecionado?");

		if (result.get() == ButtonType.OK) {
			if (service == null) {
				throw new IllegalStateException("Service was null!");
			}
			try {
				service.remove(obj);
				updateTableView();
			} catch (RuntimeException e) {
				Alerts.showAlert("Erro ao remover", null, e.getMessage(), AlertType.ERROR);
			}

		}
	}
}

