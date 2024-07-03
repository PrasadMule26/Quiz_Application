package com.classes;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connections {
	
	public Connection getConnection() {
		Connection con = null;
		
		try {
			//step-1
			Class.forName("com.mysql.jdbc.Driver");
			
			//step-2
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz_app", "root", "#Sql@123#");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return con;
	}

}
