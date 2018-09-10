import java.text.*;
import java.util.*;
import java.io.*;

public class Ticket {
	private static int counter = 100;
	private String pnr;
	private Date travelDate;
	private Train train;
	private TreeMap<Passenger, Double> passengers;
	
	public Ticket(Date date, Train train) {
		passengers = new TreeMap<>();
		setTravelDate(date);
		setTrain(train);
		pnr = generatePNR();
	}

	public static int getCounter() { return counter; }
	public String getPnr() { return pnr; }
	public Date getTravelDate() { return travelDate; }
	public Train getTrain() { return train; }
	public TreeMap<Passenger, Double> getPassengers() { return passengers; }

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public void setPassengers(TreeMap<Passenger, Double> passengers) {
		this.passengers = passengers;
	}
	
	// generate PNR using the first letter of the train's source location, destination
	// location, the travel date (year, month, day) and the increasing counter
	private String generatePNR() {
		SimpleDateFormat sdForm = new SimpleDateFormat("yyyyMMdd");
		StringBuffer sb = new StringBuffer();
		sb.append(train.getSource().charAt(0));
		sb.append(train.getDestination().charAt(0));
		sb.append("_");
		sb.append(sdForm.format(travelDate));
		sb.append("_");
		sb.append(counter++);
		
		return sb.toString();
	}
	
	// apply discount based on age or gender and return double passenger fare
	private double calcPassengerFare(Passenger passenger) {
		if (passenger.getAge() <= 12)
			return 0.5 * train.getTicketPrice();
		else if (passenger.getAge() >= 60)
			return 0.6 * train.getTicketPrice();
		else if (passenger.getGender() == 'F')
			return 0.75 * train.getTicketPrice();
		
		return train.getTicketPrice();
	}
	
	// adds passenger and price to ticket
	public void addPassenger(String name, int age, char gender) {
		passengers.put(new Passenger(name, age, gender), 
				calcPassengerFare(new Passenger(name, age, gender)));
	}
	
	// returns the sum of all ticket fares
	private double calculateTotalTicketPrice() {
		double sum = 0.0;
		for(double fare : passengers.values()){
			sum += fare;
		}
		
		return sum;
	}
	
	
	private StringBuilder generateTicket() {
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdForm = new SimpleDateFormat("MM/dd/yyyy");
		NumberFormat cf = NumberFormat.getCurrencyInstance();
		int ticketLength = 50;
		
		for (int i = 0; i < ticketLength; i++)
			sb.append("-");
		sb.append(System.getProperty("line.separator"));
		
		String currentStr = "| PNR         : " + pnr;
		sb.append(currentStr + getEndSpace(currentStr.length(), ticketLength));
		sb.append(System.getProperty("line.separator"));
		
		currentStr = "| Train no    : " + train.getTrainNo();
		sb.append(currentStr + getEndSpace(currentStr.length(), ticketLength));
		sb.append(System.getProperty("line.separator"));
		
		currentStr = "| Train name  : " + train.getTrainName();
		sb.append(currentStr + getEndSpace(currentStr.length(), ticketLength));
		sb.append(System.getProperty("line.separator"));
		
		currentStr = "| From        : " + train.getSource();
		sb.append(currentStr + getEndSpace(currentStr.length(), ticketLength));
		sb.append(System.getProperty("line.separator"));
		
		currentStr = "| To          : " + train.getDestination();
		sb.append(currentStr + getEndSpace(currentStr.length(), ticketLength));
		sb.append(System.getProperty("line.separator"));
		
		currentStr = "| Travel date : " + sdForm.format(travelDate);
		sb.append(currentStr + getEndSpace(currentStr.length(), ticketLength));
		sb.append(System.getProperty("line.separator"));
		
		currentStr = "| ";
		sb.append(currentStr + getEndSpace(currentStr.length(), ticketLength));
		sb.append(System.getProperty("line.separator"));
		
		currentStr = "| Passengers :";
		
		
		currentStr = "| " + String.format("%-21s", "Name") + String.format("%-7s", "Age") + String.format("%-9s", "Gender") + "Fare";
		sb.append(currentStr + getEndSpace(currentStr.length(), ticketLength));
		
		for(Map.Entry<Passenger, Double> entry : passengers.entrySet()) {
			sb.append(System.getProperty("line.separator"));
			
			String gender = 
					new String(entry.getKey().getGender() == 'M' ? "male" : "female");
			
			currentStr = "| " 
					+ String.format("%-21s", entry.getKey().getName()) + String.format("%-7d", entry.getKey().getAge()) 
					+ String.format("%-9s", gender) + cf.format(entry.getValue());
			
			sb.append(currentStr + getEndSpace(currentStr.length(), ticketLength));
		}
		
		sb.append(System.getProperty("line.separator"));
		
		currentStr = "| Total Price : " + cf.format(calculateTotalTicketPrice());
		sb.append(currentStr + getEndSpace(currentStr.length(), ticketLength));
		
		sb.append(System.getProperty("line.separator"));
		for (int i = 0; i < ticketLength; i++)
			sb.append("-");
		
		
		return sb;
	}
	
	private String getEndSpace(int strLen, int ticketSize) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < ticketSize - strLen; i++) {
			if (i < ticketSize - strLen - 1)
				sb.append(" ");
			else
				sb.append("|");
		}
		
		return sb.toString();
	}
	
	public void writeTicket() throws IOException {
		String filename = pnr + ".txt";
		FileOutputStream fo = new FileOutputStream("d:\\" + filename);
		String str = generateTicket().toString();
		byte[] b = str.getBytes();
		try {
			fo.write(b);
			System.out.println("Ticket should be written to the file with "
					+ "filename " + filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		fo.close();
	}
	
}
