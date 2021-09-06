package com.br.dicegame;

import java.util.Random;

/* ***The dice class must be able to roll the dice for you***  */
public class Dice { // ***You must create Object classes for your DICE and your players. ***
	
	int numberDice;
		
	
	public int getNumberDice() {
		return numberDice;
	}

	public void setNumberDice(int numberDice) {
		this.numberDice = numberDice;
	}


	/* ***The dice class must be able to roll the dice for you***  */
	public Integer rollDice() {
		Random random = new Random();
		numberDice = random.nextInt(6) + 1;		
		return numberDice;
	}

}
