package com.employees.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProjectRecord {
	private final String projectId;
	private final List<InputRecord> inputs;
}
