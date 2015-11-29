package Development;
/**
 * This is the Customer class which is a thread. It has four mandatory action
 * one optional action: buy ticket from counter, enter hall , exit hall and notify
 * other customers who are waiting at the ticket station to buy tickets.
 * The optional action for some customers is to buy popcorn.
 * @author Debaditya Ravish
 * 
 *
 */
public class Customer extends Thread{
	private ProjectionHall p;
	private TicketStation t;
	private Cinema c;
	private Waiter w;

/**
 * This is the constructor for the customer class	
 * @param p2	Projection hall in which the customer wants to enter
 * @param t		Ticket Station from which the customer wants to get the ticket
 * @param c		The cinema to which he came
 * @param w		The waiter from whom he wants popcorn
 */
	public Customer(ProjectionHall p2, TicketStation t,Cinema c,Waiter w){
		this.p = p2;
		this.t = t;
		this.c = c;
		this.w = w;
	}
	/**
	 * This is the run method of the customer thread.
	 */
	public void run(){
			System.out.println("Customer "+this.getId()+" arrived in front of cinema");
			boolean b = t.decrementTickts();
			if(b){
				if(this.getId()>10 && this.getId()<20){
					w.getPopcorn();
				}
				p.enterHall();
				p.exitHall();
				c.notifier();
			}
			else{
				System.out.println("Customer "+this.getId()+" have left as the cinema is closed for the day");
			}
		
	}

}
