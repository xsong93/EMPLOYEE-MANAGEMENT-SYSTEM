package com.one.emsys.restapi.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.one.emsys.restapi.entity.Employee;
import com.one.emsys.restapi.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	// autowire the CustomerService
	@Autowired
	private EmployeeService employeeService;
	
	// add mapping for GET /employee
	@GetMapping("/employees")
	public List<Employee> getCustomer() {
		return employeeService.getEmployees();
	}
	
	// add mapping for GET /employees/{employeeId}
	@GetMapping("employees/{employeeId}")
	public Employee getCustomer(@PathVariable int employeeId) {
		Employee theEmployee = employeeService.getEmployee(employeeId);
		if (theEmployee == null) {
			throw new EmployeeNotFoundException("Employee id not found -" + employeeId);
		}
		return theEmployee;
	}
	
	// add mapping for POST /employees - add new employee
	@PostMapping("/employees")
	public Employee addCustomer(@RequestBody Employee theEmployee) {
		// in case the pass an id in JSON ... set id to 0
		// this is force a save of new item ... instead of update
		theEmployee.setId(0);
		employeeService.saveEmployee(theEmployee);
		return theEmployee;
	}
	
	// add mapping for PUT /employees - update existing employee
	@PutMapping("/employees")
	public Employee updateCustomer(@RequestBody Employee theEmployee) {
		employeeService.saveEmployee(theEmployee);
		return theEmployee;
	}
	
	// add mapping for DELETE /employees/{employeeId} - delete employee
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		Employee tempEmployee = employeeService.getEmployee(employeeId);
		
		// throw exception if null
		if(tempEmployee == null) {
			throw new EmployeeNotFoundException("Employee id not found" + employeeId);
		}
		employeeService.deleteEmployee(employeeId);
		return "Deleted employee id - " + employeeId;
	}
}
