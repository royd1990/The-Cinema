package Presentation;
/**
 * This class contains the main method
 * which initializes all the objects for 
 * starting the program.
 * @author Debaditya Ravish
 * 
 *
 */
public class CinemaManager {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

/**
 * For this version although we have one ticket desk, one projection hall and one session, but for scaling up
 * of the future versions we have declared those variables as an array and used single instances of them in this 
 * version.
 */
		Cinema c = new Cinema(10);								
		ProjectionHall[] p = new ProjectionHall[1];	
		TicketStation[] t=new TicketStation[1];	
		Session[] s=new Session[1];
		Customer[] cust=new Customer[20];
		
		p[0] = new ProjectionHall(10, false, false, false);	
		t[0]=new TicketStation(c);		
		s[0]=new Session(p[0]);
		s[0].start();
		
		for(int i=0;i<20;i++){
			cust[i] = new Customer(p,t);
			cust[i].start();
		}

	}

}
