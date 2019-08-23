/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Sudoku {

	public ArrayList<SudukuTable> tableList;
	public SudukuTable singleTable;
	public SudukuTable answerTable;
	public printtable display;
	public Random random;
	public Token token;
	public int rand;
	public String name;
	Scanner scan = new Scanner(System.in);
	boolean gameOn = true;
	boolean gameClear = false;
	boolean is_easymode = false;
	FileIO mainIO;
	FileIO answerIO;
	File question = new File("question.txt");
	File answer = new File("answer.txt");
        public HashMap<String,Integer> myMap;

	public Sudoku() {
		tableList = new ArrayList<SudukuTable>();
		singleTable = new SudukuTable();
		// answerTable = new SudukuTable();
		display = new printtable();
		mainIO = new FileIO();
		// answerIO = new FileIO();
		mainIO.setFile(question);
		// answerIO.setFile(answer);
		// load file to arraylist to both question and answer io class
		mainIO.ReadList();
		// answerIO.ReadList();

		greeting();
		// insert user level here
		levelPicker();
		while (gameOn) {
			display.updateTable(singleTable);
			display.display();
			if (!CheckClear()) {
				// testing line, delete later
				System.out.println(rand);
				//scan.nextLine();

				System.out.println("Please choose an operation, enter 'Help' for more info: ");
				
				String operator = "";
				operator = scan.nextLine();
				String[] input = operator.trim().split(" ");
				switch (input[0].toLowerCase()) {
				case "help": {
					System.out.println(Help.in_game());
					break;
				}
				case "exit": {
					exitgame();
					break;
				}

				case "set": {
					setNumber(input);
					break;

				}

				case "remove": {
					removeNumber(input);
					break;

				}

				case "clear": {
					clearTable();
					break;

				}

				case "check": {
					Checkanswer(singleTable, rand);
					break;
				}

				case "easymode": {
					easymode(input[1]);
				}

				default: {
					System.out.println("Invalid command, please try again! enter 'Help' for more info");
					operator = scan.nextLine();
					break;
				}
				}
			} else {
				cleared();
			}
		}
	}
        public void exitgame()
	{
		System.out.println("Your game is not finish,if you exit now,your answer will be cleared.Are you true?(Yes/No)");
		String answer=scan.nextLine();
		boolean isTrue=false;
		do
		{
		if(answer.trim().equalsIgnoreCase("Yes"))
		{
			isTrue=true;
			gameOn=false;
		}
		else if(answer.trim().equalsIgnoreCase("no"))
		{
			isTrue=true;
			System.out.println("Continue your game.");
		}
		else
		{
			isTrue=false;
			System.out.println("Invalid input");
		}
		}while(isTrue==false);
	}

	public void setNumber(String[] input) {
		token = new Token();
		int value = 0;
		boolean rightValue = false;
		char a = Character.toUpperCase(input[1].charAt(0));
		if ((Character.toString(a).matches("[A-I?]"))) {
			int x = token.getPointer(a);
			int y = Character.getNumericValue(input[1].charAt(1)) - 1;
			if (x >= 0 && x <= 8 && y >= 0 && y <= 8) {
				if (singleTable.getTable()[x][y].isWriteable() == true) {
					System.out.println("Please enter the value you want to set");
					if (scan.hasNextInt()) {
						value = scan.nextInt();
						
					}
					if (value >= 1 && value <= 9) {
						rightValue = true;
						scan.nextLine();

					} else {
						System.out.println("The value should between 1-9");
					}
					if (rightValue) {
						singleTable.getTable()[x][y].setValue(value);
					}
				} else {
					System.out.println("You can't write in this block, please try again! ");
				}
			} else {
				System.out.println("The pointer index should be between A-I and 1-9, Please check your input!");
			}

		} else {
			System.out.println("Invalid pointer at " + input[1] + " Please check your input!");
		}

		if (is_easymode == true) {
			Checkanswer(singleTable, rand);
		}
	}

	public void removeNumber(String[] input) {
		token = new Token();
		int value = 0;
		boolean rightValue = false;
		char a = Character.toUpperCase(input[1].charAt(0));
		if ((Character.toString(a).matches("[A-I?]"))) {
			int x = token.getPointer(a);
			int y = Character.getNumericValue(input[1].charAt(1)) - 1;
			if (x >= 0 && x <= 8 && y >= 0 && y <= 8) {
				if (singleTable.getTable()[x][y].isWriteable() == true) {
					singleTable.getTable()[x][y].setValue(0);
				} else {
					System.out.println("You can't clear in this block, please try again! ");
				}
			} else {
				System.out.println("The pointer index should be between A-I and 1-9, Please check your input!");
			}

		} else {
			System.out.println("Invalid pointer at " + input[1] + "Please check your input!");
		}
		if (is_easymode == true) {
			Checkanswer(singleTable, rand);
		}
	}

	public void clearTable() {

		boolean rightinput = false;
		while (!rightinput) {
			System.out.println("By doing this, you'll clear everything you've done in this table. sure?\n (YES/NO)");
			String choise = scan.nextLine();
			switch (choise.toLowerCase()) {
			case "yes": {
				for (int x = 0; x < 9; x++) {
					for (int y = 0; y < 9; y++) {
						if (singleTable.getTable()[x][y].isWriteable() == true) {
							singleTable.getTable()[x][y].setValue(0);
						}
					}
				}

				rightinput = true;
				break;
			}
			case "no": {
				rightinput = true;
				break;
			}
			default: {
				System.out.println("Invalid input, please try again!");
				break;
			}
			}
		}

	}

	public boolean CheckClear() {
		return false;

	}

	public void cleared() {

	}

	public void easymode(String in) {
		if (in.equalsIgnoreCase("on")) {
			is_easymode = true;
			System.out.println("Easymode turned on, the answer will be checked once been entered.");
		} else if (in.equalsIgnoreCase("off")) {
			is_easymode = false;
			System.out.println("Easymode turned off, you'll need to enter 'check' to check your answer");
		} else {
			System.out.println("Unknown command, please try again! enter 'Help' for more info");
		}

	}

	public void Checkanswer(SudukuTable userinput, int rand) {
		answerTable = new SudukuTable();
		answerIO = new FileIO();
		answerIO.setFile(answer);
		answerIO.ReadList();
		// getList (rand) will use the same index for the question list to find the
		// answer list
		answerTable = answerIO.getList(rand);
		

		for (int x = 0; x < 9; ++x) {
			for (int y = 0; y < 9; ++y) {
				if (userinput.getTable()[x][y].getValue() == answerTable.getTable()[x][y].getValue()) {
					userinput.getTable()[x][y].setIs_true(true);
				} else {
					userinput.getTable()[x][y].setIs_true(false);
					System.out.println(userinput.getTable()[x][y].isIs_true());
				}
			}
		}
                int count=0;
                for(int x=0;x<9;++x)
		{
			for(int y=0;y<9;++y)
			{
				if(userinput.getTable()[x][y].isIs_true()!=false&&userinput.getTable()[x][y].getValue()!=0)
				{
					count++;
				}
			}
		}
		if(count==81)
		{
			finish();
		}
	}
        public void finish()
	{
		System.out.println("Well done,you have solved all questions.");
		int mark = calculateScore();
		System.out.println("You have got "+mark+"scores");
		boolean isTrue=false;
		do {
			String answer=null;
			System.out.println("Do you wish to continue?(Yes/No)");
			answer=scan.nextLine();
			if(answer.trim().equalsIgnoreCase("no"))
			{
				try {
					checkuser();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					mainIO.writetoFile(myMap);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				gameOn=false;
				isTrue=true;
                                System.out.println("Successful saved your history.");
			}
			else if(answer.trim().equalsIgnoreCase("yes"))
			{
				levelPicker();
				isTrue=true;
			}
			else
			{
				System.out.println("Invalid input");
				isTrue=false;
			}
		}while(isTrue=false);
	}
         public int calculateScore()
    {
    	int mark=0;
    	if(rand>=0&&rand<=5)
    	{
    		mark=10;
    	}
    	else if(rand>5&&rand<=11)
    	{
    		mark=20;
    	}
    	else if(rand>11&&rand<18)
    	{
    		mark=30;
    	}
    	return mark;
    }

	 public void checkuser() throws IOException
    {
    	int number=0;
    	number=calculateScore();
    	myMap=mainIO.readRecords();
		 if(myMap.containsKey(name)==true)
		    {
		    	
		    	 System.out.println("The name "+name+"is exiting, do you wanna to add your score from last time? Enter'Y'or'N'");
	             if(scan.nextLine().trim().equalsIgnoreCase("Y"))
	             {
	               Integer score;
		       score=myMap.get(name);
		       score=score+number;
                        myMap.put(name, score);      
	             }                       
	             else if(scan.nextLine().trim().equalsIgnoreCase("N"))
	             {
	            	  myMap.put(name, number); 
	             }
	             else
	             {
	            	 System.out.println("Invalid Input,type again");
	            	 scan.nextLine();
	            	 while(scan.nextLine()!="Y"||scan.nextLine()!="N")
	            	 {
	            		 System.out.println("Invalid Input,type again");
	            	 }
	            	 if(scan.nextLine().trim().equalsIgnoreCase("Y"))
		             {
		                 Integer score;
		                score=myMap.get(name);
		                score=score+number;
                                myMap.put(name, score);
		             }                       
		             else if(scan.nextLine().trim().equalsIgnoreCase("N"))
		             {
		            	 myMap.put(name, number); 
		             }	 
	             }
		    }
		    else
		    {
		    	 myMap.put(name, number); 
		    }
    }
	public void greeting()
	    {
		System.out.println("Welcome to Sudoku game.Please enter your name: ");
		name=scan.nextLine();
		}
	public void levelPicker() {

		boolean rightInput = false;
		int picker = 0;

		while (!rightInput) {
			System.out
					.println("Please choose your game level!" + "\nEasy, Midium or Hard \nenter 'Help' for more info.");
			String choise = scan.nextLine();
			switch (choise.toLowerCase()) {
			case "easy": {
				rightInput = true;
				picker = 1;
				break;
			}

			case "midium": {
				rightInput = true;
				picker = 2;
				break;
			}

			case "hard": {
				rightInput = true;
				picker = 3;
				break;
			}

			case "help": {
				System.out.println(Help.level_pick());
				break;
			}

			default:
				System.out.println("Invalid input, please try again!/n");
				break;

			}
		}
		singleTable = tablePicker(picker);

	}

	public SudukuTable tablePicker(int i) {
		random = new Random();
		rand = 0;
		// in the arrayList we read from the file. first 6 block are easy questions, and
		// the next 6 are mid and the next 6 are hard
		// therefore index 0-5 will stand for ez question and 6-11 for mid 12-17 for
		// hard.
		// this method will generate a random number regarding to the level user picked
		// and get a question using the number generated
		switch (i) {
		case 1: {

			rand = random.nextInt(6);
			System.out.println(rand);
			break;
		}
		case 2: {
			rand = (random.nextInt(12 - 6) + 6);
			System.out.println(rand);
			break;
		}
		case 3: {
			rand = (random.nextInt(18 - 12) + 12);
			System.out.println(rand);
			break;

		}

		}

		return mainIO.getList(rand);

	}

	// -------------------------------------------------------Test
	// Block----------------------------------------------------

	public void TestFunction() {
		tableList = new ArrayList<SudukuTable>();
		singleTable = new SudukuTable();
		display = new printtable();
		FileIO questionIO = new FileIO();
		questionIO.setFile(answer);
		questionIO.ReadList();

		singleTable = questionIO.getList(0);
		// System.out.println(singleTable.getTable()[4][4].getValue());
		// System.out.println(singleTable.getTable()[5][4].getValue());
		display.updateTable(singleTable);
		display.display();

	}

	public static void main(String args[]) {
		Sudoku m = new Sudoku();

		// m.TestFunction();
		// System.out.printf("|"+" \033[37m"+" %d",2);
		// System.out.println("\033[31;41mTEST\033[0m");
		// System.out.println("\033[37m"+ "123");

	}

}
