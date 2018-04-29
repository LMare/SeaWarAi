package fr.lesprogbretons.seawar.ia;

import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.lesprogbretons.seawar.model.Partie;
import fr.lesprogbretons.seawar.model.actions.Action;
import fr.lesprogbretons.seawar.model.cases.CaseEau;

public class IAThread extends Thread{

	
	private  AbstractIA ia;
	
	private  ExecutorService executorService;
	private Partie partie;
	
	private Action choosedAction;
	
	
	public IAThread(AbstractIA ia,Partie partie, ExecutorService executorService) {
		super("Calcul");
		setName("Calcul");
		this.ia=ia;
		this.executorService=executorService;
		this.partie=partie;
		this.choosedAction=null;
		
	}
	
	public Action getActionChoice() {
		return choosedAction;
		
	}
	
	
	public void run() {
      while (true) {
          try {
        	  // action choisie
                 choosedAction=ia.chooseAction((Partie)partie.clone());
          } catch (Exception ex) {

				Logger.getLogger(Partie.class.getName()).log(Level.SEVERE,null,ex);
			  }

          finally {
        	  
        	  executorService.shutdown();
        	  
          }

      }
  }
	
	
}
