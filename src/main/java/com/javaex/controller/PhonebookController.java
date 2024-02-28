package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//import com.javaex.dao.PhonebookDao;// PhonebookService클래스가 DB랑 상호작용하는 클래스니까 필요없음
import com.javaex.service.PhonebookService;
import com.javaex.vo.PersonVo;

@Controller // 어노테이션 화살표 3번 작업
public class PhonebookController {

	// 필드
	//메모리에 올려줘 (제3자에게 주입=> 제어의 역전.따라서 new 라고 객체 생성 필요X)
	@Autowired
	private PhonebookService phonebookService;
	// 생성자

	// 메소드gs
	// 메소드일반
	
	// 등록
		// localhost:8080/phonebook5/phone/write?name=ㅇㄷㅅ&hp=010&company=02
		@RequestMapping(value = "/phone/write", method = { RequestMethod.GET, RequestMethod.POST })
		public String write(@RequestParam(value = "name") String name,
							@RequestParam(value = "hp") String hp,
							@RequestParam(value = "company") String company) {
			System.out.println("PhonebookController.write()");

			System.out.println(name);
			System.out.println(hp);
			System.out.println(company);

			  //vo 묶는다 
			  PersonVo personVo = new PersonVo(name, hp, company);
			 
			/* 
			 * dao를 메모리에 올린다 PhonebookDao phonebookDao = new PhonebookDao();
			 * 
			 * dao.personInsert(vo) -코드아님 
			 * PersonVo personVo = phonebookDao.personInsert(personVo);
			 */
				phonebookService.exeWrite(personVo);
			// 리스트로 리다이렉트
			return "redirect:/phone/list"; // 리다이렉트는 고치면 안됨(feat.View Resolver 설정)
		}
	
	//등록2
	// localhost:8080/phonebook5/phone/write2?name=ㅇㄷㅅ&hp=010&company=02
	@RequestMapping(value = "/phone/write2", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute PersonVo personVo) {

		System.out.println("PhonebookController.write()");
		/*
		 * System.out.println(name); System.out.println(hp);
		 * System.out.println(company);
		 */
		// vo 묶는다
		// PersonVo personVo = new PersonVo(name, hp, company);

		System.out.println(personVo.toString());

		// 서비스를 메모리에 올리고
		// 서비스의 메소드 사용

		//PhonebookService phonebookService = new PhonebookService();
		
		phonebookService.exeWrite(personVo);

		/*
		 * dao를 메모리에 올린다 -> Service에 업무 넘김 PhonebookDao phonebookDao = new
		 * PhonebookDao();
		 * 
		 * //dao.personInsert(vo) 저장한다 phonebookDao.personInsert(personVo);
		 */

		// 리스트로 리다이렉트

		return "redirect:/phonebook5/phone/list";

	}

	
	// 리스트
		@RequestMapping(value = "/phone/list", method = { RequestMethod.GET, RequestMethod.POST })
		public String list(Model model) {
			System.out.println("PhonebookController.list()");
			
			//메모리에 올려줘 (제3자에게 주입=> 제어의 역전.따라서 new 라고 객체 생성 필요X)
			//PhonebookService phonebookService = new PhonebookService();@Service 덕분에 자동연결 됨.

			List<PersonVo> personList = phonebookService.exeList();
			/*
			 * Dao 업무를 Service로 넘기자 PhonebookDao phonebookDao = new PhonebookDao();
			 * 
			 * List<PersonVo> personList = phonebookDao.personSelect();
			 * System.out.println(personList);
			 */

			model.addAttribute("pList", personList);

			return "list";// 포워드는 이곳에! // 문자열 앞뒤로 붙이는 View Resolver 설정 따라서 /WEB-INF/views/, .jsp 안써줘도 됨
		}


	// 삭제
	@RequestMapping(value = "/phone/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("no") int no) {
		System.out.println("PhonebookController.delete()");

		//PhonebookService phonebookService = new PhonebookService();

		phonebookService.exeDelete(no);

		/* 바로 dao 쓰는 코드 있으면 안됨. Service 쓰고 있잖아
		 * dao를 메모리에 올린다 PhonebookDao phonebookDao = new PhonebookDao(); //메소드(기능처리)
		 * phonebookDao.personDelete(no);
		 */

		return "redirect:/phone/list";
	}

	
	// 수정
	@RequestMapping(value = "/phone/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute PersonVo personVo) {
		System.out.println("PhonebookController.modify()");

		//PhonebookService phonebookService = new PhonebookService();
		
		phonebookService.exeModify(personVo);
		// dao를 메모리에 올린다
		//PhonebookDao phonebookDao = new PhonebookDao();
		// 수정기능문
		//phonebookDao.personUpdate(personVo);

		return "redirect:/phone/list";
	}

	// 수정폼
	@RequestMapping(value = "/phone/modifyform", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(@RequestParam(value = "no") int no, Model model) {// 하나니까 파라미터로 꺼내줘 //Model 이라는 택배 상자에 담아줘.
		System.out.println("PhonebookController.modifyForm()");

		//PhonebookService phonebookService = new PhonebookService();
		
		
		
		PersonVo personVo = phonebookService.exeModifyForm(no);
	

		/* dao를 메모리에 올린다
		PhonebookDao phonebookDao = new PhonebookDao();

		PersonVo personVo = phonebookDao.personSelectOne(no);

		System.out.println(personVo);
		*/

		model.addAttribute("personVo", personVo);

		return "modifyForm";// 문자열 앞뒤로 붙이는 View Resolver 설정 따라서 /WEB-INF/views/, .jsp 안써줘도 됨

	}

	// 등록폼
	// localhost:8080/phonebook5/phone/writeform
	@RequestMapping(value = "/phone/writeform", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("PhonebookController.writeForm()");

		return "writeForm";// 클라이언트에게 뷰를 보여주는 역할,
	}// 준비시키는 과정. 화살표2번 핸들러 매핑. 제대로 작동하는지 syso 확인

	// 
	

}
