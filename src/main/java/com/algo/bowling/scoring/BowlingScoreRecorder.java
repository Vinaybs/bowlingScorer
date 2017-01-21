package com.algo.bowling.scoring;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.algo.bowling.scoring.exception.BowlingScoringException;

/**
 * This class keeps track of Bowling scoring. It takes a string of digits from
 * 1-10 seperated by a space. A game can have maximum 10 frames, each having two
 * scores.
 * 
 * @author VinayBS
 *
 */
public class BowlingScoreRecorder {

	private List<Frame> frames = new ArrayList<Frame>();
	private int totalScore = 0;
	private int count = 0;

	public int getTotalScore() {
		return totalScore;
	}

	/**
	 * This is the main method that will handle logic of scoring. It takes a
	 * string of space separated digits, validates the input and evaluates the
	 * score.
	 * 
	 * @param scoresStr
	 * @throws BowlingScoringException
	 */
	public void handleScoring(String scoresStr) throws BowlingScoringException {

		// Validate input
		validateInput(scoresStr);

		List<Integer> scores = setupScoresList(scoresStr);

		// Loop while list has any more scores left to process
		while (hasMoreScores(scores)) {
			int score = scores.get(count);
			count++;
			if (score == 10) {
				handleStrike(scores);
			} else {
				handleNormalScores(score, scores);
			}
		}
	}

	/**
	 * Sets up a list of integers from a space separated string of digits from
	 * 1-10.
	 * 
	 * @param scores
	 * @return
	 */
	private List<Integer> setupScoresList(String scores) {
		List<Integer> scoresList = new ArrayList<Integer>();
		String[] scoresStr = scores.split(" ");
		for (String score : scoresStr) {
			scoresList.add(Integer.parseInt(score));
		}
		return scoresList;
	}

	/**
	 * Validate input against null and ensure format is as per game rules.
	 * 
	 * @param scores
	 * @throws BowlingScoringException
	 */
	private void validateInput(String scores) throws BowlingScoringException {

		if (null == scores) {
			throw new BowlingScoringException("Input cannot be null");
		}
		if (!Pattern.matches("(^(([0-9]|10))(\\s([0-9]|10))*)", scores)) {
			throw new BowlingScoringException("Input should have integers between 1-10 followed by a space");
		}
		if (scores.split(" ").length > 21) {
			throw new BowlingScoringException("Scores cannot be more than 21");
		}

	}

	/**
	 * This method handles when there is a strike.
	 * 
	 * @param scores
	 */
	private void handleStrike(List<Integer> scores) {

		if (isSpare() && !hasMoreScores(scores)) {
			handleSpare(10);
		}

		Frame frame = new Frame(10, -1);
		if (frames.size() > 1) {
			Frame latestFrame = frames.get(frames.size() - 1);
			if (latestFrame.isStrike()) {
				Frame previousFrame = frames.get(frames.size() - 2);
				totalScore += 30;
				previousFrame.totalScore = totalScore;
			}
		}
		frames.add(frame);
	}

	/**
	 * Handles the scoring when there is a spare.
	 * 
	 * @param score
	 */
	private void handleSpare(int score) {

		Frame latestFrame = frames.get(frames.size() - 1);
		latestFrame.totalScore = 10 + score;
		totalScore += latestFrame.totalScore;

	}

	/**
	 * Checks if the latest frame is a Spare.
	 * 
	 * @return
	 */
	private boolean isSpare() {
		return !frames.isEmpty() ? frames.get(frames.size() - 1).isSpare() : false;
	}

	/**
	 * This method handles score if it is not a strike.
	 * 
	 * @param score
	 * @param scores
	 */
	private void handleNormalScores(int score, List<Integer> scores) {

		int nextScore = -1;

		if (isSpare()) {
			handleSpare(score);
		}

		if (hasMoreScores(scores)) {
			nextScore = scores.get(count);
			count++;
		}

		if (frames.size() < 10) {
			Frame frame = new Frame(score, nextScore);
			if (!frame.isSpare()) {
				totalScore += score + nextScore;
				frame.totalScore = totalScore;
			}

			frames.add(frame);
			if (!hasMoreScores(scores) && isSpare()) {
				handleSpare(0);
			}

		}

	}

	/**
	 * Checks if there are any more scores to process
	 * 
	 * @param scores
	 * @return
	 */
	private boolean hasMoreScores(List<Integer> scores) {
		boolean hasMoreScores = false;
		if (count < scores.size())
			hasMoreScores = true;

		return hasMoreScores;
	}

}
