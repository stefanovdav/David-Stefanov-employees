package com.employees.api;

import com.employees.exception.Response;
import com.employees.core.service.CSVService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1")
public class CsvController {
    private final CSVService csvService;

    public CsvController(CSVService csvService) {
        this.csvService = csvService;
    }

    @PostMapping("/parse-csv")
    public ResponseEntity<Response> uploadCSVFile(@RequestParam("file") MultipartFile file) {
        Response response = csvService.parseCsv(file);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
