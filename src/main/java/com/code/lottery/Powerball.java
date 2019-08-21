package com.code.lottery;

import java.util.Date;

import com.code.common.utility.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Powerball {
	private int id;
	private Date draw_date;
	private int ball1;
	private int ball2;
	private int ball3;
	private int ball4;
	private int ball5;
	private int powerball;

	// Constructors *************
	public Powerball() {
		// DEFAULT
	}

	public Powerball(int id, Date draw_date, int ball1, int ball2, int ball3, int ball4, int ball5, int powerball) {
		this.id = id;
		this.draw_date = draw_date;
		this.ball1 = ball1;
		this.ball2 = ball2;
		this.ball3 = ball3;
		this.ball4 = ball4;
		this.ball5 = ball5;
		this.powerball = powerball;
	}

	public Powerball(Date draw_date, int ball1, int ball2, int ball3, int ball4, int ball5, int powerball) {
		this.draw_date = draw_date;
		this.ball1 = ball1;
		this.ball2 = ball2;
		this.ball3 = ball3;
		this.ball4 = ball4;
		this.ball5 = ball5;
		this.powerball = powerball;
	}

	/* Getters and setters */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DEFAULT_TIME_PATTERN, timezone = Constants.DEFAULT_TIMEZONE)
	public Date getDrawDate() {
		return draw_date;
	}

	public void setDrawDate(Date draw_date) {
		this.draw_date = draw_date;
	}

	public int getBall1() {
		return ball1;
	}

	public void setBall1(int ball1) {
		this.ball1 = ball1;
	}

	public int getBall2() {
		return ball2;
	}

	public void setBall2(int ball2) {
		this.ball2 = ball2;
	}

	public int getBall3() {
		return ball3;
	}

	public void setBall3(int ball3) {
		this.ball3 = ball3;
	}

	public int getBall4() {
		return ball4;
	}

	public void setBall4(int ball4) {
		this.ball4 = ball4;
	}

	public int getBall5() {
		return ball5;
	}

	public void setBall5(int ball5) {
		this.ball5 = ball5;
	}

	public int getPowerball() {
		return powerball;
	}

	public void setPowerball(int powerball) {
		this.powerball = powerball;
	}

	// Developer notes
	@Override
	public String toString() {
		return "{" + Constants.TOSTRING_PREFIX + "id : " + id + Constants.TOSTRING_PREFIX + "draw_date : " + draw_date
				+ Constants.TOSTRING_PREFIX + "ball1 : " + ball1 + Constants.TOSTRING_PREFIX + "ball2 : " + ball2
				+ Constants.TOSTRING_PREFIX + "ball3 : " + ball3 + Constants.TOSTRING_PREFIX + "ball4 : " + ball4
				+ Constants.TOSTRING_PREFIX + "ball5 : " + ball5 + Constants.TOSTRING_PREFIX + "powerball : "
				+ powerball + "\n}";
	}
}
