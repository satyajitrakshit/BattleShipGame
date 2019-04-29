package com.game.util.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.game.util.GameUtilities;

public class GameUtilitiesTest {

	@Test
	public void shouldGetXCoord() {
		assertEquals(0, GameUtilities.getXCoord("A1"));
		assertEquals(4, GameUtilities.getXCoord("E5"));
	}

	@Test
	public void shouldGetYCoord() {
		assertEquals(0, GameUtilities.getYCoord("A1"));
		assertEquals(4, GameUtilities.getYCoord("E5"));
	}

}
