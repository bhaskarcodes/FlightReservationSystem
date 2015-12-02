/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlightResrvationSystem;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Collections;
/**
 * This is a part of the Flight Reservation System designed and implemented by Team Quarks.
 * The combine method returns a list of ComboFlights, sorted in increasing order of total journey duration.
 * @author Bhaskar Tejaswi - Logic Designer
 */
public class SearchMgr 
{
    
   FRSManager mgr; 

   SearchMgr(FRSManager mgr)
    {
        this.mgr=mgr;
    }
    
    public ArrayList<ComboFlight> Combine(ArrayList<Flight> Spice,ArrayList<Flight> Silk,String Source,String TDate)
    {   
      String spicedepstr=TDate;
      String spicearrstr=TDate;
      String silkdepstr = TDate;
      String silkarrstr = TDate;
      ArrayList<ComboFlight> selectedCombo = null; 
      ArrayList<Flight> SelectedSpice= new ArrayList<Flight>();     
      Calendar c = Calendar.getInstance();
      Date dep = new Date();
      Date spicearr = new Date();
      Date spicedep = new Date();
      Date silkdep = new Date();
      Date silkarr = new Date();
      Date dateFrom = new Date();
      Date dateTill = new Date();
      
      SimpleDateFormat time1=new SimpleDateFormat("MMM dd, yyyy"); //  MMM dd, yyyy
      try
      {
            spicedep = time1.parse(TDate);
            c.setTime(spicedep); 
            //Check if date lies between 01 oct 2015 and 24 oct 2015
            Date upper = time1.parse("OCT 24, 2015");       //upper limit
            Date lower = time1.parse("OCT 01, 2015");       //lower limit
            if(spicedep.after(upper) || spicedep.before(lower))      
            {
                System.out.println("Sorry, Your Flight Date is Not Valid");
                mgr.dspMgr.ShowScreen1();    //return selectedCombo;
            }      //return empty arraylist as date is outside range
            else
                selectedCombo = new ArrayList<ComboFlight>();   
      }
      catch(ParseException e)
      {
          System.out.println("Sorry, Incorrect Date Format");
            mgr.dspMgr.ShowScreen1();//e.getMessage());
      }
      for(Flight spFlight:Spice)
      { 
         if((spFlight.source).equalsIgnoreCase(Source)==true && (spFlight.daysOfWeek[c.get(Calendar.DAY_OF_WEEK )-1]==1)) 
         {// source match and day match
                                time1=new SimpleDateFormat("dd MMM yy");
                                try
                                {
                                
                                    dateFrom = time1.parse(spFlight.ValidFrom);
                                    dateTill = time1.parse(spFlight.ValidTill);
                                    
                                }
                                catch(ParseException e){
                                    System.out.println(e.getMessage());
                                }
                                if((dateFrom.before(spicedep)||dateFrom.equals(spicedep)) && (dateTill.after(spicedep)||dateTill.equals(spicedep)))
                                {                       
                                    SelectedSpice.add(spFlight);
                                }
         }
      }   
      /*trying to optimise search results by prefiltering the spice arraylist as per source*/
     for(Flight spFlight :SelectedSpice){  
         for(Flight silkFlight:Silk){
             
             if(silkFlight.source.equalsIgnoreCase(spFlight.destination)){
  
                  time1=new SimpleDateFormat("hh:mm aa MMM dd, yyyy"); // spice time 
                  SimpleDateFormat time2=new  SimpleDateFormat("HHmm MMM dd, yyyy");   // silktime   
                  SimpleDateFormat format1 = null;
                    try{
                          spicedep = time1.parse(spFlight.depTime+" "+spicedepstr);
                          spicearr = time1.parse(spFlight.arrTime+" "+spicearrstr);
                          c.setTime(spicedep);
                          format1 = new SimpleDateFormat("MMM dd, yyyy");
                          spicearrstr = format1.format(c.getTime());
                         if(spicedep.after(spicearr))
                        {                               
                            c.add(Calendar.DATE,01);
                            spicearr = c.getTime();
                            spicearrstr = format1.format(c.getTime());
                        }
                        silkdepstr = spicearrstr;
                        silkdep  = time2.parse(silkFlight.depTime+" "+silkdepstr);
                        c.setTime(silkdep);
                         if(spicearr.after(silkdep))
                        {   
                            c.add(Calendar.DATE,01);
                            silkdep = c.getTime();
                            silkdepstr = format1.format(c.getTime());
                        }
                      }
                        catch(ParseException e)
                        {
                            System.out.println(e.getMessage());
                        } 
                        if(silkFlight.daysOfWeek[c.get(Calendar.DAY_OF_WEEK )-1]==1){        
                        try
                        {
                            silkdep = time2.parse(silkFlight.depTime+" "+silkdepstr);
                            spicearr = time1.parse(spFlight.arrTime+" "+spicearrstr);    
                        }
                        catch(ParseException e)
                        {
                            System.out.println(e.getMessage());
                        }
                          long spicearrtime = spicearr.getTime() /1000;  
                          long silkdeptime = silkdep.getTime() /1000; 
                         if(silkdeptime-spicearrtime >=7200  && silkdeptime-spicearrtime <=21600){  //more than 2 less than 6 hour waiting time
                               
                               silkarrstr = silkdepstr;
                               String timearr = silkFlight.arrTime.substring(0, 4);                     
                     if(silkFlight.arrTime.endsWith("+1")){
                        try
                        {  
                         silkarr = time2.parse(silkFlight.arrTime.substring(0,4)+" "+silkarrstr);
                         c.setTime(silkarr); 
                         c.add(Calendar.DATE,01);
                         silkarrstr = format1.format(c.getTime());
                         silkarr = c.getTime();
                        }
                        catch(ParseException e){
                            System.out.println(e.getMessage());
                        }
                     }
                     else
                     {
                       try
                       {
                         silkarr = time2.parse(silkFlight.arrTime.substring(0,4)+" "+silkarrstr);
                         c.setTime(silkarr); 
                       }
                       catch(ParseException e){
                           System.out.println(e.getMessage());
                       }
                     }
                      String spice24dep="";
                      String spice24arr="";
                     try
                     {
                         SimpleDateFormat time4=new SimpleDateFormat("hh:mm aa");
                         SimpleDateFormat time3 = new SimpleDateFormat("HHmm");
                         Date spice24 = time4.parse(spFlight.depTime);
                         spice24dep = time3.format(spice24);
                         spice24 = time4.parse(spFlight.arrTime);
                         spice24arr = time3.format(spice24);
                     }
                     catch(ParseException e)
                     {
                         System.out.println(e.getMessage());
                     }
                                c.setTime(silkarr);
                                c.add(Calendar.HOUR,-02);
                                c.add(Calendar.MINUTE,-30);
                                silkarr = c.getTime(); 
                                long totalJourney = silkarr.getTime() - spicedep.getTime();
                                int[] arr = new int[2];             
                                
                                 arr = mgr.dMgr.getPassengerNumber(spFlight.FlightNumber,TDate,silkFlight.FlightNumber,silkarrstr); //String FlightNoSpice,String DateOfSpice,String FlightNoSilk,String DateOfSilk
                                if(mgr.bMgr.checkAvailibility(spFlight.FlightNumber,TDate,silkFlight.FlightNumber,silkarrstr,mgr.passNum))//arr[0]>1 && arr[1]>1 ){ 
                                {
                                    ComboFlight myCombo = new ComboFlight(Source,spFlight.destination,spFlight.FlightNumber,TDate,spice24dep,spice24arr,silkFlight.depTime,silkFlight.arrTime,silkFlight.FlightNumber,silkarrstr,silkdeptime-spicearrtime,arr,totalJourney);
                                    selectedCombo.add(myCombo);
                                }            
                              }
                         
                    }                    
                }       
            }
        }  
     
    Comparator<ComboFlight> comparator = new Comparator<ComboFlight>()
    {
            public int compare(ComboFlight combo1, ComboFlight combo2) {  //inner classes 
                    if(combo1.totalTime > combo2.totalTime)
                          return 1;                               
                    else 
                         return -1;             
            }
        };
        Collections.sort(selectedCombo,comparator); 
        if(selectedCombo.isEmpty())
        {
            
            System.out.println("No Flights Found");
            mgr.dspMgr.ShowScreen1();
        }
        return(selectedCombo);
    }//EndOfMethodCombine
}
