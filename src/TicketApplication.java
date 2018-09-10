import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TicketApplication {
	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) throws IOException, ParseException, ClassNotFoundException, SQLException {
		
		
		int trainNo = promptNumber("Train Number");
		Date travelDate = promptDate();
		
		if (travelDate.compareTo(new Date()) > 0) {
			if (checkTrain(trainNo)) {
				int numOfPassengers = promptNumber("number of passengers");
				
				Train train = getTrain(trainNo);
				Ticket ticket = new Ticket(travelDate, train);

				for (int i = 1; i <= numOfPassengers; i++) {
					String name = promptName(i);
					int age = promptNumber("age");
					char gender = promptGender();

					ticket.addPassenger(name, age, gender);
				}

				ticket.writeTicket();
				
			} else {
				System.out.println("Train " + trainNo + " does not exist");
			} 
		}
		else {
			System.out.println("Travel Date is before current date");
		}
	}
	
	public static int promptNumber(String msg) {
		System.out.print("Please enter the " + msg + ": ");
		int number = in.nextInt();
		
		while (number < 1) {
			System.out.println("Please enter a positive number.");
			System.out.print("Please enter the " + msg + ": ");
			number = in.nextInt();
		}
		
		return number;
	}
	
	public static Date promptDate() throws ParseException {
		in.nextLine();
		System.out.print("Please enter the date you would like to travel (MM/DD/YYYY): ");
		String date = in.nextLine();
		
		return new SimpleDateFormat("MM/dd/yyyy").parse(date);
	}
	
	public static boolean checkTrain(int num) throws ClassNotFoundException, SQLException {
		TrainDAO dao = new TrainDAO();
		try {
			dao.findTrain(num);
			return true;
		} catch (SQLException e) {
			return false;
		} finally {
			dao.close();
		}
		
	}
	
	public static Train getTrain(int num) throws ClassNotFoundException, SQLException {
		TrainDAO dao = new TrainDAO();
		Train train = dao.findTrain(num);
		return train;
	}
	
	public static String promptName(int num) {
		System.out.print("Please enter the name for passenger " + num + ": ");
		in.nextLine();
		return in.nextLine();
	}
	
	public static char promptGender() {
		System.out.print("Please enter the gender (m for male, f for female): ");
		char gender = in.next().charAt(0);
		
		while(Character.toUpperCase(gender) != 'M' && Character.toUpperCase(gender) != 'F') {
			System.out.println("Please enter m for male or f for female");
			System.out.print("Please enter the gender (m for male, f for female): ");
			gender = in.next().charAt(0);
		}
		
		return Character.toUpperCase(gender);
	}
}
