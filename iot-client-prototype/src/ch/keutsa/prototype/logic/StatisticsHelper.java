package ch.keutsa.prototype.logic;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart.Data;
import ch.keutsa.prototype.model.RegularBundle;

public class StatisticsHelper {

	public static ObservableList<Data> getPieChartStatistics(
			ObservableList<RegularBundle> mqttMessages) {
		Collections.sort(mqttMessages);
		Map<String, Long> timeDataConnectionTypes = new HashMap<String, Long>();

		String currentConnection;
		String lastConnection = null;
		Date currentTime = null;
		Date lastTime = null;

		for (RegularBundle regularBundle : mqttMessages) {
			currentConnection = regularBundle.getConnectionCode().toString();
			currentTime = regularBundle.getClientTime();

			if (lastConnection != null) {
				Long timeSum = timeDataConnectionTypes.get(lastConnection) != null ? timeDataConnectionTypes
						.get(lastConnection) : 0;
				timeDataConnectionTypes.put(lastConnection, timeSum
						+ getDateDiff(lastTime, currentTime, TimeUnit.MINUTES));
			}

			lastTime = currentTime;
			lastConnection = currentConnection;
		}

		ObservableList<Data> pieChartData = FXCollections.observableArrayList();
		if (timeDataConnectionTypes.size() != 0) {
			Date beginTime = mqttMessages.get(0).getClientTime();
			Date endTime = mqttMessages.get(mqttMessages.size()-1)
					.getClientTime();
			long totalTime = getDateDiff(beginTime, endTime, TimeUnit.MINUTES);

			for (Entry<String, Long> entry : timeDataConnectionTypes.entrySet()) {
				double connectionPercentage = (100.0 / totalTime) * entry.getValue();
				pieChartData.add(new Data(entry.getKey() + ": "
						+ Math.round(connectionPercentage) + "%", Math
						.round(connectionPercentage)));
			}
		} else {
			pieChartData.add(new Data(lastConnection + ": 100%", 100d));
		}

		return pieChartData;
	}

	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date2.getTime() - date1.getTime();
		return timeUnit.convert(diffInMillies, timeUnit);
	}
}