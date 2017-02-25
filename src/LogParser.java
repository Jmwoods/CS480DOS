
import java.io.*;
import java.util.*;

public class LogParser {
    
    private BufferedReader reader;
    private ArrayList<log> list;
    
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
            n = new log();
            n.list = temp.split(" ");
			
            n.setTime();
            n.setValues();
            list.add(n);
        }
    }    
}

  private static class log
    {
        String[] list;
        
        //use getTime() for time interval check
        int ID;
        int upTime;
        String protocol;
        String[] port;
        String srcIP;
        String dstIP;
        Date time;
        
        public log()
        {
            list = new String[9];
        }
        public void setValues()
        {
            ID=Integer.parseInt(list[0]);
            upTime= Integer.parseInt(list[3].substring(0,2))*3600000;
            upTime+= Integer.parseInt(list[3].substring(3,5))*60000;
            upTime+= Integer.parseInt(list[3].substring(6))*1000;
            protocol=list[4];
            port[0]=list[5];
            port[1]=list[6];
            srcIP=list[7];
            dstIP=list[8];
        }
        public void setTime()
        {
            int year =Integer.parseInt(list[1].substring(6))-1900;
            int month =Integer.parseInt(list[1].substring(0,2));
            int day= Integer.parseInt(list[1].substring(3,5));
            int hours= Integer.parseInt(list[2].substring(0,2));
            int min= Integer.parseInt(list[2].substring(3,5));
            int sec= Integer.parseInt(list[2].substring(6));
            time = new Date(year,month,day,hours,min,sec);
        }
        
    }
