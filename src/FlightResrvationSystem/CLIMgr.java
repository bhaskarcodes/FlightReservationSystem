/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlightResrvationSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASHISH
 */
public class CLIMgr implements DisplayMgrInterface
{
    FRSManager MGR;
    public CLIMgr(FRSManager mgr)
    {
        this.MGR=mgr;
    }
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    public void ShowScreen1() 
    {
            try 
            {
                System.out.print("\n\nEnter Source(DELHI/MUMBAI/PUNE): ");
                MGR.SearchCity=br.readLine().trim();
                System.out.print("Enter Date of Travel(MMM dd, yyyy): ");
                MGR.SearchDate=br.readLine().trim();
                while(true)
                {
                    System.out.print("Enter Number of Passengers(1-10): ");
                    try
                    {
                        MGR.passNum=Integer.parseInt(br.readLine());
                        break;
                    }
                    catch(Exception e)
                    {
                        System.out.println("Invalid Passenger No., Enter a Number B/W 1-10");
                        
                    }
                }
                MGR.ResCombo=MGR.sMgr.Combine(MGR.Spice, MGR.Silk, MGR.SearchCity, MGR.SearchDate);
                int n=MGR.ResCombo.size();
                if(n==0)
                {
                    System.out.println("No Flights Found");
                    MGR.dspMgr.ShowScreen1();
                }
                else
                {
                    MGR.dspMgr.ShowScreen2();
                }
                
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(CLIMgr.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }
    public void ShowScreen2() 
    {
        try 
        {
            int n=MGR.ResCombo.size();
            System.out.println();
            for(int j=0;j<n;j++)
            {
                ComboFlight res=MGR.ResCombo.get(j);
                String Result=(j+1)+". FROM:"+res.Source+" || SPICEJET-FlightNo.:"+res.SpiceNo +" | DepartureTime:"+res.timeDSpice+" | ArrivalTime:"+res.timeASpice+
                        "\n   Delay(Minutes):"+res.timeDelay+ "\n   Via:"+res.via+"|| SILKAIR-FlightNo.:"+res.SilkNo+" | DepartureTime:"+res.timeDSilk+" | ArrivalTime:"+res.timeASilk;
                System.out.println(Result);
            }
            while(true)
            {
                int SRes;
                while(true)
                {    
                    System.out.print("\nEnter the FlightChoice No.(Eg.=1,2...)");
                    try
                    {
                        SRes=Integer.parseInt(br.readLine().trim())-1;
                        break;
                    }
                    catch(NumberFormatException e)
                    {
                        System.out.println("Try Again");
                    }
                }
                if(SRes>=0 && SRes<n)
                {
                    MGR.selectedResult=SRes;
                    MGR.dspMgr.ShowScreen3();
                    break;
                }
                else
                    System.out.println("Try Again");
            }
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(CLIMgr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ShowScreen3() 
    {
        try 
        {
            System.out.print("\nEnter Your Name: ");
            MGR.name=br.readLine();
            MGR.dspMgr.ShowScreen4();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(CLIMgr.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void ShowScreen4() 
    {
        
        try 
        {
            ComboFlight res=MGR.ResCombo.get(MGR.selectedResult);
            MGR.genTicket=MGR.bMgr.generateTicket(MGR.name,res.SpiceNo , res.dateSpice, res.SilkNo, res.dateSilk, MGR.passNum);
            MGR.dMgr.Book(MGR.genTicket);
            System.out.println("Booking CONFIRMED");
            System.out.println("Booking ID: "+MGR.genTicket.BookId);
            System.out.println("Name: "+MGR.genTicket.Name);
            System.out.println("Flight Details");
            System.out.println("SPICEJET: "+MGR.genTicket.Spice+" "+MGR.genTicket.DateSpice);
            System.out.println("SILKAIR: "+MGR.genTicket.Silk+" "+MGR.genTicket.DateSilk+"\n\n");
            
            System.out.println("\nEnter:: \n1 to RETRY \n2 to Exit");
            int choice=Integer.parseInt(br.readLine());
            switch(choice)
            {
                case 1:MGR.dspMgr.ShowScreen1();
                       break;
                case 2:System.exit(0);
                    break;
                default:System.out.println("Invalid choice,Terminating.Thank You"); 
                        System.exit(0);
            }
        } catch (IOException ex) {
            Logger.getLogger(CLIMgr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
