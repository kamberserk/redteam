package com.youngobject.redteam.hunbasket.scrapper;
import java.util.Vector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.youngobject.redteam.pojo.Player;
import com.youngobject.redteam.pojo.Statistics;
import com.youngobject.redteam.pojo.GameStatistics;

public class GameStatisticsWebScraper {
	
	
	String gameInfoURL;
	
	public GameStatisticsWebScraper() {}
	
	public GameStatisticsWebScraper(String gameInfoURL) {
		
		this.gameInfoURL=gameInfoURL;
	}
	
	
	
    public Vector getGameStatistics(){
    	
    	
    
    	
        Vector<GameStatistics> gameStatisticsList = new Vector();
    	
        try {
            // Fetch the page
           
            Document doc = Jsoup.connect(gameInfoURL).get();

   
            
            
            Elements statisticsRows = doc.select("table.box-table.responsive.merkozesrol_table").select("tbody").select("tr");
            
         //   System.out.print(statisticsRows);
            
   
            
            for (Element row : statisticsRows) {
              
            	
            	if (!row.select("td").isEmpty()) {       
            
            	String forduloStr = row.select("td").get(0).text().replaceAll(". forduló", ""); // Use the correct index
                
            	int fordulo= Integer.parseInt(forduloStr);
            	
            	
                String date = row.select("td").get(1).text(); // Use the correct index
                
                String againstTeam = row.select("td").get(2).text(); // Use the correct index
                
                String gameScore = row.select("td").get(3).text(); // Use the correct index
                
                String starterStr= row.select("td").get(4).text();
                
                boolean starter=false;
                
                if ("X".equals(starterStr))
                	starter=true;
                else
                	starter=false;
                
                
                float points = Float.parseFloat(row.select("td").get(5).text()); // Use the correct index
                float closeRangeMakes = Float.parseFloat(row.select("td").get(6).text()); // Use the correct index
                float closeRangeAttempt = Float.parseFloat(row.select("td").get(7).text()); // Use the correct index
                float closeRangePercentage = Float.parseFloat(row.select("td").get(8).text()); // Use the correct index
                
                float midRangeMakes = Float.parseFloat(row.select("td").get(9).text()); // Use the correct index
                float midRangeAttempts = Float.parseFloat(row.select("td").get(10).text()); // Use the correct index
                float midRangePercentage = Float.parseFloat(row.select("td").get(11).text()); // Use the correct index
                
                float threePointMakes = Float.parseFloat(row.select("td").get(12).text()); // Use the correct index
                float threePointAttempts =Float.parseFloat( row.select("td").get(13).text()); // Use the correct index
                float threePointPercentage =Float.parseFloat( row.select("td").get(14).text()); // Use the correct index
                
                
                float totalMakes = Float.parseFloat(row.select("td").get(15).text()); // Use the correct index
                float totalAttempts = Float.parseFloat(row.select("td").get(16).text()); // Use the correct index
                float totalPercentage = Float.parseFloat(row.select("td").get(17).text()); // Use the correct index
                
                float faulMakes = Float.parseFloat(row.select("td").get(18).text()); // Use the correct index
                float faulAttempts =Float.parseFloat( row.select("td").get(19).text()); // Use the correct index
                float faulPercentage = Float.parseFloat(row.select("td").get(20).text()); // Use the correct index
                
                
                float defensiveRebound = Float.parseFloat(row.select("td").get(21).text()); // Use the correct index
                float offensiveRebound =Float.parseFloat( row.select("td").get(22).text()); // Use the correct index
                float totalRebound =Float.parseFloat( row.select("td").get(23).text()); // Use the correct index
                
                
                float steals = Float.parseFloat(row.select("td").get(24).text()); // Use the correct index
                float turnovers =Float.parseFloat( row.select("td").get(25).text()); // Use the correct index
                
                float faulsDrawn = Float.parseFloat(row.select("td").get(26).text()); // Use the correct index
                float faulsCommited = Float.parseFloat(row.select("td").get(27).text()); // Use the correct index
                
                
                float assists = Float.parseFloat(row.select("td").get(28).text()); // Use the correct index
              
                float blocks =Float.parseFloat( row.select("td").get(29).text()); // Use the correct index
                float shotsBlocked =Float.parseFloat( row.select("td").get(30).text()); // Use the correct index
                float efficiency =Float.parseFloat( row.select("td").get(31).text()); // Use the correct index
                float minutes = Float.parseFloat(row.select("td").get(32).text()); // Use the correct index
                
                
                Statistics playerStatistic=new Statistics( "GAME",  "MK",	 points,  closeRangeMakes,  closeRangeAttempt,
           			 closeRangePercentage,  midRangeMakes,  midRangeAttempts,  midRangePercentage,
        			 threePointMakes,  threePointAttempts,  threePointPercentage,  totalMakes,
        			 totalAttempts,  totalPercentage,  faulMakes,  faulAttempts,  faulPercentage,
        			 defensiveRebound,  offensiveRebound,  totalRebound,  steals,  turnovers,
        			 faulsDrawn,  faulsCommited,  assists,  blocks,  shotsBlocked,  efficiency,
        			 minutes) ;
                
                
                GameStatistics   gameStatistics=new GameStatistics(fordulo, gameScore, date, againstTeam, starter,  playerStatistic);
                
                 gameStatisticsList.add(gameStatistics);
             
              
                
            }

            }
 
         
           

        } catch (Exception e) {
            e.printStackTrace();
        }
   
    
        return gameStatisticsList;
        
    
    }
}
