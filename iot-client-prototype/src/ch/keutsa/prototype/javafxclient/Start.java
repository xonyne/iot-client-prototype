package ch.keutsa.prototype.javafxclient;

import ch.keutsa.prototype.logic.Receiver;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		MainWindow mainWindow = new MainWindow();
		Receiver receiver = new Receiver(mainWindow);
		receiver.listen();
		Scene scene = new Scene(mainWindow, 400, 400);
		scene.getStylesheets().add(
				getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();	
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
