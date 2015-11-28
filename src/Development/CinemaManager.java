package Development;

public class CinemaManager {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Cinema c = new Cinema(10,false);
		ProjectionHall[] p = new ProjectionHall[1];
		p[0] = new ProjectionHall(10, false, false, false);
		TicketStation[] t=new TicketStation[1];
		t[0]=new TicketStation(c);
		Customer[] cust=new Customer[20];
		Session[] s=new Session[1];
		s[0]=new Session(p[0]);
		s[0].start();
		Waiter w = new Waiter(c);
		PopcornMachine pop = new PopcornMachine(c);
		pop.setDaemon(true);
		pop.start();
		for(int i=0;i<20;i++){
			cust[i] = new Customer(p,t,c,w);
			cust[i].start();
		}

	}

}
