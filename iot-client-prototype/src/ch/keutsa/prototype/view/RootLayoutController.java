package ch.keutsa.prototype.view;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import ch.keutsa.prototype.javafxclient.MainIoT;
import ch.keutsa.prototype.model.AndroidClient;

public class RootLayoutController {

	private MainIoT main;

	@FXML
	private Accordion accordion;

	public void setMain(MainIoT main) {
		this.main = main;
		prepareAccordion();
	}

	private void prepareAccordion() {
		for (AndroidClient client : main.getStatistics().getClients()) {
			addTitledPane(client);
		}

		main.getClients().addListener(new ListChangeListener<AndroidClient>() {

			@Override
			public void onChanged(
					javafx.collections.ListChangeListener.Change<? extends AndroidClient> c) {
				while (c.next()) {
					for (AndroidClient client : c.getAddedSubList()) {
						Platform.runLater(new Runnable() {

							@Override
							public void run() {
								addTitledPane(client);

							}
						});
					}
				}

			}
		});
	}

	public void addTitledPane(AndroidClient client) {
		String mac = client.getMacAddress();

		Button infoTableButton = new Button("Info Table");
		infoTableButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				main.InfoTableLayout(mac);
			}
		});

		Button pieChartButton = new Button("Pie Chart");
		pieChartButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				client.recalculateStatistics();
				main.PieChartLayout(mac);
			}
		});

		Button lineChartButton = new Button("Line Chart");
		lineChartButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				main.LineChartLayout(mac);
			}
		});

		VBox vbox = new VBox(10, infoTableButton, pieChartButton,
				lineChartButton);
		vbox.setPrefWidth(Double.MAX_VALUE);
		vbox.setAlignment(Pos.TOP_CENTER);

		accordion.getPanes().add(new TitledPane(mac, vbox));
	}

	@FXML
	public void exitApplication(final ActionEvent event) {
		System.exit(0);
	}

	@FXML
	public void showAbout(final ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About");
		alert.setHeaderText("Iot App BFH Prog2 FS2015");
		alert.setContentText("Sabine Zumstein\nMichel Utz\nKevin Suter");

		alert.showAndWait();
	}

}
