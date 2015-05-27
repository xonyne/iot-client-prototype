package ch.keutsa.prototype.javafxclient;

import java.io.IOException;
import java.util.Date;

import ch.keutsa.prototype.model.basic.RegularBundle;
import ch.keutsa.prototype.model.basic.ConnectionCode;
import ch.keutsa.prototype.model.basic.RegularBundleFXML;
import ch.keutsa.prototype.view.BarChartController;
import ch.keutsa.prototype.view.InfoTableController;
import ch.keutsa.prototype.view.LineChartController;
import ch.keutsa.prototype.view.PieChartController;
import ch.keutsa.prototype.view.RootLayoutController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainIoT extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private ObservableList<RegularBundleFXML> bundles = FXCollections
			.observableArrayList();

	public MainIoT() {
		bundles.add(new RegularBundleFXML("12-b1-c8-30-00-4f", "Test",
				"0.5, 40.8", new Date(), ConnectionCode.OFFLINE.toString()));
		bundles.add(new RegularBundleFXML("12-a3-c7-00-30-5d", "Test2",
				"100.9, 30.1", new Date(), ConnectionCode.MOBILE.toString()));
		bundles.add(new RegularBundleFXML("12-a2-c6-01-20-3g", "Test3",
				"20.7, 400.0", new Date(), ConnectionCode.UNKNOWN.toString()));
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Keutsa Statistics");

		this.primaryStage.getIcons().add(
				new Image("file:resources/images/icon.png"));

		initRootLayout();
	}

	public void initRootLayout() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainIoT.class
					.getResource("../view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			RootLayoutController controller = loader.getController();
			controller.setMain(this);
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void BarChartLayout() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainIoT.class
					.getResource("../view/BarChart.fxml"));
			BorderPane barChart = (BorderPane) loader.load();

			rootLayout.setCenter(barChart);

			BarChartController controller = loader.getController();

			controller.setMain(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void InfoTableLayout() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainIoT.class
					.getResource("../view/InfoTable.fxml"));
			BorderPane infoTable = (BorderPane) loader.load();

			rootLayout.setCenter(infoTable);

			InfoTableController controller = loader.getController();

			controller.setMain(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void PieChartLayout() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainIoT.class
					.getResource("../view/PieChart.fxml"));
			StackPane pieChart = (StackPane) loader.load();

			rootLayout.setCenter(pieChart);

			PieChartController controller = loader.getController();

			controller.setMain(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void LineChartLayout() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainIoT.class
					.getResource("../view/LineChart.fxml"));
			BorderPane lineChart = (BorderPane) loader.load();

			rootLayout.setCenter(lineChart);

			LineChartController controller = loader.getController();

			controller.setMain(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addBundle(RegularBundle regular) {
		bundles.add(new RegularBundleFXML(regular.getClientMac().toString(),
				regular.clientSSID.toString(), regular.clientLocation
						.toString(), regular.getClientTime(),
				regular.getConnectionCode().toString()));
	}

	public ObservableList<RegularBundleFXML> getBundles() {
		return bundles;
	}

	public static void main(String[] args) {

		launch(args);
	}
}
