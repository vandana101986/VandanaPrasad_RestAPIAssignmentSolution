package com.gl.management.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.management.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
