package Presentation;

public class Cinema {
	private int nbTickets;
	
	public Cinema(int nbTickets){
		this.nbTickets = nbTickets;
	}
	public synchronized boolean takeTickets(){
		if(nbTickets!=0){
			nbTickets--;
			return true;
		}
		else
		{
			return false;
		}
	}

}
