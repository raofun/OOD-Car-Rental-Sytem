package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Model.Car;
import Model.Database;
import Model.Operation;
import Model.Rent;
import Model.User;

public class RentCar implements Operation {

	@Override
	public void operation(Database database, Scanner s, User user) {
		
		System.out.println("Enter Car ID (int): (-1 to show all cars)");
		int carID = s.nextInt();
		while (carID==-1) {
			new ViewCars().operation(database, s, user);
			System.out.println("Enter Car ID (int): (-1 to show all cars)");
			carID = s.nextInt();
		}
		
		//System.out.println("Enter hours (int):");
		System.out.println("Enter hours (int):");
		int hours = 0;
		while (true) {
			try {
				hours = s.nextInt();
				s.nextLine(); // consume leftover newline
				break;
			} catch (Exception e) {
				System.out.println("Invalid input. Please enter a valid number of hours:");
				s.nextLine(); // clear buffer
			}
		}


		try {
			
			ResultSet rs0 = database.getStatement()
					.executeQuery("SELECT * FROM `cars` WHERE `ID` = '"+carID+"';");
			rs0.next();
			Car car = new Car();
			car.setID(rs0.getInt("ID"));
			car.setBrand(rs0.getString("Brand"));
			car.setModel(rs0.getString("Model"));
			car.setColor(rs0.getString("Color"));
			car.setYear(rs0.getInt("Year"));
			car.setPrice(rs0.getDouble("Price"));
			car.setAvailable(rs0.getInt("Available"));
			
			if (car.isAvailable()!=0) {
				System.out.println("Car isn't available");
				return;
			}
			
			ResultSet rs1 = database.getStatement()
					.executeQuery("SELECT COUNT(*) FROM `rents`;");
			rs1.next();
			int ID = rs1.getInt("COUNT(*)");
			
			double total = car.getPrice()*hours;
			
			Rent rent = new Rent();
			
			String insert = "INSERT INTO `rents`(`ID`, `User`, `Car`, `DateTime`, `Hours`,"
					+ " `Total`, `Status`) VALUES ('"+ID+"','"+user.getID()+"',"
							+ "'"+car.getID()+"','"+rent.getDateTime()+"','"+hours+"',"
									+ "'"+total+"','0');";
			
			database.getStatement().execute(insert);
			System.out.println("Car rented successfully");
			String updateQuery = "UPDATE cars SET Available = 1 WHERE ID = " + carID;
			database.getStatement().executeUpdate(updateQuery);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
