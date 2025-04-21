package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Car;
import Model.Client;
import Model.Database;
import Model.Operation;
import Model.Rent;
import Model.User;

public class ShowUserRents implements Operation {
	
	private int userID;
	
	public ShowUserRents(int userID) {
		this.userID = userID;
	}

	@Override
	public void operation(Database database, Scanner s, User user) {
		if (userID==-9999) userID = user.getID();
		ArrayList<Rent> rents = new ArrayList<>();
		ArrayList<Integer> carIDs = new ArrayList<>();
		try {
			String select = "SELECT * FROM `rents` WHERE `User` = '"+userID+"';";
			ResultSet rs = database.getStatement().executeQuery(select);
			while (rs.next()) {
				Rent rent = new Rent();
				rent.setID(rs.getInt("ID"));
				carIDs.add(rs.getInt("Car"));
				rent.setDateTime(rs.getString("DateTime"));
				rent.setHours(rs.getInt("Hours"));
				rent.setTotal(rs.getDouble("Total"));
				rent.setStatus(rs.getInt("Status"));
				rents.add(rent);
			}
			
			String selectUser = "SELECT * FROM `users` WHERE `ID` = '"+userID+"';";
			ResultSet rs2 = database.getStatement().executeQuery(selectUser);
			rs2.next();
			User u = new Client();
			u.setID(rs2.getInt("ID"));
			u.setFirstName(rs2.getString("FirstName"));
			u.setLastName(rs2.getString("LastName"));
			u.setEmail(rs2.getString("Email"));
			u.setPhoneNumber(rs2.getString("PhoneNumber"));
			u.setPassword(rs2.getString("Password"));
		
			for (int j=0;j<rents.size();j++) {
				Rent r = rents.get(j);
				r.setUser(u);
				ResultSet rs3 = database.getStatement()
				.executeQuery("SELECT * FROM `cars` WHERE `ID` = '"+carIDs.get(j)+"';");
				rs3.next();
				Car car = new Car();
				car.setID(rs3.getInt("ID"));
				car.setBrand(rs3.getString("Brand"));
				car.setModel(rs3.getString("Model"));
				car.setColor(rs3.getString("Color"));
				car.setYear(rs3.getInt("Year"));
				car.setPrice(rs3.getDouble("Price"));
				car.setAvailable(rs3.getInt("Available"));
				r.setCar(car);
				
				System.out.println("ID:\t\t"+r.getID());
				System.out.println("Name:\t\t"+r.getUser().getFirstName()+" "+r.getUser().getLastName());
				System.out.println("Email:\t\t"+r.getUser().getEmail());
				System.out.println("Phone Number:\t"+r.getUser().getPhoneNumber());
				System.out.println("Car ID:\t\t"+r.getCar().getID());
				System.out.println("Car:\t\t"+r.getCar().getBrand()+" "+r.getCar().getModel()+" "+r.getCar().getColor());
				System.out.println("Date Time:\t"+r.getDateTime());
				System.out.println("Hours:\t\t"+r.getHours());
				System.out.println("Total:\t\t"+r.getTotal()+"৳");
				System.out.println("Status:\t\t"+r.getStatusToString());
				System.out.println("--------------------------");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
