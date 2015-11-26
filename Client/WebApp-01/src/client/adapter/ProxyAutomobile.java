package client.adapter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import client.exception.AutoException;
import client.util.Util;
import model.*;

/*class ProxyAutomobile
 * Builds, prints, updates and loads Automobile object*/
public abstract class ProxyAutomobile extends AutoException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Map<String,Automobile> a1 = new LinkedHashMap<String,Automobile>();
	
	/*Given a text file name a method called BuildAuto can be written to build an instance of  
	Automobile. This method does not have to return the Auto instance. */
	public void BuildAuto(String filename) {
		/*Build automobile object*/
		//a1 = new Automobile();
		Util readFile = new Util();
		/*Read the file to Automotive object*/
		Automobile temp = new Automobile();
		temp = readFile.buildAutoObject(filename, temp);
		a1.put(temp.getMake()+temp.getModel(), temp);
	}
	/*This function searches and prints the properties of a given modelname. */
	public void printAuto(String Modelname, String Makename) {
		if(a1.containsKey(Modelname+Makename)) {
			a1.get(Modelname+Makename).print();
		}
		else { /*DO nothing*/}
	}
	/*This function updates option set name. */
	public void updateOptionSetName(String Make,String Modelname, String OptionSetname, String newName) {
		int index = a1.get(Make+Modelname).findOptionSet(OptionSetname);
		if(index == -1)
			System.out.println("Option not found");
		else
			a1.get(Make+Modelname).setOptionSetName(newName, index);
	}
	/*This function searches the Model for a given OptionSet and Option name, and sets the optionname  to OptionnameNew. 
	 * OptionSetName and option name are sent separated by :*/
	public void updateOptionName(String make,String Modelname, String Optionname, String OptionnameNew) {
		String temp[] = Optionname.split(":");
		a1.get(make+Modelname).updateOptionName1(temp[0], temp[1],OptionnameNew);
	}
	/*This function updates price. 
	 * OptionSetName and option name are sent separated by :*/
	public void updateOptionPrice(String Make,String Modelname, String Optionname, float newprice) {
		String temp[] = Optionname.split(":");
		a1.get(Make+Modelname).updateOptionPrice1(temp[0], temp[1], newprice);
	}
	/*This function loads users choices. */
	public void loadChoice(String make, String model) {
		Util a = new Util();
		int indexOpset = 0;
		//System.out.println(model+make);//.getOpsetGrp());
		for(indexOpset = 0; indexOpset < a1.get(model+make).getOpsetGrp().size(); indexOpset++) {
			if(a1.get(model+make).getOpset(indexOpset) != null) {
				try {
					a.readChoice(a1.get(model+make),indexOpset);
				} catch (AutoException e) {
					System.out.println("Choice selection aborted due to wrong input");
				}
			}
			else
				break;
			//indexOpset++;
		}
	}
	
	public Automobile parsePropertiesObject(Properties props, Automobile a1) {
		a1.setMake(props.getProperty("CarMake"));
		if(!a1.getMake().equals(null)) {
			a1.setModel(props.getProperty("CarModel"));
			a1.setBasePrice(Float.parseFloat(props.getProperty("BasePrice")));
			/*Option Color*/
			a1.addOptionSet();
			a1.setOptionSetName(props.getProperty("Option1"), 0);
			if(props.getProperty("Option1").equals(null)) {}
			else {
				a1.addOption(0);
				a1.setOptionName(props.getProperty("OptionValue1a"), 0, 0);
				a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice1a")), 0, 0);
				a1.addOption(0);
				a1.setOptionName(props.getProperty("OptionValue1b"), 1, 0);
				a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice1b")), 1, 0);
				a1.addOption(0);
				a1.setOptionName(props.getProperty("OptionValue1c"), 2, 0);
				a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice1c")), 2, 0);
				a1.addOption(0);
				a1.setOptionName(props.getProperty("OptionValue1d"), 3, 0);
				a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice1d")), 3, 0);
				a1.addOption(0);
				a1.setOptionName(props.getProperty("OptionValue1e"), 4, 0);
				a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice1e")), 4, 0);
				a1.addOption(0);
				a1.setOptionName(props.getProperty("OptionValue1f"), 5, 0);
				a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice1f")), 5, 0);
				a1.addOption(0);
				a1.setOptionName(props.getProperty("OptionValue1g"), 6, 0);
				a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice1g")), 6, 0);
				a1.addOption(0);
				a1.setOptionName(props.getProperty("OptionValue1h"), 7, 0);
				a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice1h")), 7, 0);
				a1.addOption(0);
				a1.setOptionName(props.getProperty("OptionValue1i"), 8, 0);
				a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice1i")), 8, 0);
				a1.addOption(0);
				a1.setOptionName(props.getProperty("OptionValue1j"), 9, 0);
				a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice1j")), 9, 0);
			}
			/*Option Transmission*/
			a1.addOptionSet();
			a1.setOptionSetName(props.getProperty("Option2"), 1);
			if(props.getProperty("Option2").equals(null)) {}
			else {
				a1.addOption(1);
				a1.setOptionName(props.getProperty("OptionValue2a"), 0, 1);
				a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice2a")), 0, 1);
				a1.addOption(1);
				a1.setOptionName(props.getProperty("OptionValue2b"), 1, 1);
				a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice2b")), 1, 1);
			}
			/*Brakes*/
			a1.addOptionSet();
			a1.setOptionSetName(props.getProperty("Option3"), 2);
			if(props.getProperty("Option3").equals(null)) {}
			else {
				a1.addOption(2);
				a1.setOptionName(props.getProperty("OptionValue3a"), 0, 2);
				a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice3a")), 0, 2);
				a1.addOption(2);
				a1.setOptionName(props.getProperty("OptionValue3b"), 1, 2);
				a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice3b")), 1, 2);
				a1.addOption(2);
				a1.setOptionName(props.getProperty("OptionValue3c"), 2, 2);
				a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice3c")), 2, 2);
			}
			/*Side Impact*/
			a1.addOptionSet();
			a1.setOptionSetName(props.getProperty("Option4"), 3);
			if(props.getProperty("Option4").equals(null)) {}
			else {
				a1.addOption(3);
				a1.setOptionName(props.getProperty("OptionValue4a"), 0, 3);
				a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice4a")), 0, 3);
				a1.addOption(3);
				a1.setOptionName(props.getProperty("OptionValue4b"), 1, 3);
				a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice4b")), 1, 3);
			}
			/*Power Moonroof*/
			a1.addOptionSet();
			a1.setOptionSetName(props.getProperty("Option5"), 4);
			if(props.getProperty("Option5").equals(null)) {}
			else {
				a1.addOption(4);
				a1.setOptionName(props.getProperty("OptionValue5a"), 0, 4);
				a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice5a")), 0, 4);
				a1.addOption(4);
				a1.setOptionName(props.getProperty("OptionValue5b"), 1, 4);
				a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice5b")), 1, 4);
			}
		}
		else {/*Do nothing*/}
		return a1;
	}
	
	public static Map<String, Automobile> getA1() {
		return a1;
	}
	
	public static void setA1(Map<String, Automobile> a1) {
		ProxyAutomobile.a1 = a1;
	}
	
	public void addToMap(BuildAuto obj, Automobile a1) {
		ProxyAutomobile.getA1().put(a1.getModel()+a1.getMake(), a1);
		//System.out.println(ProxyAutomobile.getA1().get(a1.getModel()+a1.getMake()).getModel());
	}
}
