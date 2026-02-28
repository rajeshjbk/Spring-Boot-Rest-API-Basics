package com.raj.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.raj.service.ITravellerMgmtService;
import com.raj.vo.TravellerVO;

@RestController
@RequestMapping("traveller-api")
public class TravellerOperationsRestController {

	@Autowired
	private ITravellerMgmtService travellerService;

	/*@PostMapping("/save")
	public ResponseEntity<String> registerTraveller(@RequestBody TravellerVO vo){

		try {
			//use service 
			String msg = travellerService.registerTraveller(vo);

			//return ResponseEntity object
			return new ResponseEntity<String>(msg, HttpStatus.OK);

		}catch (Exception e) {

			return new ResponseEntity<String>("Problem:: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/

	@PostMapping("/save")
	public ResponseEntity<String> registerTraveller(@RequestBody TravellerVO vo){

		//use service 
		String msg = travellerService.registerTraveller(vo);

		//return ResponseEntity object
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
	}

	@PostMapping("saveAll")
	public ResponseEntity<String> registerMultipleTravellers(@RequestBody List<TravellerVO> listVO){

		//use service
		String msg = travellerService.registerTravellers(listVO);

		//return new ResponseEntity object
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<TravellerVO>> getAllTravellers(){

		//use service
		List<TravellerVO> listVO = travellerService.showAllTravellers();

		if(listVO.isEmpty()) {

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(listVO,HttpStatus.OK);
	}

	@GetMapping("/find/{ids}")
	public ResponseEntity<List<TravellerVO>> showAllTravellersByIds(@PathVariable List<Integer> ids){

		//use service
		List<TravellerVO> listVO = travellerService.showAllTravellersByIds(ids);

		if(listVO.isEmpty()) {

			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(listVO);
	}

	@GetMapping("/find/{start}/{end}")
	public ResponseEntity<List<TravellerVO>> getTravellersByBudgetRange(@PathVariable Double start, @PathVariable Double end){

		//use service
		List<TravellerVO> listVO = travellerService.findTravellersByBudgetRange(start, end);

		if(listVO.isEmpty()) {

			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(listVO);
	}
	
	@GetMapping("/findOne/{id}")
	public ResponseEntity<TravellerVO> getTravellerById(@PathVariable Integer id) {
		
		//use service
		TravellerVO vo = travellerService.getTravellerById(id);
		
		return ResponseEntity.ok(vo);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateTraveller(@RequestBody TravellerVO vo){
		
		//use service
		String msg = travellerService.updateTraveller(vo);
		
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@PatchMapping("/update/{tid}/{discount}")
	public ResponseEntity<String> updateTravellerBudget(@PathVariable Integer tid, @PathVariable Double discount){
		
		//use repo
		String msg = travellerService.updateTravellerBudget(tid, discount);
		
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{startRange}/{endRange}")
	public ResponseEntity<String> removeTravellersByBudgetRange(@PathVariable Double startRange, @PathVariable Double endRange){
		
		//use service
		String msg = travellerService.removeTravellersByBudgetRange(startRange, endRange);
		
		return ResponseEntity.ok(msg);
	}
	
	@DeleteMapping("delete/{tid}")
	public ResponseEntity<String> removeTravellersById(@PathVariable Integer tid){
		
		//use service
		String msg = travellerService.removeTravellerById(tid);
		
		return ResponseEntity.ok(msg);
	}

}
