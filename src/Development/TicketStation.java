package Development;
/**
 * This class represents a ticket station where customer comes to buy ticket
 * it takes request from customer and then go to the cinema class and access the
 * variable representing number of tickets. This class is also accessed by the deepthought
 * thread to indicate end of all the sessions and hence to close the cinema for the day.
 * @author Debaditya Ravish
 *
 */
public class TicketStation {
	private Cinema c;
	private volatile boolean cinemaClosed;
	/**
	 * This is the constructor for a ticket station object.
	 * @param c	Represents a cinema object
	 * @param cinemaClosed Reprsents a boolean variable to understand whether all sessions has ended or not.
	 */
	public TicketStation(Cinema c,boolean cinemaClosed){
		this.c = c;
		this.cinemaClosed = cinemaClosed;
	}
	/**
	 * Customers access this method to buy the tickets.
	 */
	public  boolean decrementTickts(){
		if(cinemaClosed==false){	//Check if cinema hall is closed for the day. If not buy tickets else go home.
			if(c.takeTickets()){;
				return true;
			}
			else{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * This method is accessed by deepThought thread to access the cinemaClosed variable to indicate that
	 * all the sessions has ended for the day.
	 */
	public void closeCinema(){
		cinemaClosed = true;
		System.out.println("CINEMA HALL IS CLOSED FOR TODAY");
	}
	
}
