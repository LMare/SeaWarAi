package fr.lesprogbretons.seawar.ia;

import fr.lesprogbretons.seawar.model.Partie;
import fr.lesprogbretons.seawar.model.actions.Action;
import java.util.List;


public class IAAleatoire extends AbstractIA {
	private int identifier;
    public IAAleatoire(int identifier) {
        super(identifier,"IAAleatoire");
    }


	@Override
	public Action chooseAction(Partie partie) {
		List<Action> actions = partie.getPossibleActions();
		Action result = actions.get((int) (Math.random()*(actions.size() - 0.01)));
		return result;
	}
}
