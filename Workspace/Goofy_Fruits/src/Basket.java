import java.io.Serializable;
import java.util.Iterator;
import java.util.Stack;

@SuppressWarnings("serial")
public class Basket implements Serializable
{

	private Stack <FallingItem> basket ;
	private int x ;
	private int y ;
	private FallingItemsPool pool ;
	
	
	


	int dy = 15 ;

	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	public Basket()
	{
		basket = new Stack <FallingItem>() ;
		pool = FallingItemsPool.getInstace();
	}

	/**
	 * @return the iterator
	 */
	public Iterator<FallingItem> getIterator() {
		return basket.iterator();
	}
	

	/**
	 * @param basket the basket to set
	 */
	public void setBasket(Stack <FallingItem> basket) {
		this.basket = basket;
	}

	/**
	 * @return the basket
	 */
	public Stack <FallingItem> getBasket() {
		return basket;
	}
	
	public void addItem(FallingItem item) throws Exception
	{

		if(basket.size()<21)
		{
		if(basket.size()==0)
		{
		dy = 22 ;
		if((item.getClass().getName().equals("YellowApple")||item.getClass().getName().equals("GreenApple")||(item.getClass().getName().equals("RedApple"))))
		{
			item.setX(x+55);
			
		}
		else if(item.getClass().getName().equals("Banana"))
		{
			item.setX(x+48);
		}
		else if (item.getClass().getName().equals("Coconut"))
		{
			item.setX(x+55);
			dy=25;
		}
		item.setY(y-dy);
		y-=dy;
		}
		else
		{
			dy = 15 ;
			if(basket.peek().getClass().getName().equals("Banana"))
			{
				dy = 13 ;
			}
			if((item.getClass().getName().equals("YellowApple")||item.getClass().getName().equals("GreenApple")||(item.getClass().getName().equals("RedApple"))))
			{
				item.setX(x+55);
				item.setY(y-dy);
				y-=dy;
			}
			else if(item.getClass().getName().equals("Banana"))
			{
				item.setX(x+48);
				item.setY(y-dy);
				y-=dy;
			}
			else if (item.getClass().getName().equals("Coconut"))
			{
				item.setX(x+53);
				item.setY(y-dy);
				y-=dy;
			}
		}
		basket.push(item);
		}
		else
		{
			throw new Exception("Full Basket Exception");
		}
	}
	
	public FallingItem getTopItem() throws Exception
	{
		if(basket.size()!=0)
		{
		return (FallingItem) basket.pop();
		}
		else
		{
			throw new Exception("Empty Basket Exception");
		}
	}
	
	public boolean checkMatch() throws Exception
	{
		
		if(basket.size()>=3)
		{
			FallingItem item1 = getTopItem();
			FallingItem item2 = getTopItem();
			FallingItem item3 = getTopItem();
			
			if(item1.getClass().getName().equals(item2.getClass().getName()) && item2.getClass().getName().equals(item3.getClass().getName()))
			{
				if(basket.size()==0 && item1.getClass().getName().equals("Banana"))
				{
					y+=(13*2)+22;
				}
				else if (basket.size()==0 && !item1.getClass().getName().equals("Banana"))
				{
					y+=(15*2)+22;
				}
				else if (basket.size()!=0 && item1.getClass().getName().equals("Banana"))
				{
					y+=(13*3);
				}
				else
				{
					y+=(15*3);
				}
				
				pool.release(item3);
				pool.release(item2);
				pool.release(item1);
							
				return true ;
			}
			basket.push(item3);
			basket.push(item2);
			basket.push(item1);
		}
		return false ;
	}
	
	public void move(int dx)
	{
		x+=dx;
		Iterator <FallingItem>it = basket.iterator();
		while(it.hasNext())
		{
			it.next().move(dx, 0);
		}
	}
	
	public int getSize()
	{
		return basket.size() ;
	}
	
}
