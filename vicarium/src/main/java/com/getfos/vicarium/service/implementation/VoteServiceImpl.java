package com.getfos.vicarium.service.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.getfos.vicarium.builder.VoteBuilder;
import com.getfos.vicarium.dao.DeputyDAO;
import com.getfos.vicarium.dao.VoteDAO;
import com.getfos.vicarium.model.Deputy;
import com.getfos.vicarium.model.Politic;
import com.getfos.vicarium.model.Referendum;
import com.getfos.vicarium.model.User;
import com.getfos.vicarium.model.Vote;
import com.getfos.vicarium.model.external.VoteExternal;
import com.getfos.vicarium.service.VoteService;

@SuppressWarnings("deprecation")
@Service("voteService")
@Transactional
public class VoteServiceImpl implements VoteService{
	
	@Autowired
	private VoteDAO voteDAO;
	
	@Autowired
	private DeputyDAO deputyDAO;
	
	private final static String SPLIT_IDENTIFIER = ".rdf/d";


	public Vote addVote(Vote vote) {
		return voteDAO.createVote(vote);
	}

	public List<Vote> getAllVote() {
		return voteDAO.readAll();
	}

	public List<Vote> getReferendumVotes(Referendum referendum) {
		return voteDAO.readReferendumVotes(referendum);
	}

	public List<Vote> getPoliticVotes(Politic politic) {
		return voteDAO.readPoliticVotes(politic);
	}

	public List<Vote> getUserReferendumVotes(Referendum referendum, List<User> users) {
		List<Vote> userVotes = new ArrayList<Vote>();
		for (User user : users) {
			for (Vote vote : voteDAO.readPoliticVotes(user)) {
				if(vote.getReferendum().getReferendumId().equals(referendum.getReferendumId())){
					userVotes.add(vote);
				}
			}
		}
		return userVotes;
	}
	
	public List<Vote> getDeputyReferendumVotes(Referendum referendum, List<Deputy> deputies) {
		List<Vote> votesDeputies = new ArrayList<Vote>();
		for (Deputy deputy : deputies) {
			for (Vote vote : voteDAO.readPoliticVotes(deputy)) {
				if(vote.getReferendum().getReferendumId().equals(referendum.getReferendumId())){
					votesDeputies.add(vote);
				}
			}
		}
		return votesDeputies;
	}
	
	public List<Vote> addVoteToReferendumFromCamera(Referendum referendum) throws IOException{
		List<Vote> votes = new ArrayList<>(); 
		String url = "http://dati.camera.it/sparql?query=select+distinct+%3Fidentifier+%3Fexpression+%3FdeputyId+where+%7B%0D%0A%0D%0A%3Fvotazione+a+ocd%3Avotazione%3B%0D%0A+++dc%3Aidentifier+%27" + referendum.getIdentifier() +"%27%3B%0D%0A+++dc%3Aidentifier+%3Fidentifier.%0D%0A%0D%0A%3Fvoto+a+ocd%3Avoto%3B%0D%0A+++ocd%3Arif_votazione+%3Fvotazione%3B%0D%0A+++dc%3Atype+%3Fexpression%3B%0D%0A+++ocd%3Arif_deputato+%3FdeputyId.%0D%0A%0D%0A%7D&debug=on&default-graph-uri=&format=application%2Fsparql-results%2Bjson";
		@SuppressWarnings({"resource"})
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);
		BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
		String line = "";
		while ((rd.readLine()) != null) {
		  line += rd.readLine();
		}
		System.out.println(line);
		line = "[{" + line.substring(80, line.length()-5) +"]";
		System.out.println(line);
		JSONParser parser = new JSONParser();
		try{
			Object obj = parser.parse(line);
		    JSONArray array = (JSONArray)obj;
		    ObjectMapper mapper = new ObjectMapper();
		    for (int c = 0; c < array.size(); c++) {
		    	VoteExternal voteExternal = mapper.readValue(array.get(c).toString(), VoteExternal.class);
		    	String deputyId = voteExternal.getDeputyId().getValue().split(SPLIT_IDENTIFIER)[1];
		    	deputyId = deputyId.substring(0, deputyId.length()-3);
		    	Deputy deputy = deputyDAO.readByIdentifier(deputyId);
		    	if(deputy != null){
			    	System.out.println(deputy);
			    	Vote vote = VoteBuilder.buildVote(voteExternal, referendum, deputy);
			    	voteDAO.createVote(vote);
			    	votes.add(vote);		
		    	}else{
		    		System.out.println("Deputato non trovato. Identifier deputato: " + deputyId);
		    	}
		    }
		}catch(ParseException pe){
			System.out.println("position: " + pe.getPosition());
		    System.out.println(pe);
		}
		return votes;
    }

	
	
	
}
