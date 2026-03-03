package com.raj.runners;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.raj.vo.TravellerVO;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

//@Component
public class FindAllTestRunner1 implements CommandLineRunner {

	@Autowired
	private RestTemplate template;
	
	@Override
	public void run(String... args) throws Exception {
		
		//base url
		String url = "http://localhost:9090/BootRestProj10-APIDevelopment-MiniProject-Swagger/traveller-api/all";
	    
		//invoke the end point
		ResponseEntity<String> resp = template.exchange(url, HttpMethod.GET,null, String.class);
		
		System.out.println("Result : "+resp.getBody());
		System.out.println("Status Code: "+resp.getStatusCode());
		System.out.println("Headers: "+resp.getHeaders());
		
		System.out.println("=====================================================");
		
		//convert String JSON content to List<TravellerVO> object
		ObjectMapper mapper = new ObjectMapper();
		
		List<TravellerVO> listVO = mapper.readValue(resp.getBody(), new TypeReference<List<TravellerVO>>(){});
		
		listVO.forEach(System.out::println);
		
		//exit the flow
		System.exit(0);
	}

}
