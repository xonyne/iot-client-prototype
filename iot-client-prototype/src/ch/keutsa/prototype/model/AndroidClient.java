package ch.keutsa.prototype.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import ch.keutsa.prototype.logic.RegularBundleFactory;
import ch.keutsa.prototype.logic.StatisticsHelper;

public class AndroidClient {
	private ObservableList<RegularBundle> mqttMessages;
	private ObservableList<PieChart.Data> pieChartData;
	private String macAddress;

	public AndroidClient(String macAddress) {
		this.mqttMessages = FXCollections.observableArrayList();
		this.macAddress = macAddress;
	}

	public void addMQTTMessage(RegularBundle message) {
		this.mqttMessages.add(message);
		recalculateStatistics();
	}

	public ObservableList<Data> getPieChartData() {
		return this.pieChartData;
	}

	public ObservableList<RegularBundle> getMqttMessages() {
		return mqttMessages;
	}

	public String getMacAddress() {
		return this.macAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((macAddress == null) ? 0 : macAddress.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AndroidClient other = (AndroidClient) obj;
		if (macAddress == null) {
			if (other.macAddress != null)
				return false;
		} else if (!macAddress.equals(other.macAddress))
			return false;
		return true;
	}

	// private helper methods
	private void recalculateStatistics() {
		pieChartData = StatisticsHelper.getPieChartStatistics(mqttMessages);
	}
	
	@Override
	public String toString() {
		return "AndroidClient [macAddress=" + macAddress + "]";
	}

}
