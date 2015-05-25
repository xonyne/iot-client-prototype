package ch.keutsa.prototype.javafxclient;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import ch.keutsa.prototype.logic.Receiver;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Main extends Application {

	Label lblStatus;

	@Override
	public void start(Stage primaryStage) {
		try {
			Receiver receiver = new Receiver();
			receiver.listen();
					
			BorderPane root = new BorderPane();
			
			lblStatus = new Label("Status");
			lblStatus.setMaxWidth(Double.MAX_VALUE);
			root.setBottom(lblStatus);
			BorderPane.setAlignment(lblStatus, Pos.BOTTOM_LEFT);

			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(
					getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public void updateStatus(String mac){
		lblStatus.setText(new Date().toString() +  ": New message arrived from " + mac + "...");
	}
	
	@Deprecated
	private MQTTBlockingClient initializeMQTTClient() throws IOException {

		final String MQTT_SETTINGS = "mqttSettings.properties";

		Properties prop = new Properties();
		InputStream in = MQTTBlockingClient.class
				.getResourceAsStream(MQTT_SETTINGS);
		prop.load(in);
		in.close();

		// Read MQTT settings
		boolean quietMode = Boolean.valueOf(prop.getProperty("quietMode"));
		String broker = prop.getProperty("broker");
		int port = Integer.valueOf(prop.getProperty("port"));
		boolean cleanSession = Boolean.valueOf(prop.getProperty("cleanSession"));
		boolean ssl = Boolean.valueOf(prop.getProperty("ssl"));
		String password = prop.getProperty("password").toString();
		String userName = prop.getProperty("user");

		String protocol = "tcp://";
		if (ssl) {
			protocol = "ssl://";
		}

		String url = protocol + broker + ":" + port;
		try {
			return new MQTTBlockingClient(url, MqttClient.generateClientId(),
					cleanSession, quietMode, userName, password);
		} catch (MqttException me) {
			System.out.println("reason " + me.getReasonCode());
			System.out.println("msg " + me.getMessage());
			System.out.println("loc " + me.getLocalizedMessage());
			System.out.println("cause " + me.getCause());
			System.out.println("excep " + me);
			me.printStackTrace();
		}
		return null;
	}

}
