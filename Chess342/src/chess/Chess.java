package chess;
import java.util.ArrayList;
import java.util.Scanner;

public class Chess extends Tile{

	/**
	 * The Chess class initializes the board and pieces. The board is a 2-d 8X8 array.
	 * The pieces are initialized stored in standard starting positions on the board.
	 * This class consists of several methods that help print out the board
	 * This Chess class is also the main class that is called to run the program it also contains the main method,while also having several 
	 * methods that run as the game progresses 
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
public static  Pieces[][]  chessBoard; //creates the chessboard

/**
 * moves is an arrayList that is used for the checkMate method
 */
public static ArrayList<String> moves = new ArrayList<String>();
//public static Pieces[][] temp;
/**
 * turn is an instance variable that cycles between the turns.
 * 
 */
public static boolean turn =true;
// private Tile square = new Tile(); //square tile
/**
 * piece creates a piece to be used in this class
 */
public  Pieces piece;
/**
 * wantsDraw is a boolean variable that decides if the players throw a draw
 */
public static boolean wantsDraw;

/**
 * initilizes a piece from the pieces class 
 */
public void aPartOfPieces(Pieces piece) {
this.piece = piece;
}

/**
 * white is used to help create the white pieces
 */
public static boolean white = true;
/**
 * black is used to help create the black pieces 
 */
public static boolean black = false;
/**
 * This integer array is used to find the coordinates of the white King
 */
public static int[] whiteKingCoord;
/**
 * This integer array is used to find the coordinates of the Black King
 */
public static int[] blackKingCoord;
/**
 *This variable is used if the move is illegal
 */
public static boolean notWorking = false; 
/**
 * This variable is used during castling
 */
public static boolean castle;
/**
 * This variable promotes the pawn
 */
public static boolean promoter;


/**
 *This void  method is used to initialize the board which is a 2-d array
 *All spots are null by default 
 *Pieces are created and are stored on the board
 *
 *
 * 0
 * 1
 * 2
 * 3
 * 4
 * 5
 * 6
 * 7 
 *   0 1 2 3 4 5 6 7
 */
public static void board() {
chessBoard = new Pieces[8][8]; //Initializes the chess board

whiteKingCoord = new int[2]; // x and y values for both can only contain 2 coordinates
blackKingCoord = new int[2];


whiteKingCoord[0] = 7; //index 7
whiteKingCoord[1] = 4; //index 4 in its row

blackKingCoord[0] = 0; //index 0
blackKingCoord[1] = 4; //index 4 in its row


// START off by having a null chess board
for(int i = 0; i < 8;i++) {
for(int j = 0; j < 8; j++) {
chessBoard[i][j]=null;

}
}

//****FILL IN PIECES********

// White side starting with PAWN then the other pieces

int w = 0;
while (w < 8) {
chessBoard[6][w] = new Pawn(true); //creates a pawn at every index 
w++;
////// 
}
// 
chessBoard[7][0] = new Rook(white);
chessBoard[7][1] = new Knight(white);
chessBoard[7][2] = new Bishop(white);
chessBoard[7][3] = new Queen(white);
chessBoard[7][4] = new King(white);
//chessBoard[7][5] = new Bishop(white);
//chessBoard[7][6] = new Knight(white);
chessBoard[7][7] = new Rook(white);
// 
// 

// Black side starting with PAWN then create the other pieces

int b = 0;
while(b < 8) {
chessBoard[1][b] = new Pawn(false);
b++;
}
//// 
chessBoard[0][0] = new Rook(black);
chessBoard[0][1] = new Knight(black);
chessBoard[0][2] = new Bishop(black);
chessBoard[0][3] = new Queen(black);
chessBoard[0][4] = new King(black);
chessBoard[0][5] = new Bishop(black);
chessBoard[0][6] = new Knight(black);
chessBoard[0][7] = new Rook(black);

}



/**
 * This method is used to actually print the board and place all the pieces in its place
 *  Checks to see what each spot is an instance of which piece 
 */

public static void printBoard() {
System.out.println();
int num =8;
for(int i = 0; i < 8;i++) {
for(int j = 0; j < 8;j++) {
if(chessBoard[i][j] instanceof Pawn) {
System.out.print(chessBoard[i][j].toString()+ " ");
}
else if(chessBoard[i][j] instanceof Rook) {
System.out.print(chessBoard[i][j].toString()+ " ");
}
else if(chessBoard[i][j] instanceof Knight) {
System.out.print(chessBoard[i][j].toString()+ " ");
}
else if(chessBoard[i][j] instanceof Bishop) {
System.out.print(chessBoard[i][j].toString()+ " ");
}
else if(chessBoard[i][j] instanceof Queen) {
System.out.print(chessBoard[i][j].toString()+ " ");
}
else if(chessBoard[i][j] instanceof King) {
System.out.print(chessBoard[i][j].toString()+ " ");
}

if(i%2 == 0 && chessBoard[i][j]==null) {
if(j%2==0 && chessBoard[i][j]==null) {
System.out.print("   ");
}else {
System.out.print("## ");
}
}
else if (i%2==1 && chessBoard[i][j]==null) {
if(j%2==0 && chessBoard[i][j]==null) {
System.out.print("## ");
}else {
System.out.print("   ");
}
}

if(j==7) {
System.out.print(num);
num--;
}
}
System.out.println();

}



char letter= 'a';
  for(int i = 0; i < 8;i++) {
  
  System.out.print(" "+ letter + " ");
 letter++;
  }

System.out.print("\n");
System.out.println("");
}







/**
 * This method displays the turn in the main method so the user is able to see which teams turn it is
 * turn is true if it is whites turn
 * else turn is blacks move
 * 
 */
public static String displayTurn() {

        if(turn == true) {
            return "White's move: ";
        }
        else {
           return "Black's move: ";
        }
    }
/**
 *  This method is also a helper method used in the main method which cycles through each turn
 */
    public static void rotateTurn() {

        if(turn == true) {
            turn = false;
        }
        else if(turn == false) {
            turn = true;
        }

    }

/**
 * This is the main method where the game runs its course
 * In this method each part of the game comes together to make it playable by the user 
 * @param args
 */
public static void main(String[] args) { 
board();

printBoard();
System.out.println(""); //gap between board and display



int odd = 1; //checker for odd number
int even = 0; //checker for even number 
lookForKing(white);

Scanner s = new Scanner(System.in);
//whoseTurns();
//canPieceMove("g1" +"h3");

//while loop
while(odd % 2 == 1 || even% 2 ==0) {

System.out.print(displayTurn());

String input = s.nextLine(); //goes to the next input

if(wantsDraw == true && input.equalsIgnoreCase("draw")) {
System.out.println("Game ends in draw");
break;
}

//resign
if(turn == true) {
if(input.equalsIgnoreCase("resign")) {
System.out.println("Black wins!");
break;
}
}
if(turn == false) {
if(input.equalsIgnoreCase("resign")) {
System.out.println("White wins!");
break;
}
}


System.out.print("");

String[]whatMoves = input.split(" ");

String initial = whatMoves[0]; //initial Spot
String destination = whatMoves[1]; //destination spot

//System.out.println(String.valueOf(whatMoves[1]));
int [] start = Pieces.convert(initial);

String extra = "";

//draw
if(whatMoves.length==3) {
extra = whatMoves[2];
if(turn == true) {
if(extra.equalsIgnoreCase("draw?")) {
wantsDraw=true;
rotateTurn();
}
}
if(turn == false) {
if(extra.equalsIgnoreCase("draw?")) {
wantsDraw = true;
rotateTurn();
}
}
}


if(whatMoves.length==3) {
           if(whatMoves[2].length()==1) {
               if(chessBoard[start[0]][start[1]]!=null) {
                   if(((((Pawn) chessBoard[start[0]][start[1]]).promotePawn(initial,destination,extra)))) {
                       promoter=true;
                       printBoard();
                   }

               }
           }
       }


// if(chessBoard[start[0]][start[1]]!=null ) {
// if((((King)chessBoard[start[0]][start[1]]).canCastle(initial, destination))) {
// castle=true;
// printBoard();
// }
// }
// 

//illegal moves

if((chessBoard[start[0]][start[1]].canPieceMove(initial, destination)==false)) {
notWorking = true;
}

else if((turn == true) && (!(chessBoard[start[0]][start[1]].white))) {
notWorking = true;

}
else if((turn == false) && (chessBoard[start[0]][start[1]].white)) {
notWorking = true;

}



//check
if(isBlackKingInCheck()&& turn ==false) {
System.out.println("Black King is in check");
}

if(isWhiteKingInCheck() && turn == true) {
System.out.println("White King is in check");
}

//check mate
String valuez = initial + " " + destination;
if(isBlackKingInCheck() || isWhiteKingInCheck()) {
if(!moves.contains(valuez)) {
checkMate(white);
System.out.println("Check Mate!");
if(turn==true) {
System.out.println("White wins!");
}
if(turn==false) {
System.out.println("Black wins!");
}
break;
}
}



if(notWorking == true) {
displayTurn();
System.out.println("illegal move, try again");
notWorking = false;
}

else {
chessBoard[start[0]][start[1]].moves(initial, destination);
rotateTurn();
}

}//while loop

}//main





/**
 * This method is used to look for the kings coordinates which is needed for the check methods for each respective king
 * @param kingColor determines if the king is white or black returns true if the king is white, or returns false if the king is black
 * @return returns whiteKingCoord array which contains the x and y values of the white king or returns blackKingCoord array which is the 
 * black kings coordinates
 * else if there is no king it returns null
 * 
 */
public static  int[] lookForKing(Boolean kingColor) {

for(int i = 0; i < 8;i++) {
//System.out.println(i);
for(int j = 0; j< 8;j++) {
//System.out.println(i + " "+ j);

if(chessBoard[i][j]!=null) {
//System.out.println(chessBoard[i][j].toString().equals("wK"));

if(kingColor==true &&chessBoard[i][j].toString().equals("wK") &&chessBoard[i][j].white==true) {
//System.out.println("hello");
whiteKingCoord[0] =i;
whiteKingCoord[1]=j;
return whiteKingCoord;
}
else if(kingColor==false&&chessBoard[i][j].toString().equals("bK") && chessBoard[i][j].white==false) {

blackKingCoord[0]=i;

blackKingCoord[1]=j;
// System.out.println(i+" "+ j);
return blackKingCoord;
}

}
}
}



System.out.println("no king");
return null; //if there is nothing

}

/**
 * This method is a boolean function that checks whether a piece from the black team is able to reach the king at its coordinate that
 * it is located at
 * @return true if the king is reached
 * false if the king cannot be reached 
 */
public static boolean isWhiteKingInCheck() {
   //gets convert method from pieces class


 int[] kingcoord = lookForKing(white);  
// if(canPieceMove())
String temp = convertToString(kingcoord);
//System.out.println(temp);

 //String[] secondTemp = temp.split(" ");
 //System.out.println(secondTemp);
 //String src = temp.substring(0,1);
 
 //System.out.println(src);
//  String dest = temp;
 // System.out.println(dest);
 
for(int i = 0; i < 8;i++) {
for(int j = 0; j< 8;j++) {

if(chessBoard[i][j]!=null&&chessBoard[i][j].white==false) {
//store location
int[] someArray= {i,j};
String src = convertToString(someArray);
if(chessBoard[i][j].canPieceMove(src,temp)) {
//check if the piece has a valid move to white king 
//if it does return true
//System.out.println(src + " " + temp);
return true;
}
}
}
}
return false;
}

/**
 * This method is a boolean function that checks whether a piece from the white team is able to reach the king at its coordinate that
 * it is located at
 * @return true if the king is reached
 * false if the king cannot be reached 
 */
public static boolean isBlackKingInCheck() {
   //gets convert method from pieces class


 int[] kingcoord = lookForKing(!white);  
// if(canPieceMove())
 //System.out.println(kingcoord[0] +" " + kingcoord[1]);
String temp = convertToString(kingcoord);
  

 //String[] secondTemp = temp.split(" ");
 //System.out.println(secondTemp);
 //String src = temp.substring(0,1);
 
 //System.out.println(src);
//  String dest = temp;
 // System.out.println(dest);
 
for(int i = 0; i < 8;i++) {
for(int j = 0; j< 8;j++) {

if(chessBoard[i][j]!=null&&chessBoard[i][j].white==true) {
//store location
int[] someArray= {i,j};
String src = convertToString(someArray);
if(chessBoard[i][j].canPieceMove(src,temp)) {
//check if the piece has a valid move to white king 
//if it does return true
//System.out.println(src + " " + temp);
return true;
}
}
}
}
return false;
}
 
/**
 * This is a method that checks if there are any possible moves for each respective team
 * @param white is used for the white team else it is the black tea,
 * @return an arrayList that is empty if there are no possible moves
 */
public static ArrayList<String> checkMate(boolean white){


for(int i = 0; i < 8; i++) {


for(int j =0; j < 8;j++) {

if(chessBoard[i][j]!=null && chessBoard[i][j].white ==white) {
Pieces piece = chessBoard[i][j];
Pieces[][] tempCopy = chessBoard.clone();

for(int a = 0; a < 8;a++) {


for(int b = 0; b <8;b++) {

int[] first = {i,j};

int[] second = {a,b};

String src = convertToString(first);

String dest = convertToString(second);
if(chessBoard[i][j].canPieceMove(src,dest)) {
tempCopy[a][b]=piece; //moves this move onto the copy board 

if(white==true) {
moves.add(src+ " " +dest); //adds the moves because it is able to move
}
if(white==false) { //adds the moves because it is able to move
moves.add(src+ " "+ dest);
}
} 

}
} 

} 


}
} 
return moves; //returns empty array list

}
// public static ArrayList<String> blackCheckMate(Boolean white) {
// int[] getBkLoc = lookForKing(!white); //gets the location of the black king 
// 
// ArrayList<String> moves = new ArrayList<String>();
// //String dest = convertToString(getBkLoc); //gets the destination of where the black king is located 
// 
// for(int i = 0; i < 8;i++) {
// for(int j = 0; j < 8;j++) {
// 
// 
// Pieces p = chessBoard[i][j]; //creates a piece at the location of i and j 
// Pieces [][]temp = chessBoard.clone(); //creates a clone of the chessBoard
// 
// 
// for(int a = 0; a <8;a++) {
// 
// for(int b = 0; b <8;b++) {
// if(temp[i][j]!=null ) {
// int[] some = {i,j};
// String src = convertToString(some);
// int[] other = {a,b};
// String dest = convertToString(other);
// if(temp[i][j].canPieceMove(src, dest) && p.white ==true) {
// moves.add(src + " "+ dest);
// return moves;
// 
// }
// if(p.white==false ) {
// moves.add(src + " " + dest);
// }
// 
// 
// 
// }
// 
// 
// 
// }
// }
// }
// }
// 
// return moves;
// 
// }



/*
*  makes a copy of the chessBoard
* 
*/

// public static void copyBoard() {
// 
// for(int i = 0; i < 8;i++) {
// for(int j = 0; j < 8;j++) {
// chessBoard[i][j]= temp[i][j];
// }
// }
// }
// 


// public static String convertMore(int[] arr) {
// String someVal ="";
// if(arr[1]==0) {
// someVal+="a";
// }
// if(arr[0]==0) {
// someVal+="8";
// }
// return someVal;

// }

/**
 * helper method to turn strings into integers
 * @param cord is an integer array this is passed through
 * @return a string that is value
 */
public static String convertToString(int[]cord) {
String val ="";
//System.out.println(cord[1]);

if(cord[1]==0) {
val +="a"; 
}
if(cord[1]==1) {
val +="b";
}
if(cord[1]==2) {
val +="c";
}
if(cord[1]==3) {
val +="d";
}
if(cord[1]==4) {
val +="e";
}
if(cord[1]==5) {
val +="f";
}
if(cord[1]==6) {
val +="g";
}
if(cord[1]==7) {
val +="h";
}
if(cord[0]==0) {
val +="8";
}
if(cord[0]==1) {
val +="7";
}
if(cord[0]==2) {
val +="6";
}
if(cord[0]==3) {
val +="5";
}
if(cord[0]==4) {
val +="4";
}
if(cord[0]==5) {
val +="3";
}
if(cord[0]==6) {
val +="2";
}
if(cord[0]==7) {
val +="1";
}
return val;
}



// public static boolean selfCheck(String start, String end) {
//
// int[] oldPosConv = Pieces.convert(start);
// int[] newPosConv = Pieces.convert(end);
//
// Pieces[][] copy = copyArray();
//
// if (copy[oldPosConv[0]][oldPosConv[1]] != null
// && copy[oldPosConv[0]][oldPosConv[1]].canPieceMove(start, end, copy)) {
//
// copy[newPosConv[1]][newPosConv[0]] = copy[oldPosConv[1]][oldPosConv[0]];
// copy[oldPosConv[1]][oldPosConv[0]] = null;
//
// if (!white && isBlackKingInCheck()) {
// return true;
// }
//
// if (white && isWhiteKingInCheck()) {
// return true;
// }
//
// }
//
// return false;
// }


}



