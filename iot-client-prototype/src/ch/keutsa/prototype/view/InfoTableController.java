package ch.keutsa.prototype.view;


import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ch.keutsa.prototype.javafxclient.MainIoT;
import ch.keutsa.prototype.model.RegularBundle;
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

	@SuppressWarnings("unchecked")
	public void setMain(MainIoT main, String mac) {
		this.main = main;
		updateAccordeonData(mac);
	}

	@SuppressWarnings("unchecked")
	public void updateAccordeonData(String mac) {
		infoTable.setItems(main.getStatistics().getClientByMacAddress(mac).getMqttMessages());
	}
}
