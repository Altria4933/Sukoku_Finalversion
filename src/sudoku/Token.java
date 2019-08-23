package sudoku;

import java.util.HashMap;

public class Token {
	private static HashMap<Character, Integer> map = new HashMap();

	public Token() {
		map.put('A', 0);
		map.put('B', 1);
		map.put('C', 2);
		map.put('D', 3);
		map.put('E', 4);
		map.put('F', 5);
		map.put('G', 6);
		map.put('H', 7);
		map.put('I', 8);
	}

	public int getPointer(char pointer) {
		return map.get(pointer);
	}
}
