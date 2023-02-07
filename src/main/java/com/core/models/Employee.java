package com.core.models;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor

public class Employee {
	@CsvBindByPosition(position = 0)
	private String id;
	@CsvBindByPosition(position = 1)
	private String projectId;
	@CsvBindByPosition(position = 2)
	private String dateFrom;
	@CsvBindByPosition(position = 3)
	private String dateTo;
}
