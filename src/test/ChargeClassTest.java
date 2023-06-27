import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ChargeModule.Charge;
import PrintingModule.PhotoPrinter;
import OrderModule.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class ChargeClassTest {
	
	//method to import parameters from file
	public ArrayList<String []> readFromFile(String filePath) throws FileNotFoundException, IOException
	{
		ArrayList<String []> Params = new ArrayList<String []> ();
		Path rootDir = Paths.get(".").normalize().toAbsolutePath();
		try(BufferedReader in = new BufferedReader(new FileReader(rootDir.toString() + filePath))) {
		    String str;
		    while ((str = in.readLine()) != null) {
		        Params.add(str.split(","));
		    }
		}
		return Params;
	}
	
	
	
	//TEST Calculate charge method in Charge Class which accept an order(Order data type) and return a charge of that order(Double data type)
	@Parameters
	@Test
	public void calculateChargeTest(Order order , double expectedResult)
	{
		Charge SUT = new Charge();
		assertEquals(SUT.calculateCharge(order),expectedResult,0.01);
	}
	private Object[] parametersForCalculateChargeTest() throws FileNotFoundException, IOException
	{
		ArrayList<Object[] > listToReturn = new ArrayList<Object[]>();
		
		//import photos parameters from files
		ArrayList<String []> photoParams = readFromFile("/src/test/parameterForTest/ValidPhotoParameters.txt");
		for(String [] strArray: photoParams)
		{
			boolean opt1 = Boolean.parseBoolean(strArray[0]);
			int opt2 = Integer.parseInt(strArray[1]);
			boolean opt3 = Boolean.parseBoolean(strArray[2]);
			boolean opt4 = Boolean.parseBoolean(strArray[3]);
			double ER = Double.parseDouble(strArray[4]);
			listToReturn.add(new Object[] {(new Photo(opt1,opt2,opt3,opt4)),ER});
		}
		//import documents parameters from files
		ArrayList<String []> docParams = readFromFile("/src/test/parameterForTest/ValidDocumentParameters.txt");
		for(String [] strArray: docParams)
		{
			boolean opt1 = Boolean.parseBoolean(strArray[0]);
			int opt2 = Integer.parseInt(strArray[1]);
			double ER = Double.parseDouble(strArray[2]);
			listToReturn.add(new Object[] {new Document(opt1,opt2),ER});
		}
		Object [] arrayToReturn = listToReturn.toArray();
		return arrayToReturn;
	}
	
	
	
	//Test Calculate charge method with invalid value which will return exception class
	@Parameters
	@Test(expected=IllegalArgumentException.class)
	public void calculateChargeInvalidTest(Order order)
	{
		Charge SUT = new Charge();
		SUT.calculateCharge(order);
	}
	
	private Object[] parametersForCalculateChargeInvalidTest() throws FileNotFoundException, IOException
	{
		ArrayList<Object[] > listToReturn = new ArrayList<Object[]>();
		
		//import photos parameters from files
		ArrayList<String []> photoParams = readFromFile("/src/test/parameterForTest/InvalidPhotoParameters.txt");
		for(String [] strArray: photoParams)
		{
			boolean opt1 = Boolean.parseBoolean(strArray[0]);
			int opt2 = Integer.parseInt(strArray[1]);
			boolean opt3 = Boolean.parseBoolean(strArray[2]);
			boolean opt4 = Boolean.parseBoolean(strArray[3]);
			listToReturn.add(new Object[] {(new Photo(opt1,opt2,opt3,opt4))});
		}
		//import documents parameters from files
		ArrayList<String []> docParams = readFromFile("/src/test/parameterForTest/InvalidDocumentParameters.txt");
		for(String [] strArray: docParams)
		{
			boolean opt1 = Boolean.parseBoolean(strArray[0]);
			int opt2 = Integer.parseInt(strArray[1]);
			listToReturn.add(new Object[] {new Document(opt1,opt2)});
		}
		Object [] arrayToReturn = listToReturn.toArray();
		return arrayToReturn;
	} 
	
	
	
	//Test get total charge , accept an Array of Order which return the total of charges of each Order in array
	@Parameters
	@Test
	public void getTotalChargeTest(Order [] orders ,double expectedResult)
	{
		Charge SUT = new Charge(orders);
		assertEquals(expectedResult,SUT.getTotalCharge(),0.01);
	}
	private Object [] parametersForGetTotalChargeTest()
	{
		Order photo1 = new Photo(true,3,true,true);
		Order photo2 = new Photo(true,15,true,true);
		Order photo3 = new Photo(true,35,true,true);
		Order doc1 = new Document(true,3);
		Order doc2 = new Document(true,15);
		Order doc3 = new Document(true,35);
		Order [] array1 = {photo1,doc1};
		Order [] array2 = {photo2,doc3,doc1};
		Order [] array3 = {photo1,photo2,photo3,doc1,doc2,doc3};
		Order [] array4 = {};
		
		
		return new Object[] {
				new Object[] {array1,5.25},
				new Object[] {array2,23.5},
				new Object[] {array3,58},
				new Object[] {array4,0}
				
		};
	}
	
	//mock test for Charge class will send Print request to PrintPhoto class for each quantity of Order
	@Parameters
	@Test
	public void SendPrintRequestTest(Order [] orders , int expectedResult)
	{
		Charge SUT = new Charge(orders);
		PhotoPrinter printerMock = mock(PhotoPrinter.class);
		SUT.SendPrintRequest(printerMock);
		verify(printerMock,times(expectedResult)).queueRequest();
	}
	private Object [] parametersForSendPrintRequestTest()
	{
		Order photo1 = new Photo(true,3,true,true);
		Order photo2 = new Photo(true,15,true,true);
		Order photo3 = new Photo(true,35,true,true);
		Order doc1 = new Document(true,3);
		Order doc2 = new Document(true,15);
		Order doc3 = new Document(true,35);
		Order [] array1 = {photo1,doc1};
		Order [] array2 = {photo2,doc3,doc1};
		Order [] array3 = {photo1,photo2,photo3,doc1,doc2,doc3};
		Order [] array4 = {};
		
		
		return new Object[] {
				new Object[] {array1,6},
				new Object[] {array2,53},
				new Object[] {array3,106},
				new Object[] {array4,0}
				
		};
	}
}
