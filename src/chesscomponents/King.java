package chesscomponents;

/*
 *
 * @author Akshar Patel
 */

/*
 * This class is extending an abstract class Piece
 *  
 */
public class King extends Piece {
	
	private String name;
	private String color = "";
	public static Piece b[][];
	public static Board board;
	
	public King(String color)
	{
		this.setColor(color);
		checkColor(color);
	}
	public void checkColor(String color) {
		if (color.equals("white")) {
			this.name = "wK";
		}
		else {
			this.name = "bK"; 
		}
	}
	/*
	 * (non-Javadoc)
	 * @see chesscomponents.Piece#getPieceName()
	 * Returning the name of king that it's white king or black king
	 */
	public String getPieceName() 
	{
		return this.name;
	}
	/*
	 * (non-Javadoc)
	 * @see chesscomponents.Piece#checkIfValidMove(int, int, int, int, boolean)
	 * Overridden method will take care of king's valid move
	 */
	@Override
	public boolean checkIfValidMove(int row, int col, int newRow, int newCol, boolean emptySpace) {
		// TODO Auto-generated method stub
		int newX=0;
		int newY=0;
		newX = Math.abs(newRow-row);
		newY = Math.abs(col-newCol);
		
		//King move right and left
		//if column same and row difference is = 1
		if(newY == 0 && newX == 1)
		{
			return true;
		}
		//King move up and down
		//if row remain same and column difference is = 1
		else if(newX == 0 && newY == 1)
		{
			return true;
		}
		//King cross move
		//Diagonally row and column has to be same but need only 1 step so have a diffrence between row and column is = 1
		else if((newX == newY) && (newX ==1 && newY == 1)) 
		{
			return true;
		}
		
		return false;
	}
	
}
