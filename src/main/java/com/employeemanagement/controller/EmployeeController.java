package com.employeemanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeemanagement.entity.Employee;
import com.employeemanagement.service.EmployeeService;
import com.employeemanagement.status.StatusDto;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/getAllEmployees")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}
	
	@GetMapping("/getEmployeeById/{id}")
	public Optional<Employee> getEmployeeById(@PathVariable int id) {
		return employeeService.getEmployeeById(id);
	}
	
	@PostMapping("/createEmployee")
	public ResponseEntity<StatusDto>  createEmployee(@RequestBody Employee employee) {
		 employeeService.createEmployee(employee);
		 return ResponseEntity.status(HttpStatus.CREATED).body(new StatusDto("Employee created successfully"));
						
	}
	
	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<StatusDto> updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
		 employeeService.updateEmployee(id, employee);
		 return ResponseEntity.status(HttpStatus.CREATED).body(new StatusDto("Employee updated successfully"));
	}
	
	@PutMapping("/patchEmployee/{id}")
	public ResponseEntity<StatusDto> patchEmployee(@PathVariable int id, @RequestBody Employee employee) {
		employeeService.patchEmployee(id, employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(new StatusDto("Employee updated successfully"));
	}
	
	
	
	
	
}
