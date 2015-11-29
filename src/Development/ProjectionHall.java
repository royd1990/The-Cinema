package Development;

public class ProjectionHall {
	private int nbPlaces;
	private int totalSeats;
	private boolean sessionStarted,sessionEnded,cinemaEnded;
	
	public ProjectionHall(int nbPlaces,boolean sessionStarted, boolean sessionEnded, boolean cinemaEnded){
		this.nbPlaces = nbPlaces;
		this.sessionStarted = sessionStarted;
		this.sessionEnded	= sessionEnded;
		this.totalSeats = nbPlaces;
		
	}
	
	public synchronized void sessionStart(){
		sessionStarted=true;
		System.out.println("Session has been started by "+Thread.currentThread().getName());
		notifyAll();
	}
	
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
		notifyAll();
	}
	
	public synchronized void startCinema(){
		while(sessionStarted==false){
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
	
	public synchronized void endCinema(){
		cinemaEnded = true;
		System.out.println("Cinema has been ended by "+Thread.currentThread().getName());
		notify();		
		
	}
	
	public synchronized void enterHall(){
		while(nbPlaces==0 || sessionStarted==false){
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
	
	public synchronized void exitHall(){
		if(sessionEnded==false){
			try {
				System.out.println("The customer "+Thread.currentThread().getId()+" is waiting for the movie to finish.");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		nbPlaces++;
		System.out.println("The customer "+Thread.currentThread().getId()+" will exit the projection hall.");
		if(nbPlaces==totalSeats){
			cinemaEnded=false;//Exit
			sessionEnded=false;
		}
		
		

	}

}
