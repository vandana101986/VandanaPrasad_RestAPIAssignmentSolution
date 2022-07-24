package com.gl.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gl.management.dao.EmployeeRepository;
import com.gl.management.dao.RoleRepository;
import com.gl.management.dao.UserRepository;
import com.gl.management.entity.Employee;
import com.gl.management.entity.Role;
import com.gl.management.entity.User;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepo;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	BCryptPasswordEncoder bcryptEncoder;
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		empRepo = employeeRepository;
	}
	
	@Override
	public Role saveRole(Role role) {
		return roleRepo.save(role);
	}

	@Override
	public User saveUser(User user) {
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}
	
	@Override
	public void save(Employee emp) {
		empRepo.save(emp);
	}
	
	@Override
	public List<Employee> findAll() {
		return empRepo.findAll();
	}

	@Override
	public Employee findById(int id) {
		Optional<Employee> result = empRepo.findById(id);
		Employee emp = null;
		
		if(result.isPresent()) {
			emp = result.get();
		}
		else {
			throw new RuntimeException("Did not find employee id : "+id);
		}
		return emp;
	}

	@Override
	public void deleteById(int id) {
		empRepo.deleteById(id);
	}

	public List<Employee> searchByFirstName(String firstName) {
		return empRepo.findByFirstNameContainsAllIgnoreCase(firstName);
	}

	@Override
	public List<Employee> sortByFirstNameAsc() {
		return empRepo.findAllByOrderByFirstNameAsc();
	}
	
	@Override
	public List<Employee> sortByFirstNameDesc() {
		return empRepo.findAllByOrderByFirstNameDesc();
	}




	

}
