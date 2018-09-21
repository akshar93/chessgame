package chesscomponents;

/*
 * 
 * @author Akshar Patel
 */
/*
 *This is the class for knight class  
 * Also created a valid name according to it's position 
 * valid move method will check the move is valid or not will return boolean type
 * 
 */

public class Knight extends Piece {

	private String name;
	private String color = "";
	public Knight(String color)
	{
		this.setColor(color);
		checkColor(color);
	}
	public void checkColor(String color) {
		if (color.equals("white")) {
			this.name = "wN";
		}
		else {
			this.name = "bN"; 
		}
	}
	/*
	 * (non-Javadoc)
	 * @see chesscomponents.Piece#getPieceName()
	 * will return the piece name
	 */
	public String getPieceName() 
	{
		return this.name;
	}
	@Override
	/*
	 * (non-Javadoc)
	 * @see chesscomponents.Piece#checkIfValidMove(int, int, int, int, boolean)
	 * check the move if it's valid return true
	 */
	public boolean checkIfValidMove(int row, int col, int newRow, int newCol, boolean emptySpace) {
		// TODO Auto-generated method stub
		int newX=0;
		int newY=0;
		newX = Math.abs(newRow-row);
		newY = Math.abs(col-newCol);
		//Knight have a 8 type of movements in up-down and right-left side
		//So, check the row and column difference according to that and here came up with two different possibilities for knight's 8 moves 
		if((newX == 2 && newY ==1) || (newX == 1 && newY == 2))
		{
			return true;
		}
		
		return false;
	}
}
