package com.phantomrealm.cardbattle.model.board;


/**
 * Model object used to represent the (x, y) coordinates of a position on the board
 * 
 * @author matthewpape
 */
public class Position {

	private int mRow;
	private int mColumn;
	
	public Position(int row, int column) {
		mRow = row;
		mColumn = column;
	}
	
	public int getRow() {
		return mRow;
	}
	
	public int getColumn() {
		return mColumn;
	}
	
}
