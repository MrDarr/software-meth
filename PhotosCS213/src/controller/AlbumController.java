package controller;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Album;
import model.Photo;
import model.User;

public class AlbumController {

    @FXML
    private Text albumName;

    @FXML
    private Text photoCap;
    @FXML
    private Text photoDate;
    @FXML
    private ImageView imageSlideShow;

    @FXML
    private ListView<Photo> listOfPhotos;
    
    @FXML
    private TextField moveTo;
    
    private ObservableList<Photo> obsList;
    
    private Album selectedAlbum;
    public ArrayList<Album> albumList;
    
    
    private User user;
    public ArrayList<User> currentUsers;
    
    private Photo photo;
    public ArrayList<Photo> photoList = new ArrayList<>();
    
    
    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MM/dd/yyyy");
    
    public void start(Album selectedAlbum, User user2, ArrayList<User> users, ArrayList<Photo> photosList){
    this.photoList = photosList;
    obsList = FXCollections.observableArrayList(this.photoList);
    this.listOfPhotos.setItems(obsList);
      //displaySmall();
    this.selectedAlbum=selectedAlbum;
    this.currentUsers = users;
    this.user=user2;
    
    
    albumName.setText(selectedAlbum.getAlbum());
    
    if(!photosList.isEmpty()) {
    listOfPhotos.getSelectionModel().select(0);
    }
    
    displaySmall();
    
   
//     obsList = FXCollections.observableArrayList(selectedAlbum.getPhoto());
//     listOfPhotos.setItems(obsList);
    
    
//     listOfPhotos.setCellFactory(new Callback<ListView<Photo>, ListCell<Photo>>(){
//     public ListCell<Photo> call(ListView<Photo> photosList){
//     return new ListCell<Photo>();
//     }
//     
//     
//     });
//     
//     listOfPhotos.setItems(FXCollections.observableArrayList(selectedAlbum.getPhoto()));
//     listOfPhotos.getSelectionModel().select(0);
    //adds the album names in an array List of strings 
//     ArrayList<String> names = new ArrayList<String>();
//     
//     
//     ArrayList<Album> allAlbums = user2.getAlbums();
//     for(Album current: allAlbums) {
//     names.add(current.getName());
//     }
    
    
    this.listOfPhotos
    .getSelectionModel()
    .selectedIndexProperty()
    .addListener(
    (obs,oldVal,newVal)->
    display());
    
    
    
    this.listOfPhotos.getSelectionModel().selectedItemProperty().addListener((obs,oldVal,newVal) -> details());

      }

    
    public void displaySmall() {
    listOfPhotos.setCellFactory(listOfPhotos -> new ListCell<Photo>() {
    private ImageView imageView = new ImageView();
    @Override
    public void updateItem(Photo photo, boolean empty) {
    super.updateItem(photo, empty);
    if(empty ) {
    setText(null);
    setGraphic(null);
    }else {
    Image image = new Image(photo.getFilePath());
    imageView.setFitHeight(50);
    imageView.setFitWidth(70);
    imageView.setImage(image);
    setGraphic(imageView);
    }
    }
    });
}
    
    
    public void display() {
    if(!listOfPhotos.getSelectionModel().isEmpty()) {
    imageSlideShow.setVisible(true);
    Photo photo = listOfPhotos.getSelectionModel().getSelectedItem();
    //System.out.println(photo.getFilePath());
    Image pic = new Image(photo.getFilePath());
    imageSlideShow.setImage(pic);
    
    	}
    }
    
    public void details() {
    	Photo ph = listOfPhotos.getSelectionModel().getSelectedItem();
    	if(ph!=null) {
    		
    	
    	//Photo ph = listOfPhotos.getSelectionModel().getSelectedItem();
    	Calendar dat = selectedAlbum.getPhoto().get(0).getDate();
    	String m = dateTimeFormat.format(dat.getTime());
    	photoDate.setText("Date Span \t: " + m);
    	
    	String caption = ph.getCaption();
    	photoCap.setText("Caption: " + caption);
    	}
    }
    
    @FXML
    void addPhotoButton(ActionEvent event) throws Exception {
    
    	boolean dup = false;
    FileChooser fileChooser = new FileChooser();
    
    FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("pictures", "*.png", "*.jpg", "*.bmp", "*.jpeg", "*.gif");
    fileChooser.getExtensionFilters().add(filter);
    fileChooser.setTitle("Add photo");
    
    File file = fileChooser.showOpenDialog(null);
    
    if(file!=null) {
    Image image = new Image(file.toURI().toString());
    String name = file.getName();
    Calendar date = Calendar.getInstance();
    date.setTimeInMillis(file.lastModified());
    
    //Photo newPhoto = new Photo(name,file.toURI().toString(), date, );
    Photo newP = new Photo(name, date, file.toURI().toString());

    
//     for(Photo curr : selectedAlbum.getPhoto()) {
//     if(curr.getPhoto().equals(newPhoto.getPhoto())&& (curr.getFilePath().equals(newPhoto.getFilePath()))) {
//     Alert alert = new Alert(AlertType.ERROR);
//     alert.setTitle("Error in adding new photo");
//     alert.setContentText("This photo already exists!");
//     alert.showAndWait();
//     return ;
//     }
//     }
//   
    
    //checks the photoname for duplicates
    	if(selectedAlbum.photoChecker(newP)) {
    	Alert error = new Alert(AlertType.ERROR);
    	error.setTitle("Photo exists");
    	error.setContentText("Already Exists");
    	error.showAndWait();
    	dup=true;
    		
    	}
    	
    	//checks the file path for duplicates
    	if(selectedAlbum.fPChecker(newP)) {
        	Alert error = new Alert(AlertType.ERROR);
        	error.setTitle("Photo exists");
        	error.setContentText("Already Exists");
        	error.showAndWait();
        	dup=true;
        		
        	}
    	
    	
    
    if(dup==false) {
    	
    
    this.listOfPhotos.getItems().add(newP);
    
    selectedAlbum.getPhoto().add(newP);
    listOfPhotos.getSelectionModel().select(0);
    save(currentUsers);
    }
    }
    save(currentUsers);
    
    }

    
    
