import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@SuppressWarnings("serial")
public class FallingItemsFactory implements Serializable{

	private static FallingItemsFactory instance ;
    @SuppressWarnings("rawtypes")
	private Class[] classes ;
    private static String directory ="D:\\Projects\\Games\\Java Games\\GoofyFruits\\fallingItemsClasses";
	private FallingItemsFactory()  {
        try{
        	classes = FallingItemsLoader.LoadClass(directory);
        }catch(Exception e){
        	System.exit(0);
        }
		
	}
   public static void setDirectory(String d){
	   
	   directory=d;
   }
	public static FallingItemsFactory getInstance() 
	{
		if(instance==null)
		   {
			   instance = new FallingItemsFactory();
		   }
		     return instance;
	}
	
	public FallingItem getFallingItem ( ) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		int random  = (int)(Math.random()*classes.length);
		@SuppressWarnings("rawtypes")
		Constructor c = classes[random].getConstructors()[0];
		return (FallingItem) c.newInstance();
	}
	public FallingItem getSpecificFallingItem(String fruitName) throws InstantiationException, IllegalAccessException{
		
	for(int i=0;i<classes.length;i++){
		if(classes[i].getName().equalsIgnoreCase(fruitName)){
			return (FallingItem) classes[i].newInstance();
		}
	}
	return null;
	}

}
