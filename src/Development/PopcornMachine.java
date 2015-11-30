package Development;


/**
 * This is the popcorn machine thread which is responsible for
 * making popcorns for the clients.
 * @author Debaditya Ravish
 *
 */
public class PopcornMachine extends Thread{
	private Cinema c;
	/**
	 * This is the constructor class for the PopcornMachine class.
	 * @param c This parameter represents the cinema object.
	 */
	public PopcornMachine(Cinema c){
		this.c = c;
	}
	/**
	 * This is the run method for the popcornMachine thread class
	 */
	public void run(){
		while(true){
			try {
				sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
				c.makePopcorn();
			
		}
	}

}
