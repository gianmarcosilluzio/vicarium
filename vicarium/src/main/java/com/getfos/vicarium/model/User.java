package com.getfos.vicarium.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@Entity
public class User extends Politic{
	private String facebookId;
	private String email;
	@JsonIgnore
	@Column(updatable= false)
	private String password;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ")
	@Column(updatable= false)
	private Date lastAccess;
	
	public User() {
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}

	@Override
	public String toString() {
		return "User [facebookId=" + facebookId + ", email=" + email + ", password=" + password + ", lastAccess="
				+ lastAccess + "]";
	}
	
}
