package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;
import client.exception.AutoException;

/*class OptionSet
 * It contains the name of the feature
 * and the options associated with it.*/
public class OptionSet  implements Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String name;
		private final int INITIAL_OPTIONS = 20;
		private ArrayList<Options> option = new ArrayList<Options>();
		private Options choice = new Options();
				
		/*Constructor*/
		protected OptionSet() {	
			int i;
			this.name = " ";
			for(i = 0;i < 0;i++) {
				option.add(new Options());
			}
		}
		protected OptionSet(String name, int optionSize) {
			int i = 0;
			this.name = name;
			for(i = 0;i < optionSize;i++) {
				option.add(new Options());
			}
		}
        
		/*Add option object*/
		public void addOption1() {
			this.option.add(new Options());
		}
		/*Getters*/
		protected String getName() {
			return name;
		}
		/*Get entire array of options*/
		protected ArrayList<Options> getOption() {
			return option;
		}
		/*Get a particular option at index*/
		protected Options getOption(int index) {
			return option.get(index);
		}
		/*Get a choice*/
		protected Options getChoice() {
			return choice;
		}
		/*Get a choice name*/
		protected String getChoiceName1() {
			return choice.getOptionName();
		}
		/*Get a choice price*/
		protected float getChoicePrice1() {
			return choice.getPrice();
		}
		/*Find methods*/
		/*Return if the current optionset is same as 
		 * string sent in input argument*/
		protected boolean findOptionSet1(String name) {
			if(getName().equalsIgnoreCase(name))
				return true;
			else
				return false;
		}
		/*Return whether a particular option 
		 * could be found in this optionset*/
		protected boolean findOptionByName(String name) {
			int i = 0;
			boolean found = false;
	
			for(i = 0; i < option.size(); i++) {
				found = option.get(i).foundOption(name);
				if(found == true)
					break;
				else {/*Do nothing*/}
			}
			return found;
		}
		
		/*Setters*/
		protected void setName(String name) {
			this.name = name;
		}
		protected void setChoice1(int choice) {
			String choiceName = this.getOption().get(choice).getOptionName();
			float price = this.getOption().get(choice).getPrice();
			this.choice.setOptionName1(choiceName);
			this.choice.setPrice(price);
		}
		protected void setChoice1prob(String choice) {
			float price = 0;
			this.choice.setOptionName1(choice);
			this.choice.setPrice(price);
		}
		/*set the optionset for a given optionset*/
		protected void setOptionSet(OptionSet optset) {
			int i = 0;
			
			for(i = 0; i < optset.getOption().size();i++) {
				this.option.get(i).setOptionName1(optset.getOption(i).getOptionName());
				this.option.get(i).setPrice(optset.getOption(i).getPrice());
			}
		}
		/*Set a particular option for a given option and index*/
		protected void setOption1(Options option,int index) {
			this.option.get(index).setPrice(option.getPrice());
			this.option.get(index).setOptionName1(option.getOptionName());
		}
		/*Set a particular option name for a given option name and index*/
		protected void setOptionName(int indexOption,String newName) {
			this.option.get(indexOption).setOptionName1(newName);
		}
		/*Set a particular option price for a given option name and index*/
		protected void setOptionPrice(int indexOption,float price) {
			this.option.get(indexOption).setPrice(price);
		}
		/*Set a option when both the price and name are sent as a
		 * string*/
		protected void setOptionSetDetail1(String optionString, int index) throws AutoException{
			int i = 0;
            StringTokenizer st = new StringTokenizer(optionString,",");
            String temp;
            
            while (st.hasMoreTokens()) {
            	temp = st.nextToken();
            	StringTokenizer st1 = new StringTokenizer(temp,"$");
            	if(st1.countTokens() == 2) {
            		this.option.add(new Options());
					this.option.get(i).setOptionName1(st1.nextToken());
					try {
						try {
							this.option.get(i).setPrice(Float.parseFloat((st1.nextToken())));
						} catch(NumberFormatException e4) {
							this.option.get(i).setPrice(0);
							throw new AutoException(4,index);
						}
					}
					catch (AutoException e) {}
					i++;
            	}
            	else {
            		this.option.get(i).setOptionName1("");
            		this.option.get(i).setPrice(0);
            		throw new AutoException(5,index);
            	}
			}
		}
		/*Delete*/
		protected void deleteOptionSet1() {
			this.setName(null);
			this.setOptionSet(null);
		}
		/*Upload*/
		protected void uploadOptionSet1(String newFeature, OptionSet opt) {
			this.setName(newFeature);
			this.setOptionSet(opt);
		}
		/*Print*/
		protected void print1() {
			int i = 0;
			
			System.out.println(this.getName());
			for(i = 0; i < INITIAL_OPTIONS; i++) {
				System.out.println(this.getOption(i));
			}
		}
}
