package com.example.playload.request;


import java.util.Set;

import javax.validation.constraints.*;
 
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
 
    @NotBlank
    @Size(max = 50)
    private String fname;
    
    @NotBlank
    @Size(max = 50)
    private String lname;
    @NotBlank
    @Size(max = 50)
    private String numtel;
    @NotBlank
    @Size(max = 50)
    private String books;
    private Set<String> role;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
  
    public String getUsername() {
        return username;
    }
    public String getBooks() {
    	return this.books;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getfname() {
        return fname;
    }
 
    public void setfname(String fname) {
        this.fname = fname;
    }
    public String getlname() {
        return lname;
    }
 
    public void setlname(String lname) {
        this.lname = lname;
    }
 
    public String getPassword() {
        return password;
    }
    
    public String getnumtel() {
        return numtel;
    }
 
    public void setnumtel(String numtel) {
        this.numtel = numtel;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Set<String> getRole() {
      return this.role;
    }
    
    public void setRole(Set<String> role) {
      this.role = role;
    }
}