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

public class King extends Piece {
	private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = { -9, -8, -7, -1, 1, 7, 8, 9 };

	King(int piecePostion, Alliance pieceAlliance) {
		super(piecePostion, pieceAlliance);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<Move> calculateLegalMoves(Board board) {
		final List<Move> legalMoves = new ArrayList<>();

		for (final int currentcandidateoffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {
			final int candiateDestinationCordinate = this.pieceposition + currentcandidateoffset;

			if (isFirstCOlumExclusion(this.pieceposition, currentcandidateoffset)
					|| isEightColumnExclusion(this.pieceposition, currentcandidateoffset)) {
				continue;
			}
			if (BoardUtils.isValidtileCordinate(candiateDestinationCordinate)) {
				// final Tile
				// candidateDestinationTile=board.getTile(CandiateDestinationCordinate);
				final Tile candidateDestinationTile = board.getTile(candiateDestinationCordinate);
				if (!candidateDestinationTile.isTileOccupied()) {
					legalMoves.add(new Move.MajorMove(board, this, candiateDestinationCordinate));

				} else {
					final Piece pieceAtDestination = candidateDestinationTile.getPieced();
					final Alliance peiceAlliance = pieceAtDestination.getPieceAlliance();
					if (this.pieceAlliance != peiceAlliance) {
						legalMoves.add(
								new Move.AttackMove(board, this, candiateDestinationCordinate, pieceAtDestination));
					}
				}
			}
		}

		return ImmutableList.copyOf(legalMoves);

	}

	private static boolean isFirstCOlumExclusion(final int currentPostion, final int candidateoffset) {
		return BoardUtils.FIRST_COLUMN[currentPostion] && (candidateoffset == -9) || (candidateoffset == -1)
				|| (candidateoffset == 7);

	}

	private static boolean isEightColumnExclusion(final int currentPostion, final int candidateoffset) {
		return BoardUtils.EIGTH_Column[currentPostion] && (candidateoffset == 1) || (candidateoffset == 9)
				|| (candidateoffset == -7);

	}
}
