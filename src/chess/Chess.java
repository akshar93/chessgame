package chess;

import java.io.*; 

import chesscomponents.Queen;
import chesscomponents.Rook;
import chesscomponents.Bishop;
import chesscomponents.Board;
import chesscomponents.King;
import chesscomponents.Knight;

/*
 * 
 * @author Akshar Patel
 */
/*
 * This is the main class 
 * Player will put input and according to that steps and game will be display 
 */

public class Chess {

	//This is to print who's turn 
	public static final String white = "White's move: ";
	public static final String black = "Black's move: ";
	//Creating a Board class reference variable 
	public static Board b;
	public static King king;
	//This is used to check who's move 
	public static boolean whiteMove = true;
	public static boolean draw_check    = true;
	//Check for king's check count 
	public static int checkCount = 0;
	//Used for enPassant 
	public static boolean enPassnt = false;
	
	public static String[] sArr = new String[10];
	
	public static String pawnUp = "";
	public static String thirdEntry = "";
	
	//Used to store old row and column location that helps in enPassant
	public static int oldNrow = 0;
	public static int oldNcol = 0;
	
	public static void main(String[] args) throws IOException 
	{
		String loca , newLoca;
		b = new Board();
		b.setPieces();
		//b.printBoard();
		
		//Taking the input from keyboard
		InputStreamReader r=new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(r);
        String input = null;
        
        
       
        while(true) 
        {
        	//to let your opponent know that their king is in check
        	boolean ch = kingLocation(whiteMove);
    		if(ch == true)
    		{
    			//if king is in check then check if check mate or not
       			boolean gameOver = checkMate(whiteMove);   
       			//System.out.println("GameOver:" + gameOver);
            	if(gameOver == true)
            	{
            		b.printBoard();
            		b.createBoard();
            		if (whiteMove) 
            		 {
            			System.out.println();
            			System.out.println("\nCheckmate");
            			System.out.println("Black wins");
            		 }
            		 else 
            		 {
            			System.out.println();
            			System.out.println("\nCheckmate");
            			System.out.println("White wins");
            		 }
            		//b.createBoard();
            		 System.exit(1);
            	}
            	checkCount = checkCount + 1;
            	System.out.println("Check");
    		}	
    		
        	b.printBoard();
        	System.out.println();
        	//Print Whose turn is after printing a board
        	if(whiteMove) 
        	{
        		System.out.println();
        		System.out.print(white);
        	}
        	else
        	{
        		System.out.println();
        		System.out.print(black);
        	}
        	
        	//Read the input from the players 
        	input = br.readLine();
        	sArr = input.split("\\s");
        	//Here Check How many argument player enter if it's more then two then will print a Error message 
        	if(sArr.length == 0 || sArr.length > 4) 
        	{
        		System.out.println("Illigal move, try again");
        		continue;
        	}
        	//If argument is one then check ahead 
        	if(sArr.length == 1) 
        	{
        		//If argument is one and it's RESIGN then opponent will be win the game
        		if(sArr[0].equalsIgnoreCase("resign")) 
        		{
        			if(whiteMove) 
        			{
        				System.out.println("Black wins");
        				//System.out.println("Congratulation!!!");
        				return;
        			}
        			else
        			{
        				System.out.println("White wins");
        				//System.out.println("Congratulation!!!");
        				return;
        			}
        		}
        		else if(sArr[0].equalsIgnoreCase("draw") && draw_check)
        		{
        				System.out.println("Draw");
        				return;        			
        		} 
        		else
        		{
    				System.out.println("Illigal move, Try Again");
    				continue;
        		}   
        	}
        	//Taking care to play a regualr game as casual
        	if(sArr.length == 2)
        	{
        		loca = sArr[0];
        		newLoca = sArr[1];  
        		
        		moveToLocation(loca, newLoca);
        	}
        	//Taking care of draw game
        	if(sArr.length == 3)
        	{
        		loca = sArr[0];
        		newLoca = sArr[1];      		
        		thirdEntry = sArr[2];
        		
        		if(thirdEntry.equalsIgnoreCase("draw?"))
        		{
        			if(draw_check == true)
        			{
        				if(whiteMove)
        				{
        					whiteMove = false;
        				}
        				else
        				{
        					whiteMove = true;
        				}
        			}
        		}
        		
        		if(thirdEntry.equalsIgnoreCase("Q") || thirdEntry.equalsIgnoreCase("R") || thirdEntry.equalsIgnoreCase("N") || thirdEntry.equalsIgnoreCase("B"))
        		{
        			pawnUp = thirdEntry;
        			moveToLocation(loca, newLoca);
        			//Pawn.pawnUpgrade(thirdEntry , loca, newLoca);
        		}
        	}
        	
        	b.createBoard();
        }
        		
	}
	
	
	/*
	 * This method will check all input of player and will work according to that
	 * will check starting position of piece and destination and will move to that destination 
	 * Basically, game playing
	 */
	private static void moveToLocation(String loca, String newLoca) 
	{
		int row = Math.abs((int) loca.charAt(0) - (int)('a'));
		int newRow = Math.abs((int) newLoca.charAt(0) - (int)('a'));
		
		int col = Math.abs((int) loca.charAt(1) - (int)('1') - 7);
		int newCol = Math.abs((int)newLoca.charAt(1) - (int)('1') - 7);
		int colDif;
		
		
		
		boolean placeWhereMoving = true;
			
		//Error message if if the piece is user trying to move is null/empty
		if(b.board[row][col] == null)
		{
			System.out.println("Illegal move, try again");
			return;
		}
		
		if(b.board[row][col].isWhite() != whiteMove)
		{
			System.out.println("Illegal move, try again");
			return;
		}
		
		if(b.board[newRow][newCol]==null) {
			//System.out.println("Location is Null");
		}
		else {
			//System.out.println("Location is not null");
			if(b.board[newRow][newCol]!=null) {
				//System.out.println("New row and col outside :"+(b.board[newRow][newCol].isWhite()));
				//System.out.println("original row and col :"+(b.board[row][col].isWhite()));
				if(b.board[row][col].isWhite()==b.board[newRow][newCol].isWhite()) {
					System.out.println("Illegal move, try again");
					return;
				}
				placeWhereMoving = false;
			}
			
			
		}
		//This is checking for king castling case
		//If king and rook involved with their first move true
		if((b.board[row][col].getPieceName().equals("wK") || b.board[row][col].getPieceName().equals("bK")) && b.board[row][col].fMove == true)
		{
		//Check there is no any check given to king
		if(checkCount == 0)
		{
			//Calling to castling method which is written in a Board.java class
			boolean kingCastel = b.checkCastlingPath(row, col, newRow, newCol, whiteMove);
			if(kingCastel == true)
			{
				//if its a valid move than its your opponent turn
				if(whiteMove)
				{
					whiteMove = false;
				}
				else
				{
					whiteMove = true;
				}
				return;
			}
		}
			
		}
		//This is for enPassant case / Rule
		//If it's true then will be valid enPassant and have to be in the right next move
		if(enPassnt == true)
		{
			enPassnt = false;
			//System.out.println("Enpassat true ");
			int newX, newY;
			newX = Math.abs(newRow - row);
			newY = Math.abs(col - newCol);
			/*
			 * Having successful enPassant logic is here that
			 * diagonally move 
			 * newRow and newCol need to be null
			 * original column have to be a same as previous turn's pawn column set up (oldNcol)
			 * new row has to be same as previous turn's pawn's row(oldNrow)
			 * and newCol has to one minus into previous turn's pawn's col(oldNcol-1) for black side
			 * for white need to be(oldNcol + 1)
			 */
//			if(b.board[newRow][newCol] == null && newX == 1 && newY == 1)
			if((newX == 1 && newY == 1) && (b.board[newRow][newCol] == null) && (col == oldNcol && newRow == oldNrow) && (newCol == (oldNcol - 1)))
			{
				//System.out.println("Enpassant successfull!!");
				b.board[newRow][newCol] = b.board[row][col];
				
				b.board[row][col] = null;
				b.board[oldNrow][oldNcol] = null;
				if(whiteMove)
				{
					whiteMove = false;
				}
				else
				{
					whiteMove = true;
				}
				return;
			}
			//Checking for white
			else if((newX == 1 && newY == 1) && (b.board[newRow][newCol] == null) && (col == oldNcol && newRow == oldNrow) && (newCol == (oldNcol + 1)))
			{
				//System.out.println("Enpassant successfull!! 2 plus condition");
				//Set original row and column to new desit=nation
				b.board[newRow][newCol] = b.board[row][col];
				//set original position to null
				b.board[row][col] = null;
				//Also set pawn location which is registered into previous move to null
				//That means enPassant occur successfully 
				b.board[oldNrow][oldNcol] = null;
				//Checking for who's move right now and who's move will be next time
				if(whiteMove)
				{
					whiteMove = false;
				}
				else
				{
					whiteMove = true;
				}
				return;
			}
			
		}
		//Bhavin & Akshar En Passant
		//starting enPassant from here
		colDif = Math.abs(col-newCol);
		int rowP = newRow  + 1;
		int rowM = newRow - 1;
		int tempnewCol = newCol;
		/*
		 * This enPassnt logic is that
		 * check who's turn
		 * check pawn fisrt step is taken in steps
		 * after taking two steps check in same column but in +row and -row null or not
		 * after check if not null then check is it filled with opponent's pawn
		 * if yes then make enPassant = true;
		 * In next turn (enPassant == true)
		 * then player can use enPassant 
		 * if into next step player didn't use then after that player cannot use it 
		 * 
		 */
		
		// black condition.
		if(b.board[row][col].isWhite() != true)
		{
			// black condition.
			if(b.board[row][col].getPieceName().equals("bP") && colDif == 2)
			{
				//b.enPassantStateBlack(row,col,newRow,newCol);
				// black condition.
				//Taking care for last row == 7 to avoid "array out of bound exception"
				if(newRow == 7)
				{
					//System.out.println("newRow == 7");
					//Checking column is same and -row from newRow is null or not
					if(b.board[rowM][tempnewCol] != null)
					{
						//at -row and at same newCol location if there is opponent pawn then set enPassnt=true 
						if(b.board[rowM][tempnewCol].getPieceName().equals("wP"))
						{
							//System.out.println("Hello");
							//setting newRow and newCol into oldNrow and oldNcol as previous location to use into next turn
							enPassnt = true;
							oldNrow = newRow;
							oldNcol = newCol;
						}
					}
				}
				
				// black condition.
				//Taking care of first row==0 to avoid "array out of bound exception"
				if(newRow == 0)
				{
					//System.out.println("newRow == 0");
					if(b.board[rowP][tempnewCol] != null)
					{
						if(b.board[rowP][tempnewCol].getPieceName().equals("wP"))
						{
							//System.out.println("if statement");
							//setting newRow and newCol into oldNrow and oldNcol as previous location to use into next turn
							enPassnt = true;
							oldNrow = newRow;
							oldNcol = newCol;
						}

					}
				}
				
				// black condition.
				//for rest of all rows accepting first and last row
				if(newRow > 0 && newRow < 7)
				{
					//System.out.println("rest of the code from black condition");
					if(b.board[rowM][tempnewCol] != null)
					{
						if(b.board[rowM][tempnewCol].getPieceName().equals("wP"))
						{
							//System.out.println("Hello");
							//setting newRow and newCol into oldNrow and oldNcol as previous location to use into next turn
							enPassnt = true;
							oldNrow = newRow;
							oldNcol = newCol;
						}
					}
					
					// black condition.
					if(b.board[rowP][tempnewCol] != null)
					{
						if(b.board[rowP][tempnewCol].getPieceName().equals("wP"))
						{
							//System.out.println("if statement");
							//setting newRow and newCol into oldNrow and oldNcol as previous location to use into next turn
							enPassnt = true;
							oldNrow = newRow;
							oldNcol = newCol;
						}

					}
				}
				
			}
		} // Black condition ends here.
		//enPassant checking for white and this is quite same as black
		// White condition starts here.
		else
		{
			//System.out.println("White's turn white condition.");
			//Check piece is white pawn and have two step at first move
			if(b.board[row][col].getPieceName().equals("wP") && colDif == 2)
			{
				//Taking care for last row == 7 to avoid "array out of bound exception"
				if(newRow == 7)
				{
					//System.out.println("newRow == 7");
					
					if(b.board[rowM][tempnewCol] != null)
					{
						if(b.board[rowM][tempnewCol].getPieceName().equals("bP"))
						{
							//System.out.println("Hello from white");
							//setting newRow and newCol into oldNrow and oldNcol as previous location to use into next turn
							enPassnt = true;
							oldNrow = newRow;
							oldNcol = newCol;
						}
					}
				}
				
				if(newRow == 0)
				{
					//System.out.println("newRow == 0");
					if(b.board[rowP][tempnewCol] != null)
					{
						if(b.board[rowP][tempnewCol].getPieceName().equals("bP"))
						{
							//System.out.println("if statement from white");
							//setting newRow and newCol into oldNrow and oldNcol as previous location to use into next turn
							enPassnt = true;
							oldNrow = newRow;
							oldNcol = newCol;
						}

					}
				}
				
				if(newRow > 0 && newRow < 7)
				{
					//System.out.println("rest of the code from white condition");
					if(b.board[rowM][tempnewCol] != null)
					{
						if(b.board[rowM][tempnewCol].getPieceName().equals("bP"))
						{
							//System.out.println("Hello from white");
							//setting newRow and newCol into oldNrow and oldNcol as previous location to use into next turn
							enPassnt = true;
							oldNrow = newRow;
							oldNcol = newCol;
						}
					}
					
					// white condition.
					if(b.board[rowP][tempnewCol] != null)
					{
						if(b.board[rowP][tempnewCol].getPieceName().equals("bP"))
						{
							//System.out.println("if statement from white");
							//setting newRow and newCol into oldNrow and oldNcol as previous location to use into next turn
							enPassnt = true;
							oldNrow = newRow;
							oldNcol = newCol;
						}

					}
				}
			}
		}//Done with enPassant
		
		
		//to see if user move is valid or not depending on pieces
		if(b.board[row][col].checkIfValidMove(row , col , newRow , newCol , placeWhereMoving) == false)
		{
			System.out.println("Illegal move, try again");
			return;
		}
		
		//Check a path is clear or not for Queen, bishop and rook
		if((b.board[row][col].getPieceName().equals("bR"))
				||(b.board[row][col].getPieceName().equals("wR"))
				||(b.board[row][col].getPieceName().equals("wB")) 
				|| (b.board[row][col].getPieceName().equals("bB")) 
				|| (b.board[row][col].getPieceName().equals("wQ")) 
				|| (b.board[row][col].getPieceName().equals("bQ")) )
		{
			boolean check = b.checkPath(row,col,newRow,newCol);
			if(check == false) {
				System.out.print("Illegal move, try again");
				return;
			}
			
		}
		
		if(b.board[row][col].getPieceName().equals("wK") || b.board[row][col].getPieceName().equals("bK"))
		{
			boolean check = checkIfKingInCheck(whiteMove, newRow, newCol);
			if(check == true)
			{
				/*if (whiteMove) 
       		 	{
       			System.out.println("Checkmate");
       			System.out.println("Black wins");
       		 	}
				if(!whiteMove)
       		 	{
       			System.out.println("Checkmate");
       			System.out.println("White wins");
       		 	}
       		 	System.exit(1);*/
				System.out.println("Illegal move, try again");
				return;
			}
		}
		
		//setting new position to old position
		b.board[newRow][newCol] = b.board[row][col];
		
		//setting first move to false
		b.board[newRow][newCol].fMove = false;
		
		if(sArr.length >= 2)
		{
			if(pawnUp == null|| pawnUp.equalsIgnoreCase("Q") || pawnUp.equalsIgnoreCase("R") || pawnUp.equalsIgnoreCase("N") || pawnUp.equalsIgnoreCase("B"))
				{
					if(b.board[newRow][newCol].getPieceName().equals("wP")
							|| b.board[newRow][newCol].getPieceName().equals("bP"))
					{
						pawnUpgrade(pawnUp, newRow, newCol);
					}
				}
		}
		
	
		
		//setting old position to null
		b.board[row][col] = null;
		
		//if its a valid move than its your opponent turn
		if(whiteMove)
		{
			whiteMove = false;
		}
		else
		{
			whiteMove = true;
		}
		//whiteMove=!(whiteMove);	
		//System.out.println("White move"+whiteMove);
		return;
	}
	
