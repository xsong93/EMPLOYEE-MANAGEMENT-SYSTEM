package com.emsys.client.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.emsys.client.model.Employee;

@Service
public class EmployeeServiceRestClientImpl implements EmployeeService {

	private RestTemplate restTemplate;

	private String emsysRestUrl;
		
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	public EmployeeServiceRestClientImpl(RestTemplate theRestTemplate, @Value("${emsys.rest.url}") String theUrl) {
		restTemplate = theRestTemplate;
		emsysRestUrl = theUrl;	
		logger.info("Loaded property:  emsys.rest.url=" + emsysRestUrl);
	}
	
	@Override
	public List<Employee> getEmployees() {
		logger.info("in getEmployees(): Calling REST API " + emsysRestUrl);

		// make REST call
		ResponseEntity<List<Employee>> responseEntity = restTemplate.exchange(emsysRestUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {});

		// get the list of Employees from response
		List<Employee> employees = responseEntity.getBody();

		logger.info("in getEmployees(): employees" + employees);
		return employees;
	}

	@Override
	public Employee getEmployee(int theId) {
		logger.info("in getEmployee(): Calling REST API " + emsysRestUrl);

		// make REST call
		Employee theEmployee = restTemplate.getForObject(emsysRestUrl + "/" + theId, Employee.class);

		logger.info("in saveEmployee(): theEmployee=" + theEmployee);
		return theEmployee;
	}

	@Override
	public void saveEmployee(Employee theEmployee) {
		logger.info("in saveEmployee(): Calling REST API " + emsysRestUrl);
		int employeeId = theEmployee.getId();

		// make REST call
		if (employeeId == 0) {
			// add employee
			restTemplate.postForEntity(emsysRestUrl, theEmployee, String.class);			
		} else {
			// update employee
			restTemplate.put(emsysRestUrl, theEmployee);
		}
		logger.info("in saveEmployee(): success");	
	}

	@Override
	public void deleteEmployee(int theId) {
		logger.info("in deleteEmployee(): Calling REST API " + emsysRestUrl);

		// make REST call
		restTemplate.delete(emsysRestUrl + "/" + theId);

		logger.info("in deleteEmployee(): deleted employee theId=" + theId);
	}

}
