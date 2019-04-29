package com.game;

import com.game.exception.BattleShipOutOfBoundsException;

public class BattleShipGame {

	private int battleAreaWidth;
	private int battleAreaHeight;
	private int noOfBattleships;

	private static class Cell {
		enum CellType {
			BLANK, Q, P
		}

		private CellType cellType = CellType.BLANK;
		private int hitCnt = 0;

		public Cell(CellType cellType) {
			this.cellType = cellType;
		}
	}

	private Cell[][] player1BattleArea;
	private Cell[][] player2BattleArea;

	public void setBattleAreaWidth(int battleAreaWidth) throws BattleShipOutOfBoundsException {
		if (battleAreaWidth >= 1 && battleAreaWidth <= 9) {
			this.battleAreaWidth = battleAreaWidth;
		} else {
			throw new BattleShipOutOfBoundsException("width must be witin range: 1 <= Width of Battle area (M) <= 9");
		}
	}

	public void setBattleAreaHeight(char height) throws BattleShipOutOfBoundsException {
		height = Character.toUpperCase(height);
		if (height <= 'Z' && height >= 'A') {
			this.battleAreaHeight = ((int) height - (int) 'A') + 1;
		} else {
			throw new BattleShipOutOfBoundsException("height must be witin range: A <= Height of Battle area (N) <= Z");
		}
	}

	public void setNoOfBattleships(int noOfBattleships) throws BattleShipOutOfBoundsException {
		if (noOfBattleships >= 1 && noOfBattleships <= (this.battleAreaWidth * this.battleAreaHeight)) {
			this.noOfBattleships = noOfBattleships;
		} else {
			throw new BattleShipOutOfBoundsException(
					"no. Of Battleships must be witin range: 1 <= Number of battleships <= M * N");
		}
	}

	public void createBattleArea() {
		player1BattleArea = new Cell[this.battleAreaHeight][this.battleAreaWidth];
		player2BattleArea = new Cell[this.battleAreaHeight][this.battleAreaWidth];
	}

	public void placeQnPTypeBattleships(String shipType, int shipWidth, int shipHeight, String player1Coordinate,
			String player2Coordinate) {

		for (int i = 0; i < shipWidth; i++) {
			if (shipType.charAt(0) == 'Q') {
				player1BattleArea[getYCoord(player1Coordinate)][getXCoord(player1Coordinate) + i] = new Cell(
						Cell.CellType.Q);
				player2BattleArea[getYCoord(player2Coordinate)][getXCoord(player2Coordinate) + i] = new Cell(
						Cell.CellType.Q);
			} else if (shipType.charAt(0) == 'P') {
				player1BattleArea[getYCoord(player1Coordinate)][getXCoord(player1Coordinate) + i] = new Cell(
						Cell.CellType.P);
				player2BattleArea[getYCoord(player2Coordinate)][getXCoord(player2Coordinate) + i] = new Cell(
						Cell.CellType.P);
			}
		}

		for (int i = 0; i < shipHeight; i++) {
			if (shipType.charAt(0) == 'Q') {
				player1BattleArea[getYCoord(player1Coordinate) + i][getXCoord(player1Coordinate)] = new Cell(
						Cell.CellType.Q);
				player2BattleArea[getYCoord(player2Coordinate) + i][getXCoord(player2Coordinate)] = new Cell(
						Cell.CellType.Q);
			} else if (shipType.charAt(0) == 'P') {
				player1BattleArea[getYCoord(player1Coordinate) + i][getXCoord(player1Coordinate)] = new Cell(
						Cell.CellType.P);
				player2BattleArea[getYCoord(player2Coordinate) + i][getXCoord(player2Coordinate)] = new Cell(
						Cell.CellType.P);
			}
		}

	}

	private int getXCoord(String playerCoordinate) {
		return Character.getNumericValue(playerCoordinate.charAt(1)) - 1;
	}

	private int getYCoord(String playerCoordinate) {
		return (int) playerCoordinate.charAt(0) - (int) 'A';
	}

}
