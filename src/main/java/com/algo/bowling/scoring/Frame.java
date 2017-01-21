package com.algo.bowling.scoring;

/**
 * 
 * @author VinayBS
 *
 */
public class Frame {
	int firstScore = -1;
	int secondScore = -1;
	int totalScore = 0;

	public Frame(int firstScore, int secondScore) {
		this.firstScore = firstScore;
		this.secondScore = secondScore;
	}

	public void recordTotalScore(int cumulativeScore) {
		totalScore = secondScore != -1 ? cumulativeScore + firstScore + secondScore : cumulativeScore + firstScore;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public boolean isStrike() {
		return firstScore == 10 ? true : false;
	}

	public boolean isSpare() {
		return secondScore != -1 && firstScore + secondScore == 10 ? true : false;
	}
}
