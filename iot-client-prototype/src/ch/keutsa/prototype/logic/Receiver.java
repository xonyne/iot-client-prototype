package ch.keutsa.prototype.logic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import ch.keutsa.prototype.model.RegularBundle;

public class Receiver {

	public void listen()  {
		
		new Thread(new Runnable() {

			@Override
			public void run() {

				MqttClient mqttClient = null;;
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
								RegularBundle regularBundle = (RegularBundle)SerialHelper.fromString(mm.toString());
								String folder = regularBundle.getClientMac().getMac().replace(":", "");
								Path path = FileSystems.getDefault().getPath("messages" + "/" + folder);
								if (!Files.exists(path)) {
								    new File(path.toString()).mkdirs();
								}
								String filename = new SimpleDateFormat("yyyyMMddHHmmssS").format(regularBundle.getClientTime());
								File outputFile = new File(path.toString() + "/" + filename +".xml");
								outputFile.createNewFile();
								XMLHelper.saveInstance(outputFile, regularBundle);
								
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
