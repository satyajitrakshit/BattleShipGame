package com.game.test;

import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.game.BattleShipGame;
import com.game.exception.BattleShipOutOfBoundsException;

public class BattleShipGameSetupTest {

	private BattleShipGame bsg;
	private String input;

	@Before
	public void setUp() throws Exception {
		bsg = new BattleShipGame();
		input = "5 E\n" + "2\n" + "Q 1 1 A1 B2\n" + "P 2 1 D4 C3";
	}

	@After
	public void tearDown() throws Exception {
		bsg = null;
	}

	@Test
	public void shouldSetBattleAreaWidth() throws BattleShipOutOfBoundsException {
		bsg.setBattleAreaWidth(5);
	}

	@Test(expected = BattleShipOutOfBoundsException.class)
	public void shouldThrowExceptionWhileSettingMaxBattleAreaWidth() throws BattleShipOutOfBoundsException {
		bsg.setBattleAreaWidth(10);
	}

	@Test(expected = BattleShipOutOfBoundsException.class)
	public void shouldThrowExceptionWhileSettingMinBattleAreaWidth() throws BattleShipOutOfBoundsException {
		bsg.setBattleAreaWidth(0);
	}

	@Test
	public void shouldSetBattleAreaHeight() throws BattleShipOutOfBoundsException {
		bsg.setBattleAreaHeight('E');
		bsg.setBattleAreaHeight('e');
	}

	@Test(expected = BattleShipOutOfBoundsException.class)
	public void shouldThrowExceptionWhileSettingBattleAreaHeight() throws BattleShipOutOfBoundsException {
		bsg.setBattleAreaHeight('9');
	}

	@Test
	public void shouldSetNoOfBattleships() throws BattleShipOutOfBoundsException {
		bsg.setBattleAreaWidth(5);
		bsg.setBattleAreaHeight('E');
		bsg.setNoOfBattleships(25);
	}

	@Test(expected = BattleShipOutOfBoundsException.class)
	public void shouldThrowExceptionWhileSettingNoOfBattleships() throws BattleShipOutOfBoundsException {
		bsg.setBattleAreaWidth(5);
		bsg.setBattleAreaHeight('E');
		bsg.setNoOfBattleships(26);
	}

	@Test(expected = BattleShipOutOfBoundsException.class)
	public void shouldThrowExceptionWhileSettingNoOfBattleshipsAsZero() throws BattleShipOutOfBoundsException {
		bsg.setBattleAreaWidth(5);
		bsg.setBattleAreaHeight('E');
		bsg.setNoOfBattleships(0);
	}

	@Test
	public void shouldSetupQnPTypeBattleships() throws BattleShipOutOfBoundsException {
		Scanner sc = new Scanner(input);
		bsg.setBattleAreaWidth(sc.nextInt());
		bsg.setBattleAreaHeight(sc.next().charAt(0));
		bsg.setNoOfBattleships(sc.nextInt());
		bsg.createBattleArea();
		bsg.placeQnPTypeBattleships(sc.next(), sc.nextInt(), sc.nextInt(), sc.next(), sc.next());
		bsg.placeQnPTypeBattleships(sc.next(), sc.nextInt(), sc.nextInt(), sc.next(), sc.next());

	}

}
