package Test;


public class DeepThought extends Thread{
	private ProjectionHall p;
	private int sessionNumber;
	private int noOfSessions;
	private Cinema c;
	public DeepThought(ProjectionHall p2,int sessionNumber, int noOfSessions,Cinema c){
		this.p = p2;
		this.sessionNumber = sessionNumber;
		this.noOfSessions = noOfSessions;
		this.c = c;
	}
	
	public void run(){
		try {
			sleep(10);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Deep Thought is trying to initialize its projector for session number "+sessionNumber);
		p.startCinema(sessionNumber);
			try {
				sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		p.endCinema(sessionNumber);
		System.out.println("Deep Thought has finished the projection for session number "+sessionNumber);
		if(noOfSessions==sessionNumber){
			c.cinemaCloseNote();
		}
	}

}
