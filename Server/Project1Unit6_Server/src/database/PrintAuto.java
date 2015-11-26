package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;
/*Print db methods*/
public class PrintAuto {
	private DataBaseBasic d;
	
	public PrintAuto() {
		d = new DataBaseBasic();
	}
	/*Print available cars*/
	public void displayCarsAvailable() {
		String sql = "SELECT MAKE_MODEL FROM "+d.getTableNameAutomobile();
		System.out.println("Make/Model Records in DB:");
		try {
			Statement stmt = d.getConn().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				//Retrieve by column name
				String name = rs.getString("MAKE_MODEL");
				System.out.println(name);
			}
			rs.close();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} 
	}
	/*Print option set of a car*/
	public void displayOptionSet(String car) {
		String sql = "SELECT OPTION_SET_NAME FROM "+d.getTableNameProperties() +
					 " where MAKE_MODEL='"+ car+"'";
		System.out.println("Properties availbale for car "+car+" in DB:");
		try {
			Statement stmt = d.getConn().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				//Retrieve by column name
				String name = rs.getString("OPTION_SET_NAME");
				System.out.println(name);
			}
			rs.close();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} 
	}
	/*Print options of a car for an optionset*/
	public void displayOption(String car, String osName) {
		String sql = "SELECT OPTION_NAME, OPTION_PRICE FROM "+d.getTableNameProperties() +
					 " where MAKE_MODEL='"+ car+"' AND OPTION_SET_NAME = '"+osName+"'" ;
		System.out.println("Properties availbale for car "+car+" in DB:");
		try {
			Statement stmt = d.getConn().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				//Retrieve by column name
				String name = rs.getString("OPTION_NAME");
				String price = rs.getString("OPTION_PRICE");
				StringTokenizer st1 = new StringTokenizer(name,":");
				StringTokenizer st2 = new StringTokenizer(price,":");
				while(st1.hasMoreTokens() && st2.hasMoreTokens())
					System.out.println(st1.nextToken()+" @ "+st2.nextToken());
			}
			rs.close();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} 
	}
	/*Print entire automobile with all its features*/
	public void printAutomobile(String makeModel) {
		String sql = "SELECT MAKE_MODEL, BASE_PRICE FROM "+d.getTableNameAutomobile() +
					 " where MAKE_MODEL="+ makeModel;
		try {
		Statement stmt = d.getConn().createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		 while(rs.next()){
		    //Retrieve by column name
			 String name = rs.getString("MAKE_MODEL");
			 String price = rs.getString("BASE_PRICE");
		    System.out.println("Make/Model:"+name);
		    System.out.println("Base Price:"+price);
		 }
		 rs.close();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} 
		
		sql = "SELECT OPTION_SET_NAME, OPTION_NAME, OPTION_PRICE FROM "+d.getTableNameProperties() +
		 " where MAKE_MODEL="+ makeModel;
		try {
			Statement stmt = d.getConn().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				//Retrieve by column name
				String setName = rs.getString("OPTION_SET_NAME");
				String option = rs.getString("OPTION_NAME");
				String price = rs.getString("OPTION_PRICE");
				System.out.println("Option Set Name:"+setName);
				StringTokenizer st1 = new StringTokenizer(option,":");
		        StringTokenizer st2 = new StringTokenizer(price,":");
		         while(st1.hasMoreTokens() && st2.hasMoreTokens()) {
		        	 System.out.println(st1.nextToken()+" @ "+ st2.nextToken());
		         }
		      }
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
