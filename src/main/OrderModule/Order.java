package OrderModule;
public abstract class Order {
	protected boolean Option; 
	//for document , true = black/white , false = color
	//for photo , true = normal , false = passport
	protected int Quantity;
	public Order(boolean Option,int Quantity)
	{
		this.Option = Option;
		this.Quantity = Quantity;
	}
	public int getQuantity()
	{
		return this.Quantity;
	}
	public boolean getOption()
	{
		return this.Option;
	}
	public String getOptionStr()
	{
		return null;
	}
	
}