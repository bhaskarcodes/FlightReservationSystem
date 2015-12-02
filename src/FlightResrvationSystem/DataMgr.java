package FlightResrvationSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASHISH
 */
public class DataMgr 
{
    FRSManager mgr;
    DataMgr(FRSManager mgr)
    {
        this.mgr=mgr;
    }
    public ArrayList<Flight> readSilkAir(String filename)
    {
        ArrayList<Flight> arr=new ArrayList<>();
        try
        {
            BufferedReader br=new BufferedReader(new FileReader(filename.trim()));
            String line=null;
            int i=1;
            while((line=br.readLine())!=null)
            {
                if(i>3)
                {
                    StringTokenizer tokn=new StringTokenizer(line,"|");
                    String depCity=tokn.nextToken();
                    String daysOfWeek=tokn.nextToken();
                    String FlightNumber=tokn.nextToken();
                    String time=tokn.nextToken();
                    
                    StringTokenizer timedivide=new StringTokenizer(time,"/");
                    String depTime=timedivide.nextToken();
                    String arrTime=timedivide.nextToken();
                    //StringTokenizer arrDiv=new StringTokenizer(arTime,"+");
                    //String arrTime=arrDiv.nextToken();
                    StringTokenizer City=new StringTokenizer(depCity," ");
                    String finalCity=City.nextToken();
                    
                    int days[]={0,0,0,0,0,0,0};
                    StringTokenizer daysFind=new StringTokenizer(daysOfWeek,",");
                    while(daysFind.hasMoreTokens())
                    {
                        String tem=daysFind.nextToken();
                        if(tem.equalsIgnoreCase("Mon"))
                            days[1]=1;
                        else if(tem.equalsIgnoreCase("Tue"))
                            days[2]=1;
                        else if(tem.equalsIgnoreCase("Wed"))
                            days[3]=1;
                        else if(tem.equalsIgnoreCase("Thu"))
                            days[4]=1;
                        else if(tem.equalsIgnoreCase("Fri"))
                            days[5]=1;
                        else if(tem.equalsIgnoreCase("Sat"))
                            days[6]=1;
                        else if(tem.equalsIgnoreCase("Sun"))
                            days[0]=1;
                    }
                    Flight obj=new Flight(finalCity.trim(),"SINGAPORE",days,FlightNumber.trim(),depTime.trim(),arrTime.trim(),"N/A","N/A");
                    arr.add(obj);                 
                }
                else
                    i++;
            }
            br.close();
        }        
        catch(FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }  
        return arr;
    }
    
    
    ArrayList<Flight> ReadJetAir(String filename)
    {
        ArrayList<Flight> arr=new ArrayList<>();
        try
        {
            BufferedReader br=new BufferedReader(new FileReader(filename));
            String line=null;
            int i=1;
            while((line=br.readLine())!=null)
            {
                if(i>5)
                {
                    StringTokenizer t=new StringTokenizer(line,"|");
                    String origin=t.nextToken();
                    String dest=t.nextToken();
                    String freq=t.nextToken();
                    String FNo=t.nextToken();
                    String DeptT=t.nextToken();
                    String ArrT=t.nextToken();
                    String via=t.nextToken();
                    String EffFrom=t.nextToken();
                    String EffTill=t.nextToken();
                    
                    int days[]={0,0,0,0,0,0,0};
                    
                    StringTokenizer dayDiv=new StringTokenizer(freq,",");
                    while(dayDiv.hasMoreTokens())
                    {
                        String test=(dayDiv.nextToken()).trim();
                        if(test.equalsIgnoreCase("daily"))
                        {
                            for(int j=0;j<7;j++)
                                days[j]=1;
                        }
                        else
                        {
                            if(test.equalsIgnoreCase("MONDAY"))
                                days[1]=1;
                            else if(test.equalsIgnoreCase("TUESDAY"))
                                days[2]=1;
                            else if(test.equalsIgnoreCase("WEDNESDAY"))
                                days[3]=1;
                            else if(test.equalsIgnoreCase("THURSDAY"))
                                days[4]=1;
                            else if(test.equalsIgnoreCase("FRIDAY"))
                                days[5]=1;
                            else if(test.equalsIgnoreCase("SATURDAY"))
                                days[6]=1;
                            else if(test.equalsIgnoreCase("SUNDAY"))
                                days[0]=1;
                            
                        }
                    }
                    Flight New=new Flight(origin.trim(),dest.trim(),days,FNo.trim(),DeptT.trim(),ArrT.trim(),EffFrom.trim(),EffTill.trim());
                    arr.add(New);
                              
                }
                else
                    i++;
            }
            br.close();            
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }  
        return arr;        
    }
    //Returns Integer array[2]={SpiceNoLeft,SilkNoLeft}
    int[] getPassengerNumber(String FlightNoSpice,String DateOfSpice,String FlightNoSilk,String DateOfSilk)
    {
        int passNum[]={15,15};
        try
        {
            BufferedReader br=new BufferedReader(new FileReader("bookedTickets.csv"));
            String line=null;
            while((line=br.readLine())!=null)
            {
                    StringTokenizer t=new StringTokenizer(line,"|");
                    String BookID=t.nextToken();
                    String Name=t.nextToken();
                    String DateSpice=t.nextToken();
                    String SpiceFNo=t.nextToken();
                    String DateSilk=t.nextToken();
                    String SilkFNo=t.nextToken();
                    int num=Integer.parseInt(t.nextToken());
                    if(DateSpice.equalsIgnoreCase(DateOfSpice) && SpiceFNo.equalsIgnoreCase(FlightNoSpice))
                    {
                        passNum[0]=passNum[0]-num;
                    }
                    if(DateSilk.equalsIgnoreCase(DateOfSilk)&&SilkFNo.equalsIgnoreCase(FlightNoSilk))
                    {
                        passNum[1]-=num;
                    }
            }
            br.close();            
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }  
        return passNum;        
    }
    void Book(Ticket t)
    {
        
        try 
        {
            BufferedReader br=new BufferedReader(new FileReader("bookedTickets.csv"));
            String line=null;
            PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter("TbookedTickets.csv")));
            while((line=br.readLine())!=null)
            {
                pw.println(line);
            }
            br.close();
            pw.close();
            br=new BufferedReader(new FileReader("TbookedTickets.csv"));
            line=null;
            pw=new PrintWriter(new BufferedWriter(new FileWriter("bookedTickets.csv")));
            while((line=br.readLine())!=null)
            {
                pw.println(line);
            }
            String Tickt=t.BookId+"|"+t.Name+"|"+t.DateSpice+"|"+t.Spice+"|"+t.DateSilk+"|"+t.Silk+"|"+t.passNum+"|";
            
            pw.println(Tickt);
            br.close();
            pw.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
