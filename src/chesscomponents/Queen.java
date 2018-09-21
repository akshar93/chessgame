package chesscomponents;

/*
 * 
 * @author Akshar Patel
 */

/*
 * This class is extending an abstract class Piece
 * This class have all type of behavior of queen, color, name and valid movement
 */
public class Queen extends Piece {

	private String name;
	private String color = "";
	public Queen(String color)
	{
		this.setColor(color);
		checkColor(color);
	}
	public void checkColor(String color) {
		if (color.equals("white")) {
			this.name = "wQ";
		}
		else {
			this.name = "bQ"; 
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see chesscomponents.Piece#getPieceName()
	 * return the appropriate name
	 */
	public String getPieceName() 
	{
		return this.name;
	}
	@Override
	/*
	 * (non-Javadoc)
	 * @see chesscomponents.Piece#checkIfValidMove(int, int, int, int, boolean)
	 * will check the valid movement of queen will return true if it's valid move otherwise false
	 */
	public boolean checkIfValidMove(int row, int col, int newRow, int newCol, boolean emptySpace) {
		// TODO Auto-generated method stub
		int newX=0;
		int newY=0;
		newX = Math.abs(newRow-row);
		newY = Math.abs(col-newCol);
		//Check diagonally row and column will be the same
		if(newX == newY) {
			return true;
		}
		//Check moving up-down So, row remain same and column will be the different 
		else if(newX == 0 && newY != 0) {
			return true;
		}
		//check right-left So, column is same and row will be different
		else if(newX != 0 && newY == 0) {
			return true;
		}
		
		return false;
	}
}
