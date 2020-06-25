package com.example.playload.response;


import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private int id;
	private String username;
	private String fname;
	private String lname;
	private String numtel;
	private List<String> roles;

	public JwtResponse(String accessToken, int id, String username, String fname,String lname,String numtel, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.fname = fname;
		this.lname= lname;
		this.numtel = numtel;
		this.roles = roles;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getUsername() {
		return username;
	}
    public String getnumtel() {
        return numtel;
    }
 
    public void setnumtel(String numtel) {
        this.numtel = numtel;
    }
 
	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}
}