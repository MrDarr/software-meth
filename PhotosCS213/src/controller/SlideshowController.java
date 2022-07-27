package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SlideshowController {

    @FXML
    void backToAlbum(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/album.fxml"));
		Parent sceneManager = (Parent) loader.load();
		AlbumController albumController = loader.getController();
		Scene adminScene = new Scene(sceneManager);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.setScene(adminScene);
		appStage.show();
    }

    @FXML
    void nextPhoto(ActionEvent event) {

    }

    @FXML
    void prevPhoto(ActionEvent event) {

    }

}

