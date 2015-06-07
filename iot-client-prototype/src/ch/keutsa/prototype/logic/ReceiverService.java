package ch.keutsa.prototype.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.List;

import javafx.collections.ObservableList;

import javax.xml.bind.JAXBException;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import ch.keutsa.prototype.model.AndroidClient;
import ch.keutsa.prototype.model.KeutsaStatistics;
import ch.keutsa.prototype.model.RegularBundle;
import ch.keutsa.prototype.view.RegularBundleFactory;

public class ReceiverService {

	private static final String MESSAGES_FOLDER = "messages";
	private KeutsaStatistics statistics;

	public ReceiverService(KeutsaStatistics model) {
		this.statistics = model;
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
			String path = pathToSubfolder.toString();
			path = path.replace("\\", "#");
			path = path.replace("/", "#");
			String clientFolder = path.split("#")[1];

			// regenerate mac address from folder name
			String macAddress = getMacFromFolderName(clientFolder.toString());

			// generate client object
			AndroidClient client = new AndroidClient(macAddress);
			statistics.addClient(client);

			// load mqtt message for this client
			for (final File xmlFile : pathToSubfolder.listFiles()) {
				InputStream is;
				try {
					is = new FileInputStream(xmlFile);
					client.addMQTTMessage((RegularBundle) XMLHelper
							.loadInstance(is, RegularBundle.class));
					System.out.println(client);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (JAXBException e) {
					e.printStackTrace();
				}
			}
		}
		if (statistics.getClients().isEmpty())
			statistics.addClient(new AndroidClient("default"));
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
								// System.out.println(string + ";" + mm);

								// deserialize message
								RegularBundle regularBundle = (RegularBundle) SerialHelper
										.fromString(mm.toString());

								// get message information
								String macAddress = regularBundle
										.getClientMac().getMacAsString();
								String folderName = createFolderNameFromMac(macAddress);
								System.out.println("New message from "
										+ macAddress + ".");
								System.out.println(regularBundle);

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
								List<AndroidClient> existingClients = statistics
										.getClients();
								if (existingClients.contains(new AndroidClient(
										macAddress))) {
									int index = existingClients
											.indexOf(new AndroidClient(
													macAddress));
									existingClients.get(index).addMQTTMessage(
											RegularBundleFactory
													.transform(regularBundle));
								} else {
									AndroidClient client = new AndroidClient(
											macAddress);
									client.addMQTTMessage(RegularBundleFactory
											.transform(regularBundle));
									existingClients.add(client);
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
