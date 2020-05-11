package Battleship;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class Game extends Container
{
	/**
	 * 
	 *
	 * @author      Ioana-Eliza Georgescu
	 *
	 */
	private static final long serialVersionUID = 1L;

	protected PlayingField myField;

	private JPanel statusBar, messagePanel;
	private JTextField message;
	private JButton send;
	protected Point thePoint;
	protected int result;
	protected Connection myConnection;
	protected boolean demoRunning = true;

	Game(String borderTitle)
	{
		myField = new PlayingField(borderTitle);

		setLayout(new BorderLayout());
		add(myField, BorderLayout.CENTER);

		(messagePanel = new JPanel()).setLayout(new BorderLayout());
		messagePanel.setBorder(new TitledBorder("Message Dispatcher"));
		message = new JTextField();
		message.addKeyListener(new KeyAdapter()
	  {
		@SuppressWarnings("deprecation")
		public void keyTyped (KeyEvent ke)
		{
			if (ke.getKeyChar()==KeyEvent.VK_ENTER)
			{
				if (myConnection!=null && myConnection.established())
				{
				   myConnection.sendObject(new ObjectPacket(message.getText()));
				}
				myField.addMessage("Dispatched: " + message.getText());
				message.setText("");
				if (BattleShip.soundOn()) Sound.sonar.play();
			}
		}
	  });

	  ButtonHandler handle = new ButtonHandler();
		send = new JButton("Send");
		send.addActionListener(handle);

		messagePanel.add(message, BorderLayout.CENTER);
		messagePanel.add(send, BorderLayout.EAST);
		add(messagePanel, BorderLayout.SOUTH);
	}

	public JPanel getStatusBar() {
		return statusBar;
	}

	public void setStatusBar(JPanel statusBar) {
		this.statusBar = statusBar;
	}

	private class ButtonHandler implements ActionListener
   {
	  @SuppressWarnings("deprecation")
	public void actionPerformed( ActionEvent e )
	  {
			if (e.getSource() == send)
			{
				if (myConnection!=null && myConnection.established())
				{
					myConnection.sendObject(new ObjectPacket(message.getText()));
				}
				myField.addMessage("Dispatched: " + message.getText());
				message.setText("");
				if (BattleShip.soundOn()) Sound.sonar.play();
			}
		}
	}
}
