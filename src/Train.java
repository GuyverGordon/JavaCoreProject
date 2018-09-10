
public class Train {
	private int trainNo;
	private String trainName;
	private String source;
	private String destination;
	private double ticketPrice;
	
	public Train (int trainNo, String name, String source, String destination, double price) {
		setTrainNo(trainNo);
		setTrainName(name);
		setSource(source);
		setDestination(destination);
		setTicketPrice(price);
	}

	public int getTrainNo() { return trainNo; }
	public String getTrainName() { return trainName; }
	public String getSource() { return source; }
	public String getDestination() { return destination; }
	public double getTicketPrice() { return ticketPrice; }

	public void setTrainNo(int trainNo) {
		this.trainNo = trainNo;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	
	
}
