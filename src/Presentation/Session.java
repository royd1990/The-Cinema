package Presentation;


public class Session extends Thread{
	ProjectionHall p;
	public Session(ProjectionHall p2){
		this.p = p2;
	}

	public void run(){
		try {
			sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		DeepThought ai = new DeepThought(p);
		ai.start();
		p.sessionStart();
		p.sessionEnd();
	}

}
