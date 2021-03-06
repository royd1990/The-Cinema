package Development;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
/**
 This is the cinema class which acts as
 * one of the monitor providing a shared access
 * of the variable nbTickets to the ticket counter.Since in this version 
 * we also have a popcorn machine, hence we also have synchronized methods
 * providing access to shared variables releated to that. 
 * @author Debaditya Ravish
 *
 */

public class Cinema {
	private int nbTickets;
	private volatile boolean popcornPrepared;
	private Semaphore popcorn_mutex = new Semaphore(1, true);	//Mutex semaphore
	private volatile boolean cinemaClosed;
	private ArrayList<Customer> cust = new ArrayList<Customer>();
	//private Customer[] cust;
	/**
	 * This is a constructor for the cinema class
	 * @param nbTickets represents the total number of tickets
	 * @param popcornPrepared	represents whether popcorn has been prepared by the machine or not
	 */
	public Cinema(int nbTickets,boolean popcornPrepared){
		this.nbTickets = nbTickets;
		this.popcornPrepared = popcornPrepared;
		this.cinemaClosed = false;
	}
	/**
	 * This method provides shared access of the tickets to the 
	 * customers via the ticket station.
	 */
	public synchronized boolean takeTickets(){
		System.out.println("*********Customer "+Thread.currentThread().getId()+" arrived in front of cinema********");
		while(nbTickets==0){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(cinemaClosed==false){
			nbTickets--;
			System.out.println("Customer "+Thread.currentThread().getId()+" got ticket");
			return true;
		}
		else{
			return false;
		}
		
		

	}
	/**
	 * This method is used to notify the customers who are waiting at
	 * the ticket station for the next show.
	 */
	public synchronized void notifier(){
		nbTickets++;
		notifyAll();	//We have to notify all the customers who are waiting at the ticket station for the next
						//session.
	}
	/**
	 * This method is accessed by each customer via a waiter class.
	 */
	public synchronized void takePopcorn(){
	//	try {
		//	popcorn_mutex.acquire();		//To impose order on the fact that customer who first reaches
											//for popcorn takes it before another one we have used a mutex lock.Only one customer
											//thread is allowed to to go to sleep at a time so that he is the only one woken up and
											//strict ordering is imposed.
	//	} catch (InterruptedException e1) {
	//		e1.printStackTrace();
	//	}
		//System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa           "+a.getClass());
		
		System.out.println("Customer "+Thread.currentThread().getId()+" came for popcorn");
		
		while(popcornPrepared==false){
			try {
				System.out.println("Customer "+Thread.currentThread().getId()+" is waiting for popcorn");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Customer "+Thread.currentThread().getId()+" bought popcorn");					
		popcornPrepared = false;
	//	popcorn_mutex.release();	//Once one customer has got popcorn he releases the mutex for the next customer.
	}
	
	/**
	 * Thread popcornMachine accesses this method to tell the customers that popcorn is ready
	 */
	public void makePopcorn(){
		popcornPrepared=true;
		synchronized (this) {
			
			notifyAll();					//Notifies all the customers who are waiting for popcorn.
		}
		

	}
	

	public void closeCinema(){
		cinemaClosed = true;
	}
	
	
	
}
