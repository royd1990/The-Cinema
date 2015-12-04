package Test;

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
			int i = Math.abs((rn.nextInt()%250));
			if(i<125){
				boolean b = t[j].decrementTickts(this.getId(),j);
				if(b){
					if(i>30 && i<40){
						try {
							popcorn_mutex.acquire();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
							w[j].getPopcorn(j,this.getId());
							popcorn_mutex.release();
					}				
					p[0].enterHall();
					p[0].exitHall();
					c.notifier();}
				else{
				   System.out.println("Customer "+this.getId()+" have left as the cinema is closed for the day or there are no tickets for movie in projection hall 0");
				}
			}			
			else{
					boolean b1 = t[j].decrementTickts1(this.getId(),j);
					if(b1){
						if(i>50 && i<60){
							try {
								popcorn_mutex.acquire();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
								w[j].getPopcorn(j,this.getId());
								popcorn_mutex.release();
						}				
						p[1].enterHall();
						p[1].exitHall();
						c.notifier2();
					}
					else{
						System.out.println("Customer "+this.getId()+" have left as the cinema is closed for the day or there are no tickets for movie in projection hall 1");
					}
			}
			
			
			
	}

}
