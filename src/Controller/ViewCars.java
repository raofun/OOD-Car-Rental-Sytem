package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Car;
import Model.Database;
import Model.Operation;
import Model.User;

public class ViewCars implements Operation {

	@Override
	public void operation(Database database, Scanner s, User user) {

		System.out.println();
		String select = "SELECT * FROM `cars`;";
		ArrayList<Car> cars = new ArrayList<>();
		try {
			ResultSet rs = database.getStatement().executeQuery(select);
			while (rs.next()) {
				Car car = new Car();
				car.setID(rs.getInt("ID"));
				car.setBrand(rs.getString("Brand"));
				car.setModel(rs.getString("Model"));
				car.setColor(rs.getString("Color"));
				car.setYear(rs.getInt("Year"));
				car.setPrice(rs.getDouble("Price"));
				car.setAvailable(rs.getInt("Available"));
				cars.add(car);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (Car c : cars) {
			if (c.isAvailable()<2) {
				System.out.println("ID:\t"+c.getID());
				System.out.println("Brand:\t"+c.getBrand());
				System.out.println("Model:\t"+c.getModel());
				System.out.println("Color:\t"+c.getColor());
				System.out.println("Year:\t"+c.getYear());
				System.out.println("Price:\t"+c.getPrice()+"৳ (Per Hour)");
				if (c.isAvailable()==0) {
					System.out.println("Status:\tAvailable");
				} else {
					System.out.println("Status:\tNot Available");
				}
				System.out.println("------------------");
			}
		}
		
		System.out.println();

	}

}
