package form;

import java.util.*;
import java.io.*;
/**
 * 
 * @author Andrew
 *	This method is used to read specific block in the xml file.
 */
public class readNfo {
	
	public readNfo(){
		
	}
	
	public String readAttribute(String attribute, String fileName){
		String outPut = null;
		try
        {
            String file = fileName;
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line = null;
            String str = "";
            while((line = in.readLine())!=null)
            {
             str+=line;
            }
            in.close();
            
            if(str.contains(attribute)){
            	String begin = "<" + attribute + ">";
            	String end = "</" + attribute + ">";
            	int i = str.indexOf(begin);
            	int j = str.indexOf(end);
            	outPut = str.substring(i+attribute.length()+2, j);
            	
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
		return outPut;
	}
}
