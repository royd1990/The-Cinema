package Presentation;

/**
 * This is the cinema class which acts as
 * one of the monitor providing a shared access
 * of the variable nbTickets to the ticket counter
 * @author Debaditya Ravish
 * 
 */

public class Cinema {
	private int nbTickets;
/**
 * Constructor used to created the object of 
 * this class	
 * @param nbTickets Parameter for representing number of tickets
 */
	public Cinema(int nbTickets){
		this.nbTickets = nbTickets;
	}
	
/**
 * This synchronized method provides access
 * to the variable nbTickets to all of the customer
 * threads and returns a boolean variable to indicate whether
 * the customer has got a ticket or not.
 * @return	returns a boolean variable
 */
	
	public synchronized boolean takeTickets(){
		if(nbTickets!=0){
			System.out.println("Customer "+Thread.currentThread().getId()+" enters the ticket station");
			nbTickets--;
			return true;
		}
		else
		{
			return false;
		}
	}
	

}
