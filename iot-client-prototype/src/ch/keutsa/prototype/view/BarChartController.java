package ch.keutsa.prototype.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import ch.keutsa.prototype.javafxclient.MainIoT;

public class BarChartController {

	private MainIoT main;

	@FXML
	private CategoryAxis xAxis;

	@FXML
	private NumberAxis yAxis;

	@FXML
	private void initialize() {
		xAxis = new CategoryAxis();
		yAxis = new NumberAxis();

		BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
		barChart.setBarGap(0);
		barChart.setLegendVisible(false);

		ObservableList<XYChart.Data<String, Number>> data = FXCollections
				.observableArrayList();

		for (int i = 0; i < 6; i++) {
			data.add(i, new XYChart.Data<String, Number>("" + (i + 1), 0));
		}

		XYChart.Series<String, Number> series = new XYChart.Series<>(data);
		barChart.getData().add(series);

		data.get(5).setYValue(8);
	}

	public void setMain(MainIoT main) {
		this.main = main;
		// barChart.setItems(main.getBundles());
	}
}
