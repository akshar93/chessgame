package chesscomponents;

/*
 * 
 * @author Akshar Patel
 */

/*
 * This class is extending an abtract class Piece
 * This class will give all behaviour, name, color, and movement of rook 
 */

public class Rook extends Piece{

	private String name;
	private String color = "";
	public Rook(String color)
	{
		this.setColor(color);
		checkColor(color);
	}
	public void checkColor(String color) {
		if (color.equals("white")) {
			this.name = "wR";
		}
		else {
			this.name = "bR"; 
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see chesscomponents.Piece#getPieceName()
	 * will return the valid appropriate name according to that color
	 */
	public String getPieceName() 
	{
		return this.name;
	}
	@Override
	/*
	 * (non-Javadoc)
	 * @see chesscomponents.Piece#checkIfValidMove(int, int, int, int, boolean)
	 * This method will check a valid move of rook
	 */
	public boolean checkIfValidMove(int row, int col, int newRow, int newCol, boolean emptySpace) {
		// TODO Auto-generated method stub
		int newX=0;
		int newY=0;
		newX = Math.abs(newRow-row);
		newY = Math.abs(col-newCol);
		//Check moving up-down So, row remain same and column will be the different
		if(newX == 0 && newY != 0)
		{
			return true;			
		}
		//check right-left So, column is same and row will be different
		else if(newX != 0 && newY == 0)
		{
			return true;
		}
		
		
		return false;
	}
	
	
}
