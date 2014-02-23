/**
 * 
 */
package com.phantomrealm.cardbattle.model.player.human;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.phantomrealm.cardbattle.model.board.Board;
import com.phantomrealm.cardbattle.model.board.Position;
import com.phantomrealm.cardbattle.model.card.Card;
import com.phantomrealm.cardbattle.model.deck.Deck;
import com.phantomrealm.cardbattle.model.player.BasePlayer;
import com.phantomrealm.cardbattle.model.player.PlayerIdentity;

/**
 * A human controlled player who accepts input through the console
 * 
 * @author matthewpape
 */
public class ConsoleHumanPlayer extends BasePlayer {

	public ConsoleHumanPlayer(PlayerIdentity identity, Deck deck) {
		super(identity, deck);
	}

	@Override
	public Position getMove(Board board, Deck opponentDeck) {
		Card card = getDeck().peek();
		System.out.println(card.getName() + " type: " + card.getAttackType() + ", atk: "
				+ card.getAttack() + ", def: " + card.getDefense() + ", res: " + card.getResistance());
		Position move = null;
		List<Position> moves = board.getPossibleMoves(getIdentity());
		System.out.print("Possible moves: ");
		for (Position position : moves) {
			System.out.print("(" + position.getColumn() + ", " + position.getRow() + ") ");
		}
		while (!board.isValidMove(getIdentity(), move)) {
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		        System.out.println("Enter move:");
		        String input = reader.readLine();
				int col = Integer.parseInt(input.substring(0, input.indexOf(',')));
				int row = Integer.parseInt(input.substring(input.indexOf(',') + 2));
				move = new Position(row, col);
			} catch (IOException exception) {
				System.out.println("use \"x, y\"");
			} catch (NumberFormatException exception) {
				System.out.println("use \"x, y\"");
			} catch (StringIndexOutOfBoundsException exception) {
				System.out.println("use \"x, y\"");
			}
		}
		return move;
	}
	
	

}