package com.cab.book.com.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyDriverDetails extends Driver implements UserDetails{

	Driver driver = new Driver();
	public MyDriverDetails(Driver driver) {
		super();
		this.driver = driver;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setUser(Driver driver) {
		this.driver = driver;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		driver.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
		return authorities;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return driver.getName();
	}

	@Override
	public String getPassword() {
		return driver.getPassword();
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
