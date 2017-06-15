package com.getfos.vicarium.model.external;

public class DocumentExternal {
	private ObjectJson identifier;
	private ObjectJson pdf;
	
	public DocumentExternal() {
	}

	public ObjectJson getIdentifier() {
		return identifier;
	}

	public void setIdentifier(ObjectJson identifier) {
		this.identifier = identifier;
	}

	public ObjectJson getPdf() {
		return pdf;
	}

	public void setPdf(ObjectJson pdf) {
		this.pdf = pdf;
	}

	@Override
	public String toString() {
		return "DocumentExternal [identifier=" + identifier + ", pdf=" + pdf + "]";
	}
	
}
