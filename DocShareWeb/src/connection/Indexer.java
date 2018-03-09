package connection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class Indexer {
	
	
	public static int getIndex() {
		
		int index=0;
		File file=new File("C:\\Users\\Fabio\\Desktop\\Università\\IGES\\REPOSITORY\\indexDB.txt");
		try {
		FileReader f = new FileReader(file);
		BufferedReader b;
	    b=new BufferedReader(f);
	    
	    
	    String s="";
	    String c;
	    while(true) {
	        c=b.readLine();
	        if(c==null)
	          break;
	        s=c;
	      }
	    index=Integer.parseInt(s);
	    
	    int daScrivere=index+1;
	    FileWriter writer= new FileWriter(file);
	    writer.write(""+daScrivere+"");
	    writer.flush();
	    writer.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return index;
		
	}

}
