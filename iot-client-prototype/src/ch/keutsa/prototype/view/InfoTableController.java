package ch.keutsa.prototype.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ch.keutsa.prototype.javafxclient.MainIoT;
import ch.keutsa.prototype.model.RegularBundleFXML;

public class InfoTableController {

	@FXML
	private TableView infoTable = new TableView();

	private MainIoT main;

	public InfoTableController() {
	}

	@SuppressWarnings("unchecked")
	@FXML
	private void initialize() {

		TableColumn macAddressColumn = new TableColumn("MacAddress");
		TableColumn ssidColumn = new TableColumn("SSID");
		TableColumn timeColumn = new TableColumn("Client Time");
		TableColumn locationColumn = new TableColumn("Location");
		TableColumn connectionCodeColumn = new TableColumn("Connection Code");

		infoTable.getColumns().addAll(macAddressColumn, ssidColumn, timeColumn,
				locationColumn, connectionCodeColumn);

		macAddressColumn
				.setCellValueFactory(new PropertyValueFactory<RegularBundleFXML, String>(
						"clientMac"));
		ssidColumn
				.setCellValueFactory(new PropertyValueFactory<RegularBundleFXML, String>(
						"clientSSID"));
		timeColumn
				.setCellValueFactory(new PropertyValueFactory<RegularBundleFXML, String>(
						"clientTime"));
		locationColumn
				.setCellValueFactory(new PropertyValueFactory<RegularBundleFXML, String>(
						"clientLocation"));
		connectionCodeColumn
				.setCellValueFactory(new PropertyValueFactory<RegularBundleFXML, String>(
						"connectionCode"));

	}

	@SuppressWarnings("unchecked")
	public void setMain(MainIoT main) {
		this.main = main;
		// TODO: get(dä client, vom aktuelle täb, dä meini)
		infoTable.setItems(main.getStatistics().getClients().get(0)
				.getMqttMessages());

	}
}
