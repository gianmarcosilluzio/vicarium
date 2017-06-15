package com.getfos.vicarium.service.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
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
import com.getfos.vicarium.builder.DocumentBuilder;
import com.getfos.vicarium.builder.ReferendumBuilder;
import com.getfos.vicarium.dao.ReferendumDAO;
import com.getfos.vicarium.model.Referendum;
import com.getfos.vicarium.model.external.DocumentExternal;
import com.getfos.vicarium.model.external.ReferendumExternal;
import com.getfos.vicarium.service.ReferendumService;
import com.getfos.vicarium.util.DateUtil;

@SuppressWarnings("deprecation")
@Service("referendumService")
@Transactional
public class ReferendumServiceImpl implements ReferendumService{
	
	@Autowired
	private ReferendumDAO referendumDAO;

	public Referendum addReferendum(Referendum referendum) {
		Referendum referendumDb = referendumDAO.readByIdentifier(referendum.getIdentifier());
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

	public List<Referendum> getReferendumFromCamera(Date date) throws IOException{
		List<Referendum> referendums = new ArrayList<>(); 
		String url = "http://dati.camera.it/sparql?query=SELECT+distinct+%3Fdate+%3Fdenomination+%3Fdescription+%3Fidentifier+WHERE+%7B%0D%0A%3Fvotazione+a+ocd%3Avotazione%3B+%0D%0Adc%3Adate+%27" + DateUtil.convertDateToString(date, "yyyyMMdd") + "%27%3B%0D%0Adc%3Adate+%3Fdate%3B+%0D%0Adc%3Aidentifier+%3Fidentifier%3B+%0D%0Adc%3Atitle+%3Fdenomination%3B+%0D%0Adc%3Adescription+%3Fdescription%7D+%0D%0AORDER+BY+DESC%28%3Fdate%29&debug=on&default-graph-uri=&format=application%2Fsparql-results%2Bjson";
		@SuppressWarnings({ "resource"})
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);
		BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
		String lineSingle = "";
		String line = "";
		while ((lineSingle = rd.readLine()) != null) {
		  line += lineSingle;
		}
		System.out.println(line);
		try{
			line = "[" + line.substring(155, line.length()-5) +"]";
		}catch(StringIndexOutOfBoundsException ex){
			System.out.println("Nessun referendum trovato");
			return referendums;
		}
		System.out.println(line);
		JSONParser parser = new JSONParser();
		try{
			Object obj = parser.parse(line);
		    JSONArray array = (JSONArray)obj;
		    ObjectMapper mapper = new ObjectMapper();
		    for (int c = 0; c < array.size(); c++) {
		    	ReferendumExternal referendumExternal = mapper.readValue(array.get(c).toString(), ReferendumExternal.class);
		    	Referendum referendum = ReferendumBuilder.buildReferendum(referendumExternal);
		    	referendum.setPathDocument(getPathDocument(referendum));
		    	referendums.add(referendum);
			}
		}catch(ParseException pe){
			System.out.println("position: " + pe.getPosition());
		    System.out.println(pe);
		}
		return referendums;
    }

	private String getPathDocument(Referendum referendum) throws IOException {
		String document = "";
		String url = "http://dati.camera.it/sparql?query=select+distinct+%3Fpdf+%3Fidentifier%7B+%0D%0A++%09++%3Fatto+a+ocd%3Aatto%3B+%0D%0A++++++++++dc%3Arelation+%3Fpdf.%0D%0A++%0D%0A++%09%09++%3FstatoIter++dc%3Atitle+%3Ffase.%0D%0A++%0D%0A++++++++%09++%3Fvotazione+a+ocd%3Avotazione%3B+ocd%3Arif_attoCamera+%3Fatto%3B%09%0D%0A++++++++++++++dc%3Aidentifier+%27" + referendum.getIdentifier() + "%27%3B%0D%0A++++++++++++++dc%3Aidentifier+%3Fidentifier.%0D%0A%0D%0A%09++%7D&debug=on&default-graph-uri=&format=application%2Fsparql-results%2Bjson";
		@SuppressWarnings({ "resource"})
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);
		BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
		String lineSingle = "";
		String line = "";
		while ((lineSingle = rd.readLine()) != null) {
		  line += lineSingle;
		}
		System.out.println(line);
		try{
			line = "[" + line.substring(122, line.length()-5) +"]";
		}catch(StringIndexOutOfBoundsException ex){
			System.out.println("Nessun documento trovato");
			return "";
		}
		System.out.println(line);
		JSONParser parser = new JSONParser();
		try{
			Object obj = parser.parse(line);
		    JSONArray array = (JSONArray)obj;
		    ObjectMapper mapper = new ObjectMapper();
		    DocumentExternal documentExternal = mapper.readValue(array.get(array.size()-1).toString(), DocumentExternal.class);
		    document = DocumentBuilder.buildDocument(documentExternal);
		}catch(ParseException pe){
			System.out.println("position: " + pe.getPosition());
		    System.out.println(pe);
		}
		return document;
	}
	
	/*
	 * 
	GET DOCUMENTI
	"http://dati.camera.it/sparql?query=select+distinct+%3Fpdf+%3Fidentifier%7B+%0D%0A++%09++%3Fatto+a+ocd%3Aatto%3B+%0D%0A++++++++++dc%3Arelation+%3Fpdf.%0D%0A++%0D%0A++%09%09++%3FstatoIter++dc%3Atitle+%3Ffase.%0D%0A++%0D%0A++++++++%09++%3Fvotazione+a+ocd%3Avotazione%3B+ocd%3Arif_attoCamera+%3Fatto%3B%09%0D%0A++++++++++++++dc%3Aidentifier+%27" + referendum.getIdentifier() + "%27%3B%0D%0A++++++++++++++dc%3Aidentifier+%3Fidentifier.%0D%0A%0D%0A%09++%7D&debug=on&default-graph-uri=&format=application%2Fsparql-results%2Bjson";
	select distinct ?pdf ?identifier{ 
  	  ?atto a ocd:atto; 
          dc:relation ?pdf.
  
  		  ?statoIter  dc:title ?fase.
  
        	  ?votazione a ocd:votazione; ocd:rif_attoCamera ?atto;	
              dc:identifier '794023';
              dc:identifier ?identifier.

	  }
	
	 * 
	 */

	/*
	 * 
	 * RETURN ALL REFERENDUM
	 "http://dati.camera.it/sparql?query=SELECT+distinct+%3Fdate+%3Fdenomination+%3Fdescription+%3Fidentifier+WHERE+%7B%0D%0A%3Fvotazione+a+ocd%3Avotazione%3B+%0D%0Adc%3Adate+%27" + date + "%27%3B%0D%0Adc%3Adate+%3Fdate%3B+%0D%0Adc%3Aidentifier+%3Fidentifier%3B+%0D%0Adc%3Atitle+%3Fdenomination%3B+%0D%0Adc%3Adescription+%3Fdescription%7D+%0D%0AORDER+BY+DESC%28%3Fdate%29&debug=on&default-graph-uri=&format=application%2Fsparql-results%2Bjson";
	 * SELECT distinct ?date ?denomination ?description ?identifier WHERE {
		?votazione a ocd:votazione; 
		dc:date '20170511';
		dc:date ?date; 
		dc:identifier ?identifier; 
		dc:title ?denomination; 
		dc:description ?description} 
		ORDER BY DESC(?date)
	 */

}
