package gameticket_package;

import static gameticket_package.Ticket.offeredDiscoun;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 *
 * @author Ahmed Khaled Saber 
 */
public class GameTicket {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
       //Create a SINGLE array of games, which contains national and international games.
       Game[] Games = new Game[5];
       
       Games[0]=new NationalGame("FootBall","Al-Ahly","El-Zamalek","Burg_AlArab Stadium",2020,6,15);
       Games[1]=new InternationalGame("FootBall","Egypt","Italy","Cairo Stadium",2020,6,14);
       Games[2]=new NationalGame("BasketBall","Somouha","Al-Shams","Alex Stadium",2020,6,13);
       Games[3]=new InternationalGame("VollyBall","EgyptYouth","BrazilYouth","El-Gona Stadium",2020,6,12);
       Games[4]=new NationalGame("FootBall","Ismaaily","Itehad","Alex Stadium",2020,6,11);
       
       // Upcoming Games Events 
       for (Game g : Games)
       {
           g.DisplayGameAnnouncement();
           System.out.println("--------------------------------------------------");
       }
       
       //Create an array of seats with different categories for different games.
       Seat [] seats = new Seat[7];
       
       ////"A"for first Category , "B"for Second , "C" for third, this Seats available for any announced Game
       seats[0]=new Seat("A"); 
       seats[1]=new Seat("B");
       seats[2]=new Seat("C");
       seats[3]=new Seat("C");
       seats[4]=new Seat("B");
       seats[5]=new Seat("C");
       seats[6]=new Seat("B");
       
        System.out.println("Seats Prices:\n First Category \"A\": 120 pounds \n Second Category \"B\": 30 pounds \n Third Category \"C\": 15 pounds ");
        System.out.println("Available Seats:");
        for(Seat s : seats)
        {
            System.out.println("");
            s.displaySeat();
            //or we can call
            //s.displayALLDeatails();  
        }
        
        
        System.out.println("");
        System.out.println("");
        System.out.println("==================================================== Fans Section ================================================================");
        
        // The Fans role apears here to traverse Games and available Seats then Choosing one ticket or many 
        Fan [] fans = new Fan[4];
        
        fans[0]=new Fan("Ahmed");
        fans[1]=new Fan("Dalia");
        fans[2]=new Fan("Moaaz");
        fans[3]=new Fan("Tarek");
        
        ///Fans Functionalities, every fan interactions are combinet in the following blocks of code
        
        
        
        //******************************************************* TicketID#1 ********************************************//
        //Reserve a Ticket for the First Fan
        System.out.println("Fan#1");
        System.out.println("");
        fans[0].ReserveTicket(Games[3], seats[2]); 
        // fan reserve the third seat apears on console for the 4th game which apears on console also, the fan choice is:
        /* Game[3]is:                              seats[2]is:
           International VollyBall Game            SeatID:3
           EgyptYouth  vs  BrazilYouth             SeatCategory:C 
           ON  El-Gona Stadium                     Seat Price: 15.0 Pounds
           at  2020-06-12
        */
        fans[0].make_bet("EgyptYouth is the winners");
        fans[0].displayAllFanTickets();
        
        //******************************************************* TicketID#2 ********************************************//
        //Reserve a Ticket for the Second Fan, and test USER DEFINED EXCEPTION by entring wrong id.
        System.out.println("Fan#2");
        System.out.println("");
        fans[1].ReserveTicket(Games[0], seats[1]); 
        // fan reserve the second seat apears on console for the 1st game which apears on console also, the fan choice is:
        /* Game[0]:                                seats[1]:
           National FootBall Game                  SeatID:2
           Al-Ahly  vs  El-Zamalek                 SeatCategory:B 
           ON  Burg_AlArab Stadium                 Seat Price: 30.0 Pounds
           at  2020-06-15
        */
        fans[1].cancelReservation(3); //the ticket id he reserved is #2 ,so wrong ticked id causes USER DEFINED EXCEPTION and show Message to user
        fans[1].displayAllFanTickets(); // still have his ticket because cancel is failed, and it will displayed
        
