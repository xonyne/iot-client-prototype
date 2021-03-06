package ch.keutsa.prototype.model;

import java.util.List;

public class KeutsaStatistics {
	List<AndroidClient> clients;

	public KeutsaStatistics(List<AndroidClient> clients) {
		this.clients = clients;
	}

	public void addClient(AndroidClient client) {
		this.clients.add(client);
	}

	public List<AndroidClient> getClients() {
		return this.clients;
	}

	public AndroidClient getClientByMacAddress(String mac) {
		for (AndroidClient client : clients) {
			if (client.getMacAddress().equals(mac)) {
				return client;
			}
		}
		return null;
	}

}