	/*
	 * this method will let pawn to get promoted by user's choice such as Rook, Bishop, Knight, and Queen 
	 * if Not specified by user then pawn will get promoted to Queen by default
	 */
	public static  void pawnUpgrade(String pawnUp, int newRow, int newCol) 
	{
		
		if(b.board[newRow][newCol].getPieceName().equals("wP") && newCol == 0)
		{
			if(pawnUp.equalsIgnoreCase("R"))
			{
				b.board[newRow][newCol] = new Rook("white");
			}
			else if(pawnUp.equalsIgnoreCase("N"))
			{
				b.board[newRow][newCol] = new Knight("white");
			}
			else if(pawnUp.equalsIgnoreCase("B"))
			{
				b.board[newRow][newCol] = new Bishop("white");
			}
			else if(pawnUp.equalsIgnoreCase("Q"))
			{
				b.board[newRow][newCol] = new Queen("white");
			}
			else
			{
				b.board[newRow][newCol] = new Queen("white");
			}
		}
		
		if(b.board[newRow][newCol].getPieceName().equals("bP") && newCol == 7)
		{
			if(pawnUp.equalsIgnoreCase("R"))
			{
				b.board[newRow][newCol] = new Rook("black");
			}
			else if(pawnUp.equalsIgnoreCase("N"))
			{
				b.board[newRow][newCol] = new Knight("black");
			}
			else if(pawnUp.equalsIgnoreCase("B"))
			{
				b.board[newRow][newCol] = new Bishop("black");
			}
			else if(pawnUp.equalsIgnoreCase("Q"))
			{
				b.board[newRow][newCol] = new Queen("black");
			}
			else
			{
				b.board[newRow][newCol] = new Queen("black");
			}
		}
	}
	
