package chess;

public class Queen extends Pieces{
	//pieceType queen;
	Queen(boolean white){
		super(white);
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		if(white) {
			return "wQ";
		}
		return "bQ" ;
	}

	@Override
	public void moves(String src, String dest) {
		
		int[] start = convert(src); 
		int[] stop = convert(dest);
		
		int oldRow = start[0];
		int newRow = stop[0];
		int oldCol = start[1];
		int newCol = stop[1];
		
		if(canPieceMove(src,dest)) {
			Chess.chessBoard[newRow][newCol] = Chess.chessBoard[oldRow][oldCol];
            Chess.chessBoard[oldRow][oldCol] = null;
            Chess.printBoard();
		}
	}

	@Override
	public boolean canPieceMove(String initial, String dest) {
		
		int[] start = convert(initial); 
		 int[] stop = convert(dest);
			
			int oldRow = start[0];
			int newRow = stop[0];
			int oldCol = start[1];
			int newCol = stop[1];
		
		
		if(toString()=="wQ" || toString()=="bQ") { 
			
			 
		if(Chess.chessBoard[newRow][newCol]instanceof Pieces && (Chess.chessBoard[oldRow][oldCol].white == Chess.chessBoard[newRow][newCol].white)) {
				return false;
			}
			
		//rook movements 	 
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
				return true; 	
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
					return true; 
				}
				
		//bishop methods
				
				//right and down	
				if(oldRow<newRow && oldCol<newCol) {
					int y = oldCol+1; 
					if(Math.abs(oldRow-newRow) == Math.abs(oldCol-newCol)) {
						for(int x = oldRow+1; x<=newRow -1; x++) {
						if(Chess.chessBoard[x][y] instanceof Pieces) {
							return false;
						}
						y++;
					}
					return true;
				}
			}
				
				//left and down 
				if(oldRow>newRow && oldCol<newCol) {
					int y = oldCol+1;  
							if(Math.abs(oldRow-newRow) == Math.abs(oldCol-newCol)) {
								for(int x = oldRow-1; x>=newRow +1; x--) {
								if(Chess.chessBoard[x][y] instanceof Pieces) {
									return false; 
								}
								y++;
							}
						return true;
						}
					}
						
				//right and up
				if(oldRow<newRow && oldCol>newCol) {
					int y =oldCol-1;
					if(Math.abs(oldRow-newRow) == Math.abs(oldCol-newCol)) {
						for(int x = oldRow+1; x<=newRow-1; x++) {
							if(Chess.chessBoard[x][y] instanceof Pieces) {
								return false;
							}
							y--;
						}
						return true;
					}
					
				}
				
				
				//left and up 
				if(oldRow>newRow && oldCol>newCol) {
					int y = oldCol-1;
					if(Math.abs(oldRow-newRow) == Math.abs(oldCol-newCol)) {
						for(int x = oldRow-1; x>= newRow+1; x--) {
							if(Chess.chessBoard[x][y] instanceof Pieces) {
								return false;
							}
							y--;
						}
						return true;
					}
				}
				
				return false;
			
	}//to string	 
		 
		 return false; 
}//method 
	
	
	
}