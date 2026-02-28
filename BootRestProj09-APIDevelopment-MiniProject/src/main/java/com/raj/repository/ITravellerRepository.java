package com.raj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.raj.entity.TravellerEntity;

import jakarta.transaction.Transactional;

@Repository
public interface ITravellerRepository extends JpaRepository<TravellerEntity, Integer> {

	@Query("FROM TravellerEntity WHERE budget BETWEEN :start AND :end")
	public List<TravellerEntity> fetchTravellersByBudgetRange(@Param("start") Double startRange, @Param("end") Double endRange);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM TravellerEntity t WHERE t.budget BETWEEN :startBudget AND :endBudget")
	int deleteTravellersByBudgetRange(@Param("startBudget")Double startBudget, @Param("endBudget")Double endBudget);
	
}
