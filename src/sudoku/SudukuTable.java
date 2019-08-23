package sudoku;

import java.util.Arrays;

public class SudukuTable {

	private SingleNumber[][] table;
	private Level level;

	// default constructor
	public SudukuTable() {
		this.table = new SingleNumber[9][9];
		this.setLevel(Level.Undefined);
	}
	
	
    //set default value
	public void setDefaultNumber() {
		for (int i = 0; i <= 9; i++) {
			for (int y = 0; y <= 9; y++) {
				table[i][y] = new SingleNumber();

			}

		}

	}
	
	public void setNumber (int x, int y, int value, boolean writable) {
				table[x][y] = new SingleNumber(value, writable);		
	}
	
	

	public SingleNumber[][] getTable() {
		return table;
	}

	public void setTable(SingleNumber[][] table) {
		this.table = table;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}


	//for testing only
	public void  printmyself() {
		System.out.println( this.level);
		for (int i = 0; i < table.length; i++) {
		    for (int j = 0; j < table[i].length; j++) {
		       System.out.print(table[i][j].getValue() + " ");
		    }
		    System.out.println();
		}
		//return "/n";
	}
	
	

}
