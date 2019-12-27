package myBot;

import com.google.gson.*;		// The GSON LIbrary which contains all the json classes is imported.

//Imports the java classes for making http connections and reading bytes.


import java.net.*;
import java.io.*;

public class WeatherAPI 
{
	private String mainURL = "http://api.openweathermap.org/data/2.5/weather?q=";
	
	private String place;
	
	private String apiKey = "&APPID=108fd032b65a2778185535b7e590a9d5";
	
	private double currentTempInKelvin = 0;
	
	private double todayHighKelvin = 0;
	
	private double todayLowKelvin = 0;
	

	
	WeatherAPI()
	{
		
	}
	
	WeatherAPI(String place)
	{
		this.place = place;
		
		try 
		{
			Weather();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void Weather() throws Exception		// throws all kinds of exception if any
	{
		
		URL url = null;
		
		HttpURLConnection con = null;
		
		String combinedURL = mainURL + place + apiKey;
		
			url = new URL(combinedURL);
			
			con = (HttpURLConnection) url.openConnection();		// opens the connection to the url
			
			con.setRequestMethod("GET");
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));		// gets back data in terms of bytes
			
			String Json;
			
			Json = rd.readLine();		// converts the data into string and stores it.
			
	        rd.close();
	        
	        con.disconnect();
	        
	        JsonParser(Json);		// sends the data to be parsed.
		
	}
	
	
	
	private void JsonParser(String json)
	{
		JsonElement elem = new JsonParser().parse(json);	 // element is the parent class of object and array
		
		JsonObject obj = elem.getAsJsonObject();		// all the data is parsed as a tree in the form of JSon Object
		
		JsonObject main = obj.get("main").getAsJsonObject();
		
		currentTempInKelvin = main.get("temp").getAsDouble();
		
		todayHighKelvin = main.get("temp_max").getAsDouble();
		
		todayLowKelvin = main.get("temp_min").getAsDouble();
		

	
	}
	

	
	// The functions return the  requested values.

		 public String getCurrentTemp()
			{
				 return ("Current temperature: " + ((int)getKelvinAsFarenheit(currentTempInKelvin)) +" farenheit");
			}
		
		 
		public String getHightTemp()
			{
				return ("Today's high temperature: "+((int)getKelvinAsFarenheit(todayHighKelvin))+" farenheit");
			}
		
		public String getLowTemp()
			{
				return ("Today's low temperature: "+((int)getKelvinAsFarenheit(todayLowKelvin))+" farenheit");
			}
		

		

	
	
	
	
	// is called when the temperature is needed to be converted from Kelvin to Farenheit.
	private double getKelvinAsFarenheit(double k)
	{
		double f = 0;
		
		f = ( (k - 273.15) * 1.8 ) + 32;
		
		return f;
		
	}
	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}