package com.gl.management.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.gl.management.entity.Role;
import com.gl.management.entity.User;

import lombok.Data;

@Data
public class MyUserDetails implements UserDetails {
	
	private User user;	
	
	public MyUserDetails(User user) {
		this.user=user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<Role> roles=user.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for(Role r:roles) {
			authorities.add(new SimpleGrantedAuthority(r.getName()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