        //******************************************************* TicketID#3 ********************************************//
        //Reserve a Ticket for the Third Fan, and test JAVA DEFINED EXCEPTION by cancelling ticket for a game after less than 3 days!
        System.out.println("Fan#3");
        System.out.println("");
        fans[2].ReserveTicket(Games[4], seats[0]); 
        // fan reserve the 1st seat apears on console for the 5th game which apears on console also, the fan choice is:
        /* Game[4]:                                 seats[0]:
           National FootBall Game                   SeatID:1
           Ismaaily  vs  Itehad                     SeatCategory:A
           ON  Alex Stadium                         Seat Price: 120.0 Pounds
           at  2020-06-11
        */
        fans[2].cancelReservation(3); //the ticket id is correct but, the game after less than 3 days and, it causes JAVA DEFINED EXCEPTION and show Message to user
        fans[2].displayAllFanTickets(); // still have his ticket because cancel is failed, and it will displayed
        
        //******************************************************* TicketID#4 ********************************************//
        //this fourth fan Reserve two Tickets (Create at least one sport fan with multiple tickets.), and then cancel the first one with its correct id whiout Exception
        System.out.println("Fan#4");
        System.out.println("");
        fans[3].ReserveTicket(Games[1], seats[3]);
        /* Game[1]:                                 seats[3]:
           International FootBall Game              SeatID:4
           Egypt  vs  Italy                         SeatCategory:C
           ON  Alex Stadium                         Seat Price: 15.0 Pounds
           at  2020-06-14
        */
        //******************************************************* TicketID#5 ********************************************//
        fans[3].ReserveTicket(Games[2], seats[4]);
        /* Game[2]:                                seats[4]:
           National BasketBall Game                SeatID:5
           Somouha  vs  Al-Shams                   SeatCategory:B
           ON  Alex Stadium                        Seat Price: 30.0 Pounds
           at  2020-06-13
        */
        //display the two tickets 
        fans[3].displayAllFanTickets();
        //try to Upgrade the last ticket "TicketID#5" Seat[4]: category"B" with an available Seat[5]:Category:c and it will fail ,since upgrade convert to a higher Category
        fans[3].UpgradeTicket(seats[5]);  //FAILED
        fans[3].displayAllFanTickets();
        //now we Cancel the ticket with id 5
        fans[3].cancelReservation(5);
        //display again will display just one ticket for this fan which id is #4 and category "C"
        fans[3].displayAllFanTickets();
        //try to Upgrade the ticked#4 which Category is "C" he has with new Seat[6] which Category is "B" and it is expected to Upgrde Successfully
        fans[3].UpgradeTicket(seats[6]); //UPGRADED SUCCESSFULLY
          
    } 
}
/**
 *this class is implemented to enhance my Design of this mini-System since i would not want to use many basic types in a Game Class so,
 * i divided its responsibilities and encapsulate some operations on this individual class
 * this Class Apply The Single Responsibility Principle "A class should have one, and only one, reason to change."
 */
class Stadium //this Class Apply The Single Responsibility Principle "A class should have one, and only one, reason to change.
{
    public String name;  //different access modifiers
    private int cat1_Seats;
    private int cat2_Seats; 
    private int cat3_Seats; 
    
    /**
     *Default Constructor, construct and initialize a new Stadium Object.
     */
    public Stadium ()
    {
         name = "Cairo Stadium";
         cat1_Seats=1; // it is assumed that many seats were booked
         cat2_Seats=5; 
         cat3_Seats=7; 
    }
    /**
     * @param name specify the name of the stadium holds the Event
     * Default Constructor, construct and initialize a new Stadium Object.
     */
    public Stadium (String name)
    {
         this.name = name;
         cat1_Seats=2; 
         cat2_Seats=5; 
         cat3_Seats=7; 
    }
    //Control the amount of Seats in each Category
    /**
     *
     * @param category decrement the available seats in a given Category 
     * so, if we present this Game on console again changes will happen is seats numbers in categories
     */
    public void decrementCategory(String category) //book
    {
        if (category=="A")
            this.cat1_Seats--;
        if (category=="B")
            this.cat2_Seats--;
        else
            this.cat3_Seats--;
    }
    /**
     *
     * @param category increment the available seats in a given Category 
     * so, if we present this Game on console again changes will happen is seats numbers in categories
     */
    public void inrementCategory(String category) //cancel
    {
        if (category=="A")
            this.cat1_Seats++;
        if (category=="B")
            this.cat2_Seats++;
        else
            this.cat3_Seats++;
    }
    /**
     *this method helps the Game Class by providing a block of statements that checkSeatAvailabillity
     */
    public void checkSeatAvailabillity () 
    {
        System.out.println("Up To now :");
        System.out.println("The First Category has " + this.cat1_Seats + " Free Seats available .");
        System.out.println("The Second Category has "+ this.cat2_Seats + " Free Seats available .");
        System.out.println("The Third Category has " + this.cat3_Seats + " Free Seats available .");        
        System.out.println("");
    }
      
}


