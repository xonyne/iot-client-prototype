package ch.keutsa.prototype.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ch.keutsa.prototype.view.RegularBundleFactory;

public class AndroidClient {
	private ObservableList<RegularBundleFXML> mqttMessages;
	private String macAddress;

	// TODO put chart data here?

	public AndroidClient(String macAddress) {
		this.mqttMessages = FXCollections.observableArrayList();
		this.macAddress = macAddress;
	}

	public ObservableList<RegularBundleFXML> getMqttMessages() {
		return mqttMessages;
	}

	public void addMQTTMessage(RegularBundleFXML message) {
		this.mqttMessages.add(message);
	}

	public void addMQTTMessage(RegularBundle message) {
		this.mqttMessages.add(RegularBundleFactory.transform(message));
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

	@Override
	public String toString() {
		return "AndroidClient [macAddress=" + macAddress + "]";
	}

}
