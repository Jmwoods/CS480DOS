/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs480dos;

import java.util.ArrayList;

/**
 *
 * @author Joel
 */
public class LogAnalysis {
    
    private ArrayList<Log> list;
    private ArrayList<int[]> chunkList;
    public LogAnalysis(ArrayList<Log> l)
    {
        list = l;
        chunkList = new ArrayList<int[]>();
    }
    public void setChunks()
    {
        int[] range = new int[2];
        range[0] = 0;
        range[1] = 0;
        for(int index = 1; index < list.size(); index++)
        {
            //while time difference is less then 2 add to current chunk range
            if(Math.abs(list.get(index).getTime() - list.get(index-1).getTime()) < 6)
                range[1]++;
            else //list at index is not part of any previous attack so move next range here
            {
                if(range[1]-range[0]>=400)// current chunk is big enough to be threat can be adjusted for different levels later
                    chunkList.add(range);
                range = new int[2];
                range[0] = index;
                range[1] = index;
            }
        }
    }
    public ArrayList<int[]> getChunks()
    {
        return chunkList;
    }
            
}
