package Development;

import java.util.concurrent.Semaphore;



public class Customer extends Thread{
	private ProjectionHall[] p;
	private TicketStation[] t;
	private Cinema c;
	private Waiter w;
	private Semaphore popcorn_mutex = new Semaphore(1, true);
	
	public Customer(ProjectionHall[] p2, TicketStation[] t,Cinema c,Waiter w){
		this.p = p2;
		this.t = t;
		this.c = c;
		this.w = w;
	}
	
	public void run(){
			t[0].decrementTickts();
			if(this.getId()>10 && this.getId()<20){
			 try {
				popcorn_mutex.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
				w.getPopcorn();
			 popcorn_mutex.release();
			}
			p[0].enterHall();
			p[0].exitHall();
			c.notifier();
		
	}

}
