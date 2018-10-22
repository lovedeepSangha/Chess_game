package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

public class Queen extends Piece {
	private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = { -9, -8, -7, -1, 1, 7, 8, 9 };

	Queen(int piecePostion, Alliance pieceAlliance) {
		super(piecePostion, pieceAlliance);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<Move> calculateLegalMoves(final Board board) {
		// TODO Auto-generated method stub
		final List<Move> legalMoves = new ArrayList<>();

		for (final int CandiateCordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {
			int candidateDestinationCoordinate = this.pieceposition;
			while (BoardUtils.isValidtileCordinate(candidateDestinationCoordinate)) {

				if (isFirstCOlumExclusion(candidateDestinationCoordinate, CandiateCordinateOffset)
						|| isEightColumnExclusion(candidateDestinationCoordinate, CandiateCordinateOffset)) {
					break;

				}
				candidateDestinationCoordinate += CandiateCordinateOffset;
				if (BoardUtils.isValidtileCordinate(candidateDestinationCoordinate)) {
					final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
					if (!candidateDestinationTile.isTileOccupied()) {
						legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
					} else {
						final Piece pieceAtDestination = candidateDestinationTile.getPieced();
						final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
						if (this.pieceAlliance != pieceAlliance) {
							legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate,
									pieceAtDestination));
						}
						break;
					}

				}
			}
		}
		return ImmutableList.copyOf(legalMoves);
	}

	private static boolean isFirstCOlumExclusion(final int currentPostion, final int candidateoffset) {
		return BoardUtils.FIRST_COLUMN[currentPostion]
				&& (candidateoffset == -9 || candidateoffset == 7 || candidateoffset == -1);
	}

	private static boolean isEightColumnExclusion(final int currentPostion, final int candidateoffset) {
		return BoardUtils.EIGTH_Column[currentPostion]
				&& (candidateoffset == -7 || candidateoffset == 9 || candidateoffset == 1);
	}

}
