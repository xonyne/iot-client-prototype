package ch.keutsa.prototype.logic;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import ch.keutsa.prototype.model.RegularBundle;
import ch.keutsa.prototype.model.RegularBundleFXML;

public class StatisticsHelper {

	// TODO NOT void, but?
	public static void getBarChartStatistics(List<RegularBundle> mqttMessages) {
	}

	// TODO NOT void, but?
	public static void getLineChartStatistics(List<RegularBundle> mqttMessages) {
	}

	// TODO NOT void, but?
	public static void getInfoTableStatistics(List<RegularBundle> mqttMessages) {

	}

	public static ObservableList<Data> getPieChartStatistics(
			ObservableList<RegularBundle> mqttMessages) {
		Collections.sort(mqttMessages);
		Map<String, Long> pieData = new HashMap<String, Long>();

		String currentConnection;
		String lastConnection = null;
		Long currentTime = 0l;
		Long lastTime = 0l;

		for (RegularBundle regularBundle : mqttMessages) {
			currentConnection = regularBundle.getConnectionCode().toString();
			currentTime = regularBundle.getClientTime().getTime();

			if (currentConnection.equals(lastConnection))
				continue;
			
			if (lastConnection != null) {
				Long timeSum = pieData.get(lastConnection) != null ? pieData.get(lastConnection):0;
				pieData.put(lastConnection, timeSum + (currentTime - lastTime));
			}

			lastTime = currentTime;
			lastConnection = currentConnection;
		}

		Long beginTime = mqttMessages.get(0).getClientTime().getTime();
		Long endTime = mqttMessages.get(mqttMessages.size() - 1)
				.getClientTime().getTime();
		Long totalTime = endTime - beginTime;

		ObservableList<Data> pieChartData = FXCollections.observableArrayList();
		return pieChartData;
	}
}