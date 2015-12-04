package Denouement;


public class TicketStation {
	private Cinema c;
	public TicketStation(Cinema c){
		this.c = c;
	}
	public synchronized boolean decrementTickts(long customerNo, int ticketStationNo){
		System.out.println("Customer "+customerNo+" came to ticket Station "+ticketStationNo);
		if(c.takeTickets(ticketStationNo)){
			return true;
		}
		else{
			return false;
		}
	}
	
}
