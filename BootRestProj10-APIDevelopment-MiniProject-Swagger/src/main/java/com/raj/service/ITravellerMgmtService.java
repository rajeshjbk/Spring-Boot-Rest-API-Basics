package com.raj.service;

import java.util.List;

import com.raj.vo.TravellerVO;

public interface ITravellerMgmtService {

	public String registerTraveller(TravellerVO vo);
	
	public String registerTravellers(List<TravellerVO> listVO);
	
	public List<TravellerVO> showAllTravellers();
	
	public List<TravellerVO> showAllTravellersByIds(List<Integer> ids);
	
	public List<TravellerVO> findTravellersByBudgetRange(Double startRange, Double endRange);
	
	public TravellerVO getTravellerById(Integer id);
	
	public String updateTraveller(TravellerVO vo);
	
	public String updateTravellerBudget(Integer id, Double discountPercentage);
	
	public String removeTravellersByBudgetRange(Double startBudget, Double endBudget);
	
	public String removeTravellerById(Integer tid);
}
