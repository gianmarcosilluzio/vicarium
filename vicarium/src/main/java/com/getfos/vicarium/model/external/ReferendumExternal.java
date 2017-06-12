package com.getfos.vicarium.model.external;

public class ReferendumExternal {
	private ObjectJson identifier;
	private ObjectJson denomination;
	private ObjectJson description;
	private ObjectJson pathDocument;
	private ObjectJson date;
	
	public ReferendumExternal() {
	}

	public ObjectJson getIdentifier() {
		return identifier;
	}

	public void setIdentifier(ObjectJson identifier) {
		this.identifier = identifier;
	}

	public ObjectJson getDenomination() {
		return denomination;
	}

	public void setDenomination(ObjectJson denomination) {
		this.denomination = denomination;
	}

	public ObjectJson getDescription() {
		return description;
	}

	public void setDescription(ObjectJson description) {
		this.description = description;
	}

	public ObjectJson getPathDocument() {
		return pathDocument;
	}

	public void setPathDocument(ObjectJson pathDocument) {
		this.pathDocument = pathDocument;
	}

	public ObjectJson getDate() {
		return date;
	}

	public void setDate(ObjectJson date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "ReferendumExternal [identifier=" + identifier + ", denomination=" + denomination + ", description="
				+ description + ", pathDocument=" + pathDocument + ", date=" + date + "]";
	}

	
}
