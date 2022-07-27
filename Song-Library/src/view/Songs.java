// Authors: Siham Darr (sud7) and Farhaan Mohammad (fm412)

package view;

import java.util.Comparator;

public class Songs {
private String song; //this is for add 
 
private String artist;

private String album; 

private int year;
//
// public Songs() {
// this.song = null;
// this.artist = null;
// this.album = null;
// this.year =0;
// 
// }


public Songs(String song, String artist) {
this.song=song;
this.artist=artist;
this.album=null;
}

public Songs(String song, String artist, String album) {
this.song=song;
this.artist=artist;
this.album= album;
}

public Songs(String song, String artist, int year) {
this.song=song;
this.artist=artist;
this.year = year;
}

public Songs(String song,String artist, String album,  int year){
this.song = song;
this.artist = artist;
this.album = album;
this.year = year;

}
public String getsong(){
return song;
}


public String getartist(){
return artist;
}



public String getalbum(){
return album;
}



public int getyear(){
return year;
}


public String setsong(String song){
this.song = song;
return song;

}

public String setartist(String artist) {
this.artist=artist;
return artist;
}
public String setalbum(String album) {
this.album =album;
return album;
}

public int setyear(int year) {
this.year=year;
return year;


}

//    public static Comparator<Songs> orderSongs = new Comparator<Songs>() {
//
//public int compare(Songs s1, Songs s2) {
//  String Song1 = s1.getsong().toUpperCase();
//  String Song2 = s2.getsong().toUpperCase();
//
//  //ascending order
//  return Song1.compareTo(Song2);
//    }};

@Override

public String toString() {
return song+ " - "+ artist ;
}
public static Comparator<Songs> orderSongs = new Comparator<Songs>() {

    		public int compare(Songs s1, Songs s2) {
    		String Song1 = s1.getsong().toUpperCase();
    		String Song2 = s2.getsong().toUpperCase();
    		String Artist1 = s1.getartist().toUpperCase();
    		String Artist2 = s2.getartist().toUpperCase();
    		if(Song1.equals(Song2)) {
    		return Artist1.compareTo(Artist2);
    		}
    		else {return Song1.compareTo(Song2);}
    		    }};


// 
// public static void main(String[] args) {
// // TODO Auto-generated method stub
//
// }

}