package Battleship;
class Demo extends Game implements Runnable
{
	/**
	 * 
	 *
	 * @author      Ioana-Eliza Georgescu
	 *
	 */
	private static final long serialVersionUID = 1L;

	Demo()
	{
		super("BattleShip Game in Demo Mode");
		new Thread(this, "GameDemo").start();
	}

	@SuppressWarnings("deprecation")
	public void run()
	{
		if (BattleShip.soundOn()) Sound.start.play();
		myField.placeShips();
		while (demoRunning)
		{
			thePoint = myField.getPoint();
			result = myField.getHit(thePoint);		//In real game this is sent to & received from opponent
			if (result>0) myField.setResult(thePoint, result);
			try	{	Thread.sleep(10);	}
			catch	(InterruptedException ie)	{	ie.printStackTrace();	}
		}
	}
}
