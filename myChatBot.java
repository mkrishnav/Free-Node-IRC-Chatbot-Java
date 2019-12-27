package myBot;

import org.jibble.pircbot.*;  // imports the pircbot jar file

public class myChatBot extends PircBot     // since pirc bot is an abstract class, it can only be extended.
{

	public myChatBot() 
	{
		
		this.setName("Ultron99");   //sets the name of the bot
		
	}
	
	public void onMessage(String channel, String sender,String login, String hostname, String message)
	{
	
		// if the user message matches the case, it will execute the following instructions.
		
		
		// If the user says hi or hello, it will greet the user.
		if(message.equalsIgnoreCase("Hello") || message.equalsIgnoreCase("hi"))
		{
			sendMessage(channel, "Hello " + sender + " How may I assist you today?, Type list of commands for a list of commands you can try" );
		}
		
		
		// If the user says list of commands, it will print them out
		if(message.equalsIgnoreCase("list of commands"))
		{
			
			sendMessage(channel,"1. Hello or hi." );
			
			sendMessage(channel, "2. Try typing weather(space)zipcode or weather(space)name of the place or weather(space)place,country code e.g plano,us for the weather of that place.");
			
			sendMessage(channel, "3. Try typing translation(space)Text to be translated to(space)Translation Direction. e.g en-ru for english to russsian.");
			
			sendMessage(channel, "4. type exit to exit.");
			
		}
		
		
		// If the user says weather and some place, it will send the place to the weatherAPI Class and prints the weather out.
		if(message.contains("weather "))
		{
			String place = message.substring(8);
			
			WeatherAPI weather = new WeatherAPI(place);
			
			sendMessage(channel, place);
			
			// the functions return the requested values.
			
			sendMessage(channel, weather.getCurrentTemp());
			
			sendMessage(channel, weather.getHightTemp());
			
			sendMessage(channel, weather.getLowTemp());
		
		
			
		}
		
		
		// If the user says translation, it will take the text and the translation direction and send it to translation api class..
		if(message.contains("translation "))
		{
			//translation hi how are you en-ta
			//h =12
			
			int length = message.length() - 1;
			
			int lastSpace = 0;
			
			// The loop searches for the last space so it will be easier to dive the words.
			
			for(int i = length; i >= 0; i--)
			{
				
				if( message.charAt(i) == ' ')
				{
					
					lastSpace = i;
					
					break;
					
				}
				
				
			}
			
			
			String text = message.substring(12, lastSpace);
			
			String direction = message.substring(lastSpace+1);
			
			TranslateApi translation = new TranslateApi(text, direction);
			
			// the function returns the requested value.
			
			sendMessage(channel, translation.showTranslation());

			
			
		}
		
		
		
		
		
		
		if(message.equalsIgnoreCase("exit"))
		{
			this.disconnect();
		}
		
		
		
	
	}
	
	
	
	
	
	
	
	
	
	
}
