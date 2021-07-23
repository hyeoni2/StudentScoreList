package com.example.managment;



import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class StudentInfoForm {
	
	private int id;
	
	@NotBlank(message = "반드시 이름을 입력해야 합니다.")
	private String name;
	
	private MultipartFile photo;
	
	private String phoneNumber;
}
