package Development;


public class Session extends Thread{
	private ProjectionHall p;
	private int i;
	public Session(ProjectionHall p2){
		this.p = p2;
	}

	public void run(){
		for(i=0;i<2;i++){
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
}
