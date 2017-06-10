package com.getfos.vicarium.model;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@Entity
public class Deputy extends Politic{
	
	private String identifier;
	private String board;
	private String governmentAssignment;
	
	public Deputy() {
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
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

	@Override
	public String toString() {
		return "Deputy [identifier=" + identifier + ", board=" + board + ", governmentAssignment="
				+ governmentAssignment + "]";
	}
}
