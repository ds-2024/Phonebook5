package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.PhonebookDao;
import com.javaex.vo.PersonVo;

@Service
public class PhonebookService {

	// 필드
	@Autowired
	private PhonebookDao phonebookDao; // Dao 자동 연결
	// 생성자
	// 메소드 gs
	// 메소드 일반

	// 수정
	public int exeModify(PersonVo personVo) {
		System.out.println("PhonebookService.exeModify()");

		int count = phonebookDao.personUpdate(personVo);
		System.out.println(personVo);
		return count;

	}

	// 수정폼
	public PersonVo exeModifyForm(int no) {
		System.out.println("PhonebookService.exeModifyForm()");
		// PhonebookDao phonebookDao = new PhonebookDao();

		PersonVo personVo = phonebookDao.personSelectOne(no);

		return personVo;
	}
	
	// 수정폼2
	public Map<String, Object> exeModifyForm2(int no) {
		System.out.println("PhonebookService.exeModifyForm2()");
		
		
		Map<String, Object> pMap = phonebookDao.personSelectOne2(no); /*SelectOne2 생성*/
		
		return pMap;
	}

	// 등록
	public void exeWrite(PersonVo personVo) {
		System.out.println("PhonebookService.exeWrite()");

		// 비즈니스로직 내용들어가야하는 자리
		PhonebookDao phonebookDao = new PhonebookDao();

		phonebookDao.personInsert(personVo);
	}
	
	//등록2
	public int exeWrite2(String name, String hp, String company) {
		System.out.println("PhonebookService.exeWrite2()");
		System.out.println(name);
		System.out.println(hp);
		System.out.println(company);
		
		//PersonVo를 제작해서 묶는다 --> 그런데 딱 1번만 쓸것 같다 --> 따라서 1회용 map 사용
		Map<String, String> personMap = new HashMap<String, String>();
		personMap.put("name", name);
		personMap.put("hp", hp);
		personMap.put("company", company);
		
		
		
		int count = phonebookDao.personInsert2(personMap);
		
		return count;
	}

	// 리스트
	public List<PersonVo> exeList() {
		// PhonebookDao phonebookDao = new PhonebookDao();
		List<PersonVo> personList = phonebookDao.personSelect();

		return personList;
	}

	// 삭제
	public int exeDelete(int no) {
		System.out.println("PhonebookService.exeDelete()");

		// PhonebookDao phonebookDao = new PhonebookDao();

		int count = phonebookDao.personDelete(no);

		return count;
	}

}