/**
 * A{@code Game} object represents an available upcoming Game Event, such as Basketball Game between Al-Ahly and Zamalek.
 * a game has uniqueCode and two different teams and location, National and International Games are supplied.
 */
abstract class Game //Abstract Class
{
    private final String UniqueGeneratedCode; //Final Data Member
    private String forSport;
    private String team1;   
    private String team2;
    protected String type="" ; //protected data field (using Different access modifiers), however it does not give much protection.
    private Stadium Lcation ;
    private LocalDate date;
    
    /**
     *Constructs a Game to show so a User can book a ticket for.
     * @param forSport football,basketball,swimming,....
     * @param team1    particular sports club
     * @param team2    the other particular sports club
     * @param location stadium holds the Event
     * @param year     to construct LocalDate Object
     * @param month    to construct LocalDate Object
     * @param day      to construct LocalDate Object
     */
    public Game (String forSport,String team1,String team2 ,String location , int year , int month , int day ) 
    {
        this.UniqueGeneratedCode=generateCode();
        this.forSport=forSport;
        this.team1=team1;
        this.team2=team2;
        this.Lcation=new Stadium(location);
        this.date = LocalDate.of(year , month , day);
    }
    private String generateCode() //private method (using Different access modifiers.)
    {
        String Characters = "ABCDEFGHIGKLMNOPQRSTUVWXYZ@#$%&*123456789";
        int length = 8;
        char [] Collector = new char [length];
        Random rand = new Random();
        String Code = "";
        for(int i =0 ; i<length ; i++)
            Collector[i]=Characters.charAt(rand.nextInt(Characters.length()));
        for(int i=0 ; i<Collector.length ; i++)
            Code+=Collector[i];
        HashSet<String> set = new HashSet<>() ; //Garantee the Uniquness of the Generated Code
        while (set.size()<1) //we dont want to store codes we generated befor 
            set.add(Code);
        String UnigueCode = null;
        for(String theCode : set)
             UnigueCode = theCode ;
        return  UnigueCode ;
    }
    
    /**
     *calls a block of statements that displaying Seats Availability
     */
    protected void ShowSeatsAvailabillity() //protected Method
    {
        this.Lcation.checkSeatAvailabillity();
    }

    /**
     *abstract method child can override to Display a Game Announcement
     */
    public abstract void DisplayGameAnnouncement(); //abstract method 

    /**
     *abstract method child can override to fill the state of a protected Type field
     */
    public abstract void setType(); //abstract method

    /**
     * @return a String which either "National" or "International"
     */
    public abstract String getType();
    
    //setters and getters to access private data members
    /**
     *
     * @param team1
     * @param team2
     */
    public final void setTeams(String team1 , String team2) //Final Method
    {
        this.team1=team1;
        this.team2=team2;
    }
    
    /**
     *
     * @param name
     */
    public void setLocation(String name)
    {
        this.Lcation.name=name;
    }

    /**
     *
     * @param year
     * @param month
     * @param day
     */
    public void setDate(int year , int month , int day)
    {
         this.date = LocalDate.of(year , month , day);
    }

    /**
     *
     * @return a Unique Game Code 
     */
    public final String getUniqueCode () //final Method
    {
        return this.UniqueGeneratedCode;
    }

    /**
     *
     * @return
     */
    public String getTeam1 ()
    {
        return this.team1;
    }

    /**
     *
     * @return
     */
    public String getTeam2()
    {
        return this.team2;
    }

    /**
     *
     * @return
     */
    public Stadium getLocation()
    {
        return this.Lcation;
    }

    /**
     *
     * @return
     */
    public String getLocationName()
    {
        return this.Lcation.name;
    }

    /**
     *
     * @return
     */
    public String getDate ()
    {
        return this.date.toString();
    }

    /**
     *
     * @return
     */
    public LocalDate getDateObj()
    {
        return this.date;
    }

    /**
     *
     * @param forSport
     */
    public void setSport(String forSport)
    {
        this.forSport=forSport;
    }

