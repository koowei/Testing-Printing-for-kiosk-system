package OrderModule;

import java.util.ArrayList;
import java.util.Scanner;

import ChargeModule.Charge;

public class GetOrder {
	private ArrayList <Order> orderList;
	public GetOrder()
	{
		this.orderList = new ArrayList<Order>();
	}
	public GetOrder(ArrayList <Order> orderlist)
	{
		this.orderList = orderlist;
	}
	public void getInputPrintPhoto()
	{
		
		int opt1,opt2,opt3,opt4;
		System.out.println("Size(Passport = 0| Normal = 1) :");
		opt1 = getOptionInput(0,1);
		System.out.println("Quantity(Minimum 1 & Maximum 50!) :");
		opt2 = getOptionInput(1,50);
		
		System.out.println("Using High Quality Paper (Yes = 1 | No = 0) :");
		opt3 = getOptionInput(0,1);
		
		System.out.println("Design Effect (Yes = 1 | No = 0) :");
		opt4 = getOptionInput(0,1);
		
		PrintPhoto(opt1,opt2,opt3,opt4);
	}
	public void PrintPhoto(int opt1,int opt2,int opt3, int opt4)
	{
		if(opt1 > 1 || opt1 < 0)
			throw new IllegalArgumentException();
		else if(opt2 < 1 || opt2 > 50)
			throw new IllegalArgumentException();
		else if(opt3 > 1 || opt3 < 0)
			throw new IllegalArgumentException();
		else if(opt4 > 1 || opt4 < 0)
			throw new IllegalArgumentException();
		else
		{
			this.orderList.add(new Photo( (opt1!=0),opt2,(opt3!=0),(opt4!=0)));
		}
	}
	public void getInputPrintDocument()
	{
		int opt1,opt2;
		System.out.println("Option(Color = 0| Black&White = 1) :" );
		opt1 = getOptionInput(0,1);

		System.out.println("Quantity(Minimum 1 & Maximum 50!) :");
		opt2 = getOptionInput(1,50);;
		PrintDocument(opt1,opt2);
	}
	public void PrintDocument(int opt1,int opt2)
	{
		if(opt1 > 1 || opt1 < 0)
			throw new IllegalArgumentException();
		else if(opt2 < 1 || opt2 > 50)
			throw new IllegalArgumentException();
		else
		{
			this.orderList.add(new Document((opt1!=0),opt2));
		}
	}
	public Charge sendOrder()
	{
		Order[] orderArray = new Order[orderList.size()];
	    orderList.toArray(orderArray);
	    return new Charge(orderArray);
	}
	public static int getInput()
	{
		Scanner input = new Scanner(System.in);
		try {
			String option = input.nextLine();
			return Integer.parseInt(option);
		}catch(NumberFormatException e)
		{
			System.out.println("Invalid Input , Integer Only : ");
			return getInput();
		}
	}
	public static int getOptionInput(int lowerBound , int upperBound)
	{
		int option = getInput();
		if(option < lowerBound || option > upperBound)
		{
			return getOptionInput(lowerBound,upperBound);
		}
		return option;
	}
	public ArrayList<Order> getList()
	{
		return this.orderList;
	}
}
