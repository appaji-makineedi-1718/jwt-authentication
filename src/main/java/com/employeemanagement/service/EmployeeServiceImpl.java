package com.employeemanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.employeemanagement.entity.Employee;
import com.employeemanagement.repository.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepo repo;
	
	public EmployeeServiceImpl(EmployeeRepo repo) {
		this.repo = repo;
	}

	@Override
	public List<Employee> getAllEmployees() {
		return repo.findAll();
	}

	@Override
	public Optional<Employee> getEmployeeById(int id) {
		return repo.findById(id);
	}

	@Override
	public Employee createEmployee(Employee employee) {
		return repo.save(employee);
	}

	@Override
	public Employee updateEmployee(int id, Employee employee) {
		Employee existingEmployee = repo.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
		existingEmployee.setName(employee.getName());
		existingEmployee.setCountry(employee.getCountry());
		existingEmployee.setCity(employee.getCity());
		existingEmployee.setSalary(employee.getSalary());
		
		return repo.save(existingEmployee);
	}

	@Override
	public Employee patchEmployee(int id, Employee employee) {
		

		    Employee existingEmployee = repo.findById(id)
		            .orElseThrow(() -> new RuntimeException("Employee not found"));

		    if (employee.getName() != null) {
		        existingEmployee.setName(employee.getName());
		    }

		    if (employee.getCountry() != null) {
		        existingEmployee.setCountry(employee.getCountry());
		    }

		    if (employee.getCity() != null) {
		        existingEmployee.setCity(employee.getCity());
		    }

		    if (employee.getSalary() != null) {
		        existingEmployee.setSalary(employee.getSalary());
		    }

		    return repo.save(existingEmployee);
		
	}

}
