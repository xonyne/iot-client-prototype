package ch.keutsa.prototype.view;

import ch.keutsa.prototype.javafxclient.MainIoT;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

public class PieChartController {

	@FXML 
	PieChart chart;
	
	private MainIoT mainIoT;
	
	@FXML
	public void initialize() {
		/*
		 * 
		 */
		ObservableList<PieChart.Data> pieChartData =
				FXCollections.observableArrayList(
				new PieChart.Data("Apfel", 20),
				new PieChart.Data("Gurke", 12),
				new PieChart.Data("Tomate", 25),
				new PieChart.Data("Fenchel", 22),
				new PieChart.Data("Radisli", 30));

		chart.setData(pieChartData);
		chart.setTitle("Pie Chart");
		chart.setClockwise(true);
		chart.setLegendVisible(false);
	}

	public void setMain(MainIoT mainIoT) {
		this.mainIoT = mainIoT;
	}
}
