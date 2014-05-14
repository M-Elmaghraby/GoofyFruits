
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

@SuppressWarnings("serial")
public class GoofyFruitsEngine implements Serializable{
	private Player player1;
	private Player player2;
	private FallingItemsPool pool ;
	private FallingItem[] player1Items ;
	private FallingItem[] player2Items ;

	public GoofyFruitsEngine() throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException 
	{
		pool = FallingItemsPool.getInstace();

		player1 = new Player();
		player1.setX(0);
		player1.setY(540);
		player1.getBasket().setX(0);
		player1.getBasket().setY(540);
		player2 = new Player();
		player2.setX(650);
		player2.setY(540);
		player2.getBasket().setX(650);
		player2.getBasket().setY(540);
		
		player1Items = new FallingItem[6];
		player2Items = new FallingItem[6];
		
		for(int i = 0 ; i<6 ; i++)
		{
			player1Items[i] = pool.acquireItem();
			player2Items[i] = pool.acquireItem();
			player2Items[i].setX(1300);

		}
		
			
		
	}

	/**
	 * @return the player1Items
	 */
	public FallingItem[] getPlayer1Items() {
		return player1Items;
	}

	/**
	 * @param player1Items the player1Items to set
	 */
	public void setPlayer1Items(FallingItem[] player1Items) {
		this.player1Items = player1Items;
	}

	/**
	 * @return the player2Items
	 */
	public FallingItem[] getPlayer2Items() {
		return player2Items;
	}

	/**
	 * @param player2Items the player2Items to set
	 */
	public void setPlayer2Items(FallingItem[] player2Items) {
		this.player2Items = player2Items;
	}
	/**
	 * @return the player1
	 */
	public Player getPlayer1() {
		return player1;
	}

	/**
	 * @return the player2
	 */
	public Player getPlayer2() {
		return player2;
	}

	/**
	 * Move Player1 Left
	 */
	public void movePlayer1Left() {
		if (player1.getX() > 0) {
			player1.goLeft();
			player1.move(-5, 0);
			player1.getBasket().move(-5);
		}
	}

	/**
	 * Move Player1 Right
	 */
	public void movePlayer1Right() {
		if (player1.getX() < 520) {
			player1.goRight();
			player1.move(+5, 0);
			player1.getBasket().move(+5);
		}
	}

	/**
	 * Move Player2 Left
	 */
	public void movePlayer2Left() {

		if (player2.getX() > 650) {
			player2.goLeft();
			player2.move(-5, 0);
			player2.getBasket().move(-5);

		}
	}

	/**
	 * Move Player2 Right
	 */
	public void movePlayer2Right() {

		if (player2.getX() < 1200) {
			player2.goRight();
			player2.move(+5, 0);
			player2.getBasket().move(+5);
		}
	}
	
	
		
