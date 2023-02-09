package com.employees.core.service;

import com.employees.core.models.Collaboration;
import com.employees.core.models.Colleagues;
import com.employees.repository.entities.InputRecord;
import com.employees.exception.Response;
import com.employees.exception.ApiException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Service
public class CSVService {
	public Response parseCsv(MultipartFile file) {
		// validate file
		if (file.isEmpty()) {
			throw new ApiException("File is empty", HttpStatus.BAD_REQUEST);
		}

		// parse CSV file to create a list of `Employee` objects
		try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			CsvToBean csvToBean = new CsvToBeanBuilder(reader).withType(InputRecord.class).withIgnoreLeadingWhiteSpace(true).build();

			// convert `CsvToBean` object to list of users
			List<InputRecord> inputRecords = csvToBean.parse();

			// save users list on model
			return new Response("Parsed successfully!", longestCollaboration(inputRecords));

		} catch (IOException e) {
			throw new ApiException("Can't open file", HttpStatus.BAD_REQUEST);
		}
	}

	private static Collaboration longestCollaboration (List<InputRecord> inputRecords) {
		List<Collaboration> collaborations = toCollaborations(inputRecords);
		int checker = 0;
		int longestCollaborationIndex = 0;

		for (int i = 0; i < collaborations.size(); i++) {
			int totalDays = collaborations.get(i).getProjects().getTotalCollaborationDays();
			if (totalDays > checker) {
				checker = totalDays;
				longestCollaborationIndex = i;
			}
		}
		return collaborations.get(longestCollaborationIndex);
	}

	private static List<Collaboration> toCollaborations(List<InputRecord> inputRecords) {
		// create a map of Colleagues to avoid defining same pair again
		Map<Colleagues, Collaboration> colleagues = new HashMap<>();

		//create Map of projects and the employees that worked on them
		Map<String, List<InputRecord>> projects = employeesToProjects(inputRecords);

		//loop through projects
		for (Map.Entry<String, List<InputRecord>> entry : projects.entrySet()) {
			List<InputRecord> projectInputRecords = entry.getValue();
			//loop through project employees
			for (int i = 0; i < projectInputRecords.size(); i++) {
				// for every employee check all other employees if they have overlapping date
				for (int j = i + 1; j < projectInputRecords.size(); j++) {
					InputRecord e1 = projectInputRecords.get(i);
					InputRecord e2 = projectInputRecords.get(j);
					// skip the iteration if the employees have the same id
					if (Objects.equals(e1.getId(), e2.getId())) {
						continue;
					}
					// use colleagues as key because I have made its hash to be the same
					if (colleagues.containsKey(new Colleagues(e1.getId(), e2.getId()))) {
						colleagues.get(new Colleagues(e1.getId(), e2.getId())).addProject(e1, e2);
					} else {
						colleagues.put(new Colleagues(e1.getId(), e2.getId()), new Collaboration(e1.getId(), e2.getId()));
						colleagues.get(new Colleagues(e1.getId(), e2.getId())).addProject(e1, e2);
					}
				}
			}
		}
		return new ArrayList<Collaboration>(colleagues.values());
	}

	private static Map<String, List<InputRecord>> employeesToProjects(List<InputRecord> inputRecords) {
		// the key is projectId
		Map<String, List<InputRecord>> projects = new HashMap<>();
		for (InputRecord e : inputRecords) {
			if (!projects.containsKey(e.getProjectId())) {
				projects.put(e.getProjectId(), new ArrayList<>());
			}
			projects.get(e.getProjectId()).add(e);
		}
		return projects;
	}

	//compare the times

}
