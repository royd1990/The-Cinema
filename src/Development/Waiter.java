package Development;

public class Waiter{
	private Cinema c;
	private PopcornMachine p;
	public Waiter(Cinema c,PopcornMachine p){
		this.c = c;
		this.p = p;
	}
	public void getPopcorn(){
		p.makePopcorn();
		c.takePopcorn();
	}
}
