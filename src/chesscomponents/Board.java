package chesscomponents;

import chesscomponents.Piece;
import chesscomponents.Pawn;

/*
 * 
 * @author Akshar Patel
 */

/*
 * This class will contain a methods to print board,to assign piece location on the board, to check the clear path for piece movement, 
 */
public class Board {
	
	//Creating this variable for creating a board and specially for " " and ## 
	public String[][] nextBoard = new String[8][8];
	//Creating a array obj of piece class
	public Piece[][] board;
	
	public Board()
	{	
		board = new Piece[8][8];
		//Creating a board
		createBoard();
	}
	
	
	
	/*
	 * This method is checking a path of piece from original location to it's destination 
	 * Checking that is path is clear or not 
	 * This method is checking path for queen,bishop and rook
	 */
	public boolean checkPath(int row, int col, int newRow, int newCol)
	{
		//System.out.println("Original row :"+ row + " New Row :"+newRow);
		//System.out.println("Original col :"+ col + " New Col :"+newCol);
		int newX = Math.abs(newRow-row);
		int newY = Math.abs(col-newCol);
		//System.out.println(newX+ "And"+newY);
		
		//Here checking the number-1 condition of Queen Movement up-down
		//Queen up movement
		if(newX == 0 && newY != 0)
		{
		if(row == newRow &&  newCol < col)
		 {
			int tempR=row;
			int tempC=col;
			int teampNRow = newRow;
			int tempNCol  = newCol;
			//System.out.println("in if");
			//System.out.println("Original row :"+ row + " New Row :"+newRow);
			//System.out.println("Original col :"+ col + " New Col :"+newCol);
			int size = Math.abs(newCol-col);
			//System.out.println(size);
			tempC = tempC - 1;
			for(int i= 0; i<size-1; i++)
			{
				if(board[tempR][tempC]== null)
				{
					tempC = tempC - 1;
				}
				else
				{
					return false;
				}
			}
			return true;
		}
		//Queen down movement
		
		else if(row == newRow &&  newCol > col)
		{
			int tempR=row;
			int tempC=col;
			int teampNRow = newRow;
			int tempNCol  = newCol;
			int size = Math.abs(newCol-col);
			tempC = tempC + 1;
			for(int i= 0; i<size - 1; i++)
			{
				if(board[tempR][tempC]== null)
				{
					tempC = tempC + 1;
				}
				else
				{
					return false;
				}
			}
			return true;
		 }
		}//close if(newX == 0 && newY != 0) Number 1
		
		//Here Checking Number-2 condition for queen movement right-left
		//Check right and left movement
		//This is for right side movement
		if(newX != 0 && newY == 0)
		{
		 if(row < newRow && col == newCol)
		 {
			int tempR=row;
			int tempC=col;
			int teampNRow = newRow;
			int tempNCol  = newCol;
			int size = Math.abs(newRow-row);
			tempR = tempR + 1;
			
			for(int i= 0; i<size - 1; i++)
			{
				if(board[tempR][tempC] == null)
				{
					tempR = tempR + 1;
				}
				else
				{
					return false;
				}
			}
			return true;
		}
		//This movement is for left side movement
		else if(row > newRow && col == newCol)
		{
			int tempR=row;
			int tempC=col;
			int teampNRow = newRow;
			int tempNCol  = newCol;
			int size = Math.abs(newRow-row);
			tempR = tempR - 1;
			for(int i= 0; i<size-1; i++)
			{
				if(board[tempR][tempC]== null)
				{
					tempR = tempR - 1;
				}
				else
				{
					return false;
				}
			}
			return true;
		}
	  }//end if(newX != 0 && newY == 0) Number 2
	
	//Number 3 Check movement in a diagonal 
	if(newX == newY)
	{
		//white up-->left And black down-->left Num-1
		if(newRow < row && newCol < col)
		{
			int tempR=row;
			int tempC=col;
			int teampNRow = newRow;
			int tempNCol  = newCol;
			int size = Math.abs(newCol-col);
			tempR = tempR-1;
			tempC = tempC-1;
			for(int i = 0; i < size-1; i++)
			{
				if(board[tempR][tempC]==null)
				{
					tempR = tempR-1;
					tempC = tempC-1;
				}
				else
				{
					//System.out.println("You cannot jump1!!!");
					return false;
				}
			}
			return true;
		}//End White Up-Left Num -1
		//white down-->left And black up-->left Num-2		
		else if(newRow > row &&newCol > col)
		{
			int tempR=row;
			int tempC=col;
			int teampNRow = newRow;
			int tempNCol  = newCol;
			int size = Math.abs(newCol-col);
			tempR = tempR+1;
			tempC = tempC+1;
			for(int i = 0; i < size-1; i++)
			{
				if(board[tempR][tempC]==null)
				{
					tempR = tempR+1;
					tempC = tempC+1;
				}
				else
				{
					//System.out.println("You cannot jump2!!!");
					return false;
				}
			}
			return true;
			
		}//End with the down-left Num-2
		//white up-->right And black up-->right Num-3
		else if(newRow > row && newCol < col)
		{
			int tempR=row;
			int tempC=col;
			int teampNRow = newRow;
			int tempNCol  = newCol;
			int size = Math.abs(newCol-col);
			tempR = tempR+1;
			tempC = tempC-1;
			for(int i = 0; i < size-1; i++)
			{
				if(board[tempR][tempC]==null)
				{
					tempR = tempR+1;
					tempC = tempC-1;
				}
				else
				{
					//System.out.println("You cannot jump3!!!");
					return false;
				}
			}
			return true;
			
		}//End with Num-3
		//down-->right And black down-->right Num-4
		else if(newRow < row && newCol > col)
		{
			int tempR=row;
			int tempC=col;
			int teampNRow = newRow;
			int tempNCol  = newCol;
			int size = Math.abs(newCol-col);
			tempR = tempR-1;
			tempC = tempC+1;
			for(int i = 0; i < size-1; i++)
			{
				if(board[tempR][tempC]==null)
				{
					tempR = tempR-1;
					tempC = tempC+1;
				}
				else
				{
					//System.out.println("You cannot jump4!!!");
					return false;
				}
				
			}
			return true;
			
		}//End Num-4
	}// End if(newX == newY) Number 3
	return false;
	
}
		
		
	

	
	/*
	 * Here Printing the chess board using black spot"##" and white spot " "
	 */
	public void createBoard() {
		boolean space = true;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (space) {
					nextBoard[i][j] = "   ";
					space = false;
				}
				else {
					nextBoard[i][j] = "## ";
					space = true;
				}
				//System.out.print(nextBoard[i][j]);
			}
			if(space){
				space = false;
			}
			else{
				space = true;
			}
			//System.out.println();
		}			
	}
	
	/*
	 * This method is setting a piece on the board on the appropriate location
	 */
	public void setPieces() {
		//Set the value null in the board for all rows and columns 
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				board[i][j] = null;
			}
		}	
		
		//Set black pawn on appropriate location using rows and columns
		for(int j=0; j<8;j++){
			board[j][1] = new Pawn("black");
		}
		//Set white pawn on appropriate location using rows and columns
		for(int j =0; j<8;j++){
			board[j][6] = new Pawn("white");
		}
		
	

		//Set the Black Pieces on board 
		board[0][0] = new Rook("black");
		board[1][0]	= new Knight("black");
		board[2][0] = new Bishop("black");
		board[3][0]	= new Queen("black");
		board[4][0]	= new King("black");
		board[5][0] = new Bishop("black");
		board[6][0]	= new Knight("black");
		board[7][0] = new Rook("black");
		
		//Set the White Pieces on board
		board[7][7] = new Rook("white");
		board[6][7]	= new Knight("white");
		board[5][7]	= new Bishop("white");
		board[3][7]	= new Queen("white");
		board[4][7]	= new King("white");
		board[2][7]	= new Bishop("white");
		board[1][7]	= new Knight("white");
		board[0][7]	= new Rook("white");
		
		
		
		
	}
	/*
	 * This method is printing a board with complete setup of pieces on the board
	 */
	public void printBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				//Set piece on location
				if (board[j][i] != null) {
					nextBoard[j][i] = board[j][i].getPieceName() + " ";
				}
			}
		}
		
		System.out.println(" ");
		//Print board location with piece setup
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				System.out.print(nextBoard[x][y]);
			}
			// Print number values on the last column to signify which column
			System.out.print("" + (8-y));
			System.out.println();
		}
		
		// Print letters under the board to signify the rows
		for(char ch = 'a'; ch<'i'; ch++){
			System.out.print(" "+ch+" ");
			
		}
		
	}

	/*
	 * This method will check valid castling 
	 */
	public boolean checkCastlingPath(int row, int col, int newRow, int newCol, boolean whiteMove)
	{
		
		int newX = Math.abs(newRow-row);
		int newY = Math.abs(newCol-col);
		int newR = (newRow-row);
		
		//This is checking a valid castling on white side 
		if(whiteMove == true)
		{
			//right side castling
			if(newY == 0 && newR > 0)
			{
				int tempRow = row;
				int tempCol = col;
				//If king's and rook's first move is true means there are no any moves occure before this move is going to occur
				if(board[7][7].fMove == true && board[4][7].fMove==true)
				{
					//Allowing castling
					int size = Math.abs(row - 7);
					tempRow = tempRow + 1;
					//Checking is anything into path or not
					//Checking path is clear or not
					for(int i = 0; i < size-1; i++)
					{
						if(board[tempRow][tempCol] == null)
						{
							tempRow = tempRow + 1;
						}
						else
						{
							return false;
						}
					}
					//king location
					board[newRow][newCol] = board[row][col];
					//for rook location
					board[5][7] = board[7][7];
					
					board[newRow][newCol].fMove = false;
					board[5][7].fMove = false;
					
					board[row][col] = null;
					board[7][7] = null;
					return true;
				}	
			}
			
			//left side castling
			if(newY == 0 && newR < 0)
			{
				int tempRow = row;
				int tempCol = col;
				//Need king's and rook's first move and no any moves happened before this
				if(board[0][7].fMove == true && board[4][7].fMove==true)
				{
					//Allowing castling
					int size = Math.abs(row - 0);
					tempRow = tempRow - 1;
					//Checking for path is clear or not
					for(int i = 0; i < size-1; i++)
					{
						if(board[tempRow][tempCol] == null)
						{
							tempRow = tempRow - 1;
						}
						else
						{
							return false;
						}
					}
					//king location
					board[newRow][newCol] = board[row][col];
					//for rook location
					board[3][7] = board[0][7];
				
					board[newRow][newCol].fMove = false;
					board[3][7].fMove = false;
				
					board[row][col] = null;
					board[0][7] = null;
					return true;
				}
			
			}
		}
		//This is checking a valid castling on Black side 
		else
		{
			//black right side
			if(newY == 0 && newR > 0)
			{
				int tempRow = row;
				int tempCol = col;
				if(board[7][0].fMove == true && board[4][0].fMove==true)
				{
					//Allowing castling
					int size = Math.abs(row - 7);
					tempRow = tempRow + 1;
					//check that path is clear or not
					for(int i = 0; i < size-1; i++)
					{
						if(board[tempRow][tempCol] == null)
						{
							tempRow = tempRow + 1;
						}
						else
						{
							return false;
						}
					}
					//king location
					board[newRow][newCol] = board[row][col];
					//for rook location
					board[5][0] = board[7][0];
				
					board[newRow][newCol].fMove = false;
					board[5][0].fMove = false;
				
					board[row][col] = null;
					board[7][0] = null;
					return true;
				}
			
			}
			
			//black left side
			if(newY == 0 && newR < 0)
			{
				int tempRow = row;
				int tempCol = col;
				if(board[0][0].fMove == true && board[4][0].fMove==true)
				{
					//Allowing castling
					int size = Math.abs(row - 0);
					tempRow = tempRow - 1;
					//Checking for path is clear or not
					for(int i = 0; i < size-1; i++)
					{
						if(board[tempRow][tempCol] == null)
						{
							tempRow = tempRow - 1;
						}
						else
						{
							return false;
						}
					}
					//king location
					board[newRow][newCol] = board[row][col];
					//for rook location
					board[3][0] = board[0][0];
				
					board[newRow][newCol].fMove = false;
					board[3][0].fMove = false;
				
					board[row][col] = null;
					board[0][0] = null;
					return true;
				}
			
			}
		}
		
		
		return false;
		
		
	}
}
		
	
	
	
	
	
	

