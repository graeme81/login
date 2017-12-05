package com.work.jdbc;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

public class TestJdbc {

	public static void main(String[] args) {
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/work?useSSL=false";
		String user = "hbstudent";
		String pass = "hbstudent";
		
		try{
			System.out.println("connecting to Database: "+ jdbcUrl);
			
			Connection myConn = (Connection) DriverManager.getConnection(jdbcUrl, user, pass);
			
			System.out.println("Connection Successful!!!");
			
			
		}catch(Exception exc){
			exc.printStackTrace();
		}

	}

}
