package Denouement;

import java.util.concurrent.Semaphore;

public class Cinema {
	private int nbTickets;
	private boolean popcornPrepared;
//	private Semaphore ticket_mutex = new Semaphore(3, true);
	private volatile int endCount;
	public Cinema(int nbTickets,boolean popcornPrepared,int endCount){
		this.nbTickets = nbTickets;
		this.popcornPrepared = popcornPrepared;
		this.endCount = endCount;
	}
	
	public synchronized boolean takeTickets(int ticketStatioNo){
		while(nbTickets==0){
			try {
				//System.out.println("Customer "+Thread.currentThread().getId()+" is waiting for tickets "+"at ticket station "+ticketStatioNo);
				//ticket_mutex.release();
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(endCount!=2){
			System.out.println("Customer "+Thread.currentThread().getId()+" is getting served "+"at ticket station "+ticketStatioNo);
			nbTickets--;
			System.out.println("Customer "+Thread.currentThread().getId()+" got tickets "+"at ticket station "+ticketStatioNo);
			return true;
		}
		else{
			return false;
		}
	}
	
	public synchronized void notifier(){
		nbTickets++;
		notifyAll();
	}
	
	public synchronized void takePopcorn(){
	//	try {
	//		ticket_mutex.acquire();
	//	} catch (InterruptedException e1) {
	//		e1.printStackTrace();
	//	}
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
	//	ticket_mutex.release();
	}
	
	public void makePopcorn(){
		popcornPrepared=true;
		synchronized (this) {			
			notifyAll();					//Notifies all the customers who are waiting for popcorn.
		}
	}
	
	public void cinemaCloseNote(){
		endCount++;
	}
	

}
