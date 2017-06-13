package com.getfos.vicarium.model.external;

public class VoteExternal {
	private ObjectJson identifier;
	private ObjectJson name;
	private ObjectJson surname;
	private ObjectJson expression;
	private ObjectJson deputyId;
	
	public VoteExternal() {
	}

	public ObjectJson getName() {
		return name;
	}

	public void setName(ObjectJson name) {
		this.name = name;
	}

	public ObjectJson getSurname() {
		return surname;
	}

	public void setSurname(ObjectJson surname) {
		this.surname = surname;
	}

	public ObjectJson getExpression() {
		return expression;
	}

	public void setExpression(ObjectJson expression) {
		this.expression = expression;
	}

	public ObjectJson getDeputyId() {
		return deputyId;
	}

	public void setDeputyId(ObjectJson deputyId) {
		this.deputyId = deputyId;
	}

	public ObjectJson getIdentifier() {
		return identifier;
	}

	public void setIdentifier(ObjectJson identifier) {
		this.identifier = identifier;
	}

	@Override
	public String toString() {
		return "VoteExternal [identifier=" + identifier + ", name=" + name + ", surname=" + surname + ", expression="
				+ expression + ", deputyId=" + deputyId + "]";
	}
	
}
