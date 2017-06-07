package com.gettfos.vicarium.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@Entity
public class Vote {
	@Id
	@GeneratedValue
	private Integer id;
	private Integer userId;
	private Integer referendumId;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getReferendumId() {
		return referendumId;
	}

	public void setReferendumId(Integer referendumId) {
		this.referendumId = referendumId;
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

	@Override
	public String toString() {
		return "Vote [id=" + id + ", userId=" + userId + ", referendumId=" + referendumId + ", expression=" + expression
				+ ", date=" + date + "]";
	}
	
}
