package chesscomponents;

/*
 * 
 * @author Akshar Patel
 */
/*
 * This Class is extending an abstract class Piece
 * This class will check for bishop behaviour, name, color, and valid movement
 */
public class Bishop extends Piece{

	private String name;
	private String color = "";
	public Bishop(String color)
	{
		this.setColor(color);
		checkColor(color);
	}
	public void checkColor(String color) {
		if (color.equals("white")) {
			this.name = "wB";
		}
		else {
			this.name = "bB"; 
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see chesscomponents.Piece#getPieceName()
	 * will return a name of bishop with appropriate color
	 */
	public String getPieceName() 
	{
		return this.name;
	}
	
	@Override
	/*
	 * (non-Javadoc)
	 * @see chesscomponents.Piece#checkIfValidMove(int, int, int, int, boolean)
	 * This method will check valid move of bishop
	 */
	public boolean checkIfValidMove(int row, int col, int newRow, int newCol, boolean placeWhereMoving) {
		// TODO Auto-generated method stub
		int newX=0;
		int newY=0;
		boolean flag = false;
		
		newX = Math.abs(newRow-row);
		newY = Math.abs(col-newCol);
		
		if((newX == newY)) 
		{		
			flag=true;			
		}
		
		return flag;
	}
}
