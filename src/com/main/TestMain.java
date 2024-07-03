package com.main;

import java.util.Scanner;

import com.implementation.AdminImpl;
import com.implementation.UserImpl;

public class TestMain {
	public static void main(String[] args) {
		System.out.println("Welcome to Quiz based application \n");

		System.out.println("User Operation \n");

		System.out.println("1. Student Registration");
		System.out.println("2. Student Login");
		System.out.println("3. Display the list of questions");
		System.out.println("4. Store Quiz result into database");
		System.out.println("5. Display Quiz result \n");

		System.out.println("Admin Operation \n");

		System.out.println("6. Display all students score as per ascending order");
		System.out.println("7. Fetch student score by using id");
		System.out.println("8. Add question with 4 options into database \n");

		Scanner scanner = new Scanner(System.in);
		boolean running = true;

		while (running) {
			System.out.println("Enter your choice:");
			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline
			UserImpl userApp = new UserImpl();
			AdminImpl adminApp = new AdminImpl();

			switch (choice) {
			case 1:
				userApp.registerStudent();
				break;
			case 2:
				if (userApp.loginStudent()) {
					// If login is successful, display additional options
					boolean loggedIn = true;
					while (loggedIn) {
						System.out.println("Enter your choice:");
						int loggedInChoice = scanner.nextInt();
						scanner.nextLine(); // Consume newline

						switch (loggedInChoice) {

						case 3:
							userApp.displayQuestions();
							System.out.println("Quiz result stored successfully!");
							
							break;

						case 4:
							
							System.out.println("Score added Successfully!");
							

							break;

						case 5:
							System.out.println("Enter your username:");
							String username = scanner.nextLine();

							userApp.displayQuizResult(username);
							break;

						}

					}

				}
			case 6:

				adminApp.displayAllStudentsScores();
				break;
			case 7:
				System.out.print("Enter the student Id: ");
				int studentId = scanner.nextInt();
				adminApp.fetchStudentById(studentId);
				break;

			case 8:
				for(int i=1; i<=10; i++) {
					adminApp.addQuestion();
				}
				break;
				
			default:
				System.out.println("You Need to first login this application");

			}
		}
	}
}
