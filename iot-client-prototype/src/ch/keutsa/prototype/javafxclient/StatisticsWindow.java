package ch.keutsa.prototype.javafxclient;

import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;

public class StatisticsWindow extends BorderPane {

	ComboBox<String> cboClients;

	public StatisticsWindow() {
		initializeWindow();
	}
	
	private void initializeWindow() {
		cboClients = new ComboBox<String>();
		this.setTop(cboClients);
		BorderPane.setAlignment(cboClients, Pos.CENTER);
		// TODO fill combo box with client names
	}
}
