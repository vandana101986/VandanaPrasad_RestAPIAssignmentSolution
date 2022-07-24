package com.gl.management.service;

import java.util.List;

import com.gl.management.entity.Employee;
import com.gl.management.entity.Role;
import com.gl.management.entity.User;

public interface EmployeeService {
	
	public List<Employee> findAll();
	public Employee findById(int id);
	public void save(Employee emp);
	public void deleteById(int id);
	public List<Employee> searchByFirstName(String firstName);
	
	public List<Employee> sortByFirstNameAsc();
	public List<Employee> sortByFirstNameDesc();
	
	public User saveUser(User user);
	public Role saveRole(Role role);
	

}
