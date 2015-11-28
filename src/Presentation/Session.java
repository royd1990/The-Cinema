package Presentation;

/**
 * This class represents a session thread responsible for starting and ending 
 * a movie session.
 * @author Debaditya Ravish
 *
 */
public class Session extends Thread{
	private ProjectionHall p;
	private TicketStation t;
	/**
	 * This is a constructor for the object, which is initialized by the projection
	 * hall attached with the session.
	 * @param p2 This represents the projection hall on which the movie session will be displayed.
	 */
	public Session(ProjectionHall p2, TicketStation t){
		this.p = p2;
		this.t = t;
	}
/**
 * This is the run method of the session thread. A session thread
 * performs three actions: It initializes a deepthought thread to project the movie. It declares
 * the start of a session and it declares the end of a session.
 */
	public void run(){
		try {
			sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		DeepThought ai = new DeepThought(p,t);
		ai.start();
		p.sessionStart();
		p.sessionEnd();
	}

}
