package Development;

/**
 * This class represents a session thread responsible for starting and ending 
 * a movie session.
 * @author Debaditya Ravish
 *
 */
public class Session extends Thread{
	private ProjectionHall p;
	private int i;
	private  int noOfSessions;
	private TicketStation t;
	private Cinema c;
	/**
	 * This class is the constructor for the Session object
	 * @param p2	Represents a projection hall
	 * @param t		Represents a ticket station
 	 */
	public Session(ProjectionHall p2, TicketStation t, Cinema c, int nbSessions){
		this.p = p2;
		this.t = t;
		this.c = c;
		this.noOfSessions = nbSessions;
	}

	/**
	 * This is the run method for the session thread.
	 * Consists of three actions:Start a DeepThought thread, Start the session, end the session.
	 */
	public void run(){
		for(i=0;i<noOfSessions;i++){
		//	try {
		//		sleep(1000);
		//	} catch (InterruptedException e) {
		//		e.printStackTrace();
	//		}
			int j=i+1;
			DeepThought ai = new DeepThought(p,j,noOfSessions,t,c);
			ai.start();
			p.sessionStart(i);
			p.sessionEnd(i);
		}
	}
}
