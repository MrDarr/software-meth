package chess;

public class Rook extends Pieces {
	//pieceType rook;
	Rook(boolean white ){
		super(white);
		
	}

	@Override
	public String toString() {
	// TODO Auto-generated method stub
		
		if(white) {
			return "wR";
		}
		return "bR";
	}


	@Override
	public void moves(String src, String dest) {
		// TODO Auto-generated method stub
		
		int[] start = convert(src); 
		int[] stop = convert(dest);
		
		int oldRow = start[0];
		int newRow = stop[0];
		int oldCol = start[1];
		int newCol = stop[1];
		
		if(canPieceMove(src,dest)) {
			 Chess.chessBoard[newRow][newCol] = Chess.chessBoard[oldRow][oldCol];
	            Chess.chessBoard[oldRow][oldCol] = null;
	            hasMoved=true;
	            Chess.printBoard();
			}
	}
 

	@Override
	public boolean canPieceMove(String initial, String destination) {
		// TODO Auto-generated method stub
		
		int[] start = convert(initial); 
		int[] stop = convert(destination);
		
		int oldRow = start[0];
		int newRow = stop[0] ;
		int oldCol = start[1];
		int newCol = stop[1];
		
		
		
	if(toString()=="wR"){
		
		if(Chess.chessBoard[newRow][newCol]!= null && (Chess.chessBoard[oldRow][oldCol].white == Chess.chessBoard[newRow][newCol].white)) {
			return false;
		}
		
		//can't move to the same tile
		if(oldRow == newRow && oldCol == newCol) {
			return false;
		}
		
		//horizontal movement
		if(oldRow == newRow) {
			//right
			if(oldCol<newCol) {
				for(int i = oldCol+1; i<newCol; ++i) {
					if(Chess.chessBoard[oldRow][i] instanceof Pieces ){	
						return false;
				}
			}
			}
		//left
		else {
			for(int i = oldCol-1; i> newCol; --i) {
				if(Chess.chessBoard[oldRow][i] instanceof Pieces ){ 
					return false;
			}
		} 
	}
			
}
		//vertical movement
		else if(oldCol == newCol){
			//down
			if(oldRow < newRow) {
				for(int i = oldRow +1; i<newRow; ++i) {
					if(Chess.chessBoard[i][oldCol] instanceof Pieces ){
						return false;
				}
			}
			}
			//up
			else {
				for(int i = oldRow-1; i>newRow; --i) {
					if(Chess.chessBoard[i][oldCol]instanceof Pieces ) {
					return false;
				}
			}
		}
			
		}
	
		else {
			return false;
		}
		
		
		return true;
	}
	
if(toString()=="bR"){
		
		if(Chess.chessBoard[newRow][newCol]!= null && (Chess.chessBoard[oldRow][oldCol].white == Chess.chessBoard[newRow][newCol].white)) {
			return false;
		}
		
		//can't move to the same tile
		if(oldRow == newRow && oldCol == newCol) {
			return false;
		}
		
		//horizontal movement
		if(oldRow == newRow) {
			//right
			if(oldCol<newCol) {
				for(int i = oldCol+1; i<newCol; ++i) {
					if(Chess.chessBoard[oldRow][i]instanceof Pieces ){	
						return false;
				}
			}
			}
		//left
		else {
			for(int i = oldCol-1; i> newCol; --i) {
				if(Chess.chessBoard[oldRow][i] instanceof Pieces ){ 
					return false;
			}
		} 
	}
			
}
		//vertical movement
		else if(oldCol == newCol){
			//down
			if(oldRow < newRow) {
				for(int i = oldRow +1; i<newRow; ++i) {
					if(Chess.chessBoard[i][oldCol] instanceof Pieces ){
						return false;
				}
			}
			}
			//up
			else {
				for(int i = oldRow-1; i>newRow; --i) {
					if(Chess.chessBoard[i][oldCol]instanceof Pieces ) {
					return false;
				}
			}
		}
			
		}
	
		else {
			return false;
		}
		
		
		return true;
	}

	
	
	return true;
	}
	
	

}