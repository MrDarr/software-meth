// Authors: Siham Darr (sud7) and Farhaan Mohammad (fm412)
package view;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class songLibController {
   
    @FXML
    private TextField addedAlbum;

    @FXML
    private TextField addedArtist;

    @FXML
    private TextField addedSong;

    @FXML
    private TextField addedYear;

    @FXML
    private TextField selectedAlbum;

    @FXML
    private TextField selectedArtist;

    @FXML
    private TextField selectedSong;

    @FXML
    private TextField selectedYear;
   
    @FXML
    private Button add;

    @FXML
    private Button delete;

    @FXML
    private Text display;

    @FXML
    private ListView<String> listsOfSongs;

    @FXML
    private Button update;
   

   
    private ArrayList<Songs> songs = new ArrayList<Songs>();// store all information
    private ObservableList<String> moreSongs = FXCollections.observableArrayList();
    
    // use index of with the dash
    // add first, then sort, copy to observable list, then list view
    public void read() throws FileNotFoundException {  

    BufferedReader br =new BufferedReader(new FileReader("src/view/songs.txt")); try {
    	String line ="";
	while ((line = br.readLine()) != null ) {
		
	
		String[] songDetails = new String[4];
		String[] temp = line.split("-"); //create array of song details, separated by ","
		for(int i = 0; i < temp.length; i++) {

			songDetails[i] = temp[i].trim();
		
}
		int year = 0;
		String album = "";
		try {
		 year = Integer.parseInt(songDetails[2]);
		}catch(NumberFormatException e) {
			album = songDetails[2];
		}
		if(songDetails[3] != null) {
		try {
			year = Integer.parseInt(songDetails[3]);
		}catch(NumberFormatException e) {
			year =0;
		}
		}
		
		Songs p = new Songs(songDetails[0], songDetails[1], album, year);
		
		songs.add(p); //add song array entry 0 (song title) to songs list
		
		
}

    } catch (FileNotFoundException e) {
    	e.printStackTrace();
    } catch (IOException e) {
    	e.printStackTrace();
    } finally {
    	if (br != null) {
    		try {
    			br.close();
    		} catch (IOException e) {
    			e.printStackTrace();
}
}}
    for(Songs s: songs) {
		
		moreSongs.add(s.toString());
		
	}
	//moreSongs.add(songs.toString());
	listsOfSongs.setItems(moreSongs);
}
   
    public void writesToFile()throws IOException{
 	   FileWriter w = new FileWriter("src/view/songs.txt");
 	   int i =0;
 	   
 	   while(i < songs.size()) {
 		 
 		 w.write(songs.get(i).getsong()+ " - " + songs.get(i).getartist());
 		 if(songs.get(i).getalbum()!=null) {
 			w.write(" - " + songs.get(i).getalbum());
 		 }
 		 if(songs.get(i).getyear() >0) {
 			 w.write(" - " + songs.get(i).getyear());
 		 }
 		 w.write( "\n" );
 		 i++;
 		 

 		 
 	   }
		 w.close();
    }
   
@FXML
void addSong(MouseEvent event) throws IOException {
   Songs o;
   int errorDup = 0;
   
   if(addedSong.getText().isEmpty() == false && addedArtist.getText().isEmpty() == false) {
    if(addedAlbum.getText().isEmpty() == false && addedYear.getText().isEmpty() == false) {
    o = new Songs(addedSong.getText(), addedArtist.getText(), addedAlbum.getText(), Integer.parseInt(addedYear.getText()));
}
    else if(addedAlbum.getText().isEmpty() == false) {
o = new Songs(addedSong.getText(), addedArtist.getText(), addedAlbum.getText());
}
    else if(addedYear.getText().isEmpty() == false) {
o = new Songs(addedSong.getText(), addedArtist.getText(), Integer.parseInt(addedYear.getText()));
}
else {
o = new Songs(addedSong.getText(), addedArtist.getText());
}
    for(Songs x : songs) {
    if(o.getsong().toLowerCase().equals(x.getsong().toLowerCase()) && o.getartist().toLowerCase().equals(x.getartist().toLowerCase())) {
    addedSong.setText("");
    addedArtist.setText("");
    addedAlbum.setText("");
    addedYear.setText("");
    Alert error = new Alert(AlertType.ERROR);
       error.setTitle("Error Alert Dialog");
       error.setContentText("YOU CANNOT HAVE DUPLICATES");
       error.showAndWait();
       errorDup++;
       break;
    }
    }
    if(errorDup == 0) {
    Alert add = new Alert(AlertType.CONFIRMATION,"you can add", ButtonType.YES,ButtonType.NO,ButtonType.CANCEL);
    add.setTitle("ADD CONFIRMATION");
        add.showAndWait();
        addedSong.setText("");
    addedArtist.setText("");
    addedAlbum.setText("");
    addedYear.setText("");
       songs.add(o);
//       moreSongs.add(o.toString());
       Collections.sort(songs, Songs.orderSongs);
//       moreSongs.sorted();
//       Collections.sort(moreSongs);
       
       listsOfSongs.getItems().clear();
       for(Songs s: songs) {
    	  moreSongs.add(s.toString());
       }
       
       listsOfSongs.getItems().sorted();
       listsOfSongs.setItems(moreSongs);
       listsOfSongs.getSelectionModel().select(0);
   
      
       }
    }
   

   else {
    Alert someError= new Alert(AlertType.CONFIRMATION,"INVALID INPUT", ButtonType.CANCEL);
   someError.setTitle("CAN'T ADD");
   someError.showAndWait();
   addedSong.setText("");
addedArtist.setText("");
addedAlbum.setText("");
addedYear.setText("");
   }
   writesToFile();

}


    @FXML
    void deleteSong(MouseEvent event) throws IOException {
        int index = listsOfSongs.getSelectionModel().getSelectedIndex(); //gets the index
        if(moreSongs.isEmpty()== true) {
        Alert emptyAlert = new Alert(AlertType.INFORMATION);
        emptyAlert.setTitle("Warning");
        emptyAlert.setHeaderText("Add song before deleting");
        emptyAlert.showAndWait();
        }
        else {

       Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete?", ButtonType.YES,ButtonType.NO,ButtonType.CANCEL);
       alert.setTitle("Deletion");
       alert.showAndWait();
       
       if(alert.getResult()==ButtonType.YES) {
        if(index>-1) {
        songs.remove(index);
            moreSongs.remove(index);
            selectedSong.setText("");
            selectedArtist.setText("");
            selectedAlbum.setText("");
            selectedYear.setText("");
           //listsOfSongs.getItems(index);
        }
        
          // moreSongs.sorted();
           Collections.sort(songs, Songs.orderSongs);
         //  Collections.sort(moreSongs);
           listsOfSongs.getItems().clear();
           for(Songs s: songs) {
        	  moreSongs.add(s.toString());
           }
       listsOfSongs.setItems(moreSongs);
       showItems(index);
       listsOfSongs.getSelectionModel().select(0);
       }
   }
        writesToFile();
    
        }
    

   
   
    //works
    public void showItems(int index){
    if(listsOfSongs.getSelectionModel().getSelectedIndex() < 0) {
    
    }
    else {
    selectedSong.setText(songs.get(index).getsong());
       selectedArtist.setText(songs.get(index).getartist());
       selectedAlbum.setText(songs.get(index).getalbum());
       String year= String.valueOf(songs.get(index).getyear());
       if(Integer.parseInt(year) == 0) {selectedYear.setText(null);}
       else {selectedYear.setText(String.valueOf(Integer.parseInt(year)));}
    }
   
    }
   
   
   
// works
    @FXML
    void updateSong(MouseEvent event) throws IOException {
    int index= listsOfSongs.getSelectionModel().getSelectedIndex();
    int errorDup = 0;
    if(index >-1) {
    	for(Songs x : songs) {
    	    if(selectedSong.getText().toLowerCase().equals(x.getsong().toLowerCase()) && selectedArtist.getText().toLowerCase().equals(x.getartist().toLowerCase())) {
    	    addedSong.setText("");
    	    addedArtist.setText("");
    	    addedAlbum.setText("");
    	    addedYear.setText("");
    	    Alert error = new Alert(AlertType.ERROR);
    	       error.setTitle("Error Alert Dialog");
    	       error.setContentText("YOU CANNOT HAVE DUPLICATES");
    	       error.showAndWait();
    	       errorDup++;
    	       break;
    	    }
    	 }
    
    	if(errorDup == 0) {
    	
            if( selectedSong.getText().isEmpty() == true || selectedArtist.getText().isEmpty() == true){ //checks to see if Song and artist fields are empty
            Alert error = new Alert(AlertType.ERROR);
                error.setTitle("Error Alert Dialog");
                error.setContentText("You can not have the song and artist fields empty!!"+ " Please enter a field for both!!");
                error.showAndWait();
                if(selectedSong.getText().isBlank()) {
            		selectedSong.setText(songs.get(index).getsong());
            	}
                if(selectedArtist.getText().isBlank()) {
            		selectedArtist.setText(songs.get(index).getartist());
            	}
            }
            else {
              Alert d = new Alert(Alert.AlertType.CONFIRMATION, String.valueOf(index), ButtonType.YES, ButtonType.NO);
              d.setTitle("Update");
              d.setHeaderText("Confirm Update?");
              d.showAndWait();
              if(d.getResult()==ButtonType.YES) {
		          songs.get(index).setsong(selectedSong.getText());
		          songs.get(index).setartist(selectedArtist.getText());
		          songs.get(index).setalbum(selectedAlbum.getText());
	          if((selectedYear.getText()) == null || selectedYear.getText() == "") {songs.get(index).setyear(0);}
	          else {songs.get(index).setyear(Integer.parseInt(selectedYear.getText()));}
          
        //  moreSongs.set(index, songs.get(index).toString());
          //        listsOfSongs.getItems().set(index, moreSongs.get(index));
                Collections.sort(songs, Songs.orderSongs);
                //Collections.sort(moreSongs);
                listsOfSongs.getItems().clear();
                
                for(Songs s: songs) {
             	  moreSongs.add(s.toString());
                }
                listsOfSongs.setItems(moreSongs);
                //listsOfSongs.getSelectionModel().select(index);
              }
            }
    	}
    listsOfSongs.getSelectionModel().select(index);
    writesToFile();
    }
    else {
        Alert error = new Alert(AlertType.ERROR);
                error.setTitle("Error Alert Dialog");
                error.setContentText("Cannot Update an Invalid Index. Please add Songs first!");
                error.showAndWait();
    }
    }

    public void start(Stage primaryStage) throws FileNotFoundException {
    // TODO Auto-generated method stub
    //int index= listsOfSongs.getSelectionModel().getSelectedIndex();

	listsOfSongs
	.getSelectionModel()
	.selectedIndexProperty()
	.addListener(
			(obs, oldVal, newVal) ->
	//showItem(mainStage)
			showItems(listsOfSongs.getSelectionModel().getSelectedIndex())
			);
	read();
	listsOfSongs.getSelectionModel().select(0);
	showItems(0);
}
}
