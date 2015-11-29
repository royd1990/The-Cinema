package Development;
/**
 * This class represents another monitor, the projection hall
 * which handles access of Customers, Session and Deepthought thread
 * to various shared variables in a syncrhonized fashion.
 * @author Debaditya Ravish
 *
 */
public class ProjectionHall {
	private int nbPlaces;
	private int totalSeats;
	private boolean sessionStarted,sessionEnded,cinemaEnded;
	
	/**
	 * This is constructor to initialize the projection hall
	 * @param nbPlaces		represents the number of seats in a projection hall
	 * @param sessionStarted This variable is to indicate whether the movie session has started or not.
	 * @param sessionEnded	This variable is to indicate whether the movie session has ended or not.
	 * @param cinemaEnded	This variable is to indicate whether the movie has started or not.
	 */
	public ProjectionHall(int nbPlaces,boolean sessionStarted, boolean sessionEnded, boolean cinemaEnded){
		this.nbPlaces = nbPlaces;
		this.sessionStarted = sessionStarted;
		this.sessionEnded	= sessionEnded;
		this.totalSeats = nbPlaces;			
		
	}
	
	/**
	 * This method is called by the session thread to announce the start of a session. Once the session has started
	 * it notifies all the threads that are waiting on the start of the session.
	 */
	public synchronized void sessionStart(){
		sessionStarted=true;
		System.out.println("Session has been started by "+Thread.currentThread().getName());
		notifyAll();//This is a notifyAll() because we have to notify all the customer threads(to enter projection
					// room as well as the deepthought thread to start the movie.
	}
	
	/**
	 * This method is called by the session thread to indicate the ned of a session. Once the session has ended it notifies
	 * all the customer threads to leave the projection room.
	 */
	public synchronized void sessionEnd(){
		while(cinemaEnded==false){
			try {
				System.out.println("Session is yet to be ended by "+Thread.currentThread().getName());
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		sessionStarted = false;
		sessionEnded = true;
		System.out.println("Session has been ended by "+Thread.currentThread().getName());
		notifyAll();	//This is a notifyAll because we must notify all the customer threads about the session end
						//and permit them to leave the projection room.
	}
	
	/**
	 * This method is called by the Deepthought thread to start a cinema session.
	 */
	public synchronized void startCinema(){
		while(sessionStarted==false){	//Deepthought thread checks every time whether session thread has started a session after being
										//woken up.
			try {
				System.out.println("Cinema is yet to be started by "+Thread.currentThread().getName());
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Cinema has been started by "+Thread.currentThread().getName());
		cinemaEnded = false;		
	}
	
	/**
	 * This method is called by the Deepthought to indicate the end of a cinema.
	 */
	public synchronized void endCinema(){
		cinemaEnded = true;
		System.out.println("Cinema has been ended by "+Thread.currentThread().getName());
		notify();		//This is a notify because we need to wake up only current session thread which waits on the variable
						// cinemaEnded.
		
	}
	
	/**
	 * This method is called by the customer threads to enter the projection room.
	 */
	public synchronized void enterHall(){
		while(nbPlaces==0 || sessionStarted==false){ //This is a while because the customer thread must check every time after waking up
			 										 //whether the session has started or not.
			try {
				System.out.println("The customer "+Thread.currentThread().getId()+" is waiting to enter the projection hall.");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		nbPlaces--;
		System.out.println("The customer "+Thread.currentThread().getId()+" is going to enter the projection hall.");
	}
	/**
	 * This method is called by the customer threads to exit the movie hall
	 */
	
	public synchronized void exitHall(){
		if(sessionEnded==false){			//This is a if before once the session has ended and the customers have been 
											//notified there is no chance the sessionEnded variable will change again so there
											//is no need to check.
			try {
				System.out.println("The customer "+Thread.currentThread().getId()+" is waiting for the movie to finish.");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		nbPlaces++;
		System.out.println("The customer "+Thread.currentThread().getId()+" will exit the projection hall.");
		/**
		 * This part of the code is for reinitializing the states before starting the next session.
		 * The last customer changes the state before exiting.
		 */
		if(nbPlaces==totalSeats){
			cinemaEnded=false;//Exit
			sessionEnded=false;
		}
		
		

	}

}
