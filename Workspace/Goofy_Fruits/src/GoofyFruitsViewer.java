import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class GoofyFruitsViewer extends JFrame
{
	
	private static final int DELAY = 15;   // Ms (polling interval)

	private Timer pollTimer;   // timer which triggers the polling
	
	private GoofyFruitsEngine engine;

	private GamePadController gpController1;
	private GamePadController gpController2;
	
	private JLabel background ;
	
	private JLabel player1Label ;
	private JLabel player2Label ;
	private JLabel scoreImageLabel ;

	private JLabel player1Score ;
	private JLabel player2Score ;
	
	private JLabel playerWins ;
	private ImageIcon playerWinsIcon ;
	
	private ImageIcon bgIcon;
    private ImageIcon scoreIcon ;
	private ImageIcon p1Icon;
	private ImageIcon p2Icon;

	
	private FallingItemViewer fruit1;
	private FallingItemViewer fruit2;
	private FallingItemViewer fruit3;
	private FallingItemViewer fruit4;
	private FallingItemViewer fruit5;
	private FallingItemViewer fruit6;
	
	private FallingItemViewer fruit7;
	private FallingItemViewer fruit8;
	private FallingItemViewer fruit9;
	private FallingItemViewer fruit10;
	private FallingItemViewer fruit11;
	private FallingItemViewer fruit12;
	
    private AudioPlayerManager audioPlayer ;

	
	
	private BasketViewer basket1Viewer ;
	private BasketViewer basket2Viewer ;
	
	
	private Container gameContainer ;
	
	
	public GoofyFruitsViewer() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
	    super("GamePad Viewer");
	    
	    gpController1 = new GamePadController(1);
		gpController2 = new GamePadController(2);
		
		engine = new GoofyFruitsEngine();
		
		try {
			initComponents();
		} catch (Exception e) 
		{
		}
		 try {
				audioPlayer= AudioPlayerManager.getInstance();
			    Thread thread = new Thread(audioPlayer);
			    thread.start();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		 startPolling();
	}
	
	
	public GoofyFruitsViewer(GoofyFruitsEngine engine) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
	    super("GamePad Viewer");
	    
	    gpController1 = new GamePadController(1);
		gpController2 = new GamePadController(2);
		
		this.engine = engine;
		
		try {
			initComponents();
		} catch (Exception e) 
		{
		}
		 try {
				audioPlayer= AudioPlayerManager.getInstance();
			    Thread thread = new Thread(audioPlayer);
			    thread.start();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		 startPolling();
	}

	private void initComponents() throws Exception 
	{
		
		 setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	     setResizable(false);
	     setUndecorated(true);
	     setSize(1366, 768);
		
	     gameContainer = getContentPane();
	     
	     
		 scoreIcon = new ImageIcon("D:\\Projects\\Games\\Java Games\\GoofyFruits\\GameImages\\score.png");
		 scoreImageLabel = new JLabel(scoreIcon);
		 scoreImageLabel.setBounds(500, 40, 334, 164);
		 gameContainer.add(scoreImageLabel);
		 
		  
		 player1Score = new JLabel("0");
		 player1Score.setFont(new java.awt.Font("Segoe Print", 1, 48));
		 player1Score.setForeground(new java.awt.Color(255, 255, 0));
		 player1Score.setBounds(570, 10, 100, 50);
		 gameContainer.add(player1Score);
		 
		 player2Score = new JLabel("0");
		 player2Score.setFont(new java.awt.Font("Segoe Print", 1, 48));
		 player2Score.setForeground(new java.awt.Color(255, 255 , 0));
		 player2Score.setBounds(700, 10, 100, 50);
		 gameContainer.add(player2Score);
		 
		 playerWinsIcon = new ImageIcon("D:\\Projects\\Games\\Java Games\\GoofyFruits\\GameImages\\p1wins.png");
		 playerWins = new JLabel(playerWinsIcon);
		 playerWins.setBounds(120, 200, 1075, 198);
		 
		 setPlayer1();
		 setPlayer2();
		 
		  fruit1 = new FallingItemViewer(engine.getPlayer1Items()[0], gameContainer);
		  fruit1.add();	
          
		  fruit2 = new FallingItemViewer(engine.getPlayer1Items()[1], gameContainer);
		  fruit2.add();	

		  fruit3 = new FallingItemViewer(engine.getPlayer1Items()[2], gameContainer);
		  fruit3.add();	

		  fruit4 = new FallingItemViewer(engine.getPlayer1Items()[3], gameContainer);
		  fruit4.add();	

		  fruit5 = new FallingItemViewer(engine.getPlayer1Items()[4], gameContainer);
		  fruit5.add();	

		  fruit6 = new FallingItemViewer(engine.getPlayer1Items()[5], gameContainer);
		  fruit6.add();
		  
		  fruit7 = new FallingItemViewer(engine.getPlayer2Items()[0], gameContainer);
		  fruit7.add();	
          
		  fruit8 = new FallingItemViewer(engine.getPlayer2Items()[1], gameContainer);
		  fruit8.add();	

		  fruit9 = new FallingItemViewer(engine.getPlayer2Items()[2], gameContainer);
		  fruit9.add();	

		  fruit10 = new FallingItemViewer(engine.getPlayer2Items()[3], gameContainer);
		  fruit10.add();	

		  fruit11 = new FallingItemViewer(engine.getPlayer2Items()[4], gameContainer);
		  fruit11.add();	

		  fruit12 = new FallingItemViewer(engine.getPlayer2Items()[5], gameContainer);
		  fruit12.add();

		  basket1Viewer = new BasketViewer(engine.getPlayer1().getBasket(), gameContainer);
		  basket1Viewer.update();
	 
		  basket2Viewer = new BasketViewer(engine.getPlayer2().getBasket(), gameContainer);
		  basket2Viewer.update();
		  
		 bgIcon = new ImageIcon("D:\\Projects\\Games\\Java Games\\GoofyFruits\\GameImages\\background.png");
		 background = new JLabel(bgIcon);
		 background.setBounds(0, 0, 1366, 768);
		 gameContainer.add(background);
		
		 setVisible(true);

		 
		
	}
	
	public void setScore1(int score)
	{
		player1Score.setText(score+"");
	}
	
	
	public void setScore2(int score)
	{
		player2Score.setText(score+"");
	}
	
	
	public GoofyFruitsEngine getEngine()
	{
		return engine ;
	}
	
	/**
	 * Method to set Player 1
	 */
	public void setPlayer1()
	{
		ReadOnlyGameObjectIF player1 = engine.getPlayer1();
		
		p1Icon = new ImageIcon(player1.getImageUrl());
		player1Label = new JLabel(p1Icon);
		player1Label.setBounds(player1.getX(), player1.getY(), player1.getWidth(), player1.getHeight());
		gameContainer.add(player1Label);
		
	}
	
	/**
	 * Method to set Player 2
	 */
	public void setPlayer2()
	{
		ReadOnlyGameObjectIF player2 = engine.getPlayer2();
		
		p2Icon = new ImageIcon(player2.getImageUrl());
		player2Label = new JLabel(p2Icon);
		player2Label.setBounds(player2.getX(), player2.getY(), player2.getWidth(), player2.getHeight());
		gameContainer.add(player2Label);
		
	}
	
	/**
	 * Method to update Player 1 view
	 */
	public void viewPlayer1()
	{
		ReadOnlyGameObjectIF player1 = engine.getPlayer1();
		p1Icon = new ImageIcon(player1.getImageUrl());
		player1Label.setIcon(p1Icon);
		player1Label.setLocation(player1.getX(), player1.getY());
	}
	
	/**
	 * Method to update Player 2 view
	 */
	public void viewPlayer2()
	{
		ReadOnlyGameObjectIF player2 = engine.getPlayer2();
		p2Icon = new ImageIcon(player2.getImageUrl());
		player2Label.setIcon(p2Icon);
		player2Label.setLocation(player2.getX(), player2.getY());
	}
	  /* Set up a timer which is activated every DELAY Ms
    and polls the game pad and updates the GUI. 
    Safe since the action handler is executed in the 
    event-dispatching thread. */
	
	private void startPolling()
	  {
	    ActionListener pollUpdateAction = new ActionListener() 
	    {
	      public void actionPerformed(ActionEvent e) 
	      {
	    	  runGPController1();
	    	  runGPController2();
	    	  
	    	  try
	    	  {
	    	  fruit1.setItem(engine.animateP1Path1(engine.getPlayer1Items()[0]));
	    	  fruit2.setItem(engine.animateP1Path2(engine.getPlayer1Items()[1]));
	    	  fruit3.setItem(engine.animateP1Path3(engine.getPlayer1Items()[2]));
	    	  fruit4.setItem(engine.animateP1Path4(engine.getPlayer1Items()[3]));
	    	  fruit5.setItem(engine.animateP1Path5(engine.getPlayer1Items()[4]));
	    	  fruit6.setItem(engine.animateP1Path6(engine.getPlayer1Items()[5]));
	    	  fruit7.setItem(engine.animateP2Path1(engine.getPlayer2Items()[0]));
	    	  fruit8.setItem(engine.animateP2Path2(engine.getPlayer2Items()[1]));
	    	  fruit9.setItem(engine.animateP2Path3(engine.getPlayer2Items()[2]));
	    	  fruit10.setItem(engine.animateP2Path4(engine.getPlayer2Items()[3]));
	    	  fruit11.setItem(engine.animateP2Path5(engine.getPlayer2Items()[4]));
	    	  fruit12.setItem(engine.animateP2Path6(engine.getPlayer2Items()[5]));
	    	  }
	    	  catch (Exception e2) 
	    	  {
	    		  System.out.println("error in pool");
			  }
	    	  basket1Viewer.update();
	          basket2Viewer.update();
	          
	        try{
				 if(engine.getPlayer1().getBasket().checkMatch())
				 {
					 player1Score.setText(Integer.parseInt(player1Score.getText())+1+"");
					 engine.getPlayer1().setScore(Integer.parseInt(player1Score.getText()));
				 }
			    } catch (Exception e1) {}
	        try {
				if(engine.getPlayer2().getBasket().checkMatch())
				{
					 player2Score.setText(Integer.parseInt(player2Score.getText())+1+"");
					 engine.getPlayer2().setScore(Integer.parseInt(player2Score.getText()));
				 }
			    } catch (Exception e1) {}
			    
			     if(Integer.parseInt(player1Score.getText())==20  || engine.getPlayer2().getBasket().getSize()==20)
			     {
			    	 pollTimer.stop();
			    	 gameContainer.removeAll();
			    	 gameContainer.add(playerWins);
			    	 gameContainer.add(background);
			    	 playerWins.setVisible(true);
			     }
			     
			     if(Integer.parseInt(player2Score.getText())==20  || engine.getPlayer1().getBasket().getSize()==20)
			     {
			    	 pollTimer.stop();
			    	 playerWinsIcon = new ImageIcon("D:\\Projects\\Games\\Java Games\\GoofyFruits\\GameImages\\p2wins.png");
			    	 playerWins.setIcon(playerWinsIcon);
			    	 gameContainer.removeAll();
			    	 gameContainer.add(playerWins);
			    	 gameContainer.add(background);
			    	 playerWins.setVisible(true);
			     }
			    
			    


	      }
	    };
	   
	    pollTimer = new Timer(DELAY, pollUpdateAction);
	    pollTimer.start();
	  }
	
	/**
	 * run the gamePadController number 1
	 */
	public void runGPController1()
	{
		gpController1.poll();
	    int dir = gpController1.getXYStickDir();
        if(dir == GamePadController.WEST)
        {
        	engine.movePlayer1Left();
        	viewPlayer1();
        	
        }
        else if(dir == GamePadController.EAST)
        {
        	engine.movePlayer1Right();
        	viewPlayer1();
        }
        
        
        if(gpController1.getButtons()[1]==true)
        {
        	System.exit(0);
        }
        if(gpController1.getButtons()[9]==true)
        {
        	SaveAndLoad.save(getEngine());
        }
	}
	
	
	/**
	 * run the gamePadController number 2
	 */
	public void runGPController2()
	{
		gpController2.poll();
	    int dir = gpController2.getXYStickDir();
        if(dir == GamePadController.WEST)
        {
         engine.movePlayer2Left();
         viewPlayer2();
        }
        else if(dir == GamePadController.EAST)
        {
        	engine.movePlayer2Right();
        	viewPlayer2();
        }
	}
}