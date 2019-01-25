
import java.util.Random;

public class Consommateur implements Runnable {
	private Tampon boite;
	private int val;
	private Random random;
	
	
	public Consommateur(Tampon boite, int valeur, Random random) {
		super();
		this.boite = boite;
		this.val = valeur;
		this.random = random;
	}

	


	@Override
	public void run() {
		
		while(true){
			try {
				Thread.sleep(Math.abs(random.nextInt()%1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String m = boite.consommer();//le consommateur consomme un message
			System.out.println("le consommateur "+this.val +" a consomme le message de la position" + " "+boite.getOut()); // affiche le message consomm�
		}
		
	}
}
