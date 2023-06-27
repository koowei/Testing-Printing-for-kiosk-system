package ChargeModule;
import OrderModule.*;
import PrintingModule.PhotoPrinter;

public class Charge
{
    private static final double DOC_BW_LESS_THAN_5 = 0.50;
    private static final double DOC_BW_5_TO_10 = 0.40;
    private static final double DOC_BW_11_TO_20 = 0.30;
    private static final double DOC_BW_21_TO_50 = 0.20;

    private static final double DOC_C_LESS_THAN_5 = 1.00;
    private static final double DOC_C_5_TO_10 = 0.90;
    private static final double DOC_C_11_TO_20 = 0.80;
    private static final double DOC_C_21_TO_50 = 0.70;

    private static final double PHOTO_N_LESS_THAN_5 = 1.00;
    private static final double PHOTO_N_5_TO_10 = 0.90;
    private static final double PHOTO_N_11_TO_20 = 0.75;
    private static final double PHOTO_N_21_TO_50 = 0.50;

    private static final double PASSPORT_LESS_THAN_5 = 1.20;
    private static final double PASSPORT_5_TO_10 = 0.95;
    private static final double PASSPORT_11_TO_20 = 0.85;
    private static final double PASSPORT_21_TO_50 = 0.75;

    private static final double ADD_HQ_PAPER_CHARGE = 0.10; 
    private static final double ADD_DESIGN_E_CHARGE = 0.15; 
    private Order [] orders;
    private double totalCharge;
    public Charge()
    {
    	
    }
    public Charge(Order [] orders)
    {
    	 this.orders = orders;
    	 this.totalCharge = 0;
    }
    public Order[] getOrders()
    {
    	return this.orders;
    }
    public double getTotalCharge() {
    	for(int i=0;i < this.orders.length;i++)
        {
    		this.totalCharge += calculateCharge(orders[i]);
        }
    	return this.totalCharge;
    }
    public double calculateCharge(Order order) 
    {
    	double charge = 0;
      
        	Order o = order;
        	if(o.getQuantity() <=0 || o.getQuantity() > 50)
        		throw new IllegalArgumentException("Quantity of" + o.toString() +" out of range. Please try again.");
	        if(o instanceof Document) 
	        {
	 
	        	if(o.getOption())
	            {
	            	if (o.getQuantity() < 5) 
	                    charge += DOC_BW_LESS_THAN_5 * o.getQuantity();
	                 
	                else if (o.getQuantity() >= 5 && o.getQuantity() <= 10) 
	                    charge += DOC_BW_5_TO_10 * o.getQuantity();
	                
	                else if (o.getQuantity() >= 11 && o.getQuantity() <= 20) 
	                    charge += DOC_BW_11_TO_20 * o.getQuantity();
	                
	                else if (o.getQuantity() >= 21 && o.getQuantity() <= 50) 
	                    charge += DOC_BW_21_TO_50 * o.getQuantity();
	            }
	                    
	            else
	            {
	            	if (o.getQuantity() < 5) 
	                    charge += DOC_C_LESS_THAN_5 * o.getQuantity();
	                
	                else if (o.getQuantity() >= 5 && o.getQuantity() <= 10) 
	                    charge += DOC_C_5_TO_10 * o.getQuantity();
	                
	                else if (o.getQuantity() >= 11 && o.getQuantity() <= 20) 
	                    charge += DOC_C_11_TO_20 * o.getQuantity();
	                
	                else if (o.getQuantity() >= 21 && o.getQuantity() <= 50) 
	                    charge += DOC_C_21_TO_50 * o.getQuantity();
	            	
	            }
	        }
	        
	        if(o instanceof Photo)
	        {
	            if(o.getOption())//normal
	            {
	            	if (o.getQuantity() < 5) 
	                    charge += PHOTO_N_LESS_THAN_5 * o.getQuantity();
	                
	                else if (o.getQuantity() >= 5 && o.getQuantity() <= 10)
	                    charge += PHOTO_N_5_TO_10 * o.getQuantity();
	                
	                else if (o.getQuantity() >= 11 && o.getQuantity() <= 20) 
	                    charge += PHOTO_N_11_TO_20 * o.getQuantity();
	                
	                else if (o.getQuantity() >= 21 && o.getQuantity() <= 50) 
	                    charge += PHOTO_N_21_TO_50 * o.getQuantity();
	            }
	            
	            else
	            {
	            	if (o.getQuantity() < 5) 
	                    charge += PASSPORT_LESS_THAN_5 * o.getQuantity();
	                
	                else if (o.getQuantity() >= 5 && o.getQuantity() <= 10)
	                    charge += PASSPORT_5_TO_10 * o.getQuantity();
	                
	                else if (o.getQuantity() >= 11 && o.getQuantity() <= 20) 
	                    charge += PASSPORT_11_TO_20 * o.getQuantity();
	                
	                else if (o.getQuantity() >= 21 && o.getQuantity() <= 50) 
	                    charge += PASSPORT_21_TO_50 * o.getQuantity();
	            }
	        }
        
	        if(o instanceof Photo)
	        {
	        	Photo p = (Photo) o;
	        	if(p.getPaper())
	        		charge += ADD_HQ_PAPER_CHARGE * o.getQuantity();
	        	
	        	if(p.getDesign())
	        		charge += ADD_DESIGN_E_CHARGE * o.getQuantity();
	        }
        return charge;
    }
    public void SendPrintRequest(PhotoPrinter printer)
    {
 
    	java.util.Stack<String> orderStack = new java.util.Stack<String>();
	    for(int i=0;i <orders.length;i++)
	     for(int j=0;j<orders[i].getQuantity();j++)
	     	orderStack.push(orders[i].getClass().getName());
    	while(!(orderStack.isEmpty()))
    	{
    		orderStack.pop();
    		printer.queueRequest();
    	}
    }
}