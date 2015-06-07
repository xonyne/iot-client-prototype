package ch.keutsa.prototype.view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import ch.keutsa.prototype.javafxclient.MainIoT;
import ch.keutsa.prototype.model.RegularBundleFXML;

;

public class LineChartController {

	@FXML
	private CategoryAxis yAxis;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private LineChart<String, String> lineChart;

	private MainIoT mainIoT;

	private XYChart.Series<String, String> series1 = new XYChart.Series<String, String>();

	@FXML
	public void initialize() {

		lineChart.setTitle("Overview");

		// TODO Nach Datum Serien erfassen
		// TODO Date wäre auch möglich - Date-Time, nach ConnectionCode
		// sortiert?

	}

	public void setMain(MainIoT mainIoT) {
		this.mainIoT = mainIoT;
		// prepareData(mainIoT.getBundles());
	}

	public void prepareData(ObservableList<RegularBundleFXML> bundles) {

		// SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		// for (RegularBundleFXML rb : mainIoT.getBundles()) {
		// series1.getData().add(new XYChart.Data<String,
		// String>(dateFormat.format(rb.clientTime),
		// rb.connectionCode.getValue()));
		// }
		// lineChart.getData().add(series1);
		//
		// SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		// series1.setName(formatter.format(mainIoT.getBundles().get(1).clientTime));
		// //TODO: getClientNames()
		//
		// XYChart.Series<String, String> series2 = new XYChart.Series<String,
		// String>();
		// series2.setName("Android2");
		// lineChart.getData().add(series2);
	}
}
