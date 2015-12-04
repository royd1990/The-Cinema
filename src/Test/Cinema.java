package Test;


public class Cinema {
	private int nbTickets;
	private int nbTickets2;
	private boolean popcornPrepared;
//	private Semaphore ticket_mutex = new Semaphore(3, true);
	private volatile int endCount;
	private int nbSessions;
	public Cinema(int nbTickets,int nbTickets2,boolean popcornPrepared,int endCount,int nbSessions){
		this.nbTickets = nbTickets;
		this.popcornPrepared = popcornPrepared;
		this.endCount = endCount;
		this.nbTickets2 = nbTickets2;
		this.nbSessions = nbSessions;
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
		if(endCount!=nbSessions){
			System.out.println("Customer "+Thread.currentThread().getId()+" is getting served "+"at ticket station "+ticketStatioNo+" for movie in projection hall 0");
			nbTickets--;
			System.out.println("Customer "+Thread.currentThread().getId()+" got tickets "+"at ticket station "+ticketStatioNo+" for movie in projection hall 0");
			return true;
		}
		else{
			return false;
		}
	}
	
	public synchronized boolean takeTickets2(int ticketStatioNo){
		while(nbTickets2==0){
			try {
				//System.out.println("Customer "+Thread.currentThread().getId()+" is waiting for tickets "+"at ticket station "+ticketStatioNo+" for movie in projection hall 1");
				//ticket_mutex.release();
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(endCount!=nbSessions){
			System.out.println("Customer "+Thread.currentThread().getId()+" is getting served "+"at ticket station "+ticketStatioNo+" for movie in projection hall 1");
			nbTickets2--;
			System.out.println("Customer "+Thread.currentThread().getId()+" got tickets "+"at ticket station "+ticketStatioNo+" for movie in projection hall 1");
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
	
	public synchronized void notifier2(){
		nbTickets2++;
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
