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
	private String identifier;
	private String denomination, description, pathDocument;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ")
	private Date date;
	private Integer totalVoteDeputyFavorevole;
	private Integer totalVoteDeputyContrario;
	private Integer totalVoteDeputyAstenuto;
	private Integer totalVoteDeputyAssente;
	private Integer totalVoteUserFavorevole;
	private Integer totalVoteUserContrario;
	private Integer totalVoteUserAstenuto;
	
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

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Integer getTotalVoteDeputyFavorevole() {
		return totalVoteDeputyFavorevole;
	}

	public void setTotalVoteDeputyFavorevole(Integer totalVoteDeputyFavorevole) {
		this.totalVoteDeputyFavorevole = totalVoteDeputyFavorevole;
	}

	public Integer getTotalVoteDeputyContrario() {
		return totalVoteDeputyContrario;
	}

	public void setTotalVoteDeputyContrario(Integer totalVoteDeputyContrario) {
		this.totalVoteDeputyContrario = totalVoteDeputyContrario;
	}

	public Integer getTotalVoteDeputyAstenuto() {
		return totalVoteDeputyAstenuto;
	}

	public void setTotalVoteDeputyAstenuto(Integer totalVoteDeputyAstenuto) {
		this.totalVoteDeputyAstenuto = totalVoteDeputyAstenuto;
	}

	public Integer getTotalVoteDeputyAssente() {
		return totalVoteDeputyAssente;
	}

	public void setTotalVoteDeputyAssente(Integer totalVoteDeputyAssente) {
		this.totalVoteDeputyAssente = totalVoteDeputyAssente;
	}

	public Integer getTotalVoteUserFavorevole() {
		return totalVoteUserFavorevole;
	}

	public void setTotalVoteUserFavorevole(Integer totalVoteUserFavorevole) {
		this.totalVoteUserFavorevole = totalVoteUserFavorevole;
	}

	public Integer getTotalVoteUserContrario() {
		return totalVoteUserContrario;
	}

	public void setTotalVoteUserContrario(Integer totalVoteUserContrario) {
		this.totalVoteUserContrario = totalVoteUserContrario;
	}

	public Integer getTotalVoteUserAstenuto() {
		return totalVoteUserAstenuto;
	}

	public void setTotalVoteUserAstenuto(Integer totalVoteUserAstenuto) {
		this.totalVoteUserAstenuto = totalVoteUserAstenuto;
	}
	
	
}
