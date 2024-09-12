package com.youngobject.redteam.pojo;
public class Statistics {

	
	String 	type; // 3 tip 
	String mk;
	
	float points;
	float closeRangeMakes;
	float closeRangeAttempts;
	float closeRangePercentage;
	
	
	float midRangeMakes;
	float midRangeAttempts;
	float midRangePercentage;
	
	float threePointMakes;
	float threePointAttempts;
	float threePointPercentage;
	
	
	float totalMakes;
	float totalAttempts;
	float totalPercentage;
	
	
	float faulMakes;
	float faulAttempts;
	float faulPercentage;
	
	float defensiveRebound;
	float offensiveRebound;
	
	float totalRebound;
	
	float steals;
	float turnovers;
	
	float faulsDrawn;
	float faulsCommited;
	
	
	float assists;
	
	float blocks;
	float shotsBlocked;
	
	float efficiency;
	float minutes;
	
	public Statistics() {}
	
	public Statistics(String type, String mk, float points, float closeRangeMakes, float closeRangeAttempts,
			float closeRangePercentage, float midRangeMakes, float midRangeAttempts, float midRangePercentage,
			float threePointMakes, float threePointAttempts, float threePointPercentage, float totalMakes,
			float totalAttempts, float totalPercentage, float faulMakes, float faulAttempts, float faulPercentage,
			float defensiveRebound, float offensiveRebound, float totalRebound, float steals, float turnovers,
			float faulsDrawn, float faulsCommited, float assists, float blocks, float shotsBlocked, float efficiency,
			float minutes) {
		super();
		this.type = type;
		this.mk = mk;
		this.points = points;
		this.closeRangeMakes = closeRangeMakes;
		this.closeRangeAttempts = closeRangeAttempts;
		this.closeRangePercentage = closeRangePercentage;
		this.midRangeMakes = midRangeMakes;
		this.midRangeAttempts = midRangeAttempts;
		this.midRangePercentage = midRangePercentage;
		this.threePointMakes = threePointMakes;
		this.threePointAttempts = threePointAttempts;
		this.threePointPercentage = threePointPercentage;
		this.totalMakes = totalMakes;
		this.totalAttempts = totalAttempts;
		this.totalPercentage = totalPercentage;
		this.faulMakes = faulMakes;
		this.faulAttempts = faulAttempts;
		this.faulPercentage = faulPercentage;
		this.defensiveRebound = defensiveRebound;
		this.offensiveRebound = offensiveRebound;
		this.totalRebound = totalRebound;
		this.steals = steals;
		this.turnovers = turnovers;
		this.faulsDrawn = faulsDrawn;
		this.faulsCommited = faulsCommited;
		this.assists = assists;
		this.blocks = blocks;
		this.shotsBlocked = shotsBlocked;
		this.efficiency = efficiency;
		this.minutes = minutes;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMk() {
		return mk;
	}
	public void setMk(String mk) {
		this.mk = mk;
	}
	public float getPoints() {
		return points;
	}
	public void setPoints(float points) {
		this.points = points;
	}
	public float getCloseRangeMakes() {
		return closeRangeMakes;
	}
	public void setCloseRangeMakes(float closeRangeMakes) {
		this.closeRangeMakes = closeRangeMakes;
	}
	public float getCloseRangeAttempts() {
		return closeRangeAttempts;
	}
	public void setCloseRangeAttempt(float closeRangeAttempts) {
		this.closeRangeAttempts = closeRangeAttempts;
	}
	public float getCloseRangePercentage() {
		return closeRangePercentage;
	}
	public void setCloseRangePercentage(float closeRangePercentage) {
		this.closeRangePercentage = closeRangePercentage;
	}
	public float getMidRangeMakes() {
		return midRangeMakes;
	}
	public void setMidRangeMakes(float midRangeMakes) {
		this.midRangeMakes = midRangeMakes;
	}
	public float getMidRangeAttempts() {
		return midRangeAttempts;
	}
	public void setMidRangeAttempts(float midRangeAttempts) {
		this.midRangeAttempts = midRangeAttempts;
	}
	public float getMidRangePercentage() {
		return midRangePercentage;
	}
	public void setMidRangePercentage(float midRangePercentage) {
		this.midRangePercentage = midRangePercentage;
	}
	public float getThreePointMakes() {
		return threePointMakes;
	}
	public void setThreePointMakes(float threePointMakes) {
		this.threePointMakes = threePointMakes;
	}
	public float getThreePointAttempts() {
		return threePointAttempts;
	}
	public void setThreePointAttempts(float threePointAttempts) {
		this.threePointAttempts = threePointAttempts;
	}
	public float getThreePointPercentage() {
		return threePointPercentage;
	}
	public void setThreePointPercentage(float threePointPercentage) {
		this.threePointPercentage = threePointPercentage;
	}
	public float getTotalMakes() {
		return totalMakes;
	}
	public void setTotalMakes(float totalMakes) {
		this.totalMakes = totalMakes;
	}
	public float getTotalAttempts() {
		return totalAttempts;
	}
	public void setTotalAttempts(float totalAttempts) {
		this.totalAttempts = totalAttempts;
	}
	public float getTotalPercentage() {
		return totalPercentage;
	}
	public void setTotalPercentage(float totalPercentage) {
		this.totalPercentage = totalPercentage;
	}
	public float getFaulMakes() {
		return faulMakes;
	}
	public void setFaulMakes(float faulMakes) {
		this.faulMakes = faulMakes;
	}
	public float getFaulAttempts() {
		return faulAttempts;
	}
	public void setFaulAttempts(float faulAttempts) {
		this.faulAttempts = faulAttempts;
	}
	public float getFaulPercentage() {
		return faulPercentage;
	}
	public void setFaulPercentage(float faulPercentage) {
		this.faulPercentage = faulPercentage;
	}
	public float getDefensiveRebound() {
		return defensiveRebound;
	}
	public void setDefensiveRebound(float defensiveRebound) {
		this.defensiveRebound = defensiveRebound;
	}
	public float getOffensiveRebound() {
		return offensiveRebound;
	}
	public void setOffensiveRebound(float offensiveRebound) {
		this.offensiveRebound = offensiveRebound;
	}
	public float getTotalRebound() {
		return totalRebound;
	}
	public void setTotalRebound(float totalRebound) {
		this.totalRebound = totalRebound;
	}
	public float getSteals() {
		return steals;
	}
	public void setSteals(float steals) {
		this.steals = steals;
	}
	public float getTurnovers() {
		return turnovers;
	}
	public void setTurnovers(float turnovers) {
		this.turnovers = turnovers;
	}
	public float getFaulsDrawn() {
		return faulsDrawn;
	}
	public void setFaulsDrawn(float faulsDrawn) {
		this.faulsDrawn = faulsDrawn;
	}
	public float getFaulsCommited() {
		return faulsCommited;
	}
	public void setFaulsCommited(float faulsCommited) {
		this.faulsCommited = faulsCommited;
	}
	public float getAssists() {
		return assists;
	}
	public void setAssists(float assists) {
		this.assists = assists;
	}
	public float getBlocks() {
		return blocks;
	}
	public void setBlocks(float blocks) {
		this.blocks = blocks;
	}
	public float getShotsBlocked() {
		return shotsBlocked;
	}
	public void setShotsBlocked(float shotsBlocked) {
		this.shotsBlocked = shotsBlocked;
	}
	public float getEfficiency() {
		return efficiency;
	}
	public void setEfficiency(float efficiency) {
		this.efficiency = efficiency;
	}
	public float getMinutes() {
		return minutes;
	}
	public void setMinutes(float minutes) {
		this.minutes = minutes;
	}
	
	
}
