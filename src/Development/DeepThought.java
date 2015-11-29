package Development;


public class DeepThought extends Thread{
	ProjectionHall p;
	private int sessionNumber;
	private int noOfSessions;
	private TicketStation t;
	public DeepThought(ProjectionHall p2, int sessionNumber,int noOfSessions, TicketStation t){
		this.p = p2;
		this.sessionNumber = sessionNumber;
		this.noOfSessions  = noOfSessions;
		this.t = t;
	}
	
	public void run(){
		try {
			sleep(10);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
		if(noOfSessions==sessionNumber){
			t.closeCinema();
		}
	}

}
