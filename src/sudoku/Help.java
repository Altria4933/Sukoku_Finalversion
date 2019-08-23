package sudoku;


public class Help {

	public final static String helpmain() {
		return "";
	}

	public final static String in_game() {
		return "----------------------------------------\n"
				+ "There are folling operations you can use in the game.\n"
				+ "----------------------------------------\n"
				+ "HELP-- Will show this guide.\n"
				+ "\nSET-- Will fill one block with position input. \n"
				+ "For example 'SET A1' will move the pointer to A1 block, \n"
				+ "and you'll be promoted to enter the value you want to set after that. \n"
				+ "\nCHECK-- Will check the anser you've input is right. wrong answer will be shown in red. \n"
				+ "\nREMOVE-- Will remove one block with postition input. \n "
				+ "Same as the set command, 'Remove A1' will remove the value of A1 block(Make it blank).\n"
				+ "\nEASYMODE-- Will automaticly check the correctness of the value entered after each operation. \n"
				+ "Note if easymode is off, you have to manualy enter 'Check' to check answer and EasyMode is by defalut off.\n"
				+ "----------------------------------------\n";
	}

	public final static String level_pick() {
		return "----------------------------------------\n"
				+ "There's three levels in this game, Easy, Midium and Hard. \n"
				+ "easier game will have less blank block to be filled in therefore easier to slove. \n"
				+ "But higher level game will be rewared with more scores.\n"
				+ "----------------------------------------\n";

	}

}