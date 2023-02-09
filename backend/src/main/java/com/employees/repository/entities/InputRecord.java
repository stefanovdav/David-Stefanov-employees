package com.employees.repository.entities;

import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InputRecord {
	@CsvBindByPosition(position = 0)
	private String id;
	@CsvBindByPosition(position = 1)
	private String projectId;
	@CsvBindByPosition(position = 2)
	private String startDate;
	@CsvBindByPosition(position = 3)
	private String endDate;
}
