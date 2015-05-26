package ch.keutsa.prototype.view;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import ch.keutsa.prototype.javafxclient.MainIoT;
import ch.keutsa.prototype.model.basic.ConnectionCode;

public class LineChartController {

	@FXML 
	final CategoryAxis xAxis = new CategoryAxis();
	@FXML
	final NumberAxis yAxis = new NumberAxis();
	@FXML
	final LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
	
	private MainIoT mainIoT;
	
	@FXML
	public void initialize() {
	
		xAxis.setLabel("Verbindung");
		lineChart.setTitle("Übersicht");
		
		XYChart.Series series = new XYChart.Series();
		series.setName("Verbindungen");
		series.getData().add(new XYChart.Data(1, "Mobile"));
	}
	
	public void setMain(MainIoT mainIoT) {
		this.mainIoT = mainIoT;
	}
}
