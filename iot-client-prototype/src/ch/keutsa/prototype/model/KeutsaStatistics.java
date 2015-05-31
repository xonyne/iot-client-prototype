package ch.keutsa.prototype.model;

import java.util.Iterator;
import java.util.Map.Entry;

import javafx.collections.ObservableMap;

public class KeutsaStatistics {
	ObservableMap<String, AndroidClient> clients;
	
	// TODO put chart data here?

	public KeutsaStatistics(ObservableMap<String, AndroidClient> clients) {
		this.clients = clients;
	}

	public void addClient(String macAddress, AndroidClient client) {
		this.clients.put(macAddress, client);
	}

	public ObservableMap<String, AndroidClient> getClients() {
		return this.clients;
	}

	public void updateStatistic() {
		Iterator<Entry<String, AndroidClient>> it = clients.entrySet()
				.iterator();
		while (it.hasNext()) {
			// TODO recalculate all charts for this client
		}
	}
}
