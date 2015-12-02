# Flight Reservation System (Using Java & Swing )  

<b>Team Members :  </b>  

1. Ashish Kumar Sarkar (Team Lead)  

2. Bhaskar Tejaswi (Logic Designer)  

3. Mukul Anand (UI Designer)  

4. Ankit Anurag (Data Designer)   

<b>Aim :</b>  

To develop a system which enables users to search for the best combination of flight from select Indian cities to Singapore and book tickets on the basis of availability ,keeping in mind some external restrictions mentioned in the problem specification. Focus was given on adopting best design practices 

and making the system user friendly and efficient.  
  
<b>Problem Statement :  </b>  

To expand its route offering to north-western India, SilkAir (www.silkair.com), the regional wing of Singapore Airlines, is partnering SpiceJet (www.spicejet.com).  Under the tie-up, travellers from cities like Delhi, Mumbai and Pune will be able to enjoy a seamless flight connection to Singapore.  

SpiceJet will provide the connection from these north-western cities to Bangalore, Chennai, Hyderabad and Kolkata, where SilkAir currently operates flights to Singapore.

For example, a traveller can take SpiceJet flight SG 315 from Delhi (DEL) to Chennai (MAA), and then SilkAir flight MI5853 from Chennai (MAA) to Singapore (SIN). 

 SG315	 1705 hrs 1955 hrs    
 DEL - MAA  
 MI5853	 2315 hrs  0605 hrs (+1 day)  
 MAA - SIN  

<b>The Requirements : </b>   
  
Develop a Flight Reservation System for travellers from Delhi, Mumbai and Pune to

1. Search for one-way flights to Singapore  

2. View the list of connecting options, ranked by flight duration  

3. Select and book flights for up to 10 people in a group - include a booking reference number and 1 passenger name  

Flight details are provided in the following *.csv files:  

SpiceJet Schedule -  2015.spicejet.csv

SilkAir Schedule - 2015.silkair.csv

SpiceJet Schedule - see http://www.spicejet.com/Schedules.aspx  

SilkAir Schedule - see http://www.silkair.com/scheduleSearch-flow.form?execution=e1s1  

<b>The Assumptions :</b>    

1. There needs to be a minimum of 120 minutes between connecting flights for baggage transfer, and a maximum of 6 hours.

2. Travel will take place between October1 and October24, 2015.  If the domestic flight takes off on October24, it is permissible that the connecting international flight lands on October25.

3. There is a time difference of 2-1/2 hours between India and Singapore.

4. Each flight can take a total of 15 passengers.

5. Passengers travelling in a group must stay together on all flight segments, i.e. groups cannot be split across flights.

<b>Provision for Display Upgrade : </b>  

The Display upgrade capability is to be demonstrated by the option to switch between  

1.A text-based user interface i.e. Terminal  

2.A graphical user interface i.e. Swing.  

The Display will be defaulted to Terminal.   

To invoke the Swing Display, an extra parameter is to be provided.  

Example : java FlightReservationSystem 2015.spicejet.csv 2015.silkair.csv swing
