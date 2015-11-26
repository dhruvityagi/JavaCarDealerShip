package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*Add car record to database*/
public class AddCarRecord {
	private DataBaseBasic d;
	
	public AddCarRecord() {
		d = new DataBaseBasic();
	}
	/*Add car record to database*/
	public void addAutomobile(String valueAuto, String valueProp, String car) {
		String sql = "SELECT MAKE_MODEL from "+d.getTableNameAutomobile() +
		 			 " where MAKE_MODEL="+ car;
		try {
			Statement stmt = d.getConn().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(!rs.absolute(1)){
				String sqlAuto = "INSERT INTO " + d.getTableNameAutomobile()+ 
				  " VALUES (" + valueAuto + ")";
				//System.out.println(sqlAuto);
				try {
					d.executeUpdate(d.getConn(), sqlAuto);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
}
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		String sqlProp = "INSERT INTO "+d.getTableNameProperties()+ 
		 				 " (MAKE_MODEL,OPTION_SET_NAME,OPTION_NAME,OPTION_PRICE) VALUES (" + valueProp + ")";
		//System.out.println(sqlProp);
		try {
			d.executeUpdate(d.getConn(), sqlProp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
