package com.getfos.vicarium.model;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@Entity
public class Deputy extends Politic{
	
	private String identifier;
	private String politicalGroup;
	private String politicalList;
	private String governmentAssignment;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ")
	private Date lastUpdateDate;
	
	public Deputy() {
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getPoliticalGroup() {
		return politicalGroup;
	}

	public void setPoliticalGroup(String politicalGroup) {
		this.politicalGroup = politicalGroup;
	}

	public String getGovernmentAssignment() {
		return governmentAssignment;
	}

	public void setGovernmentAssignment(String governmentAssignment) {
		this.governmentAssignment = governmentAssignment;
	}

	public String getPoliticalList() {
		return politicalList;
	}

	public void setPoliticalList(String politicalList) {
		this.politicalList = politicalList;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	@Override
	public String toString() {
		return "Deputy [identifier=" + identifier + ", politicalGroup=" + politicalGroup + ", politicalList="
				+ politicalList + ", governmentAssignment=" + governmentAssignment + ", lastUpdateDate="
				+ lastUpdateDate + "]";
	}

}
