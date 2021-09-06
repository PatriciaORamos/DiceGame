package com.br.dicegame;


public class Player { // ***You must create Object classes for your dice and your PLAYER. ***
	
	String name;
	int points;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}

	/*  ***player class must be able to track player scores.*** */
	public void trackPlayerScores(int numberDice) {
		points = points + numberDice;
	}

}
