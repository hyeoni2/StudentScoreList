package com.example.managment;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ScoreForm {
	
	@NotBlank(message = "학사번호 입력은 필수입니다.")
	@Size(message = "학사번호는 5자리입니다.", min = 5, max = 5)
	private String studentNo;
	
	
	@Range(min = 0, max = 100, message = "성적등록은 0~100이하의 수만 가능합니다.")
	private int guk;
	
	
	@Range(min = 0, max = 100, message = "성적등록은 0~100이하의 수만 가능합니다.")
	private int math;
	

	@Range(min = 0, max = 100, message = "성적등록은 0~100이하의 수만 가능합니다.")
	private int sahee;

}
