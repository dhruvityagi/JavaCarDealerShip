package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

import model.Automobile;
import adapter.BuildAuto;

/*Delete options related to db*/
public class DeleteDB {
	private DataBaseBasic d;
	
	//Constructor
	public DeleteDB() {
		d = new DataBaseBasic();
	}
	/*Delete table*/
	public void deleteTable() {
		// Drop the table
	    try {
		    String dropString = "DROP TABLE " + d.getTableNameProperties();
			d.executeUpdate(d.getConn(), dropString);
			System.out.println("Dropped the table");
	    } catch (SQLException e) {
			System.out.println("ERROR: Could not drop the table");
			e.printStackTrace();
			return;
		}
		try {
		    String dropString = "DROP TABLE " + d.getTableNameAutomobile();
			d.executeUpdate(d.getConn(), dropString);
			System.out.println("Dropped the table");
	    } catch (SQLException e) {
			System.out.println("ERROR: Could not drop the table");
			e.printStackTrace();
			return;
		}
	}
	/*Delete Car record*/
	public void deleteCarRecord(String makeModel) {
	      String sql = "DELETE FROM "+d.getTableNameProperties()+
	                   " WHERE MAKE_MODEL = '"+makeModel+"'";
	      try {
			d.executeUpdate(d.getConn(), sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sql = "DELETE FROM "+d.getTableNameAutomobile()+
		      " WHERE MAKE_MODEL = '"+makeModel+"'";
		try {
			d.executeUpdate(d.getConn(), sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BuildAuto obj =  new BuildAuto();
		obj.getA1().remove(makeModel);
	}
	/*Delete option set of a car*/
	public void deleteOptionSet(String makeModel, String optionSet) {
	      String sql = "DELETE FROM "+d.getTableNameProperties()+
	                   " WHERE MAKE_MODEL = '"+makeModel+
	                   "' AND OPTION_SET_NAME = '"+optionSet+"'";
		try {
			d.executeUpdate(d.getConn(), sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ReadAutoToAutomobile r = new ReadAutoToAutomobile();
		Automobile a1 = new Automobile();
		BuildAuto obj =  new BuildAuto();
		a1 = r.readAutomobile(makeModel, a1);
		obj.getA1().remove(makeModel);
		obj.getA1().put(makeModel, a1);
	}
	/*Delete an option*/
	public void deleteOption(String makeModel, String optionSet, String option) {
	      String sql = "SELECT OPTION_NAME, OPTION_PRICE FROM "+d.getTableNameProperties()+
	                   " WHERE MAKE_MODEL = '"+makeModel+
	                   "' AND OPTION_SET_NAME = '"+optionSet+"'";
		Statement stmt;
		String newOption="";
		String newPrice="";
		try {
			stmt = d.getConn().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		      while(rs.next()){
		         //Retrieve by column name
		    	 String name = rs.getString("OPTION_NAME");
		         String price  = rs.getString("OPTION_PRICE");
		         StringTokenizer st1 = new StringTokenizer(name,":");
		         StringTokenizer st2 = new StringTokenizer(price,":");
		         while(st1.hasMoreTokens() && st2.hasMoreTokens()) {
		        	 String temp = st1.nextToken();
		        	 if(temp.equalsIgnoreCase(option)) {
		        		 st2.nextToken();
		        	 }
		        	 else {
		        		 if(newPrice.equalsIgnoreCase("")) {
		        			 newPrice = st2.nextToken();
			        		 newOption = temp;
		        		 }
		        		 else {
			        		 newPrice = newPrice+":"+st2.nextToken();
			        		 newOption = newOption+":"+temp;
		        		 }
		        	 }
		         }
		      }
		      rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*Update the db*/
	    sql = "UPDATE "+d.getTableNameProperties()+
          			 " SET OPTION_PRICE = '"+newPrice+"', OPTION_NAME = '" + newOption+
          			 "' WHERE MAKE_MODEL = '" +makeModel+"' AND OPTION_SET_NAME = '"+optionSet+"'";
	    try {
			d.executeUpdate(d.getConn(), sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ReadAutoToAutomobile r = new ReadAutoToAutomobile();
		Automobile a1 = new Automobile();
		BuildAuto obj =  new BuildAuto();
		a1 = r.readAutomobile(makeModel, a1);
		obj.getA1().remove(makeModel);
		obj.getA1().put(makeModel, a1);
	}
}
