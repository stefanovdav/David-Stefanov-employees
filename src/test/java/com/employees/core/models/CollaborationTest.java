package com.employees.core.models;

import static com.employees.core.models.Collaboration.calculateCollaborationPeriod;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;

import com.employees.repository.entities.InputRecord;
import org.junit.jupiter.api.Test;

public class CollaborationTest {

	@Test
	public void testCollaborationPeriodWithOverlappingDates() {
		InputRecord record1 = new InputRecord("1", "2", "2022-01-01", "2022-01-31");
		InputRecord record2 = new InputRecord("2", "2", "2022-01-15", "2022-02-14");

		int actual = calculateCollaborationPeriod(record1, record2);
		int expected = 16;

		assertEquals(expected, actual);
	}

	@Test
	public void testCollaborationPeriodWithNonOverlappingDates() {
		InputRecord record1 = new InputRecord("1", "2", "2022-01-01", "2022-01-31");
		InputRecord record2 = new InputRecord("2", "2", "2022-02-01", "2022-02-28");

		int actual = calculateCollaborationPeriod(record1, record2);
		int expected = 0;

		assertEquals(expected, actual);
	}

	@Test
	public void testCollaborationPeriodWithOneRecordBeforeAnother() {
		InputRecord record1 = new InputRecord("1", "2", "2022-01-01", "2022-01-31");
		InputRecord record2 = new InputRecord("2", "2", "2022-02-01", "2022-02-28");

		int actual = calculateCollaborationPeriod(record1, record2);
		int expected = 0;

		assertEquals(expected, actual);
	}

	@Test
	public void testCollaborationPeriodWithOneRecordAfterAnother() {
		InputRecord record1 = new InputRecord("1", "2", "2022-02-01", "2022-02-28");
		InputRecord record2 = new InputRecord("2", "2", "2022-01-01", "2022-01-31");

		int actual = calculateCollaborationPeriod(record1, record2);
		int expected = 0;

		assertEquals(expected, actual);
	}
}
