package chess;



public class King extends Pieces {

//pieceType king ;





King(boolean white){

super(white);



}





@Override

public String toString() {

// TODO Auto-generated method stub





if(white) {

return "wK";





}









return "bK" ;

}





@Override

public boolean canPieceMove(String initial, String dest) {

// TODO Auto-generated method stub

int[] start = convert(initial);

int[] stop = convert(dest);





int oldRow= start[0];

int oldCol = start[1];

int newRow = stop[0];

int newCol = stop[1];







if(toString()=="wK") {




if(Chess.chessBoard[newRow][newCol]!= null && (Chess.chessBoard[oldRow][oldCol].white == Chess.chessBoard[newRow][newCol].white)) {

return false;

}

if((oldCol==newCol) && (oldRow-newRow)==1) { //king moves one space up vertically

return true;

}

else if((oldCol==newCol) &&(oldRow-newRow)==-1){ //king moves one space down

return true;





}

else if((oldCol!= newCol) &&(oldRow==newRow)){

if((newCol==oldCol+1)||(newCol==oldCol-1)) {// Whether the king can move right or left

return true;

}

}

else if((oldCol!=newCol) &&(oldRow-newRow)==1) { //checks to see if the king is moving one step up and to the right or left



if((newCol==oldCol+1)||(newCol==oldCol-1)){

return true;




}
}

else if((oldCol!=newCol) && (oldRow-newRow)==-1) { //checks to see if the king is moving one step back and to the right or left
if((newCol==oldCol+1)||(newCol==oldCol-1)) {
return true;
}
}


}// to string


// //check to see if king hasnt moved
// if(toString()=="wK" && Chess.chessBoard[oldRow][oldCol]!=null && 
// Chess.chessBoard[oldRow][oldCol]instanceof King && 
// Chess.chessBoard[oldRow][oldCol].hasMoved==false ) { 
// 
// if(newCol == oldCol+2) { //right
// if(Chess.chessBoard[oldRow][oldCol+1]==null && Chess.chessBoard[oldRow][oldCol+2]==null) {
// if(Chess.chessBoard[newRow][7].hasMoved==false) {
// Chess.chessBoard[newRow][newCol-1]=Chess.chessBoard[newRow][7];
// Chess.chessBoard[newRow][7]=null;
// return true;
// }
// }
// }
// 
// if(newCol == oldCol-2) { //left
// if(Chess.chessBoard[oldRow][oldCol-1]==null && Chess.chessBoard[oldRow][oldCol-2]==null) {
// if(Chess.chessBoard[newRow][0].hasMoved==false) {
// Chess.chessBoard[newRow][newCol+1]=Chess.chessBoard[newRow][0];
// Chess.chessBoard[newRow][0]=null;
// return true;
// }
// }
// }
// 
// }

else if(toString()=="bK") {


if(Chess.chessBoard[newRow][newCol]!= null && (Chess.chessBoard[oldRow][oldCol].white == Chess.chessBoard[newRow][newCol].white)) {

return false;

}

if((newCol==oldCol) && (newRow-oldRow)==1) { //moves black king one up

return true;

}

else if((oldCol==newCol) && (newRow-oldRow)==-1 ) { //moves black king one back

return true;

}

else if((newCol!= oldCol) &&(newRow==oldRow)){

if((oldCol==newCol+1)||(oldCol==newCol-1)) {// Whether the king can move right or left

return true;

}

}

else if((newCol!=oldCol) &&(newRow-oldRow)==1) { //checks to see if the king is moving one step up and to the right or left



if((oldCol==newCol+1)||(oldCol==newCol-1)){

return true;





}



}

else if((newCol!=oldCol) && (newRow-oldRow)==-1) { //checks to see if the king is moving one step back and to the right or left

if((oldCol==newCol+1)||(oldCol==newCol-1)) {

return true;

}

}

}//to string 




return false;

}




public boolean canCastle(String initial, String dest) {


int[] start = convert(initial);
int[] stop = convert(dest);


int oldRow= start[0];
int oldCol = start[1];
int newRow = stop[0];
int newCol = stop[1];

//check to see if king hasnt moved
if(Chess.chessBoard[oldRow][oldCol]!=null && 
Chess.chessBoard[oldRow][oldCol]instanceof King && 
Chess.chessBoard[oldRow][oldCol].hasMoved==false) { 

if(newCol == oldCol+2) { //right
if(Chess.chessBoard[oldRow][oldCol+1]==null && Chess.chessBoard[oldRow][oldCol+2]==null) {
if(Chess.chessBoard[newRow][7].hasMoved==false) {
Chess.chessBoard[newRow][newCol-1]=Chess.chessBoard[newRow][7];
Chess.chessBoard[newRow][7]=null;
return true;
}
}
}

if(newCol == oldCol-2) { //left
if(Chess.chessBoard[oldRow][oldCol-1]==null && Chess.chessBoard[oldRow][oldCol-2]==null) {
if(Chess.chessBoard[newRow][0].hasMoved==false) {
Chess.chessBoard[newRow][newCol+1]=Chess.chessBoard[newRow][0];
Chess.chessBoard[newRow][0]=null;
return true;
}
}
}

}

return false;
}


@Override
public void moves(String src, String dest) {
// TODO Auto-generated method stub

int[] start = convert(src);
int[] stop = convert(dest);


int oldRow= start[0];
int oldCol = start[1];
int newRow = stop[0];
int newCol = stop[1];

if(canPieceMove(src,dest)) {

//System.out.println("Does this reach???");

Chess.chessBoard[newRow][newCol] = Chess.chessBoard[oldRow][oldCol];
Chess.chessBoard[oldRow][oldCol] = null;
hasMoved=true;
Chess.printBoard();
}

//if(oldCol+2==newCol||oldCol-2==newCol) {


if(canCastle(src, dest)) {


Chess.chessBoard[newRow][newCol] = Chess.chessBoard[oldRow][oldCol];
Chess.chessBoard[oldRow][oldCol] = null;
Chess.printBoard();
}


//}


}

}

