package chesscomponents;

/*
 * 
 * @author Akshar Patel
 */

/*
 * This is the Pawn class that extends abstract class Piece 
 * Here declared name, color of pawn and checking move method will check if it's valid move or not
 */
public class Pawn extends Piece{

	private String name;
	private String color = "";
	
	public Pawn(String color)
	{
		this.setColor(color);
		
		checkColor(color);
	}
	public void checkColor(String color) {
		if (color.equals("white")) {
			this.name = "wP";
		}
		else {
			this.name = "bP"; 
		}
	}
	/*
	 * (non-Javadoc)
	 * @see chesscomponents.Piece#getPieceName()
	 * will give the name of piece
	 */
	public String getPieceName() 
	{
		return this.name;
	}
	
	@Override
	/*
	 * (non-Javadoc)
	 * @see chesscomponents.Piece#checkIfValidMove(int, int, int, int, boolean)
	 * check the move of pawn if it's valid then will return true otherwise false
	 */
	public boolean checkIfValidMove(int row, int col, int newRow, int newCol, boolean placeWhereMoving) 
	{
		//to calculate new coordinate 
		int newX = 0, newY = 0;
		boolean flag, colo ;
		
		int newXnoAbs =0, newYnoAbs=0;
		
		flag = false;
		colo = this.isWhite();
		
		//if white's move
		if(colo == true)
		{
			//calculate new coordinate
			newX = Math.abs(newRow - row);
			newY = Math.abs(newCol - col);
			
			newXnoAbs = (newRow - row);
			newYnoAbs = (newCol - col);
		}
		//if black's move
		if(colo == false)
		{
			//calculate new coordinate
			newX = Math.abs(newRow - row);
			newY = Math.abs(col - newCol);
			
			newXnoAbs = (newRow - row);
			newYnoAbs = (col - newCol);
		}
		
		
		//one step move
		if(newXnoAbs == 0 && newYnoAbs == -1 && placeWhereMoving == true)
		{
			flag = true;
		}
		
		// two step move
		if(newXnoAbs == 0 && newYnoAbs == -2 && placeWhereMoving == true && this.fMove == true)
		{
			flag = true;
		}
		
		//cross move
		if(newXnoAbs == -1 && newYnoAbs == -1 && placeWhereMoving == false)
		{
			flag = true;
		}
		
		if(newXnoAbs == 1 && newYnoAbs == -1 && placeWhereMoving == false)
		{
			flag = true;
		}
	
		
		return flag;
	}
	
	
}
