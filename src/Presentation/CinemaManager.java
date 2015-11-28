package Presentation;

import java.util.Random;

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
		Cinema c = new Cinema(90);								
		ProjectionHall[] p = new ProjectionHall[1];	
		TicketStation[] t=new TicketStation[1];	
		Session[] s=new Session[1];
		Customer[] cust=new Customer[90];
		
		p[0] = new ProjectionHall(90, false, false, false);	
		t[0]=new TicketStation(c);		
		s[0]=new Session(p[0],t[0]);
		s[0].start();
		
		for(int i=0;i<90;i++){
			cust[i] = new Customer(p,t);
/**
 * This block of code about random number generation is written to simulate and test one feature i.e. what happens when
 * customer comes to the hall after the cinema has closed. It can be commented to test another feature i.e. there is no 
 * order imposed in ticket disbursement in this version.
 */
			Random rn = new Random();
			int j = Math.abs((rn.nextInt()%500));
			try {
				Thread.sleep(j);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	//End of random sleep time code block.
			
			cust[i].start();

		}

	}

}
