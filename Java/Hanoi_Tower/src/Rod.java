
public class Rod {
	
	char position ;
	Plate [] plateStore ;
	char [][] image ;
	
	public Rod(char position,int platecount)
	{
		this.position = position;
	    this.plateStore = new Plate[platecount];
	    this.image = new char[platecount+1][2*platecount+3];
	    ResetRodImage(this.plateStore);
	}
	
	public void SetPlateStore(Plate[] plates)
	{
		int j = plates.length -1 ;
		this.plateStore = new Plate[plates.length];
		for(int i = plates.length -1 ; i >= 0 ; i--)
		{
			if(plates[i].now == this.position) 
			{
				this.plateStore[j] = plates[i] ;
				j--;
			}
			
		}
		ResetRodImage(this.plateStore);
		
	}
	
	public void ResetRodImage(Plate[] plateStore)
	{
		for(int j = 0 ; j < image.length ; j++)
		{
			for(int i = 0; i < image[j].length ; i++)
			{
				image[j][i] = ' ' ;
			}
		}
		for(int j = 0 ; j < image.length ; j++)
		{
			for(int i = 0; i < image[j].length ; i++)
			{
				if (j == 0 )
				{
						image[j][(image[j].length-1)/2] = '*' ;
				}
				else 
				{
					if( plateStore[j-1] == null) 
					{
						image[j][(image[j].length-1)/2] = '*' ;
					}
					else
					{
						if( i >= (image[j].length-1)/2 - (plateStore[j-1].size-1)/2  &&  i <= (image[j].length-1)/2 + (plateStore[j-1].size-1)/2)
						{
							image[j][i] = '=' ;
						}
					}
				}
				
			}
		}
	}
	
}
