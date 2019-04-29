package com.game;

import com.game.exception.BattleShipOutOfBoundsException;
import com.game.util.GameUtilities;

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

	public int getNoOfBattleships() {
		return noOfBattleships;
	}

	public void createBattleArea() {
		player1BattleArea = new Cell[this.battleAreaHeight][this.battleAreaWidth];
		player2BattleArea = new Cell[this.battleAreaHeight][this.battleAreaWidth];
	}

	public void placeQnPTypeBattleships(String shipType, int shipWidth, int shipHeight, String player1Coordinate,
			String player2Coordinate) {

		for (int i = 0; i < shipWidth; i++) {
			if (shipType.charAt(0) == 'Q') {
				player1BattleArea[GameUtilities.getYCoord(player1Coordinate)][GameUtilities.getXCoord(player1Coordinate)
						+ i] = new Cell(Cell.CellType.Q);
				player2BattleArea[GameUtilities.getYCoord(player2Coordinate)][GameUtilities.getXCoord(player2Coordinate)
						+ i] = new Cell(Cell.CellType.Q);
			} else if (shipType.charAt(0) == 'P') {
				player1BattleArea[GameUtilities.getYCoord(player1Coordinate)][GameUtilities.getXCoord(player1Coordinate)
						+ i] = new Cell(Cell.CellType.P);
				player2BattleArea[GameUtilities.getYCoord(player2Coordinate)][GameUtilities.getXCoord(player2Coordinate)
						+ i] = new Cell(Cell.CellType.P);
			}
		}

		for (int i = 0; i < shipHeight; i++) {
			if (shipType.charAt(0) == 'Q') {
				player1BattleArea[GameUtilities.getYCoord(player1Coordinate) + i][GameUtilities
						.getXCoord(player1Coordinate)] = new Cell(Cell.CellType.Q);
				player2BattleArea[GameUtilities.getYCoord(player2Coordinate) + i][GameUtilities
						.getXCoord(player2Coordinate)] = new Cell(Cell.CellType.Q);
			} else if (shipType.charAt(0) == 'P') {
				player1BattleArea[GameUtilities.getYCoord(player1Coordinate) + i][GameUtilities
						.getXCoord(player1Coordinate)] = new Cell(Cell.CellType.P);
				player2BattleArea[GameUtilities.getYCoord(player2Coordinate) + i][GameUtilities
						.getXCoord(player2Coordinate)] = new Cell(Cell.CellType.P);
			}
		}

	}

	public void startBattleShipGame(String player1Steps, String player2Steps) {
		String[] p1StepsArr = player1Steps.split(" ");
		String[] p2StepsArr = player2Steps.split(" ");

		boolean p1Turn = true;
		boolean p2Turn = false;
		int p1 = 0, p2 = 0;
		while (true) {

			if (p1Turn == true && p1 == (p1StepsArr.length - 1)) {
				System.out.println("Player-1 has no more missiles left to launch");

			} else if (p1Turn == true && targetCell("Player-1", p1StepsArr[p1]) == true) {
				if (getWiningPosition()) {
					System.out.println("Player-1 won the battle");
					break;
				} else {
					System.out.println("Player-1 fires a missile with target " + p1StepsArr[p1] + " which got hit");
					p1++;
				}
			} else {
				p1Turn = false;
				p2Turn = true;
				p1++;
				System.out.println("Player-1 fires a missile with target " + p1StepsArr[p1] + " which got miss");
			}

			// "--------------------------------------------------------------------------------";

			if (p2Turn == true && p2 == (p2StepsArr.length - 1)) {
				System.out.println("Player-2 has no more missiles left to launch");
			} else if (p2Turn == true && targetCell("Player-2", p2StepsArr[p2]) == true) {
				if (getWiningPosition()) {
					System.out.println("Player-2 won the battle");
					break;
				} else {
					System.out.println("Player-2 fires a missile with target " + p2StepsArr[p2] + " which got hit");
					p2++;
				}
			} else {
				p1Turn = true;
				p2Turn = false;
				p2++;
				System.out.println("Player-2 fires a missile with target " + p2StepsArr[p2] + " which got miss");
			}

			if (p1 == (p1StepsArr.length - 1) && p2 == (p2StepsArr.length - 1)) {
				break;
			}
		}

	}

	private boolean getWiningPosition() {
		return false;
	}

	private boolean targetCell(String player, String coordinate) {
		if (player == "Player-1") {
			if (player1BattleArea[GameUtilities.getYCoord(coordinate)][GameUtilities.getXCoord(coordinate)] != null) {
				return true;
			}
		} else if (player == "Player-2") {
			if (player2BattleArea[GameUtilities.getYCoord(coordinate)][GameUtilities.getXCoord(coordinate)] != null) {
				return true;
			}
		}

		return false;
	}
}
