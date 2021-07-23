package com.example.managment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;


public interface ScoreRepository extends JpaRepository<Score, String> {

	@Procedure(procedureName = "get_guk_avg")
	double getGukAvg();
	
	@Procedure(procedureName = "get_math_avg")
	double getMathAvg();
	
	@Procedure(procedureName = "get_sahee_avg")
	double getSaheeAvg();
	

	@Query(value = "CALL order_by_score_desc();", nativeQuery = true)
	List<Score> order_by_score_desc();
	
}
