package com.code.lottery;

import com.code.common.utility.Constants;

public class OccurrenceStat {
	private int number;
	private int count;

	// Constructors *************
	public OccurrenceStat() {
		// DEFAULT
	}

	public OccurrenceStat(int number, int count) {
		this.number = number;
		this.count = count;
	}

	/* Getters and setters */
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	// Developer notes
	@Override
	public String toString() {
		return "{" + Constants.TOSTRING_PREFIX + "number : " + number + Constants.TOSTRING_PREFIX + "count : " + count
				+ "\n}";
	}
}
