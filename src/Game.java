/*
 * Class Game - models Snakes and Ladders game
 */

import java.util.ArrayList;

public class Game {
	private int lengthOfBoard;
	private int breadthOfBoard;
	private int sidesOfDice;
	private ArrayList<ArrayList<Cell>> cells; //cells within the game board
	private ArrayList<Snake> snakes; //list of all snakes
	private ArrayList<Ladder> ladders; //list of all ladders
	private Player player1;
	private Player player2;
	
	public Game(int lengthOfBoard, int breadthOfBoard, int sidesOfDice){
		this.lengthOfBoard = lengthOfBoard;
		this.breadthOfBoard = breadthOfBoard;
		this.sidesOfDice = sidesOfDice;
	}
	
	public ArrayList<ArrayList<Cell>> getCells() {
		return cells;
	}
	
	public int getLengthOfBoard() {
		return lengthOfBoard;
	}

	public int getBreadthOfBoard() {
		return breadthOfBoard;
	}

	public int getSidesOfDice() {
		return sidesOfDice;
	}

	public static void main(String[] args) {
		Game game = new Game(10, 12, 6);
		game.initializeCells(); 
		game.createSnakes(); 
		game.createLadders(); 
                game.display();
		game.player1 = new Player();
		game.player2 = new Player();
		
		Player currentPlayer = game.player1;
		while( !game.isHome(currentPlayer.getCurrentPosition())){
			if(currentPlayer == game.player1) System.out.println("Player 1");
			else System.out.println("Player 2");
			currentPlayer.throwDice(game);
			if(game.isHome(currentPlayer.getCurrentPosition())) 
                           break;
			//change the turn of the player
			if(currentPlayer == game.player1) currentPlayer = game.player2;
			else currentPlayer = game.player1;
		}
		
		//Whoever is the currentPlayer, he has won the game
		if(currentPlayer == game.player1) System.out.println("Player1 reached end position. Player1 wins! :)");
		else System.out.println("Player2 reached end position. Player1 wins! :)");
	}
	
	private void initializeCells(){
		//initialize arraylist of arraylist containing cells
		this.cells = new ArrayList<ArrayList<Cell>>();
		for(int i = 0; i <= breadthOfBoard; i++){
			this.cells.add(new ArrayList<Cell>());
			for(int j = 0; j <= lengthOfBoard; j++){
				this.cells.get(i).add(null); 
			}
		}
	}
	
	public void createSnakes(){
		//Create snakes and add it to ArrayList<Snake> snakes
		//While processing the snakes also initialize those cells which contain mouth of the snake
		snakes = new ArrayList<Snake>();
		snakes.add(new Snake(new Position(7,1), new Position(4,2)));
		snakes.add(new Snake(new Position(2,2), new Position(2,6)));
		snakes.add(new Snake(new Position(4,3), new Position(7,9)));
		snakes.add(new Snake(new Position(7,4), new Position(7,6)));
		snakes.add(new Snake(new Position(1,6), new Position(4,7)));
		snakes.add(new Snake(new Position(8,8), new Position(8,10)));
		snakes.add(new Snake(new Position(6,8), new Position(6,10)));
		snakes.add(new Snake(new Position(3,8), new Position(2,10)));
		
		for(int i = 0; i < 7; i++){
			Snake snake = snakes.get(i);
			Position mouth = snake.getMouth();
			int x = mouth.getPositionX();
			int y = mouth.getPositionY();
			
			if(this.cells.get(x).get(y) == null){
				Cell cell = new Cell(new Position(x,y));
				this.cells.get(x).add(y, cell);
				this.cells.get(x).get(y).addSnake(snake);
			} else {
				this.cells.get(x).get(y).addSnake(snake);
			}
		}
	}
	
	public void createLadders(){
		//Create ladders and add it to ArrayList<Ladder> ladders
		//While processing the ladders also initialize those cells which contain start of the ladder
		ladders = new ArrayList<Ladder>();
		ladders.add(new Ladder(new Position(4,1), new Position(7,2)));
		ladders.add(new Ladder(new Position(9,1), new Position(10,3)));
		ladders.add(new Ladder(new Position(1,2), new Position(3,4)));
		ladders.add(new Ladder(new Position(8,3), new Position(4,9)));
		ladders.add(new Ladder(new Position(1,4), new Position(2,6)));
		ladders.add(new Ladder(new Position(10,6), new Position(7,7)));
		ladders.add(new Ladder(new Position(3,7), new Position(1,9)));
		ladders.add(new Ladder(new Position(10,8), new Position(10,10)));
		
		for(int i = 0; i < 7; i++){
			Ladder ladder = ladders.get(i);
			Position start = ladder.getStart();
			int x = start.getPositionX();
			int y = start.getPositionY();
			
			if(this.cells.get(x).get(y) == null){
				Cell cell = new Cell(new Position(x,y));
				this.cells.get(x).add(y, cell);
				this.cells.get(x).get(y).addLadder(ladder);
			} else {
				this.cells.get(x).get(y).addLadder(ladder);
			}
		}
	}
	
	public boolean isHome(Position position){
            //check if user has reached the end cell on the board
		if(position.getPositionX() == 1 && position.getPositionY() == lengthOfBoard) 
                    return true;
		else 
                    return false;
	}

        private void display(){
            System.out.println("\t\t\tGame board layout");
            int counter = lengthOfBoard * breadthOfBoard;
            while(counter > 0){
                for(int i = lengthOfBoard; i > 0; i--){
                    if(i % 2 == 0){
                        for(int k = 0; k < breadthOfBoard; k++)
                            System.out.printf("  %d\t  ", counter - k);
                    }
                    else{
                        for(int k = breadthOfBoard - 1; k >= 0; k--)
                            System.out.printf("  %d\t  ", counter - k);
                    }
                    System.out.println();
                    for(int j = 1; j <= breadthOfBoard; j++ ){
                        System.out.printf("(%d, %d)\t", j, i);
                        counter--;
                    }
                    System.out.println();
                }
            }

            System.out.println("Snake positions");
            for(int i = 0; i < snakes.size(); i++){
                int fromX, fromY, toX, toY;
                fromX = snakes.get(i).getMouth().getPositionX();
                fromY = snakes.get(i).getMouth().getPositionY();
                toX = snakes.get(i).getTail().getPositionX();
                toY = snakes.get(i).getTail().getPositionY();
                System.out.printf("(%d, %d) -> (%d, %d)\n", fromX, fromY, toX, toY);
            }
            System.out.println("Ladder positions");
            for(int i = 0; i < ladders.size(); i++){
                int fromX, fromY, toX, toY;
                fromX = ladders.get(i).getStart().getPositionX();
                fromY = ladders.get(i).getStart().getPositionY();
                toX = ladders.get(i).getEnd().getPositionX();
                toY = ladders.get(i).getEnd().getPositionY();
                System.out.printf("(%d, %d) -> (%d, %d)\n", fromX, fromY, toX, toY);
            }
        }           
}
