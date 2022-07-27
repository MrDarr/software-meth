package chess;

public class Knight extends Pieces {
	//pieceType knight;
	Knight(boolean white){
		super(white);
		
	}

	public boolean kill;
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if(white) {
			return "wN";
		}
		
		
		
		return "bN";
		
		
	
	
	
	
}

/*
 * 
 */


	@Override
	public boolean canPieceMove(String initial, String dest) {
		// TODO Auto-generated method stub
		//System.out.println(initial);
		//System.out.println(dest);
		int[] start = convert(initial);
		
		int[] stop = convert(dest);
		
		
		int oldRow= start[0];
		int oldCol = start[1];
		int newRow = stop[0];
		int newCol = stop[1];
			if(toString()=="wN") {
				
				if(Chess.chessBoard[newRow][newCol]!= null && (Chess.chessBoard[oldRow][oldCol].white == Chess.chessBoard[newRow][newCol].white)) {
					return false;
				}
				if(((newCol!=oldCol)) &&(oldRow-newRow)==2) { // moves two spaces up and one to the right or left
					//System.out.println(newCol+" "+ oldCol);
					if((newCol==oldCol+1 )||(newCol==oldCol-1)) {
						
					
					return true;
				}
					
					
				}
				
				else if((newCol!=oldCol) &&( oldRow-newRow)==-2) { // moves two spaces back and oen to the right or to the left
					
					
					if((newCol==oldCol+1)||(newCol==oldCol-1)){
						
						
						return true;
					}
				}
				else if((newCol!= oldCol) && (oldRow-newRow)==1){
					
					if((newCol==oldCol+2) || (newCol==oldCol-2)) {
						return true;
					}
				}
				else if((newCol!=oldCol) && (oldRow-newRow)==-1) {
					if((newCol==oldCol+2)||(newCol-newRow)==-2) {
						return true;
					}
				}
				
				
				
			}
			else if(toString() == "bN") {
				
				if(Chess.chessBoard[newRow][newCol]!= null && (Chess.chessBoard[oldRow][oldCol].white == Chess.chessBoard[newRow][newCol].white)) {
					return false;
				}
				if((oldCol!= newCol) && ( newRow-oldRow)==2) {
					if(oldCol ==newCol+1 || oldCol ==newCol-1) {
						return true;
					}
					
					
				}
				else if ((oldCol!= newCol) && (newRow-oldRow)==-2) {
					if((oldCol==newCol+1) || (oldCol==newCol-1)) {
						return true;
					}
					
				}
				
				else if(( oldCol != newCol )&& (newRow -oldRow )==1) {
					if((oldCol==newCol+2) || (oldCol==newCol-2)) {
						return true;
					}
				}
				else if((oldCol!=newCol)&& (newRow-oldRow)==-1) {
					if((oldCol==newCol+2)||(oldCol==newCol-2)) {
						return true;
					}
				}
				
				
				
				
			}
			
		
		
		
		
		
		
		return false;
	}




	@Override
	public void moves(String src, String dest) {
		// TODO Auto-generated method stub
		
	//System.out.println(canPieceMove(src,dest));
		
		int[] start = convert(src);
		int[] stop = convert(dest);
		
		
		int oldRow= start[0];
		int oldCol = start[1];
		int newRow = stop[0];
		int newCol = stop[1];
		
		if(canPieceMove(src,dest)) {
			
			Chess.chessBoard[newRow][newCol] = Chess.chessBoard[oldRow][oldCol];
			Chess.chessBoard[oldRow][oldCol] = null;
			Chess.printBoard();
		}
		
		
		
	}
}