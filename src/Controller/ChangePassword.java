package Controller;

import java.sql.SQLException;
import java.util.Scanner;

import Model.Database;
import Model.Operation;
import Model.User;

public class ChangePassword implements Operation {

	@Override
	public void operation(Database database, Scanner s, User user) {

		System.out.println("Enter old password:");
		String oldPassword = s.next();
		if (!oldPassword.equals(user.getPassword())) {
			System.out.println("Password doesn't match");
			return;
		}
		
		System.out.println("Enter new password:");
		String newPassword = s.next();
		System.out.println("Confirm password:");
		String confirmPassword = s.next();
		while (!newPassword.equals(confirmPassword)) {
			System.out.println("Password doesn't match");
			System.out.println("Enter new password:");
			newPassword = s.next();
			System.out.println("Confirm password:");
			confirmPassword = s.next();
		}
		
		try {
			String update = "UPDATE `users` SET "
					+ "`Password`='"+newPassword+"' WHERE `ID` = '"+user.getID()+"';";
			database.getStatement().execute(update);
			System.out.println("Password changed successfully");
			user.setPassword(newPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
