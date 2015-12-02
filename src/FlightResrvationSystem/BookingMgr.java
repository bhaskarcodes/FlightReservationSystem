/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlightResrvationSystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
/**
 *
 * @author ASHISH
 */
public class BookingMgr 
{
    FRSManager mgr;
    BookingMgr(FRSManager mgr)
    {
        this.mgr=mgr;
    }
    public boolean checkAvailibility(String FlightNoSpice,String DateOfSpice,String FlightNoSilk,String DateOfSilk,int pass)
    {
        int arr[]=new int[2];
        arr=mgr.dMgr.getPassengerNumber(FlightNoSpice, DateOfSpice, FlightNoSilk, DateOfSilk);
        if(((arr[0])<mgr.passNum) || ((arr[1])<mgr.passNum))
        {
            return false;
        }
        else
            return true;
    }
    Ticket generateTicket(String Name,String FlightNoSpice,String DateOfSpice,String FlightNoSilk,String DateOfSilk,int pass)
    {
        String BookID=null;
        try
        {
            BufferedReader br=new BufferedReader(new FileReader("bookedTickets.csv"));
            String line=null;
            while((line=br.readLine())!=null)
            {
                    StringTokenizer t=new StringTokenizer(line,"|");
                    BookID=t.nextToken();
                    String Nm=t.nextToken();
                    String DateSpice=t.nextToken();
                    String SpiceFNo=t.nextToken();
                    String DateSilk=t.nextToken();
                    String SilkFNo=t.nextToken();                   
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
        StringTokenizer ticket=new StringTokenizer(BookID,"-");
        String tem=ticket.nextToken();
        int no=Integer.parseInt(ticket.nextToken());
        no++;
        String BKNo="QRK-"+no;
        Ticket t=new Ticket(Name,BKNo,pass,DateOfSpice,FlightNoSpice,DateOfSilk,FlightNoSilk);
        return t;
    }
}
