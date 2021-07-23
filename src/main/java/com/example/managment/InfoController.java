package com.example.managment;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class InfoController {

	@Autowired
	StudentInfoRepository studentInfoR;
	
	@GetMapping("/student_add")
	public String student_add(
			StudentInfoForm studentInfoForm) {
	
		return "student_add";
	}
	
	@PostMapping("/student_add")
	public String student_add_process(
			@Valid StudentInfoForm studentInfoForm,
			BindingResult bindingresult

			) {
		
		if(bindingresult.hasErrors()) {
			return "student_add";
		}
		
		StudentInfo studentinfo = new StudentInfo();
		
		if(!(studentInfoForm.getPhoto().isEmpty())){
			MultipartFile photo = studentInfoForm.getPhoto();
			String fileContentType = photo.getContentType();
			
			InputStream is = null;
			
			try {
				is = photo.getInputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			byte[] imeage = null;
			try {
				imeage = is.readAllBytes();
				studentinfo.setPhoto(imeage);
				studentinfo.setImeageType(photo.getContentType());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		studentinfo.setName(studentInfoForm.getName());
		studentinfo.setPhoneNumber(studentInfoForm.getPhoneNumber());
		
		studentInfoR.save(studentinfo);
		return "redirect:/student_list";
		
	}
	
	
	@GetMapping("/student_list")
	public String student_list(
			Model model){
		List<StudentInfo> studentInfoList = studentInfoR.findAll();
		
		model.addAttribute("studentInfoList", studentInfoList);
		return "student_list";
	}
	
	
	
	@GetMapping("/get_image/{id}")
	public void get_imeage(
			@PathVariable("id") int id,
			HttpServletResponse response
			) throws IOException {
		Optional<StudentInfo> studentInfoOptional
		= studentInfoR.findById(id);
		
		if(studentInfoOptional.isPresent()) {
			StudentInfo studentInfo = studentInfoOptional.orElse(null);
			
			response.setContentType(studentInfo.getImeageType());
			OutputStream os = response.getOutputStream();
			byte byteArray[] = studentInfo.getPhoto();
			os.write(byteArray);
			os.flush();
			os.close();
		}
		
		
	
		
		
		
		
		
	}
}
