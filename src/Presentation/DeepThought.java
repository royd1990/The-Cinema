package Presentation;


public class DeepThought extends Thread{
	ProjectionHall p;
	public DeepThought(ProjectionHall p2){
		this.p = p2;
	}
	
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
	}

}
