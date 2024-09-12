package com.youngobject.redteam.pojo;

import java.util.*;
import com.youngobject.redteam.pojo.*;


public class Player {

	
	
	String jersey;
	String pictureLink;
	String infoLink; // can be used as ID
	String gameStatsLink;

	String playerName;
	int birthYear;
	String position;
	int height;
	int weight;
	String nationality;
	Vector playerInfoStatistics;
	Vector playerGameStatistics;


	public String getGameStatsLink() {
		return gameStatsLink;
	}

	public void setGameStatsLink(String gameStatsLink) {
		this.gameStatsLink = gameStatsLink;
	}



public Vector getPlayerInfoStatistics() {
	return playerInfoStatistics;
}

public void setPlayerInfoStatistics(Vector playerInfoStatistics) {
	this.playerInfoStatistics = playerInfoStatistics;
}

public Vector getPlayerGameStatistics() {
	return playerGameStatistics;
}

public void setPlayerGameStatistics(Vector playerGameStatistics) {
	this.playerGameStatistics = playerGameStatistics;
}


	public String getJersey() {
		return this.jersey;
	}

	public void setJersey(String jersey) {
		this.jersey = jersey;
	}

	public String getPictureLink() {
		return pictureLink;
	}

	public void setPictureLink(String pictureLink) {
		this.pictureLink = pictureLink;
	}

	public String getInfoLink() {
		return infoLink;
	}

	public void setInfoLink(String infoLink) {
		this.infoLink = infoLink;
	}

	public String getPlayerName() {
		return this.playerName;
	}

	public void setName(String playerName) {
		this.playerName = playerName;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality.toUpperCase();
	}


	
	public Player() {
		
		
	}

	public Player(String jersey, String playerName, int birthYear, String position, int height,
			int weight, String pictureLink, String infoLink) {
		// TODO Auto-generated constructor stub
	
		this.jersey=jersey;
		this.playerName=playerName;
		this.birthYear=birthYear;
		this.position=position;
		this.height=height;
		this.weight=weight;
		this.pictureLink=pictureLink;
		this.infoLink=infoLink;
			
	}
	
	
	public void printInfo() {
		
		System.out.println(this.jersey+ " "+this.playerName+ " "+ this.birthYear+ " "+this.position+" "+this.height+" "+this.weight+ " "+this.pictureLink+" "+this.infoLink);
		
	}
	
	
	public Statistics getAverageStats() {
		
		for (int i=0;i<playerInfoStatistics.size();++i) {
			if ("AVERAGE".equals(((Statistics) playerInfoStatistics.get(i)).getType()))
					return (Statistics) playerInfoStatistics.get(i);
			
			
		}
		return new Statistics();
	}
	
	public Statistics getTotalStats() {
		
		for (int i=0;i<playerInfoStatistics.size();++i) {
			if ("TOTAL".equals(((Statistics) playerInfoStatistics.get(i)).getType()))
					return (Statistics) playerInfoStatistics.get(i);
			
			
		}
		return new Statistics();
	}
	
	
	public Statistics getRelativeStats() {
		
		for (int i=0;i<playerInfoStatistics.size();++i) {
			if ("RELATIVE".equals(((Statistics) playerInfoStatistics.get(i)).getType()))
					return (Statistics) playerInfoStatistics.get(i);
			
			
		}
		return new Statistics();
	}
	
	
	
	
}
