/*
 * Class Player - holds position details of individual player
 */

import java.util.Random;

public class Player {
	private Position currentPosition;

	public Player(){
		this.currentPosition = new Position(1, 1);
	}
	
	public void throwDice(Game game){
		Random rand = new Random();
		int steps = rand.nextInt(6) + 1; //generate random integer between 1-6 to simulate dice throw
		System.out.println("\tDice shows " + steps);
		takeSteps(steps, game);
		System.out.println("\tNew Position (" + currentPosition.getPositionX() + "," + currentPosition.getPositionY() + ")");
		while(!isMoveStable(game)){
			moveToNextPosition(game);
			System.out.println("\tMoving to (" + currentPosition.getPositionX() + "," + currentPosition.getPositionY() + ")");
		}
	}
	
	public void takeSteps(int steps, Game game){
		int newX = currentPosition.getPositionX();
		int newY = currentPosition.getPositionY();
		System.out.println("\tCurrentPosition = ("+newX+","+newY+")");
		
		//check if current position is either at the right end or left end of the game board
		if(((newX + steps) > game.getBreadthOfBoard() && newY % 2 == 1) || (newX - steps < 1 && newY % 2 == 0)){
			if(!(newY == game.getLengthOfBoard())){ //if currently in top row, do nothing until valid dice move occurs
				if( newY % 2 == 0){ //move player in the left direction
					int stepsRemaining = steps - newX;
					newX = 1;
					newX += stepsRemaining - 1; //one step scrificed in climbing up a row
				} else { //move player in the right direction
					int stepsRemaining = steps - (game.getBreadthOfBoard() - newX);
					newX = game.getBreadthOfBoard();
					newX -= stepsRemaining - 1; //one step scrificed in climbing up a row
				}
				newY = newY+1;
			}
		} else {
			//player moves within the same row
			if(newY % 2 == 0){ //move player in the left direction
				newX -= steps;
			} else { //move player in the right direction
				newX += steps;
			}
		}
                //update positions after moving
		currentPosition.setPositionX(newX);
		currentPosition.setPositionY(newY);
	}
	
	private void moveToNextPosition(Game game){
		Cell cell = game.getCells().get(currentPosition.getPositionX()).get(currentPosition.getPositionY());
		Snake snake = null;
		Ladder ladder = null;
		if(cell.getSnakesWithMouth().size() > 0){
			System.out.println("\tFound Snake");
			snake = cell.getSnakesWithMouth().get(0);
			Position tail = snake.getTail();
                        //update position after sliding down the snake
			currentPosition.setPositionX(tail.getPositionX());
			currentPosition.setPositionY(tail.getPositionY());
		} else if(cell.getLaddersWhichStarts().size() > 0){
			System.out.println("\tFound Ladder");
			ladder = cell.getLaddersWhichStarts().get(0);
			Position end = ladder.getEnd();
                        //update position after climbing up the ladder
			currentPosition.setPositionX(end.getPositionX());
			currentPosition.setPositionY(end.getPositionY());
		}
	}
	
	//Check for any face of snake or start of Ladder
	private boolean isMoveStable(Game game){
		//get respective cell from the current position
		//if the cell is not null that means cell has either snake's mouth or ladder return true;
		//return false if cell is null
		if(game.getCells().get(currentPosition.getPositionX()).get(currentPosition.getPositionY()) == null) 
                    return true;
		else 
                    return false;
	}
	
	public Position getCurrentPosition() {
		return currentPosition;
	}

}
