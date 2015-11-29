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
		/**
		 * For this version although we have one ticket desk, one projection hall and mutiple sessions, but for scaling up
		 * of the future versions we have declared those variables as an array and used single instances of them in this 
		 * version.
		 */
		Cinema c = new Cinema(5,false);
		ProjectionHall[] p = new ProjectionHall[1];
		p[0] = new ProjectionHall(5, false, false, false);
		TicketStation[] t=new TicketStation[1];
		t[0]=new TicketStation(c,false);
		Customer[] cust=new Customer[15];
		Session[] s=new Session[1];
		s[0]=new Session(p[0],t[0]);
		s[0].start();
		PopcornMachine pop = new PopcornMachine(c);
		Waiter w = new Waiter(c,pop);
		pop.setDaemon(true);
		pop.start();
		
		for(int i=0;i<15;i++){
			cust[i] = new Customer(p,t,c,w);
			cust[i].start();
		}

	}

}
