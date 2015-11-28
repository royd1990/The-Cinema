package Presentation;
/**
 * This is a simple class which represents a ticket desk where 
 * a customer will approach to buy tickets. It relays the customer's 
 * request to the main cinema monitor an books a ticket from the ticket pool
 * and returns a boolean variable to indicate whether the buy was successful or not.
 * @author royd1990
 *
 */
public class TicketStation {
	Cinema c;
	/**
	 * This is a constructor for the class which is initialized by the cinema object.
	 * @param c	cinema object attached to the ticket station.
	 */
	public TicketStation(Cinema c){
		this.c = c;
	}
	/**
	 * This places a call to the cinema monitor and it returns a boolean variable indicating whether a ticket
	 * can be booked or not.
	 * @return boolean variable indicating whether a ticket can be booked or not.
	 */
	public synchronized boolean decrementTickts(){
		// TODO Auto-generated method stub
		boolean x = c.takeTickets();
		return x;
	}
	
}
