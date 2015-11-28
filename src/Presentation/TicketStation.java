package Presentation;
/**
 * This is a  class which represents a ticket desk where 
 * a customer will approach to buy tickets. It relays the customer's 
 * request to the main cinema monitor an books a ticket from the ticket pool
 * and returns a boolean variable to indicate whether the buy was successful or not.
 * It also receives updates from the deepthought thread whether the projection has ended for the day or not.
 * If yes then it does not provides any more tickets.
 * @author royd1990
 *
 */
public class TicketStation {
	private Cinema c;
	private volatile boolean movieEnded;
	/**
	 * This is a constructor for the class which is initialized by the cinema object.
	 * @param c	cinema object attached to the ticket station.
	 */
	public TicketStation(Cinema c){
		this.c = c;
		movieEnded = false;
	}
	/**
	 * This places a call to the Cinema monitor and it returns a boolean variable indicating whether a ticket
	 * can be booked or not.
	 * @return boolean variable indicating whether a ticket can be booked or not.
	 */
	public boolean decrementTickts(){
		if(movieEnded==false)
		{
			boolean x = c.takeTickets();
			return x;
		}
		return false;
	}
	
	public void endMovie(){
		movieEnded = true;
	}
	
	
}
