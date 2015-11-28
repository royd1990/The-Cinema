package Presentation;

public class TicketStation {
	Cinema c;
	public TicketStation(Cinema c){
		this.c = c;
	}
	public synchronized boolean decrementTickts(){
		// TODO Auto-generated method stub
		boolean x = c.takeTickets();
		return x;
	}
	
}
