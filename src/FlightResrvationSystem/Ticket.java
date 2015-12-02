/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlightResrvationSystem;

/**
 *
 * @author ASHISH
 */
public class Ticket 
{
    String Name;
    String BookId;
    int passNum;
    String DateSpice;
    String DateSilk;
    String Spice;
    String Silk;
    public Ticket(String Name,String BookId,int num,String DateSpice,String Spice,String DateSilk,String Silk)
    {
        this.Name=Name;
        this.BookId=BookId;
        this.passNum=num;
        this.DateSpice=DateSpice;
        this.Spice=Spice;
        this.DateSilk=DateSilk;
        this.Silk=Silk;
    }
    
}
