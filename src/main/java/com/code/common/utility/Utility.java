package com.code.common.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

public class Utility {

	// Null value replacing method
	public static String replaceNull(final String input) {
		return input == null ? "" : input;
	}

	// Sub method to trim non-null string
	public static String trimString(final String input) {
		if (input != null && !input.endsWith("")) {
			return input.trim();
		} else {
			return input;
		}
	}

	// Sub method to check if parameter is empty or null
	public static boolean isEmpty(final String str) {
		boolean flag = false;

		if (str == null || str.equalsIgnoreCase("")) {
			flag = true;
		}
		return flag;
	}

	// Check if String is numeric value
	public static boolean isNumeric(String numStr) {
		return numStr.matches("[+-]?\\d*(\\.\\d+)?");
	}

	// Convert String to database ready date value
	public static Date strToDate(String input) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd").parse(input);
	}

	public static int nextRandom(int min, int max) {

		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	public static int[] nextRandomArray(int count, int max) {

		int[] nums = new int[count];

		ArrayList<Integer> numList = new ArrayList<Integer>();
		for (int i = 1; i < max + 1; i++) {
			numList.add(i);
		}

		Collections.shuffle(numList);

		for (int j = 0; j < count; j++) {
			nums[j] = numList.get(j);
		}

		Arrays.sort(nums);
		return nums;
	}
}
