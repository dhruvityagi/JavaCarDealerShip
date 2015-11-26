package adapter;

public interface UpdateAuto {
	/*This function searches the Model for a given 
	 *OptionSet and sets the name of OptionSet to newName. */
	public void updateOptionSetName(String make,String Modelname, 
									String OptionSetname, String newName);
	/*This function searches the Model for a given 
	 *OptionSet and Option name, and sets the price  to newPrice. */
	public void updateOptionName(String make,String Modelname, 
								 String OptionnameOld, String OptionnameNew);
	/*This function searches the Model for a given OptionSet and 
	 * Option name, and sets the price  to newPrice. */
	public void updateOptionPrice(String make,String Modelname, 
								  String Optionname, float newprice);
}
