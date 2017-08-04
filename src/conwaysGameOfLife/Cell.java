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
	
	public String getBlock() {
		return block;
	}
	public int findNeighbors(Cell[][] arr) {
		int neighbors = 0;
		for(int i = 0; i < 10; i++) {
			for(int x = 0; x < 10; x++) {
				if(i+1<10&&arr[i+1][x].occupied == true) {
					neighbors++;
				}
				if(i-1>=0&&arr[i-1][x].occupied == true) {
					neighbors++;
				}
				if(x+1<10&&arr[i][x+1].occupied == true) {
					neighbors++;
				}
				if(x-1>=0&&arr[i][x-1].occupied == true) {
					neighbors++;
				}
				
				if(i+1<10&&x+1<10&&arr[i+1][x+1].occupied == true) {
					neighbors++;
				}
				if(i-1>=0&&x-1>=0&&arr[i-1][x-1].occupied == true) {
					neighbors++;
				}
				if(i+1<10&&x-1>=0&&arr[i+1][x-1].occupied == true) {
					neighbors++;
				}
				if(i-1>=0&&x+1<10&&arr[i-1][x+1].occupied == true) {
					neighbors++;
				}
			}
		}
		return neighbors;
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
