
// GamePadController.java
// Andrew Davison, October 2006, ad@fivedots.coe.psu.ac.th

/* This controller supports a game pad with two
   analog sticks with axes (x,y) and (z,rz), 12 buttons, a 
   D-Pad acting as a point-of-view (POV) hat, and a 
   single rumbler.

   The sticks are assumed to be absolute and analog, while the 
   hat and buttons are absolute and digital.

   -----
   The sticks and hat data are accessed as compass directions
   (e.g. NW, NORTH). The compass constants are public so they can be 
   used in the rest of the application.

   The buttons values (booleans) can be accessed individually, or 
   together in an array. 

   The rumbler can be switched on/off, and its current status retrieved.
*/

import net.java.games.input.*;


public class GamePadController
{
  public static final int NUM_BUTTONS = 10;

  // public stick and hat compass positions 
  public static final int NUM_COMPASS_DIRS = 9;
  public static final int NW = 0;
  public static final int NORTH = 1;
  public static final int NE = 2;
  public static final int WEST = 3;
  public static final int NONE = 4;   // default value
  public static final int EAST = 5;
  public static final int SW = 6;
  public static final int SOUTH = 7;
  public static final int SE = 8;


  private Controller controller;

  private int gamePadNumber ;
  private Component[] comps;  // holds the components

  // comps[] indices for specific components
  private int xAxisIdx, yAxisIdx;
  private int[] buttonsIdx;   // indices for the buttons

  private Rumbler[] rumblers;
  


  public GamePadController(int gamepadNumber)
  {
    // get the controllers
	 
	this.gamePadNumber = gamepadNumber ;  
    ControllerEnvironment ce =
         ControllerEnvironment.getDefaultEnvironment();
    net.java.games.input.Controller[] cs = ce.getControllers();
    if (cs.length == 0) {
      System.out.println("No controllers found");
      System.exit(0);
    }
    else
      System.out.println("Num. controllers: " + cs.length);


    // get the game pad controller
    controller = findGamePad(cs);
    System.out.println("Game controller: " +
                       controller.getName() + ", " + 
                       controller.getType());

    // collect indices for the required game pad components
    findCompIndices(controller);

    findRumblers(controller);
  } // end of GamePadController()



  private Controller findGamePad(Controller[] cs)
  /* Search the array of controllers until a suitable game pad
     controller is found (eith of type GAMEPAD or STICK).
  */
  {
    Controller.Type type;
    int i = 0;
    while(i < cs.length) {
      type = cs[i].getType();
      if ((type == Controller.Type.GAMEPAD) ||
          (type == Controller.Type.STICK))
        break;
      i++;
    }
    
    if(gamePadNumber == 2)
    {
    	i++;
    	while(i < cs.length) {
    	      type = cs[i].getType();
    	      if ((type == Controller.Type.GAMEPAD) ||
    	          (type == Controller.Type.STICK))
    	        break;
    	      i++;
    	    }
    }
    
    if (i == cs.length) {
      System.out.println("No game pad found");
      System.exit(0);
    }
    else
      System.out.println("Game pad index: " + i);

    return cs[i];
  }  // end of findGamePad()


  private void findCompIndices(Controller controller)
  /* Store the indices for the analog sticks axes 
     (x,y) and (z,rz), POV hat, and
     button components of the controller.
  */
  {
    comps = controller.getComponents();
    if (comps.length == 0) {
      System.out.println("No Components found");
      System.exit(0);
    }
    else
      System.out.println("Num. Components: " + comps.length);

    // get the indices for the axes of the analog sticks: (x,y) 
    xAxisIdx = findCompIndex(comps, Component.Identifier.Axis.X, "x-axis");
    yAxisIdx = findCompIndex(comps, Component.Identifier.Axis.Y, "y-axis");


    // get POV hat index

    findButtons(comps);
  }  // end of findCompIndices()



  private int findCompIndex(Component[] comps, 
                         Component.Identifier id, String nm)
  /* Search through comps[] for id, returning the corresponding 
     array index, or -1 */
  {
    Component c;
    for(int i=0; i < comps.length; i++) {
      c = comps[i];
      if ((c.getIdentifier() == id) && !c.isRelative()) {
        System.out.println("Found " + c.getName() + "; index: " + i);
        return i;
      }
    }

    System.out.println("No " + nm + " component found");
    return -1;
  }  // end of findCompIndex()



