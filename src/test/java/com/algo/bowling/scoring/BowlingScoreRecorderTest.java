package com.algo.bowling.scoring;

import org.junit.Test;

import com.algo.bowling.scoring.exception.BowlingScoringException;

import junit.framework.Assert;

public class BowlingScoreRecorderTest {

	@Test
	public void testNullInput() {
		BowlingScoreRecorder bowlingScoreRecorder = new BowlingScoreRecorder();
		String scoresStr = null;
		try {
			bowlingScoreRecorder.handleScoring(scoresStr);
		} catch (BowlingScoringException e) {
			Assert.assertEquals("Input cannot be null", ((BowlingScoringException) e).getErrMessage());
		}

	}

	@Test
	public void testInvalidScoreFormat() {
		BowlingScoreRecorder bowlingScoreRecorder = new BowlingScoreRecorder();
		String scoresStr = "1 a 4 e r tttt ";
		try {
			bowlingScoreRecorder.handleScoring(scoresStr);
		} catch (BowlingScoringException e) {
			Assert.assertEquals("Input should have integers between 1-10 followed by a space",
					((BowlingScoringException) e).getErrMessage());
		}

	}

	@Test
	public void testHandleBowlingScoringPositive1() {
		BowlingScoreRecorder bowlingScoreRecorder = new BowlingScoreRecorder();
		String scoresStr = "1 2 3 4";
		bowlingScoreRecorder.handleScoring(scoresStr);
		Assert.assertEquals(10, bowlingScoreRecorder.getTotalScore());

	}

	@Test
	public void testHandleBowlingScoringPositive2() {
		BowlingScoreRecorder bowlingScoreRecorder = new BowlingScoreRecorder();
		String scoresStr = "9 1 9 1";
		bowlingScoreRecorder.handleScoring(scoresStr);
		Assert.assertEquals(29, bowlingScoreRecorder.getTotalScore());

	}

	@Test
	public void testHandleBowlingScoringPositive3() {
		BowlingScoreRecorder bowlingScoreRecorder = new BowlingScoreRecorder();
		String scoresStr = "10 10 10 10 10 10 10 10 10 10 10 10";
		bowlingScoreRecorder.handleScoring(scoresStr);
		Assert.assertEquals(300, bowlingScoreRecorder.getTotalScore());

	}

	@Test
	public void testHandleBowlingScoringPositive4() {
		BowlingScoreRecorder bowlingScoreRecorder = new BowlingScoreRecorder();
		String scoresStr = "9 1 9 1 9 1 9 1 9 1 9 1 9 1 9 1 9 1 9 1 10";
		bowlingScoreRecorder.handleScoring(scoresStr);
		Assert.assertEquals(191, bowlingScoreRecorder.getTotalScore());

	}

}
