package kr.or.ddit.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class CalculatorController {
	@RequestMapping(method=RequestMethod.GET, value="/calculate") //get방식의 요청만 처리하겠다 미리 선언
	public String calForm() {
		return "cal/form";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/calculate")
	public String calProcessCase4( 
			@RequestParam int left //require는 생략된 상태=기본적으로 true
			, @RequestParam int right
			, HttpSession session
			, Model model 
	) throws StreamWriteException, DatabindException, IOException{ 
		
		int result = left + right;
		
		model.addAttribute("result", result);
		
		return "jsonView";

	}
	
//	@RequestMapping(method=RequestMethod.POST, value="/calculate")
//	@ResponseBody 
	public Map<String, Object> calProcessCase3( 
			@RequestParam int left //require는 생략된 상태=기본적으로 true
			, @RequestParam int right
			, HttpSession session
	) throws StreamWriteException, DatabindException, IOException{ 
		int result = left + right;
		Map<String, Object> target = Collections.singletonMap("result", result);
		return target;
//		1. marchalling
//		2. serialization
		// 스프링을 사용하면 ObjectMapper얘는 스프링이 알아서 만들어줘요

	}
	
//	@RequestMapping(method=RequestMethod.POST, value="/calculate")
	public void calProcessCase2( 
			@RequestParam int left //require는 생략된 상태=기본적으로 true
			, @RequestParam int right
			, HttpSession session
			, OutputStream os
	) throws StreamWriteException, DatabindException, IOException{ 
		int result = left + right;
		Map<String, Object> target = Collections.singletonMap("result", result);
//		1. marchalling
//		2. serialization
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(os, target); // writer->마샬링, read->언마샬링 //출력스트림 넘겨서 마샬링, 직렬화 한번에!
//		session.setAttribute("result", result);
//		return "redirect:/calculate"; 
	}
	
//	@RequestMapping(method=RequestMethod.POST, value="/calculate")
	public void calProcessCase1( 
			@RequestParam int left //require는 생략된 상태=기본적으로 true
			, @RequestParam int right
			, HttpSession session
			, HttpServletResponse resp
	) throws StreamWriteException, DatabindException, IOException{ 
		int result = left + right;
		Map<String, Object> target = Collections.singletonMap("result", result);
//		1. marchalling
//		2. serialization
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(resp.getOutputStream(), target); // writer->마샬링, read->언마샬링 //출력스트림 넘겨서 마샬링, 직렬화 한번에!
//		session.setAttribute("result", result);
//		return "redirect:/calculate"; 
	}
}
