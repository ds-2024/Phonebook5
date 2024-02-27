package com.javaex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javaex.dao.PhonebookDao;
import com.javaex.vo.PersonVo;

@Service
public class PhonebookService {
	
	//필드

	private PhonebookDao phonebookDao; //Dao 자동 연결
	//생성자
	//메소드 gs
	//메소드 일반
	
	//수정폼
	public PersonVo exeModifyForm(int no) {
		System.out.println("PhonebookService.exeModifyForm()");
		//PhonebookDao phonebookDao = new PhonebookDao();
		
		PersonVo personVo = phonebookDao.personSelectOne(no);
		
		
		return personVo;
	}
	
	//수정
	public int exeModify(PersonVo personVo) {
		System.out.println("PhonebookService.exeModify()");
		//PhonebookDao phonebookDao = new PhonebookDao();
		
		int count = phonebookDao.personUpdate(personVo);
		
		return count;
		
	}
	
	//리스트
	public List<PersonVo> exeList() {
		PhonebookDao phonebookDao = new PhonebookDao();
		List<PersonVo> personList = phonebookDao.personSelect();
		
		return personList;
	}
	
	//삭제
	public int exeDelete(int no) {
		System.out.println("PhonebookService.exeDelete()");
		
		//PhonebookDao phonebookDao = new PhonebookDao();
		
		int count = phonebookDao.personDelete(no);
		
		return count;
	}
	
	//등록
	public void exeWrite(PersonVo personVo) {
		System.out.println("PhonebookService.exeWrite()");
		
		//비즈니스로직 내용들어가야하는 자리
		PhonebookDao phonebookDao = new PhonebookDao();
		
		phonebookDao.personInsert(personVo);
	}

}
