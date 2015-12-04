package Test;

import java.util.concurrent.Semaphore;

public class Waiter{
	private Cinema c;
	private Semaphore popcorn_mutex = new Semaphore(1, true);	//Mutex semaphore
	public Waiter(Cinema c){
		this.c = c;
	}
	public void getPopcorn(int waiterNo,long custNo){
		try {
			popcorn_mutex.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Customer "+custNo+" has arrived to waiter "+waiterNo);
		c.takePopcorn();
		popcorn_mutex.release();
	}
}
