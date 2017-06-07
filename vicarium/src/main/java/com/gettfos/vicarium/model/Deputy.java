package com.gettfos.vicarium.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@Entity
public class Deputy {
	@Id
	@GeneratedValue
	private Integer id;
	private String name, surname, sex, qualification, occupation, board, governmentAssignment, pathPhoto;
	@OneToMany
	private List<Vote> votes = new ArrayList<Vote>();
	
	public Deputy() {
	}

	public Deputy(String name, String surname, String sex, String qualification, String occupation, String board,
			String governmentAssignment, String pathPhoto) {
		super();
		this.name = name;
		this.surname = surname;
		this.sex = sex;
		this.qualification = qualification;
		this.occupation = occupation;
		this.board = board;
		this.governmentAssignment = governmentAssignment;
		this.pathPhoto = pathPhoto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public String getGovernmentAssignment() {
		return governmentAssignment;
	}

	public void setGovernmentAssignment(String governmentAssignment) {
		this.governmentAssignment = governmentAssignment;
	}

	public String getPathPhoto() {
		return pathPhoto;
	}

	public void setPathPhoto(String pathPhoto) {
		this.pathPhoto = pathPhoto;
	}

	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	@Override
	public String toString() {
		return "Deputy [id=" + id + ", name=" + name + ", surname=" + surname + ", sex=" + sex + ", qualification="
				+ qualification + ", occupation=" + occupation + ", board=" + board + ", governmentAssignment="
				+ governmentAssignment + ", pathPhoto=" + pathPhoto + ", votes=" + votes + "]";
	}
	
}
