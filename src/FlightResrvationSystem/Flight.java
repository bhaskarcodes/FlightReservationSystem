package FlightResrvationSystem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Class that is used to create a blueprint of Flight type objects
 * @author ASHISH
 */
public class Flight 
{
    String source;
    String destination;
    int daysOfWeek[]=new int[7];
    String FlightNumber;
    String depTime;
    String arrTime;
    String ValidFrom;
    String ValidTill;
    public Flight(String source,String dest,int days[],String FNo,String dTime,String aTime,String ValidFrom,String ValidTill)
    {

            this.source=source;
            this.destination=dest;
            this.daysOfWeek=days;
            this.FlightNumber=FNo;
            this.depTime=dTime;
            this.arrTime=aTime;
            this.ValidFrom=ValidFrom;
            this.ValidTill=ValidTill;
            
    }
}
