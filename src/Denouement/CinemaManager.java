package Denouement;


public class CinemaManager {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Cinema c = new Cinema(20,false);
		
		ProjectionHall[] p = new ProjectionHall[2];
		
		for(int i=0;i<2;i++){
			p[i] = new ProjectionHall(20, false, false, false,i);
		}

		TicketStation[] t=new TicketStation[3];
		
		for(int i=0;i<3;i++){
			t[i] = new TicketStation(c);
		}
		
		
		Customer[] cust=new Customer[5];
		Session[] s=new Session[2];
		
		for(int i=0;i<2;i++){
			s[i] = new Session(p[i]);
			s[i].start();
		}
		
		
		Waiter w[] = new Waiter[3];
		
		for(int i=0;i<3;i++){
			w[i] = new Waiter(c);
		}
		
		
		PopcornMachine pop = new PopcornMachine(c);
		pop.setDaemon(true);
		pop.start();


		for(int i=0;i<5;i++){
			cust[i] = new Customer(p,t,c,w);
			cust[i].start();
		}

	}

}
