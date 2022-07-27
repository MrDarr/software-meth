package chess;

public class Bishop extends Pieces{
	//pieceType bishop;

	Bishop(boolean white ){
		super(white);
		
	}

	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		if(white) {
			return "wB";
			
		}
		
			
		return "bB";
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
			if(toString()=="wB") {
			//	System.out.println(newCol+" "+ oldCol);
				if(((newCol!=oldCol)) &&(oldRow-newRow)==1) { // moves two spaces up and one to the right or left
			//	System.out.println(newCol+" "+ oldCol);
					if(newCol==oldCol+1 ) {
						
					
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


//public pieceType getBishop() { //get bishop method 
//	return bishop;
//}