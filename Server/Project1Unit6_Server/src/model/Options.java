package model;

import java.io.Serializable;

/*class Options
 * Its an inner class. It mentions an option
 * for an optionset and price associated with
 * it.*/
public class Options  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String optionName;
	private float price;
	
	/*Constructors*/
	protected Options() {
		this.optionName = "";
		this.price = 0;
	}
	protected Options(String optionName,float price) {
		this.optionName = optionName;
		this.price = price;
	}
	
	/*Getter*/
	protected String getOptionName() {
		return optionName;
	}
	protected float getPrice() {
		return price;
	}
	/*Find*/
	/*Return whether the current option
	 * is same as the String sent as input 
	 * argument.*/
	protected boolean foundOption(String name) {
		if(getOptionName().equalsIgnoreCase(name))
			return true;
		else
			return false;
	}
	
	/*Setter*/
	protected void setOptionName1(String optionName) {
		this.optionName = optionName;
	}
	protected void setPrice(float price) {
		this.price = price;
	}
	/*delete*/
	protected void deleteOption2() {
		setOptionName1(null);
		setPrice(0);
	}
	/*Update*/
	protected void update2(String newName, float newPrice) {
		setOptionName1(newName);
		setPrice(newPrice);
	}
	/*Print*/
	protected void print2() {
		System.out.println("Name:"+getOptionName());
		System.out.println("Price:"+getPrice());
	}
} //class end