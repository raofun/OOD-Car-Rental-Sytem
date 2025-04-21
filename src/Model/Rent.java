package Model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Rent {
	
	private int ID;
	private User user;
	private Car car;
	private LocalDateTime dateTime;
	private int hours;
	private double total;
	private int status;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm");
	
	//Status 0 ==> running
	//       1 ==> returned
	
	public Rent() {
		dateTime = LocalDateTime.now();
	}
	
	public int getID() {
		return ID;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Car getCar() {
		return car;
	}
	
	public void setCar(Car car) {
		this.car = car;
	}
	
	public String getDateTime() {
		return formatter.format(dateTime);
	}
	
	public LocalDateTime getLocalDateTime() {
		return dateTime;
	}
	
	public void setDateTime(String dateTimeString) {
		this.dateTime = LocalDateTime.parse(dateTimeString, formatter);
	}
	
	public int getHours() {
		return hours;
	}
	
	public void setHours(int hours) {
		this.hours = hours;
	}
	
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
	public int getStatus() {
		return status;
	}
	
	public String getStatusToString() {
		long passedHours = ChronoUnit.HOURS.between(dateTime, LocalDateTime.now());
		String status = "";
		if (getStatus()!=1 && passedHours<getHours()) {
			status = "On Rent";
		} else if (getStatus()!=1 && passedHours>getHours()) {
			status = "Delayed";
		} else if (getStatus()==1) {
			status = "Returned";
		}
		return status;
	}
	
	public long getDelayedHours() {
		long passedHours = ChronoUnit.HOURS.between(dateTime, LocalDateTime.now());
		return passedHours-hours;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}

}
