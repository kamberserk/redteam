package com.youngobject.redteam.pojo;

public class GameStatistics extends Statistics{



	int fordulo;
	String gameScore;
	
	String date;
	String againstTeam;
	boolean starter;
	

	
	
	public GameStatistics(int fordulo,String gameScore,String date,String againstTeam,boolean starter, Statistics playerStatistics) {

		this.fordulo=fordulo;
		this.gameScore=gameScore;
		this.date= date;
		this.againstTeam=againstTeam;
		this.starter=starter;
		
		this.type = playerStatistics.type;
		this.mk = playerStatistics.mk;
		this.points = playerStatistics.points;
		this.closeRangeMakes = playerStatistics.closeRangeMakes;
		this.closeRangeAttempts = playerStatistics.closeRangeAttempts;
		this.closeRangePercentage = playerStatistics.closeRangePercentage;
		this.midRangeMakes = playerStatistics.midRangeMakes;
		this.midRangeAttempts = playerStatistics.midRangeAttempts;
		this.midRangePercentage = playerStatistics.midRangePercentage;
		this.threePointMakes = playerStatistics.threePointMakes;
		this.threePointAttempts = playerStatistics.threePointAttempts;
		this.threePointPercentage = playerStatistics.threePointPercentage;
		this.totalMakes = playerStatistics.totalMakes;
		this.totalAttempts = playerStatistics.totalAttempts;
		this.totalPercentage = playerStatistics.totalPercentage;
		this.faulMakes = playerStatistics.faulMakes;
		this.faulAttempts = playerStatistics.faulAttempts;
		this.faulPercentage = playerStatistics.faulPercentage;
		this.defensiveRebound = playerStatistics.defensiveRebound;
		this.offensiveRebound = playerStatistics.offensiveRebound;
		this.totalRebound = playerStatistics.totalRebound;
		this.steals = playerStatistics.steals;
		this.turnovers = playerStatistics.turnovers;
		this.faulsDrawn = playerStatistics.faulsDrawn;
		this.faulsCommited = playerStatistics.faulsCommited;
		this.assists = playerStatistics.assists;
		this.blocks = playerStatistics.blocks;
		this.shotsBlocked = playerStatistics.shotsBlocked;
		this.efficiency = playerStatistics.efficiency;
		this.minutes = playerStatistics.minutes;
		
		
	}


	public int getFordulo() {
		return fordulo;
	}


	public void setFordulo(int fordulo) {
		this.fordulo = fordulo;
	}


	public String getGameScore() {
		return gameScore;
	}


	public void setGameScore(String gameScore) {
		this.gameScore = gameScore;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getAgainstTeam() {
		return againstTeam;
	}


	public void setAgainstTeam(String againstTeam) {
		this.againstTeam = againstTeam;
	}


	public boolean isStarter() {
		return starter;
	}


	public void setStarter(boolean starter) {
		this.starter = starter;
	}



	
	
}
