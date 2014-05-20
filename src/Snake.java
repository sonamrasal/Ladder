/*
 * Class Snake - holds mouth and tail locations of snakes
 */

public class Snake {
	private Position tail; //tail location of the snake
	private Position mouth; //mouth location of the snake
	
	public Snake(Position tail, Position mouth) {
		this.tail = tail;
		this.mouth = mouth;
	}

	public Position getTail() {
		return tail;
	}

	public Position getMouth() {
		return mouth;
	}
}
