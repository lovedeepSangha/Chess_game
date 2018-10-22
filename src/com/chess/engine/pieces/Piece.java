package com.chess.engine.pieces;

import java.util.Collection;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

public abstract class Piece {
	protected final int pieceposition;
	protected final Alliance pieceAlliance;
	protected final boolean isFirstMove;
	Piece(final int piecePostion, final Alliance pieceAlliance) {
		// TODO Auto-generated constructor stub
		this.pieceAlliance = pieceAlliance;
		this.pieceposition = piecePostion;
this.isFirstMove=false;

	}
	public int getPiecePosition(){
		return this.pieceposition;
		
	}
	  public boolean isFirstMove() {
		return this.isFirstMove;
	}

	public Alliance getPieceAlliance() {
		return this.pieceAlliance;
	}

	public abstract Collection<Move> calculateLegalMoves(final Board board);
}
