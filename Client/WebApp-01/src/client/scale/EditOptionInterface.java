package client.scale;
/*Interface EditOptionInterface
 * It contains functions to edit the options 
 * in automobile*/
public interface EditOptionInterface {
	//public void editOptionAC(String make, String model, String OptionSetName, String OptionNameOld, String OptionNameNew, float OptionPriceNew);
	/*This function searches the Model for a given 
	 * OptionSet and Option name, and sets the price  to newPrice. */
	public void editOptionName(String make,String Modelname, 
							   String OptionnameOld, String OptionnameNew);
	/*This function searches the Model for a given OptionSet 
	 * and Option name, and sets the price  to newPrice. */
	public void editOptionPrice(String make,String Modelname, 
								String Optionname, float newprice);
}
