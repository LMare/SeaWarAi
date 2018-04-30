package fr.lesprogbretons.seawar.ia;

import fr.lesprogbretons.seawar.model.Partie;
import fr.lesprogbretons.seawar.model.actions.Action;
import fr.lesprogbretons.seawar.model.actions.PassTurn;
import fr.lesprogbretons.seawar.model.boat.Boat;

import static fr.lesprogbretons.seawar.SeaWar.seaWarController;
import static fr.lesprogbretons.seawar.screen.SeaWarMapScreen.selectedTile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;





public class IAAleatoire extends AbstractIA {
	private int identifier;
    public IAAleatoire(int identifier) {
        super(identifier,"IAAleatoire");
    }


	@Override
	public Action chooseAction(Partie partie) {
		Random rnd = new Random();
		//System.out.println("Partie get possible actions");
		Boat boat=partie.getMap().getBateaux2().get(0);
		partie.setBateauSelectionne(boat);
		seaWarController.selection(boat.getPosition().getX(), boat.getPosition().getY());
		selectedTile.setCoords(boat.getPosition().getY(), boat.getPosition().getX());
		int iact=rnd.nextInt(partie.getPossibleActions().size());
		System.out.println(" Action index "+iact+"/"+partie.getPossibleActions().size());
		Action result=partie.getPossibleActions().get(iact);
		//seaWarController.endTurn();
		return result;
	}
}
