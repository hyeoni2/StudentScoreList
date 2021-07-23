package com.example.managment;

import java.util.List;
import java.util.Optional;

import javax.persistence.Tuple;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class ScoreController {
	
	@Autowired
	ScoreRepository scoreRepository;
	
	@GetMapping("/score_add")
	public String score_add(
			ScoreForm scoreForm) {
		
		return "score_add";
	}
	
	
	@PostMapping("/score_add")
	public String score_add_process(
			@Valid ScoreForm scoreForm, 
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
		
			return "score_add";
	}
	//저장 처리
		Score score = new Score();
		score.setStudentNo(scoreForm.getStudentNo());
		score.setGuk(scoreForm.getGuk());
		score.setMath(scoreForm.getMath());
		score.setSahee(scoreForm.getSahee());
		
		Score scoreResult = scoreRepository.save(score);
		if(scoreResult != null) {
			redirectAttributes.addFlashAttribute("db_message","성적정보가 잘 저장되었습니다.");
			
			return "redirect:score_list";
		}
	
		return "score_add";
	}
	

	
	
	@GetMapping("/score_edit/{studentNo}")
	public String score_edit(
			@PathVariable String studentNo,
			Model model
			) {

		Score score = new Score();
		
		Optional<Score> optionalScore = scoreRepository.findById(studentNo);
		
		if(optionalScore.isPresent()) {
			score = optionalScore.orElse(null);
		}
		
		model.addAttribute("score", score);
		
		return "score_edit";
	}
	
	
	@PostMapping("/score_edit/{studentNo}")
	public String score_edit_process(
			@PathVariable String studentNo,
			@RequestParam int guk,
			@RequestParam int math,
			@RequestParam int sahee,
			RedirectAttributes redirectAttributes
			) {
		
		Score score = new Score();
		score.setStudentNo(studentNo);
		score.setGuk(guk);
		score.setMath(math);
		score.setSahee(sahee);
		
		Score scoreResult = scoreRepository.save(score);
		if(scoreResult != null) {
			redirectAttributes.addFlashAttribute("db_message","성적정보가 수정되었습니다.");
			return "redirect:/score_list";
		}

		return "score_edit";
	}
	
	
	
	
	

	

	@GetMapping("/score_del/{studentNo}")
	public String score_del(
			@PathVariable String studentNo,
			RedirectAttributes redirectAttributes
			) {
		scoreRepository.deleteById(studentNo);
		redirectAttributes.addFlashAttribute("db_message",  
				"「학사번호 " + studentNo + "」" + "의 성적 자료가 삭제되었습니다.");
		return "redirect:/score_list";
	
	}
	
	
	
	@GetMapping("/score_list")
	public String score_list(
			Model model,
			@RequestParam(defaultValue = "date") String sort
			) {
		System.out.println("정렬"+sort);
		
		long counts = scoreRepository.count();
		String countString = String.format("%d건 검색됨", counts);
		model.addAttribute("countString", countString);
		
		List<Score> scoreList  = null;
		if(sort.equals("date")){
			scoreList = scoreRepository.findAll(Sort.by(Sort.Direction.ASC, "editedAt"));
		}else if(sort.equals("studentNo")) {
			scoreList = scoreRepository.findAll(Sort.by(Sort.Direction.ASC, "studentNo"));
		}else if(sort.equals("score")){
			scoreList = scoreRepository.order_by_score_desc(); 
		}
	
		double getGukAvg = scoreRepository.getGukAvg();
		double getMathAvg = scoreRepository.getMathAvg();
		double getSaheeAvg = scoreRepository.getSaheeAvg();
		
		
		model.addAttribute("scoreList", scoreList);
		model.addAttribute("getGukAvg", getGukAvg);
		model.addAttribute("getMathAvg", getMathAvg);
		model.addAttribute("getSaheeAvg", getSaheeAvg);
		model.addAttribute("sort", sort);
		return "score_list";
	}
}
