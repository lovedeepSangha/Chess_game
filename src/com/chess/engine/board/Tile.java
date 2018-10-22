package com.chess.engine.board;

import java.util.HashMap;
import java.util.Map;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

public abstract class Tile {
	
	
	
	
	protected final int tileCordinate;
	
	
	
	private Tile(final int tileCordinate) {
		this.tileCordinate = tileCordinate;

	}

	public abstract boolean isTileOccupied();

	public abstract Piece getPieced();
	
	
	
	
	
	private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTIles();

	
	
	
	
	
	
	
	
	private static Map<Integer, EmptyTile> createAllPossibleEmptyTIles() {

	final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

		for (int i = 0; i < BoardUtils.NUM_TILES; i++) {
			emptyTileMap.put(i, new EmptyTile(i));
		}

		return ImmutableMap.copyOf(emptyTileMap);

	}

	
	
	
	
	
	
	
	public static Tile createTile(final int tileCordinate, final Piece piece) {
		return piece != null ? new OccupiedTIle(tileCordinate, piece) : EMPTY_TILES_CACHE.get(tileCordinate);

	}

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	//CLASS FOR EMPTY TILE///////////////////////////////////////////////////////
	public static final class EmptyTile extends Tile {
	private	EmptyTile(final int cordinate) {
			super(cordinate); // TODO Auto-generated constructor stub
		}

		@Override
		public boolean isTileOccupied() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Piece getPieced() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	//////////////////////////CLASS FOR EMPTY TILE/////////////////////////////////
	
	
	
	
	
	
	
	
	
	
	//cLASS FOR OCUUPIED.////////////////////////////////////////////////////

	public static final class OccupiedTIle extends Tile {
		private final Piece pieceonTile;

		private OccupiedTIle(int tileCordinate,final Piece pieceonTile) {
			super(tileCordinate);
			this.pieceonTile = pieceonTile;

		}

		@Override
		public boolean isTileOccupied() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Piece getPieced() {
			// TODO Auto-generated method stub
			return null;
		}
	}
//////////////////////CLASS FOR OCCUPIED/////////////////////////////////////










	
	
	
	
	
	
	
	
	
}
