package Presentation;

/**
 * This class represents the deepthough AI which is responsible for projecting movies in 
 * the projection halls. This is thread that gets started from inside a session thread.
 * @author Debaditya Ravish
 *
 */

public class DeepThought extends Thread{
	private ProjectionHall p;
	private TicketStation t;
/**
 * Constructor to initialized the AI with the projection hall
 * on which it is supposed to project a movie.
 * @param p2	Projection hall reference
 */
	public DeepThought(ProjectionHall p2, TicketStation t){
		this.p = p2;
		this.t = t;
	}

/**
 * This is the run method which defines three operations for the AI thread:
 * start cinema, end cinema and notify ticket station. To define the projection time we have a call to the
 * Sleep method.
 */
	public void run(){
		try {
			sleep(10);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Deep Thought is trying to initialize its projector");
		p.startCinema();
			try {
				sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		p.endCinema();
		System.out.println("Deep Thought has finished the projection");
		t.endMovie();
		System.out.println("Deep Thought has notified the ticketstation to stop giving tickets");
	}

}
