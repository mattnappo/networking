package conwaysGameOfLife;

public class Cell {
	
	boolean occupied;
	int neighbors;
	String block;
	int x;
	int y;
	
	public Cell(int xx, int yy) {
		occupied = false;
		neighbors = 0;
		x = xx;
		y = yy;
		block = "[ ]";
	}
	
	public int findNeighbors() {
		return 0;
	}
	
	public void changeState() {
		if(occupied == true) {
			occupied = false;
			block = "[ ]";
		} else {
			occupied = true;
			block = "[#]";
		}
	}
	
}
