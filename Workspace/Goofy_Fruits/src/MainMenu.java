import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class MainMenu extends JFrame
{

	private JLabel background ;
	private ImageIcon bgIcon;

	private static final int DELAY = 100;   // Ms (polling interval)

	private Timer pollTimer;   // timer which triggers the polling
	
	private GamePadController gpController1;
	private GamePadController gpController2;
	
	private Container gameContainer ;
	
	private JLabel selectJLabel ;
	private ImageIcon selectIcon ;

	private int selector = 0 ;
	
	public MainMenu()
	{
		
		gpController1 = new GamePadController(1);
		gpController2 = new GamePadController(2);
		
		 setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	     setResizable(false);
	     setUndecorated(true);
	     setSize(1366, 768);
		
	     gameContainer = getContentPane();
	     
	     selectIcon = new ImageIcon("D:\\Projects\\Games\\Java Games\\GoofyFruits\\GameImages\\select.png");
		 selectJLabel = new JLabel(selectIcon);
		 selectJLabel.setBounds(880,350,500,64);
		 gameContainer.add(selectJLabel);
	     
	     bgIcon = new ImageIcon("D:\\Projects\\Games\\Java Games\\GoofyFruits\\GameImages\\intrance.png");
		 background = new JLabel(bgIcon);
		 background.setBounds(0, 0, 1366, 768);
		 gameContainer.add(background);
		 
		
		
		 setVisible(true);
		 startPolling();
	}
	
	private void startPolling()
	  {
	    ActionListener pollUpdateAction = new ActionListener() 
	    {
	      public void actionPerformed(ActionEvent e) 
	      {
	    	  runGPController1();
	    	  runGPController2();
	    	  if(selector==0)
	    	  {
	    		  selectJLabel.setLocation(880, 350);
	    	  }
	    	  else if (selector ==1)
	    	  {
	    		  selectJLabel.setLocation(880, 430);
	    	  }
	    	  else if (selector == 2)
	    	  {
	    		  selectJLabel.setLocation(880, 520);
	    	  }
	    		  
	    	
	      }
	    };
	   
	    pollTimer = new Timer(DELAY, pollUpdateAction);
	    pollTimer.start();
	  }
	
	/**
	 * run the gamePadController number 1
	 */
	@SuppressWarnings("unused")
	public void runGPController1()
	{
		gpController1.poll();
	    int dir = gpController1.getXYStickDir();
      if(dir == GamePadController.SOUTH)
      {
    	  if(selector<2)
    	  {
    		  selector++;
    	  }
      	
      }
      else if(dir == GamePadController.NORTH)
      {
    	  if(selector>0)
    	  {
    		  selector--;
    	  }
      }
      
      if(gpController1.getButtons()[2]==true)
      {
    	  if(selector==0)
    	  {
    		  try {
    			pollTimer.stop();
				GoofyFruitsViewer game = new GoofyFruitsViewer();
				this.setVisible(false);
			} catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	  }
    	  else if (selector ==1)
    	  {
    		  try {
      			pollTimer.stop();
  				GoofyFruitsViewer game = new GoofyFruitsViewer();
				this.setVisible(false);
  			} catch (Exception e) 
  			{
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			} 
    	  }
    	  else if (selector == 2)
    	  {
    		  System.exit(0);
    	  }
      }
	}
	
	
	/**
	 * run the gamePadController number 2
	 */
	public void runGPController2()
	{
		gpController2.poll();
	    int dir = gpController2.getXYStickDir();
      if(dir == GamePadController.SOUTH)
      {
    	  
     
      }
      else if(dir == GamePadController.NORTH)
      {
    	  
      }
	}
	
	
	@SuppressWarnings("unused")
	public static void main(String[] args) 
	{
	MainMenu mainMenu = new MainMenu();
	}

}
