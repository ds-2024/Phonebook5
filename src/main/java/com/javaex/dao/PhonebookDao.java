package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.PersonVo;

public class PhonebookDao {
	
	//필드 
	
	//생성자
	
	//메소드-gs
	
	//메소드-일반
	
	//삭제
	public int personDelete(int no) {
		int count = -1;
		
		// 0. import java.sql.*;
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					try {
					// 1. JDBC 드라이버 (Oracle) 로딩
						Class.forName("com.mysql.cj.jdbc.Driver");

					// 2. Connection 얻어오기
						String url = "jdbc:mysql://localhost:3306/phone_db";
						conn = DriverManager.getConnection(url, "phone", "phone");	
					// 3. SQL문 준비 / 바인딩 / 실행
					// SQL문 준비
						
						String query = "";
						query +=" delete from person ";
						query += " where person_id = ? " ;
						
						//바인딩	
						pstmt = conn.prepareStatement(query);
						pstmt.setInt(1, no);
						
					//실행	
					
						count =  pstmt.executeUpdate();

						
						
					// 4.결과처리
						System.out.println(count + "건 삭제되었습니다.");
						
					} catch (ClassNotFoundException e) {
						System.out.println("error: 드라이버 로딩 실패 - " + e);
						} catch (SQLException e) {
						System.out.println("error:" + e);
						} finally {
						// 5. 자원정리
						try {
						if (rs != null) {
						rs.close();
						} 
						if (pstmt != null) {
						pstmt.close();
						}
						if (conn != null) {
						conn.close();
						}
						} catch (SQLException e) {
						System.out.println("error:" + e);
						}
					}	
					return count;	
		}
	
	//수정
		public int personUpdate(PersonVo personVo) {
			int count = -1;
			
			// 0. import java.sql.*;
						Connection conn = null;
						PreparedStatement pstmt = null;
						ResultSet rs = null;
						try {
						// 1. JDBC 드라이버 (Oracle) 로딩
							Class.forName("com.mysql.cj.jdbc.Driver");

						// 2. Connection 얻어오기
							String url = "jdbc:mysql://localhost:3306/phone_db";
							conn = DriverManager.getConnection(url, "phone", "phone");	
						// 3. SQL문 준비 / 바인딩 / 실행
						// SQL문 준비
							
							String query = " update person ";
							query += " set person_id = ? ";
							query += " where person_id = ? " ;
							
							 
							
						//생성	
							pstmt = conn.prepareStatement(query);
							
						//바인딩
							pstmt.setInt(1, personVo.getPersonId() );
							pstmt.setInt(2, personVo.getNewpersonId() );
							
						//실행	
						
							count =  pstmt.executeUpdate();

							
							
						// 4.결과처리
							System.out.println(count + "건 수정되었습니다.");
							
						} catch (ClassNotFoundException e) {
							System.out.println("error: 드라이버 로딩 실패 - " + e);
							} catch (SQLException e) {
							System.out.println("error:" + e);
							} finally {
							// 5. 자원정리
							try {
							if (rs != null) {
							rs.close();
							} 
							if (pstmt != null) {
							pstmt.close();
							}
							if (conn != null) {
							conn.close();
							}
							} catch (SQLException e) {
							System.out.println("error:" + e);
							}
						}	
						return count;	
			}
						
					
	
	//db가져오기
	public List<PersonVo> personSelect() {
		
	List<PersonVo> personList = new ArrayList<PersonVo>();
	
			// 0. import java.sql.*;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				// 1. JDBC 드라이버 (Oracle) 로딩
					Class.forName("com.mysql.cj.jdbc.Driver");
	
				// 2. Connection 얻어오기
					String url = "jdbc:mysql://localhost:3306/phone_db";
					conn = DriverManager.getConnection(url, "phone", "phone");	
				// 3. SQL문 준비 / 바인딩 / 실행
				// SQL문 준비
					String query = "";
					query += " select person_id, ";
					query += "	      name, ";
					query += "        hp, ";
					query += "	      company ";
					query += " from person "; //띄어쓰기 주의
					
				//바인딩	
				pstmt = conn.prepareStatement(query);
					
				
				//실행	
			
				rs = pstmt.executeQuery();

				
				
				// 4.결과처리
				
				while(rs.next()) {
					int personId = rs.getInt("person_id");
					String name = rs.getString("name");
					String hp = rs.getString("hp");
					String company = rs.getString("company");
					
					//db에서 가져온 데이터 vo로 묶기
					PersonVo personVo = new PersonVo(personId, name, hp, company);
					//리스트에 주소 추가
					personList.add(personVo);
				}
			
				
			} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
			} catch (SQLException e) {
			System.out.println("error:" + e);
			} finally {
			// 5. 자원정리
			try {
			if (rs != null) {
			rs.close();
			} 
			if (pstmt != null) {
			pstmt.close();
			}
			if (conn != null) {
			conn.close();
			}
			} catch (SQLException e) {
			System.out.println("error:" + e);
			}
		}	
				
			return personList;		
				/*select  person_id,
				name,
		        hp,
		        company
		from person;*/
				
				
				/*System.out.println(query);*/
				
				/*insert into person
				values(null, 'ㅇㄷㅅ', '010-1111-2222','02-1111-2222');*/
			
			
		}
		
	
	
	//등록
	public int personInsert(PersonVo personVo) {
		int count = -1;
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");

		// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/phone_db";
			conn = DriverManager.getConnection(url, "phone", "phone");	
		// 3. SQL문 준비 / 바인딩 / 실행
		// SQL문 준비
			String query = "";
			query += " insert into person ";
			query += " values(null, ?, ? ,?) "; //띄어쓰기 필수!!! 못알아 먹는다
			
			
			System.out.println(query);
			
			/*insert into person
			values(null, 'ㅇㄷㅅ', '010-1111-2222','02-1111-2222');*/
		
		//바인딩	
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());
			
		//실행	
		
			count = pstmt.executeUpdate();

			
			
		// 4.결과처리
			System.out.println(count + "건 등록되었습니다.");
			
		} catch (ClassNotFoundException e) {
		System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		System.out.println("error:" + e);
		} finally {
		// 5. 자원정리
		try {
		if (rs != null) {
		rs.close();
		} 
		if (pstmt != null) {
		pstmt.close();
		}
		if (conn != null) {
		conn.close();
		}
		} catch (SQLException e) {
		System.out.println("error:" + e);
		}
		}
		return count;
	}
	
	//삭제
	

}
