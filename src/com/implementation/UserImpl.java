package com.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.classes.Connections;
import com.interfaces.UserOperation;

public class UserImpl implements UserOperation {


	// method for student registration
	@Override
	public void registerStudent() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the first name:");
		String firstName = scanner.nextLine();
		System.out.println("Enter the last name:");
		String lastName = scanner.nextLine();
		System.out.println("Enter the username:");
		String username = scanner.nextLine();
		System.out.println("Enter the password:");
		String password = scanner.nextLine();
		System.out.println("Enter the city:");
		String city = scanner.nextLine();
		System.out.println("Enter the email:");
		String email = scanner.nextLine();
		System.out.println("Enter the mobile number:");
		String mobileNumber = scanner.nextLine();

		try {
			Connections conns = new Connections();
			Connection connection = conns.getConnection();

			String query = "INSERT INTO students (first_name, last_name, username, password, city, email, mobile_number, score) VALUES (?, ?, ?, ?, ?, ?, ?, 0)";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			stmt.setString(3, username);
			stmt.setString(4, password);
			stmt.setString(5, city);
			stmt.setString(6, email);
			stmt.setString(7, mobileNumber);
			stmt.executeUpdate();
			System.out.println("Student registered successfully!");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//method for student login
	@Override
	public boolean loginStudent() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the username:");
		String username = scanner.nextLine();
		System.out.println("Enter the password:");
		String password = scanner.nextLine();

		try {

			Connections conns = new Connections();
			Connection connection = conns.getConnection();

			String query = "SELECT * FROM students WHERE username = ? AND password = ?";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				System.out.println("Login successful!");
				return true;
			} else {
				System.out.println("Invalid username or password.");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

//method for display question	
	@Override
	public void displayQuestions() {
		Scanner scanner = new Scanner(System.in);
		
		try {
			Connections conns = new Connections();
			Connection connection = conns.getConnection();
			String query = "SELECT * FROM questions";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			int score=0;
			
			while (rs.next()) {
				System.out.println(rs.getInt("question_id") + ". " + rs.getString("question_text"));
				System.out.println("a. " + rs.getString("option_a"));
				System.out.println("b. " + rs.getString("option_b"));
				System.out.println("c. " + rs.getString("option_c"));
				System.out.println("d. " + rs.getString("option_d"));
				
				System.out.println("");
				System.out.print("Enter the answer: ");
				String answer = scanner.next();
				

				if (answer.equalsIgnoreCase(rs.getString("correct_option"))) {
					
					score++;
					UserImpl user = new UserImpl();
					user.storeQuizResult(score);
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//method for storeQuizResult
	@Override
	public void storeQuizResult(int score) {
		try {

			Connections conns = new Connections();
			Connection connection = conns.getConnection();

			String query = "UPDATE students SET score = ?";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, score);
//			stmt.setString(2, username);
			stmt.executeUpdate();
			UserImpl user = new UserImpl();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//method for displayQuizResult
	@Override
	public void displayQuizResult(String username) {
		try {
			
			Connections conns = new Connections();
			Connection connection = conns.getConnection();

			String query = "SELECT score FROM students WHERE username = ?";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				System.out.println("Your score is: " + rs.getInt("score"));
			} else {
				System.out.println("Student not found.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
