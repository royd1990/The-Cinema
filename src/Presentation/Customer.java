package Presentation;

/**
 * This represents the customer class which
 * is a thread for our program.
 * @author Debaditya Ravish
 *
 */

public class Customer extends Thread{
	private ProjectionHall[] p;
	private TicketStation[] t;

	/**
	 * 
	 * @param p2 This represents the array of projection halls where customer can go. For this version it is 1
	 * @param t	 This represents the array of ticket stations where customer can take tickets. For this version it is 1.
	 */
	public Customer(ProjectionHall[] p2, TicketStation[] t){
		this.p = p2;
		this.t = t;
	}
	
	/**
	 * The run method of the thread.
	 * Customer has 3 actions: get ticket from ticket counter, enter the projection hall, exit the projection hall.
	 * These three functions are represented here.
	 */
	
	public void run(){
		boolean x = t[0].decrementTickts();	//Get tickets from ticket counter
/**
 * If customer gets tickets he enters the hall else he goes back.
 */
		if(x){
			p[0].enterHall();				//Enter projection hall
			p[0].exitHall();				//Exit the projection hall
		}
		else{
			System.out.println("Customer "+this.getId()+" has left as there were no more tickets");
		}
	}

}
