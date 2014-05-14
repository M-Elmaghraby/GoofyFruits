import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class SaveAndLoad 
{
	public static void save(GoofyFruitsEngine engine)
	{

		try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("GameEngine.ser");
	         ObjectOutputStream out =
	                            new ObjectOutputStream(fileOut);
	         out.writeObject(engine);
	         out.close();
	          fileOut.close();
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	      	
	}
	
	public static GoofyFruitsEngine load()
	{
		GoofyFruitsEngine engine= null ;
		try
        {
           FileInputStream fileIn =
                         new FileInputStream("GameEngine.ser");
           ObjectInputStream in = new ObjectInputStream(fileIn);
           engine = (GoofyFruitsEngine) in.readObject();
           in.close();
           fileIn.close();
           return engine;
       }catch(IOException i)
       {
           i.printStackTrace();
       }catch(ClassNotFoundException c)
       {
           System.out.println("Employee class not found");
           c.printStackTrace();
       }
		return engine;	
	}

}