    /**
     *
     * @return
     */
    public String getSport()
    {
        return this.forSport;
    }
}

/**
 * A{@code InternationalGame} object represents an available upcoming International Game Event, such as Basketball Game between Egypt and Italy.
 * a game has uniqueCode and two different teams and location, International Games are Constructed from this Class.
 */
class InternationalGame extends Game
{
    
    /**
     *Constructs a Game to show so a User can book a ticket for.
     * @param forSport football,basketball,swimming,....
     * @param team1    particular sports club
     * @param team2    the other particular sports club
     * @param location stadium holds the Event
     * @param year     to construct LocalDate Object
     * @param month    to construct LocalDate Object
     * @param day      to construct LocalDate Object
     */
    public InternationalGame(String forSport,String team1, String team2, String location, int year, int month, int day) 
    {
        super(forSport,team1, team2, location, year, month, day);
        setType();
    }
    
     @Override
     /**
     * method child override to Display an International Game Announcement
     */
    public void DisplayGameAnnouncement()
    {
        System.out.println(this.type+" " +super.getSport()+" Game ");
        System.out.println(super.getTeam1()+"  vs  "+super.getTeam2());
        System.out.println("ON  "+super.getLocationName());
        System.out.println("at  "+super.getDate());
        ShowSeatsAvailabillity();   //access protected Method from parent directly
    }
    
    @Override
    /**
     * method child override to fill the state of a protected Type field
     */
    public void setType()
    {
        this.type="International";
    }

    @Override
    /**
     * @return a String which either "National" or "International"
     */
    public String getType() 
    {
        return this.type;
    }
    
}

/**
 * A{@code NationalGame} object represents an available upcoming NationalGame Game Event, such as Football Game between Al-Ahly and Zamalek.
 * a game has uniqueCode and two different teams and location, NationalGames are Constructed from this Class.
 */
class NationalGame extends Game //Inhertance Tree
{
    /**
     *Constructs a Game to show so a User can book a ticket for.
     * @param forSport football,basketball,swimming,....
     * @param team1    particular sports club
     * @param team2    the other particular sports club
     * @param location stadium holds the Event
     * @param year     to construct LocalDate Object
     * @param month    to construct LocalDate Object
     * @param day      to construct LocalDate Object
     */
    public NationalGame(String forSport,String team1, String team2, String location, int year, int month, int day) 
    {
        super(forSport , team1, team2, location, year, month, day);
        setType();
    }
    
    @Override
     /**
     * method child override to Display an International Game Announcement
     */
    public void DisplayGameAnnouncement()
    {
        System.out.println(this.type+" " +super.getSport()+" Game ");
        System.out.println(super.getTeam1()+"  vs  "+super.getTeam2());
        System.out.println("ON  "+super.getLocationName());
        System.out.println("at  "+super.getDate());
        ShowSeatsAvailabillity();   //access protected Method from parent directly
    }
    
    @Override
     /**
     * method child override to fill the state of a protected Type field
     */
    public void setType() 
    {
        this.type= "National";
    }   
    
    @Override
    /**
     * @return a String which either "National" or "International"
     */
    public String getType()
    {
        return this.type;
    }
}

/**
 * A{@code Seat} object represents a seat in a particular Stadium in where a specific upcoming  Game Event, such as Football Game.
 * a seat has id with three different categories is available Class"A",Class"B",and Class"C" ,and price.
 */
class Seat implements Display //Interface Implementation
{

    /**
     *the "Counter"helps to create ordered and unique IDs.
     */
    public static int counter=1; //static data field 
    private int numberID ;
    private String category;
    private double price ; //calculated data field
   
    public static ArrayList<Seat> reservedSeats = new ArrayList<>(); //once create a ticket for a particular game with a specific seat it will pushed here
    //the class ticket is responsible for fillin this array
    //the Class fan is responsiblle to traverse it to check if a specific seat is reserved or not
    
    /**
     *Construct a Seat with a given category
     * @param category "A","B",or"C", determine Seat Category
     */
    public Seat (String category)
    {
        this.numberID = counter;
        counter++;
        this.category=category;
        this.price=calculatePrice(); //calculated data field  
    }
    private double calculatePrice () //Private Method
    {
        if(this.category.equalsIgnoreCase("A"))
            this.price=120;
        else if (this.category.equalsIgnoreCase("B"))
            this.price=30;
        else 
            this.price=15;
        return this.price;
    }

