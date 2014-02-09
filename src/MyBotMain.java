import org.jibble.pircbot.*;

public class MyBotMain {

	public static void main(String[] args) throws Exception {
	
		//generate a new bot
		MyBot bot = new MyBot();
		bot.verboseChannel = false;

		//enable debugging
		try
		{
		bot.setVerbose(true);
		
		//specify servernetwork to connect to
		bot.connect(bot.networkName);
		
		//join channel
		bot.joinChannel(bot.channelName);
	
	} catch (Exception e)
	{
		System.out.println("Invalid Configuation File Found!");
	}
	}

}
