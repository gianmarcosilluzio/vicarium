package com.getfos.vicarium.builder;

import com.getfos.vicarium.model.ExpressionCategory;
import com.getfos.vicarium.model.Politic;
import com.getfos.vicarium.model.Referendum;
import com.getfos.vicarium.model.Vote;
import com.getfos.vicarium.model.external.VoteExternal;

public class VoteBuilder {
	
	public static Vote buildVote(VoteExternal external, Referendum referendum, Politic politic){
		Vote vote = new Vote();
		vote.setDate(referendum.getDate());
		vote.setPolitic(politic);
		vote.setReferendum(referendum);
		String expression = external.getExpression().getValue();
		ExpressionCategory expressionCategory = null;
		switch(expression){
			case "Non ha votato": expressionCategory = ExpressionCategory.ASSENTE; break;
			case "Favorevole": expressionCategory = ExpressionCategory.FAVOREVOLE; break;
			case "Contrario": expressionCategory = ExpressionCategory.CONTRARIO; break;
			case "Astensione": expressionCategory = ExpressionCategory.ASTENUTO; break;
		}
		vote.setExpression(expressionCategory);
		return vote;
	}
}
