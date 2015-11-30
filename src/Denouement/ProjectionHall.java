package Denouement;

public class ProjectionHall {
	private int nbPlaces;
	private boolean sessionStarted,sessionEnded,cinemaEnded;
	private int id;
	private boolean allExited;
	
	public ProjectionHall(int nbPlaces,boolean sessionStarted, boolean sessionEnded, boolean cinemaEnded,int id){
		this.nbPlaces = nbPlaces;
		this.sessionStarted = sessionStarted;
		this.sessionEnded	= sessionEnded;
		this.id = id;
		
	}
	
	public synchronized void sessionStart(){

		sessionStarted=true;
		System.out.println("Session has been started by "+Thread.currentThread().getName()+" in projection hall "+id);
		notifyAll();
	}
	
	public synchronized void sessionEnd(){
		while(cinemaEnded==false){
			try {
				System.out.println("Session is yet to be ended by "+Thread.currentThread().getName()+" in projection hall "+id);
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		sessionStarted = false;
		sessionEnded = true;
		System.out.println("Session has been ended by "+Thread.currentThread().getName()+" in projection hall "+id);
		notifyAll();
	}
	
	public synchronized void startCinema(){
		while(sessionStarted==false){
			try {
				System.out.println("Cinema is yet to be started by "+Thread.currentThread().getName()+" in projection hall "+id);
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Cinema has been started by "+Thread.currentThread().getName()+" in projection hall "+id);
		cinemaEnded = false;		
	}
	
	public synchronized void endCinema(){
		cinemaEnded = true;
		System.out.println("Cinema has been ended by "+Thread.currentThread().getName()+" in projection hall "+id);
		notify();		
		
	}
	
	public synchronized void enterHall(){
		while(nbPlaces==0 || sessionStarted==false){
			try {
				System.out.println("The customer "+Thread.currentThread().getId()+" is waiting to enter the projection hall "+id);
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		nbPlaces--;
		System.out.println("The customer "+Thread.currentThread().getId()+" is going to enter the projection hall "+id);
	}
	
	public synchronized void exitHall(){
		
		if(sessionEnded==false){
			try {
				System.out.println("The customer "+Thread.currentThread().getId()+" is waiting for the movie to finish "+" in projection hall "+id);
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		nbPlaces++;
		cinemaEnded=false;//Exit
		sessionEnded=false;
		System.out.println("The customer "+Thread.currentThread().getId()+" will exit the projection hall "+id);
		
		

	}

}
