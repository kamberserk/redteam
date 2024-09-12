package com.youngobject.redteam.hunbasket.scrapper;
import java.util.Vector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.youngobject.redteam.pojo.Player;
import com.youngobject.redteam.pojo.Statistics;

public class PlayerStatisticsWebScraper {
	
	
	String infoURL;
	
	public PlayerStatisticsWebScraper() {}
	
	public PlayerStatisticsWebScraper(String infoURL) {
		
		this.infoURL=infoURL;
	}
	
	
	public String getGameStatsLink() {
		
	//	System.out.println("inside");
		
		String gameStatsURL="";
		
		
		try {
		  Document doc = Jsoup.connect(infoURL).get();

		     
		  gameStatsURL = doc.select("div.box-table-header").select("div").select("a.box-tool-btn").attr("href");

    	

    } catch (Exception e) {
        e.printStackTrace();
    }
          
         return  gameStatsURL;
          
          
          
		
	}
	
	public String getNationality() {
		
		
		String nationality="";
		
		 
	        try {
	            // Fetch the page
	           
	            Document doc = Jsoup.connect(infoURL).get();

	     
	            Elements nationalityRows = doc.select("div.players_info_cont");
	            
	         //   System.out.print(statisticsRows);
	            
	   
	            
	            for (Element row : nationalityRows) {
	              	         
	            	       
	            	if (row.text().contains("Állampolgárság"))
	            			
	            			//System.out.println(row.text());
	            	
	            	nationality = row.text().replaceAll("Állampolgárság: ", "");
		                
		            }

	                
	            

	            
	         
	           

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	   
	    
	        return  nationality;
	        
	    
	    }
	
	
    public Vector getStatistics(){
    	
        Vector<Statistics> playerStatistics = new Vector();
    	
        try {
            // Fetch the page
           
            Document doc = Jsoup.connect(infoURL).get();

     
            Elements statisticsRows = doc.select("table.box-table.responsive.player_table").select("tbody").select("tr");
            
          //  System.out.print(statisticsRows);
            
   
            
            for (Element row : statisticsRows) {
              
            	
            
            	
            	String type = row.select("td").get(0).text(); // Use the correct index
            	
            	if ("Á".equals(type))
            		type="AVERAGE";
            	else
            	if ("Ö".equals(type))	
            		type="TOTAL";
            	else
                if ("R".equals(type))
                	type="RELATIVE";
            	
            	
                String mk = row.select("td").get(1).text(); // Use the correct index
                float points = Float.parseFloat(row.select("td").get(2).text()); // Use the correct index
                float closeRangeMakes = Float.parseFloat(row.select("td").get(3).text()); // Use the correct index
                float closeRangeAttempt = Float.parseFloat(row.select("td").get(4).text()); // Use the correct index
                float closeRangePercentage = Float.parseFloat(row.select("td").get(5).text()); // Use the correct index
                
                float midRangeMakes = Float.parseFloat(row.select("td").get(6).text()); // Use the correct index
                float midRangeAttempts = Float.parseFloat(row.select("td").get(7).text()); // Use the correct index
                float midRangePercentage = Float.parseFloat(row.select("td").get(8).text()); // Use the correct index
                
                float threePointMakes = Float.parseFloat(row.select("td").get(9).text()); // Use the correct index
                float threePointAttempts =Float.parseFloat( row.select("td").get(10).text()); // Use the correct index
                float threePointPercentage =Float.parseFloat( row.select("td").get(11).text()); // Use the correct index
                
                
                float totalMakes = Float.parseFloat(row.select("td").get(12).text()); // Use the correct index
                float totalAttempts = Float.parseFloat(row.select("td").get(13).text()); // Use the correct index
                float totalPercentage = Float.parseFloat(row.select("td").get(14).text()); // Use the correct index
                
                float faulMakes = Float.parseFloat(row.select("td").get(15).text()); // Use the correct index
                float faulAttempts =Float.parseFloat( row.select("td").get(16).text()); // Use the correct index
                float faulPercentage = Float.parseFloat(row.select("td").get(17).text()); // Use the correct index
                
                
                float defensiveRebound = Float.parseFloat(row.select("td").get(18).text()); // Use the correct index
                float offensiveRebound =Float.parseFloat( row.select("td").get(19).text()); // Use the correct index
                float totalRebound =Float.parseFloat( row.select("td").get(20).text()); // Use the correct index
                
                
                float steals = Float.parseFloat(row.select("td").get(21).text()); // Use the correct index
                float turnovers =Float.parseFloat( row.select("td").get(22).text()); // Use the correct index
                
                float faulsDrawn = Float.parseFloat(row.select("td").get(23).text()); // Use the correct index
                float faulsCommited = Float.parseFloat(row.select("td").get(24).text()); // Use the correct index
                
                
                float assists = Float.parseFloat(row.select("td").get(25).text()); // Use the correct index
              
                float blocks =Float.parseFloat( row.select("td").get(26).text()); // Use the correct index
                float shotsBlocked =Float.parseFloat( row.select("td").get(27).text()); // Use the correct index
                float efficiency =Float.parseFloat( row.select("td").get(28).text()); // Use the correct index
                float minutes = Float.parseFloat(row.select("td").get(29).text()); // Use the correct index
                
                
                Statistics playerStatistic=new Statistics( type,  mk,  points,  closeRangeMakes,  closeRangeAttempt,
           			 closeRangePercentage,  midRangeMakes,  midRangeAttempts,  midRangePercentage,
        			 threePointMakes,  threePointAttempts,  threePointPercentage,  totalMakes,
        			 totalAttempts,  totalPercentage,  faulMakes,  faulAttempts,  faulPercentage,
        			 defensiveRebound,  offensiveRebound,  totalRebound,  steals,  turnovers,
        			 faulsDrawn,  faulsCommited,  assists,  blocks,  shotsBlocked,  efficiency,
        			 minutes) ;
                
               playerStatistics.add(playerStatistic);
             
              
                
            }

            
         
           

        } catch (Exception e) {
            e.printStackTrace();
        }
   
    
        return playerStatistics;
        
    
    }
}
