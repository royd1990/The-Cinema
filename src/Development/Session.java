package Development;


public class Session extends Thread{
	private ProjectionHall p;
	private int i;
	private final int noOfSessions=3;
	private TicketStation t;
	public Session(ProjectionHall p2, TicketStation t){
		this.p = p2;
		this.t = t;
	}

	public void run(){
		for(i=0;i<noOfSessions;i++){
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			DeepThought ai = new DeepThought(p,(i+1),noOfSessions,t);
			ai.start();
			p.sessionStart();
			p.sessionEnd();
		}
	}
}
