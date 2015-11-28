package Development;

public class Cinema {
	private int nbTickets;
	private boolean popcornPrepared;
	//private boolean buyPopcorn;
//	private Semaphore popcorn_mutex = new Semaphore(1, true);
	
	public Cinema(int nbTickets,boolean popcornPrepared){
		this.nbTickets = nbTickets;
		this.popcornPrepared = popcornPrepared;
		//this.buyPopcorn = buyPopcorn;
	}
	public synchronized void takeTickets(){
		while(nbTickets==0){
			try {
				System.out.println("Customer is waiting for tickets");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		nbTickets--;
	}
	
	public synchronized void notifier(){
		nbTickets++;
		notifyAll();
	}
	
	public synchronized void takePopcorn(){
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
	}
	
	public synchronized void makePopcorn(){
		popcornPrepared=true;
		notify();
	}
	

	
	
	
	
}
