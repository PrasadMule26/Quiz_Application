package com.implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import com.interfaces.AdminOperation;

public class AdminImpl implements AdminOperation {
	
	public Connection getConnection() {
		Connection con = null;

		try {
			// step-1
			Class.forName("com.mysql.jdbc.Driver");

			// step-2
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz_app", "root", "#Sql@123#");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
	
	// method for etchStudentById
	@Override
	public void fetchStudentById(int studentId) {
		try {

			AdminImpl admin = new AdminImpl();
			Connection connection = admin.getConnection();

			String query = "SELECT * FROM students WHERE student_id = ?";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, studentId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				System.out.println(rs.getString("first_name") + " " + rs.getString("last_name") + " - Score: "
						+ rs.getInt("score"));
			} else {
				System.out.println("Student not found.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// method for displayAllStudentsScores
	@Override
	public void displayAllStudentsScores() {

		try {

			AdminImpl admin = new AdminImpl();
			Connection connection = admin.getConnection();

			String query = "SELECT * FROM students ORDER BY score ASC";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				System.out.println(rs.getString("first_name") + " " + rs.getString("last_name") + " - Score: "
						+ rs.getInt("score"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// method for addQuestion
	@Override
	public void addQuestion() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the question:");
		String questionText = scanner.nextLine();
		System.out.println("Enter option A:");
		String optionA = scanner.nextLine();
		System.out.println("Enter option B:");
		String optionB = scanner.nextLine();
		System.out.println("Enter option C:");
		String optionC = scanner.nextLine();
		System.out.println("Enter option D:");
		String optionD = scanner.nextLine();
		System.out.println("Enter the correct option (a/b/c/d):");
		char correctOption = scanner.nextLine().charAt(0);

		try {

			AdminImpl admin = new AdminImpl();
			Connection connection = admin.getConnection();
			
			String query = "INSERT INTO questions (question_text, option_a, option_b, option_c, option_d, correct_option) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, questionText);
			stmt.setString(2, optionA);
			stmt.setString(3, optionB);
			stmt.setString(4, optionC);
			stmt.setString(5, optionD);
			stmt.setString(6, String.valueOf(correctOption));
			stmt.executeUpdate();
			System.out.println("Question added successfully!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
