package com.gettfos.vicarium.model;

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
	private Integer id;
	private String denomination, description, pathDocument;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ")
	private Date date;
	
	public Referendum() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Referendum [id=" + id + ", denomination=" + denomination + ", description=" + description
				+ ", pathDocument=" + pathDocument + ", date=" + date + "]";
	}
	
}
