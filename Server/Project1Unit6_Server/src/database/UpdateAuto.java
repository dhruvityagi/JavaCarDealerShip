package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

import model.Automobile;

import adapter.BuildAuto;
/*Update db methods*/
public class UpdateAuto {
	private DataBaseBasic d;
	
	public UpdateAuto() {
		d = new DataBaseBasic();
	}
	/*Update make*/
	public void updateMakeName(String oldName, String newName) {
	    String sql = "UPDATE "+d.getTableNameAutomobile()+
          			 " SET MAKE_MODEL = '"+newName+"' WHERE MAKE_MODEL = '" +oldName+"'";
	    try {
			d.executeUpdate(d.getConn(), sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sql = "UPDATE "+d.getTableNameProperties()+
			 " SET MAKE_MODEL = '"+newName+"' WHERE MAKE_MODEL = '" +oldName+"'";
		try {
			d.executeUpdate(d.getConn(), sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ReadAutoToAutomobile r = new ReadAutoToAutomobile();
		Automobile a1 = new Automobile();
		BuildAuto obj =  new BuildAuto();
		a1 = r.readAutomobile(newName, a1);
		obj.getA1().remove(oldName);
		obj.getA1().put(newName, a1);
	}
	/*Update base price*/
	public void updateBasePrice(String makeModel, String string) {
	    String sql = "UPDATE "+d.getTableNameAutomobile()+
          			 " SET BASE_PRICE = '"+string+"' WHERE MAKE_MODEL = '" +makeModel+"'";
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
	/*Update Option set name*/
	public void updateOptionSetName(String makeModel, String osNameOld, String osNameNew) {
	    String sql = "UPDATE "+d.getTableNameProperties()+
          			 " SET OPTION_SET_NAME = '"+osNameNew+
          			 "' WHERE MAKE_MODEL = '" +makeModel+"' AND OPTION_SET_NAME = '"+osNameOld+"'";
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
	/*Update option name*/
	public void updateOptionName(String makeModel, String optionSet, String oNameOld, String oNameNew) {
		String newOption="";
		String sql = "SELECT OPTION_NAME FROM " + d.getTableNameProperties()+
					 " where MAKE_MODEL='"+ makeModel+"' AND OPTION_SET_NAME = '"+optionSet+"'";
		try {
			Statement stmt = d.getConn().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		      while(rs.next()){
		         //Retrieve by column name
		    	 String name = rs.getString("OPTION_NAME");
		         StringTokenizer st1 = new StringTokenizer(name,":");
		         while(st1.hasMoreTokens()) {
		        	 String temp = st1.nextToken();
		        	 if(temp.equalsIgnoreCase(oNameOld)) {
		        		 if(newOption.equals(""))
		        			 newOption = oNameNew;
		        		 else
		        			 newOption = newOption+":"+oNameNew;
		        	 }
		        	 else {
		        		 if(newOption.equals(""))
		        			 newOption = temp;
		        		 else 
		        			 newOption = newOption+":"+temp;
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
          			 " SET OPTION_NAME = '"+newOption+
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
	/*Update option price*/
	public void updateOptionPrice(String makeModel, String optionSet, String oName, String oPriceNew) {
		String newOption="";
		String sql = "SELECT OPTION_NAME, OPTION_PRICE FROM "+d.getTableNameProperties()+
					 " where MAKE_MODEL='"+ makeModel+"' AND OPTION_SET_NAME = '"+optionSet+"'";
		try {
			Statement stmt = d.getConn().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		      while(rs.next()){
		         //Retrieve by column name
		    	 String name = rs.getString("OPTION_NAME");
		    	 String price = rs.getString("OPTION_PRICE");
		         StringTokenizer st1 = new StringTokenizer(name,":");
		         StringTokenizer st2 = new StringTokenizer(price,":");
		         while(st1.hasMoreTokens() && st2.hasMoreTokens()) {
		        	 String temp = st1.nextToken();
		        	 if(temp.equalsIgnoreCase(oName)) {
		        		 if(newOption.equals(""))
		        			 newOption = oPriceNew;
		        		 else
		        			 newOption = newOption+":"+oPriceNew;
		        	 }
		        	 else {
		        		 if(newOption.equals(""))
		        			 newOption = st2.nextToken();
		        		 else 
		        			 newOption = newOption+":"+st2.nextToken();
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
          			 " SET OPTION_PRICE = '"+newOption+
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
