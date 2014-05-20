/*
 * Class Cell - holds details of individual cells on the game board
 */

import java.util.ArrayList;

public class Cell {
	private Position position;
	private ArrayList<Ladder> laddersWhichStarts; //List of all the ladders which start in this cell 
	private ArrayList<Snake> snakesWithMouth; //List of all snakes whose mouths are in this cell
	
	public Cell(Position position) {
		this.position = position;
		snakesWithMouth = new ArrayList<Snake>();
		laddersWhichStarts = new ArrayList<Ladder>();
	}
	
	public ArrayList<Ladder> getLaddersWhichStarts() {
		return laddersWhichStarts;
	}

	public ArrayList<Snake> getSnakesWithMouth() {
		return snakesWithMouth;
	}

	public void addLadder(Ladder ladder){
		this.laddersWhichStarts.add(ladder);
	}
	
	public void addSnake(Snake snake){
		this.snakesWithMouth.add(snake);
	}
}
