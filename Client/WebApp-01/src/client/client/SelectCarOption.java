package client.client;

import java.util.Scanner;
import java.util.StringTokenizer;

import client.adapter.BuildAuto;
import client.adapter.CreateAuto;

import model.Automobile;

public class SelectCarOption {
	public boolean overActivitySet = true;
	public String customerChoice = null;
	/*Constructor*/
	public SelectCarOption(){}
	
	/*Display models available at server*/
	public String displayAvailableModel(String t) {
		String input;
		Scanner in = new Scanner(System.in);
		System.out.println("=====================================");
		System.out.println("Displaying models available at Server");
		System.out.println("=====================================");
		StringTokenizer st = new StringTokenizer(t,"\n");
		int i = 0;
		while(st.hasMoreTokens()) {
			System.out.println(st.nextToken()+":"+i);
			i++;
		}
		System.out.println("Enter your choice(Number)");
		input = in.nextLine();
		return input;
	}
	/*Display choicesof selected car*/
	public void displaySelectedCar(BuildAuto obj, String make, String model) {
		int i;
		System.out.println("Selected Vehicle "+ make + " "+ model);
		for(i = 0; i < obj.getA1().get(model+make).getOpsetGrp().size();i++) {
			System.out.println("Selected " + obj.getA1().get(model+make).getOptionSetName(i));
			System.out.println(obj.getA1().get(model+make).getChoiceName(i) +
					           " @ " + obj.getA1().get(model+make).getChoicePrice(i));
		}
		obj.getA1().get(model+make).getTotalPriceAutmobile();
		System.out.println("==============================================================");
		System.out.println("Total cost: $"+obj.getA1().get(model+make).getTotalPrice());
		System.out.println("==============================================================");
	}
}
