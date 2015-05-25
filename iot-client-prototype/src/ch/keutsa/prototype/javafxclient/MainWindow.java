package ch.keutsa.prototype.javafxclient;

import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class MainWindow extends BorderPane {

	Label lblStatus;

	public MainWindow() {
		initializeWindow();
	}
	
	private void initializeWindow() {
		lblStatus = new Label("Log");
		lblStatus.setMaxWidth(Double.MAX_VALUE);
		lblStatus.setMaxHeight(Double.MAX_VALUE);
		this.setBottom(lblStatus);
		BorderPane.setAlignment(lblStatus, Pos.BOTTOM_LEFT);
	}
	
	public void updateStatus(String mac){
		lblStatus.setText(lblStatus.getText() + "\n" + new SimpleDateFormat("HH:mm:ss").format(new Date()) +  " -> New message arrived from " + mac + " ...");
	}
}
