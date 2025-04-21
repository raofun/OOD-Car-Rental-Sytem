package Model;

import java.util.Scanner;

import Controller.ChangePassword;
import Controller.EditUserData;
import Controller.Quit;
import Controller.RentCar;
import Controller.ReturnCar;
import Controller.ShowUserRents;
import Controller.ViewCars;

public class Client extends User {
	
	private Operation[] operations = new Operation[] {
			new ViewCars(),
			new RentCar(),
			new ReturnCar(),
			new ShowUserRents(-9999),
			new EditUserData(),
			new ChangePassword(),
			new Quit()};
	
	public Client() {
		super();
	}

	@Override
	public void showList(Database database, Scanner s) {
		System.out.println("\n1. View Cars");
		System.out.println("2. Rent Car");
		System.out.println("3. Return Car");
		System.out.println("4. Show My Rents");
		System.out.println("5. Edit My Data");
		System.out.println("6. Change Password");
		System.out.println("7. Quit\n");
		
		int i = s.nextInt();
		if (i<1 || i>7) {
			showList(database, s);
			return;
		}
		operations[i-1].operation(database, s, this);
		if (i!=7) showList(database, s);
	}
	
}