    /**
     *Display Seat information
     */
    public void displaySeat()
    {
        //System.out.printf("SeatID:%d\tSeatCategory:%s\tSeatPrice:%fPounds",this.numberID,this.category,this.price);
        System.out.println("");
        System.out.println("SeatID:" + this.numberID);
        System.out.println("SeatCategory:" + this.category);
        System.out.printf("Seat Price: %.1f Pounds", this.price );
        System.out.println("");
    }

    /**
     *
     * @return
     */
    public int getID()
    {
        return this.numberID;
    }

    /**
     *
     * @param cat
     */
    public void setCategory(String cat)
    {
        this.category=cat;
    }

    /**
     *
     * @return
     */
    public String getCategory ()
    {
        return this.category;
    }

    /**
     *
     * @param price
     */
    public void setPrice (double price)
    {
        this.price=price;
    }

    /**
     *
     * @return
     */
    public double getPrice()
    {
        return this.price;
    }
    //interface method

    @Override
    /**
     * Interface implemented Method to Display ALL Details related by the Objects of the Class implements 
     */
    public void displayALLDeatails() 
    {
        System.out.printf("SeatID:%d\tSeatCategory:%s\tSeatPrice:%fPounds",this.numberID,this.category,this.price);
    }
    
}

/**
 * A{@code Ticket} object represents a Ticket that a visitor User can Reserve causes to book seat(s) in the Game Location,
 * a Ticket has id with three different categories is available with respect to the Seat fan wants Class"A",Class"B",and Class"C" 
 * ,and price including special price if there is offeredDiscoun.
 * Any Fan can Reserve more than one Ticket, and he/she can cancel,upgrade,Display,and make a bet.
 */
class Ticket implements Display //Interface Implementation
{
    public static double offeredDiscoun=.2;  // public static data member
    private boolean discount = false;
    private double newPrice;
    
    /**
     *the "Counter"helps to create ordered and unique IDs.
     */
    public static int counter=1;
    private final int ticketID;
    private Game game;
    private Seat seat;
    
    /**
     * Construct a new  Ticket for the given game reserving a given seat
     * @param game the game user selected to attend.
     * @param seat the seat user reserved.
     */
    public Ticket (Game game , Seat seat)
    {
        this.game=game;
        this.seat=seat;
        this.ticketID=counter;
        counter++;
        this.newPrice=calculatedPrice();
        
        Seat.reservedSeats.add(seat);   
    }
    
    //Constructor Overloading
    /**
     * Construct a new  Ticket for the given game reserving a given seat
     * @param game the game user selected to attend.
     * @param seat the seat user reserved.
     * @param ISdiscount flag to indicates that this game is offer discount for fans 
     */
    public Ticket (Game game , Seat seat,boolean ISdiscount)
    {
        this.ticketID=counter;
        counter++;
        this.game=game;
        this.seat=seat;
        this.discount=ISdiscount;
        this.newPrice=calculatedPrice();
        
        Seat.reservedSeats.add(seat);   
    }
    
    /**
     *Implement Static Method that use static data field offeredDiscoun to calculate the Discount.
     * @param oldPrice price before discount
     * @param newPrice price before discount
     * @return Discount amount if there is an Offer provided  
     */
    public static double DiscountAmount(double oldPrice , double newPrice ) //STATIC Method
    {
        newPrice= oldPrice*offeredDiscoun;
        return newPrice;
    }

    /** 
     * Display Ticket information on Console
     */
    public void displayTicket()
    {
        //Polymorphism while calling this.game.getType() method
        System.out.println("******************************************************************************************************************************************************************");
        System.out.printf("Ticketid#%d\tGame Code:%s\t%s %s Game\t%s vs %s\tOn%s\tDate:%s\nSeatID:%d\tSeatCategory:%s\tTotalPrice:%.1fPounds"
                ,this.ticketID,this.game.getUniqueCode(),this.game.getType(),this.game.getSport(),this.game.getTeam1(),this.game.getTeam2(),this.game.getLocationName(),this.game.getDate()
                ,this.seat.getID(),this.seat.getCategory(),this.newPrice); 
        System.out.println("");
        System.out.println("******************************************************************************************************************************************************************");

        
        
    }
    private double calculatedPrice()
    {
        if(this.discount==false)
            this.newPrice=this.seat.getPrice(); // the seats price by default
        return this.newPrice=this.seat.getPrice()*offeredDiscoun;
    }
    
