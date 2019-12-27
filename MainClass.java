//	Programmer Name - Murali Krishna Velineni
//	CS 2336.501

// This program will deploy a chatbot in a chatserver and will answer the user's basic questions. find weather and translate.

package myBot;

public class MainClass 
{

	public static void main(String[] args) throws Exception
	{
		
		myChatBot botOne = new myChatBot(); // creates an instance of the pircbot
		
		String channel = "#randomChennl90";
		
		botOne.setVerbose(true);  // setVerbose sets the chatbot to output its results
		
		botOne.connect("irc.freenode.net");    // joins the chat service
		
		botOne.joinChannel(channel);  // joins the particular channel in the service.
		
		botOne.sendMessage(channel,"Hey! Enter any message and I’ll respond!"); // sends the message at the start.

		
		WeatherAPI api = new WeatherAPI("plano");
		
		System.out.println(api.getCurrentTemp());
	}

}
