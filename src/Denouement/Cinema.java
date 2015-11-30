package Denouement;

import java.util.concurrent.Semaphore;

public class Cinema {
	private int nbTickets;
	private boolean popcornPrepared;
	private Semaphore ticket_mutex = new Semaphore(3, true);
	
	public Cinema(int nbTickets,boolean popcornPrepared){
		this.nbTickets = nbTickets;
		this.popcornPrepared = popcornPrepared;
	}
	
	public synchronized void takeTickets(int ticketStatioNo){
		while(nbTickets==0){
			try {
				System.out.println("Customer "+Thread.currentThread().getId()+" is waiting for tickets "+"at ticket station "+ticketStatioNo);
				ticket_mutex.release();
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Customer "+Thread.currentThread().getId()+" is getting served "+"at ticket station "+ticketStatioNo);
			nbTickets--;
		System.out.println("Customer "+Thread.currentThread().getId()+" got tickets "+"at ticket station "+ticketStatioNo);
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
	
	public synchronized void makePopcorn(){
		popcornPrepared=true;
		notify();
	}
	

}
