package chess;


//package TileandBoard.*;


public abstract class Pieces { 
	/**
	 * The Piece class is used to create every piece and contains abstract methods that help the pieces move
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @author Siham Darr (sud7)
	 * @author  Farhaan Mohammed ()
	 * @version 1.0
	 * @since 2022-03-22
	 */

	  /**
		 * 
		 */

	/** 
	 * hasMoved is an instance that is used to check if a piece is moved
	 */
	public boolean hasMoved;
	// Pieces piece color ; 
	//private String color;
	/**
	 * white is a boolean instance that checks if the piece is an instance of white or its counterpart black
	 */
	public boolean white;
	//public boolean kill;

	//private boolean black;
	
	
	//private Pieces piece;
//	
//	Components of a piece are color and the way the specific piece moves 
	//each type of piece will have its own class 	
	
/**
 * Constructor used to initilize each field
 * @param white
 */
	Pieces(boolean white){ // constructor for the class of piece 
		this.white=white;
		this.hasMoved = false;
		
	
		//this.piece=piece;
		

		
		
	}
	
	
	
	/**
	 * abstract class  used in every piece to see if the piece is able to move
	 * @param initial first position the piece is at
	 * @param dest the final position the piece wants to be moved towards 
	 * @return true or false whether the piece is able to move or not 
	 */
	public abstract  boolean canPieceMove(String initial, String dest);
	/**
	 * abstract class  used in every piece to see if the piece is able to move
	 * @param initial first position the piece is at
	 * @param dest the final position the piece wants to be moved towards 
	 * 
	 */
	
	
	
	public abstract void moves( String src, String dest);  // abstarct class for moves for every piece 
	
	//public abstract boolean canMove();
	
	/**
	 * toString method that is used to determine which color the piece is 
	 */
	public abstract String toString(); //abstract toString method
	
	
	//public abstract boolean canPieceMove(String initial, String destination);
	

	/**
	 * converts strings into integers and places them into an array
	 * @param needsConvert is passed as the single String that needs to be converted
	 * @return an array of integers 
	 */
	public  static int[] convert(String needsConvert) { //converts the inputs which are strings to an integer and stores them in an array
		int[] sAndD = new int[2]; // this array contains both the source and the destination
		
		//make sure the letters we are working with are inputed as a lower case
	//sAndD[0] = 0; //Initialize 
	//sAndD[1] =0; // Initialize

		//System.out.println(needsConvert);
		
		needsConvert= needsConvert.toLowerCase();
		
			//System.out.println(needsConvert);
			//if(needsConvert.length()==2) { // the string has two positions EX: a2
				int turn2int = needsConvert.charAt(0)-'a';
				//System.out.println(turn2int);
				sAndD[0] = turn2int;
			//	System.out.println(Integer.parseInt(""+needsConvert.charAt(1)));
				sAndD[1] = Character.getNumericValue(needsConvert.charAt(1));
				
				sAndD[1] = 8 - sAndD[1];
			
		//	}
			
		//	for(int i = 0; i < sAndD.length; i++) {
			//System.out.print(sAndD[i]);
		//}
			//return;
			
			int temp = sAndD[0];
			sAndD[0] = sAndD[1];
			sAndD[1] = temp;
		
		return sAndD;
		
		
		
	}
	


}

//CODE DUMPS 




//public boolean validMove(int[][] spot) {
//
//if(spot[0][0] < 0  || spot[0][0] > 8 || spot[8][8] <0|| spot[8][8] >8) { //to check if the value is off the board 
//	return false;
//}else {
//	return true;
//}
//
//
//
//public boolean isWhite() {
//	return white;
//}
//
//
//public boolean isBlack() {
//	return black;
//}
//
//public boolean canMove() {
//	
//}
//public Pieces getPiece() {  //gets the piece Object that we hace created 
//	return piece;
//}



//Pieces(Pieces piece){
//this.piece=piece;
//
//}
//public void getPiece(Pieces piece) {
//
//return;
//}


//String getcolor() { //gets color
//return color;
//}


//public enum pieceType {
//Rook, Queen, King, Pawn, Bishop, Knight;
//}

//public enum colorType{
//Black, White;
//}
//
//Pieces getpiece(){ //gets piece 
//	return piece;
//	
//	
//}
//
//public abstract void moveDiagonal(); // abstract method
//	
//	
//	
//
//
//public abstract void moveHorizontal(); //abstract method
//
//
//public abstract void moveVertical(); // abstract method
//

