package com.raj.runners;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.raj.vo.TravellerVO;

import tools.jackson.databind.ObjectMapper;

@Component
public class DeleteByBudgetRangeTestRunner1 implements CommandLineRunner {

	@Autowired
	private RestTemplate template;

	@Override
	public void run(String... args) throws Exception {

		//url
		String url = "http://localhost:9090/BootRestProj10-APIDevelopment-MiniProject-Swagger/traveller-api/delete/{startRange}/{endRange}";
        
		//invoke the end point
		ResponseEntity<String> resp = template.exchange(url, HttpMethod.DELETE,null, String.class,40000.0,60000.0);

		System.out.println("Result : "+resp.getBody());
		System.out.println("Status Code: "+resp.getStatusCode());
		System.out.println("Headers: "+resp.getHeaders());

		//exit the flow
		System.exit(0);
	}

}
