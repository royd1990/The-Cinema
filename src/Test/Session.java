package Test;


public class Session extends Thread{
	private ProjectionHall p;
	private int i;
	private int noOfSessions;
	//private TicketStation t;
	private Cinema c;
	
	public Session(ProjectionHall p2,Cinema c,int nbSessions){
		this.p = p2;
		//this.t = t;
		this.c = c;
		this.noOfSessions = nbSessions;
	}

	public void run(){
		for(i=0;i<noOfSessions;i++){
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int j = i+1;
			DeepThought ai = new DeepThought(p,j,noOfSessions,c);
			ai.start();
			p.sessionStart(i);
			p.sessionEnd(i);
		}
	}
}
