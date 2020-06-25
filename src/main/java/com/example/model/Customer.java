package com.example.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="Customer",
uniqueConstraints = { 
		@UniqueConstraint(columnNames = "username")}
		)
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int customerid;
	public String username;
	public String fname;
	public String lname;
	public String numtel;
	public String password;
	public String books;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn (name = "user_id"), 
				inverseJoinColumns = @JoinColumn (name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	public Customer() {
	}
	public Customer(String username, String password,String fname,String lname,String numtel,String books) {
		this.username = username;
		this.password = password;
		this.fname = fname;
		this.lname=lname;
		this.numtel = numtel;
		this.books = books;
	}
	public Set<Role> getRoles(){
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getId() {
		return this.customerid;
	}
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
	public String getfname() {
		return this.fname;
	}
	public String getlname() {
		return this.lname;
		
	}
	public String getnumtel() {
		return this.numtel;
		
	}

	public String getbooks() {
		return this.books;
		
	}
}
