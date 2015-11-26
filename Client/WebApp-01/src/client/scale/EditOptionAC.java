package client.scale;

import client.adapter.BuildAuto;
import client.adapter.CreateAuto;
import client.adapter.UpdateAuto;

/*class EditOptionAC
 * It implements the functions to edit the buildAuto
 * objects*/
public abstract class EditOptionAC extends Thread{
	private String make = null;
	private String model = null;
	private String optionNameOld = null;
	private String optionNameNew = null;
	private String OptionSetName = null;
	private float priceNew = -1;
	private CreateAuto autoObj = new BuildAuto();
	private UpdateAuto autoObj1 = new BuildAuto();
	
	/*Getters and Setters*/
	public String getOptionNameOld() {
		return optionNameOld;
	}
	public void setOptionNameOld(String optionNameOld) {
		this.optionNameOld = optionNameOld;
	}
	public String getOptionNameNew() {
		return optionNameNew;
	}
	public void setOptionNameNew(String optionNameNew) {
		this.optionNameNew = optionNameNew;
	}
	public String getOptionSetName() {
		return OptionSetName;
	}
	public void setOptionSetName(String optionSetName) {
		OptionSetName = optionSetName;
	}
	public float getPriceNew() {
		return priceNew;
	}
	public void setPriceNew(float priceNew) {
		this.priceNew = priceNew;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	/*Interface method implementation*/
	/*This function searches the Model for a given 
	 * OptionSet and Option name, and sets the price  to newPrice. */
	public void editOptionName(String make,String Modelname, 
											String OptionnameOld, String OptionnameNew) {
		autoObj1.updateOptionName(make, Modelname, OptionnameOld, OptionnameNew);
	}
	/*This function searches the Model for a given OptionSet and 
	 * Option name, and sets the price  to newPrice. */
	public void editOptionPrice(String make,String Modelname, 
											 String Optionname, float newprice) {
		autoObj1.updateOptionPrice(make, Modelname, Optionname, newprice);
	}
	
	/*Overriding run method*/
	public void run() {
		String temp;
		/*Build an object*/
		autoObj.BuildAuto("FordProperties.txt");
		/*Print object*/
		System.out.println("Initial");
		autoObj.printAuto("Ford","ZTW");
		
		/*Edit options*/
		temp = getOptionSetName()+":"+getOptionNameOld();
		
		if(getOptionNameNew() == null) {
			editOptionPrice(getMake(),getModel(),temp, getPriceNew());
		}
		else if(getPriceNew() == -1) {
			editOptionName(getMake(),getModel(), temp, getOptionNameNew());
		}
		else {
			editOptionName(getMake(),getModel(), temp, getOptionNameNew());
			editOptionPrice(getMake(),getModel(),temp, getPriceNew());
		}	
		System.out.println("After Change");
		autoObj.printAuto("Ford","ZTW");
	}
	/*Edit option parameters initialization*/
	public void editOptionAC(String make, String model, String OptionSetName, 
										  String OptionNameOld, String OptionNameNew) {
		setMake(make);
		setModel(model);
		setOptionNameOld(OptionNameOld);
		setOptionNameNew(OptionNameNew);
		setOptionSetName(OptionSetName);
	}
	/*Edit option parameters initialization*/
	public void editOptionAC(String make, String model, 
										  String OptionSetName, String OptionNameOld,
										  float OptionPriceNew) {
		setMake(make);
		setModel(model);
		setOptionNameOld(OptionNameOld);
		setOptionSetName(OptionSetName);
		setPriceNew(OptionPriceNew);
	}
}
