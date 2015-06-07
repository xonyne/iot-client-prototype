package ch.keutsa.prototype.javafxclient;

import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ch.keutsa.prototype.logic.ReceiverService;
import ch.keutsa.prototype.model.AndroidClient;
import ch.keutsa.prototype.model.KeutsaStatistics;
import ch.keutsa.prototype.view.BarChartController;
import ch.keutsa.prototype.view.InfoTableController;
import ch.keutsa.prototype.view.LineChartController;
import ch.keutsa.prototype.view.PieChartController;
import ch.keutsa.prototype.view.RootLayoutController;

public class MainIoT extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private ObservableList<AndroidClient> clients = FXCollections
			.observableArrayList();
	KeutsaStatistics statistics;

	public MainIoT() {
		this.statistics = new KeutsaStatistics(clients);
		ReceiverService receiver = new ReceiverService(statistics);
		receiver.listen();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Keutsa Statistics");

		this.primaryStage.getIcons().add(
				new Image("file:resources/images/icon.png"));

		// "default close operation"
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent t) {
				System.exit(0);
			}
		});
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

	public void InfoTableLayout(String string) {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainIoT.class
					.getResource("../view/InfoTable.fxml"));
			BorderPane infoTable = (BorderPane) loader.load();

			rootLayout.setCenter(infoTable);

			InfoTableController controller = loader.getController();

			controller.setMain(this, string);
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

	public static void main(String[] args) {
		launch(args);
	}

	public KeutsaStatistics getStatistics() {
		return statistics;
	}

}
