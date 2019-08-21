package com.code.lottery;

import java.util.Date;

import com.code.common.utility.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Lotto {
	private int id;
	private Date draw_date;
	private int num1;
	private int num2;
	private int num3;
	private int num4;
	private int num5;
	private int num6;

	// Constructors *************
	public Lotto() {
		// DEFAULT
	}

	public Lotto(int id, Date draw_date, int num1, int num2, int num3, int num4, int num5, int num6) {
		this.id = id;
		this.draw_date = draw_date;
		this.num1 = num1;
		this.num2 = num2;
		this.num3 = num3;
		this.num4 = num4;
		this.num5 = num5;
		this.num6 = num6;
	}

	public Lotto(Date draw_date, int num1, int num2, int num3, int num4, int num5, int num6) {
		this.draw_date = draw_date;
		this.num1 = num1;
		this.num2 = num2;
		this.num3 = num3;
		this.num4 = num4;
		this.num5 = num5;
		this.num6 = num6;
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

	public int getNum1() {
		return num1;
	}

	public void setNum1(int num1) {
		this.num1 = num1;
	}

	public int getNum2() {
		return num2;
	}

	public void setNum2(int num2) {
		this.num2 = num2;
	}

	public int getNum3() {
		return num3;
	}

	public void setNum3(int num3) {
		this.num3 = num3;
	}

	public int getNum4() {
		return num4;
	}

	public void setNum4(int num4) {
		this.num4 = num4;
	}

	public int getNum5() {
		return num5;
	}

	public void setNum5(int num5) {
		this.num5 = num5;
	}

	public int getNum6() {
		return num6;
	}

	public void setNum6(int num6) {
		this.num6 = num6;
	}

	// Developer notes
	@Override
	public String toString() {
		return "{" + Constants.TOSTRING_PREFIX + "id : " + id + Constants.TOSTRING_PREFIX + "draw_date : " + draw_date
				+ Constants.TOSTRING_PREFIX + "num1 : " + num1 + Constants.TOSTRING_PREFIX + "num2 : " + num2
				+ Constants.TOSTRING_PREFIX + "num3 : " + num3 + Constants.TOSTRING_PREFIX + "num4 : " + num4
				+ Constants.TOSTRING_PREFIX + "num5 : " + num5 + Constants.TOSTRING_PREFIX + "num6 : " + num6 + "\n}";
	}
}
