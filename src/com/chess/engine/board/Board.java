package com.chess.engine.board;

import java.util.Map;

import com.chess.engine.Alliance;
import com.chess.engine.pieces.Piece;

public class Board {
	
	
	 
	
	
	
	
	
	private Board(Builder builder) {

	}

	public Tile getTile(final int tilecordinate) {
		return null;
	}

	public static class Builder {
		Map<Integer, Piece>boardConfig;
	Alliance nextMoveMaker;
	
	
	public Builder setPiece(final Piece piece)
	{
		this.boardConfig.put(piece.getPiecePosition(), piece);
	return this;}
	public Builder setMoveMaker(final Alliance nextMoveMaker){
		this.nextMoveMaker=nextMoveMaker;
		return this;
		 
	}
	public Builder() {
	
	
	}	public Board build() {
			return new Board(this);

		}
	}

}
