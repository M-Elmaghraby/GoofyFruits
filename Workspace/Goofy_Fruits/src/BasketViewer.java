import java.awt.Container;
import java.util.Iterator;


public class BasketViewer 
{

	private Basket basket ;
	private FallingItemViewer[] basketViewer;
	private Iterator<FallingItem> iterator ;
	private final int MAXIMUM_SIZE = 21 ;
	@SuppressWarnings("unused")
	private Container container;
	@SuppressWarnings("unused")
	private FallingItemViewer dummy ;
	
	public BasketViewer(Basket basket , Container container)
	{
		this.basket = basket ;
		this.container = container;
		iterator = basket.getIterator();
		basketViewer = new FallingItemViewer[MAXIMUM_SIZE];
		dummy = new FallingItemViewer(new DummyItem(), container);
		
		for(int i = 0 ; i < MAXIMUM_SIZE ; i++)
		{
			basketViewer[i]= new FallingItemViewer(new DummyItem(), container);
		}
	}
	
	public  void update()
	{
		
		int i = basket.getSize()-1 ;
		iterator = basket.getIterator();
		while(iterator.hasNext())
		{

			basketViewer[i].setItem(iterator.next());
			basketViewer[i].add();

			i--;
		}
		for(i = basket.getSize() ; i<basketViewer.length ; i++)
		{
			basketViewer[i].remove();
		}
		
	}
	
	
	
}
