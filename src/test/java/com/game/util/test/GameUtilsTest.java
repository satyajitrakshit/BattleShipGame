package com.game.util.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.game.util.GameUtils;

public class GameUtilsTest {

	@Test
	public void shouldGetXCoord() {
		assertEquals(0, GameUtils.getXCoord("A1"));
		assertEquals(4, GameUtils.getXCoord("E5"));
	}

	@Test
	public void shouldGetYCoord() {
		assertEquals(0, GameUtils.getYCoord("A1"));
		assertEquals(4, GameUtils.getYCoord("E5"));
	}

}
