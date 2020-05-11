package Battleship;
import java.io.*;
import java.applet.*;
import java.net.*;
import java.util.*;

/**
 * 
 *
 * @author      Ioana-Eliza Georgescu
 *
 */

@SuppressWarnings("deprecation")
public class Sound
{
	private static Random random = new Random();
	static AudioClip yourTurn, wait, splash, sonar, start, victorious, loser, lostShip;

	@SuppressWarnings("deprecation")
	private static AudioClip [] bombSounds = new AudioClip[5];
	static
	{
		try
		{
			bombSounds[0] = Applet.newAudioClip(new File("Sound/boom.wav").toURL());
			bombSounds[1] = Applet.newAudioClip(new File("Sound/blooey.wav").toURL());
			bombSounds[2] = Applet.newAudioClip(new File("Sound/bomb.wav").toURL());
			bombSounds[3] = Applet.newAudioClip(new File("Sound/explosion.wav").toURL());
			bombSounds[4] = Applet.newAudioClip(new File("Sound/thunder.wav").toURL());
			yourTurn = Applet.newAudioClip(new File("Sound/yourTurn.wav").toURL());
			wait = Applet.newAudioClip(new File("Sound/wait.wav").toURL());
			splash = Applet.newAudioClip(new File("Sound/splash.wav").toURL());
			sonar = Applet.newAudioClip(new File("Sound/sonar.wav").toURL());
			start = Applet.newAudioClip(new File("Sound/start.wav").toURL());
			victorious = Applet.newAudioClip(new File("Sound/victorious.wav").toURL());
			loser = Applet.newAudioClip(new File("Sound/loser.wav").toURL());
			lostShip = Applet.newAudioClip(new File("Sound/lostship.wav").toURL());
		}
		catch(MalformedURLException mue){}
	}

	public static void playHit()	{	bombSounds[random.nextInt(5)].play();	}
}