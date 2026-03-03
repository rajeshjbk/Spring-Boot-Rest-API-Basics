package com.raj.runners;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.raj.vo.TravellerVO;

import tools.jackson.databind.ObjectMapper;

//@Component
public class UpdateObjectTestRunner1 implements CommandLineRunner {

	@Autowired
	private RestTemplate template;

	@Override
	public void run(String... args) throws Exception {

		//url
		String url = "http://localhost:9090/BootRestProj10-APIDevelopment-MiniProject-Swagger/traveller-api/update";

		//prepare request header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

	    //convert java object as json content using jackson api
		TravellerVO vo = new TravellerVO(1012,"Radhika", "Bokaro1", "Bokaro1", "Delhi1", 60000.0);
		ObjectMapper mapper = new ObjectMapper();
		String body = mapper.writeValueAsString(vo);
				
		//prepare HttpEntity object representing headers and body
		HttpEntity<String> entity = new HttpEntity<String>(body,headers);

		//invoke the end point
		ResponseEntity<String> resp = template.exchange(url, HttpMethod.PUT,entity, String.class);

		System.out.println("Result : "+resp.getBody());
		System.out.println("Status Code: "+resp.getStatusCode());
		System.out.println("Headers: "+resp.getHeaders());

		//exit the flow
		System.exit(0);
	}

}