  private void findButtons(Component[] comps)
  /* Search through comps[] for NUM_BUTTONS buttons, storing
     their indices in buttonsIdx[]. Ignore excessive buttons.
     If there aren't enough buttons, then fill the empty spots in
     buttonsIdx[] with -1's. */
  {
    buttonsIdx = new int[NUM_BUTTONS];
    int numButtons = 0;
    Component c;

    for(int i=0; i < comps.length; i++) {
      c = comps[i];
      if (isButton(c)) {    // deal with a button
        if (numButtons == NUM_BUTTONS)   // already enough buttons
          System.out.println("Found an extra button; index: " + i + ". Ignoring it");
        else {
          buttonsIdx[numButtons] = i;  // store button index
          System.out.println("Found " + c.getName() + "; index: " + i);
          numButtons++;
        }
      }
    }

    // fill empty spots in buttonsIdx[] with -1's
    if (numButtons < NUM_BUTTONS) {
      System.out.println("Too few buttons (" + numButtons +
                             "); expecting " + NUM_BUTTONS);
      while (numButtons < NUM_BUTTONS) {
        buttonsIdx[numButtons] = -1;
        numButtons++;
      }
    }
  }  // end of findButtons()



  private boolean isButton(Component c)
  /* Return true if the component is a digital/absolute button, and
     its identifier name ends with "Button" (i.e. the
     identifier class is Component.Identifier.Button).
  */
  {
    if (!c.isAnalog() && !c.isRelative()) {    // digital and absolute
      String className = c.getIdentifier().getClass().getName();
      // System.out.println(c.getName() + " identifier: " + className);
      if (className.endsWith("Button"))
        return true;
    }
    return false;
  }  // end of isButton()



  private void findRumblers(Controller controller)
  /* Find the rumblers. Use the last rumbler for making vibrations, 
     an arbitrary decision. */
  {
    // get the game pad's rumblers
    rumblers = controller.getRumblers();
    if (rumblers.length == 0) {
      System.out.println("No Rumblers found");
    }
    else {
      System.out.println("Rumblers found: " + rumblers.length);
    }
  }  // end of findRumblers()



  // ----------------- polling and getting data ------------------


  public void poll()
  // update the component values in the controller
  {  controller.poll();  }


  public int getXYStickDir()
  // return the (x,y) analog stick compass direction
  {
    if ((xAxisIdx == -1) || (yAxisIdx == -1)) {
      System.out.println("(x,y) axis data unavailable");
      return NONE;
    }
    else 
      return getCompassDir(xAxisIdx, yAxisIdx);
  } // end of getXYStickDir()


 

  private int getCompassDir(int xA, int yA)
  // Return the axes as a single compass value
  {
    float xCoord = comps[ xA ].getPollData();
    float yCoord = comps[ yA ].getPollData();
    // System.out.println("(x,y): (" + xCoord + "," + yCoord + ")");

    int xc = Math.round(xCoord);
    int yc = Math.round(yCoord);
    // System.out.println("Rounded (x,y): (" + xc + "," + yc + ")");

    if ((yc == -1) && (xc == -1))   // (y,x)
      return NW;
    else if ((yc == -1) && (xc == 0))
      return NORTH;
    else if ((yc == -1) && (xc == 1))
      return NE;
    else if ((yc == 0) && (xc == -1))
      return WEST;
    else if ((yc == 0) && (xc == 0))
      return NONE;
    else if ((yc == 0) && (xc == 1))
      return EAST;
    else if ((yc == 1) && (xc == -1))
      return SW;
    else if ((yc == 1) && (xc == 0))
      return SOUTH;
    else if ((yc == 1) && (xc == 1))
      return SE;
    else {
      System.out.println("Unknown (x,y): (" + xc + "," + yc + ")");
      return NONE;
    }
  }  // end of getCompassDir()


  public boolean[] getButtons()
  /* Return all the buttons in a single array. Each button value is
     a boolean. */
  {
    boolean[] buttons = new boolean[NUM_BUTTONS];
    float value;
    for(int i=0; i < NUM_BUTTONS; i++) {
      value = comps[ buttonsIdx[i] ].getPollData();
      buttons[i] = ((value == 0.0f) ? false : true);
    }
    return buttons;
  }  // end of getButtons()


  public boolean isButtonPressed(int pos)
  /* Return the button value (a boolean) for button number 'pos'.
     pos is in the range 1-NUM_BUTTONS to match the game pad 
     button labels.
  */
  {
    if ((pos < 1) || (pos > NUM_BUTTONS)) {
      System.out.println("Button position out of range (1-" +
                          NUM_BUTTONS + "): " + pos);
      return false;
    }

    if (buttonsIdx[pos-1] == -1)   // no button found at that pos
      return false;

    float value = comps[ buttonsIdx[pos-1] ].getPollData();   
       // array range is 0-NUM_BUTTONS-1
    return ((value == 0.0f) ? false : true);
  } // end of isButtonPressed()
} // end of GamePadController class
