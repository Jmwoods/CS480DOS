
import java.io.*;
import java.util.*;

public class LogParser {
    
    private BufferedReader reader;
    private ArrayList<node> list;
    
    // Initialize parser with the file to be read
    public LogParser(File file) {
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException ex) {
            reader = null;
        } finally {
            list = new ArrayList<node>();
        }
    }
    
    // Returns whether the parser is able to read
    public boolean canRead() {
        return reader != null;
    }
    
    private void readText() throws IOException {
        
        String temp;
        node n;
        	   
        while((temp = reader.readLine())!= null) {
            n = new node();
            n.list = temp.split(" ");
            list.add(n);
        }
    }    
}

class node {
    String[] list;
    public node() {
        list = new String[9];
    }
}
