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
import com.getfos.vicarium.builder.ReferendumBuilder;
import com.getfos.vicarium.dao.ReferendumDAO;
import com.getfos.vicarium.model.Referendum;
import com.getfos.vicarium.model.external.ReferendumExternal;
import com.getfos.vicarium.service.ReferendumService;

@SuppressWarnings("deprecation")
@Service("referendumService")
@Transactional
public class ReferendumServiceImpl implements ReferendumService{
	
	@Autowired
	private ReferendumDAO referendumDAO;

	public Referendum addReferendum(Referendum referendum) {
		Referendum referendumDb = referendumDAO.readByDate(referendum.getDate());
		if(referendumDb != null){
			return referendumDb;
		}
		return referendumDAO.createReferendum(referendum);
	}

	public List<Referendum> getAllReferendum() {
		return referendumDAO.readAll();
	}

	public Referendum getReferendumById(Integer id) {
		return referendumDAO.readById(id);
	}

	public List<Referendum> getReferendumFromCamera() throws IOException{
		List<Referendum> referendums = new ArrayList<>(); 
		String url = "http://dati.camera.it/sparql?query=SELECT+distinct+*+WHERE+%7B%0D%0A%3Fidentifier+a+ocd%3Avotazione%3B+%0D%0Adc%3Adate+%3Fdate%3B+%0D%0Adc%3Atitle+%3Fdenomination%3B+%0D%0Adc%3Adescription+%3Fdescription%3B%0D%0Aocd%3Arif_leg+%3Chttp%3A%2F%2Fdati.camera.it%2Focd%2Flegislatura.rdf%2Frepubblica_17%3E%7D+%0D%0AORDER+BY+DESC%28%3Fdate%29+LIMIT+2%0D%0A%09%09&debug=on&default-graph-uri=&format=application%2Fsparql-results%2Bjson";
		@SuppressWarnings({ "resource"})
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);
		BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
		String line = "";
		while ((rd.readLine()) != null) {
		  line += rd.readLine();
		}
		//System.out.println(line);
		line = "[{" + line.substring(93, line.length()-5) +"]";
		//System.out.println(line);
		JSONParser parser = new JSONParser();
		try{
			Object obj = parser.parse(line);
		    JSONArray array = (JSONArray)obj;
		    ObjectMapper mapper = new ObjectMapper();
		    for (int c = 0; c < array.size(); c++) {
		    	ReferendumExternal referendum = mapper.readValue(array.get(c).toString(), ReferendumExternal.class);
		    	referendums.add(ReferendumBuilder.buildReferendum(referendum));
			}
		}catch(ParseException pe){
			System.out.println("position: " + pe.getPosition());
		    System.out.println(pe);
		}
		return referendums;
    }

}
