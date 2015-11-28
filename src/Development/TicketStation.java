package Development;

public class TicketStation {
	Cinema c;
	public TicketStation(Cinema c){
		this.c = c;
	}
	public synchronized void decrementTickts(){
		// TODO Auto-generated method stub
		c.takeTickets();
		
	}
	
}
