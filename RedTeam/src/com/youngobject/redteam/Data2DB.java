package com.youngobject.redteam;

import java.util.Vector;
import com.youngobject.redteam.pojo.*;

public class Data2DB {

	
	public Data2DB() {
		
		
	}
	
public String createInstatTeam() {
	
	
	 String str = "CREATE TABLE IF NOT EXISTS InstatTeam (" +
             "id INT,"+
			 "team TEXT NOT NULL, " +
             "gamesPlayed FLOAT, " +

             "possessions FLOAT, " +
             "points FLOAT, " +
             "pointsPerPossession FLOAT, " +

             "fieldGoalsMade FLOAT, " +
             "fieldGoalsAttempted FLOAT, " +
             "fieldGoalsPercentage FLOAT, " +

             "twoPointFieldGoalsMade FLOAT, " +
             "twoPointFieldGoalsAttempted FLOAT, " +
             "twoPointFieldGoalsPercentage FLOAT, " +

             "threePointFieldGoalsMade FLOAT, " +
             "threePointFieldGoalsAttempted FLOAT, " +
             "threePointFieldGoalsPercentage FLOAT, " +

             "freeThrowsMade FLOAT, " +
             "freeThrowsAttempted FLOAT, " +
             "freeThrowsPercentage FLOAT, " +

             "rebounds FLOAT, " +
             "offensiveRebounds FLOAT, " +
             "defensiveRebounds FLOAT, " +
             "assists FLOAT, " +
             "steals FLOAT, " +

             "turnovers FLOAT, " +
             "blocks FLOAT, " +
             "fouls FLOAT, " +
             "foulsDrawn FLOAT, " +

             "offensiveRating FLOAT, " +
             "defensiveRating FLOAT, " +
             "defensiveEfficiency FLOAT, " +
             "netRating FLOAT, " +

             "assistsToTurnovers FLOAT, " +
             "stealsToTurnovers FLOAT, " +
             "drawfoulRate FLOAT, " +
             "effectiveFieldGoalPercentage FLOAT, " +
             "trueShootingPercentage FLOAT, " +
             "deflections FLOAT, " +
             "transitionsMade FLOAT, " +
             "transitionsAttempted FLOAT, " +
             "transitionAttacksPercentage FLOAT, " +

             "catchAndShootMade FLOAT, " +
             "catchAndShootAttempted FLOAT, " +
             "catchAndShootShotsMadePercentage FLOAT, " +

             "catchAndDriveMade FLOAT, " +
             "catchAndDriveAttempted FLOAT, " +
             "catchAndDriveShotsMadePercentage FLOAT, " +

             "screensOffMade FLOAT, " +
             "screensOffAttempted FLOAT, " +
             "screensOffShotsPercentage FLOAT, " +

             "postsUpMade FLOAT, " +
             "postsUpAttempted FLOAT, " +
             "postsUpShotsPercentage FLOAT, " +

             "isolationsMade FLOAT, " +
             "isolationsAttempted FLOAT, " +
             "isolationShotsPercentage FLOAT, " +

             "handOffMade FLOAT, " +
             "handOffAttempted FLOAT, " +
             "handOffAttemptedPercentage FLOAT, " +

             "cutsMade FLOAT, " +
             "cutsAttempted FLOAT, " +
             "cutsAttemptedPercentage FLOAT, " +

             "PNRHandlersMade FLOAT, " +
             "PNRHandlersAttempted FLOAT, " +
             "PNRHandlersSuccessfulPercentage FLOAT, " +

             "PNRRollersMade FLOAT, " +
             "PNRRollersAttempted FLOAT, " +
             "PNRRollersSuccessfulPercentage FLOAT, " +

             "PNPMade FLOAT, " +
             "PNPAttempted FLOAT, " +
             "PNPPercentage FLOAT, " +

             "drivesMade FLOAT, " +
             "drivesWithShot FLOAT, " +
             "drivesPercentage FLOAT, " +

             "uncontestedFieldGoalsMade FLOAT, " +
             "UncontestedFieldGoals FLOAT, " +
             "UncontestedFieldGoalsPercentage FLOAT, " +

             "contestedFieldGoalsMade FLOAT, " +
             "contestedFieldGoals FLOAT, " +
             "contestedFieldGoalsPercentage FLOAT, " +

             "pointsOffTurnovers FLOAT, " +

             "defensiveReboundsPercentage FLOAT, " +
             "offensiveReboundsPercentage FLOAT, " +

             "turnoverRatio FLOAT" +
             ");";
	
	System.out.println(str);
	
	return str;
	
	
	
}
	
public String saveInstatTeam(int id, InstatTeam team) {
    String sql = String.format(
        "INSERT INTO InstatTeam (" +
        "id, team, gamesPlayed, possessions, points, pointsPerPossession, fieldGoalsMade, " +
        "fieldGoalsAttempted, fieldGoalsPercentage, twoPointFieldGoalsMade, twoPointFieldGoalsAttempted, " +
        "twoPointFieldGoalsPercentage, threePointFieldGoalsMade, threePointFieldGoalsAttempted, " +
        "threePointFieldGoalsPercentage, freeThrowsMade, freeThrowsAttempted, freeThrowsPercentage, " +
        "Rebounds, offensiveRebounds, defensiveRebounds, assists, steals, turnovers, blocks, fouls, foulsDrawn, " +
        "offensiveRating, defensiveRating, defensiveEfficiency, netRating, assistsToTurnovers, stealsToTurnovers, " +
        "drawfoulRate, effectiveFieldGoalPercentage, trueShootingPercentage, deflections, transitionsMade, " +
        "transitionsAttempted, transitionAttacksPercentage, catchAndShootMade, catchAndShootAttempted, " +
        "catchAndShootShotsMadePercentage, catchAndDriveMade, catchAndDriveAttempted, " +
        "catchAndDriveShotsMadePercentage, screensOffMade, screensOffAttempted, screensOffShotsPercentage, " +
        "postsUpMade, postsUpAttempted, postsUpShotsPercentage, isolationsMade, isolationsAttempted, " +
        "isolationShotsPercentage, handOffMade, handOffAttempted, handOffAttemptedPercentage, cutsMade, " +
        "cutsAttempted, cutsAttemptedPercentage, PNRHandlersMade, PNRHandlersAttempted, " +
        "PNRHandlersSuccessfulPercentage, PNRRollersMade, PNRRollersAttempted, PNRRollersSuccessfulPercentage, " +
        "PNPMade, PNPAttempted, PNPPercentage, drivesMade, drivesWithShot, drivesPercentage, " +
        "uncontestedFieldGoalsMade, UncontestedFieldGoals, UncontestedFieldGoalsPercentage, " +
        "contestedFieldGoalsMade, contestedFieldGoals, contestedFieldGoalsPercentage, pointsOffTurnovers, " +
        "defensiveReboundsPercentage, offensiveReboundsPercentage, turnoverRatio) VALUES (" +
        "%d, '%s', %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f);",
        id, team.getTeam(), team.getGamesPlayed(), team.getPossessions(), team.getPoints(), team.getPointsPerPossession(),
        team.getFieldGoalsMade(), team.getFieldGoalsAttempted(), team.getFieldGoalsPercentage(), team.getTwoPointFieldGoalsMade(),
        team.getTwoPointFieldGoalsAttempted(), team.getTwoPointFieldGoalsPercentage(), team.getThreePointFieldGoalsMade(),
        team.getThreePointFieldGoalsAttempted(), team.getThreePointFieldGoalsPercentage(), team.getFreeThrowsMade(),
        team.getFreeThrowsAttempted(), team.getFreeThrowsPercentage(), team.getRebounds(), team.getOffensiveRebounds(),
        team.getDefensiveRebounds(), team.getAssists(), team.getSteals(), team.getTurnovers(), team.getBlocks(), team.getFouls(), team.getFoulsDrawn(),
        team.getOffensiveRating(), team.getDefensiveRating(), team.getDefensiveEfficiency(), team.getNetRating(), team.getAssistsToTurnovers(),
        team.getStealsToTurnovers(), team.getDrawfoulRate(), team.getEffectiveFieldGoalPercentage(), team.getTrueShootingPercentage(),
        team.getDeflections(), team.getTransitionsMade(), team.getTransitionsAttempted(), team.getTransitionAttacksPercentage(),
        team.getCatchAndShootMade(), team.getCatchAndShootAttempted(), team.getCatchAndShootShotsMadePercentage(), 
        team.getCatchAndDriveMade(), team.getCatchAndDriveAttempted(), team.getCatchAndDriveShotsMadePercentage(),
        team.getScreensOffMade(), team.getScreensOffAttempted(), team.getScreensOffShotsPercentage(), team.getPostsUpMade(),
        team.getPostsUpAttempted(), team.getPostsUpShotsPercentage(), team.getIsolationsMade(), team.getIsolationsAttempted(),
        team.getIsolationShotsPercentage(), team.getHandOffMade(), team.getHandOffAttempted(), team.getHandOffAttemptedPercentage(),
        team.getCutsMade(), team.getCutsAttempted(), team.getCutsAttemptedPercentage(), team.getPNRHandlersMade(),
        team.getPNRHandlersAttempted(), team.getPNRHandlersSuccessfulPercentage(), team.getPNRRollersMade(), team.getPNRRollersAttempted(),
        team.getPNRRollerSsuccessfulPercentage(), team.getPNPMade(), team.getPNPAttempted(), team.getPNPPercentage(), team.getDrivesMade(),
        team.getDrivesWithShot(), team.getDrivesPercentage(), team.getUncontestedFieldGoalsMade(), team.getUncontestedFieldGoals(),
        team.getUncontestedFieldGoalsPercentage(), team.getContestedFieldGoalsMade(), team.getContestedFieldGoals(),
        team.getContestedFieldGoalsPercentage(), team.getPointsOffTurnovers(), team.getDefensiveReboundsPercentage(),
        team.getOffensiveReboundsPercentage(), team.getTurnoverRatio()
    );
    
    System.out.println(sql);
    return sql;
}




	
public String   createPlayer() {
	
	 String createTableSQL = "CREATE TABLE players (\n" +
             "    id INT PRIMARY KEY,\n" +
             "    jersey VARCHAR(10) NOT NULL,\n" +
             "    picture_link VARCHAR(255) NOT NULL,\n" +
             "    info_link VARCHAR(255) NOT NULL,\n" +
             "    game_stats_link VARCHAR(255) NOT NULL,\n" +
             "    player_name VARCHAR(100) NOT NULL,\n" +
             "    birth_year INT,\n" +
             "    position VARCHAR(50) NOT NULL,\n" +
             "    height INT,\n" +
             "    weight INT,\n" +
             "    nationality VARCHAR(50) NOT NULL\n" +
             ");";

// Print the SQL statement
System.out.println(createTableSQL);
	

return createTableSQL;
}	
	
public String	savePlayer(int id,Player player) {
	
	
	        String sql=  "INSERT INTO players (id,jersey, picture_link, info_link, game_stats_link, player_name, birth_year, position, height, weight, nationality) VALUES ("+ 
	        		id+","+
	                "'" + player.getJersey() + "', " +
	                "'" + player.getPictureLink() + "', " +
	                "'" + player.getInfoLink() + "', " +
	                "'" + player.getGameStatsLink() + "', " +
	                "'" + player.getPlayerName() + "', " +
	                "" + player.getBirthYear() + ", " +
	                "'" + player.getPosition() + "', " +
	                "" + player.getHeight() + ", " +
	                "" + player.getWeight() + ", " +
	                "'" + player.getNationality() + "'" +
	                ");";
	        
	        System.out.println(sql);
	        return sql;
	        
	    }

public String	createStatistics() {

	
	 
     StringBuilder sql = new StringBuilder();
     sql.append("CREATE TABLE playerStats (\n");
     sql.append("    id INT,\n");
     sql.append("    type VARCHAR(50),\n");
     sql.append("    mk VARCHAR(50),\n");
     sql.append("    points FLOAT,\n");
     sql.append("    closeRangeMakes FLOAT,\n");
     sql.append("    closeRangeAttempts FLOAT,\n");
     sql.append("    closeRangePercentage FLOAT,\n");
     sql.append("    midRangeMakes FLOAT,\n");
     sql.append("    midRangeAttempts FLOAT,\n");
     sql.append("    midRangePercentage FLOAT,\n");
     sql.append("    threePointMakes FLOAT,\n");
     sql.append("    threePointAttempts FLOAT,\n");
     sql.append("    threePointPercentage FLOAT,\n");
     sql.append("    totalMakes FLOAT,\n");
     sql.append("    totalAttempts FLOAT,\n");
     sql.append("    totalPercentage FLOAT,\n");
     sql.append("    faulMakes 	,\n");
     sql.append("    faulAttempts FLOAT,\n");
     sql.append("    faulPercentage FLOAT,\n");
     sql.append("    defensiveRebound FLOAT,\n");
     sql.append("    offensiveRebound FLOAT,\n");
     sql.append("    totalRebound FLOAT,\n");
     sql.append("    steals FLOAT,\n");
     sql.append("    turnovers FLOAT,\n");
     sql.append("    faulsDrawn FLOAT,\n");
     sql.append("    faulsCommited FLOAT,\n");
     sql.append("    assists FLOAT,\n");
     sql.append("    blocks FLOAT,\n");
     sql.append("    shotsBlocked FLOAT,\n");
     sql.append("    efficiency FLOAT,\n");
     sql.append("    minutes FLOAT\n");
     sql.append(");");

     System.out.println(sql.toString());
     return sql.toString();
	 
	

}



