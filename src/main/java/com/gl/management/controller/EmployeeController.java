package com.gl.management.controller;

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

import com.gl.management.entity.Employee;
import com.gl.management.entity.Role;
import com.gl.management.entity.User;
import com.gl.management.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	private EmployeeService empService;
		
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		empService = employeeService;
	}
	
	//POST -for adding a role
	@PostMapping("/role")
	public Role saveRole(@RequestBody Role role) {
		return empService.saveRole(role);
	}
	
	//POST - for adding a user
	@PostMapping("/user")
	public User saveUser(@RequestBody User user) {
		return empService.saveUser(user);
	}

	//GET -for listing all employees
	@GetMapping("/employees")
	public List<Employee> findAll() {
		return empService.findAll();
	}
	
	//GET - for getting an employee by id
	@GetMapping("/employees/{empId}")
	public Employee getEmployee(@PathVariable int empId) {
		Employee emp = empService.findById(empId);
		if (emp == null) {
			throw new RuntimeException("Employee id not found - " + empId);
		}
		return emp;
	}

	//POST - for adding an employee
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		theEmployee.setId(0);
		empService.save(theEmployee);
		return theEmployee;
	}

	//PUT - update an employee record
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		empService.save(theEmployee);
		return theEmployee;
	}

	//DELETE -Delete an employee
	@DeleteMapping("/employees/{empId}")
	public String deleteEmployee(@PathVariable int empId) {
		Employee emp = empService.findById(empId);
		if (emp == null) {
			throw new RuntimeException("Employee id not found - " + empId);
		}
		empService.deleteById(empId);
		return "Deleted employee id - " + empId;
	}

	//GET -search employee by firstName
	@GetMapping("/employees/search/{firstName}")
	public List<Employee> searchByFirstName(@PathVariable String firstName) {
		return empService.searchByFirstName(firstName);
	}

	//GET - Sort all employees in Ascending order
	@GetMapping("/employees/sort")
	public List<Employee> sortByFirstName() {
		return empService.sortByFirstNameAsc();
	}
	
	//GET - Sort all employees in Descending order
	@GetMapping("/employees/desc")
	public List<Employee> descByFirstName(){
		return empService.sortByFirstNameDesc();
	}

}
