package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Model.Database;
import Model.Operation;
import Model.Rent;
import Model.User;

public class ReturnCar implements Operation {

	@Override
	public void operation(Database database, Scanner s, User user) {
		
		System.out.println("Enter Rent ID (int): (-1 to show all rents)");
		int ID = s.nextInt();
		while (ID==-1) {
			new ShowUserRents(user.getID()).operation(database, s, user);
			System.out.println("Enter Rent ID (int): (-1 to show all rents)");
			ID = s.nextInt();
		}
		
		try {
			String select = "SELECT * FROM `rents` WHERE `ID` = '"+ID+"';";
			ResultSet rs = database.getStatement().executeQuery(select);
			rs.next();
			Rent r = new Rent();
			r.setID(rs.getInt("ID"));
			r.setUser(user);
			r.setDateTime(rs.getString("DateTime"));
			r.setHours(rs.getInt("Hours"));
			r.setTotal(rs.getDouble("Total"));
			r.setStatus(rs.getInt("Status"));

			int carID = rs.getInt("Car");

			if (r.getStatusToString().equals("Delayed")) {
				System.out.println(r.getDelayedHours()+" delayed hours");
				System.out.println("You will have to pay 10000৳ as fine");
			}
			
			String update = "UPDATE `rents` SET `Status`='1' WHERE `ID` = '"+ID+"';";
			database.getStatement().execute(update);

			String updateCar = "UPDATE `cars` SET `Available` = 0 WHERE `ID` = '" + carID + "';";
			database.getStatement().execute(updateCar);

			System.out.println("Car returned successfully");


		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
