package sudoku;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileIO {
	public static String line;
	static File input;
	ArrayList<SudukuTable> questionarray = new ArrayList<SudukuTable>();
        public HashMap<String,Integer> records=new HashMap<String, Integer>(); 
	public void ReadList() {
		// ArrayList<SudukuTable> questionarray = new ArrayList<SudukuTable>();

		try {
			FileReader fr = new FileReader(input);
			BufferedReader br = new BufferedReader(fr);
			Scanner sc = new Scanner(br);
			while (sc.hasNextLine()) {
				// default level
				SudukuTable newTable = new SudukuTable();
				Level level = Level.Undefined;
				String selector = sc.nextLine();
				//level enum is reserved for future database use, will not used in stage one
				//stage one will pick the right table based on arraylist index (0-5 is easy  6-12 = mid  13-18 = hard)
				if (selector.charAt(0) == 'E') {
					level = Level.Easy;
				} else if (selector.charAt(0) == 'M') {
					level = Level.Midium;
				} else if (selector.charAt(0) == 'H') {
					level = Level.Hard;
				}

				// this for loop is to read each 9 lines from one block
				for (int x = 0; x < 9; x++) {
					line = sc.nextLine();
					// this loop aim to find each single space of the total 81 space of one question
					// block
					for (int y = 0; y < 9; y++) {
						int value = Character.getNumericValue(line.charAt(y));
						// System.out.println(value);
						
						if (value == 0) {
							newTable.setNumber(x, y, value, true);
						} else {
							newTable.setNumber(x, y, value, false);
						}
						newTable.setLevel(level);
					}
				}
				questionarray.add(newTable);
				
			}
			fr.close(); br.close();

			// test block
			// for (int i =0; i < questionarray.size(); i++) {
			// questionarray.get(i).printmyself();
			// }

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
        	public HashMap<String,Integer> readRecords() throws NumberFormatException, IOException 
    {
        BufferedReader br=new BufferedReader(new FileReader("Userfile.txt"));
        try{
            String line=null;
        while((line=br.readLine())!=null)
        {
            String a[]=line.split(" ");
            records.put(a[0], Integer.parseInt(a[1]));
        }
        }
        finally {
           if (br != null) {
                 try {
                     br.close();
                     } catch(Exception ex){
                            ex.printStackTrace();
                                          }
        }
        }
        return records;
    }
    public void writetoFile(HashMap<String,Integer>records) throws FileNotFoundException
    {
         PrintWriter pw=new PrintWriter("Userfile.txt");
       try{ 
          
        for(Map.Entry<String, Integer> entry:records.entrySet())
        {
            pw.println(entry.getKey()+" "+entry.getValue());
        }
       }catch(Exception ex)
       {
           System.out.println("Invalid");
       }
       finally{
           if(pw!=null)
           {
               pw.close();
           }
       }
    }
    
    public HashMap<String, Integer> getMymaps()
    {
    	return this.records;
    }

	private Object fr(File input2) {
		// TODO Auto-generated method stub
		return null;
	}

	public SudukuTable getList(int i) {
		return questionarray.get(i);
	}

	public void setFile(File input) {
		this.input = input;
	}

	// testing only

	// ReadList();
	// questionarray.get(0);

	// System.out.println();

}
