package OrderModule;
public class Document extends Order {
	
	public Document(boolean Option,int Quantity) {
		super(Option,Quantity);
	}
	public String getOptionStr()
	{
		if(this.Option)
			return "Black and White";
		else
			return "Color";
	}

}