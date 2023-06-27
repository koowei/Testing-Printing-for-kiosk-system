package OrderModule;
public class Photo extends Order {

	private boolean HQpaper;
	private boolean Design;
	public Photo(boolean Option, int Quantity,boolean HQpaper,boolean Design) {
		super(Option, Quantity);
		this.HQpaper = HQpaper;
		this.Design = Design;
	}
	public boolean getPaper()
	{
		return this.HQpaper;
	}
	public boolean getDesign()
	{
		return this.Design;
	}
	public String getOptionStr()
	{
		if(this.Option)
			return "Normal";
		else
			return "Passport";
	}
}