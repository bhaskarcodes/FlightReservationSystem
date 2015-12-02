/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlightResrvationSystem;

import java.util.ArrayList;
import java.io.*;
/**
 *
 * @author ASHISH
 */
public class FRSManager 
{
    DataMgr dMgr;       
    DisplayMgrInterface dspMgr;
    SearchMgr sMgr;
    BookingMgr bMgr;
    String SearchCity;
    String SearchDate;
    ArrayList<Flight> Silk;
    ArrayList<Flight> Spice;
    ArrayList<ComboFlight> ResCombo;
    int selectedResult;
    int passNum;
    Ticket genTicket;
    String name;
    public static void main(String args[]) throws IOException
    {
        int i=2;
        String SpiceNm=args[0];
        String SilkNm=args[1];
        String inter=null;
        if (args.length==3)
        {
            if(args[2].equalsIgnoreCase("swing"))
            i=1;
            else
            {
                i=2;
                System.out.println("Cannot Understand You! Continuing with CLI");
            }
        } 
        FRSManager MGR=new FRSManager();
        MGR.dMgr=new DataMgr(MGR);       
        MGR.sMgr=new SearchMgr(MGR);
        MGR.bMgr=new BookingMgr(MGR);
        MGR.Silk=MGR.dMgr.readSilkAir(SilkNm);
        MGR.Spice=MGR.dMgr.ReadJetAir(SpiceNm);
        if(i==1)
        {
            MGR.dspMgr=new DisplayMgr(MGR);
            MGR.dspMgr.ShowScreen1();
        }
        else
        {
            MGR.dspMgr=new CLIMgr(MGR);
            MGR.dspMgr.ShowScreen1();
            
        }
     }
}
