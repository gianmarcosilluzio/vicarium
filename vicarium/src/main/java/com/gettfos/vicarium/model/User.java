package com.gettfos.vicarium.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@Entity
public class User {
	@Id
	@GeneratedValue
	private Integer id;
	private String facebookId, name, surname, sex, qualification, occupation, pathPhoto;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ")
	private Date registrationDate;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ")
	private Date lastAccess;
	@OneToMany
	private List<Vote> votes = new ArrayList<Vote>();
	
	public User() {
	}
	
	public User(String facebookId, String name, String surname, String sex, String qualification, String occupation,
			String pathPhoto, Date registrationDate, Date lastAccess) {
		super();
		this.facebookId = facebookId;
		this.name = name;
		this.surname = surname;
		this.sex = sex;
		this.qualification = qualification;
		this.occupation = occupation;
		this.pathPhoto = pathPhoto;
		this.registrationDate = registrationDate;
		this.lastAccess = lastAccess;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getPathPhoto() {
		return pathPhoto;
	}

	public void setPathPhoto(String pathPhoto) {
		this.pathPhoto = pathPhoto;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}

	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", facebookId=" + facebookId + ", name=" + name + ", surname=" + surname + ", sex="
				+ sex + ", qualification=" + qualification + ", occupation=" + occupation + ", pathPhoto=" + pathPhoto
				+ ", registrationDate=" + registrationDate + ", lastAccess=" + lastAccess + "]";
	}

	
	
}
