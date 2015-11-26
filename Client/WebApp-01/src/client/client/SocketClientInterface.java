package client.client;


import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

import client.adapter.BuildAuto;

import model.Automobile;

public interface SocketClientInterface {
	public void sendDataToServer(Properties pros, ObjectOutputStream oos);
	public void sendDataToServer(String pros, ObjectOutputStream oos);
	public String readFromServer(String action, ObjectInputStream ois,ObjectOutputStream oos, BuildAuto obj);
	public ReturnTypeVariety clientActivityCarChoice(ObjectInputStream ois,ObjectOutputStream oos, 
			BuildAuto obj, SelectCarOption selectObject, String state, String car);
	public String clientActivityCarChoice1(ObjectInputStream ois,ObjectOutputStream oos, 
			BuildAuto obj, SelectCarOption selectObject);
	void clientActivityCarChoice2(ObjectInputStream ois,ObjectOutputStream oos, 
			   BuildAuto obj, SelectCarOption selectObject, String carSelected);
	public void clientActivityCarINI(ObjectInputStream ois,ObjectOutputStream oos, SelectCarOption selectObject, String car);
}
