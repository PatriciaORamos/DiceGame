package com.br.dicegame;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Patricia Ramos
 *
 */

public class DiceGame {

	static int startPlayer = 0;
	static String wantContinue = "Y";

	public static void main(final String args[]) {

		final Scanner key = new Scanner(System.in);

		System.out.println("Press 1 to COMPUTER start ou 2 for YOU start");
		startPlayer = key.nextInt();

		/*
		 * ***Two players take turns rolling a single die. *** ***You must have a
		 * computer player to play against, and its decisions should include some level
		 * of randomization and planning.***
		 */
		ArrayList<Player> players = new ArrayList<>();
		Player player1 = new Player();
		player1.setName("COMPUTER");
		players.add(player1);

		Player player2 = new Player();
		player2.setName("YOU");
		players.add(player2);

		/*
		 * ***You must have a clear user interface - the user should know what’s going
		 * on in the game and what they’re expected to enter as input. ***
		 */
		while (startPlayer != 1 && startPlayer != 2) {
			System.out.println("Press 1 - COMPUTER start ou 2 - YOU start");
			startPlayer = key.nextInt();
		}

		// Start round of the player. Check player and call to play dice
		startDiceGame(key, startPlayer, players);
		
		//if any player has 100 ou more points, game end
		if(wantContinue.equalsIgnoreCase("END")) {			
			System.out.println("\n========GAME END========");			
		}
		
		key.close();

	}

	// Start round of the player. Check player and call to play dice
	public static void startDiceGame(Scanner key, int startPlayer, ArrayList<Player> players) {
		if(wantContinue.equalsIgnoreCase("Y")) {		
			if(startPlayer==1) {
				playDice(key, players, players.get(0));				
			} else {
				playDice(key, players, players.get(1));
			}
		
		} 
	}

	//method roll dice, add number dice in total, check if number dice equal 1, check if total points equal ou greater 100 points
	public static void playDice(Scanner key, ArrayList<Player> players, Player player) {
		System.out.println("\n***************** Round of the  " + player.getName() + " *****************");

		boolean continueGame = true;

		// while play want continue, dice will continue roll
		while (wantContinue.equalsIgnoreCase("Y")) {
			Dice dice = new Dice();
			int numberDice = dice.rollDice();
			System.out.println("Number of the dice: " + numberDice);
			
			/*
			 * ***Whatever number they get to, that’s their score for the round and is added
			 * to their overall total.***
			 */
			addOverallTotal(player, numberDice, players);
			
			/* ***Rounds will continue until one player reaches 100 points.*** */
			continueGame = hundredPoints(players);
			
			if(continueGame) {
				/*
				 * ***However, if the player rolls a 1, their turn is immediately over and their
				 * total for the round is returned to zero. ***
				 */
				continueGame = zeroTotal(key, player, numberDice, players);
			}		
			
			
			/*
			 * ***On each turn, a player will roll the die as many times as they like,
			 * continually adding the total of the die, until they want to stop***
			 */
			//if no number dice equal 1 and no total point equal 100 then continue play. However ask only to user if he would like continue.
			if (continueGame) {
				if(player.getName().equalsIgnoreCase("YOU")) {
					System.out.println("Do you want continue roll dice? Press (Y/N)");
					wantContinue = key.next();
					while (!wantContinue.equalsIgnoreCase("Y") && !wantContinue.equalsIgnoreCase("N")) {
						System.out.println("Do you want continue roll dice? Press (Y/N)\n");
						wantContinue = key.next();
					}
				}
			}
			
			//if player user decide stop, change round to computer player
			if(wantContinue.equalsIgnoreCase("N")) {
				changePlayer(key, players);
			}
				
		}

	}

	private static void changePlayer(Scanner key, ArrayList<Player> players) {
		//change player
		if (startPlayer == 1) {
			startPlayer = 2;
		} else {
			startPlayer = 1;
		}

		wantContinue = "Y";
		// Call - to play dice
		startDiceGame(key, startPlayer, players);
	}

	/*
	 * ***Whatever number they get to, that’s their score for the round and is added
	 * to their overall total.***
	 */
	public static void addOverallTotal(Player player, int numberDice, ArrayList<Player> players) {
		//add total in player
		player.trackPlayerScores(numberDice);
		
		//print score of all players
		for (Player p : players) {
			System.out.println("--> Player " + p.getName() + " points: "+ p.getPoints());			
		}

	}

	/*
	 * ***However, if the player rolls a 1, their turn is immediately over and their
	 * total for the round is returned to zero. ***
	 */
	public static boolean zeroTotal(Scanner key, Player player, int numberDice, ArrayList<Player> players) {
		boolean continueGame = true;

		// if number of the Dice equal 1 then total of the point of the user is zero
		if (numberDice == 1) {
			player.setPoints(0);
			System.out.println(player.getName() + " LOST ALL POINTS");	
			
			//change de round player
			changePlayer(key, players);
			continueGame = false;
		}

		return continueGame;
	}

	/* ***Rounds will continue until one player reaches 100 points.*** */
	public static boolean hundredPoints(ArrayList<Player> players) {
		boolean continueGame = true;
		for (Player player : players) {
			// get total points of the players if 100, stop rounds
			if (player.getPoints() >= 100) {
				wantContinue = "END";
				continueGame = false;
			}		
		}
		return continueGame;
	}

}