	public FallingItem animateP1Path1(FallingItem item) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
	{
		if(item.getX()<50 )
	  	  {
	  	  int y = (int)-Math.sqrt(10000 - ((item.getX())*(item.getX())))+100;	  
	  	  item.setX(item.getX()+3);
	  	  item.setY(y);
	  	  
	  	  }
		else if(detectCollision1(item))
		  {
			try
			{
			item.setAngle(0);
			player1.getBasket().addItem(item);
			player1Items[0]=pool.acquireItem();
			return player1Items[0];
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		  }
	  	  else if (item.getY()<700)
	  	  {
		    	 item.move(0, 2);
		    	 item.setAngle(item.getAngle()+5);
	  	  }
	  	else
		  {
			  pool.release(item);
			  player1Items[0] = pool.acquireItem();
		  }
		      return player1Items[0];
	}
	
	
	public FallingItem animateP1Path2(FallingItem item) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
	{
		if(item.getX()< 170 )
	  	  {
	  	  int y = (int)-Math.sqrt(40000 - ((item.getX())*(item.getX())))+200;	  
	  	  item.setX(item.getX()+3);
	  	  item.setY(y);
	  	  
	  	  }
		else if(detectCollision1(item))
		  {
			try
			{
				
				item.setAngle(0);
			player1.getBasket().addItem(item);
			player1Items[1]= pool.acquireItem();
			return player1Items[1];
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		  }
	  	  else if (item.getY()<700)
	  	  {
		    	 item.move(0, 2);
		    	 item.setAngle(item.getAngle()+5);
	  	  }
	  	else
		  {
	  		 pool.release(item);
			  player1Items[1] = pool.acquireItem();
		  }
		      return player1Items[1];
	}
	
	
	public FallingItem animateP1Path3(FallingItem item) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
	{
		if(item.getX()< 400 )
	  	  {
	  	  int y = (int)-Math.sqrt(202500 - ((item.getX())*(item.getX())))+450;	  
	  	  item.setX(item.getX()+3);
	  	  item.setY(y);
	  	  
	  	  }
		else if(detectCollision1(item))
		  {
			try
			{
				item.setAngle(0);
			player1.getBasket().addItem(item);
			player1Items[2]= pool.acquireItem();
			return player1Items[2];
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		  }
	  	  else if (item.getY()<700)
	  	  {
		    	 item.move(0, 2);
	  	  }
	  	else
		  {
	  		 pool.release(item);
			 player1Items[2] = pool.acquireItem();
		  }
		     player1Items[2].setAngle(player1Items[2].getAngle()+5);
		     return player1Items[2];

	}
	
	public FallingItem animateP1Path4(FallingItem item) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
	{
		if(item.getX()< 350 )
	  	  {
	  	  int y = (int)-Math.sqrt(360000 - ((item.getX())*(item.getX())))+600;	  
	  	  item.setX(item.getX()+3);
	  	  item.setY(y);
	  	  
	  	  }
		else if(detectCollision1(item))
		  {
			try
			{
				item.setAngle(0);
			player1.getBasket().addItem(item);
			player1Items[3]= pool.acquireItem();
			return player1Items[3];
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		  }
	  	  else if (item.getY()<700)
	  	  {
		    	 item.move(0, 2);

	  	  }
	  	else
		  {
	  		pool.release(item);
			 player1Items[3] = pool.acquireItem();
		  }
		     player1Items[3].setAngle(player1Items[3].getAngle()+5);
		     return player1Items[3];

	}
	
	public FallingItem animateP1Path5(FallingItem item) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
	{
		if(item.getX()< 500 )
	  	  {
	  	  int y = (int)-Math.sqrt(1000000 - ((item.getX())*(item.getX())))+1000;	  
	  	  item.setX(item.getX()+3);
	  	  item.setY(y);
	  	  
	  	  }
		  else if(detectCollision1(item))
		  {
			try
			{
				
				item.setAngle(0);
			player1.getBasket().addItem(item);
			player1Items[4]= pool.acquireItem();
			return player1Items[4];
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		  }
	  	  else if (item.getY()<700)
	  	  {
		    	 item.move(0, 2);
	  	  }
	  	else
		  {
	  		pool.release(item);
			 player1Items[4] = pool.acquireItem();
		  }
		     player1Items[4].setAngle(player1Items[4].getAngle()+5);
		     return player1Items[4];

	}
	
	public FallingItem animateP1Path6(FallingItem item) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
	{
		if(item.getX()< 220 )
	  	  {
	  	  int y = (int)-Math.sqrt(250000 - ((item.getX())*(item.getX())))+500;	  
	  	  item.setX(item.getX()+3);
	  	  item.setY(y);
	  	  
	  	  }
		else if(detectCollision1(item))
		  {
			try
			{
				
				item.setAngle(0);
			player1.getBasket().addItem(item);
			player1Items[5]= pool.acquireItem();
			return player1Items[5];
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		  }
	  	  else if (item.getY()<700)
	  	  {
		    	 item.move(0, 2);

	  	  }
	  	else
		  {
	  		pool.release(item);
			 player1Items[5] = pool.acquireItem();
		  }
		     player1Items[5].setAngle(player1Items[5].getAngle()+5);
		     return player1Items[5];

	}
	
	public FallingItem animateP2Path1(FallingItem item) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
	{
		if(item.getX()> 1300-30 )
	  	  {
	  	  int y = (int)-Math.sqrt(10000 - ((item.getX()-1300)*(item.getX()-1300)))+100;	  
	  	  item.setX(item.getX()-3);
	  	  item.setY(y);
	  	  
	  	  }
		else if(detectCollision2(item))
		  {
			try
			{
				item.setAngle(0);
			player2.getBasket().addItem(item);
			player2Items[0]= pool.acquireItem();
			player2Items[0].setX(1300);
			player2Items[0].setY(0);
			
			return player2Items[0];
	
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		  }
	  	  else if (item.getY()<700)
	  	  {
		    	 item.move(0, 2);
		    	 item.setAngle(item.getAngle()+5);
	  	  }
		
	  	else
		  {
	  		pool.release(item);
			 player2Items[0] = pool.acquireItem();
			 player2Items[0].setX(1300);
		  }
		     player2Items[0].setAngle(player2Items[0].getAngle()+5);
		     return player2Items[0];

	}
	
	
	public FallingItem animateP2Path2(FallingItem item) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
	{
		if(item.getX()> 1300-170 )
	  	  {
	  	  int y = (int)-Math.sqrt(40000 - ((item.getX()-1300)*(item.getX()-1300)))+200;	  
	  	  item.setX(item.getX()-3);
	  	  item.setY(y);
	  	  
	  	  }
		else if(detectCollision2(item))
		  {
			try
			{
				item.setAngle(0);
				player2.getBasket().addItem(item);
			
			
			player2Items[1]= pool.acquireItem();
			player2Items[1].setX(1300);
			return player2Items[1];
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		  }
	  	  else if (item.getY()<700)
	  	  {
		    	 item.move(0, 2);
		    	 item.setAngle(item.getAngle()+5);
	  	  }
	  	else
		  {
	  		pool.release(item);
			 player2Items[1] = pool.acquireItem();
			 player2Items[1].setX(1300);
			 player2Items[1].setY(0);
		  }
		     player2Items[1].setAngle(player2Items[1].getAngle()+5);
		     return player2Items[1];
	}
	
	
	public FallingItem animateP2Path3(FallingItem item) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
	{
		if(item.getX()> 1300- 400 )
	  	  {
	  	  int y = (int)-Math.sqrt(202500 - ((item.getX()-1300)*(item.getX()-1300)))+450;	  
	  	  item.setX(item.getX()-3);
	  	  item.setY(y);
	  	  
	  	  }
		else if(detectCollision2(item))
		  {
			try
			{
				item.setAngle(0);
				player2.getBasket().addItem(item);
			
			player2Items[2]= pool.acquireItem();
			player2Items[2].setX(1300);
			player2Items[2].setY(0);
			return player2Items[2];
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		  }
	  	  else if (item.getY()<700)
	  	  {
		    	 item.move(0, 2);
	  	  }
	  	else
		  {
	  		pool.release(item);
			 player2Items[2] = pool.acquireItem();
			 player2Items[2].setX(1300);
		  }
		     player2Items[2].setAngle(player2Items[2].getAngle()+5);
		     return player2Items[2];
	}
	
	public FallingItem animateP2Path4(FallingItem item) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
	{
		if(item.getX()> 1300- 350 )
	  	  {
	  	  int y = (int)-Math.sqrt(360000 - ((item.getX()-1300)*(item.getX()-1300)))+600;	  
	  	  item.setX(item.getX()-3);
	  	  item.setY(y);
	  	  
	  	  }
		else if(detectCollision2(item))
		  {
			try
			{
				
				item.setAngle(0);
				player2.getBasket().addItem(item);
			player2Items[3]= pool.acquireItem();
			player2Items[3].setX(1300);
			player2Items[3].setY(0);
			return player2Items[3];
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		  }
	  	  else if (item.getY()<700)
	  	  {
		    	 item.move(0, 2);

	  	  }
	  	else
		  {
	  		pool.release(item);
			 player2Items[3] = pool.acquireItem();
			 player2Items[3].setX(1300);
		  }
		     player2Items[3].setAngle(player2Items[3].getAngle()+5);
		     return player2Items[3];
	}
	
	public FallingItem animateP2Path5(FallingItem item) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
	{
		if(item.getX()> 1300- 500 )
	  	  {
	  	  int y = (int)-Math.sqrt(1000000 - ((item.getX()-1300)*(item.getX()-1300)))+1000;	  
	  	  item.setX(item.getX()-3);
	  	  item.setY(y);
	  	  
	  	  }
		else if(detectCollision2(item))
		  {
			try
			{
				item.setAngle(0);
				player2.getBasket().addItem(item);
			player2Items[4]= pool.acquireItem();
			player2Items[4].setX(1300);
			player2Items[4].setY(0);
			return player2Items[4];
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		  }
	  	  else if (item.getY()<700)
	  	  {
		    	 item.move(0, 2);

	  	  }
	  	else
		  {
	  		pool.release(item);
			 player2Items[4] = pool.acquireItem();
			 player2Items[4].setX(1300);
		  }
		     player2Items[4].setAngle(player2Items[4].getAngle()+5);
		     return player2Items[4];
	}
	
	public FallingItem animateP2Path6(FallingItem item) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
	{
		if(item.getX() > 1300-220 )
	  	  {
	  	  int y = (int)-Math.sqrt(250000 - ((item.getX()-1300)*(item.getX()-1300)))+500;	  
	  	  item.setX(item.getX()-3);
	  	  item.setY(y);
	  	  
	  	  }
		else if(detectCollision2(item))
		  {
			try
			{
				item.setAngle(0);
				player2.getBasket().addItem(item);
			player2Items[5]= pool.acquireItem();
			player2Items[5].setX(1300);
			player2Items[5].setY(0);
			return player2Items[5];
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		  }
	  	  else if (item.getY()<700)
	  	  {
		    	 item.move(0, 2);

	  	  }
	  	else
		  {
	  		pool.release(item);
			 player2Items[5] = pool.acquireItem();
			 player2Items[5].setX(1300);
		  }
		     player2Items[5].setAngle(player2Items[5].getAngle()+5);
		     return player2Items[5];
	}

		
	public boolean detectCollision1(FallingItem item)
	{
		boolean result =((item.getY()<=player1.getBasket().getY())&&(item.getY()+40>=player1.getBasket().getY()) && (item.getX()<= player1.getBasket().getX()+95) && (item.getX()>= player1.getBasket().getX()+50)); 
		return (result);
	}
	
	public boolean detectCollision2(FallingItem item)
	{
		boolean result =((item.getY()<=player2.getBasket().getY())&&(item.getY()+40>=player2.getBasket().getY()) && (item.getX()<= player2.getBasket().getX()+95) && (item.getX()>= player2.getBasket().getX()+50)); 
		return (result);
	}
	
	
}
