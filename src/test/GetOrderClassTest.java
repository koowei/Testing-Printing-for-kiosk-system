import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;

import ChargeModule.Charge;
import PrintingModule.PhotoPrinter;
import OrderModule.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class GetOrderClassTest {
	
	//Test Print Document method which accept options (document type and its quantity) and will add it into ArrayList of the GetOrder Class created
	@Test
    @Parameters
    public void printDocumentValidTest(int option, int quantity, Document ER) {
		 GetOrder go = new GetOrder();
	     go.PrintDocument(option,quantity);
	     boolean AR = false;
	     // means the item we add
	     int lastAddedOrder = go.getList().size()-1;
	     
	     if(go.getList().get(lastAddedOrder).getOption() == ER.getOption())
	    	if(go.getList().get(lastAddedOrder).getQuantity() == ER.getQuantity())
	    		AR = true;
	     assertTrue(AR);
    }
	
	//Test Print Document method which accept options (document type and its quantity) and will return exception class 
	private Object[] parametersForPrintDocumentValidTest() {
		Document document1 = new Document(true,3);
        Document document2 = new Document(true,7);
        Document document3 = new Document(true,15);
        Document document4 = new Document(true,35);
        Document document5 = new Document(false,3);
        Document document6 = new Document(false,7);
        Document document7 = new Document(false,15);
        Document document8 = new Document(false,35);

        return new Object[] {
                new Object[] {1,3,document1},
                new Object[] {1,7,document2},
                new Object[] {1,15,document3},
                new Object[] {1,35,document4},
                new Object[] {0,3,document5},
                new Object[] {0,7,document6},
                new Object[] {0,15,document7},
                new Object[] {0,35,document8}
        };
    }
	@Test(expected = IllegalArgumentException.class)
    @Parameters
    public void printDocumentInvalidTest(int option, int quantity) {
		 GetOrder go = new GetOrder();
	     go.PrintDocument(option,quantity);
    }
	private Object[] parametersForPrintDocumentInvalidTest() {
        return new Object[] {
                new Object[] {-1,3},
                new Object[] {2,7},
                new Object[] {0,-1},
                new Object[] {1,51},
      
        };
    }
	
	
	//Test Print Photo method which accept options (document type , its quantity , design and paper type) and will add it into ArrayList of the GetOrder Class created
	@Test
    @Parameters
    public void printPhotoValidTest(int option, int quantity,int getPaper,int getDesign,Photo ER) {
		 GetOrder go = new GetOrder();
		 go.PrintPhoto(option,quantity,getPaper,getDesign);
	     boolean AR = false;
	     // means the item we add
	     int lastAddedOrder = go.getList().size()-1;
	     
	     if(go.getList().get(lastAddedOrder).getOption() == ER.getOption())
	    	if(go.getList().get(lastAddedOrder).getQuantity() == ER.getQuantity())
	    		AR = true;
	     assertTrue(AR);
    }
	private Object[] parametersForPrintPhotoValidTest() {
		  Photo photo1 = new Photo(false,1,false,false);
          Photo photo2 = new Photo(true,1,false,false);
          Photo photo3 = new Photo(false,1,true,false);
          Photo photo4 = new Photo(true,1,true,false);
          Photo photo5 = new Photo(false,1,false,true);
          Photo photo6 = new Photo(true,1,false,true);
          Photo photo7 = new Photo(false,1,true,true);
          Photo photo8 = new Photo(true,1,true,true);

          return new Object[] {
                  new Object[] {0,1,0,0,photo1},
                  new Object[] {1,1,0,0,photo2},
                  new Object[] {0,1,1,0,photo3},
                  new Object[] {1,1,1,0,photo4},
                  new Object[] {0,1,0,1,photo5},
                  new Object[] {1,1,0,1,photo6},
                  new Object[] {0,1,1,1,photo7},
                  new Object[] {1,1,1,1,photo8}
          };
    }
	
	//Test Print Photo method which accept options (document type and its quantity) and will return exception class 
	@Test(expected = IllegalArgumentException.class)
    @Parameters
    public void printPhotoInvalidTest(int option, int quantity,int getPaper,int getDesign) {
		 GetOrder go = new GetOrder();
		 go.PrintPhoto(option,quantity,getPaper,getDesign);
    }
	private Object[] parametersForPrintPhotoInvalidTest() {
        return new Object[] {
                new Object[] {-1,1,1,1},
                new Object[] {2,1,1,1},
                new Object[] {0,-1,1,1},
                new Object[] {1,-1,1,1},
                new Object[] {0,51,1,1},
                new Object[] {1,51,1,1},
                new Object[] {0,1,-1,1},
                new Object[] {1,1,-1,1},
                new Object[] {0,1,2,1},
                new Object[] {1,1,2,1},
                new Object[] {0,1,0,-1},
                new Object[] {1,1,0,-1},
                new Object[] {0,1,1,-1},
                new Object[] {1,1,1,-1},
                new Object[] {0,1,0,2},
                new Object[] {1,1,0,2},
                new Object[] {0,1,1,2},
                new Object[] {1,1,1,2}
        };
    }

	
	//Test send Order method which will return a Charge instance with Order Array parameter 
	@Test
	public void sendOrderTest()
	{
		GetOrder SUT = new GetOrder();
		assertTrue(SUT.sendOrder() instanceof Charge);
	}
}
  

