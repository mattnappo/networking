package conwaysGameOfLife;

public class ConwaysGameOfLife {
	
	Cell[][] oldFrame;
	Cell[][] newFrame;
	
	public ConwaysGameOfLife() {
		oldFrame = new Cell[30][30];
		oldFrame = fill(oldFrame);
		
		newFrame = new Cell[30][30];
		newFrame = fill(newFrame);
		
		print(oldFrame);
		print(newFrame);
	}
	
	public Cell[][] fill(Cell[][] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int x = 0; x < arr.length; x++) {
				arr[i][x] = new Cell(i, x);
			}
		}
		return arr;
	}
	
	public void print(Cell[][] arr) {
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
	
	public static void main(String[] args) {
		new ConwaysGameOfLife();	
	}	
}
