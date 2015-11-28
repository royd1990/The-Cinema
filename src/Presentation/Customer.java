package Presentation;


public class Customer extends Thread{
	private ProjectionHall[] p;
	private TicketStation[] t;
	
	public Customer(ProjectionHall[] p2, TicketStation[] t){
		this.p = p2;
		this.t = t;
	}
	
	public void run(){
		boolean x = t[0].decrementTickts();
		if(x){
			p[0].enterHall();
			p[0].exitHall();
		}
		else{
			System.out.println("Customer "+this.getId()+" has left as there were no more tickets");
		}
	}

}
