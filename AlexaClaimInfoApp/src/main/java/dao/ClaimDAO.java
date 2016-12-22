package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClaimDAO {
	private static final Logger log = LoggerFactory.getLogger(ClaimDAO.class);

	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/claims";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root";
	private static Connection con = null;

	public HashMap<Integer, String> fetchClaims() {
		System.out.println("Starting fetchClaims");
		HashMap<Integer, String> userClaimMap = new HashMap<Integer, String>();
		ResultSet rs = null;
		Statement statement = null;
		Connection connection = null;
		try {
			connection = getDBConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from user_claims where usercode='Midhun'");
			int index = 0;
			while (rs.next()) {
				System.out.println("user_claim  claimnumber : " + rs.getString("claimId"));
				userClaimMap.put(++index, rs.getString("claimId"));
			}
			System.out.println("Completed fetchClaims");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
			}
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException se) {
			}
		}
		return userClaimMap;
	}

	public String fetchClaimDetails(String claimNumber) {
		System.out.println("Starting fetchClaimDetails for ClaimNumber " + claimNumber);
		HashMap<Integer, String> userClaimMap = new HashMap<Integer, String>();
		ResultSet rs = null;
		Statement statement = null;
		Connection connection = null;
		String claimDetails = null;
		String claimStatus = null;
		String claimDiscription = null;
		try {
			connection = getDBConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from claim_details where claimId  = '" + claimNumber + "'");
			while (rs.next()) {
				claimStatus = rs.getString("claimStatus");
				claimDiscription = rs.getString("description");
			}
			claimDetails = "your Claim Status is " + claimStatus + ". Claim Details is " + claimDiscription;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
			}
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException se) {
			}
		}
		System.out.println("Completed fetchClaimDetails : " + claimDetails);
		return claimDetails;
	}

	private Connection getDBConnection() throws ClassNotFoundException, SQLException {
		if (con == null) {
			Class.forName(DB_DRIVER);
			con = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
		}
		return con;
	}

}
