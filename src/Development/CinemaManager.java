package Development;
/**
 * This class contains the main method
 * which initializes all the objects for 
 * starting the program.
 * @author Debaditya Ravish
 *
 */
public class CinemaManager {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Cinema c = new Cinema(250,false);
		ProjectionHall p ;
		p = new ProjectionHall(250, false, false, false);
		TicketStation t;
		t=new TicketStation(c,false);
		Customer[] cust=new Customer[600];
		Session s;
		s=new Session(p,t,c);
		s.start();
		PopcornMachine pop = new PopcornMachine(c);
		Waiter w = new Waiter(c);
		pop.setDaemon(true);
		pop.start();
		
		for(int i=0;i<600;i++){
			cust[i] = new Customer(p,t,c,w);
			cust[i].start();
		}

	}

}
