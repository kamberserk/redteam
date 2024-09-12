package com.youngobject.redteam.hunbasket.scrapper;
import java.util.Vector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.youngobject.redteam.pojo.Player;

public class PlayerWebScraper {
	
	
	String teamURL;
	
	public PlayerWebScraper(String teamURL) {
		
		this.teamURL=teamURL;
	}
	
	
	
    public Vector getPlayers(){
    	
        Vector<Player> players = new Vector();
    	
        try {
            // Fetch the page
           
            Document doc = Jsoup.connect(teamURL).get();

     
            Elements playerRows = doc.select("table.team-players.responsive").select("tbody").select("tr");
            
        //    System.out.print(playerRows);
            
   
            
            for (Element row : playerRows) {
              
            	
            	String infoLink = row.select("a").attr("href");
            	String pictureLink=row.select("div.team-players-pic").attr("style");
            	
            	pictureLink=pictureLink.substring(pictureLink.indexOf("https:"));
            	pictureLink=pictureLink.substring(0, pictureLink.length()-1);
            		
            	
            	String jersey = row.select("td").get(0).text(); // Use the correct index
                String playerName = row.select("td").get(1).text(); // Use the correct index
                String birthYearStr = row.select("td").get(2).text(); 
                int birthYear=Integer.parseInt(birthYearStr);
                // Use the correct index
                String position = row.select("td").get(3).text(); // Use the correct index
                String heightStr = row.select("td").get(4).text().replaceAll(" cm", "");; // Use the correct index
                int height = Integer.parseInt(heightStr);
                
                String weightStr = row.select("td").get(5).text().replaceAll(" kg", ""); // Use the correct index
                int weight = Integer.parseInt(weightStr);
                
                
                
                Player player =  new Player(jersey,playerName,birthYear,position,height,weight,pictureLink,infoLink);
               // player.printInfo();
                players.add( player);
             
              
                
            }

            
         
           

        } catch (Exception e) {
            e.printStackTrace();
        }
   
    
        return players;
        
    
    }
}
