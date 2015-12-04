package Test;

public class PopcornMachine extends Thread{
	private Cinema c;
	public PopcornMachine(Cinema c){
		this.c = c;
	}
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
