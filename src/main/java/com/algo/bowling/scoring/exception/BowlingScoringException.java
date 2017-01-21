package com.algo.bowling.scoring.exception;

public class BowlingScoringException extends RuntimeException {

	String errMessage;

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	public BowlingScoringException(String errMessage) {
		this.errMessage = errMessage;
	}

}
