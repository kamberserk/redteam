package com.youngobject.redteam;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;


import com.youngobject.redteam.pojo.Player;
import com.youngobject.redteam.pojo.Statistics;
import com.instatscout.basketball.data.ExcelParser;
import com.youngobject.redteam.hunbasket.scrapper.GameStatisticsWebScraper;
import com.youngobject.redteam.hunbasket.scrapper.PlayerStatisticsWebScraper;
import com.youngobject.redteam.hunbasket.scrapper.PlayerWebScraper;
import com.youngobject.redteam.pojo.GameStatistics;
import com.youngobject.redteam.pojo.InstatTeam;
public class Main {

	
	
	public static final String teamURL = "https://hunbasket.hu/csapat/x2324/hun2b/10575/david-kornel-ka";
	public static final String TEAMNAME="David Kornel";
	
	
	
	
	public static final String SQLFILE2 = "/Users/kam/Desktop/sql.txt";
	public static final String SQLFILE = "/Users/kam/Desktop/sql_end.txt";

	
	public static void main(String[] args) {
		
	
		  File file = new File(SQLFILE);
		

	        // Delete the file if it exists
	        if (file.exists()) {
	            file.delete();
	      
	       }
		
	    
	        
		
		PrintStream fileStream;
		try {
			fileStream = new PrintStream(SQLFILE);
			System.setOut(fileStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		//String teamURL = "https://hunbasket.hu/csapat/x2324/hun2a/9221/salgotarjani-kse";
	
	
		/*
		 * 
		 * 
		 * THE TEAM WE WANT TO GET THE INFORMATION ABOUT SHOULD BE POSTED HERE AS A TEAM MAIN URL WITH CORRECT YEAR
		 * 
		 */
		
	//	System.out.println("Getting Player List.......");
		
		PlayerWebScraper playerWebScraper = new PlayerWebScraper(teamURL);
		
		Vector players = playerWebScraper.getPlayers();

	//	System.out.println("..........................");
		
		/*
		 * 
		 * 
		 * GETTING ALL THE PLAYER INFORMTION FROM THE TEAM MAIN PAGE
		 * 
		 */
		

		
	//	System.out.println("Getting Player Statistics.......");
		
	
		for (int i=0;i<players.size();++i) {
					
			String infoLink=((Player)players.get(i)).getInfoLink();
			
			
			/*
			 * 
			 * 
			 * GOING TO SPECIFIC PLAYER INFORMATION PAGE TO COLLECT AVERAGE TOTAL AND RELATIVE INFORMATION
			 * 
			 */
			
				
			
			
		//	System.out.print(((Player)players.get(i)).getPlayerName());
		//	System.out.print("Total Rebound"+ ((Player)players.get(i)).getAverageStats().getTotalRebound());
			
		PlayerStatisticsWebScraper playerStatisticsWebScrapper = new PlayerStatisticsWebScraper(infoLink);
	
		Vector  playerStatistics = playerStatisticsWebScrapper.getStatistics();
		
		((Player)players.get(i)).setNationality(playerStatisticsWebScrapper.getNationality());
		
		((Player)players.get(i)).setGameStatsLink(playerStatisticsWebScrapper.getGameStatsLink());
		
		
		
		((Player)players.get(i)).setPlayerInfoStatistics(playerStatistics);
		
		
		
		
		//System.out.println(((Player)players.get(i)).getGameStatsLink());
		
		
		
		/*
		 * 
		 * 
		 * ADDING THE DATA AVERAGE TOTAL AND RELATIVE INFORMATION to player
		 * 
		 */
		
		/*
		System.out.print(((Player)players.get(i)).getPlayerName());
		System.out.print(" "+ ((Player)players.get(i)).getAverageStats().getTotalRebound());
		System.out.print(" "+ ((Player)players.get(i)).getTotalStats().getTotalRebound());
				System.out.print(" "+ ((Player)players.get(i)).getNationality());
		System.out.println(" "+ ((Player)players.get(i)).getPlayerName());
		*/
	
	//	System.out.print(" Average Points : "+ ((Player)players.get(i)).getAverageStats().getPoints());
	//	System.out.print(" Average Assists : "+ ((Player)players.get(i)).getAverageStats().getPoints());
	//	System.out.println(" Average 3P% : "+ ((Player)players.get(i)).getAverageStats().getThreePointPercentage());
	
		
		}
		
	
	//	System.out.println("..........................");
	
		
		
	//	System.out.print("Getting Game Statistics........");
		
		
		for (int i=0;i<players.size();++i) {
				
			String gameLink=((Player)players.get(i)).getGameStatsLink();
			
			
//			System.out.print(((Player)players.get(i)).getPlayerName());	

			
	//		System.out.println(" Game Link : "+ gameLink);
			
			GameStatisticsWebScraper playerGameInfoStatisticsWebScrapper = new GameStatisticsWebScraper(gameLink);
			
			Vector  gameStatistics = playerGameInfoStatisticsWebScrapper.getGameStatistics();
			
			((Player)players.get(i)).setPlayerGameStatistics(gameStatistics);		
		
					
			
		}
		

	//	System.out.println("..........................");	
		
		
		

	//	for (int i=0;i<players.size();++i) {
		
	//	System.out.println(((Player)players.get(i)).getPlayerName());	
		
		
	//	Vector  gameList=	(((Player)players.get(i)).getPlayerGameStatistics());
				
	//			for (int j=0;j<gameList.size();++j) {
					
			//		System.out.print(((GameStatistics)gameList.get(j)).getFordulo());
					
			//		System.out.print(" "+((GameStatistics)gameList.get(j)).getAgainstTeam());
			//		System.out.print(" "+((GameStatistics)gameList.get(j)).isStarter());
			//		System.out.println(" "+((GameStatistics)gameList.get(j)).getPoints());
					
	//			}
				
	//	}
	
	
	
		////////////////////////////DATA COLLECTIONIS FINISHED////////////////////////////
		
		
///////////////////////////burada database e yazma kısmını ekleycez 	
	
		

		
		//System.out.println("/////////////////////////////////////////////////////////////////////////////////////////////////////");		
		
		
		
		Data2DB db = new Data2DB();
		
		db.createPlayer();
		db.createStatistics();
		db.createGameStatistics();
		db.createInstatTeam();
		
		for (int i=0;i<players.size();++i) {
	
		db.savePlayer(i, (Player)players.get(i));
		

		
Vector <Statistics> statisticsList = 	((Player)players.get(i)).getPlayerInfoStatistics();

for (int j=0;j<statisticsList.size();++j) {
	
	db.saveStatistics(i,(Statistics)statisticsList.get(j));
	
}
		


Vector <GameStatistics> gameStatisticsList = 	((Player)players.get(i)).getPlayerGameStatistics();

for (int j=0;j<gameStatisticsList.size();++j) {
	
	db.saveGameStatistics(i,(GameStatistics)gameStatisticsList.get(j));
	
}


}

		
////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
ExcelParser mapper = new ExcelParser();
mapper.normalizeExcelFile();

List<InstatTeam> teams = new ArrayList<>();

teams =   mapper.parseExcelFile();
int teamCounter=0;

// Example: Print the details of each team
for (InstatTeam team : teams) {
	
	db.saveInstatTeam(teamCounter, team);   
	
    //System.out.println(team.getTeam() + " - Points: " + team.getPoints());
    teamCounter++;
}



db.generateStatsSqlStatements(TEAMNAME);

// Initialize BufferedReader and BufferedWriter
try (BufferedReader reader = new BufferedReader(new FileReader(SQLFILE));
     BufferedWriter writer = new BufferedWriter(new FileWriter(SQLFILE2))) {

    String line;
    // Read lines from the input file
    while ((line = reader.readLine()) != null) {
        // Write lines that do not contain 'log4j' to the output file
        if (!line.contains("log4j")) {
            writer.write(line);
            writer.newLine();
        }
    }
} catch (IOException e) {
    e.printStackTrace();
}



File file2 = new File(SQLFILE);
if (file2.exists()) {
    file2.delete();

}





}






		

	
	
	

}
