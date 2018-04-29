package fr.lesprogbretons.seawar.model.actions;

import fr.lesprogbretons.seawar.model.Partie;
import fr.lesprogbretons.seawar.model.boat.Boat;

public class PassTurn extends Action {
	private Partie partie;
	private Boat boat;
	
	public PassTurn(Partie partie, Boat boat) {
		this.partie=partie;
		this.boat=boat;
		
	}
	
	@Override
	public Object clone() {
		return new PassTurn(partie, boat);
	}

	@Override
	public void apply(Partie partie) {
		//state.endTurn();
		System.out.println("this boat doesn't play in this turn");
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Prefer to withdrawing, it better ";
	}

}
