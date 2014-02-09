import java.util.Scanner;
import java.io.*;

import org.jibble.pircbot.*;

public class MyBot extends PircBot {

	public boolean verboseChannel;
	String botName;
	String networkName;
	String channelName;
	File f = null;
	BufferedReader fileScanner;
	FileWriter write;

	public MyBot() throws IOException {
		
		//load a config file here.
		try
		{
			//create new file
			f = new File("botconfig.cfg");
			if (!f.exists()){
			write = new FileWriter(f);
			write.write("Test Configuration File");
			write.write("\n--------------------------");
			write.write("\nnetworkname " + networkName);
			write.write("\nchannelname " + channelName);
			write.write("\nbotname " + botName);
			write.close();
			}
			
			//load the file
			fileScanner = new BufferedReader(new FileReader("botconfig.cfg"));
			for (String line; (line = fileScanner.readLine()) != null; )
			{
				String[] currentLinesWords = line.split(" ");
				if (currentLinesWords[0].equalsIgnoreCase("botname"))
				{
					botName = currentLinesWords[1];
				}
				else if (currentLinesWords[0].equalsIgnoreCase("networkname"))
				{
					networkName = currentLinesWords[1];
				}
				else if (currentLinesWords[0].equalsIgnoreCase("channelname"))
				{
					channelName = currentLinesWords[1];
				}
			}
			fileScanner.close();
			
		} catch (Exception e)
		{
			e.printStackTrace();
			write = new FileWriter(f);
			write.write("Test Configuration File");
			write.write("\n--------------------------");
			write.write("\nnetworkname " + "INSERT IRC NETWORK NAME HERE");
			write.write("\nchannelname " + "INSERT CHANNEL NAME YOU WISH TO CONNECT TO");
			write.write("\nbotname " + "DefaultBot");
			write.close();
		}
		this.setName(botName);
	}
	
	protected void onMessage(
			String channel,
			String sender,
			String login,
			String hostname,
			String message)
	{
		if (verboseChannel == true)
		{
			sendMessage("Silverbullets", sender + " from " + channel + " says: " + message);
		}
	}
			
			
	
	protected void onPrivateMessage(
			String sender,
			String login,
			String hostname,
			String message)
	{
		String input = message;
		String[] words = input.split("\\s+");
		
		if (words[0].equalsIgnoreCase("actAsRelay") && words.length == 2)
		{
			if (words[1].equals("on"))
			{
				verboseChannel = true;
				sendMessage(sender, "Channel Relay Enabled!");
			}
			else if (words[1].equals("off"))
			{
				verboseChannel = false;
				sendMessage(sender, "Channel Relay Disabled!");
			}
			else {
				sendMessage(sender, "Error: Syntax is as follows: actasrelay on/off.");
			}
		}
		else if (words[0].equalsIgnoreCase("help"))
		{
			sendMessage(sender, "Current Implemented Commands: " + "help, " + "myinfo, " + "actasrelay.");
		}
		else if (words[0].equalsIgnoreCase("myinfo"))
		{
			sendMessage(sender, "You are logged in as " + sender + " and your host name is: " + hostname + ".");
		}
		else {
			sendMessage(sender, "I do not understand the command " + message);
		}
	}
}
