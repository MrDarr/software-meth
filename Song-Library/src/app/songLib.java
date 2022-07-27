package app;
import java.io.IOException;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.songLibController;


public class songLib extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader();   
			loader.setLocation(getClass().getResource("/view/second.fxml"));
			
			// load the fxml
			AnchorPane root = (AnchorPane)loader.load();

			// get the controller (Do NOT create a new Controller()!!)
			// instead, get it through the loader
			songLibController listController = loader.getController();
			listController.start(primaryStage);

			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show(); 
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		// set up FXML loader

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
