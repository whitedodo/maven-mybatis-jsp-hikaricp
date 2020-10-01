/*
 * 
 * 	주제(Subject): Jsp/Servlet Maven - MyBatis 3.5.5, hikariCP 3.4.2, Oracle 19g
 * 	생성일자(Create Date): 2020-10-01
 *  파일명(Filename): FrontController.java
 * 	저자(Author): Dodo / rabbit.white at daum dot net
 * 	설명(Description): 
 * 
 * 
 */

package com.example.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.example.web.db.SqlMapSessionFactory;
import com.example.web.mapper.CompUsersMapper;
import com.example.web.model.CompUsers;

public class FrontController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    SqlSessionFactory factory = null;
    SqlMapSessionFactory sqlMapFactory = null;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	
	protected void doAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		sqlMapFactory = SqlMapSessionFactory.getInstance();
		
		try {
			factory = sqlMapFactory.getSqlSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try (SqlSession session = factory.openSession()) {
		  CompUsersMapper mapper = session.getMapper(CompUsersMapper.class);
		  CompUsers user = mapper.findByUsername("user");
		  
		  System.out.println("계정명:" + user.getUsername());
		  
		}
		
	}

}
