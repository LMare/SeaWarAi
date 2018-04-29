package fr.lesprogbretons.seawar.model.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.lesprogbretons.seawar.model.Partie;
import fr.lesprogbretons.seawar.model.boat.Boat;
import fr.lesprogbretons.seawar.model.cases.Case;

// very bad programming way in my o
import static fr.lesprogbretons.seawar.screen.SeaWarMapScreen.selectedTile;
import static fr.lesprogbretons.seawar.SeaWar.seaWarController;
import static fr.lesprogbretons.seawar.SeaWar.logger;

public class MoveOnBoat extends Action {

	private List<Boat> boats;
	
	
	public MoveOnBoat() {
		super();
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object clone() {
		MoveOnBoat moveOnBoat =new MoveOnBoat();
		return moveOnBoat;
	}

	@Override
	public void apply(Partie partie) {
		
		System.out.println("Move the selected boat");
		int cantPlay = 0;
        Random rnd = new Random();

        //Get player 2 boats
        ArrayList<Boat> boats = partie.getMap().getBateaux2();
        for (Boat b : boats) {
        	//
        	if (b.getMoveAvailable() == 0 || partie.getMap().getCasesDisponibles(b.getPosition(), 1).isEmpty()) cantPlay++;
            logger.debug("Nb : " + cantPlay);
        }

        if (cantPlay == boats.size()) {
            seaWarController.endTurn();
        } else {
            if (!partie.isAnyBateauSelectionne()) {
                Boat b;
                do {
                    int nb = rnd.nextInt(boats.size());
                    b = boats.get(nb);
                } while (partie.getMap().getCasesDisponibles(b.getPosition(), 1).isEmpty() || b.getMoveAvailable() == 0);
                seaWarController.selection(b.getPosition().getX(), b.getPosition().getY());
                selectedTile.setCoords(b.getPosition().getY(), b.getPosition().getX());
                logger.debug("IAAleatoire sélection : " + b.getPosition().getX() + ";" + b.getPosition().getY());

            } else {
                Boat boat = partie.getBateauSelectionne();
                ArrayList<Case> cases = partie.getMap().getCasesDisponibles(boat.getPosition(), 1);
                if (!cases.isEmpty()) {
                    int nb = rnd.nextInt(cases.size());
                    //On se déplace
                    seaWarController.selection(cases.get(nb).getX(), cases.get(nb).getY());
                    selectedTile.setCoords(cases.get(nb).getY(), cases.get(nb).getX());
                    logger.debug("IAAleatoire déplacement : " + cases.get(nb).getX() + ";" + cases.get(nb).getY());
                } else {
                    //Si on ne peut plus se déplacer
                    partie.unselectBateau();
                }
                ArrayList<Case> boatInRange = partie.getMap().getBoatInRange(boat, partie.getOtherPlayer());
                if (!boatInRange.isEmpty() && boat.canShoot()) {
                    int nb = rnd.nextInt(boatInRange.size());
                    seaWarController.selection(boatInRange.get(nb).getX(), boatInRange.get(nb).getY());
                    selectedTile.setCoords(boatInRange.get(nb).getY(), boatInRange.get(nb).getX());
                    logger.debug("IAAleatoire coup : " + boatInRange.get(nb).getX() + ";" + boatInRange.get(nb).getY());
                }
            }
        }

		
	}

	public List<Boat> getBoats() {
		return boats;
	}

	public void setBoats(List<Boat> boats) {
		this.boats = boats;
	}

	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Move on the boat";
	}
	
	
	

}
