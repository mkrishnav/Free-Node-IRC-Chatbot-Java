package myBot;

import com.google.gson.*;  	// The GSON LIbrary which contains all the json classes is imported.

//Imports the java classes for making http connections and reading bytes.


import java.net.*;
import java.io.*;

public class TranslateApi 
{
	private String mainURL = "https://translate.yandex.net/api/v1.5/tr.json/translate?";
	
	private String toBetranslated = "&text=";
	
	private String apiKey = "key=trnsl.1.1.20190310T042529Z.524dc7ba741cee2e.ec4b387a460b1b7dffddd9b7655f5f44360d8c93";
	
	private String translateDirection = "&lang=";
	
	private String translatedText;
	
	
	
	TranslateApi()
	{
		
	}
	
	
	
	TranslateApi(String toBe, String Direction)
	{
		toBe = toBe.replaceAll(" ", "+");   // All the spaces are filled with + since the url dosent accept spaces.
		
		// The url is combined into one
		
		toBetranslated = toBetranslated + toBe;
		
		translateDirection = translateDirection + Direction;
		
		try {
			
			translate();
			
		} 
		
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	
	private void translate() throws Exception
	{
		
		URL url = null;
		
		HttpURLConnection con = null;
		
		String combinedURL = mainURL + apiKey + toBetranslated + translateDirection;
		
		
			url = new URL(combinedURL);
			
			
			con = (HttpURLConnection) url.openConnection();		// opens the connection to the url
			
			
			con.setRequestMethod("GET");
			
			
			BufferedReader rd = new BufferedReader(new InputStreamReader( con.getInputStream() ) );		// gets back data in terms of bytes
			
			String Json;
			
			Json = rd.readLine();		// converts the data into string and stores it.
			
	        rd.close();
	        
	        con.disconnect();
	        
	        JsonParser(Json);		// sends the data to be parsed.
		
	}
	
	
	
	private void JsonParser(String json)
	{
		JsonElement elem = new JsonParser().parse(json);		// element is the parent class of object and array
		
		JsonObject obj = elem.getAsJsonObject();		// all the data is parsed as a tree in the form of JSon Object
		
		JsonArray arr = obj.get("text").getAsJsonArray();		// the text element is stored as a Json array
		
		translatedText = arr.get(0).getAsString();		
	
	}
	
	
	// returns the translated text.
	
	public String showTranslation()
	{
		
		return ("Translated Text: " + translatedText);
			
		
	}
	
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

