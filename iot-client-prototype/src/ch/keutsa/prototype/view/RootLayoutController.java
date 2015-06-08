package ch.keutsa.prototype.view;

import java.util.ArrayList;

import ch.keutsa.prototype.javafxclient.MainIoT;
import ch.keutsa.prototype.model.AndroidClient;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class RootLayoutController {

	private MainIoT main;

	@FXML
	private Accordion accordion;

	@FXML
	public void handleBarChart() {
		main.BarChartLayout();
	}

	public void setMain(MainIoT main) {
		this.main = main;
		ArrayList<TitledPane> titles = new ArrayList<TitledPane>();
		
		for (AndroidClient client : main.getStatistics().getClients()) {

			Button button1 = new Button("Info Table");
			button1.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					main.InfoTableLayout(client.getMacAddress());
				}
			});

			Button button2 = new Button("Pie Chart");
			button2.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					client.recalculateStatistics();
					main.PieChartLayout(client.getMacAddress());
				}
			});
			
			Button button3 = new Button("Line Chart");
			button3.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					main.LineChartLayout(client.getMacAddress());
				}
			});
			
			VBox vbox = new VBox(10,button1,button2,button3);
			vbox.setPrefWidth(Double.MAX_VALUE);	
			vbox.setAlignment(Pos.TOP_CENTER);
			
			TitledPane pane = new TitledPane(client.getMacAddress(),vbox);

			titles.add(pane);
		}
		accordion.getPanes().addAll(titles);
	}
}
