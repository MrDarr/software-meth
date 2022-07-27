package chess;
//import ChessBoard.java;

public class Pawn extends Pieces {

	/**
	 * The pawn class extends pieces and uses all the abstract methods that are in the pieces
	 * to help the pawn piece move or 
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
		 * chessBoard is a 2-d array that will store all pieces
		 */
Pawn(boolean white){
super(white);

}

public int flag;




// WE Check white first because white is the first color that moves

public  String toString() {  //toString method to see whether the pawn is black or white 

// TODO Auto-generated method stub
if(white) {
      
return "wP";

}

return "bP";
}


// TODO Auto-generated method stub




// public void pawnFirstMove() { //check if it is PAWNS first move
// //ChessBoard.chessBoard[0]
// for(int i = 0; i < 8;i++) {
// for(int j = 0; j <8;j++) {
// if(ChessBoard.chessBoard[i][j] instanceof Pawn) {
// Chess
// }
// }
// }
// 
// }



public  void moves(String src, String dest) {
        // TODO Auto-generated method stub

        int[] start = convert(src); 

        int[] stop = convert(dest);

        int oldRow = start[0];
        int newRow = stop[0] ;
        int oldCol = start[1];
        int newCol = stop[1];
        String input = "";
       //newCol=oldCol;
           //int temp = oldCol;
        //int secondTemp= Math.abs(newRow-oldRow);


        //WHITE PAWN movement
        //System.out.print("this r");
     //System.out.print(canPieceMove(src,dest));
//        System.out.println(canPieceMove(src,dest));
//        System.out.println(canPieceMove(src,dest));
//        if(toString()=="wP") {
            //System.out.print("this reaches");

            if(canPieceMove(src,dest)) {
            hasMoved=true;

            //System.out.println("Does this reach???");
                if (stop[0]==0 && white) {
                    promotePawn(src, dest, "Q");
                }
                else if (stop[0]==7 && !white) {
                    promotePawn(src, dest, "Q");
                }
                else {
                Chess.chessBoard[newRow][newCol] = Chess.chessBoard[oldRow][oldCol];
                Chess.chessBoard[oldRow][oldCol] = null;

                }
            }


            Chess.printBoard();


            }
//        }


/*
 * 
 * 
 * 
 * 
 * 
 * 
 */



public  boolean promotePawn(String src, String dest, String input) {

hasMoved = true;  //set has moved = true to indicate the pawn has moved 

       int[] start = convert(src); 



int[] stop = convert(dest);



int oldRow = start[0];

int newRow = stop[0] ;

int oldCol = start[1];

int newCol = stop[1];

//System.out.println(input + "" + newRow);

if(hasMoved ==true && white ) {

//System.out.println("reaches here 1");





if(oldRow==1 &&(oldRow-newRow)==1 ) {

//moves(src,dest); //moves the piece

//System.out.println("reaches here 2");



if(input.equals("Q")) {



Chess.chessBoard[newRow][newCol]= new Queen(true);

}

//System.out.println("reaches here 3");

if(input.equals("R") ) {

// System.out.println("here sigam is gay");

Chess.chessBoard[newRow][newCol]= new Rook(true);

//System.out.println(Chess.chessBoard[newRow][newCol].toString());

}

//System.out.println("reaches here 4");

//System.out.println(input);

if(input.equals("B")) {

Chess.chessBoard[newRow][newCol]= new Bishop(true);

}

if(input.equals("N")) {

Chess.chessBoard[newRow][newCol]= new Knight(true);

}







}









Chess.chessBoard[oldRow][oldCol]=null;

return true;



}

if(hasMoved ==true && !white ) {

//System.out.println("reaches here 1");





if(oldRow==6 &&(newRow-oldRow)==1 ) {

//moves(src,dest); //moves the piece

//System.out.println("reaches here 2");



if(input.equals("Q")) {



Chess.chessBoard[newRow][newCol]= new Queen(false);

}

//System.out.println("reaches here 3");

if(input.equals("R") ) {

// System.out.println("here sigam is gay");

Chess.chessBoard[newRow][newCol]= new Rook(false);

//System.out.println(Chess.chessBoard[newRow][newCol].toString());

}

//System.out.println("reaches here 4");

//System.out.println(input);

if(input.equals("B")) {

Chess.chessBoard[newRow][newCol]= new Bishop(false);

}

if(input.equals("N")) {

Chess.chessBoard[newRow][newCol]= new Knight(false);

}







}









Chess.chessBoard[oldRow][oldCol]=null;

return true;



}

Chess.chessBoard[oldRow][oldCol]=null;

return false;

}


@Override
public  boolean canPieceMove(String initial, String dest) {
// TODO Auto-generated method stub
int[] start = convert(initial); 

int[] stop = convert(dest);

int oldRow = start[0];

int newRow = stop[0];

int oldCol = start[1];

int newCol = stop[1];



// System.out.println("old:" + oldRow+ " " + oldCol + " " + newRow+ " "+ newCol);
if(white &&!(Chess.chessBoard[newRow][newCol]instanceof Pieces)) {

if(hasMoved==false) { //if the pawn has not moved then you can move two pieces

if((oldCol==newCol) && ((oldRow-newRow)==2) && !(Chess.chessBoard[newRow][newCol]instanceof Pieces)) {

return true;
}

else if( (oldCol==newCol) && ((oldRow-newRow)==1)){

return true;
}


}


if(hasMoved==true) { 
if((oldCol==newCol)&& (oldRow-newRow)==1) {
return true;
}

else if( oldRow==3  && (Math.abs(oldRow-newRow)==1) && (newCol==oldCol+1) ) {//enpassant for white 
//System.out.println("hello");

if(!(Chess.chessBoard[newRow][newCol]instanceof Pieces)) {
// System.out.println("someone");
if(Chess.chessBoard[oldRow][oldCol+1]instanceof Pawn && Chess.chessBoard[oldRow][oldCol+1].toString()=="bP") {
// System.out.println("omer farooq");
Chess.chessBoard[oldRow][oldCol+1]= null;

return true;

}
}
}

}

}
if(toString()== "wP" && (Chess.chessBoard[newRow][newCol]instanceof Pieces) && !(Chess.chessBoard[newRow][newCol].toString()=="wP") ){ //kills pieces
//System.out.println(oldRow + " " + newRow);
if((oldCol!=newCol) && (oldRow-newRow)==1) {

if((newCol==oldCol+1) ||(newCol==oldCol-1)){
return true;
}
}

}






// else if(toString()=="wP"&& (Chess.chessBoard[newRow][newCol+1]instanceof Pieces)) { //kills the pieces
// 
// if((oldCol!= newCol) &&((oldRow-newRow)==1||(oldRow-newRow==-1)) ){
//// if(oldCol==newCol+1) {
// 
// 
// System.out.println(oldRow + " "+ newRow);
// return true;
// //}
// }
// }

// else if(toString()=="wP" && ((Chess.chessBoard[newRow+1][newCol]instanceof Pawn ))){
// if((oldCol!= newCol) &&((oldRow-newRow==3))){
// return true;
// }

// }


else if (toString()=="bP") {

if(hasMoved == false) {
if((oldCol == newCol) && (newRow-oldRow)==2) {
//System.out.print("peppee");
//hasMoved=true;
return true;
}
else if((oldCol== newCol) && (newRow-oldRow)==1) {
//hasMoved=true;
return true;
}





}
else if(hasMoved ==true) {
if((oldCol==newCol) && (newRow-oldRow)==1){
return true;
}
//else if()
}
}
if(toString()== "bP" && Chess.chessBoard[newRow][newCol]instanceof Pieces) { //kills the pieces
//System.out.println("hello");
//System.out.println(oldRow + " " + newRow);
if((newCol!=oldCol) && (newRow-oldRow)==1) {
//System.out.println("hello");
if((oldCol==newCol+1) ||(oldCol==newCol-1)){
return true;
}
}

}


return false;

}
}
