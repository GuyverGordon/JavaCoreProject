import java.sql.*;

public class TrainDAO {
	private static final String DRIVER_NAME = "OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "hr";
	private static final String PASSWORD = "hr";
	private static Connection conn;
	
	public TrainDAO() throws SQLException, ClassNotFoundException {
		Class.forName("oracle.jdbc.driver." + DRIVER_NAME);
		conn = DriverManager.getConnection(DB_URL,
						USERNAME, PASSWORD);
	}
	
	public Train findTrain(int trainNo) throws SQLException {
		ResultSet rs = conn.createStatement().executeQuery("select * from trains "
				+ "where train_no = " + trainNo);
		rs.next();
		
		// 1 = train number, 2 = train name, 3 = source, 4 = destination, 5 = ticket price
		return new Train( rs.getInt(1), rs.getString(2),
				rs.getString(3), rs.getString(4), rs.getDouble(5));
	}
	
	public void close() throws SQLException {
		conn.close();
	}
}
