package Controller;

import java.sql.SQLException;
import java.util.Scanner;

import Model.Database;
import Model.Operation;
import Model.User;

public class DeleteCar implements Operation {

	@Override
	public void operation(Database database, Scanner s, User user) {
		System.out.println("Enter ID (int): (-1 to show all cars)");
		int ID = s.nextInt();
		while (ID==-1) {
			new ViewCars().operation(database, s, user);
			System.out.println("Enter ID (int): (-1 to show all cars)");
			ID = s.nextInt();
		}
		
		try {
			String update = "UPDATE `cars` SET `Available`='2' WHERE `ID` = '"+ID+"';";
			database.getStatement().execute(update);
			System.out.println("Car deleted successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
