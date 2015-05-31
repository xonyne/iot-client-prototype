package ch.keutsa.prototype.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import javax.xml.bind.JAXBException;

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
import ch.keutsa.prototype.model.KeutsaStatistics;
import ch.keutsa.prototype.model.RegularBundle;

public class ReceiverService {

	private static final String MESSAGES_FOLDER = "messages";
	private KeutsaStatistics model;

	public ReceiverService(KeutsaStatistics model) {
		this.model = model;
		loadClientMessages();
	};

	private void loadClientMessages() {
		File messagesFolder = new File(MESSAGES_FOLDER);
		if (messagesFolder.exists()) {
			loadRegularBundlesFromFolder(messagesFolder);
		}
	}

	private void loadRegularBundlesFromFolder(final File messagesFolder) {
		for (final File pathToSubfolder : messagesFolder.listFiles()) {

			// get only the folder name from the path
			String clientFolder = pathToSubfolder.toString().split("/")[1];

			// regenerate mac address from folder name
			String macAddress = getMacFromFolderName(clientFolder.toString());

			// generate client object
			AndroidClient client = new AndroidClient(macAddress);
			model.addClient(macAddress, client);

			// load mqtt message for this client
			for (final File xmlFile : pathToSubfolder.listFiles()) {
				InputStream is;
				try {
					is = new FileInputStream(xmlFile);
					client.addMQTTMessage((RegularBundle) XMLHelper
							.loadInstance(is, RegularBundle.class));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (JAXBException e) {
					e.printStackTrace();
				}
			}	
		}
	}

	private String getMacFromFolderName(String folderName) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while (i <= 10) {
			sb.append(folderName.substring(i, i + 2) + ":");
			i += 2;
		}
		return sb.toString().substring(0, sb.length() - 1);
	}

	private String createFolderNameFromMac(String mac) {
		return mac.replace(":", "");
	}

	public ReceiverService(ObservableList<RegularBundle> bundles,
			ObservableList<AndroidClient> clients) {
	}

	public void listen() {

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
								RegularBundle regularBundle = (RegularBundle) SerialHelper
										.fromString(mm.toString());

								// get message information
								String macAddress = regularBundle
										.getClientMac().getMacAsString();
								String folderName = createFolderNameFromMac(macAddress);

								// directory: mac address of the client
								// file name: current date
								Path directory = FileSystems.getDefault()
										.getPath(
												MESSAGES_FOLDER + "/"
														+ folderName);
								String filename = new SimpleDateFormat(
										"yyyyMMddHHmmssS").format(regularBundle
										.getClientTime());

								// create dir if it does not exist
								if (!Files.exists(directory)) {
									new File(directory.toString()).mkdirs();
								}

								// save file
								File outputFile = new File(directory.toString()
										+ "/" + filename + ".xml");
								outputFile.createNewFile();
								XMLHelper.saveInstance(outputFile,
										regularBundle);

								// add message to in memory map
								ObservableMap<String, AndroidClient> existingClients = model
										.getClients();
								if (existingClients.containsKey(macAddress)) {
									existingClients.get(macAddress)
											.addMQTTMessage(regularBundle);
								} else {
									model.addClient(macAddress,
											new AndroidClient(macAddress));
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

		}).start();
		;

	}
}