    /**
     *
     */
    public void setDiscount()
    {
        this.discount=true;
    }

    /**
     *
     * @return
     */
    public boolean IsDiscount ()
    {
        return this.discount;
    }

    /**
     *
     * @return
     */
    public int getID()
    {
        return this.ticketID;
    }

    /**
     *
     * @return
     */
    public Game getGame()
    {
        return this.game;
    }

    /**
     *
     * @return
     */
    public Seat getSeat()
    {
        return this.seat;
    }
    
    
    @Override
    /**
     * Interface implemented Method to Display ALL Details related by the Objects of the Class implements 
     */
    public void displayALLDeatails() //implement abstract method of Display Interface
    {
        //Polymorphism while calling this.game.getType() method
        System.out.printf("Ticketid#%d\tGame Code:%s\t%s %s Game\t%s vs %s\tOn%s\tDate:%s\nSeatID:%d\tSeatCategory:%s\tTotalPrice:%fPounds"
                ,this.ticketID,this.game.getUniqueCode(),this.game.getType(),this.game.getSport(),this.game.getTeam1(),this.game.getTeam2(),this.game.getLocationName(),this.game.getDate()
                ,this.seat.getID(),this.seat.getCategory(),this.newPrice);
        
    }
    
    
}

/**
 * A{@code Fan} object represents an individual fan will look at available upcoming Game Events, such as Basketball Game between Al-Ahly and Zamalek.
 * a fan has name and tickets he selected, he/she Make a bet for the game result.
 */
class Fan 
{
    private String name;
    private ArrayList<Ticket>fanTickets;
    private String bet;
    
    /**
     *Construct a new fan Object has many Functionalities
     * @param name Fans Name
     */
    public Fan (String name)
    {
        this.name=name;
        this.fanTickets=new ArrayList<>();
        this.bet="";
    }
    
    /**
     *check If a given seat is Available or not.
     * @param aseat Seat Object
     * @return Available? true/false
     */
    public boolean checkIfAvailable(Seat aseat)
    {
        for(Seat s : Seat.reservedSeats)
        {
            if (s.equals(aseat))
                return false;
        }
        return true;
    }

    /**
     *make Fan Reserve a Ticket for specific Game, selecting a specific seat.
     * @param game Game Object
     * @param seat Seat Object
     * @throws USER Defined Eception "BoomException" if the entered seat is already took by some other fan.
     */
    public void ReserveTicket (Game game , Seat seat)
    {
        try //USER Defined Eception
        {
            if (!checkIfAvailable(seat))
                throw new BoomException("WRONG, This Seat is no mare Available");
        } 
        catch (BoomException ex)
        {
            System.out.println("");
            System.out.println("");
            System.out.println("ERROR:"+ex.getMessage());
        }
        
        if (checkIfAvailable(seat))
        {
            Ticket ticket = new Ticket(game,seat);
            this.fanTickets.add(ticket);
        }
        System.out.println("");
        System.out.println("");
        System.out.println("DONE -> yor Ticket is Reserved Succefully.");
    }

    /**
     * make Fan cancel a Ticket with specific Id he booked before
     * @param tickedID id for the ticket want to be canceled
     * @see <a href="https://stackoverflow.com/questions/20165564/calculating-days-between-two-dates-with-java"> page reference of Calculating days between two dates with Java.
     * @throws USER Defined Exceptin"BoomException" if the ticked id is not found
     * @throws JAVA Defined Exceptin"Arithmetic Exception"if the cancel operation done in time in which the game will be in less than 3 days.
     */
    public void cancelReservation(int tickedID)
    {
        boolean found=false ;
        int indexOfRemoval=this.fanTickets.size()-1; //indicate the last reserved ticket stack logic
        for(int i=0 ; i<this.fanTickets.size();i++)
        {
            if(this.fanTickets.get(i).getID()==tickedID)
            {
                found=true;
                indexOfRemoval=i;
                break;
            }   
        }
        boolean raiseException=false;
        //USER Defined Exceptin
        try
        {
            if(!found)
            {
                raiseException=true;
                throw new BoomException("you do not have a Ticket with such ID! cancel operation is failed. ");
                
            }   
        }
        catch(BoomException ex)
        {
            System.out.println("ERROR:"+ex.getMessage());
        }
        
        //calculate the days between the Date in which user want to cancel and the Date of the Event
        final LocalDate currentDate = LocalDate.now();
        final LocalDate gameEventDate= this.fanTickets.get(indexOfRemoval).getGame().getDateObj(); // the Date of the Game assigned to the Ticket
        Long diffDays = ChronoUnit.DAYS.between(gameEventDate,currentDate);
        int daysbetween = (int)Math.abs(diffDays);
        
       
        //JAVA Defined Exception
        try
        {
            if (found && daysbetween<3)
            {
                raiseException=true;
                throw new ArithmeticException("Cancel is Failed, Cancel accepted only before the game date by at least 3 days");
            }
        }
        catch(ArithmeticException ex)
        {
            System.out.println("ERROR:"+ex.getMessage());
        }
        if(!raiseException)
        {
            this.fanTickets.remove(indexOfRemoval);
            System.out.println("Your Cancel Request has done succefully");
        }
    }
    
