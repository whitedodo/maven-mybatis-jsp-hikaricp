/*
 * 
 * 	주제(Subject): Jsp/Servlet Maven - MyBatis 3.5.5, hikariCP 3.4.2, Oracle 19g
 * 	생성일자(Create Date): 2020-10-01
 *  파일명(Filename): SqlMapSessionFactory.java
 * 	저자(Author): Dodo / rabbit.white at daum dot net
 * 	설명(Description): 
 *  1. Java 방식으로 HikariCP 3.4.2 연결함. , Dodo, 2020-10-01
 *  2. MyBatis 3.5.5와 HikariCP DataSource로 사용함(자바 방식), Dodo, 2020-10-01
 */

package com.example.web.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.example.web.mapper.CompUsersMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import oracle.jdbc.pool.OracleDataSource;

public class SqlMapSessionFactory {

	private static SqlMapSessionFactory factory = new SqlMapSessionFactory();

	public static SqlMapSessionFactory getInstance() {
		return factory;
	}

	public static SqlSessionFactory ssf;

    private static String CLASSNAME;
    private static String JDBC_URL;
    private static String USERNAME;
    private static String PASSWORD;
    private static String CACHE_PREP_STMTS;
    
    private HikariDataSource ds;
    private HikariConfig config;


	private SqlMapSessionFactory() {
		
		/* HikariCP 로드 */
		
    	InputStream inputStream;
    	config = new HikariConfig();
    	
	   	String resource = "db.properties";
	    Properties properties = new Properties();
	    
	    try {

	    	inputStream = getClass().getClassLoader().getResourceAsStream(resource);
	        properties.load(inputStream);

	        System.out.println("jdbcurl:" + properties.getProperty("jdbcUrl"));
	        System.out.println("className" + properties.getProperty("dataSourceClassName"));

	        CLASSNAME = properties.getProperty("dataSourceClassName");
	        JDBC_URL = properties.getProperty("jdbcUrl");
	        USERNAME = properties.getProperty("dataSource.user");
	        PASSWORD = properties.getProperty("dataSource.password");
	        CACHE_PREP_STMTS = properties.getProperty("cachePrepStmts");

	        config.setDriverClassName(CLASSNAME);
	        config.setJdbcUrl( JDBC_URL );
	        config.setUsername( USERNAME );
	        config.setPassword( PASSWORD );
	        config.addDataSourceProperty( "cachePrepStmts" , CACHE_PREP_STMTS );
	        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
	        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
	        ds = new HikariDataSource( config );

	    	System.out.println("성공:" + ds);

	    } catch (IOException e) {
	    	System.out.println("오류:" + e.getMessage());
	        e.printStackTrace();
	    }
	    
	}
   	
	// iBatis(MyBatis 반환)
	public SqlSessionFactory getSqlSessionFactory() {
		
		DataSource hDs = ds;
    	// DataSource dataSource = getOracleDataSource();
		
		System.out.println(hDs);
		
    	TransactionFactory transactionFactory = new JdbcTransactionFactory();
    	Environment environment = new Environment("development", transactionFactory, hDs);
    	Configuration configuration = new Configuration(environment);

    	configuration.addMapper(CompUsersMapper.class);		// Mapper 클래스
    	
//    	System.out.println("성공2");
        return new SqlSessionFactoryBuilder().build(configuration);

    }

	public void close(Connection conn, PreparedStatement ps, ResultSet rs) {

		if ( rs != null ) {

			try {
				rs.close();
			}
			catch(Exception ex) {
				System.out.println("오류 발생: " + ex);
			}
			
			close(conn, ps);	// Recursive 구조 응용(재귀 함수)
		} // end of if

	}	

	public void close(Connection conn, PreparedStatement ps) {

		if (ps != null ) {
			try {
				ps.close();
			}
			catch(Exception ex) {
				System.out.println("오류 발생: " + ex);
			}
		} // end of if

		if (conn != null ) {

			try {
				conn.close();
			}
			catch(Exception ex) {
				System.out.println("오류 발생: " + ex);
			}

		} // end of if

	}

}