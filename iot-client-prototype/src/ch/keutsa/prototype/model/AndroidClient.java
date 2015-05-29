package ch.keutsa.prototype.model;

import java.util.ArrayList;
import java.util.List;

public class AndroidClient {
	private List<RegularBundle> mqttMessages;
	private String macAddress;
	
	public AndroidClient(String macAddress) {
		this.mqttMessages = new ArrayList<RegularBundle>();
		this.macAddress = macAddress;
	}
	
	public void addMQTTMessage(RegularBundle message) {
		this.mqttMessages.add(message);
	}
	
	public String getMacAddress(){
		return this.macAddress;
	}
}
