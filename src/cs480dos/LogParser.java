


import java.io.*;
import java.util.*;

public class LogParser {
    
    private BufferedReader reader;
    
    // Initialize parser with the file to be read
    public LogParser(File file) throws FileNotFoundException {
            reader = new BufferedReader(new FileReader(file));
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