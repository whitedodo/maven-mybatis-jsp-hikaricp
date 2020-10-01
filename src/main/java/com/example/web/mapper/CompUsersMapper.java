/*
 * 
 * 	주제(Subject): Jsp/Servlet Maven - MyBatis 3.5.5, hikariCP 3.4.2, Oracle 19g
 * 	생성일자(Create Date): 2020-10-01
 *  파일명(Filename): CompUsersMapper.java
 * 	저자(Author): Dodo / rabbit.white at daum dot net
 * 	설명(Description): 
 * 
 * 
 */

package com.example.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.web.model.CompUsers;

@Mapper
public interface CompUsersMapper {
	
	  @Select("SELECT * FROM comp_users WHERE username = #{username}")
	  public CompUsers findByUsername(String username);
	  
}

/*

[Spring Boot 버전의 MyBatis 핵심 사용법]

@Mapper
public interface StudentMyBatisRepository {

	@Select("select * from student")
	public List<Student> findAll();

	@Select("SELECT * FROM student WHERE id = #{id}")
	public Student findById(long id);

	@Delete("DELETE FROM student WHERE id = #{id}")
	public int deleteById(long id);

	@Insert("INSERT INTO student(id, name, passport) VALUES (#{id}, #{name}, #{passport})")
	public int insert(Student student);

	@Update("Update student set name=#{name}, passport=#{passport} where id=#{id}")
	public int update(Student student);

}

*/