package com.getfos.vicarium.model;

public enum ExpressionCategory {
	
	FAVOREVOLE("Favorevole"), CONTRARIO("Contrario"), ASTENUTO("Astenuto"), ASSENTE("Assente");
	
	private String description;
	
	ExpressionCategory(String description){
		description = this.description;
	}

	public String getDescription() {
		return description;
	}

}