	/*
	 * this method will find king's location in board
	 * Depending on who's turn is it
	 * if white's turn then white king's location will be returned
	 * if black's turn then black king's location will be returned
	 */
	public static boolean kingLocation(boolean checkMoveColor)
	{
		int row, col;
		row = 0;
		col = 0;
		
		if(checkMoveColor == true)
		{
			for(int i=0; i<8;i++)
			{
				for(int j=0; j<8;j++)
				{
					if(b.board[j][i] != null && b.board[j][i].getPieceName().equals("wK"))
					{
						row = j;
						col = i;
						break;
					}
				}
			}
		}
		else
		{
			for(int i=0; i<8;i++)
			{
				for(int j=0; j<8;j++)
				{
					if(b.board[j][i] != null && b.board[j][i].getPieceName().equals("bK"))
					{
						row = j;
						col = i;
						break;
					}
				}
			}
		}
		
		//to see if king is in check
		return checkIfKingInCheck(checkMoveColor, row , col);
	}
	
	/*
	 * this method will find king's location in board
	 * Depending on who's turn is it
	 * if white's turn then white king's location will be returned
	 * if black's turn then black king's location will be returned
	 * then This will check all possible location where king can be moved
	 * and if king would move to that location then check if king will be in check or not
	 * if all the conditions are true then check mate
	 */
	public static boolean checkMate(boolean checkMoveColor)
	{
		int row, col;
		row = 0;
		col = 0;
		
		boolean moveUp = false, moveDown = false, moveRight= false, moveLeft= false , moveRightUp= false, moveLeftUp= false, moveDownRight= false, moveDownLeft=false;
		boolean currLocation = false;
		
		if(checkMoveColor == true)
		{
			for(int i=0; i<8;i++)
			{
				for(int j=0; j<8;j++)
				{
					if(b.board[j][i] != null && b.board[j][i].getPieceName().equals("wK"))
					{
						row = j;
						col = i;
						break;
					}
				}
			}
			
			int c = col + 1; 
			
			if(c > 7) {moveDown = true;}
			else 
			{
				if(b.board[row][col+1] != null)
				{
					if(b.board[row][col+1].isWhite() == true)
					{
						moveDown = true;
					}
					else
					{
						moveDown = checkIfKingInCheck(checkMoveColor, row, col+1);
					}
				}
				else
				{
					moveDown = checkIfKingInCheck(checkMoveColor, row, col+1);
				}
			}
			
			if(c > 7 || (row+1) > 7) {moveDownRight = true;}
			else 
			{
				if(b.board[row+1][col+1] != null)
				{
					if(b.board[row+1][col+1].isWhite() == true)
					{
						moveDownRight = true;
					}
					else
					{
						moveDownRight = checkIfKingInCheck(checkMoveColor, row+1 , col+1);
					}
				}
				else
				{
					moveDownRight = checkIfKingInCheck(checkMoveColor, row+1 , col+1);
				}
			}
		
			if(c > 7 || (row - 1) < 0) {moveDownLeft = true;}
			else 
			{
				if(b.board[row-1][col+1] != null)
				{
					if(b.board[row-1][col+1].isWhite() == true)
					{
						moveDownLeft = true;
					}
					else
					{
						moveDownLeft = checkIfKingInCheck(checkMoveColor, row-1 , col+1);
					}
				}
				else
				{
					moveDownLeft = checkIfKingInCheck(checkMoveColor, row-1 , col+1);
				}
			}
			
		
			if(b.board[row][col-1] != null)
			{
			if(b.board[row][col-1].isWhite() == true){moveUp= true;}
			else{moveUp = checkIfKingInCheck(checkMoveColor, row , col-1);}
			}
			else
			{
				moveUp = checkIfKingInCheck(checkMoveColor, row , col-1);
			}
			
			if((row+1) > 7) {moveRight = true;}
			else {
			if(b.board[row+1][col] != null)
			{
			if(b.board[row+1][col].isWhite() == true ){moveRight= true;}
			else{moveRight = checkIfKingInCheck(checkMoveColor, row +1, col);}
			}
			else
			{
				moveRight = checkIfKingInCheck(checkMoveColor, row +1, col);
			}
			}
			
			if((row - 1) < 0) {moveLeft = true;}
			else {
			if(b.board[row-1][col] != null)
			{
			if(b.board[row-1][col].isWhite() == true){moveLeft= true;}
			else {moveLeft = checkIfKingInCheck(checkMoveColor, row -1, col);}
			}
			else
			{
				moveLeft = checkIfKingInCheck(checkMoveColor, row -1, col);
			}
			}
			
			if((row+1) > 7) {moveRightUp = true;}
			else {
			if(b.board[row+1][col-1] != null)
			{
			if(b.board[row+1][col-1].isWhite() == true ){moveRightUp= true;}
			else {moveRightUp = checkIfKingInCheck(checkMoveColor, row+1 , col-1);}
			}
			else
			{
				moveRightUp = checkIfKingInCheck(checkMoveColor, row+1 , col-1);
			}
			}
				
			if((row-1) < 0) {moveLeftUp = true;}
			else {
			if(b.board[row-1][col-1] != null)
			{
			if(b.board[row-1][col-1].isWhite() == true){moveLeftUp= true;}
			else {moveLeftUp = checkIfKingInCheck(checkMoveColor, row-1 , col-1);}
			}
			else
			{
				moveLeftUp = checkIfKingInCheck(checkMoveColor, row-1 , col-1);
			}
			}
			
			
			
		}
		
		if(checkMoveColor == false)
		{
			for(int i=0; i<8;i++)
			{
				for(int j=0; j<8;j++)
				{
					if(b.board[j][i] != null && b.board[j][i].getPieceName().equals("bK"))
					{
						row = j;
						col = i;
						break;
					}
				}
			}
			
			int c = col - 1;
			
			if(c < 0) {moveDown = true;}
			else 
			{
				if(b.board[row][col-1] != null)
				{
					if(b.board[row][col-1].isWhite() == false)
					{
						moveDown = true;
					}
					else
					{
						moveDown = checkIfKingInCheck(checkMoveColor, row, col-1);
					}
				}
				else
				{
					moveDown = checkIfKingInCheck(checkMoveColor, row, col-1);
				}
			}
			
			if(c < 0 || (row+1) >7) {moveDownRight = true;}
			else 
			{
				if(b.board[row+1][col-1] != null)
				{
					if(b.board[row+1][col-1].isWhite() == false)
					{
						moveDownRight = true;
					}
					else
					{
						moveDownRight = checkIfKingInCheck(checkMoveColor, row+1 , col-1);
					}
				}
				else
				{
					moveDownRight = checkIfKingInCheck(checkMoveColor, row+1 , col-1);
				}
			}
			
			if(c < 0 || (row - 1) < 0) {moveDownLeft = true;}
			else 
			{
				if(b.board[row-1][col-1] != null)
				{
					if(b.board[row-1][col-1].isWhite() == false)
					{
						moveDownLeft = true;
					}
					else
					{
						moveDownLeft = checkIfKingInCheck(checkMoveColor, row-1 , col-1);
					}
				}
				else
				{
					moveDownLeft = checkIfKingInCheck(checkMoveColor, row-1 , col-1);
				}
			}
				
				if(b.board[row][col+1] != null)
				{
				if(b.board[row][col+1].isWhite() == false){moveUp= true;}
				else{moveUp = checkIfKingInCheck(checkMoveColor, row , col+1);}
				}
				else
				{
					moveUp = checkIfKingInCheck(checkMoveColor, row , col+1);
				}	
				
				if((row+1) > 7) {moveRight = true;}
				else {
				if(b.board[row+1][col] != null)
				{
				if(b.board[row+1][col].isWhite() == false){moveRight= true;}
				else{moveRight = checkIfKingInCheck(checkMoveColor, row+1 , col);}
				}
				else
				{
					moveRight = checkIfKingInCheck(checkMoveColor, row+1 , col);
				}	
				}
			
				if((row-1) < 0) {moveLeft = true;}
				else {
				if(b.board[row-1][col] != null)
				{
				if(b.board[row-1][col].isWhite() == false){moveLeft= true;}
				else{moveLeft = checkIfKingInCheck(checkMoveColor, row -1, col);}
				}
				else
				{
					moveLeft = checkIfKingInCheck(checkMoveColor, row -1, col);
				}	
				}
			
				if((row+1) > 7) {moveRightUp = true;}
				else {
				if(b.board[row+1][col+1] != null)
				{
				if(b.board[row+1][col+1].isWhite() == false){moveRightUp= true;}
				else{moveRightUp = checkIfKingInCheck(checkMoveColor, row+1 , col+1);}
				}
				else
				{
					moveRightUp = checkIfKingInCheck(checkMoveColor, row+1 , col+1);
				}	
				}
				
				if((row-1) < 0) {moveLeftUp = true;}
				else {
				if(b.board[row-1][col+1] != null)
				{
				if(b.board[row-1][col+1].isWhite() == false){moveLeftUp = true;}
				else{moveLeftUp = checkIfKingInCheck(checkMoveColor, row-1 , col+1);}
				}
				else
				{
					moveLeftUp = checkIfKingInCheck(checkMoveColor, row-1 , col+1);
				}	
				}

			}
	
		/*if(checkCount>0)
		{
		currLocation = checkIfKingInCheck(checkMoveColor, row , col);
		return currLocation;
		}*/
		
		if(moveUp==true && moveDown==true && moveRight==true && moveLeft==true && moveRightUp==true && moveDownRight==true && moveLeftUp==true && moveDownLeft==true )
		{
			return true;
		}
		
		return false;
	}
	
