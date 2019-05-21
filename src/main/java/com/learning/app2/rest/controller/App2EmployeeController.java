package com.learning.app2.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.app2.model.Employee;
import com.learning.app2.restcall.App2RestCall;

@RestController
@RequestMapping("/api/app/employee")
public class App2EmployeeController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private App2RestCall app2RestCall;

	@PostMapping("/employee")
	public ResponseEntity<String> saveEmployee(@RequestBody Employee employee) {
		boolean status = app2RestCall.saveEmployee(employee);
		if (status) {
			logger.info("Employee Data Saved in App1");
			return new ResponseEntity<>("Employee Data Saved in App1", HttpStatus.OK);
		}
		return new ResponseEntity<>("Employee Data save Failed in App1", HttpStatus.BAD_REQUEST);

	}

	@PutMapping("/employee")
	public ResponseEntity<String> updateEmployee(@RequestBody Employee employee) {
		boolean status = app2RestCall.saveEmployee(employee);
		if (status) {
			logger.info("Employee Data updated in App1");
			return new ResponseEntity<>("Employee Data Updated in App1", HttpStatus.OK);
		}
		logger.info("Employee Data updated Failed in App1");
		return new ResponseEntity<>("Employee Data  Updation Failed in App1", HttpStatus.BAD_REQUEST);

	}

	@GetMapping("/employee/{empId}")
	public ResponseEntity<Employee> emplyoyee(@PathVariable("empId") String empId) {

		Employee employee = app2RestCall.getEmployee(empId);
		if (!ObjectUtils.isEmpty(employee)) {
			logger.info("Employee data retrieved");
			return new ResponseEntity<>(employee, HttpStatus.OK);
		}
		logger.info("Employee data retrieved failed");
		return new ResponseEntity<>(employee, HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/employee/{empId}")
	public ResponseEntity<String> updateEmployee(@PathVariable("empId") String empId) {
		app2RestCall.deleteEmployee(empId);
		logger.info("Employee Deleted in App1{}", empId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
