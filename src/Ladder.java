/*
 * Class Ladder - holds starting and ending location of ladders
 */

public class Ladder {
	private Position start; //starting location  of ladder
	private Position end; //ending location of ladder
	
	public Ladder(Position start, Position end) {
		this.start = start;
		this.end = end;
	}

	public Position getStart() {
		return start;
	}

	public Position getEnd() {
		return end;
	}
}