    /**
     *let fan Upgrade his old last Ticket he reserved.
     * @param newSeat seat which fan need to replace with old one
     */
    public void UpgradeTicket (Seat newSeat) //upgrade the last one 
    {
      int lastTicketindex=this.fanTickets.size()-1;
      
      //bring all ticket information
      Game game = this.fanTickets.get(lastTicketindex).getGame();
      Seat oldSeat = this.fanTickets.get(lastTicketindex).getSeat();
      
      //check the old category must be less than the nwe one to enable Ubgrade
      String oldSeatCategory = oldSeat.getCategory();
      int allowUpgrade = oldSeatCategory.compareToIgnoreCase(newSeat.getCategory()); //we need this indicator since that UPGRADE must from a lower category to a higher
      
      if(allowUpgrade== 1)
      {
          this.fanTickets.remove(lastTicketindex);
          this.ReserveTicket(game, newSeat);
          System.out.println("and your last Ticket is Upgraded");
      }
      else if(allowUpgrade==0)
            System.out.println("Caution: Upgrade Failed, the Seat Category of Your Ticket Already the same of the new one,no need to Upgrade!");
      else
      {
          System.out.println("");
          System.out.println("");
          System.out.println("SORRY, it is not allowable to Degrade your Reserved Seat.");
          System.out.println("");
      }
            
    }
    
    //Overloading methods

    /**
     *let fan Upgrade any of his old Tickets he reserved.
     * @param oldTicket the old Ticket of the fan. 
     * @param newSeat seat which fan need to replace with old one.
     */
    public void UpgradeTicket (Ticket oldTicket , Seat newSeat) //upgrade a particular ticket belongs to this fan
    {
        int oldTicketID=oldTicket.getID();
        
        int allowUpgrade = oldTicket.getSeat().getCategory().compareToIgnoreCase(newSeat.getCategory());
        if(allowUpgrade==-1)
        {
          cancelReservation(oldTicketID);
          System.out.print("and");
          ReserveTicket(oldTicket.getGame(),newSeat);
          System.out.println("and your Old Ticket is Upgraded");
        }
        else if(allowUpgrade==0)
            System.out.println("Caution: Upgrade Failed, the Seat Category of Your Ticket Already the same of the new one,no need to Upgrade!");
        else
            System.out.println("it is Unallowable to Degrade your Reserved Seat");
    }

    /**
     *let Fan Make a bet for the game result.
     * @param bet variable to Store the fans bet.
     */
    public void make_bet (String bet)
    {
        this.bet=bet;
        
        System.out.println("DONE -> your bet is saved, follow us you may be one of the few winners");
    }

    /**
     * Looping to Display All Fan Tickets information.
     */
    public void displayAllFanTickets()
    {
        if(this.fanTickets.size()==0)
            System.out.println("you have no Tickets to show");
        else
        {
            for(Ticket t : this.fanTickets)
            {
                System.out.println("");
                t.displayTicket();
            }
        }
            
    } 
}

/**
 * User Defined Exception extends  Exception Class
 */
class BoomException extends Exception
{

    /**
     * Constructs new BoomException Object.
     * @param message Message tells the User something to handle Exceptions 
     */
    public BoomException (String message)
    {
        super(message);
    }
}


/**
 *Interface contains a method Display all Details related to the instance of the Class implements it. 
 */
interface Display //Interface
{

    /**
     * Display all Details related to the instance of the Class implements it. 
     */
    public void displayALLDeatails();
}



