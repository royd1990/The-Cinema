package Denouement;

public class ProjectionHall {
	private int nbPlaces;
	private boolean sessionStarted,sessionEnded,cinemaEnded;
	private int id;
	private int totalSeats;
	private boolean allExited;
	
	public ProjectionHall(int nbPlaces,boolean sessionStarted, boolean sessionEnded, boolean cinemaEnded,int id){
		this.nbPlaces = nbPlaces;
		this.sessionStarted = sessionStarted;
		this.sessionEnded	= sessionEnded;
		this.id = id;
		allExited = false;
		this.totalSeats = nbPlaces;
		
	}
	
	public synchronized void sessionStart(int i){
		if(i!=0)
		{
			while(allExited==false){
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			sessionStarted = true;
			sessionEnded = false;
			allExited=false;	
		}
		else{
			sessionStarted=true;
			sessionEnded = false;
		}
		int j = i+1;	
		System.out.println("Session "+j+" has been started by "+Thread.currentThread().getName()+" in projection hall "+id);
		notifyAll();
	}
	
	public synchronized void sessionEnd(int i){
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
		int j=i+1;
		System.out.println("Session "+j+" has been ended by "+Thread.currentThread().getName()+" in projection hall "+id);
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
		notifyAll();		
		
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
		
		while(sessionEnded==false){
			try {
				System.out.println("The customer "+Thread.currentThread().getId()+" is waiting for the movie to finish "+" in projection hall "+id);
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		nbPlaces++;
		System.out.println("The customer "+Thread.currentThread().getId()+" will exit the projection hall "+id);
		if(nbPlaces==totalSeats){
			System.out.println("Last customer comes and seat number is "+nbPlaces);
			allExited=true;
			cinemaEnded=false;//Exit
			sessionEnded=false;
			notifyAll();
			
		}
		
		
		

	}

}
