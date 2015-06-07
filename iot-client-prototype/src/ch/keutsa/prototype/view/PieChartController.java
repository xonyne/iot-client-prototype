package ch.keutsa.prototype.view;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import ch.keutsa.prototype.javafxclient.MainIoT;

public class PieChartController {

	@FXML
	PieChart chart;

	private MainIoT main;

	@FXML
	public void initialize() {
		chart.setTitle("Pie Chart");
		chart.setClockwise(true);
		chart.setLegendVisible(false);
	}

	@SuppressWarnings("unchecked")
	public void setMain(MainIoT main, String string) {
		this.main = main;
		chart.setData(main.getStatistics().getClientByMacAddress(string).getPieChartData());
	}

}
