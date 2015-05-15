package ch.keutsa.prototype.javafxclient;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Main extends Application {

	MQTTBlockingClient mqttBlockingClient;

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
					
			Label lblStatus = new Label("Status");
			lblStatus.setMaxWidth(Double.MAX_VALUE);
			BorderPane.setAlignment(lblStatus, Pos.BOTTOM_LEFT);
			root.getChildren().add(lblStatus);
			
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(
					getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			mqttBlockingClient = initializeMQTTClient();
			lblStatus.setText(mqttBlockingClient.isConnected() ? "Verbunden" : "Nich verbunden");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	private MQTTBlockingClient initializeMQTTClient() throws IOException {

		final String MQTT_SETTINGS = "mqttSettings.properties";

		Properties prop = new Properties();
		InputStream in = MQTTBlockingClient.class
				.getResourceAsStream(MQTT_SETTINGS);
		prop.load(in);
		in.close();

		// Read MQTT settings
		boolean quietMode = Boolean.valueOf(prop.get("quietMode").toString());
		String broker = prop.get("broker").toString();
		int port = Integer.valueOf(prop.get("port").toString());
		boolean cleanSession = Boolean.valueOf(prop.get("cleanSession").toString());
		boolean ssl = Boolean.valueOf(prop.get("ssl").toString());
		String password = prop.get("password").toString();
		String userName = prop.get("user").toString();

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
