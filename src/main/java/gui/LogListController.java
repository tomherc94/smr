package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import aplication.Main;
import gui.listeners.DataChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Log;
import model.services.LogService;

public class LogListController implements Initializable, DataChangeListener {

	private LogService service;

	@FXML
	private TableView<Log> tableViewLog;

	@FXML
	private TableColumn<Log, Integer> tableColumnId;

	@FXML
	private TableColumn<Log, String> tableColumnIpClient;

	@FXML
	private TableColumn<Log, String> tableColumnDateHour;

	@FXML
	private TableColumn<Log, Double> tableColumnCpuMhz;

	@FXML
	private TableColumn<Log, Long> tableColumnFreeRam;

	@FXML
	private TableColumn<Log, Long> tableColumnFreeSwap;

	@FXML
	private TableColumn<Log, Double> tableColumnDiskUsagePerc;

	private ObservableList<Log> obsList;

	public void setLogService(LogService service) {
		this.service = service;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNode();

	}

	// padrao para iniciar o comportamento das colunas
	private void initializeNode() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnIpClient.setCellValueFactory(new PropertyValueFactory<>("ipClient"));
		tableColumnDateHour.setCellValueFactory(new PropertyValueFactory<>("dateHour"));
		tableColumnCpuMhz.setCellValueFactory(new PropertyValueFactory<>("cpuMhz"));
		tableColumnFreeRam.setCellValueFactory(new PropertyValueFactory<>("freeRam"));
		tableColumnFreeSwap.setCellValueFactory(new PropertyValueFactory<>("freeSwap"));
		tableColumnDiskUsagePerc.setCellValueFactory(new PropertyValueFactory<>("diskUsagePerc"));

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewLog.prefHeightProperty().bind(stage.heightProperty());
	}

	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Log> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewLog.setItems(obsList);
	}

	@Override
	public void onDataChanged() {
		updateTableView();
	}

}
