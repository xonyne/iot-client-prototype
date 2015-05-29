package ch.keutsa.prototype.logic;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.layout.BorderPane;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import ch.keutsa.prototype.model.AndroidClient;
import ch.keutsa.prototype.model.RegularBundle;

public class Receiver {
	
	private ObservableMap<String, AndroidClient> clients;
	
	public Receiver(ObservableMap<String, AndroidClient> clients) {
		this.clients = clients;
		loadClientMessages();
	};
	
	private void loadClientMessages() {
		// TODO Load existing xml messages from file system
	}

	public Receiver(ObservableList<RegularBundle> bundles, ObservableList<AndroidClient> clients){}
	
	public void listen()  {
		
		new Thread(new Runnable() {

			@Override
			public void run() {

				MqttClient mqttClient = null;
				try {
					mqttClient = new MqttClient("tcp://iot.eclipse.org:1883",
							"ch.bfh.fbi.utzum1.listener");
					mqttClient.setCallback(new MqttCallback() {

						public void connectionLost(Throwable throwable) {
						}

						public void messageArrived(String string, MqttMessage mm)
								throws Exception {

							try {
								System.out.println(string + ";" + mm);
								
								// deserialize message
								RegularBundle regularBundle = (RegularBundle)SerialHelper.fromString(mm.toString());
								
								// get message information
								String macAddress = regularBundle.getClientMac().getMacAsString().replace(":", "");
								
								// save message to xml
								String date = new SimpleDateFormat("yyyyMMddHHmmssS").format(regularBundle.getClientTime());
								Path directory = FileSystems.getDefault().getPath("messages" + "/" + macAddress);
								if (!Files.exists(directory)) {
								    new File(directory.toString()).mkdirs();
								}
								File outputFile = new File(directory.toString() + "/" + date +".xml");
								outputFile.createNewFile();
								XMLHelper.saveInstance(outputFile, regularBundle);
								
								// add message to in memory list
								if(clients.containsKey(macAddress)) {
									clients.get(macAddress).addMQTTMessage(regularBundle);
								} else {
									clients.put(macAddress, new AndroidClient(macAddress));
								}
								
							} catch (Exception e) {
								e.printStackTrace();
							}
						}

						public void deliveryComplete(IMqttDeliveryToken imdt) {
						}
					});
					MqttConnectOptions options = new MqttConnectOptions();
					options.setCleanSession(false);
					mqttClient.connect(options);
					mqttClient.subscribe("ch.keutsa.prototype");
					System.in.read();
				} catch (MqttException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
		}).start();;
		
	}
}
