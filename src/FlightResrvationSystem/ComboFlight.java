/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlightResrvationSystem;
/**
 * Class that is used to create a blueprint of Combo-Flight type objects
 * @author Ashish
 */
public class ComboFlight
{
    String Source;
    String via;
    String SpiceNo;
    String dateSpice;
    String timeDSpice;
    String timeASpice;
    long timeDelay;
    String SilkNo;
    String dateSilk;
    String timeDSilk;
    String timeASilk;
    int seats[]=new int[2];
    long totalTime;
    public ComboFlight(String Source,String via,String SpiceNo,String dateSpice,String timeDSpice,String timeASpice,String timeDSilk,String timeASilk,String SilkNo,String dateSilk,long timeDelay,int arr[],long totalTime){
        this.Source=Source;
        if(via.equalsIgnoreCase("Bengaluru"))
            this.via="BLR";
        else if(via.equalsIgnoreCase("Chennai"))
            this.via="MAA";
        else if(via.equalsIgnoreCase("Hyderabad"))
            this.via="HYD";
        else
            this.via="CCU";
        this.SpiceNo=SpiceNo;
        this.dateSpice=dateSpice;
        this.timeDSpice=timeDSpice;
        this.timeASpice=timeASpice;
        this.timeDSilk=timeDSilk;
        this.timeASilk=timeASilk;
        this.SilkNo=SilkNo;
        this.dateSilk=dateSilk;
        this.timeDelay=(timeDelay/60);
        this.seats=arr;
        this.totalTime = totalTime;
    }  
}
