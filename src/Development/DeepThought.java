package Development;
/**
 * This class is the Deepthought thread which has three actions:
 * Start the cinema, end the cinema and finally if all the programmed session
 * have ended notify the ticket stations to close the cinema.
 * @author Debaditya Ravish
 *
 */

public class DeepThought extends Thread{
	ProjectionHall p;
	private int sessionNumber;
	private int noOfSessions;
	private TicketStation t;
	private Cinema c;
	
	/**
	 * This is the constructor for the Deepthought thread class
	 * @param p2			The projection hall on which the movie should run
	 * @param sessionNumber This parameter indicates the session number
	 * @param noOfSessions	This parameter indicates the no of sessions that are to be run
	 * @param t				This parameter indicates the ticket station.
	 */
	public DeepThought(ProjectionHall p2, int sessionNumber,int noOfSessions, TicketStation t, Cinema c){
		this.p = p2;
		this.sessionNumber = sessionNumber;
		this.noOfSessions  = noOfSessions;
		this.t = t;
		this.c = c;
	}
	
	/**
	 * This is the run method of the Deepthought thread.
	 */
	public void run(){
		try {
			sleep(10);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(noOfSessions==sessionNumber){
			t.closeCinema();
			c.closeCinema();
			
		}
		System.out.println("Deep Thought is trying to initialize its projector for session Number "+sessionNumber);
		p.startCinema();
			try {
				sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		p.endCinema();
		System.out.println("Deep Thought has finished the projection for session Number "+sessionNumber);
		
	}

}
