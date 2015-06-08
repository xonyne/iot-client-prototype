package ch.keutsa.prototype.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import ch.keutsa.prototype.javafxclient.MainIoT;
import ch.keutsa.prototype.model.AndroidClient;
import ch.keutsa.prototype.model.RegularBundle;

;

public class LineChartController {

	@FXML
	private CategoryAxis yAxis;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private LineChart<String, String> lineChart;
	@FXML
	private Button otherClients;

	private XYChart.Series<String, String> series1 = new XYChart.Series<String, String>();

	@FXML
	public void initialize() {

		lineChart.setTitle("Overview");
	}

	public void setMain(MainIoT mainIoT, String mac) {
		prepareData(mainIoT.getStatistics().getClientByMacAddress(mac));
	}

	public void prepareData(AndroidClient client) {
		series1.setName(client.toString());
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		List<RegularBundle> data = new ArrayList<RegularBundle>(
				client.getMqttMessages());

		Collections.sort(data, new Comparator<RegularBundle>() {
			@Override
			public int compare(RegularBundle o1, RegularBundle o2) {
				return o1.clientTime.compareTo(o2.clientTime);
			}
		});
		for (RegularBundle bundleSorted : data) {
			series1.getData().add(
					new XYChart.Data<String, String>(dateFormat
							.format(bundleSorted.clientTime), bundleSorted
							.getConnectionCode().toString()));
		}
		lineChart.getData().add(series1);
	}
}
