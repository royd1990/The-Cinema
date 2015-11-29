package Development;

import java.util.concurrent.Semaphore;

public class PopcornMachine extends Thread{
	private Cinema c;
	private boolean makePopcorn;
	private Semaphore popcorn_mutex  = new Semaphore(1, true);
	public PopcornMachine(Cinema c){
		this.c = c;
		this.makePopcorn = false;
	}
	public void run(){
		while(true){
			try {
				sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(makePopcorn){
				c.makePopcorn();
				makePopcorn = false;
				popcorn_mutex.release();
			}
			
			
		}
	}
	
	public void makePopcorn(){		
		try {
			popcorn_mutex.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		makePopcorn = true;
	}

}
