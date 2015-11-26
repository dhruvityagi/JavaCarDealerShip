package adapter;

import model.Automobile;

public interface CreateAuto {
	/*Given a text file name a method called BuildAuto can be written to build an instance of  
	Automobile. This method does not have to return the Auto instance. */ 
	public void BuildAuto(String filename);
	/*This function searches and prints the properties of a given modelname and makename. */
    public void printAuto(String Modelname, String Makename);
    /*This function loads user choice*/
    public void loadChoice(String make, String model); 
}
