package Test;


public class CinemaManager {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int nbTickets = 50;
		int nbTickets2 = 50;
		int nbSeats = 50;
		int projHall = 2;
		int ticketStation = 3;
		int nbCustomers = 210;
		int nbSessions = 2;
		int nbWaiters = 3;
		Cinema c = new Cinema(nbTickets,nbTickets2,false,0,nbSessions);
//	if(nbTickets<=nbSeats){
		ProjectionHall[] p = new ProjectionHall[projHall];
		
		for(int i=0;i<projHall;i++){
			p[i] = new ProjectionHall(nbSeats, false, false, false,i);
		}

		TicketStation[] t=new TicketStation[ticketStation];
		
		for(int i=0;i<ticketStation;i++){
			t[i] = new TicketStation(c);
		}
		
		
		Customer[] cust=new Customer[nbCustomers];
		Session[] s=new Session[nbSessions];
		
		for(int i=0;i<nbSessions;i++){
			s[i] = new Session(p[i],c,nbSessions);
			s[i].start();
		}
		
		
		Waiter w[] = new Waiter[nbWaiters];
		
		for(int i=0;i<nbWaiters;i++){
			w[i] = new Waiter(c);
		}
		
		
		PopcornMachine pop = new PopcornMachine(c);
		pop.setDaemon(true);
		pop.start();


		for(int i=0;i<nbCustomers;i++){
			cust[i] = new Customer(p,t,c,w);
			cust[i].start();
		}
	  }
	//else{
	//	System.out.println("Number of tickets sold for one session cannot be greater than ");
	//}
	

}
