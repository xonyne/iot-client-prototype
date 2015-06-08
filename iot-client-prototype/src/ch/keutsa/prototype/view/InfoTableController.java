package ch.keutsa.prototype.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ch.keutsa.prototype.javafxclient.MainIoT;
import ch.keutsa.prototype.model.RegularBundle;

public class InfoTableController {

	@FXML
	private TableView<RegularBundle> infoTable = new TableView<RegularBundle>();

	private MainIoT main;

	public InfoTableController() {
	}

	@SuppressWarnings("unchecked")
	@FXML
	private void initialize() {

		TableColumn<RegularBundle, String> macAddressColumn = new TableColumn<RegularBundle, String>(
				"MacAddress");
		TableColumn<RegularBundle, String> ssidColumn = new TableColumn<RegularBundle, String>(
				"SSID");
		TableColumn<RegularBundle, String> timeColumn = new TableColumn<RegularBundle, String>(
				"Client Time");
		TableColumn<RegularBundle, String> locationColumn = new TableColumn<RegularBundle, String>(
				"Location");
		TableColumn<RegularBundle, String> connectionCodeColumn = new TableColumn<RegularBundle, String>(
				"Connection Code");

		infoTable.getColumns().addAll(macAddressColumn, ssidColumn, timeColumn,
				locationColumn, connectionCodeColumn);

		macAddressColumn
				.setCellValueFactory(new PropertyValueFactory<RegularBundle, String>(
						"clientMac"));
		ssidColumn
				.setCellValueFactory(new PropertyValueFactory<RegularBundle, String>(
						"clientSSID"));
		timeColumn
				.setCellValueFactory(new PropertyValueFactory<RegularBundle, String>(
						"clientTime"));
		locationColumn
				.setCellValueFactory(new PropertyValueFactory<RegularBundle, String>(
						"clientLocation"));
		connectionCodeColumn
				.setCellValueFactory(new PropertyValueFactory<RegularBundle, String>(
						"connectionCode"));

	}

	public void setMain(MainIoT main, String mac) {
		this.main = main;
		updateAccordeonData(mac);
	}

	public void updateAccordeonData(String mac) {
		infoTable.setItems(main.getStatistics().getClientByMacAddress(mac)
				.getMqttMessages());
	}
}
