package com.game.util;

public class GameUtils {

	private GameUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static int getXCoord(String playerCoordinate) {
		return Character.getNumericValue(playerCoordinate.charAt(1)) - 1;
	}

	public static int getYCoord(String playerCoordinate) {
		return (int) playerCoordinate.charAt(0) - (int) 'A';
	}

}
