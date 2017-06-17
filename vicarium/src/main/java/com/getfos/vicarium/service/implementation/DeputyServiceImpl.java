package com.getfos.vicarium.service.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import com.getfos.vicarium.builder.DeputyBuilder;
import com.getfos.vicarium.dao.DeputyDAO;
import com.getfos.vicarium.dao.VoteDAO;
import com.getfos.vicarium.model.Deputy;
import com.getfos.vicarium.model.ExpressionCategory;
import com.getfos.vicarium.model.User;
import com.getfos.vicarium.model.Vote;
import com.getfos.vicarium.model.external.PoliticExternal;
import com.getfos.vicarium.service.DeputyService;

@SuppressWarnings("deprecation")
@Service("deputyService")
@Transactional
public class DeputyServiceImpl implements DeputyService{
	
	@Autowired
	private DeputyDAO deputyDAO;
	
	@Autowired
	private VoteDAO voteDAO;

	public Deputy addDeputy(Deputy deputy) {
		Deputy deputyDb = deputyDAO.readByIdentifier(deputy.getIdentifier());
		if(deputyDb != null){
			return deputyDb;
		}
		return deputyDAO.createDeputy(deputy);
	}

	public Deputy updateDeputy(Deputy deputy) {
		return deputyDAO.updateDeputy(deputy);
	}

	public List<Deputy> getAll() {
		return deputyDAO.readAll();
	}

	public Map<String, List<Object>> bestMatch(User user) {
		Map<String, List<Object>> results = new HashMap<>();
		Map<Deputy, Integer> deputyScores = new HashMap<Deputy, Integer>();
		Map<String, Integer> politicScores = new HashMap<String, Integer>();
		List<String> politicGroups = new ArrayList<>();
		List<Integer> deputyIdsAgree = new ArrayList<>();
		List<Integer> deputyIdsNotAgree = new ArrayList<>();
		List<ExpressionCategory> expressionAgree = new ArrayList<>();
		List<ExpressionCategory> expressionNotAgree = new ArrayList<>();
		List<Vote> userVotes = voteDAO.readPoliticVotes(user);
		List<Deputy> deputies = deputyDAO.readAll();
		for (Vote vote : userVotes) {
			for (ExpressionCategory expressionCategory : ExpressionCategory.getAll()) {
				if(!vote.getExpression().equals(expressionCategory) && !expressionCategory.equals(ExpressionCategory.ASSENTE)){
					expressionNotAgree.add(expressionCategory);
				}
			}
			expressionAgree.add(vote.getExpression());
			deputyIdsAgree.addAll(voteDAO.readDeputyIdsAgree(deputies, expressionAgree, vote.getReferendum()));
			deputyIdsNotAgree.addAll(voteDAO.readDeputyIdsAgree(deputies, expressionNotAgree, vote.getReferendum()));
			expressionAgree.clear();
			expressionNotAgree.clear();
		}
		int count = 0;
		for (Deputy deputy : deputies) {
			count = 0;
			for (Integer deputyIdAgree : deputyIdsAgree) {
				if(deputy.getId() == deputyIdAgree){
					count++;
				}
			}
			for (Integer deputyIdNotAgree : deputyIdsNotAgree) {
				if(deputy.getId() == deputyIdNotAgree){
					count--;
				}
			}
			if(politicScores.get(deputy.getPoliticalGroup()) != null){
				Integer value = politicScores.get(deputy.getPoliticalGroup());
				politicScores.put(deputy.getPoliticalGroup(), value += count);
			}else{
				politicScores.put(deputy.getPoliticalGroup(), count);
			}
			deputyScores.put(deputy, count);
		}
		politicScores = sortByValue(politicScores);
		deputyScores = sortByValue(deputyScores);
		deputies.clear();
		for (Map.Entry<Deputy, Integer> dep : deputyScores.entrySet()){
			deputies.add(dep.getKey());
		}
		for (Map.Entry<String, Integer> group : politicScores.entrySet()){
			politicGroups.add(group.getKey());
		}
		results.put("deputies", new ArrayList<Object>(deputies));
		results.put("politicalgroup", new ArrayList<Object>(politicGroups));
		return results;
	}
	
	private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue( Map<K, V> map ){
	    List<Map.Entry<K,V>> list = new LinkedList<>(map.entrySet());
	    Collections.sort(list, new Comparator<Map.Entry<K, V>>(){
	        @Override
	        public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
	        {
	            return ( o2.getValue()).compareTo( o1.getValue() );
	        }
	    } );
	
	    Map<K, V> result = new LinkedHashMap<>();
	    for (Map.Entry<K, V> entry : list)
	    {
	        result.put( entry.getKey(), entry.getValue() );
	    }
	    return result;
	}
	
