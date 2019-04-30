package com.game;

import java.util.HashMap;
import java.util.Map;

import com.game.exception.BattleShipOutOfBoundsException;
import com.game.util.GameUtilities;

public class BattleShipGame {

	private int battleAreaWidth;
	private int battleAreaHeight;
	private int noOfBattleships;
	private Map<String, Integer> player1Map;
	private Map<String, Integer> player2Map;

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
		player1Map = new HashMap<String, Integer>();
		player1Map.put("Q", 0);
		player1Map.put("P", 0);
		player2Map = new HashMap<String, Integer>();
		player2Map.put("Q", 0);
		player2Map.put("P", 0);
	}

	public void placeQnPTypeBattleships(String shipType, int shipWidth, int shipHeight, String player1Coordinate,
			String player2Coordinate) {

		for (int i = 0; i < shipWidth; i++) {
			if (shipType.charAt(0) == 'Q') {
				player1BattleArea[GameUtilities.getYCoord(player1Coordinate)][GameUtilities.getXCoord(player1Coordinate)
						+ i] = new Cell(Cell.CellType.Q);
				player1Map.put("Q", player1Map.get("Q") + 2);

				player2BattleArea[GameUtilities.getYCoord(player2Coordinate)][GameUtilities.getXCoord(player2Coordinate)
						+ i] = new Cell(Cell.CellType.Q);
				player2Map.put("Q", player2Map.get("Q") + 2);

			} else if (shipType.charAt(0) == 'P') {
				player1BattleArea[GameUtilities.getYCoord(player1Coordinate)][GameUtilities.getXCoord(player1Coordinate)
						+ i] = new Cell(Cell.CellType.P);
				player1Map.put("P", player1Map.get("P") + 1);

				player2BattleArea[GameUtilities.getYCoord(player2Coordinate)][GameUtilities.getXCoord(player2Coordinate)
						+ i] = new Cell(Cell.CellType.P);
				player2Map.put("P", player2Map.get("P") + 1);
			}
		}

		for (int i = 0; i < shipHeight; i++) {
			if (shipType.charAt(0) == 'Q') {
				player1BattleArea[GameUtilities.getYCoord(player1Coordinate) + i][GameUtilities
						.getXCoord(player1Coordinate)] = new Cell(Cell.CellType.Q);
				player1Map.put("Q", player1Map.get("Q") + 2);

				player2BattleArea[GameUtilities.getYCoord(player2Coordinate) + i][GameUtilities
						.getXCoord(player2Coordinate)] = new Cell(Cell.CellType.Q);
				player2Map.put("Q", player2Map.get("Q") + 2);

			} else if (shipType.charAt(0) == 'P') {
				player1BattleArea[GameUtilities.getYCoord(player1Coordinate) + i][GameUtilities
						.getXCoord(player1Coordinate)] = new Cell(Cell.CellType.P);
				player1Map.put("P", player1Map.get("P") + 1);

				player2BattleArea[GameUtilities.getYCoord(player2Coordinate) + i][GameUtilities
						.getXCoord(player2Coordinate)] = new Cell(Cell.CellType.P);
				player2Map.put("P", player2Map.get("P") + 1);
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

			if (p1Turn == true && p1 == (p1StepsArr.length)) {
				System.out.println("Player-1 has no more missiles left to launch");
				p1Turn = false;
				p2Turn = true;

			} else if (p1Turn == true && targetCell("Player-2", p1StepsArr[p1]) == true) {
				if (getWiningPosition("Player-2")) {
					System.out.println("Player-1 won the battle");
					break;
				} else {
					System.out.println("Player-1 fires a missile with target " + p1StepsArr[p1] + " which got hit");
					p1++;
				}
			} else if (p1Turn == true && targetCell("Player-2", p1StepsArr[p1]) == false) {
				System.out.println("Player-1 fires a missile with target " + p1StepsArr[p1] + " which got miss");
				p1Turn = false;
				p2Turn = true;
				p1++;
			}

			// "--------------------------------------------------------------------------------";

			if (p2Turn == true && p2 == (p2StepsArr.length)) {
				System.out.println("Player-2 has no more missiles left to launch");
				p1Turn = true;
				p2Turn = false;

			} else if (p2Turn == true && targetCell("Player-1", p2StepsArr[p2]) == true) {
				if (getWiningPosition("Player-1")) {
					System.out.println("Player-2 won the battle");
					break;
				} else {
					System.out.println("Player-2 fires a missile with target " + p2StepsArr[p2] + " which got hit");
					p2++;
				}
			} else if (p2Turn == true && targetCell("Player-1", p2StepsArr[p2]) == false) {
				System.out.println("Player-2 fires a missile with target " + p2StepsArr[p2] + " which got miss");
				p1Turn = true;
				p2Turn = false;
				p2++;
			}

			if (p1 == p1StepsArr.length && p2 == p2StepsArr.length) {
				break;
			}
		}

	}

	private boolean getWiningPosition(String player) {
		if (player == "Player-1") {
			if (player1Map.get("Q") == 0 && player1Map.get("P") == 0) {
				return true;
			}
		}
		if (player == "Player-2") {
			if (player2Map.get("Q") == 0 && player2Map.get("P") == 0) {
				return true;
			}
		}
		return false;
	}

	private boolean targetCell(String player, String coordinate) {
		Cell cell = null;
		if (player == "Player-1") {
			cell = player1BattleArea[GameUtilities.getYCoord(coordinate)][GameUtilities.getXCoord(coordinate)];
			if (cell != null) {
				cell.hitCnt++;
				if (cell.cellType.equals(Cell.CellType.Q) && cell.hitCnt == 2) {
					player1BattleArea[GameUtilities.getYCoord(coordinate)][GameUtilities.getXCoord(coordinate)] = null;
				} else {
					player1BattleArea[GameUtilities.getYCoord(coordinate)][GameUtilities.getXCoord(coordinate)] = cell;
				}

				if (cell.cellType.equals(Cell.CellType.P) && cell.hitCnt == 1) {
					player1BattleArea[GameUtilities.getYCoord(coordinate)][GameUtilities.getXCoord(coordinate)] = null;
				}

				if (cell.cellType.equals(Cell.CellType.Q)) {
					player1Map.put("Q", player1Map.get("Q") - 1);
				} else if (cell.cellType.equals(Cell.CellType.P)) {
					player1Map.put("P", player1Map.get("P") - 1);
				}
				return true;
			}
			return false;

		} else if (player == "Player-2") {
			cell = player2BattleArea[GameUtilities.getYCoord(coordinate)][GameUtilities.getXCoord(coordinate)];
			if (cell != null) {
				cell.hitCnt++;
				if (cell.cellType.equals(Cell.CellType.Q) && cell.hitCnt == 2) {
					player2BattleArea[GameUtilities.getYCoord(coordinate)][GameUtilities.getXCoord(coordinate)] = null;
				} else {
					player2BattleArea[GameUtilities.getYCoord(coordinate)][GameUtilities.getXCoord(coordinate)] = cell;
				}

				if (cell.cellType.equals(Cell.CellType.P) && cell.hitCnt == 1) {
					player2BattleArea[GameUtilities.getYCoord(coordinate)][GameUtilities.getXCoord(coordinate)] = null;
				}

				if (cell.cellType.equals(Cell.CellType.Q)) {
					player2Map.put("Q", player2Map.get("Q") - 1);
				} else if (cell.cellType.equals(Cell.CellType.P)) {
					player2Map.put("P", player2Map.get("P") - 1);
				}
				return true;
			}
			return false;

		}

		return false;
	}
}
