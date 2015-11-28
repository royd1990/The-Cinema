package Development;

public class Waiter{
	private Cinema c;
	public Waiter(Cinema c){
		this.c = c;
	}
	public void getPopcorn(){
		c.takePopcorn();
	}
}