	public List<Deputy> getDeputyFromCamera() throws IOException{
		List<Deputy> deputies = new ArrayList<>(); 
		String url = "http://dati.camera.it/sparql?query=%23%23%23%23+tutti+i+deputati+in+carica+nella+XVII+Legislatura+con+info%0D%0A%0D%0A%0D%0ASELECT+DISTINCT+%3Fidentifier+%3Fsurname+%3Fname+%3Fqualification%0D%0A%3Fbirthday+%3Fbirthplace+%3Fsex%0D%0A%3FpoliticalList+%3FpoliticalGroup+%3FlastUpdateDate%0D%0AWHERE+%7B%0D%0A%3Fidentifier+ocd%3Arif_mandatoCamera+%3Fmandato%3B+a+foaf%3APerson.%0D%0A%0D%0A%23%23+deputato%0D%0A%3Fd+a+ocd%3Adeputato%3B+ocd%3Aaderisce+%3Faderisce%3B%0D%0Aocd%3Arif_leg+%3Chttp%3A%2F%2Fdati.camera.it%2Focd%2Flegislatura.rdf%2Frepubblica_17%3E%3B%0D%0Aocd%3Arif_mandatoCamera+%3Fmandato.%0D%0AOPTIONAL%7B%3Fd+dc%3Adescription+%3Fqualification%7D%0D%0A%0D%0A%23%23anagrafica%0D%0A%3Fd+foaf%3Asurname+%3Fsurname%3B+foaf%3Agender+%3Fsex%3Bfoaf%3AfirstName+%3Fname.%0D%0AOPTIONAL%7B%0D%0A%3Fidentifier+%3Chttp%3A%2F%2Fpurl.org%2Fvocab%2Fbio%2F0.1%2FBirth%3E+%3Fnascita.%0D%0A%3Fnascita+%3Chttp%3A%2F%2Fpurl.org%2Fvocab%2Fbio%2F0.1%2Fdate%3E+%3Fbirthday%3B%0D%0Ardfs%3Alabel+%3Fnato%3B+ocd%3Arif_luogo+%3FluogoNascitaUri.%0D%0A%3FluogoNascitaUri+dc%3Atitle+%3Fbirthplace.%0D%0A%7D%0D%0A%0D%0A%23%23aggiornamento+del+sistema%0D%0AOPTIONAL%7B%3Fd+%3Chttp%3A%2F%2Flod.xdams.org%2Fontologies%2Fods%2Fmodified%3E+%3FlastUpdateDate.%7D%0D%0A%0D%0A%23%23+mandato%0D%0A%3Fmandato+ocd%3Arif_elezione+%3Felezione.+%0D%0AMINUS%7B%3Fmandato+ocd%3AendDate+%3FfineMandato.%7D%0D%0A+%0D%0A%23%23+totale+mandati%0D%0A%3Fidentifier+ocd%3Arif_mandatoCamera+%3FmadatoCamera.%0D%0A%0D%0A%23%23+elezione%0D%0AOPTIONAL+%7B%0D%0A%3Felezione+dc%3Acoverage+%3Fcollegio.%0D%0A%3Felezione+ocd%3Alista+%3FpoliticalList%0D%0A%7D%0D%0A%0D%0A%23%23+adesione+a+gruppo%0D%0A%3Faderisce+ocd%3Arif_gruppoParlamentare+%3Fgruppo.%0D%0AOPTIONAL+%7B%3Faderisce+rdfs%3Alabel+%3FpoliticalGroup%7D%0D%0AMINUS%7B%3Faderisce+ocd%3AendDate+%3FfineAdesione%7D%0D%0A+%0D%0A%7D%0D%0A%0D%0A%0D%0A%09%09&debug=on&default-graph-uri=&format=application%2Fsparql-results%2Bjson";
		@SuppressWarnings("resource")
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);
		BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
		String lineSingle = "";
		String line = "";
		while ((lineSingle = rd.readLine()) != null) {
		  line += lineSingle;
		}
		//System.out.println(line);
		line = "[" + line.substring(238, line.length()-5) +"]";
		//System.out.println(line);
		JSONParser parser = new JSONParser();
		try{
			Object obj = parser.parse(line);
		    JSONArray array = (JSONArray)obj;
		    ObjectMapper mapper = new ObjectMapper();
		    for (int c = 0; c < array.size(); c++) {
		    	PoliticExternal deputy = mapper.readValue(array.get(c).toString(), PoliticExternal.class);
		    	deputies.add(DeputyBuilder.buildDeputy(deputy));
			}
		}catch(ParseException pe){
			System.out.println("position: " + pe.getPosition());
		    System.out.println(pe);
		}
		return deputies;
    }

}
