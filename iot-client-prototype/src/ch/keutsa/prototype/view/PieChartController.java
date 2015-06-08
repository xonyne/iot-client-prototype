package ch.keutsa.prototype.view;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import ch.keutsa.prototype.javafxclient.MainIoT;

public class PieChartController {

	@FXML
	PieChart chart;

	@FXML
	public void initialize() {
		chart.setTitle("Percentage per connection type");
		chart.setClockwise(true);
		chart.setLegendVisible(true);
	}

	public void setMain(MainIoT main, String string) {
		chart.setData(main.getStatistics().getClientByMacAddress(string)
				.getPieChartData());
	}

}
