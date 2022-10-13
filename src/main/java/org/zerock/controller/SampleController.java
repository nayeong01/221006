package org.zerock.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	/*
	@InitBinder // 날짜 같은 문자열을 Date 타입으로 변환해주는 어노테이션
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dataFormat, false));
	}
*/	
	@GetMapping("/ex03")
	public String ex03(TodoDTO todo) {
		log.info("todo: "+todo);
		return "ex03";
	}
	
	@GetMapping("/ex04") // @ModelAttribute : int타입의 테이터를 화면까지 전달해주는 어노테이션
						 // 기본 자료형을 사용할 땐 반드시 붙여주어야 한다.
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
		
		log.info("dto: "+dto);
		log.info("page: "+page);
		
		return "/sample/ex04";
	}
	
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06..........");
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("김나영");
		
		return dto;
	}
	
	@GetMapping("/ex07") //ResponseEntity : Httpheaders 객체를 같이 전달할 수 있다.
	public ResponseEntity<String> ex07(){
		log.info("/ex07..........");
		String msg = "{\"name\": \"홍길동\"}";
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<>(msg, header, HttpStatus.OK);
	}
	
	@RequestMapping("")
	public void basic() {
		log.info("basic..............");
	}
	
	@RequestMapping(value="/basic", method= {RequestMethod.GET, RequestMethod.POST})
	public void basicGet() { // get, post 방식 둘 다 지원가능
		log.info("basic get.................");
	}
	
	@GetMapping("/basicOnlyGet")
	public void basicGet2() { // 오직 get 방식만 지원
		log.info("basic get only get............");
	}
	
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info(""+dto);
		return "ex01";
	}
	
	@GetMapping("/ex02") // @RequestParam : 파라미터 변수의 이름과, 전달되는 파라미터의 이름이 다른 경우에 쓰인다.
	public String ex02 (@RequestParam("name") String name, @RequestParam("age") int age) {
		log.info("name: "+name);
		log.info("age: "+age);
		
		return "ex02";
	}
	
	@GetMapping("/ex02List") // List타입으로 자동 생성되어 여러 개의 파라미터가 넘어와도 하나의 리스트에 다 담긴다.
	public String ex02List(@RequestParam("ids")ArrayList<String> ids) {
		log.info("ids: "+ids);
		
		return "ex02List";
	}
	
	@GetMapping("/ex02Array") //배열타입도 List와 같은 결과.
	public String ex02Array(@RequestParam("ids") String[] ids) {
		log.info("array ids: "+Arrays.toString(ids));
		
		return "ex02Array";
	}
	
	@GetMapping("/ex02Bean") // 여러 개의 객체를 만들고 싶을 때 사용한다. 
	public String ex02Bean(SampleDTOList list) {
		log.info("list dtos: "+list);
		return "ex02Bean";
	}
	
	
}
