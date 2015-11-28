package Denouement;

public class Waiter{
	private Cinema c;
	public Waiter(Cinema c){
		this.c = c;
	}
	public void getPopcorn(int waiterNo,long custNo){
		System.out.println("Customer "+custNo+" has arrived to waiter "+waiterNo);
		c.takePopcorn();
	}
}
