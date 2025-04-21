package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Model.Database;
import Model.Operation;
import Model.User;

public class ShowSpecUserRents implements Operation {

	@Override
	public void operation(Database database, Scanner s, User user) {
		System.out.println("Enter User ID (int): (-1 to show all users)");
		int ID = s.nextInt();
		while (ID==-1) {
			printUsers(database);
			System.out.println("Enter User ID (int): (-1 to show all users)");
			ID = s.nextInt();
		}
		new ShowUserRents(ID).operation(database, s, user);
	}
	
	private void printUsers(Database database) {
		try {
			ResultSet rs = database.getStatement().executeQuery("SELECT * FROM `users`;");
			while (rs.next()) {
				int accType = rs.getInt("Type");
				if (accType==0) {
					System.out.println("ID:\t\t"+rs.getInt("ID"));
					System.out.println("First Name:\t"+rs.getString("FirstName"));
					System.out.println("Last Name:\t"+rs.getString("LastName"));
					System.out.println("Email:\t\t"+rs.getString("Email"));
					System.out.println("Phone Number:\t"+rs.getString("PhoneNumber"));
					System.out.println("----------------------------");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
