
import java.util.Random;

public class Election {
	

	
		
		static class Message{
			
			Participant process;
			Message(Participant p){
				
				process=p;
			}
		}
		
		static class MessageBox{
			
			int entries,maxEntries;
			Object[] elements;
			
			public MessageBox(int number)
			{
				maxEntries=number;
				elements=new Object[maxEntries];
				entries=0;
			}
		
		
		synchronized void send(Object msg)throws InterruptedException{
			
			while(entries==maxEntries)
				wait();
			elements[entries]=msg;
			entries=entries+1;
			notifyAll();
		}
		
		synchronized Object recieve()throws InterruptedException{
			
			while(entries==0)
				wait();
			Object x;
			x=elements[0];
			for(int i=1;i<entries;i++)
				elements[i-1]=elements[i];
			entries=entries-1;
			notifyAll();
			return x;
		}
		}

		static class Participant extends Thread{
			
			MessageBox inbox;
			MessageBox[]neighbour;
			int value;
			
			Participant leader;
			Participant self;
			
			public void run(){
				
				leader=this;
				self=this;
				
				for(int i=0;i<neighbour.length;i++)
					
					try{
						neighbour[i].send(new Message(self));
					}catch(Exception e){}
					
					try{while(true){
						Message m=(Message)inbox.recieve();
						System.out.println(value+" Recoit "+m.process.value);
						
						if(m.process.value>leader.value)
							leader=m.process;
					
					}
					
			}catch(Exception e){}
			
		}
	}
		
	public static void main(String args[]){
		
		Tampon boite = new Tampon();
		Random random = new Random();
		
		
		
		
		final int processNo=6;
		final int[] value=new int[processNo];
		
		Random randomGenerator=new Random();
		
		
		//Assigning Random ID to the process
		for(int i=0;i<value.length;i++){
			
			value[i]=randomGenerator.nextInt(100);
		}
		
		Participant[] processes=new Participant[processNo];
		MessageBox[] box=new MessageBox[processNo];
		
		for(int i=0;i<processNo;i++){
			
			processes[i]=new Participant();
			processes[i].value=i;
			box[i]=new MessageBox(4);
			
		}
		
		for(int i=0;i<processNo;i++){
			processes[i].inbox=box[i];
			processes[i].neighbour=new MessageBox[processNo];
		}
		
		for(int i=0;i<processNo;i++){
			for(int j=0;j<processNo;j++)
				processes[i].neighbour[j]=processes[j].inbox;
		}
		
		for(int i=0;i<processNo;i++)
			processes[i].start();
		
		try{
			Thread.sleep(100);
		}catch(Exception e){}
		
		
		for(int i=0;i<processNo;i++)
			processes[i].interrupt();
		int j=0; int i=0;
		for(;i<processNo;i++)
		{
			if(processes[i].leader!=null)
				System.out.println(processes[i].value+" a elu  comme consomateur "+processes[i].leader.value);
			System.out.println("le thread  "+processes[i].leader.value +"  "+ " a ete elu ");
			j=processes[i].leader.value	;
		
		}
		System.out.println("ici on commence a produit et e consomme ");
		
		Producteur prod1 = new Producteur(boite, "thread 0 ", random);
		Producteur prod2 = new Producteur(boite, "thread 1 ", random);
		Producteur prod3 = new Producteur(boite, "thread 2 ", random);
		Producteur prod4 = new Producteur(boite, "thread 3 ", random);
		Producteur prod5 = new Producteur(boite, "thread 4 ", random);
		
		Consommateur conso = new Consommateur(boite, j, random);
		
		Thread t1 = new Thread(prod1);
		Thread t2 = new Thread(prod2);
		Thread t3 = new Thread(prod3);
		Thread t4 = new Thread(prod4);
		Thread t5 = new Thread(prod5);
		
		Thread t6 = new Thread(conso);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
	}
		
	}


