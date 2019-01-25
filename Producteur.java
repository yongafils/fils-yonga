
import java.util.Random;

public class Producteur implements Runnable{

	private Tampon boite;
	private String nom;
	private Random random;
	int i = 0;
	
	public Producteur(Tampon boite, String nom, Random random) {
		super();
		this.boite = boite;
		this.nom = nom;
		this.random = random;
	}


	@Override
	public void run() {
	
		
		while(true){
			try {
				Thread.sleep(Math.abs(random.nextInt()%1000)); //sleep de quelques secondes juste pour la visualisation du programme sur la console
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			 
				
				boite.produire(this.nom +"a produit le message a la position "+boite.getIn()); //un thread produit un message
			
			
		}
	}
}
