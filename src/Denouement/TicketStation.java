package Denouement;


public class TicketStation {
	Cinema c;
	public TicketStation(Cinema c){
		this.c = c;
	}
	public synchronized void decrementTickts(long customerNo, int ticketStationNo){
		System.out.println("Customer "+customerNo+" came to ticket Station "+ticketStationNo);
		c.takeTickets(ticketStationNo);
	}
	
}
