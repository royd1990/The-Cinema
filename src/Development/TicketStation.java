package Development;

public class TicketStation {
	private Cinema c;
	private volatile boolean cinemaClosed;
	public TicketStation(Cinema c,boolean cinemaClosed){
		this.c = c;
		this.cinemaClosed = cinemaClosed;
	}
	public synchronized void decrementTickts(){
		if(cinemaClosed==false){
			c.takeTickets();
		}
	}
	
	public void closeCinema(){
		cinemaClosed = true;
		System.out.println("CINEMA HALL IS CLOSED FOR TODAY");
	}
	
}
