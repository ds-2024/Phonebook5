package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhonebookDao;
import com.javaex.vo.PersonVo;

@Controller //어노테이션 화살표 3번 작업
public class PhonebookController {
	
	//필드
	
	//생성자
	
	//메소드gs
	//메소드일반
	
	//등록폼
	//localhost:8080/phonebook5/phone/writeform
	@RequestMapping(value="/phone/writeform", method = {RequestMethod.GET, RequestMethod.POST} )
	public String writeForm() {
		System.out.println("PhonebookController.writeForm()");
		
		return "/WEB-INF/views/writeForm.jsp";//클라이언트에게 뷰를 보여주는 역할
	}// 준비시키는 과정. 화살표2번 핸들러 매핑. 제대로 작동하는지 syso 확인
	
	
	//등록2
	//localhost:8080/phonebook5/phone/write2?name=ㅇㄷㅅ&hp=010&company=02
	@RequestMapping(value="/phone/write2", method = {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute PersonVo personVo) {
		
		System.out.println("PhonebookController.write()");
		/*
		System.out.println(name);
		System.out.println(hp);
		System.out.println(company);
		*/
		//vo  묶는다
		//PersonVo personVo =  new PersonVo(name, hp, company);
		
		
		System.out.println(personVo.toString());
		
		//dao를 메모리에 올린다
		PhonebookDao phonebookDao = new PhonebookDao();
		
		//dao.personInsert(vo) 저장한다
		phonebookDao.personInsert(personVo);
		
		//리스트로 리다이렉트
		
		return "redirect:/phonebook5/phone/list";
		
		
	}
		//등록
		//localhost:8080/phonebook5/phone/write?name=ㅇㄷㅅ&hp=010&company=02
		@RequestMapping(value="/phone/write", method = {RequestMethod.GET, RequestMethod.POST})
		public String write(@RequestParam(value="name") String name,
				  @RequestParam(value="hp")	String hp,
				  @RequestParam(value="company") String company) {
			System.out.println("PhonebookController.write()");
			
			System.out.println(name);
			System.out.println(hp);
			System.out.println(company);
			
			//vo  묶는다
			PersonVo personVo =  new PersonVo(name, hp, company);
				
			//dao를 메모리에 올린다
			PhonebookDao phonebookDao = new PhonebookDao();
			
			//dao.personInsert(vo)
			phonebookDao.personInsert(personVo);
			
			//리스트로 리다이렉트
			return "redirect:/phone/list";
		}
		@RequestMapping(value="/phone/list", method = {RequestMethod.GET, RequestMethod.POST})
		public String list(Model model) {
			System.out.println("PhonebookController.list()");
			
			PhonebookDao phonebookDao = new PhonebookDao();
			
			List<PersonVo> personList = phonebookDao.personSelect();
			System.out.println(personList);
			
			model.addAttribute("pList", personList);
			
			return "/WEB-INF/views/list.jsp";// 포원드는 이곳에!
		}


}



