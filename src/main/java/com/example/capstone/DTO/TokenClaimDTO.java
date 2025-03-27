package com.example.capstone.DTO;

public class TokenClaimDTO {
private String email;
private String name;
public TokenClaimDTO()
{
	
}
public TokenClaimDTO(String email, String name) {
	super();
	this.email = email;
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

}
