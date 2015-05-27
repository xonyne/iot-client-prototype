package ch.keutsa.prototype.view;

import ch.keutsa.prototype.javafxclient.MainIoT;
import javafx.fxml.FXML;

public class RootLayoutController {

	private MainIoT main;
	
	@FXML
	public void handleBarChart() {
		main.BarChartLayout();
	}
	
	@FXML
	public void handleInfoTable() {
		main.InfoTableLayout();
	}
	
	@FXML
	public void handlePieChart() {
		main.PieChartLayout();
	}
	
	@FXML
	public void handleLineChart() {
		main.LineChartLayout();
	}
	
    public void setMain(MainIoT main) {
        this.main = main;
    }
}
