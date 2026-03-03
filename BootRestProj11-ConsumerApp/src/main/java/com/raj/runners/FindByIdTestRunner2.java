package com.raj.runners;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.raj.vo.TravellerVO;

import tools.jackson.databind.ObjectMapper;

//@Component
public class FindByIdTestRunner2 implements CommandLineRunner {

	@Autowired
	private RestTemplate template;

	@Override
	public void run(String... args) throws Exception {

		//url
		String url = "http://localhost:9090/BootRestProj10-APIDevelopment-MiniProject-Swagger/traveller-api/findOne/{id}";

		//invoke the end point
		ResponseEntity<String> resp = template.exchange(url, HttpMethod.GET, null, String.class, 1007);
		//get the response details
		String respBody = resp.getBody();
		int status = resp.getStatusCode().value();
		HttpHeaders headers = resp.getHeaders();

		System.out.println("Result is: "+respBody);
		System.out.println("Response Status Code: "+status);
		System.out.println("Response Headers: "+headers);

		System.out.println("==================================================");
		
		//convert String JSON content to Java class object
		ObjectMapper mapper = new ObjectMapper();
		TravellerVO vo = mapper.readValue(respBody, TravellerVO.class);
		System.out.println("VO class object: "+vo);
		
		System.exit(0);
	}

}
