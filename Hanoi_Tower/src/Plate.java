
public class Plate {
	int size ;
	char target ;
	char  now ;
	
	
	public Plate(int order,char init)
	{
		this.size = 2*order + 1 ;	
		this.now = init;
	}
}
