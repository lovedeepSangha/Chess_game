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

public class Knight extends Piece {
	private final static int[] CANDIDATE_MOVE_CORDINATES = { -17, -15, -10, -6, 6, 10, 15, 17 };

	Knight(final int piecePostion, Alliance pieceAlliance) {
		super(piecePostion, pieceAlliance);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<Move> calculateLegalMoves(final Board board) {

		List<Move> legalMoves = new ArrayList<>();
		for (int currentCandidateOffset : CANDIDATE_MOVE_CORDINATES) {
			final int candidateDestinationCoordinate = this.pieceposition + currentCandidateOffset;
			if (BoardUtils.isValidtileCordinate(candidateDestinationCoordinate)) {

				if (isFirstCOlumExclusion(this.pieceposition, currentCandidateOffset)
						|| isSecondColumnExclusion(this.pieceposition, currentCandidateOffset)
						|| isSeventhCOlumnExclusion(this.pieceposition, currentCandidateOffset)
						|| isEigth(this.pieceposition, currentCandidateOffset)) {
					continue;
				}

				final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
				if (!candidateDestinationTile.isTileOccupied()) {
					legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));

				} else {
					final Piece pieceAtDestination = candidateDestinationTile.getPieced();
					final Alliance peiceAlliance = pieceAtDestination.getPieceAlliance();
					if (this.pieceAlliance != peiceAlliance) {
						legalMoves.add(
								new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));

					}
				}
			}
		}

		// TODO Auto-generated method stub

		return ImmutableList.copyOf(legalMoves);
	}

	private static boolean isFirstCOlumExclusion(final int currentPostion, final int candidateoffset) {
		return BoardUtils.FIRST_COLUMN[currentPostion] && (candidateoffset == -17) || (candidateoffset == -10)
				|| (candidateoffset == 6) || (candidateoffset == 15);

	}

	private static boolean isSecondColumnExclusion(final int currentPostion, final int candidateoffset) {
		return BoardUtils.SECOND_COLUMN[currentPostion] && (candidateoffset == -10 || candidateoffset == 6);

	}

	private static boolean isSeventhCOlumnExclusion(final int currentPostion, final int candidateoffset) {
		return BoardUtils.SEVENTH_Column[currentPostion] && (candidateoffset == -6 || candidateoffset == 10);

	}

	private static boolean isEigth(final int currentPostion, final int candidateoffset) {
		return BoardUtils.EIGTH_Column[currentPostion] && (candidateoffset == -15 || candidateoffset == -6)
				|| candidateoffset == 10 || candidateoffset == 17;

	}
}
