package com.employeemanagement.service;

import java.util.List;
import java.util.Optional;

import com.employeemanagement.entity.Employee;


public interface EmployeeService {
	
	public List<Employee> getAllEmployees();
	
	public Optional<Employee> getEmployeeById(int id);
	
	public Employee createEmployee(Employee employee);
	
	public Employee updateEmployee(int id, Employee employee);
	
	public Employee patchEmployee(int id, Employee employee);

}
