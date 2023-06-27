package MainModule;
import java.util.Scanner;

import ChargeModule.Charge;
import OrderModule.*;
public class Application {
	public static void main(String[]args)
	{
		GetOrder o = new GetOrder();
		int choice;
		do {
		System.out.println("Print \n1.Photo\n2.Document\n3.Done");
		choice = getInput();
		switch(choice)
		{
		case 1://printPhoto
			o.getInputPrintPhoto();
			break;
		case 2://printDocument
			o.getInputPrintDocument();
			break;
		case 3://exit
			System.out.println("The charge of this order is " + o.sendOrder().getTotalCharge());
			break;
		default:
			System.out.println("Invalid Choice !");
		}
		}while(choice != 3);
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
	
}
