import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.Queue;



@SuppressWarnings("serial")
public class FallingItemsPool implements Serializable{
	private Queue<FallingItem> fruits ; 
	private int instanceCount=0;
	private final  int maxInstances= 60;
	private static FallingItemsPool poolInstance ;
	private FallingItemsFactory factory;
   
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private FallingItemsPool(){
		factory = FallingItemsFactory.getInstance();
		fruits = new LinkedList();
	}
	public static FallingItemsPool getInstace(){
		if( poolInstance == null){
		   poolInstance= new FallingItemsPool ();
		   return poolInstance;
		}
		return poolInstance;
	
	}
		
		
		
	
	public FallingItem acquireItem() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		if(instanceCount==maxInstances){
			// return object till there is in the pool or return null
            if(fruits.peek()!=null){
            	return fruits.poll();	
            }else{
            	return null;
            }
			
			
		}else{
		 // create object & increase the instance count
			instanceCount++;
			return factory.getFallingItem();
		
		}
	}
	public void release(FallingItem fruit)
	{
		fruit.setX(0);
		fruit.setY(0);
		fruit.setAngle(0);
		fruits.add(fruit);
	
	}
 public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	 
     
     FallingItemsPool pool = FallingItemsPool.getInstace();
     FallingItem fruit = new FallingItem();
     System.out.println(pool.acquireItem());
     System.out.println(pool.acquireItem());
     System.out.println(pool.acquireItem());
     System.out.println(pool.acquireItem());
     System.out.println(pool.acquireItem());
     System.out.println(pool.acquireItem());
     pool.release(fruit);
   System.out.println(pool.acquireItem());
     
}

}
