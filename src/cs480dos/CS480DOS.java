/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Joel
 */
public class CS480DOS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws IOException 
    {
        File logFile = new File("C:\\Users\\user\\Desktop\\testlog.txt\\");
        LogParser lp = new LogParser(logFile);
        ArrayList<Log> list = lp.readText();
        LogAnalysis results = new LogAnalysis(list);
        results.setChunks();
        ArrayList<int[]> chunks = results.getChunks();
        System.out.println(chunks.size());
        for(int index = 0; index < chunks.size();index++)
        {
            //System.out.print(chunks.get(index)[0]+" ");
            //System.out.println(chunks.get(index)[1]);
            for(int i = chunks.get(index)[0]; i <= chunks.get(index)[1];i++)
                System.out.println(list.get(i));
            System.out.println();
        }
    }
    
}
