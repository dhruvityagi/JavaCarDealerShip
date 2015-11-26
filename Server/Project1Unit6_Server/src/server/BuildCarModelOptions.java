package server;

import java.util.Properties;
import model.Automobile;
import adapter.BuildAuto;

public class BuildCarModelOptions {
	/*Constructor*/
	public BuildCarModelOptions(){};
	
	public Automobile createAutomobileFromProp(Properties props, BuildAuto obj) {
		Automobile a1 = new Automobile();
		a1 = obj.parsePropertiesObject(props,a1); 
		return a1;
	}
	
	public void addAutoMap(BuildAuto obj, Automobile a1) {
		obj.addToMap(obj, a1);
	}
}
