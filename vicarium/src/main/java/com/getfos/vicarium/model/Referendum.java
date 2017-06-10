package com.getfos.vicarium.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@Entity
public class Referendum {
	@Id
	@GeneratedValue
	private Integer referendumId;
	private String denomination, description, pathDocument;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ")
	private Date date;
	
	public Referendum() {
	}

	public Integer getReferendumId() {
		return referendumId;
	}

	public void setReferendumId(Integer referendumId) {
		this.referendumId = referendumId;
	}

	public String getDenomination() {
		return denomination;
	}

	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPathDocument() {
		return pathDocument;
	}

	public void setPathDocument(String pathDocument) {
		this.pathDocument = pathDocument;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
