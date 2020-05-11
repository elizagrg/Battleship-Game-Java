package Battleship;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * 
 *
 * @author      Ioana-Eliza Georgescu
 *
 */

public class BattleShip extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private 	JMenuItem newGame, newServerGame, newClientGame, help, quit, about,
				feel, restart, internet;

	private JMenuBar bar;
	private JMenu fileMenu, optionMenu, challenge;
	private JCheckBoxMenuItem easy, normal, impossible;
	private static JCheckBoxMenuItem sound;

	private JPanel statusBar;
	private JLabel status;

	private Container c;

	private Game theDemo;

	private int gamePort = 5555;
	private int difficulty = 1;				//easy is 0, normal is 1, impossible is 2!

	private JPanel 	buttonPanel = new JPanel();

	public BattleShip()
	{
		super("BattleShip");
		c = getContentPane();

		ItemHandler itemHandler = new ItemHandler();

		setJMenuBar(bar = new JMenuBar());

		(fileMenu = new JMenu("File")).setMnemonic('F');		// This is the File Menu

		newServerGame = new JMenuItem("New Server Game...",'S');
		newServerGame.addActionListener(itemHandler);
		fileMenu.add(newServerGame);

		newClientGame = new JMenuItem("New Client Game...",'C');
		newClientGame.addActionListener(itemHandler);
		fileMenu.add(newClientGame);

		newGame = new JMenuItem("New Standalone Game...",'G');
		newGame.addActionListener(itemHandler);
		fileMenu.add(newGame);

		restart = new JMenuItem("Restart Application",'R');
		restart.addActionListener(itemHandler);
		restart.setEnabled(false);
		fileMenu.add(restart);

		fileMenu.addSeparator();
		about = new JMenuItem("About...",'A');
		about.addActionListener(itemHandler);
		fileMenu.add(about);

		quit = new JMenuItem("Exit",'x');
		quit.addActionListener(itemHandler);
		fileMenu.add(quit);

		(optionMenu = new JMenu("Options")).setMnemonic('O');	 // This is the Console Menu

		help = new JMenuItem("Help",'H');
		help.addActionListener(itemHandler);
		optionMenu.add(help);

		feel = new JMenuItem("Look and Feel...",'L');
		feel.addActionListener(itemHandler);
		optionMenu.add(feel);

		optionMenu.addSeparator();
		internet = new JMenuItem("Internet Settings...",'I');
		internet.addActionListener(itemHandler);
		optionMenu.add(internet);

		challenge = new JMenu("Standalone Difficulty");

		(easy = new JCheckBoxMenuItem("Easy")).addActionListener(itemHandler);
		(normal = new JCheckBoxMenuItem("Normal")).setSelected(true);
		normal.addActionListener(itemHandler);
		(impossible = new JCheckBoxMenuItem("Impossible")).addActionListener(itemHandler);

		challenge.add(easy);
		challenge.add(normal);
		challenge.add(impossible);
		optionMenu.add(challenge);

		sound = new JCheckBoxMenuItem("Sound");
		sound.addActionListener(itemHandler);
		sound.setSelected(true);
		optionMenu.add(sound);

		bar.add(fileMenu);
		bar.add(optionMenu);

		theDemo = new Demo();

		statusBar = new JPanel();
		status = new JLabel(" Location: " + Connection.getMyIP() + "     Port: " + gamePort);
		statusBar.add(status);

		c.add(theDemo, BorderLayout.CENTER);
		c.add(statusBar, BorderLayout.SOUTH);

		pack();
		setVisible(true);

		addWindowListener( new WindowAdapter()
			{ public void windowClosing( WindowEvent e )	{  System.exit(0);	}	}
		);
	}

	private class ItemHandler implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			if (e.getSource()==newServerGame)
			{
				newServerGame.setEnabled(false);
				newClientGame.setEnabled(false);
				restart.setEnabled(true);
			}
			if (e.getSource()==newClientGame)
			{
				newServerGame.setEnabled(false);
				newClientGame.setEnabled(false);
				restart.setEnabled(true);
			}
			if (e.getSource()==newGame)
			{
				switch (difficulty)
				{
					case 0:	break;
					case 1:	break;
					case 2:	break;
				}
				newServerGame.setEnabled(false);
				newClientGame.setEnabled(false);
				restart.setEnabled(true);
			}
			if (e.getSource()==restart)
			{
				newServerGame.setEnabled(true);
				newClientGame.setEnabled(true);
			}
			if (e.getSource()==about) {
				JOptionPane.showMessageDialog(c,
					"BattleShip Game\n\n" +
					"Program Design:        William Dubel\n" +
					"Board Design:          Robert Ledesma\n" +
					"Game Logic:            Ruben Dalmacio\n\n" +
					"Created: June 28, 2001",
					"About BattleShip", JOptionPane.PLAIN_MESSAGE);
			}
			if (e.getSource()==quit)	{  System.exit(0);	}


			// Code to handle Console menu item events
			if (e.getSource()==help)
			{
				new HelpBrowser();
			}
			if (e.getSource()==internet)
			{
				try
				{
					gamePort = Integer.parseInt(JOptionPane.showInputDialog(c.getParent(),
						"Enter the port to use for game communication:", "Internet Settings",
						JOptionPane.QUESTION_MESSAGE));
				}
				catch(NumberFormatException nfe)	{	/* do nothing	*/	}
				finally	{
					status.setText(" Location: " + Connection.getMyIP() + "     Port: " + gamePort);
				}
			}
			if (e.getSource()==easy)
			{
				if (normal.isSelected() || impossible.isSelected())
				{
					normal.setSelected(false);
					impossible.setSelected(false);
					difficulty = 0;
				}
				else easy.setSelected(true);
			}
			if (e.getSource()==normal)
			{
				if (easy.isSelected() || impossible.isSelected())
				{
					easy.setSelected(false);
					impossible.setSelected(false);
					difficulty = 1;
				}
				else normal.setSelected(true);
			}
			if (e.getSource()==impossible)
			{
				if (easy.isSelected() || normal.isSelected())
				{
					normal.setSelected(false);
					easy.setSelected(false);
					difficulty = 2;
				}
				else impossible.setSelected(true);
			}
			if (e.getSource()==feel)  {
				UIManager.LookAndFeelInfo looks[] = UIManager.getInstalledLookAndFeels();
				String options[] = new String[looks.length];
				for (int i=0; i<looks.length; i++) options[i]=looks[i].getClassName();

				Object selection = JOptionPane.showInputDialog(c, "Choose a Look and Feel:",
					"Look and Feel", JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
				if (selection!=null)  {
					try	{
						UIManager.setLookAndFeel((String)selection);
						SwingUtilities.updateComponentTreeUI(c.getParent());
					}
					catch(Exception lnfe)	{
						JOptionPane.showMessageDialog(c.getParent(),lnfe.getMessage(),
						"Look&Feel Exception", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}

	public static boolean soundOn()		{	return sound.isSelected();	}

	public static void main(String [] args)
	{
		new BattleShip();		//Main creates a new instance of our BattleShip class
	}

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	public void setButtonPanel(JPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}
}
