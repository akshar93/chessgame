package chesscomponents;

/*
 * 
 * @author Akshar Patel
 */

/*
 * This is an abstract class and have some of abstract method so other class can extend this class and can override method
 * The beasic purpose to make this class is that will helpful for all different type of chess pieces so their class can use same variable and method
 */
public abstract class Piece {

	
	private String color = "";
	
	//Check the first move of piece
	public boolean fMove = true;
	
	//set the color according to request
	public void setColor(String color) {
		this.color = color;
	}
	//This is abstract method so sub class need to override it
	public abstract String getPieceName();
	//public abstract String getPieceName();
	
	//public abstract boolean isValidMove(int currRow, int currColumn, int newRow, int newColumn, boolean isOpenSpace);
	
	//This method is to check that piece color is white or black
	public boolean isWhite()
	{
		if(color.equalsIgnoreCase("white")) 
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	//This abstract method is need to override in subclass and have to check valid movement according to piece type
	public abstract boolean checkIfValidMove(int row, int col, int newRow, int newCol, boolean emptySpace);
	
}
