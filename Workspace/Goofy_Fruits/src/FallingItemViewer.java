import java.awt.Container;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class FallingItemViewer
{

	private ImageIcon objIcon ;
	private JLabel objLabel ;
	private RotatedIcon objRi;
	private Container objCont;
	private FallingItem item ;
	
	
	/**
	 * Constructor
	 * @param itm
	 * @param objCont
	 */
	public FallingItemViewer(FallingItem itm ,Container objCont)
	{
		this.objCont = objCont ;
		this.item = itm ;
		objIcon = new ImageIcon(item.getImageUrl());
		objRi = new RotatedIcon (objIcon,0);
		objLabel = new JLabel(objRi);
		objLabel.setBounds(item.getX(), item.getY(), item.getWidth(), item.getHeight());
	}
	
	
	/**
	 * @return the item
	 */
	public FallingItem getItem() {
		return item;
	}


	/**
	 * @param item the item to set
	 */
	public void setItem(FallingItem itm) {
		this.item = itm ;
		objIcon = new ImageIcon(item.getImageUrl());
		objRi.setIcon(objIcon);
		objRi.setAngle(item.getAngle());
		objLabel.setIcon(objRi);
		objLabel.setBounds(item.getX(), item.getY(), item.getWidth(), item.getHeight());
	}


	/**
	 * adds the Object to the contentPane
	 */
	public void add()
	{
		objCont.add(objLabel,0);
	}
	
	/**
	 * removes the Object to the contentPane
	 */
	public void remove()
	{
		objCont.remove(objLabel);
	}
	
	
	/**
	 * rotates the object by the specified item
	 * @param angle
	 */
	public void rotate(double angle)
	{
		objRi.setAngle(angle);
	}
	
	
	/**
	 * sets the location of the object
	 * @param x
	 * @param y
	 */
	public void setLocation( int x , int y )
	{
		objLabel.setLocation(x, y);
	}
	
	
	
}
