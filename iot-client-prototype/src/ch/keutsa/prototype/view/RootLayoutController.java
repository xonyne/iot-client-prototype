package ch.keutsa.prototype.view;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import ch.keutsa.prototype.javafxclient.MainIoT;
import ch.keutsa.prototype.model.AndroidClient;

public class RootLayoutController {

	private MainIoT main;

	@FXML
	private Accordion accordion;

	@FXML
	public void handleBarChart() {
		main.BarChartLayout();
	}

	@FXML
	public void handlePieChart() {
		main.PieChartLayout();
	}

	@FXML
	public void handleLineChart() {
		main.LineChartLayout();
	}

	public void setMain(MainIoT main) {
		this.main = main;
		prepareAccordion();
	}

	private void prepareAccordion() {
		for (AndroidClient client : main.getStatistics().getClients()) {
			addTitledPane(client.getMacAddress());
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
								addTitledPane(client.getMacAddress());

							}
						});
					}
				}

			}
		});
	}

	public void addTitledPane(String mac) {
		Button infoTableButton = new Button("Info Table");
		infoTableButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				main.InfoTableLayout(mac);
			}
		});
		accordion.getPanes().add(new TitledPane(mac, infoTableButton));
	}
}
