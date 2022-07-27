package app;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * Main class where our program will execute
 * 
 * */

public class PhotosApp extends Application {
	
	public Stage mainStage;
	
   
    
    @Override
    public void start(Stage primaryStage) {
		try {
			mainStage = primaryStage;
			
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/loginpage.fxml"));
			AnchorPane root = fxmlLoader.load();

			
			Scene scene = new Scene(root);
			mainStage.setResizable(false);
			mainStage.setTitle("Photo Library");
			mainStage.setScene(scene);
			mainStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    
    
    public static void main(String[] args) {
        launch(args);
    }
}