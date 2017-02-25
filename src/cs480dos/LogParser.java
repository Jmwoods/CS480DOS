package cs480dos;


import java.io.*;
import java.util.*;

public class LogParser {
    
    private BufferedReader reader;
    
    // Initialize parser with the file to be read
    public LogParser(File file) {
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException ex) {
            reader = null;
        }
    }
    
    // Returns whether the parser is able to read
    public boolean canRead() {
        return reader != null;
    }
    
    public ArrayList<Log> readText() throws IOException {
        ArrayList<Log> list;
        list = new ArrayList<Log>();
        String temp;
        Log n;
        while((temp = reader.readLine())!= null) {
            n = new Log();
            n.list = temp.split(" ");	
            n.setTime();
            n.setValues();
            list.add(n);
        }
        if(list.size()!= 0)
            return list;
        else
            return null;
    }      
}