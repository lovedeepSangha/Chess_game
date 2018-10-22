package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.google.common.collect.ImmutableList;

public class Pawn extends Piece {
	private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = { 8, 16, 7, 9 };

	Pawn(final int piecePostion, final Alliance pieceAlliance) {
		super(piecePostion, pieceAlliance);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<Move> calculateLegalMoves(Board board) {
		final List<Move> legalMoves = new ArrayList<>();
		for (final int CandiateCordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {
		final	int candidateDestinationCoordinate = this.pieceposition
					+ (this.getPieceAlliance().getDirection() * CandiateCordinateOffset);
			if (!BoardUtils.isValidtileCordinate(candidateDestinationCoordinate)) {
				continue;
			}
			if (CandiateCordinateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
				// left something
				legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
			} else if (CandiateCordinateOffset == 16 && this.isFirstMove()
					&& (BoardUtils.SECOND_ROW[this.pieceposition] && this.pieceAlliance.isBlack())
					|| (BoardUtils.SEVENTH_ROW[this.pieceposition] && this.pieceAlliance.isWhite())) {
				final int BehindCandidateDestinationCoordinate = this.pieceposition
						+ (this.pieceAlliance.getDirection() * 8);
				if (!board.getTile(BehindCandidateDestinationCoordinate).isTileOccupied()
						&& !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
					legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
				}
			} else if (CandiateCordinateOffset == 7
					&& !(BoardUtils.EIGTH_Column[this.pieceposition] && this.pieceAlliance.isWhite()
							|| (BoardUtils.FIRST_COLUMN[this.pieceposition] && this.pieceAlliance.isBlack()))) {
				if (board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
					final Piece pieceCandidate = board.getTile(candidateDestinationCoordinate).getPieced();
					if (this.pieceAlliance != pieceCandidate.getPieceAlliance()) {
						///
						legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceCandidate));
					}
				}
			} else if (CandiateCordinateOffset == 9
					&& (BoardUtils.EIGTH_Column[this.pieceposition] && this.pieceAlliance.isBlack()
							|| (BoardUtils.FIRST_COLUMN[this.pieceposition] && this.pieceAlliance.isWhite()))) {
				if (board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
					final Piece pieceCandidate = board.getTile(candidateDestinationCoordinate).getPieced();
					if (this.pieceAlliance != pieceCandidate.getPieceAlliance()) {
						///
						legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceCandidate));
					}
				}
			}
		} // TODO Auto-generated method stub
		return ImmutableList.copyOf(legalMoves);
	}

}