	public String	saveStatistics(int id,Statistics statistics) {

	    StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO playerStats (\n");
        sql.append("    id, type, mk, points, closeRangeMakes, closeRangeAttempts, closeRangePercentage,\n");
        sql.append("    midRangeMakes, midRangeAttempts, midRangePercentage, threePointMakes, threePointAttempts, threePointPercentage,\n");
        sql.append("    totalMakes, totalAttempts, totalPercentage, faulMakes, faulAttempts, faulPercentage,\n");
        sql.append("    defensiveRebound, offensiveRebound, totalRebound, steals, turnovers,\n");
        sql.append("    faulsDrawn, faulsCommited, assists, blocks, shotsBlocked, efficiency, minutes\n");
        sql.append(") VALUES (\n");
        sql.append("    ").append(id).append(", ");
        sql.append("'").append(statistics.getType()).append("', ");
        sql.append("'").append(statistics.getMk()).append("', ");
        sql.append(statistics.getPoints()).append(", ");
        sql.append(statistics.getCloseRangeMakes()).append(", ");
        sql.append(statistics.getCloseRangeAttempts()).append(", ");
        sql.append(statistics.getCloseRangePercentage()).append(", ");
        sql.append(statistics.getMidRangeMakes()).append(", ");
        sql.append(statistics.getMidRangeAttempts()).append(", ");
        sql.append(statistics.getMidRangePercentage()).append(", ");
        sql.append(statistics.getThreePointMakes()).append(", ");
        sql.append(statistics.getThreePointAttempts()).append(", ");
        sql.append(statistics.getThreePointPercentage()).append(", ");
        sql.append(statistics.getTotalMakes()).append(", ");
        sql.append(statistics.getTotalAttempts()).append(", ");
        sql.append(statistics.getTotalPercentage()).append(", ");
        sql.append(statistics.getFaulMakes()).append(", ");
        sql.append(statistics.getFaulAttempts()).append(", ");
        sql.append(statistics.getFaulPercentage()).append(", ");
        sql.append(statistics.getDefensiveRebound()).append(", ");
        sql.append(statistics.getOffensiveRebound()).append(", ");
        sql.append(statistics.getTotalRebound()).append(", ");
        sql.append(statistics.getSteals()).append(", ");
        sql.append(statistics.getTurnovers()).append(", ");
        sql.append(statistics.getFaulsDrawn()).append(", ");
        sql.append(statistics.getFaulsCommited()).append(", ");
        sql.append(statistics.getAssists()).append(", ");
        sql.append(statistics.getBlocks()).append(", ");
        sql.append(statistics.getShotsBlocked()).append(", ");
        sql.append(statistics.getEfficiency()).append(", ");
        sql.append(statistics.getMinutes()).append("\n");
        sql.append(");");

        System.out.println(sql.toString());
        
        return sql.toString();
	
	}


	public String createGameStatistics() {
	
	       StringBuilder sql = new StringBuilder();
	        sql.append("CREATE TABLE GameStatistics (\n");
	        sql.append("    id   INT,\n");
	        sql.append("    fordulo INT,\n");
	        sql.append("    gameScore VARCHAR(50),\n");
	        sql.append("    date VARCHAR(20),\n");
	        sql.append("    againstTeam VARCHAR(100),\n");
	        sql.append("    starter BOOLEAN,\n");
	        sql.append("    type VARCHAR(50),\n");
	        sql.append("    mk VARCHAR(50),\n");
	        sql.append("    points FLOAT,\n");
	        sql.append("    closeRangeMakes FLOAT,\n");
	        sql.append("    closeRangeAttempts FLOAT,\n");
	        sql.append("    closeRangePercentage FLOAT,\n");
	        sql.append("    midRangeMakes FLOAT,\n");
	        sql.append("    midRangeAttempts FLOAT,\n");
	        sql.append("    midRangePercentage FLOAT,\n");
	        sql.append("    threePointMakes FLOAT,\n");
	        sql.append("    threePointAttempts FLOAT,\n");
	        sql.append("    threePointPercentage FLOAT,\n");
	        sql.append("    totalMakes FLOAT,\n");
	        sql.append("    totalAttempts FLOAT,\n");
	        sql.append("    totalPercentage FLOAT,\n");
	        sql.append("    faulMakes FLOAT,\n");
	        sql.append("    faulAttempts FLOAT,\n");
	        sql.append("    faulPercentage FLOAT,\n");
	        sql.append("    defensiveRebound FLOAT,\n");
	        sql.append("    offensiveRebound FLOAT,\n");
	        sql.append("    totalRebound FLOAT,\n");
	        sql.append("    steals FLOAT,\n");
	        sql.append("    turnovers FLOAT,\n");
	        sql.append("    faulsDrawn FLOAT,\n");
	        sql.append("    faulsCommited FLOAT,\n");
	        sql.append("    assists FLOAT,\n");
	        sql.append("    blocks FLOAT,\n");
	        sql.append("    shotsBlocked FLOAT,\n");
	        sql.append("    efficiency FLOAT,\n");
	        sql.append("    minutes FLOAT\n");
	        sql.append(");");

	        System.out.println(sql.toString());
	        
	        return sql.toString();
		
		
	}


	public void saveGameStatistics(int i, GameStatistics gameStatistics) {

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO GameStatistics (\n");
        sql.append("    id, fordulo, gameScore, date, againstTeam, starter,\n");
        sql.append("    type, mk, points, closeRangeMakes, closeRangeAttempts, closeRangePercentage,\n");
        sql.append("    midRangeMakes, midRangeAttempts, midRangePercentage, threePointMakes, threePointAttempts, threePointPercentage,\n");
        sql.append("    totalMakes, totalAttempts, totalPercentage, faulMakes, faulAttempts, faulPercentage,\n");
        sql.append("    defensiveRebound, offensiveRebound, totalRebound, steals, turnovers,\n");
        sql.append("    faulsDrawn, faulsCommited, assists, blocks, shotsBlocked, efficiency, minutes\n");
        sql.append(") VALUES (\n");
        sql.append("    ").append(i).append(", ");
        sql.append(gameStatistics.getFordulo()).append(", ");
        sql.append("'").append(gameStatistics.getGameScore()).append("', ");
        sql.append("'").append(gameStatistics.getDate()).append("', ");
        sql.append("'").append(gameStatistics.getAgainstTeam()).append("', ");
        sql.append(gameStatistics.isStarter() ? "TRUE" : "FALSE").append(", ");
        sql.append("'").append(gameStatistics.getType()).append("', ");
        sql.append("'").append(gameStatistics.getMk()).append("', ");
        sql.append(gameStatistics.getPoints()).append(", ");
        sql.append(gameStatistics.getCloseRangeMakes()).append(", ");
        sql.append(gameStatistics.getCloseRangeAttempts()).append(", ");
        sql.append(gameStatistics.getCloseRangePercentage()).append(", ");
        sql.append(gameStatistics.getMidRangeMakes()).append(", ");
        sql.append(gameStatistics.getMidRangeAttempts()).append(", ");
        sql.append(gameStatistics.getMidRangePercentage()).append(", ");
        sql.append(gameStatistics.getThreePointMakes()).append(", ");
        sql.append(gameStatistics.getThreePointAttempts()).append(", ");
        sql.append(gameStatistics.getThreePointPercentage()).append(", ");
        sql.append(gameStatistics.getTotalMakes()).append(", ");
        sql.append(gameStatistics.getTotalAttempts()).append(", ");
        sql.append(gameStatistics.getTotalPercentage()).append(", ");
        sql.append(gameStatistics.getFaulMakes()).append(", ");
        sql.append(gameStatistics.getFaulAttempts()).append(", ");
        sql.append(gameStatistics.getFaulPercentage()).append(", ");
        sql.append(gameStatistics.getDefensiveRebound()).append(", ");
        sql.append(gameStatistics.getOffensiveRebound()).append(", ");
        sql.append(gameStatistics.getTotalRebound()).append(", ");
        sql.append(gameStatistics.getSteals()).append(", ");
        sql.append(gameStatistics.getTurnovers()).append(", ");
        sql.append(gameStatistics.getFaulsDrawn()).append(", ");
        sql.append(gameStatistics.getFaulsCommited()).append(", ");
        sql.append(gameStatistics.getAssists()).append(", ");
        sql.append(gameStatistics.getBlocks()).append(", ");
        sql.append(gameStatistics.getShotsBlocked()).append(", ");
        sql.append(gameStatistics.getEfficiency()).append(", ");
        sql.append(gameStatistics.getMinutes()).append("\n");
        sql.append(");");

        System.out.println(sql.toString());
		
		
		
	}

	
	

	    public static void generateStatsSqlStatements(String teamName) {
	        String dropTables = "DROP TABLE IF EXISTS instatteam_key_stats_rankings;\n" +
	                            "DROP TABLE IF EXISTS instatteam_offensive_stats_rankings;\n" +
	                            "DROP TABLE IF EXISTS instatteam_defensive_stats_rankings;\n";
	        
	        String createTables = "CREATE TABLE instatteam_key_stats_rankings (\n" +
	                              "    team TEXT,\n" +
	                              "    type TEXT,\n" +
	                              "    value FLOAT,\n" +
	                              "    rank INTEGER\n" +
	                              ");\n\n" +
	                              "CREATE TABLE instatteam_offensive_stats_rankings (\n" +
	                              "    team TEXT,\n" +
	                              "    type TEXT,\n" +
	                              "    value FLOAT,\n" +
	                              "    rank INTEGER\n" +
	                              ");\n\n" +
	                              "CREATE TABLE instatteam_defensive_stats_rankings (\n" +
	                              "    team TEXT,\n" +
	                              "    type TEXT,\n" +
	                              "    value FLOAT,\n" +
	                              "    rank INTEGER\n" +
	                              ");\n";

	        String keyStatsInsert = String.format(
	            "INSERT INTO instatteam_key_stats_rankings (team, type, value, rank)\n" +
	            "WITH RankedTeams AS (\n" +
	            "    SELECT\n" +
	            "        team,\n" +
	            "        '%s' AS type,\n" +
	            "        %s AS value,\n" +
	            "        ROW_NUMBER() OVER (ORDER BY %s DESC) AS rank\n" +
	            "    FROM InstatTeam\n" +
	            ")\n" +
	            "SELECT team, type, value, rank\n" +
	            "FROM RankedTeams\n" +
	            "WHERE team='%s';\n\n", 
	            "%s", "%s", "%s", teamName
	        );

	        String[] keyStatTypes = {
	            "defensiveRating", "offensiveRating", "points", "assistsToTurnovers", "possessions", "pointsPerPossession"
	        };

	        String[] keyStatColumns = {
	            "defensiveRating", "offensiveRating", "points", "assistsToTurnovers", "possessions", "pointsPerPossession"
	        };

	        System.out.println(dropTables);
	        System.out.println(createTables);

	        for (int i = 0; i < keyStatTypes.length; i++) {
	            System.out.println(String.format(keyStatsInsert, keyStatTypes[i], keyStatColumns[i], keyStatColumns[i], teamName));
	        }

	        // Offensive stats
	        String offensiveStatsInsert = String.format(
	            "INSERT INTO instatteam_offensive_stats_rankings (team, type, value, rank)\n" +
	            "WITH RankedTeams AS (\n" +
	            "    SELECT\n" +
	            "        team,\n" +
	            "        '%s' AS type,\n" +
	            "        %s AS value,\n" +
	            "        ROW_NUMBER() OVER (ORDER BY %s DESC) AS rank\n" +
	            "    FROM InstatTeam\n" +
	            ")\n" +
	            "SELECT team, type, value, rank\n" +
	            "FROM RankedTeams\n" +
	            "WHERE team='%s';\n\n", 
	            "%s", "%s", "%s", teamName
	        );

	        String[] offensiveStatTypes = {
	            "points", "offensiveRebounds", "assists", "3PA", "3P%"
	        };

	        String[] offensiveStatColumns = {
	            "points", "offensiveRebounds", "assists", "threePointFieldGoalsAttempted", "threePointFieldGoalsPercentage"
	        };

	        for (int i = 0; i < offensiveStatTypes.length; i++) {
	            System.out.println(String.format(offensiveStatsInsert, offensiveStatTypes[i], offensiveStatColumns[i], offensiveStatColumns[i], teamName));
	        }

	        // Defensive stats
	        String defensiveStatsInsert = String.format(
	            "INSERT INTO instatteam_defensive_stats_rankings (team, type, value, rank)\n" +
	            "WITH RankedTeams AS (\n" +
	            "    SELECT\n" +
	            "        team,\n" +
	            "        '%s' AS type,\n" +
	            "        %s AS value,\n" +
	            "        ROW_NUMBER() OVER (ORDER BY %s DESC) AS rank\n" +
	            "    FROM InstatTeam\n" +
	            ")\n" +
	            "SELECT team, type, value, rank\n" +
	            "FROM RankedTeams\n" +
	            "WHERE team='%s';\n\n", 
	            "%s", "%s", "%s", teamName
	        );

	        String[] defensiveStatTypes = {
	            "defensiveRebounds", "steals", "blocks"
	        };

	        String[] defensiveStatColumns = {
	            "defensiveRebounds", "steals", "blocks"
	        };

	        for (int i = 0; i < defensiveStatTypes.length; i++) {
	            System.out.println(String.format(defensiveStatsInsert, defensiveStatTypes[i], defensiveStatColumns[i], defensiveStatColumns[i], teamName));
	        }
	    }

	  
	    
	    
	    
	    
	    
	}

	
	



