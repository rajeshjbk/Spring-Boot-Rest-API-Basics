package com.raj.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

//@Component
public class FindByIdTestRunner implements CommandLineRunner {

	@Autowired
	private RestTemplate template;
	
	@Override
	public void run(String... args) throws Exception {
		
		//url
		String url = "http://localhost:9090/BootRestProj10-APIDevelopment-MiniProject-Swagger/traveller-api/findOne/{id}";

		//invoke the end point
		ResponseEntity<String> resp = template.getForEntity(url, String.class, 1007);
		
		//get the result
		String respBody = resp.getBody();
		int status = resp.getStatusCode().value();
		HttpHeaders headers = resp.getHeaders();
		
		System.out.println("Result is: "+respBody);
		System.out.println("Response Status Code: "+status);
		System.out.println("Response Headers: "+headers);
		
		System.exit(0);
	}

}
