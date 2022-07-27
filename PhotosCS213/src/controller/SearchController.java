package controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Album;
import model.Photo;
import model.Tag;
import model.User;

public class SearchController {

	@FXML
	private TextField dateFrom;

	@FXML
	private TextField dateTo;

	@FXML
	    private ImageView imageView;

	@FXML
	    private ListView<Photo> listOfPhotos;

	@FXML
	    private TextField newAlbumName;

	@FXML
	private TextField tagName;

	@FXML
	private TextField tagValue;


private User user;
public ArrayList<User> users;

public boolean checked = false ;
public boolean next = false;

public ArrayList<Photo> photoList = new ArrayList<>();
private ObservableList <Photo> obsList;

ListView<Album> a;


public void start (ArrayList<User> users ,User user, ListView<Album> a) {
this.user=user;
this.users= users; 
this.a = a;
ObservableList<Album> obsList = FXCollections.observableArrayList(user.getAlbums());
//System.out.println(obsList);

this.listOfPhotos
.getSelectionModel()
.selectedIndexProperty()
.addListener(
		(obs,oldVal,newVal)->
		display());

}


public void display() {
	if(!listOfPhotos.getSelectionModel().isEmpty()) {
		imageView.setVisible(true);
		Photo photo = listOfPhotos.getSelectionModel().getSelectedItem();
		Image pic = new Image(photo.getFilePath());
		imageView.setImage(pic);
	}
}
@FXML
void backToLibrary(ActionEvent event) throws IOException {

FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/library.fxml"));
Parent sceneManager = (Parent) loader.load();
LibraryController libraryController = loader.getController();

Scene adminScene = new Scene(sceneManager);
Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
libraryController.start(users, user);
appStage.setScene(adminScene);
appStage.show();
}

@FXML
   void createAlbum(ActionEvent event) throws Exception {
	boolean dup = false;
	ArrayList<Album> newestAddition = user.getAlbums();
	Album temp = new Album(newAlbumName.getText());
	if(user.checker(temp)) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Album exists!! CANNOT RENAME!!");
		alert.setContentText("");
		alert.showAndWait();
		dup=true;
	}
	//SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MM/dd/yyyy");
	//Calendar now = Calendar.getInstance();
	if(dup==false) {
		System.out.println("reaches 1");
	
	Album nAlb = new Album(newAlbumName.getText());
	newestAddition.add(nAlb);
	ArrayList<Photo> newAdds = nAlb.getPhoto();
	System.out.println("reaches 2");
	System.out.println(newAdds);
	Photo m;
	
	for(Photo p : listOfPhotos.getItems()) {
		System.out.println("reaches 3");
		nAlb.getPhoto().add(p);
		System.out.println("reaches 4");
		//System.out.println(nAlb.getPhoto().add(p));
		//newAdds.add(p);
		//listOfPhotos.getItems().add(p);
		
		
		
	}
	
	}
	save(users);
	
   }


public boolean dateValid() {
	if(dateTo.getText()!= null && dateFrom.getText()!= null) {
		next = true; // can go to next picture
		return true;
	}else {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(" DATE IS NOT VALID!! PLEASE ENTER A RANGE");
		alert.setContentText("");
		alert.showAndWait();
		return false;
		
		
		
	}
	
	
}
@FXML
void searchDateButton(ActionEvent event) throws ParseException {
	
	SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	ArrayList<Album> listOfAlbums = user.getAlbums();
	
	
	for(Album curr: listOfAlbums) {
		
		
		ArrayList<Photo> listPhotos = curr.getPhoto();
		
		for(Photo now: listPhotos) {
			
			//String[] pDate = now.getDate().getTime().toString().split(" ");
			//System.out.println(now);
			Calendar pDate = now.getDate();
			//System.out.println(pDate);
		
			
			//Date start = dateTimeFormat.parse(dateTo.getText());
			//Date end = dateTimeFormat.parse(dateFrom.getText());
			if(dateValid()==true ) {
				
			//	System.out.println("reaches 1");
				//System.out.println(dateTimeFormat.parse(dateTimeFormat.format(pDate.getTime())).before(dateTimeFormat.parse(dateTo.getText())));
				if(dateTimeFormat.parse(dateTimeFormat.format(pDate.getTime())).before(dateTimeFormat.parse(dateTo.getText()))
						
					&&dateTimeFormat.parse(dateTimeFormat.format(pDate.getTime())).after(dateTimeFormat.parse(dateFrom.getText()))) {
				
				ArrayList<Photo> result = new ArrayList<Photo>();
					result.add(now);
					listOfPhotos.getItems().add(now);
					Image image = new Image(now.getFilePath());
					imageView.setImage(image);
					
					
				}

				
				
				
			}
			
			
			
			
		}
	}
	
	
	
}


//check 
@FXML
void searchTagButton(ActionEvent event) {

	
    String name = tagName.getText();
    String value = tagValue.getText();

    if (name != null || value != null) {
        for (Album albums : this.user.getAlbums()) {
            for (Photo photo2 : albums.getPhoto()) {
                for (Tag tags : photo2.getTag()) {
                    if (tags.getTagName().equals(name)) {
                        listOfPhotos.getItems().addAll(albums.getPhoto());
                        return;

                    }
                    if (tags.getTagValue().equals(value)) {
                        listOfPhotos.getItems().addAll(albums.getPhoto());
                        return;
                    }

                }
            }
        }
    }

    if (name != null && value != null) {

        for (Album albums : this.user.getAlbums()) {
            for (Photo photo2 : albums.getPhoto()) {
                for (Tag tags : photo2.getTag()) {
                    if ((tags.getTagName().equals(name)) && (tags.getTagValue().equals(value))) {
                        listOfPhotos.getItems().addAll(albums.getPhoto());
                        return;
                    }
                }
            }
        }
    }

}

public void save(List<User> users) throws Exception {
	
	try {
		
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("data/data.dat"));
		o.writeObject(users);
		o.close();
		
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}




}

}