package com.learning.app2.restcall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.learning.app2.model.Employee;
import com.learning.app2.util.App2Constants;
import com.learning.app2.util.App2ServiceUrls;

@Component
public class App2RestCall {

	@Autowired
	private App2ServiceUrls app2ServiceUrls;

	@Autowired
	private RestTemplate restTemplate;

	
	/**
	 * RestCall save Student Data
	 * 
	 * @return status
	 */
	public boolean saveEmployee(Employee employee) {
		StringBuilder uriBuilderString = new StringBuilder();
		uriBuilderString.append(app2ServiceUrls.getBaseUrl());
		uriBuilderString.append(App2Constants.APP1_EMPLOYEE_URL).append(App2Constants.APP1_HOME_EMPLOYEE_URL);
		String url = String.valueOf(uriBuilderString);
		HttpEntity<Employee> entity = new HttpEntity<>(employee);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		if (response.getHeaders()!=null)
			return true;
		return false;
	}

	public void updateStudent(Employee employee) {
		StringBuilder uriBuilderString = new StringBuilder();
		uriBuilderString.append(app2ServiceUrls.getBaseUrl());
		uriBuilderString.append(App2Constants.APP1_EMPLOYEE_URL);
		String url = String.valueOf(uriBuilderString);
		HttpEntity<Employee> entity = new HttpEntity<>(employee);
		restTemplate.put(url, entity);

	}

	public Employee getEmployee(String empId) {
		StringBuilder uriBuilderString = new StringBuilder();
		uriBuilderString.append(app2ServiceUrls.getBaseUrl()).
		append(App2Constants.APP1_EMPLOYEE_URL).append(App2Constants.APP1_HOME_EMPLOYEE_URL)
		.append(App2Constants.BACK_SLASH+empId);
		String url = String.valueOf(uriBuilderString);
		Employee employee = restTemplate.getForObject(url, Employee.class);
		if (employee != null) {
			return employee;
		}
		return null;

	}

	public void deleteEmployee(String empId) {
		StringBuilder uriBuilderString = new StringBuilder();
		uriBuilderString.append(app2ServiceUrls.getBaseUrl())
		.append(App2Constants.APP1_EMPLOYEE_URL).append(App2Constants.APP1_HOME_EMPLOYEE_URL)
		.append(App2Constants.BACK_SLASH+empId);
		String url = String.valueOf(uriBuilderString);
		restTemplate.delete(url);

	}

}
