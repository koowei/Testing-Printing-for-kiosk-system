import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ChargeModule.Charge;
import OrderModule.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
@RunWith(JUnitParamsRunner.class)
public class IntegrationOrderAndChargeClassTest {
	GetOrder go;
    Charge c;

    @Before
    public void setupForAllTests() {
        go = new GetOrder();
        c = new Charge();
    }
    
    @Parameters
    @Test
    public void testOrderAndChargePrintPhoto(int option, int quantity,int getPaper,int getDesign,double ER) {
        // add a photo order with high quality paper and design effect
    	go.PrintPhoto(option,quantity,getPaper,getDesign);
        Photo photoOrder = (Photo) go.getList().get(0);

        // send the order and calculate the total charge
        Charge charge = go.sendOrder();
   
        // assert that the calculated charges match the expected charges
        assertEquals(ER, charge.calculateCharge(photoOrder), 0.001);

    }
    private Object[] parametersForTestOrderAndChargePrintPhoto() {
        return new Object[] {
        		 new Object[] {1,3,1,1,3.75},
                 new Object[] {1,7,1,1,8.05},
                 new Object[] {1,15,1,1,15},
                 new Object[] {1,35,1,1,26.25},
                 
                 new Object[] {1,3,1,0,3.3},
                 new Object[] {1,7,1,0,7},
                 new Object[] {1,15,1,0,12.75},
                 new Object[] {1,35,1,0,21},
                 
                 new Object[] {1,3,0,1,3.45},
                 new Object[] {1,7,0,1,7.35},
                 new Object[] {1,15,0,1,13.5},
                 new Object[] {1,35,0,1,22.75},
                 
                 new Object[] {1,3,0,0,3},
                 new Object[] {1,7,0,0,6.3},
                 new Object[] {1,15,0,0,11.25},
                 new Object[] {1,35,0,0,17.5},
                 
                 new Object[] {0,3,1,1,4.35},
                 new Object[] {0,7,1,1,8.4},
                 new Object[] {0,15,1,1,16.5},
                 new Object[] {0,35,1,1,35},
                 
                 new Object[] {0,3,1,0,3.9},
                 new Object[] {0,7,1,0,7.35},
                 new Object[] {0,15,1,0,14.25},
                 new Object[] {0,35,1,0,29.75},
                 
                 new Object[] {0,3,0,1,4.05},
                 new Object[] {0,7,0,1,7.7},
                 new Object[] {0,15,0,1,15},
                 new Object[] {0,35,0,1,31.5},
                 
                 new Object[] {0,3,0,0,3.6},
                 new Object[] {0,7,0,0,6.65},
                 new Object[] {0,15,0,0,12.75},
                 new Object[] {0,35,0,0,26.25},
        };
  }
    @Parameters
    @Test
    public void testOrderAndChargePrintDocument(int option, int quantity, double ER) {
  
        // add a document order with color option
        go.PrintDocument(option,quantity);
        Document docOrder = (Document) go.getList().get(0);

        // send the order and calculate the total charge
        Charge charge = go.sendOrder();

        // assert that the calculated charges match the expected charges
        assertEquals(ER, charge.calculateCharge(docOrder), 0.001);
    }
    private Object[] parametersForTestOrderAndChargePrintDocument() {

        return new Object[] {
                new Object[] {1,3,1.5},
                new Object[] {1,8,3.2},
                new Object[] {1,15,4.5},
                new Object[] {1,48,9.6},
                new Object[] {0,3,3},
                new Object[] {0,8,7.2},
                new Object[] {0,15,12},
                new Object[] {0,48,33.6}
        };
    }
    
}
