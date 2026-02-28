package com.raj.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.entity.TravellerEntity;
import com.raj.exception.TravellersNotFoundException;
import com.raj.repository.ITravellerRepository;
import com.raj.vo.TravellerVO;

@Service
public class TravellerMgmtServiceImpl implements ITravellerMgmtService {

	@Autowired
	private ITravellerRepository travellerRepo;

	@Override
	public String registerTraveller(TravellerVO vo) {

		//convert vo to entity
		TravellerEntity entity = new TravellerEntity();
		BeanUtils.copyProperties(vo, entity);
		entity.setCreatedBy(System.getProperty("user.name"));

		//use repo
		Integer idVal = travellerRepo.save(entity).getTid();

		return "Traveller Obj is saved with id value: "+idVal;
	}

	@Override
	public String registerTravellers(List<TravellerVO> listVO) {

		//convert listVO to listEntity
		/*List<TravellerEntity> listEntity = new ArrayList<TravellerEntity>();
		listVO.forEach(vo ->{

			TravellerEntity entity = new TravellerEntity();
			BeanUtils.copyProperties(vo, entity);
			entity.setCreatedBy(System.getProperty("user.name"));
			listEntity.add(entity);
		});*/

		List<TravellerEntity> listEntity = listVO.stream()
				.map(vo -> {

					TravellerEntity entity = new TravellerEntity();
					BeanUtils.copyProperties(vo, entity);
					entity.setCreatedBy(System.getProperty("user.name"));
					return entity;

				})
				.collect(Collectors.toList());

		//use repo
		List<TravellerEntity> savedList = travellerRepo.saveAll(listEntity);
		//get only id values
		List<Integer> ids = savedList.stream().map(TravellerEntity::getTid).collect(Collectors.toList());

		//return the message
		return ids.size()+" no. of Travellers are saved with id values:: "+ids;
	}

	@Override
	public List<TravellerVO> showAllTravellers() {

		//use repo
		List<TravellerEntity> listEntity = travellerRepo.findAll();

		//convert listEntity to listVO
		List<TravellerVO> listVO = listEntity.stream()
				.map(entity -> {
					TravellerVO vo = new TravellerVO();
					BeanUtils.copyProperties(entity, vo);
					return vo;
				})
				.collect(Collectors.toList());

		return listVO;
	}

	@Override
	public List<TravellerVO> showAllTravellersByIds(List<Integer> ids) {

		//fetch entities from DB
		List<TravellerEntity> listEntity = travellerRepo.findAllById(ids);

		//convert listEntity to listVO
		List<TravellerVO> listVO = listEntity.stream()
				.map(entity -> {
					TravellerVO vo = new TravellerVO();
					BeanUtils.copyProperties(entity, vo);
					return vo;
				})
				.collect(Collectors.toList());

		return listVO;
	}

	@Override
	public List<TravellerVO> findTravellersByBudgetRange(Double startRange, Double endRange) {

		if(startRange > endRange) {

			throw new IllegalArgumentException("Start range can't be greater than end range");
		}
		//use repo
		List<TravellerEntity> listEntity = travellerRepo.fetchTravellersByBudgetRange(startRange, endRange);

		//convert listEntity to listVO
		List<TravellerVO> listVO = new ArrayList<TravellerVO>();

		for (TravellerEntity entity : listEntity) {

			TravellerVO vo = new TravellerVO();
			BeanUtils.copyProperties(entity, vo);

			listVO.add(vo);
		}
		return listVO;
	}

	@Override
	public TravellerVO getTravellerById(Integer id) {

		//use repo
		TravellerEntity existTraveller = travellerRepo.findById(id)
				.orElseThrow(()-> new TravellersNotFoundException("Traveller not found with id: "+id));

		TravellerVO travellerVO = new TravellerVO();

		BeanUtils.copyProperties(existTraveller, travellerVO);

		return travellerVO;
	}

	@Override
	public String updateTraveller(TravellerVO vo) {

		//use repo
		TravellerEntity existTraveller = travellerRepo.findById(vo.getTid())
				.orElseThrow(()-> new TravellersNotFoundException("Traveller not found with id: "+vo.getTid()));

		//convert vo to Entity
		BeanUtils.copyProperties(vo, existTraveller);

		//update the object
		travellerRepo.save(existTraveller);

		return "Traveller Object is updated";
	}

	@Override
	public String updateTravellerBudget(Integer tid, Double discountPercentage) {

		//use repo
		TravellerEntity existTraveller = travellerRepo.findById(tid)
				.orElseThrow(()-> new TravellersNotFoundException("Traveller not found with id: "+tid));

		//calculate discount amount
		Double discount =existTraveller.getBudget()*discountPercentage/100.0;

		//set final amount
		existTraveller.setBudget(existTraveller.getBudget()-discount);

		//update the object
		travellerRepo.save(existTraveller);

		return tid+" Traveller Budget is updated";	
	}

	@Override
	public String removeTravellersByBudgetRange(Double startBudget, Double endBudget) {

		//usee repo
		int deleteCount = travellerRepo.deleteTravellersByBudgetRange(startBudget, endBudget);

		if(deleteCount==0) {

			throw new TravellersNotFoundException("No Travellers found within budget range: "+startBudget+" - "+endBudget);
		}

		return deleteCount+" no.of travellers are deleted based on "+startBudget+" ... "+endBudget+" range";
	}

	@Override
	public String removeTravellerById(Integer tid) {

		//use repo
		TravellerEntity existTraveller = travellerRepo.findById(tid)
				.orElseThrow(()-> new TravellersNotFoundException("Traveller not found with id: "+tid));

		//delete the object
		travellerRepo.deleteById(tid);
		
		return tid+" Traveller is found and deleted";
	}
}
