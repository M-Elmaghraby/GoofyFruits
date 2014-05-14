import java.io.Serializable;


@SuppressWarnings("serial")
public class Player extends GameObject implements ReadOnlyGameObjectIF , Serializable
{
	private final int GOOFY_WIDTH = 150 ;
	private final int GOOFY_HEIGHT = 204 ;
	private final String GOOFY_GOING_RIGHT = "D:\\Projects\\Games\\Java Games\\GoofyFruits\\GameImages\\goofy-right.png"; 
	private final String GOOFY_GOING_LEFT = "D:\\Projects\\Games\\Java Games\\GoofyFruits\\GameImages\\goofy-left.png"; 
	private Basket basket ;
	private int score ;
	
	/**
	 * Player Constructor
	 */
	
	public Player()
	{
		width = GOOFY_WIDTH ;
		height = GOOFY_HEIGHT ;
		imageUrl = GOOFY_GOING_RIGHT ;
		basket = new Basket();
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public int getScore() {
		return score;
	}

	/**
	 * 
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}




	/**
	 * @return the basket
	 */
	public Basket getBasket() {
		return basket;
	}

	/**
	 * @param basket the basket to set
	 */
	public void setBasket(Basket basket) {
		this.basket = basket;
	}

	/**
	 * Method to change the player image to the right image
	 */
	public void goLeft()
	{
		imageUrl = GOOFY_GOING_LEFT ;
	}
	
	/**
	 * Method to change the player image to the left image
	 */
	public void goRight()
	{
		imageUrl = GOOFY_GOING_RIGHT ;
	}

}
