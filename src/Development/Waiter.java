package Development;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * This class represents a waiter, to whom customers requests popcorn.
 * @author Debaditya Ravish
 *
 */
public class Waiter{
	private Cinema c;
	private Semaphore popcorn_mutex = new Semaphore(1, true);	//Mutex semaphore

	/**
	 * This is the constructor for the waiter object
	 * @param c This parameter represents a cinema 
	 */
	public Waiter(Cinema c){
		this.c = c;
	}
	/**
	 * The waiter accesses the popcorn machine to make a popcorn while the customer 
	 * waits for the popcorn.
	 */
	public void getPopcorn(){
		//cust.add(a);
		try {
			popcorn_mutex.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		c.takePopcorn();
		popcorn_mutex.release();
	}
}
