package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import exception.AutoException;
import model.OptionSet;
import model.Options;

/*class Automobile
 *It contains the name,base price and various
 *features(option set ) of the car. */
public class Automobile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private static final long serialVersionUID = 1L;
	/*Property name*/
	private String make;
	private String model;
	private float basePrice = 0;
	private final int MAX_SIZE = 20;
	private ArrayList <OptionSet> opset = new ArrayList <OptionSet>();
	private float totalPrice = 0;
		
	/*Constructor*/
	public Automobile() {	
		int i = 0;
		
		for(i=0;i < 0;i++) {
			opset.add(new OptionSet());
		}
		this.make = "";
		this.basePrice = 0;
	}
	public Automobile(String name, float baseprice, 
			          int OptionSetsize) {
		int i = 0;
		
		for(i=0;i < OptionSetsize;i++) {
			opset.add(new OptionSet());
		}
		this.make = name;
		this.basePrice = baseprice;
	}

	/*Getters*/
	/*Get make of car*/
	public String getMake() {
		return make;
	}
	/*Get model of car*/
	public String getModel() {
		return model;
	}
	/*Get base price of car*/
	public float getBasePrice() {
		return basePrice;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	/*Get option set object of car
	 * for a particular index*/
	public OptionSet getOpset(int index) {
		return opset.get(index);
	}
	/*Get option set group object of car
	 * for a particular index*/
	public ArrayList<OptionSet> getOpsetGrp() {
		return opset;
	}
	/*Get option object of car
	 * for a particular index of optionset*/
	public ArrayList<model.Options> getOption(int indexSet) {
		
		return opset.get(indexSet).getOption();
	}
	/*Get optionset name of car
	 * for a particular index of optionset*/
	public String getOptionSetName(int indexSet) {
		
		return opset.get(indexSet).getName();
	}
	/*Get option arraylist of car
	 * for a particular index of optionset*/
	public ArrayList<Options> getOptionSetOption(int indexSet) {
		
		return opset.get(indexSet).getOption();
	}
	/*Get option name of car
	 * for a particular index of optionset 
	 * and index of option*/
	public String getOptionName1(int indexSet, int indexOption) {
		
		return opset.get(indexSet).getOption(indexOption).getOptionName();
	}
	/*Get option price of car
	 * for a particular index of optionset 
	 * and index of option*/
	public float getOptionPrice1(int indexSet, int indexOption) {
		
		return opset.get(indexSet).getOption(indexOption).getPrice();
	}
	/*Find methods*/
	/*OptionSet corresponding to a particular 
	 * feature is found and index of the option
	 * set is returned. Using index number any optionset
	 * can be accessed.
	 * -1 is returned for no match*/
	public int findOptionSet(String name) {
		int i = 0;
		boolean found = false;
		
		for(i = 0; i < this.opset.size(); i++) {
			found = opset.get(i).findOptionSet1(name);
			if(found == true) {
				//System.out.println("Found optionSet@"+i);
				return i;
			}
			else{/*Do nothing*/}
		}
		System.out.println("Not Found optionSet");
		return -1;
	}
	/*Return optionset index whose option name is mentioned
	 * -1 is returned for no match*/
	public int findOption(String name) {
		int i = 0;
		boolean found = false;
		
		for(i = 0; i < this.opset.size(); i++) {
			found = opset.get(i).findOptionByName(name);
			if(found == true) {
				//System.out.println("Found in optionSet:"+opset[i].getName());
				return i;
			}
			else{/*Do nothing*/}
		}
		System.out.println("Not found");
		return -1;
	}
	
	/*Setters*/
	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}
	public void setModel(String name) {
		this.model = name;
	}
	public void setMake(String name) {
		this.make = name;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	/*Set a particular optionset name when index of optionset
	 * and new optionset name are given*/
	public void setOptionSetName(String name, int index) {
		this.opset.get(index).setName(name);
	}
	/*Set a particular option name when index of optionset,option
	 * and new option name are given*/
	public void setOptionName(String newName, 
								int indexOption, int indexOptionSet) {
		this.opset.get(indexOptionSet).setOptionName(indexOption,newName);
	}
	/*Set a particular option price when index of optionset,option
	 * and new option price are given*/
	public void setOptionPrice(float newPrice, 
								int indexOption, int indexOptionSet) {
		this.opset.get(indexOptionSet).setOptionPrice(indexOption, newPrice);
	}
	/*Set a particular optionset when index of optionset
	 * and new optionset are given*/
	public void setOptionSet(OptionSet opt, int index) {
		this.opset.get(index).setName(opt.getName());
		this.opset.get(index).setOptionSet(opt);
	}
	/*Set a particular option in a given 
	 * optionset when index of optionset and option
	 * and new optionset and option are given*/
	public void setOption(OptionSet opt, int index1, Options op, int index2) {
		this.opset.get(index1).setOption1(op,index2);
	}
	/*Set a particular optionset when index of optionset
	 * and name of optionset and string containing all option
	 * is given*/
	public void setOptionSetDetail(String name, int index, String optionString) {
		this.opset.add(new OptionSet());
		this.opset.get(index-3).setName(name);
		try {
			this.opset.get(index-3).setOptionSetDetail1(optionString,index);
		} catch (AutoException e) {}
	}
	
	/*Add optionset object*/
	public void addOptionSet() {
		this.opset.add(new OptionSet());
	}
	/*Add option object*/
	public void addOption(int index) {
		this.getOpset(index).addOption1();
	}
		
	/*Delete*/
	/*A particular optionset is deleted when a particular
	 * index and optionset is given*/
	public void deleteOptionSet(OptionSet optset, int index) { 
		int i = 0;
		for(i = index; i < (opset.size() - 1); i ++)
			opset.add(opset.get(i+1));
	}
	/*A particular option in an optionset is deleted when a 
	 * particular index of optionset and option along with
	 * optionset and option value are given*/
	public void deleteOption(OptionSet optset, int index1, int index2) { 
		int i = 0;
		for(i = index2; i < (opset.get(index1).getOption().size() - 1); i ++)
			opset.get(index1).getOption().add(i,opset.get(index1).getOption().get(i+1));
	}
	
	/*Update*/
	/*For a given OptionSet find it and 
	 * set it*/
	public void updateOptionSet(OptionSet optset) { 
		int setNumber =  findOptionSet(optset.getName());
		setOptionSet(optset, setNumber);
	}
	/*For a given OptionSet and option find it and 
	 * set the option*/
	public void updateOption(String optset, String op, String newOp, float price) { 
		int setNumber =  findOptionSet(optset);
		int optionNumber = findOption(op);
		
		this.getOpset(setNumber).getOption(optionNumber).setOptionName1(newOp);
		this.getOpset(setNumber).getOption(optionNumber).setPrice(price);
	}
	/*For a given OptionSet name and option name find it and 
	 * set the option name*/
	public void updateOptionName1(String optset, String op, String newOptionName) { 
		int setNumber =  findOptionSet(optset);
		int optionNumber = findOption(op);
		
		if(setNumber == -1 || optionNumber == -1)
			System.out.println("Wrong input for "+optset +":"+op+":"+newOptionName);
		else
			this.getOpset(setNumber).getOption(optionNumber).setOptionName1(newOptionName);
	}
	/*For a given OptionSet name and option name find it and 
	 * set the price*/
	public void updateOptionPrice1(String optset, String op, float price) { 
		int setNumber =  findOptionSet(optset);
		int optionNumber = findOption(op);
		
		if(setNumber == -1 || optionNumber == -1)
			System.out.println("Wrong input for "+optset +":"+op+":"+price);
		else
			this.getOpset(setNumber).getOption(optionNumber).setPrice(price);
	}
	
	/*Print Automotive*/
	public void print() {
		int i = 0;
		int j = 0;
		System.out.println("--------------------------------------------------------");
		System.out.println("Make:"+getMake());
		System.out.println("Model:"+getModel());
		System.out.println("Base Price:"+getBasePrice());
		System.out.println("--------------------------------------------------------");
		System.out.println("Printing properties:");
		System.out.println("--------------------------------------------------------");
		for(i=0;i < opset.size();i++) {
			if(opset.get(i).getName().equals(" ")) {
				break;
			}
			else {/*Do nothing*/}
			System.out.println("Optionset:"+opset.get(i).getName()+"-");
			for(j = 0; j< opset.get(i).getOption().size();j++) {
				if(opset.get(i).getOption(j).getOptionName().equals("")) {
					break;
				}
				else {/*Do nothing*/}
				System.out.println("\t"+opset.get(i).getOption(j).getOptionName()+" @ $"+
						           opset.get(i).getOption(j).getPrice());
			}
			getTotalPriceAutmobile();
			System.out.println("========================================================");
		}
		System.out.println("Price of automobile: $"+getTotalPrice());
	}
	/*Get total price of automobile after choice*/
	public void getTotalPriceAutmobile() {
		Iterator<OptionSet> it = opset.iterator();
		setTotalPrice(getBasePrice());
		
		while (it.hasNext()) {
			OptionSet a = it.next();
			setTotalPrice(getTotalPrice()+a.getChoice().getPrice());
		}
	}
	/*Set user choice*/
	public void setChoice(int index,int name) {
		opset.get(index).setChoice1(name);
	}
	public void setChoiceProb(int index,String choice) {
		opset.get(index).setChoice1prob(choice);
	}
	/*Get user choice for a optionset*/
	public Options getChoice(int index) {
		return opset.get(index).getChoice();
	}
	/*Get user choice name for a optionset*/
	public String getChoiceName(int index) {
		return opset.get(index).getChoiceName1();
	}
	/*Get user choice price for a optionset*/
	public float getChoicePrice(int index) {
		return opset.get(index).getChoicePrice1();
	}
}