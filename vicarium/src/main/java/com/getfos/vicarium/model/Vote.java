package com.getfos.vicarium.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@Entity
public class Vote {
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "referendum_id", nullable = false)
	private Referendum referendum;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "politic_id", nullable = false)
	private Politic politic;
	@Enumerated(EnumType.ORDINAL)
	private ExpressionCategory expression;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ")
	private Date date;
	
	public Vote() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Referendum getReferendum() {
		return referendum;
	}

	public void setReferendum(Referendum referendum) {
		this.referendum = referendum;
	}

	public Politic getPolitic() {
		return politic;
	}

	public void setPolitic(Politic politic) {
		this.politic = politic;
	}

	public ExpressionCategory getExpression() {
		return expression;
	}

	public void setExpression(ExpressionCategory expression) {
		this.expression = expression;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