	/*
	 * this method previously gets king's location from kingLocation method depending on its color
	 * it will check if every other color pieces put king in check or not
	 * if true then result will be transfered to main where it will print check to let player know that their king is in check
	 */
	public static  boolean checkIfKingInCheck(boolean checkMoveColor, int row, int col) 
	{	
	
		if(checkMoveColor == true)
		{
			for(int i =0;i<8;i++)
			{
				for(int j = 0; j<8;j++)
				{
					if(b.board[j][i] != null && b.board[j][i].isWhite() == false)
					{
						if(b.board[j][i].getPieceName().equals("bQ")
								|| b.board[j][i].getPieceName().equals("bB")
								||b.board[j][i].getPieceName().equals("bR"))
						{
							if(b.checkPath(j,i,row,col) == true)
							{
								if(b.board[j][i].checkIfValidMove(j, i, row, col, true) == true)
								{
									return true;
								}
							}
						}
						
						if(b.board[j][i].getPieceName().equals("bN"))
						{
							int newX = Math.abs(row - j);
							int newY = Math.abs(i - col);
							
							if((newX == 2 && newY ==1) || (newX == 1 && newY == 2))
							{
								return true;
							}
						
						}
						
						if(b.board[j][i].getPieceName().equals("bP"))
						{
							int newXnoAbs = (row - j);
							int newYnoAbs = (i - col);
							
							//cross move
							if(newXnoAbs == -1 && newYnoAbs == -1)
							{
								return true;
							}
							
							if(newXnoAbs == 1 && newYnoAbs == -1)
							{
								return true;
							}
						}
					}
				}
			}
		}
		
		
		
		if(checkMoveColor == false)
		{	
			for(int i =0;i<8;i++)
			{
				for(int j = 0; j<8;j++)
				{
					if(b.board[j][i] != null && b.board[j][i].isWhite() == true)
					{
						if(b.board[j][i].getPieceName().equals("wQ")
								|| b.board[j][i].getPieceName().equals("wB")
								||b.board[j][i].getPieceName().equals("wR"))
						{
							if(b.checkPath(j,i,row,col) == true)
							{
								if(b.board[j][i].checkIfValidMove(j, i, row, col, true) == true)
								{
									return true;
								}
							}
						}
						
						if(b.board[j][i].getPieceName().equals("wN"))
						{
							int newX = Math.abs(row - j);
							int newY = Math.abs(i - col);
							
							if((newX == 2 && newY ==1) || (newX == 1 && newY == 2))
							{
								return true;
							}
						}
						
						if(b.board[j][i].getPieceName().equals("wP"))
						{
							int newXnoAbs = (row - j);
							int newYnoAbs = (col - i);
							
							//cross move
							if(newXnoAbs == -1 && newYnoAbs == -1 )
							{
								return true;
							}
							
							if(newXnoAbs == 1 && newYnoAbs == -1)
							{
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
}
	

