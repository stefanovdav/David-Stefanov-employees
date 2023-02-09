package com.employees.utills;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateParser {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	// parse String date to time
	// TODO: rename
	public static Calendar format(String date) {
		Calendar calendar = Calendar.getInstance();
		if (date.trim().equals("NULL")) {
			try {
				calendar.setTime(sdf.parse(sdf.format(calendar.getTime())));
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		} else {
			try {
				calendar.setTime(sdf.parse(date));
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}
		return calendar;
	}
}
