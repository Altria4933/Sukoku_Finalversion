package sudoku;

public class printtable {
    public SudukuTable table=new SudukuTable();
    
    public void updateTable (SudukuTable table) {
    	this.table = table;
    }
    
	public void display()
	{
		// TODO Auto-generated method stub
         System.out.println("---------------------------------------------------------------");
         System.out.printf("  ");
         for(int colums=1;colums<=3;++colums)
         {
        	 System.out.printf(" ,  %d  ,  %d  ,  %d  ",colums*3-2,colums*3-1,colums*3);
         }
         
         System.out.printf(",\n");
         for(int a=0;a<19;++a)
         {
        	
        	 if(a%6==0)
        	 {
        		 System.out.printf("  +");
        		 for(int b=0;b<3;++b)
        		 {
        			 System.out.printf("+=====+=====+=====+");
        		 }
        		 System.out.printf("+");
            	 System.out.printf("\n");
        	 }
        	 else if(a%2!=0)
        		 {
        			 System.out.printf("%c",a/2+'A');
        			 System.out.printf(" |");
        			 int number=0;
        			 while(number<9)
        			 {
        				 int value=table.getTable()[a/2][number].getValue();
        				 boolean writeable=table.getTable()[a/2][number].isWriteable();
        				 boolean isTrue=table.getTable()[a/2][number].isIs_true();
        				
        				 if(writeable==true&&value!=0&&isTrue==true)
        				 {
        					 System.out.printf("| \033[32m %d \033[0m ",value);
        					 //System.out.print(writeable);
        				 }
        				 else if(writeable==true&&value!=0&&isTrue==false)
        				 {
        					 System.out.printf("| \033[31m %d \033[0m ",value);
        					 //System.out.print(writeable);
        				 }
        				 else if(writeable==false&&value!=0)
        				 {
        					 System.out.printf("|  %d  ",value);
        					 //System.out.print(writeable);
        				 }
        				 else
        				 {
        					 System.out.printf("|  %s  "," ");
        					 //System.out.print(writeable);
        				 }
        				 if(number==2||number==5||number==8)
        				 {
        					 System.out.printf("|");
        				 }
        				 ++number;
        			 }
        			 System.out.printf("|");
        			 System.out.printf("\n");
        			 
        		 }
            else
                 {
                     System.out.print("  +");
                     for(int index =0; index < 3; index++)
                     {
                         System.out.print("+-----+-----+-----+");
                     }
                     System.out.println("+");
                 }
         }
         System.out.println("---------------------------------------------------------------");
	}

}
