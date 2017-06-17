package com.getfos.vicarium.model;

import java.util.ArrayList;
import java.util.List;

public enum ExpressionCategory {
	
	FAVOREVOLE("Favorevole"), CONTRARIO("Contrario"), ASTENUTO("Astenuto"), ASSENTE("Assente");
	
	private String description;
	
	ExpressionCategory(String description){
		description = this.description;
	}

	public String getDescription() {
		return description;
	}
	
	public static List<ExpressionCategory> getAll(){
		List<ExpressionCategory> list = new ArrayList<>();
		list.add(ExpressionCategory.ASTENUTO);
		list.add(ExpressionCategory.FAVOREVOLE);
		list.add(ExpressionCategory.CONTRARIO);
		return list;		
	}

}
