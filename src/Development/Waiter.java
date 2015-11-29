package Development;

/**
 * This class represents a waiter, to whom customers requests popcorn.
 * @author Debaditya Ravish
 *
 */
public class Waiter{
	private Cinema c;


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
		c.takePopcorn();
	}
}
