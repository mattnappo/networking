package conwaysGameOfLife;

public class ConwaysGameOfLife {
	
	Cell[][] oldFrame;
	Cell[][] newFrame;
	
	public ConwaysGameOfLife() {
		oldFrame = new Cell[10][10];
		oldFrame = fill(oldFrame);
		
		newFrame = new Cell[10][10];
		newFrame = fill(newFrame);
	}
	
	public Cell[][] fill(Cell[][] arr) {
		for(int i = 0; i < 10; i++) {
			for(int x = 0; x < 10; x++) {
				arr[i][x] = new Cell(i, x);
			}
		}
		return arr;
	}
	
	public void print(Cell[][] arr) {
		for(int i = 0; i < 10; i++) {
			for(int x = 0; x < 10; x++) {
				System.out.print(arr[i][x].getBlock());
			}
			System.out.println();
		}
	}
	
	public void update() {
		for(int i = 0; i < 10; i++) {
			for(int x = 0; x < 10; x++) {
				if(oldFrame[i][x].occupied == true) {
					if(oldFrame[i][x].findNeighbors(oldFrame) <= 1) {
						newFrame[i][x].changeState();
					}
					if(oldFrame[i][x].findNeighbors(oldFrame) >= 4) {
						newFrame[i][x].changeState();
					}
				} else {
					if(oldFrame[i][x].findNeighbors(oldFrame) == 3) {
						newFrame[i][x].changeState();
					}
				}
			}
		}
		oldFrame = newFrame;
	}
	
	public static void main(String[] args) {
		ConwaysGameOfLife life = new ConwaysGameOfLife();
		life.oldFrame[3][3].changeState();
		life.oldFrame[3][4].changeState();
		life.oldFrame[3][5].changeState();
		
		System.out.println(life.oldFrame[3][3].findNeighbors(life.oldFrame));
		
		life.print(life.oldFrame);
		System.out.println("\n");
		life.update();
		life.print(life.newFrame);
		
	}
}
