package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringTokenizer;

import adapter.BuildAuto;

import model.Automobile;

public class ReadAutoToAutomobile {
	private DataBaseBasic d;
	
	public ReadAutoToAutomobile() {
		d = new DataBaseBasic();
	}
	/*Read data from db and add to linkedhashmap*/
	public BuildAuto populateLinkedHashMapFromDB(BuildAuto obj) {
		
		String sql = "SELECT MAKE_MODEL FROM "+d.getTableNameAutomobile();
		try {
			Statement stmt = d.getConn().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				//Retrieve by column name
				Automobile a1 = new Automobile();
				a1 = readAutomobile(rs.getString("MAKE_MODEL"), a1);
				obj.getA1().put(rs.getString("MAKE_MODEL"), a1);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return obj;
	}
	/*REad from db to automobile object*/
	public Automobile readAutomobile(String makeModel, Automobile a1) {
		String sql = "SELECT MAKE_MODEL, BASE_PRICE FROM "+d.getTableNameAutomobile() +
					 " where MAKE_MODEL='"+ makeModel+"'";
		try {
		Statement stmt = d.getConn().createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		 while(rs.next()){
		    //Retrieve by column name
			 a1.setMake(rs.getString("MAKE_MODEL"));
			 a1.setBasePrice(Float.parseFloat(rs.getString("BASE_PRICE")));
		 }
		 rs.close();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} 
		
		sql = "SELECT OPTION_SET_NAME, OPTION_NAME, OPTION_PRICE FROM "+d.getTableNameProperties() +
		 " where MAKE_MODEL='"+ makeModel+"'";
		try {
			Statement stmt = d.getConn().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			int i = 0;
			while(rs.next()){
				//Retrieve by column name
				a1.addOptionSet();
				a1.setOptionSetName(rs.getString("OPTION_SET_NAME"), i);
				String option = rs.getString("OPTION_NAME");
				String price = rs.getString("OPTION_PRICE");
				StringTokenizer st1 = new StringTokenizer(option,":");
		        StringTokenizer st2 = new StringTokenizer(price,":");
		        int j = 0;
		         while(st1.hasMoreTokens() && st2.hasMoreTokens()) {
		        	 a1.addOption(i);
		        	 a1.setOptionName(st1.nextToken(), j, i);
		        	 a1.setOptionPrice(Float.parseFloat(st2.nextToken()), j, i);
		        	 j++;
		         }
		         i++;
		      }
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return a1;
	}
	/*Parse properties file and load to db*/
	public void parsePropertiesObject(Properties props) {
		String valueAuto = "";
		String valueProp = "";
		AddCarRecord add = new AddCarRecord();
		//"'FordZTW','Color','Red:Blue:Green','0:0:0'"
		valueAuto = "'"+props.getProperty("CarModel")+props.getProperty("CarMake")+"'"+
					",'"+props.getProperty("BasePrice")+"'";
		
		valueProp = "'"+props.getProperty("CarModel")+props.getProperty("CarMake")+"'"+
					",'"+props.getProperty("Option1")+"'"+
					",'"+props.getProperty("OptionValue1a")+":"+
						props.getProperty("OptionValue1b")+":"+
						props.getProperty("OptionValue1c")+":"+
						props.getProperty("OptionValue1d")+":"+
						props.getProperty("OptionValue1e")+":"+
						props.getProperty("OptionValue1f")+":"+
						props.getProperty("OptionValue1g")+":"+
						props.getProperty("OptionValue1h")+":"+
						props.getProperty("OptionValue1i")+":"+
						props.getProperty("OptionValue1j")+"'"+
					",'"+props.getProperty("OptionPrice1a")+":"+
						props.getProperty("OptionPrice1b")+":"+
						props.getProperty("OptionPrice1c")+":"+
						props.getProperty("OptionPrice1d")+":"+
						props.getProperty("OptionPrice1e")+":"+
						props.getProperty("OptionPrice1f")+":"+
						props.getProperty("OptionPrice1g")+":"+
						props.getProperty("OptionPrice1h")+":"+
						props.getProperty("OptionPrice1i")+":"+
						props.getProperty("OptionPrice1j")+"'";
		add.addAutomobile(valueAuto, valueProp,
						"'"+props.getProperty("CarModel")+props.getProperty("CarMake")+"'");
		
		valueAuto = "";
		valueProp = "";
		valueAuto = "'"+props.getProperty("CarModel")+props.getProperty("CarMake")+"'"+
		",'"+props.getProperty("BasePrice")+"'";
		valueProp = "'"+props.getProperty("CarModel")+props.getProperty("CarMake")+"'"+
		",'"+props.getProperty("Option2")+"'"+
		",'"+props.getProperty("OptionValue2a")+":"+
			props.getProperty("OptionValue2b")+"'"+
		",'"+props.getProperty("OptionPrice2a")+":"+
			props.getProperty("OptionPrice2b")+"'";
		add.addAutomobile(valueAuto, valueProp,
				"'"+props.getProperty("CarModel")+props.getProperty("CarMake")+"'");
		
		valueAuto = "";
		valueProp = "";
		valueAuto = "'"+props.getProperty("CarModel")+props.getProperty("CarMake")+"'"+
		",'"+props.getProperty("BasePrice")+"'";
		valueProp = "'"+props.getProperty("CarModel")+props.getProperty("CarMake")+"'"+
		",'"+props.getProperty("Option3")+"'"+
		",'"+props.getProperty("OptionValue3a")+":"+
			props.getProperty("OptionValue3b")+":"+
			props.getProperty("OptionValue3c")+"'"+
		",'"+props.getProperty("OptionPrice3a")+":"+
			props.getProperty("OptionPrice3b")+":"+
			props.getProperty("OptionPrice3c")+"'";
		add.addAutomobile(valueAuto, valueProp,
				"'"+props.getProperty("CarModel")+props.getProperty("CarMake")+"'");
		
		valueAuto = "";
		valueProp = "";
		valueAuto = "'"+props.getProperty("CarModel")+props.getProperty("CarMake")+"'"+
		",'"+props.getProperty("BasePrice")+"'";
		valueProp = "'"+props.getProperty("CarModel")+props.getProperty("CarMake")+"'"+
		",'"+props.getProperty("Option4")+"'"+
		",'"+props.getProperty("OptionValue4a")+":"+
			props.getProperty("OptionValue4b")+"'"+
		",'"+props.getProperty("OptionPrice4a")+":"+
			props.getProperty("OptionPrice4b")+"'";
		add.addAutomobile(valueAuto, valueProp,
				"'"+props.getProperty("CarModel")+props.getProperty("CarMake")+"'");
		
		valueAuto = "";
		valueProp = "";
		valueAuto = "'"+props.getProperty("CarModel")+props.getProperty("CarMake")+"'"+
		"'"+props.getProperty("BasePrice")+"'";
		valueProp = "'"+props.getProperty("CarModel")+props.getProperty("CarMake")+"'"+
		",'"+props.getProperty("Option5")+"'"+
		",'"+props.getProperty("OptionValue5a")+":"+
			props.getProperty("OptionValue5b")+"'"+
		",'"+props.getProperty("OptionPrice5a")+":"+
			props.getProperty("OptionPrice5b")+"'";
		add.addAutomobile(valueAuto, valueProp,
				"'"+props.getProperty("CarModel")+props.getProperty("CarMake")+"'");
	}
}
