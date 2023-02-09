package com.employees.core.models;

import com.employees.repository.entities.InputRecord;
import com.employees.utills.DateParser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Calendar;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Collaboration {

	private final Colleagues pair;
	private final Projects projects;

	public Collaboration(String id1, String id2) {
		this.pair = new Colleagues(id1, id2);
		this.projects= new Projects();
	}

	public void addProject(InputRecord e1, InputRecord e2) {
		projects.add(new ProjectPeriod(e1.getProjectId(),
				calculateCollaborationPeriod(e1, e2)));
	}

	static int calculateCollaborationPeriod(InputRecord e1, InputRecord e2) {
		Calendar dateFrom1 = DateParser.format(e1.getStartDate());
		Calendar dateTo1 = DateParser.format(e1.getEndDate());
		Calendar dateFrom2 = DateParser.format(e2.getStartDate());
		Calendar dateTo2 = DateParser.format(e2.getEndDate());

		//take the later start and the earlier finish
		Calendar start = (dateFrom1.after(dateFrom2)) ? dateFrom1 : dateFrom2;
		Calendar end = (dateTo1.before(dateTo2)) ? dateTo1 : dateTo2;

		if (start.before(end)) {
			long diff = end.getTimeInMillis() - start.getTimeInMillis();
			return (int) (diff / (1000 * 60 * 60 * 24));
		} else {
			return 0;
		}
	}
}
