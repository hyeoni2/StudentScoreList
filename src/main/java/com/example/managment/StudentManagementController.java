package com.example.managment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class StudentManagementController {
	
	
	
	@GetMapping("/")
	public String home() {
		
		return "index";
	}
}
