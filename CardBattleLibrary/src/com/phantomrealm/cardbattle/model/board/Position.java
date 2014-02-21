package com.phantomrealm.cardbattle.model.board;


/**
 * Model object used to represent the (x, y) coordinates of a position on the board
 * 
 * @author matthewpape
 */
public class Position {

	private int mRow;
	private int mColumn;
	
	/**
	 * Creates a position which represents a set of (x, y) coordinates
	 * @param row (y coordinate)
	 * @param column (x coordinate)
	 */
	public Position(int row, int column) {
		mRow = row;
		mColumn = column;
	}
	
	/**
	 * Get the row (y coordinate)
	 * @return
	 */
	public int getRow() {
		return mRow;
	}
	
	/**
	 * Get the column (x coordinate)
	 * @return
	 */
	public int getColumn() {
		return mColumn;
	}
	
}
