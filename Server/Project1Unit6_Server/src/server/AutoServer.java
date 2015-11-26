package server;

import java.util.Properties;

import adapter.BuildAuto;

import model.Automobile;

public interface AutoServer {
	public Automobile parsePropertiesObject(Properties props, Automobile a1);
	public void addToMap(BuildAuto obj, Automobile a1);
}
