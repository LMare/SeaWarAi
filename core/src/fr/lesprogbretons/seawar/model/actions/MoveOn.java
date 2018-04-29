package fr.lesprogbretons.seawar.model.actions;

import fr.lesprogbretons.seawar.model.boat.Boat;
import fr.lesprogbretons.seawar.model.cases.Case;

public abstract class MoveOn extends Action {
	protected final Boat boat;
	protected final Case target;
	public MoveOn(Boat boat,Case target){
		super();
		this.boat=boat;
		this.target=target;
		
		
	}

	
	public Boat getBoat() {
		
		return boat;
		
		
	}
	
	public Case getTarget() {
		return target;
		
	}
	
	
	
}
