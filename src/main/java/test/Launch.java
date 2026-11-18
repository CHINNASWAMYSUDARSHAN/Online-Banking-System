package test;

import java.sql.Connection;
import java.sql.Statement;

import util.DBConnection;

public class Launch {

	public static void main(String[] args) {
		Connection con = DBConnection.getConnection();
		 if (con != null) {
	            System.out.println("DB Connected Successfully");
	        } else {
	            System.out.println("DB Connection Failed");
	        }
	}

}
