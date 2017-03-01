/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Joel
 */
public class Log
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
        private Calendar cal;
        public Log()
        {
            list = new String[9];
            port = new String[2];
            cal = Calendar.getInstance();
        }
        public void setValues()
        {
            ID=Integer.parseInt(list[0]);
            upTime= Integer.parseInt(list[3].substring(0,list[3].indexOf(':')))*3600000;
            upTime+= Integer.parseInt(list[3].substring(list[3].indexOf(':')+1,list[3].lastIndexOf(':')))*60000;
            upTime+= Integer.parseInt(list[3].substring(list[3].lastIndexOf(':')+1))*1000;
            protocol=list[4];
            port[0]=list[5];
            port[1]=list[6];
            srcIP=list[7];
            dstIP=list[8];
        }
        public void setTime()
        {
            int year =Integer.parseInt(list[1].substring(6));
            int month =Integer.parseInt(list[1].substring(0,2));
            int day= Integer.parseInt(list[1].substring(3,5));
            int hours= Integer.parseInt(list[2].substring(0,2));
            int min= Integer.parseInt(list[2].substring(3,5));
            int sec= Integer.parseInt(list[2].substring(6));
            //Calendar.set(year + 1900, month, date, hrs, min, sec);
            cal.set(year,month-1,day,hours,min,sec);
            time = cal.getTime();
        }
        public String toString()
        {
            String temp = "";
            for(int i = 0; i < 9;i++)
                    temp = temp + list[i] + " ";
            return temp;
        }
        public long getTime()
        {
            return time.getTime()/1000;
        }
        
    }