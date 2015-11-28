package Denouement;

import java.util.Random;
import java.util.concurrent.Semaphore;


public class Customer extends Thread{
	private ProjectionHall[] p;
	private TicketStation[] t;
	private Cinema c;
	private Semaphore popcorn_mutex = new Semaphore(1, true);
	private Waiter[] w;
	public Customer(ProjectionHall[] p2, TicketStation[] t,Cinema c, Waiter[] w){
		this.p = p2;
		this.t = t;
		this.c = c;
		this.w = w;
	}
	
	public void run(){
			
			Random rn = new Random();
			int j = Math.abs((rn.nextInt()%3));									
			t[j].decrementTickts(this.getId(),j);			
			if(this.getId()>10 && this.getId()<20){
				 try {
					popcorn_mutex.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
					w[j].getPopcorn(j,this.getId());
				 popcorn_mutex.release();
				}

			if((this.getId()>10 && this.getId()<19)||(this.getId()>30 && this.getId()<39)){
				p[0].enterHall();
				p[0].exitHall();
			}
			else{
				p[1].enterHall();
				p[1].exitHall();
			}
			c.notifier();
		
	}

}
