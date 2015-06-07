package ch.keutsa.prototype.view;

import java.util.ArrayList;
import ch.keutsa.prototype.javafxclient.MainIoT;
import ch.keutsa.prototype.model.AndroidClient;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;

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
		main.getStatistics().getClients();
		ArrayList<TitledPane> titles = new ArrayList<TitledPane>();
    for (AndroidClient client : main.getStatistics().getClients()) {           

  		Button button1 = new Button("Info Table");
  		button1.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
						main.InfoTableLayout(client.getMacAddress());
				}
			});
    	TitledPane pane = new TitledPane(client.getMacAddress(), button1);
    	titles.add(pane);
  }   
  accordion.getPanes().addAll(titles);
	}
}
