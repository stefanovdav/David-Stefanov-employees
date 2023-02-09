package com.employees.utills;

import org.junit.jupiter.api.Test;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class DateParserTest {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Test
	public void testFormatWithValidDate() throws ParseException {
		Calendar expected = Calendar.getInstance();
		expected.setTime(sdf.parse("2022-12-25"));

		Calendar actual = DateParser.format("2022-12-25");

		assertEquals(expected, actual);
	}

	@Test
	public void testFormatWithNullDate() throws ParseException {
		Calendar expected = Calendar.getInstance();
		expected.setTime(sdf.parse(sdf.format(expected.getTime())));

		Calendar actual = DateParser.format("NULL");

		assertEquals(expected, actual);
	}

	@Test
	public void testFormatWithInvalidDate() {
		assertThrows(RuntimeException.class, () -> {
			DateParser.format("invalid date");
		});
	}
}