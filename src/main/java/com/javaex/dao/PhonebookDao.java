package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class PhonebookDao {

	@Autowired
	private SqlSession sqlSession;

	// 삭제
	public int personDelete(int no) {
		System.out.println("PhonebookDao.personDelete()");

		int count = sqlSession.delete("phonebook.delete", no);
		System.out.println(count);
		return count;

	}

	// 수정
	public int personUpdate(PersonVo personVo) {
		System.out.println("PhonebookDao.personUpdate()");

		int count = sqlSession.update("phonebook.update", personVo);
		System.out.println(count);
		System.out.println(personVo);
		return count;
	}

	// 수정폼(1개 데이터 가져오기 뿌릴데이터 가져오기)
	// 1개 가져오기
	public PersonVo personSelectOne(int no) {
		System.out.println("PhonebookDao.personSelectOne()");

		PersonVo personVo = sqlSession.selectOne("phonebook.selectOne", no);

		System.out.println(personVo);

		return personVo;
	}

	// 등록
	public int personInsert(PersonVo personVo) {
		System.out.println("PhonebookDao.personInsert()");

		int count = sqlSession.insert("phonebook.insert", personVo);

		return count;
	}

	// 전체가져오기
	public List<PersonVo> personSelect() {
		System.out.println("phonebookService.exeList()");
		List<PersonVo> personList = sqlSession.selectList("phonebook.select");
		System.out.println(personList);
		return personList;
	}

}