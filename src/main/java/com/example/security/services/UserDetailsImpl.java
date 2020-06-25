package com.example.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.model.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private int id;

	private String username;

	private String fname;
	
	private String lname;
	private String numtel;
	private String books;
	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(int id, String username, String password,
			Collection<? extends GrantedAuthority> authorities,String fname,String lname,String numtel,String books) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.fname = fname;
		this.lname=lname;
		this.numtel=numtel;
		this.books =books;
	}

	public static UserDetailsImpl build(Customer user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new UserDetailsImpl(
				user.getId(), 
				user.getUsername(), 
				user.getPassword(), 
				authorities,
				user.getfname(),
				user.getlname(),
				user.getnumtel(),
				user.getbooks()
				);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public int getId() {
		return id;
	}
	public void setfname(String fname) {
		this.fname = fname;
	}
	public void setlname(String lname) {
		this.lname = lname;
	}
	public void setnumtel(String num) {
		this.numtel = num;
	}
	public void setbooks(String books) {
		this.books = books;;
	}
	public String getbooks() {
		return this.books;
		
	}
	public String getfname() {
		return fname;
	}
	public String getlname() {
		return lname;
	}
	
	public String getnumtel() {
		return numtel;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
}