package com.employees.core.models;

import com.employees.repository.entities.InputRecord;
import com.employees.utills.DateParser;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.HashSet;

@Getter
@Setter
public class Projects {
	private final HashSet<ProjectPeriod> projects;

	public Projects() {
		this.projects = new HashSet<>();
	}

	public int getTotalCollaborationDays() {
		int total = 0;
		for (ProjectPeriod p: projects) {
			total += p.getPeriod();
		}
		return total;
	}

	public void add(ProjectPeriod p) {
		projects.add(p);
	}
}