    @FXML
    void backToLibrary(ActionEvent event) throws Exception {

    save(currentUsers);
    
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/library.fxml"));
Parent sceneManager = (Parent) loader.load();
LibraryController libraryController = loader.getController();
libraryController.start(currentUsers, user);
Scene adminScene = new Scene(sceneManager);
Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
appStage.setScene(adminScene);
appStage.show();


    }

    @FXML
    void deletePhotoButton(ActionEvent event) throws Exception{
    
    int photos = listOfPhotos.getSelectionModel().getSelectedIndex();
    
    
    if(listOfPhotos.getSelectionModel().getSelectedItem()!=null) {
    Alert rem = new Alert(AlertType.CONFIRMATION,"Are you sure you want to remove photo", ButtonType.YES,ButtonType.NO,ButtonType.CANCEL);
    rem.showAndWait();
    if(rem.getResult()==ButtonType.YES) {
    	selectedAlbum.getPhoto().remove(photos);
    	save(currentUsers);
    	obsList = FXCollections.observableArrayList(selectedAlbum.getPhoto());
    	listOfPhotos.setItems(obsList);

}
    
    }
   
    
//     if(listOfPhotos.getSelectionModel().getSelectedItem()!=null) {
//     Photo selectedPhoto = listOfPhotos.getSelectionModel().getSelectedItem();
//     
//     
//     Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete this photo?", ButtonType.YES, ButtonType.NO);
//     alert.setTitle("Photo deletion");
//     alert.showAndWait();
//     
//     if(alert.getResult() == ButtonType.YES) {
//     //obsList.remove(selectedPhoto);
//     listOfPhotos.getItems().remove(selectedPhoto);
//     save(currentUsers);
//     }
//     else {
//     Alert error = new Alert(AlertType.ERROR);
//     error.setTitle("Deletion error");
//     error.setHeaderText("Select photo to delete");
//     }
//     }
  
    }
    
    
    @FXML
    void moveToButton(ActionEvent event) throws Exception {
    
    Photo movePhoto = listOfPhotos.getSelectionModel().getSelectedItem();
    String move = moveTo.getText();
    
    
    for(Album album: this.user.getAlbums()) {
    if(album.getAlbum().equals(move)) {
    for(Photo photo2: album.getPhoto()) {
    if((photo2.getFilePath().equals(movePhoto.getFilePath())) && (photo2.getPhoto().equals(movePhoto.getPhoto()))){
    Alert error = new Alert(AlertType.ERROR);
    error.setHeaderText("Error: Photo is already present");
    error.showAndWait();
    return;
    }
    }
    album.getPhoto().add(movePhoto);
    
    this.selectedAlbum.getPhoto().remove(movePhoto);
    listOfPhotos.getItems().remove(movePhoto);
    Alert conf = new Alert(AlertType.CONFIRMATION);
    conf.setHeaderText("Photo has moved to album");     
    conf.showAndWait();
    save(currentUsers);
    
    }
    }
    
    }

    @FXML
    void nextPhotoButton(ActionEvent event) {
    if(!listOfPhotos.getSelectionModel().isEmpty()) {
    listOfPhotos.getSelectionModel().select(listOfPhotos.getSelectionModel().getSelectedIndex()+1);
    }
    }

    @FXML
    void prevPhotoButton(ActionEvent event) {
    if(!listOfPhotos.getSelectionModel().isEmpty()&&listOfPhotos.getSelectionModel().getSelectedIndex()!=0) {
    listOfPhotos.getSelectionModel().select(listOfPhotos.getSelectionModel().getSelectedIndex()-1);
    }
    }

    @FXML
    void selectPhotoButton(ActionEvent event) throws IOException {
    
    if(photoList.size()>0) {
    int photoIndex = listOfPhotos.getSelectionModel().getSelectedIndex();
   Photo curr = selectedAlbum.getPhoto().get(photoIndex);
    
    PhotoController.photo=listOfPhotos.getSelectionModel().getSelectedItem();
    PhotoController.listsUsers = currentUsers;
    PhotoController.photosList = photoList;
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/photo.fxml"));
    Parent sceneManager = (Parent) loader.load();
    PhotoController photoController = loader.getController();
    Scene adminScene = new Scene(sceneManager);
    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    
    if(appStage!=null) {
    photoController.start(currentUsers, selectedAlbum, user, listOfPhotos);
    appStage.setScene(adminScene);
    appStage.show();
    
    }
    
    }

    }
    
    
    
    public void save(List<User> users) throws Exception {
    
    try {
    
ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("data/data.dat"));
o.writeObject(currentUsers);
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

