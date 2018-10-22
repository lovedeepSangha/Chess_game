package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public class Move {

	final Board board;
	final Piece movedPiece;
	final int DestinationCordinate;

	private Move(final Board board, final Piece movedPiece, final int DestinationCordinate) {
		this.board = board;
		this.movedPiece = movedPiece;
		this.DestinationCordinate = DestinationCordinate;

	}

	public static final class MajorMove extends Move {

		public MajorMove(final Board board, final Piece movedPiece, final int DestinationCordinate) {
			super(board, movedPiece, DestinationCordinate);
			// TODO Auto-generated constructor stub
		}

	}

	public static final class AttackMove extends Move {
final Piece attackedPiece;
	public	AttackMove(final Board board, final Piece movedPiece, final int DestinationCordinate,final Piece attackedPiece) {
			super(board, movedPiece, DestinationCordinate);
		this.attackedPiece=attackedPiece;
		// TODO Auto-generated constructor stub
		}

	}

}
