package com.phantomrealm.cardbattle.view.board;

import com.phantomrealm.cardbattle.model.board.Board;
import com.phantomrealm.cardbattle.model.board.BoardSlot;
import com.phantomrealm.cardbattle.model.card.AttackType;
import com.phantomrealm.cardbattle.model.card.Card;
import com.phantomrealm.cardbattle.model.player.PlayerIdentity;

/**
 * Displays a visual representation of a Board
 * 
 * @author matthewpape
 */
public class ConsoleBoardView {

	Board mBoard;
	
	/**
	 * Creates a new view for displaying a given board in the console
	 * @param board
	 */
	public ConsoleBoardView(Board board) {
		mBoard = board;
	}
	
	public void displayBoard() {
		for (int row = 0; row < mBoard.getHeight(); ++row) {
			displayRow(mBoard.getBoardRow(row));
		}
		System.out.println("");
	}
	
	private void displayRow(BoardSlot[] row) {
		for (BoardSlot slot : row) {
			printEdge(slot);
		}
		System.out.println();
		
		for (BoardSlot slot : row) {
			printName(slot.getCard());
		}
		System.out.println();
		
		for (int slot = 0; slot < row.length; ++slot) {
			printEmpty();
		}
		System.out.println();
		
		for (BoardSlot slot : row) {
			printAttackType(slot.getCard());
		}
		System.out.println();
		
		for (BoardSlot slot : row) {
			printAttack(slot.getCard());
		}
		System.out.println();
		
		for (BoardSlot slot : row) {
			printDefense(slot.getCard());
		}
		System.out.println();
		
		for (BoardSlot slot : row) {
			printResistance(slot.getCard());
		}
		System.out.println();
		
		for (BoardSlot slot : row) {
			printEdge(slot);
		}
		System.out.println();
		
		
	}
	
	private void printEdge(BoardSlot slot) {
		PlayerIdentity owner = slot.getOwner();
		char edge = '=';
		if (owner == PlayerIdentity.LEFT_PLAYER) {
			edge = '<';
		} else if (owner == PlayerIdentity.RIGHT_PLAYER) {
			edge = '>';
		}
		for (int i = 0; i < 13; ++i) {
			System.out.print(edge);
		}
	}
	
	private void printEmpty() {
		System.out.print("|");
		for (int i = 0; i < 11; ++i) {
			System.out.print(" ");
		}
		System.out.print("|");
	}
	
	private void printName(Card card) {
		StringBuilder builder = new StringBuilder("| ");
		if (card == null) {
			builder.append("          |");
		} else {
			String name = card.getName();
			if (name.length() > 9) {
				builder.append(name.substring(0, 9));
			} else {
				builder.append(name);
				for (int i = 0; i < 9 - name.length(); ++i) {
					builder.append(" ");
				}
			}
			builder.append(" |");
		}
		System.out.print(builder.toString());
	}
	
	private void printAttackType(Card card) {
		if (card != null) {
			AttackType type = card.getAttackType();
			switch (type) {
			case MAGICAL:
				System.out.print("| type: mag |");
				break;
			case PHYSICAL:
				System.out.print("| type: phys|");
				break;
			}
		} else {
			printEmpty();
		}
	}
	
	private void printAttack(Card card) {
		if (card != null) {
			int attack = card.getAttack();	
			StringBuilder builder = new StringBuilder("| atk: ");
			String attackString = "" + attack;
			if (attackString.length() > 4) {
				builder.append(attackString.subSequence(0, 4));
			} else {
				builder.append(attackString);
				for (int i = 0; i < 4 - attackString.length(); ++i) {
					builder.append(" ");
				}
			}
			builder.append(" |");
			System.out.print(builder.toString());
		} else {
			printEmpty();
		}
	}
	
	private void printDefense(Card card) {
		if (card != null) {
			int defense = card.getResistance();	
			StringBuilder builder = new StringBuilder("| def: ");
			String defenseString = "" + defense;
			if (defenseString.length() > 4) {
				builder.append(defenseString.subSequence(0, 4));
			} else {
				builder.append(defenseString);
				for (int i = 0; i < 4 - defenseString.length(); ++i) {
					builder.append(" ");
				}
			}
			builder.append(" |");
			System.out.print(builder.toString());
		} else {
			printEmpty();
		}
	}
	
	private void printResistance(Card card) {
		if (card != null) {
			int resistance = card.getResistance();	
			StringBuilder builder = new StringBuilder("| res: ");
			String resistanceString = "" + resistance;
			if (resistanceString.length() > 4) {
				builder.append(resistanceString.subSequence(0, 4));
			} else {
				builder.append(resistanceString);
				for (int i = 0; i < 4 - resistanceString.length(); ++i) {
					builder.append(" ");
				}
			}
			builder.append(" |");
			System.out.print(builder.toString());
		} else {
			printEmpty();
		}
	}

}
