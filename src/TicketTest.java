import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TicketTest {

	public static void main(String[] args) throws ClassNotFoundException,
		SQLException, IOException {
		
		//testDatabase();
		testTicketClass();
		
	}
	
	public static void testDatabase() throws ClassNotFoundException, SQLException {
		TrainDAO dao = new TrainDAO();
		Train train = dao.findTrain(1001);
		System.out.println(train.getTrainNo() + ", " + train.getTrainName() + ", " 
				+ train.getSource() + ", " + train.getDestination() + ", "
				+ train.getTicketPrice());
	}
	
	public static void testTicketClass() throws ClassNotFoundException, 
		SQLException, IOException {
		
		Date date = new Date();
		TrainDAO dao = new TrainDAO();
		Train train = dao.findTrain(1001);
		Passenger p1 = new Passenger("Jonathan", 36, 'M');
		Passenger p2 = new Passenger("Melissa", 34, 'F');
		
		Ticket t = new Ticket(date, train);
		t.addPassenger("Jonathan", 36, 'M');
		t.addPassenger(p2.getName(), p2.getAge(), p2.getGender());
		System.out.println("PNR: " + t.getPnr());
		System.out.println("Travel Date: " + t.getTravelDate());
		System.out.println("Passenger List: ");
		System.out.println("Jonathan, " + t.getPassengers().get(p1));
		System.out.println(p2.getName() + ", " + t.getPassengers().get(p2));
		
		SimpleDateFormat sdForm = new SimpleDateFormat("MM/dd/yyyy");
		System.out.println(t.getTravelDate());
		System.out.println(sdForm.format(t.getTravelDate()));
		
		t.writeTicket();
		
	}

}
