package fr.lesprogbretons.seawar.model.actions;

import fr.lesprogbretons.seawar.model.Partie;
import fr.lesprogbretons.seawar.model.boat.Boat;

public class Shooter extends Action{

	private Boat targetBoat;
	private Partie partie;
	
	public Shooter(Partie partie,Boat targetBoat) {
		this.targetBoat=targetBoat;
		this.partie=partie;
		
		
	}
	
	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return new Shooter(partie,targetBoat);
	}

	@Override
	public void apply(Partie state) {
		// TODO Auto-generated method stub
		System.out.println("Shooting enemy boat");
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Shooting the enemy...";
	}

}
